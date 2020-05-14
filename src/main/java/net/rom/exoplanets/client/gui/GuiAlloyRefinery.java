package net.rom.exoplanets.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.conf.SConfigCore;
import net.rom.exoplanets.container.ContainerAlloyRefinery;
import net.rom.exoplanets.content.block.machine.TileAlloyRefinery;

public class GuiAlloyRefinery extends GuiContainer {
    private static final ResourceLocation guiTextures = new ResourceLocation(ExoInfo.MODID, "textures/gui/alloyrefinery.png");
    private IInventory tileAlloyRefinery;

    public GuiAlloyRefinery(InventoryPlayer playerInventory, IInventory smelterInventory) {
        super(new ContainerAlloyRefinery(playerInventory, smelterInventory));
        this.tileAlloyRefinery = smelterInventory;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (TileEntityFurnace.isBurning(this.tileAlloyRefinery)) {
            i1 = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(k + 20, l + 27 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }

        i1 = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

        if (SConfigCore.enableDebug)
            drawDebugInfo();
    }

    private int getCookProgressScaled(int pixels) {
        int j = this.tileAlloyRefinery.getField(2);
        int k = this.tileAlloyRefinery.getField(3);
        return k != 0 && j != 0 ? j * pixels / k : 0;
    }

    private int getBurnLeftScaled(int pixels) {
        int currentItemBurnTime = this.tileAlloyRefinery.getField(1);
        if (currentItemBurnTime == 0)
            currentItemBurnTime = 200;
        return this.tileAlloyRefinery.getField(0) * pixels / currentItemBurnTime;
    }

    private void drawDebugInfo() {
        if (!(tileAlloyRefinery instanceof TileAlloyRefinery)) return;

        TileAlloyRefinery tile = (TileAlloyRefinery) tileAlloyRefinery;
        FontRenderer fontRender = mc.fontRenderer;
        int x = 5;
        int y = 5;
        int yIncrement = 10;
        int color = 0xFFFFFF;

        GL11.glPushMatrix();
        float scale = 0.75f;
        GL11.glScalef(scale, scale, 1f);
        for (String str : tile.getDebugLines()) {
            fontRender.drawStringWithShadow(str, x, y, color);
            y += yIncrement;
        }
        GL11.glPopMatrix();
    }
}
