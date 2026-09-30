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
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE;


public class ZapMod implements ModInitializer {
    public static final String MOD_ID = "zap_mod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    Identifier STEP_MODIFIER_ID = Identifier.fromNamespaceAndPath("zap_mod", "step_height_mod");

    private static final Set<UUID> HAS_LEVITATED = new HashSet<>();
    private static final Set<UUID> HAS_EXPLODED = new HashSet<>();

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

            for (var player : server.getPlayerList().getPlayers()) {
                UUID playerId = player.getUUID();
                boolean fullarmor =
                        player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.ZAP2_HELMET) &&
                        player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.ZAP2_CHESTPLATE) &&
                        player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.ZAP2_LEGGINGS) &&
                        player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.ZAP2_BOOTS);


                AttributeInstance stepHeight = player.getAttribute(Attributes.STEP_HEIGHT);

                if (fullarmor) {
                    if (!HAS_LEVITATED.contains(playerId)) {
                        player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 3 * 20, 0, false, false, false));
                        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 63, 5, false, false, false));
                        HAS_LEVITATED.add(playerId);
                    }
                    MobEffectInstance levitationCheck = player.getEffect(MobEffects.LEVITATION);
                    if (levitationCheck != null && levitationCheck.getDuration() == 1) {
                        OnFullArmorExplodeCallback.EVENT.invoker().interact(player, player.position());
                        HAS_EXPLODED.add(playerId);
                    }
                    if (!player.hasEffect(MobEffects.LEVITATION)) {
                        player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 41, 9, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 41, 2, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 41, 4, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 41, 4, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 41, 1, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.HASTE, 41, 4, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 41, 1, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 251, 0, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 41, 0, false, false, true));
                        if (!stepHeight.hasModifier(STEP_MODIFIER_ID)) {
                            stepHeight.addTransientModifier(new AttributeModifier(STEP_MODIFIER_ID, +0.5d, ADD_VALUE));
                        }
                    }
                } else {
                        stepHeight.removeModifier(STEP_MODIFIER_ID);
                        HAS_LEVITATED.remove(playerId);
                        HAS_EXPLODED.remove(playerId);
                }
            }
        });
    }
    public static final TagKey<@NotNull Item> REPAIRS_ZAP = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "repairs_zap"));
}