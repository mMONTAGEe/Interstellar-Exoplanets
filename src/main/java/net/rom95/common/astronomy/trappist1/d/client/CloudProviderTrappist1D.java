package net.rom95.common.astronomy.trappist1.d.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.rom95.api.world.CloudProvider;
import net.rom95.api.world.IStormProvider;
import net.rom95.client.gui.rendering.Texture;
import net.rom95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.rom95.core.ExoInfo;

@EventBusSubscriber
public class CloudProviderTrappist1D extends CloudProvider {

	private static final Texture CLOUDS = new Texture(ExoInfo.MODID, "textures/world/varda-clouds.png");

	private IStormProvider stormProvider;

	@Override
	public float getMaxCloudSpeedDuringStorm () {
		return 32;
	}

	@Override
	public float getMaxNormalCloudSpeed () {
		return 12;
	}

	@Override
	public Texture getCloudTexture () {
		return CLOUDS;
	}

	@Override
	public boolean areCloudsApplicableTo (WorldProvider provider) {
		return provider instanceof WorldProviderTrappist1D;
	}

	@Override
	public double getCloudMovementX (World world, float cloudTicksPrev, float cloudTicks) {
		return -super.getCloudMovementX(world, cloudTicksPrev, cloudTicks);
	}

	@Override
	public void render (float partialTicks, WorldClient world, Minecraft mc) {
		super.render(partialTicks, world, mc);
	}
}