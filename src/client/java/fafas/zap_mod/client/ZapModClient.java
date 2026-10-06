package fafas.zap_mod.client;

import fafas.zap_mod.client.particles.AuraParticle1;
import fafas.zap_mod.client.particles.AuraParticle2Gre;
import fafas.zap_mod.client.particles.AuraParticle2Yel;
import fafas.zap_mod.client.render.AuraRender;
import fafas.zap_mod.items.ModItems;
import fafas.zap_mod.particles.ModParticles;
import fafas.zap_mod.sounds.CustomModSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;


public class ZapModClient implements ClientModInitializer {

    private net.minecraft.world.item.ItemStack lastItem = net.minecraft.world.item.ItemStack.EMPTY;
    private int counter01 = 0;
    private int counter02 = 0;
    private SoundInstance currentSound = null;



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

            double playerX = client.player.getX();
            double playerY = client.player.getY();
            double playerZ = client.player.getZ();


            boolean fullarmorzap =
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModItems.ZAP_HELMET) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP_CHESTPLATE) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP_LEGGINGS) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP_BOOTS);

            boolean fullarmorzap2 =
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModItems.ZAP2_HELMET) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP2_CHESTPLATE) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP2_LEGGINGS) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP2_BOOTS);
            //Inicio varzea do som na armadura.
            if (fullarmorzap2){

                if(counter02 % 4 == 0) {
                    client.level.addParticle(//faz a particula do mod
                            ModParticles.AURA1_PARTICLE,
                            playerX, playerY, playerZ,
                            0, 0, 0
                    );
                }
                if(counter02 % 7 ==0){
                    client.level.addParticle(
                            ModParticles.AURA2_PARTICLE_YEL,
                            playerX, playerY, playerZ,
                            0, 0, 0
                    );
                }
                //para particula de fumaça
                double varx = (RandomSource.create().nextDouble()-0.5) * 3.5;
                double varz = (RandomSource.create().nextDouble()-0.5) * 3.5;
                double px = playerX + varx;
                double pz = playerZ + varz;
                double ux = varx / 8;
                double vy = RandomSource.create().nextDouble() * 0.6;
                double wz = varz / 8;
                client.level.addParticle(ParticleTypes.WHITE_SMOKE, px, playerY, pz, ux, vy, wz);

                counter01++;
                //para parar o som
                if (counter01 >= 97*20||currentSound == null) {
                    currentSound = SimpleSoundInstance.forMusic(CustomModSounds.ZAP_MUSIC);
                    client.getSoundManager().play(currentSound);
                    counter01 = 0;
                }
            }
            else {
                if(currentSound !=null){
                    client.getSoundManager().stop(currentSound);
                    currentSound = null;
                }
            }
            //Fim da varzea do som da armadura.
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
        counter02++;
            if (counter02 >= 100000){counter02 = 0;}
        });

    }
}
