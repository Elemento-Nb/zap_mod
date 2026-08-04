package fafas.zap_mod.potions;

import fafas.zap_mod.ZapMod;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import fafas.zap_mod.items.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.alchemy.Potion;
import org.jetbrains.annotations.NotNull;

public class ModPotions {
    public static final Holder<@NotNull Potion> ZAP_POT =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_potion"),
                    new Potion("zap",
                            new MobEffectInstance(MobEffects.HASTE, 20000, 0),
                            new MobEffectInstance(MobEffects.STRENGTH, 20000, 0),
                            new MobEffectInstance(MobEffects.REGENERATION, 20000, 0),
                            new MobEffectInstance(MobEffects.SPEED, 20000, 0),
                            new MobEffectInstance(MobEffects.ABSORPTION, 20000, 0),
                            new MobEffectInstance(MobEffects.CONDUIT_POWER, 20000, 0),
                            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 20000, 0),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20000, 0),
                            new MobEffectInstance(MobEffects.GLOWING, 20000, 0),
                            new MobEffectInstance(MobEffects.HEALTH_BOOST, 20000, 0),
                            new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 20000, 0),
                            new MobEffectInstance(MobEffects.INVISIBILITY, 20000, 0),
                            new MobEffectInstance(MobEffects.JUMP_BOOST, 20000, 0),
                            new MobEffectInstance(MobEffects.LUCK, 20000, 0),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 20000, 0),
                            new MobEffectInstance(MobEffects.TRIAL_OMEN, 20000, 0)
                    )
            );

    public static final Holder<@NotNull Potion> CONC0 =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_conc0"),
                    new Potion("zap0",
                            new MobEffectInstance(MobEffects.HASTE, 10000, 1),
                            new MobEffectInstance(MobEffects.STRENGTH, 10000, 1),
                            new MobEffectInstance(MobEffects.REGENERATION, 10000, 1),
                            new MobEffectInstance(MobEffects.SPEED, 10000, 1),
                            new MobEffectInstance(MobEffects.ABSORPTION, 10000, 1),
                            new MobEffectInstance(MobEffects.CONDUIT_POWER, 10000, 1),
                            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 10000, 1),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 10000, 1),
                            new MobEffectInstance(MobEffects.GLOWING, 10000, 1),
                            new MobEffectInstance(MobEffects.HEALTH_BOOST, 10000, 1),
                            new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 10000, 1),
                            new MobEffectInstance(MobEffects.INVISIBILITY, 10000, 1),
                            new MobEffectInstance(MobEffects.JUMP_BOOST, 10000, 1),
                            new MobEffectInstance(MobEffects.LUCK, 10000, 1),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 10000, 1),
                            new MobEffectInstance(MobEffects.TRIAL_OMEN, 10000, 1)
                    )
            );

    public static final Holder<@NotNull Potion> CONC1 =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_conc1"),
                    new Potion("zap1",
                            new MobEffectInstance(MobEffects.HASTE, 5000, 3),
                            new MobEffectInstance(MobEffects.STRENGTH, 5000, 3),
                            new MobEffectInstance(MobEffects.REGENERATION, 5000, 3),
                            new MobEffectInstance(MobEffects.SPEED, 5000, 3),
                            new MobEffectInstance(MobEffects.ABSORPTION, 5000, 3),
                            new MobEffectInstance(MobEffects.CONDUIT_POWER, 5000, 3),
                            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 5000, 3),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5000, 3),
                            new MobEffectInstance(MobEffects.GLOWING, 5000, 3),
                            new MobEffectInstance(MobEffects.HEALTH_BOOST, 5000, 3),
                            new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 5000, 3),
                            new MobEffectInstance(MobEffects.INVISIBILITY, 5000, 3),
                            new MobEffectInstance(MobEffects.JUMP_BOOST, 5000, 3),
                            new MobEffectInstance(MobEffects.LUCK, 5000, 3),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 5000, 3),
                            new MobEffectInstance(MobEffects.TRIAL_OMEN, 5000, 3)
                    )
            );

    public static final Holder<@NotNull Potion> CONC2 =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_conc2"),
                    new Potion("zap2",
                            new MobEffectInstance(MobEffects.HASTE, 2500, 7),
                            new MobEffectInstance(MobEffects.STRENGTH, 2500, 7),
                            new MobEffectInstance(MobEffects.REGENERATION, 2500, 7),
                            new MobEffectInstance(MobEffects.SPEED, 2500, 7),
                            new MobEffectInstance(MobEffects.ABSORPTION, 2500, 7),
                            new MobEffectInstance(MobEffects.CONDUIT_POWER, 2500, 7),
                            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 2500, 7),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2500, 7),
                            new MobEffectInstance(MobEffects.GLOWING, 2500, 7),
                            new MobEffectInstance(MobEffects.HEALTH_BOOST, 2500, 7),
                            new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 2500, 7),
                            new MobEffectInstance(MobEffects.INVISIBILITY, 2500, 7),
                            new MobEffectInstance(MobEffects.JUMP_BOOST, 2500, 7),
                            new MobEffectInstance(MobEffects.LUCK, 2500, 7),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 2500, 7),
                            new MobEffectInstance(MobEffects.TRIAL_OMEN, 2500, 7)
                    )
            );

    public static final Holder<@NotNull Potion> CONC3 =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_conc3"),
                    new Potion("zap3",
                            new MobEffectInstance(MobEffects.HASTE, 1250, 15),
                            new MobEffectInstance(MobEffects.STRENGTH, 1250, 15),
                            new MobEffectInstance(MobEffects.REGENERATION, 1250, 15),
                            new MobEffectInstance(MobEffects.SPEED, 1250, 15),
                            new MobEffectInstance(MobEffects.ABSORPTION, 1250, 15),
                            new MobEffectInstance(MobEffects.CONDUIT_POWER, 1250, 15),
                            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 1250, 15),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1250, 15),
                            new MobEffectInstance(MobEffects.GLOWING, 1250, 15),
                            new MobEffectInstance(MobEffects.HEALTH_BOOST, 1250, 15),
                            new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 1250, 15),
                            new MobEffectInstance(MobEffects.INVISIBILITY, 1250, 15),
                            new MobEffectInstance(MobEffects.JUMP_BOOST, 1250, 15),
                            new MobEffectInstance(MobEffects.LUCK, 1250, 15),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 1250, 15),
                            new MobEffectInstance(MobEffects.TRIAL_OMEN, 1250, 15)
                    )
            );

    public static final Holder<@NotNull Potion> CONC4 =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_conc4"),
                    new Potion("zap4",
                            new MobEffectInstance(MobEffects.HASTE, 625, 31),
                            new MobEffectInstance(MobEffects.STRENGTH, 625, 31),
                            new MobEffectInstance(MobEffects.REGENERATION, 625, 31),
                            new MobEffectInstance(MobEffects.SPEED, 625, 31),
                            new MobEffectInstance(MobEffects.ABSORPTION, 625, 31),
                            new MobEffectInstance(MobEffects.CONDUIT_POWER, 625, 31),
                            new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 625, 31),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 625, 31),
                            new MobEffectInstance(MobEffects.GLOWING, 625, 31),
                            new MobEffectInstance(MobEffects.HEALTH_BOOST, 625, 31),
                            new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 625, 31),
                            new MobEffectInstance(MobEffects.INVISIBILITY, 625, 31),
                            new MobEffectInstance(MobEffects.JUMP_BOOST, 625, 31),
                            new MobEffectInstance(MobEffects.LUCK, 625, 31),
                            new MobEffectInstance(MobEffects.NIGHT_VISION, 625, 31),
                            new MobEffectInstance(MobEffects.TRIAL_OMEN, 625, 31)
                    )
            );

    public static void registerPotions() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.addMix(
                    net.minecraft.world.item.alchemy.Potions.AWKWARD,
                    ModItems.WHATS_APP,
                    ZAP_POT
            );

            builder.addMix(
                    ZAP_POT,
                    net.minecraft.world.item.Items.BLAZE_POWDER,
                    CONC0
            );

            builder.addMix(
                    CONC0,
                    net.minecraft.world.item.Items.BLAZE_POWDER,
                    CONC1
            );

            builder.addMix(
                    CONC1,
                    net.minecraft.world.item.Items.BLAZE_POWDER,
                    CONC2
            );

            builder.addMix(
                    CONC2,
                    net.minecraft.world.item.Items.BLAZE_POWDER,
                    CONC3
            );

            builder.addMix(
                    CONC3,
                    net.minecraft.world.item.Items.BLAZE_POWDER,
                    CONC4
            );
        });
    }
}