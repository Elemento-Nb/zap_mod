package fafas.zap_mod;

import fafas.zap_mod.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import org.jetbrains.annotations.NotNull;


import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ZapWorldGenerator extends FabricDynamicRegistryProvider {
    public static final ResourceKey<@NotNull ConfiguredFeature<?, ?>> ZAP_ORE_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE,
            Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_ore"));
    public static final ResourceKey<@NotNull PlacedFeature> ZAP_ORE_PLACED_KEY = ResourceKey.
            create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_ore_placed"));

    public ZapWorldGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture){
        super(output, registriesFuture);
    }


    public static void bootstrapConfiguredFeatures(BootstrapContext<@NotNull ConfiguredFeature<?, ?>> context){
        RuleTest  replaceStone = new BlockMatchTest(Blocks.STONE);

        List<OreConfiguration.TargetBlockState> targets =
                List.of(OreConfiguration.target(replaceStone, ModBlocks.ZAP_ORE.defaultBlockState()));

        ConfiguredFeature<?, ?> zapOreFeature = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targets, 4));

        context.register(ZAP_ORE_KEY, zapOreFeature);
    }


    public static void bootstrapPlacedFeatures(BootstrapContext<@NotNull PlacedFeature> context){
        var configuredRegistry = context.lookup(Registries.CONFIGURED_FEATURE);
        var feature = configuredRegistry.getOrThrow(ZAP_ORE_KEY);

        PlacedFeature placedFeature = new PlacedFeature(
                feature,
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(1),
                                VerticalAnchor.absolute(12)
                        ),
                        BiomeFilter.biome()
                )
        );
        context.register(ZAP_ORE_PLACED_KEY, placedFeature);
    }
    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries){
        entries.add(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE).getOrThrow(ZAP_ORE_KEY));
        entries.add(registries.lookupOrThrow(Registries.PLACED_FEATURE).getOrThrow(ZAP_ORE_PLACED_KEY));
    }
    @Override
    public @NotNull String getName() {
        return "Zap World Gen";
    }
}

