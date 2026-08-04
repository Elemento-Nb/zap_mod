package fafas.zap_mod.items;

import fafas.zap_mod.ZapMod;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;


import java.util.function.Function;

import static fafas.zap_mod.items.ZapArmorClass.*;
import static fafas.zap_mod.items.ZapItemClass.*;

public class ModItems {





   public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings){
       ResourceKey<@NotNull Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, name));

       T item = itemFactory.apply(settings.setId(itemKey));

       Registry.register(BuiltInRegistries.ITEM, itemKey, item);
       return item;
   }

    public static final ResourceKey<@NotNull CreativeModeTab> ZAP_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_item_group"));


    public static final CreativeModeTab ZAP_GROUP = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.WHATS_APP))
            .title(Component.translatable("itemGroup.zap_mod.zap_mod_item_group"))
            .build();

    //
    //
    //
    //
    //
    public static final Item WHATS_APP = register("zap", ZapItemClass::new, new Item.Properties()
            .food(ZAP_ITEM_FOOD, ZAP_ITEM_CONSUMABLE)
    );
    //^ registers a test item from the TestItemC class which is a class that extends item

    public static final Item ZAP_HELMET = register("zap_helmet", Item::new, new Item.Properties().humanoidArmor(ZAP_INSTANCE, ArmorType.HELMET)
            .durability(ArmorType.HELMET.getDurability(ZAP1_DURA))
    );
    //^ registers test helmet


    public static final Item ZAP_CHESTPLATE = register("zap_chestplate", Item::new, new Item.Properties()
            .humanoidArmor(ZAP_INSTANCE, ArmorType.CHESTPLATE)
            .durability(ArmorType.CHESTPLATE.getDurability(ZAP1_DURA))

    );
    //^ registers test chestplate


    public static final Item ZAP_LEGGINGS = register("zap_leggings", Item::new, new Item.Properties()
            .humanoidArmor(ZAP_INSTANCE, ArmorType.LEGGINGS)
            .durability(ArmorType.LEGGINGS.getDurability(ZAP1_DURA))
    );
    //^ registers test leggings


    public static final Item ZAP_BOOTS = register("zap_boots", Item::new, new Item.Properties()
            .humanoidArmor(ZAP_INSTANCE, ArmorType.BOOTS)
            .durability(ArmorType.BOOTS.getDurability(ZAP1_DURA))

    );
    //^ register test boots

    public static final Item ZAP_SWORD = register("zap_sword", ZapOtherItemClass::new, new Item.Properties()
            .sword(ZAP_TOOL_MATERIAL,3,-2.0f));

    public static final Item ZAP_PICKAXE = register("zap_pickaxe", Item::new, new Item.Properties()
            .pickaxe(ZAP_TOOL_MATERIAL,1, -2.8f));
    //^ registers a test pickaxe

    public static final Item ZAP_AXE = register("zap_axe", Item::new, new Item.Properties()
            .axe(ZAP_TOOL_MATERIAL,5,-2.9f));

    public static final Item ZAP_SHOVEL = register("zap_shovel", Item::new, new Item.Properties()
            .shovel(ZAP_TOOL_MATERIAL, 1, -2.0f));

    public static final Item ZAP2 = register("zap2", Item::new, new Item.Properties()
            .food(ZAP2_ITEM_FOOD, ZAP2_ITEM_CONSUMABLE));

    public static final Item ZAP2_HELMET = register("zap2_helmet", Item::new, new Item.Properties()
            .humanoidArmor(ZAP2_INSTANCE, ArmorType.HELMET)
            .durability(ArmorType.HELMET.getDurability(ZAP2_DURA))
    );

    public static final Item ZAP2_CHESTPLATE = register("zap2_chestplate", Item::new, new Item.Properties()
            .humanoidArmor(ZAP2_INSTANCE, ArmorType.CHESTPLATE)
            .durability(ArmorType.CHESTPLATE.getDurability(ZAP2_DURA))
    );

    public static final Item ZAP2_LEGGINGS = register("zap2_leggings", Item::new, new Item.Properties()
            .humanoidArmor(ZAP2_INSTANCE, ArmorType.LEGGINGS)
            .durability(ArmorType.LEGGINGS.getDurability(ZAP2_DURA))
    );

    public static final Item ZAP2_BOOTS = register("zap2_boots", Item::new, new Item.Properties()
            .humanoidArmor(ZAP2_INSTANCE, ArmorType.BOOTS)
            .durability(ArmorType.BOOTS.getDurability(ZAP2_DURA))
    );

    public static final Item ZAP2_SWORD = register("zap2_sword", ZapOtherItemClass::new, new Item.Properties()
            .sword(ZAP2_TOOL_MATERIAL, 9, -1.0f));

    public static final Item ZAP2_PICKAXE = register("zap2_pickaxe", Item::new, new Item.Properties()
            .pickaxe(ZAP2_TOOL_MATERIAL, 1, -2.8f));

    public static final Item ZAP2_AXE = register("zap2_axe", Item::new, new Item.Properties()
            .axe(ZAP2_TOOL_MATERIAL, 5, -2.9f));

    public static final Item ZAP2_SHOVEL = register("zap2_shovel", Item::new, new Item.Properties()
            .shovel(ZAP2_TOOL_MATERIAL, 1, -2f));
    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ZAP_GROUP_KEY, ZAP_GROUP);
        //^ Registers the item group tab

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register((creativeTab -> creativeTab.accept(ModItems.WHATS_APP)));
        //^ Register the test item in the test mod tab

        CompostableRegistry.INSTANCE.add(ModItems.WHATS_APP, 2.0f);
        //^ Make the test item compostable with a 90% chance of increasing the composter's level

        FuelValueEvents.BUILD.register(((builder, context) -> {
            builder.add(ModItems.WHATS_APP, 30000 * 20);
            //^ Make the test item a fuel that burns for 30000 seconds(20 ticks = 1 second)

        }));
        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_SWORD));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_PICKAXE));


        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_AXE));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_SHOVEL));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_HELMET));


        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_CHESTPLATE));


        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_LEGGINGS));


        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_BOOTS));


        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_AXE));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP_SHOVEL));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2));

        CompostableRegistry.INSTANCE.add(ZAP2, 4.9f);

        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ZAP2, 90000000 * 20);
        });

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_HELMET));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_CHESTPLATE));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_LEGGINGS));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_BOOTS));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_SWORD));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_PICKAXE));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_AXE));

        CreativeModeTabEvents.modifyOutputEvent(ZAP_GROUP_KEY).register(creativeTab -> creativeTab.accept(ZAP2_SHOVEL));
    }
}
