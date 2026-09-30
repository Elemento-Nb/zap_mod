package fafas.zap_mod.client;

import fafas.zap_mod.client.particles.AuraParticle1;
import fafas.zap_mod.client.render.SimpleRender;
import fafas.zap_mod.items.ModItems;
import fafas.zap_mod.particles.ModParticles;
import fafas.zap_mod.sounds.CustomModSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;


public class ZapModClient implements ClientModInitializer {

    private net.minecraft.world.item.ItemStack lastItem = net.minecraft.world.item.ItemStack.EMPTY;
    private int counter01 = 0;
    private SoundInstance currentSound = null;



    @Override
    public void onInitializeClient() {
        SimpleRender.initialize();
        ParticleProviderRegistry.getInstance().register(ModParticles.AURA1, AuraParticle1.Provider::new);
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
            //fim varzea do assobio
            lastItem = client.player.getMainHandItem();

            //Inicio varzea do som na armadura.
            boolean fullarmor =
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModItems.ZAP2_HELMET) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP2_CHESTPLATE) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP2_LEGGINGS) &&
                    client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP2_BOOTS);

            if (fullarmor){
                counter01++;
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
        });

    }
}
