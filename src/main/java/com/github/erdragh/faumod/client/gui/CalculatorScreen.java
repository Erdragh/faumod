package com.github.erdragh.faumod.client.gui;

import com.github.erdragh.faumod.Faumod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

@Environment(value= EnvType.CLIENT)
public class CalculatorScreen extends Screen {

  private static final Identifier CALC_TEXTURE = new Identifier(Faumod.MODID, "textures/gui/calculator.png");

  private final PlayerEntity player;
  private final ItemStack itemStack;
  private final Hand hand;


  /**
   * an array storing the buttons used in the calculator:
   *
   * It is ordered like this:
   * <table border="1">
   *   <tr>
   *     <td>| 7 (0)</td>
   *     <td>| 8 (1)</td>
   *     <td>| 9 (2)</td>
   *     <td>| + (3)</td>
   *   </tr>
   *   <tr>
   *     <td>| 4 (4)</td>
   *     <td>| 5 (5)</td>
   *     <td>| 6 (6)</td>
   *     <td>| - (7)</td>
   *   </tr>
   *   <tr>
   *     <td>| 1 (8)</td>
   *     <td>| 3 (9)</td>
   *     <td>| 2 (10)</td>
   *     <td>| * (11)</td>
   *   </tr>
   *   <tr>
   *     <td>| 0 (12)</td>
   *     <td>| C (13)</td>
   *     <td>| = (14)</td>
   *     <td>| / (15)</td>
   *   </tr>
   * </table>
   */
  private TexturedButtonWidget[] calculatorButtons = new TexturedButtonWidget[16];

  public CalculatorScreen(PlayerEntity player, ItemStack itemStack, Hand hand) {
    super(Text.translatable("fau.calculator.title"));
    this.player = player;
    this.itemStack = itemStack;
    this.hand = hand;
  }

  @Override
  protected void init() {
    this.calculatorButtons[0] = this.addDrawableChild(new TexturedButtonWidget(0, 0, 20, 20, 0, 0, new Identifier(Faumod.MODID, "/textures/gui/calculator.png"), button -> {
      System.out.println("Player pressed on button 7");
    }));
  }

  @Override
  public boolean charTyped(char chr, int modifiers) {
    System.out.println("Player pressed key: " + chr);
    if (super.charTyped(chr, modifiers)) {
      return true;
    }
    return true;
  }

  @Override
  public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
    this.renderBackground(matrices);
    this.setFocused(null);
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    RenderSystem.setShaderTexture(0, CALC_TEXTURE);
    this.drawTexture(matrices, 0, 0, 0, 0, 48, 64);
    for (int i = 0; i < calculatorButtons.length; i++) {
      if (calculatorButtons[i] != null) {
        calculatorButtons[i].renderButton(matrices, mouseX, mouseY, delta);
      }
    }
  }

  @Override
  public boolean shouldPause() {
    return false;
  }
}
