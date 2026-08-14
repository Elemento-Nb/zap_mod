package fafas.zap_mod;

import fafas.zap_mod.blocks.ModBlocks;
import fafas.zap_mod.events.OnFullArmorExplodeCallback;
import fafas.zap_mod.events.ZapModEvents;
import fafas.zap_mod.items.ModItems;
import fafas.zap_mod.potions.ModPotions;
import fafas.zap_mod.sounds.CustomModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ZapMod implements ModInitializer {
    public static final String MOD_ID = "zap_mod";
    boolean hasLevitated = false;
    boolean hasExploded = false;
    int counter2 = 0;


    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        new ZapModEvents().onInitialize();
        ModItems.initialize();
        ModPotions.registerPotions();
        ModBlocks.initialize();
        CustomModSounds.registerSounds();
        LOGGER.info("Hello Fabric world!");

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ZapWorldGenerator.ZAP_ORE_PLACED_KEY
        );

        ServerTickEvents.END_SERVER_TICK.register(server -> {

            for (var player : server.getPlayerList().getPlayers()){

                boolean fullarmor =

                        player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.ZAP2_HELMET) &&
                        player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.ZAP2_CHESTPLATE) &&
                        player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.ZAP2_LEGGINGS) &&
                        player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.ZAP2_BOOTS);
                        AttributeInstance stepHeight = player.getAttribute(Attributes.STEP_HEIGHT);
                if (fullarmor){
                    counter2++;
                    if (!hasLevitated) {
                        player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 3 * 20, 0, false, false, false));
                        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 63, 5, false, false, false));
                        hasLevitated = true;
                    }
                    if (!hasExploded && counter2 >= 60) {
                        OnFullArmorExplodeCallback.EVENT.invoker().interact(player, player.position());
                        hasExploded = true;
                    }
                    if (counter2 >=60) {
                        stepHeight.setBaseValue(1.1f);
                        player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 41, 9, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 41, 2, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 41, 4, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 41, 4, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 41, 1, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.HASTE, 41, 4, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 41, 1, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 251, 0, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 41, 0, false, false, true));
                    }
                }
                else {
                    hasLevitated = false;
                    counter2 = 0;
                    hasExploded = false;
                    stepHeight.setBaseValue(0.6f);
                }
            }
        });
    }
    public static final TagKey<@NotNull Item> REPAIRS_ZAP = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "repairs_zap"));
}