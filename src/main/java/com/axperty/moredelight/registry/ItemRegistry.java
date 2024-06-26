package com.axperty.moredelight.registry;

import com.axperty.moredelight.MoreDelight;
import com.axperty.moredelight.item.ItemList;
import com.axperty.moredelight.item.ToolMaterials;
import com.nhoryzon.mc.farmersdelight.registry.EffectsRegistry;
import com.nhoryzon.mc.farmersdelight.item.KnifeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {

    public static void registerItems() {
        // Wooden Knife
        ItemList.WOODEN_KNIFE = knife("wooden_knife", new KnifeItem(ToolMaterials.WOOD_MATERIAL, new FabricItemSettings()));

        // Stone Knife
        ItemList.STONE_KNIFE = knife("stone_knife", new KnifeItem(ToolMaterials.STONE_MATERIAL, new FabricItemSettings()));

        // Omelette
        ItemList.OMELETTE = item("omelette", new Item(food(null, 6, 0.5f)));

        // Cooked Rice with Milk Cream and Beef
        ItemList.COOKED_RICE_WITH_MILK_CREAM_AND_BEEF = item("cooked_rice_with_milk_cream_and_beef", new Item(meal(9, 2f)));

        // Pasta with Milk Cream and Ham
        ItemList.PASTA_WITH_MILK_CREAM_AND_HAM = item("pasta_with_milk_cream_and_ham", new Item(meal(9, 2f)));

        // Cooked Diced Potatoes
        ItemList.COOKED_DICED_POTATOES = item("cooked_diced_potatoes", new Item(meal(9, 2f)));

        // Diced Potatoes
        ItemList.DICED_POTATOES = item("diced_potatoes",
                new Item(food(null, 1, 0.3f)));

        // Bread Slice
        ItemList.BREAD_SLICE = item("bread_slice", new Item(food(null, 1, 0.3f)));

        // Toast
        ItemList.TOAST = item("toast", new Item(food(null, 2, 0.5f)));

        // Egg in Toast
        ItemList.TOAST_WITH_EGG = item("toast_with_egg", new Item(food(null, 5, 0.5f)));

        // Toast with Honey
        ItemList.TOAST_WITH_HONEY = item("toast_with_honey", new Item(food(null, 5, 0.5f)));

        // Toast with Sweet Berries
        ItemList.TOAST_WITH_SWEET_BERRIES = item("toast_with_sweet_berries", new Item(food(null, 5, 0.5f)));

        // Toast with Chocolate
        ItemList.TOAST_WITH_CHOCOLATE = item("toast_with_chocolate", new Item(food(null, 5, 0.5f)));
    }

    private static Item knife(String name, Item item) {
        ItemGroupEvents.modifyEntriesEvent(MoreDelight.GROUP).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(MoreDelight.MOD_ID, name), item);
    }

    private static Item item(String name, Item item) {
        ItemGroupEvents.modifyEntriesEvent(MoreDelight.GROUP).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(MoreDelight.MOD_ID, name), item);
    }

    private static FabricItemSettings food(Item remainder, int hunger, float saturation) {
        return new FabricItemSettings().recipeRemainder(remainder)
                .food(new FoodComponent.Builder().hunger(hunger).saturationModifier(saturation).build());
    }

    private static FabricItemSettings meal(int hunger, float saturation) {
        return new FabricItemSettings().recipeRemainder(Items.BOWL).maxCount(16)
                .food(new FoodComponent.Builder().hunger(hunger)
                        .saturationModifier(saturation).statusEffect(new StatusEffectInstance(EffectsRegistry.NOURISHMENT.get(), 3600, 0), 1.0f).build());
    }

    private static FabricItemSettings basic() {
        return new FabricItemSettings();
    }
}
