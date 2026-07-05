package fafas.zap_mod;

import fafas.zap_mod.blocks.ModBlocks;
import fafas.zap_mod.items.ModItems;
import fafas.zap_mod.potions.ModPotions;
import fafas.zap_mod.sounds.CustomModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ZapMod implements ModInitializer {
    public static final String MOD_ID = "zap_mod";
    public int contadors = 0;
    public static final Identifier ENCANTAR_ESPADA_PACK = Identifier.fromNamespaceAndPath(MOD_ID,"zap_sword");


    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ModItems.initialize();
        ModPotions.registerPotions();
        ModBlocks.initialize();
        CustomModSounds.registerSounds();
        LOGGER.info("Hello Fabric world!");


        ServerTickEvents.END_SERVER_TICK.register(server -> {

            for (var player : server.getPlayerList().getPlayers()){
                boolean fullarmor =

                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModItems.ZAP2_HELMET) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP2_CHESTPLATE) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP2_LEGGINGS) &&
                        player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP2_BOOTS);

                if (fullarmor){
                    contadors++;
                    if (contadors >= 40) {
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.STRENGTH, 41, 9, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.RESISTANCE, 41, 2, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.SPEED, 41, 4, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.HEALTH_BOOST, 41, 4, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.REGENERATION, 41, 1, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.HASTE, 41, 4, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.JUMP_BOOST, 41, 1, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.NIGHT_VISION, 251, 0, false,false, true));
                        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.SATURATION, 41, 0, false,false, true));
                        contadors = 0;
                    }
                }
            }
        });
    }
    public static final TagKey<Item> REPARA_ZAP = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "repara_zap"));
}