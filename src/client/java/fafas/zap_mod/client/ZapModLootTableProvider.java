package fafas.zap_mod.client;

import fafas.zap_mod.blocks.ModBlocks;
import fafas.zap_mod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ZapModLootTableProvider extends FabricBlockLootSubProvider {
    protected ZapModLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup){
        super(dataOutput, registryLookup);
    }
    @Override
    public void generate(){
    add(ModBlocks.ZAP_ORE, LootTable.lootTable()
        .withPool(LootPool.lootPool()
            .setRolls(new UniformGenerator(new ConstantValue(1), new ConstantValue(3)))
            .add(LootItem.lootTableItem(Items.EMERALD))
        )
        .withPool(LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1))
            .add(LootItem.lootTableItem(ModItems.WHATS_APP)
            .when(LootItemRandomChanceCondition.randomChance(0.15f)))
        )
    );
    }
}
