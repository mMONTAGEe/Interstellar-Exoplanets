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

package net.romvoid95.common.astronomy.wolf1061.b.biome;

import net.romvoid95.common.world.biome.ExoBiome;
import net.romvoid95.common.world.biome.properties.BiomeData.BiomeDataBuilder;

public class Wolf1061B_Biomes extends ExoBiome {

	public Wolf1061B_Biomes (BiomeDataBuilder biomeDataBuilder) {
		super(biomeDataBuilder.generate());
	}

}