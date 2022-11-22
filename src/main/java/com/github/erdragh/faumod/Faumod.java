package com.github.erdragh.faumod;

import com.github.erdragh.faumod.item.CalculatorItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Faumod implements ModInitializer {

  public static final String MODID = "faumod";

  public static final Item CALCULATOR = new CalculatorItem();

  @Override
  public void onInitialize() {
    Registry.register(Registry.ITEM, new Identifier(MODID, "calculator"), CALCULATOR);
  }
}
