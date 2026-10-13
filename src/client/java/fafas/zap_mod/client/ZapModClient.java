package fafas.zap_mod.client;

import com.mojang.blaze3d.platform.InputConstants;
import fafas.zap_mod.ZapMod;
import fafas.zap_mod.client.particles.AuraParticle1;
import fafas.zap_mod.client.particles.AuraParticle2Gre;
import fafas.zap_mod.client.particles.AuraParticle2Yel;
import fafas.zap_mod.client.render.AuraRender;
import fafas.zap_mod.client.render.CallRenderEntrypoint;
import fafas.zap_mod.items.ModItems;
import fafas.zap_mod.particles.ModParticles;
import fafas.zap_mod.sounds.CustomModSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.phys.Vec3;


public class ZapModClient implements ClientModInitializer {

    private net.minecraft.world.item.ItemStack lastItem = net.minecraft.world.item.ItemStack.EMPTY;
    private int counter01 = 0;
    private int counter02 = 0;
    private int cooldown01 = 0;
    private int cooldown02 = 0;
    private SoundInstance currentSound = null;
    boolean triggerSecondRing = false;
    boolean skill1Active = false;

    public static KeyMapping armorSkill1;
    public static KeyMapping armorSkill2;

    @Override
    public void onInitializeClient() {
        LivingEntityRenderLayerRegistrationCallback.EVENT.register(((entityType, livingEntityRenderer, registrationHelper, context) ->{
            if (livingEntityRenderer instanceof AvatarRenderer playerRenderer){
                registrationHelper.register(new AuraRender(playerRenderer));
            }
        }
        ));
        ParticleProviderRegistry.getInstance().register(ModParticles.AURA1_PARTICLE, AuraParticle1.Provider::new); //registro das particulas
        ParticleProviderRegistry.getInstance().register(ModParticles.AURA2_PARTICLE_YEL, AuraParticle2Yel.Provider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.AURA2_PARTICLE_GRE, AuraParticle2Gre.Provider::new);

        armorSkill1 = KeyMappingHelper.registerKeyMapping(new KeyMapping( //adicionao maping da tecla pra pressionar
                "key.zap_mod.armor_skill1",
                InputConstants.KEY_R,
                KeyMapping.Category.register(Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "category.zap_mod.title"))
        ));
        armorSkill2 = KeyMappingHelper.registerKeyMapping(new KeyMapping( //adicionao maping da tecla pra pressionar
                "key.zap_mod.armor_skill2",
                InputConstants.KEY_C,
                KeyMapping.Category.register(Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "category.zap_mod.title2"))

        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null ) return; //checa se o player ta no mundo pra as varzea não quebrar
            //Inicio varzea do assobio.
            net.minecraft.world.item.ItemStack currentItem = client.player.getMainHandItem();
            if (!net.minecraft.world.item.ItemStack.isSameItem(currentItem, lastItem)) {
                // Verifica se a mão não está vazia agora (para não tocar som ao tirar o item da mão)
                if (currentItem.is(ModItems.WHATS_APP)) {
                    // Toca o som.
                    // Parâmetros: Som, Volume, Pitch
                    client.player.playSound(CustomModSounds.WHISTLE1, 1.0f, 1.0f);//assobia ao pegar o zap
                } else if (currentItem.is(ModItems.ZAP2)) {
                    client.player.playSound(CustomModSounds.WHISTLE2, 1.0f, 1.0f);//assobia ao pegar o zap2
                }

            }
            lastItem = client.player.getMainHandItem();
            //fim varzea do assobio
            for (net.minecraft.world.entity.player.Player player : client.level.players()){
                double playerX = player.getX();
                double playerY = player.getY();
                double playerZ = player.getZ();

                boolean fullarmorzap =
                        player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.ZAP_HELMET) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP_CHESTPLATE) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP_LEGGINGS) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP_BOOTS);

                boolean fullarmorzap2 =
                        player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.ZAP2_HELMET) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP2_CHESTPLATE) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP2_LEGGINGS) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP2_BOOTS);
                //Inicio varzea do som na armadura.
                if (player == client.player) {
                    if(fullarmorzap2) {
                        counter01++;
                        //para parar o som no fim
                        if (counter01 >= 97 * 20 || currentSound == null) {
                            currentSound = SimpleSoundInstance.forMusic(CustomModSounds.ZAP_MUSIC);
                            client.getSoundManager().play(currentSound);
                            counter01 = 0;
                        }
                    }
                else{
                        if (currentSound != null) {
                            client.getSoundManager().stop(currentSound);
                            currentSound = null;
                        }
                    }
                }
                //fim varzea do som da armadura

                //skills
                while (fullarmorzap2 && cooldown01 >= 180 && armorSkill1.isDown()){ //skill 1 da zap2
                    client.player.playSound(CustomModSounds.CALL_SONG,1.0f,1.0f);
                    int width = client.getWindow().getGuiScaledWidth();
                    int height = client.getWindow().getGuiScaledHeight();
                    CallRenderEntrypoint.spawnSquares(7, width, height);
                    cooldown01 = 0;
                    triggerSecondRing = true;
                    skill1Active = true;
                    ZapClientState.setUsingSkill1(true);
                }
                while (fullarmorzap2 && cooldown02 >= 30 && armorSkill2.isDown()) { //skill 2 da zap2
                    client.player.playSound(CustomModSounds.WHISTLE1, 1.0f, 2.0f);
                    if (client.player.isFallFlying()) {

                        Vec3 lookVector = client.player.getLookAngle();//gets player looking angle
                        double boost = 2.0;
                        Vec3 currentVelocity = client.player.getDeltaMovement();

                        client.player.setDeltaMovement(
                                currentVelocity.x + lookVector.x * boost,
                                currentVelocity.y + lookVector.y * boost,
                                currentVelocity.z + lookVector.z * boost
                        );

                    cooldown02 =0;
                    }
                }
                while (fullarmorzap && cooldown01 >= 120 && armorSkill1.isDown()){
                    int width = client.getWindow().getGuiScaledWidth();
                    int height = client.getWindow().getGuiScaledHeight();
                    CallRenderEntrypoint.spawnSquares(1, width, height);
                    client.player.playSound(CustomModSounds.CALL_SONG,1.0f,1.0f);
                    cooldown01 = 0;
                    triggerSecondRing = true;
                    skill1Active = true;
                    ZapClientState.setUsingSkill1(true);
                }
                cooldown01++;
                cooldown02++;
                if(triggerSecondRing && cooldown01>= 48){
                    client.player.playSound(CustomModSounds.CALL_SONG,1.0f,1.0f);
                    triggerSecondRing=false;
                }
                if (cooldown01 >= 110 && skill1Active){
                    CallRenderEntrypoint.sqrClear();
                    skill1Active = false;
                }
                if(cooldown01>=15){
                    ZapClientState.setUsingSkill1(false);
                }


                if (fullarmorzap2) {
                    if (counter02 % 4 == 0) {
                        client.level.addParticle(//faz a particula do mod
                                ModParticles.AURA1_PARTICLE,
                                playerX, playerY, playerZ,
                                0, 0, 0
                        );
                    }
                    if (counter02 % 7 == 0) {
                        client.level.addParticle(
                                ModParticles.AURA2_PARTICLE_YEL,
                                playerX, playerY, playerZ,
                                0, 0, 0
                        );
                    }

                    //para particula de fumaça
                    double varx = (RandomSource.create().nextDouble() - 0.5) * 3.5;
                    double varz = (RandomSource.create().nextDouble() - 0.5) * 3.5;
                    double px = playerX + varx;
                    double pz = playerZ + varz;
                    double ux = varx / 8;
                    double vy = RandomSource.create().nextDouble() * 0.6;
                    double wz = varz / 8;
                    client.level.addParticle(ParticleTypes.WHITE_SMOKE, px, playerY, pz, ux, vy, wz);
                }
                if (fullarmorzap){
                    if(counter02 % 4 == 0) {
                        client.level.addParticle(//faz a particula do mod
                                ModParticles.AURA1_PARTICLE,
                                playerX, playerY, playerZ,
                                0, 0, 0
                        );
                    }
                    if(counter02 % 7 ==0){
                        client.level.addParticle(
                                ModParticles.AURA2_PARTICLE_GRE,
                                playerX, playerY, playerZ,
                                0, 0, 0
                        );
                    }

                }
            }
            counter02++;
            if (counter02 >= 100000){counter02 = 0;}
        });

    }
}
