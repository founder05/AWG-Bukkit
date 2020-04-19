package com.marc_val_96.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;

/**
 * Class to access the properties of a biome.
 */
public interface LocalBiome {
    /**
     * Returns whether this biome is part of the vanilla biomes created by
     * Mojang, or whether it is a custom biomes.
     *
     * @return Whether this biome is a custom biome.
     */
    boolean isCustom();

    /**
     * Gets the name of this biome, like Plains. For vanilla biomes, this is
     * the name Mojang gave to them, for custom biomes the name is decided by
     * the server owner in the WorldConfig.
     *
     * @return The name.
     */
    String getName();

    /**
     * Gets the {@link BiomeIds biome ids} of this biome.
     *
     * @return The id.
     * @see BiomeIds
     */
    BiomeIds getIds();

    /**
     * Gets the temperature at the given position, if this biome would be
     * there. This temperature is based on a base temperature value, but it
     * will be lower at higher altitudes.
     *
     * @param x The x position in the world.
     * @param y The y position in the world.
     * @param z The z position in the world.
     * @return The temperature.
     */
    float getTemperatureAt(int x, int y, int z);

    /**
     * Gets the {@link BiomeConfig} of this biome, which holds all settings
     * of this biome.
     *
     * @return The BiomeConfig.
     */
    BiomeConfig getBiomeConfig();
}
