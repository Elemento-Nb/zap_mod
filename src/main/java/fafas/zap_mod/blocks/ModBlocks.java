package fafas.zap_mod.blocks;

import fafas.zap_mod.ZapMod;
import fafas.zap_mod.items.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    private static ResourceKey<net.minecraft.world.level.block.Block> keyOfBlock(String name){
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, name));
    }

    private static ResourceKey<net.minecraft.world.item.Item> keyOfItem(String name){
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, name));
    }


    private static net.minecraft.world.level.block.Block register(String name,
    Function<BlockBehaviour.Properties, net.minecraft.world.level.block.Block> blockFactory, BlockBehaviour.Properties properties) {
        ResourceKey<net.minecraft.world.level.block.Block> blockKey = keyOfBlock(name);
        net.minecraft.world.level.block.Block block = blockFactory.apply(properties.setId(blockKey));

        ResourceKey<net.minecraft.world.item.Item> itemKey = keyOfItem(name);
        net.minecraft.world.item.BlockItem blockItem = new net.minecraft.world.item.BlockItem(block,
                new net.minecraft.world.item.Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

    }








    public static final net.minecraft.world.level.block.Block ZAP_ORE = register(
            "zap_ore",
            net.minecraft.world.level.block.Block::new,
            BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.EMERALD_ORE).requiresCorrectToolForDrops()
            );
    public static final net.minecraft.world.level.block.Block TESTBLOCK = register(
            "testblock",
            net.minecraft.world.level.block.Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.GRASS)
    );

    public static void initialize(){
        CreativeModeTabEvents.modifyOutputEvent(ModItems.ZAP_GROUP_KEY).register((creativeTab -> {
            creativeTab.accept(ZAP_ORE.asItem());
        }));
        CreativeModeTabEvents.modifyOutputEvent(ModItems.ZAP_GROUP_KEY).register((creativeTab -> {
            creativeTab.accept(TESTBLOCK.asItem());
        }));
    }
}
