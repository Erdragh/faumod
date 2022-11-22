package com.github.erdragh.faumod.item;

import com.github.erdragh.faumod.client.gui.CalculatorScreen;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CalculatorItem extends Item {

  public CalculatorItem() {
    super(new FabricItemSettings().group(ItemGroup.TOOLS));
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    if (world.isClient) {
      MinecraftClient.getInstance().setScreen(new CalculatorScreen(user, user.getActiveItem(), hand));
    }
    return super.use(world, user, hand);
  }
}
