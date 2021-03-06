/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.romvoid95.common.block.machine;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.romvoid95.api.crafting.RecipeBuilder;
import net.romvoid95.common.lib.EnumMetal;
import net.romvoid95.common.lib.block.IBlockTileEntity;
import net.romvoid95.common.lib.interfaces.IAddRecipe;
import net.romvoid95.common.lib.interfaces.IMetal;
import net.romvoid95.common.tile.TileEntityAlloyRefinery;
import net.romvoid95.core.ExoplanetsMod;
import net.romvoid95.core.initialization.ExoBlocks;

public class BlockAlloyRefinery extends BlockMachine implements IAddRecipe, IBlockTileEntity {

    public BlockAlloyRefinery() {
        super(Material.IRON);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityAlloyRefinery();
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityAlloyRefinery.class;
    }

    @Override
    public void addRecipes(RecipeBuilder recipes) {
    	
    	for (IMetal metals : EnumMetal.values()) {

    		ItemStack metalBlocks = metals.getBlock();
            ItemStack result = new ItemStack(this);
            recipes.addShaped("alloy_refinery", result, "iii", "afa", "bab", 'i', "sheetTitanium", 'a', "sheetPlatinum", 'b', metalBlocks, 'f' , ExoBlocks.METALFURNACE);
    	}


    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileEntityAlloyRefinery) {
                player.openGui(ExoplanetsMod.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
}
