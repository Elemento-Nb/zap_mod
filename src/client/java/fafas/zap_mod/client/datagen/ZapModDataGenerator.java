package fafas.zap_mod.client.datagen;

import fafas.zap_mod.ZapWorldGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ZapModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ZapItemTagProvider::new);
        pack.addProvider(ZapModLootTableProvider::new);
        pack.addProvider(ZapWorldGenerator::new);
    }
    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder){
        registryBuilder.add(Registries.CONFIGURED_FEATURE,ZapWorldGenerator::bootstrapConfiguredFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE,ZapWorldGenerator::bootstrapPlacedFeatures);
    }
}
