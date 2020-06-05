package me.marc_val_96.bukkit.advancedworldgenerator;

import com.marc_val_96.advancedworldgenerator.AWG;
import com.marc_val_96.advancedworldgenerator.BiomeIds;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.WeightedMobSpawnGroup;
import com.marc_val_96.advancedworldgenerator.configuration.standard.PluginStandardValues;
import com.marc_val_96.advancedworldgenerator.configuration.standard.WorldStandardValues;
import com.marc_val_96.advancedworldgenerator.logging.LogMarker;
import com.marc_val_96.advancedworldgenerator.util.helpers.StringHelper;
import me.marc_val_96.bukkit.advancedworldgenerator.util.EnumHelper;
import me.marc_val_96.bukkit.advancedworldgenerator.util.MobSpawnGroupHelper;
import me.marc_val_96.bukkit.advancedworldgenerator.util.WorldHelper;
import net.minecraft.server.v1_12_R1.BiomeBase;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import org.bukkit.block.Biome;

import java.util.List;

public class AWGBiomeBase extends BiomeBase {
    private static final int MAX_TC_BIOME_ID = 1023;
    public final int generationId;

    private AWGBiomeBase(BiomeConfig biomeConfig, BiomeIds biomeIds) {
        super(new BiomeBase_a(biomeConfig.getName(), biomeConfig));
        this.generationId = biomeIds.getGenerationId();

        // Sanity check
        if (this.getHumidity() != biomeConfig.biomeWetness) {
            throw new AssertionError("Biome temperature mismatch");
        }

        this.q = ((BukkitMaterialData) biomeConfig.surfaceBlock).internalBlock();
        this.r = ((BukkitMaterialData) biomeConfig.groundBlock).internalBlock();

        // Mob spawning
        addMobs(this.t, biomeConfig.spawnMonsters);
        addMobs(this.u, biomeConfig.spawnCreatures);
        addMobs(this.v, biomeConfig.spawnWaterCreatures);
        addMobs(this.w, biomeConfig.spawnAmbientCreatures);
    }

    /**
     * Creates a CustomBiome instance. Minecraft automatically registers those
     * instances in the BiomeBase constructor. We don't want this for virtual
     * biomes (the shouldn't overwrite real biomes), so we restore the old
     * biome, unregistering the virtual biome.
     *
     * @param biomeConfig Settings of the biome
     * @param biomeIds    Ids of the biome.
     * @return The CustomBiome instance.
     */
    public static AWGBiomeBase createInstance(BiomeConfig biomeConfig, BiomeIds biomeIds) {
        AWGBiomeBase customBiome = new AWGBiomeBase(biomeConfig, biomeIds);

        // Insert the biome in Minecraft's biome mapping
        String biomeNameWithoutSpaces = StringHelper.toComputerFriendlyName(biomeConfig.getName());
        MinecraftKey biomeKey = new MinecraftKey(PluginStandardValues.PLUGIN_NAME, biomeNameWithoutSpaces);
        int savedBiomeId = biomeIds.getSavedId();

        // We need to init array size because Mojang uses a strange custom
        // ArrayList. RegistryID arrays are not correctly (but randomly!) copied
        // when resized.
        if (BiomeBase.getBiome(MAX_TC_BIOME_ID) == null) {
            BiomeBase.REGISTRY_ID.a(MAX_TC_BIOME_ID,
                    new MinecraftKey(PluginStandardValues.PLUGIN_NAME, "null"),
                    new AWGBiomeBase(biomeConfig, new BiomeIds(MAX_TC_BIOME_ID, MAX_TC_BIOME_ID)));
        }

        if (biomeIds.isVirtual()) {
            // Virtual biomes hack: register, then let original biome overwrite
            // In this way, the id --> biome mapping returns the original biome,
            // and the biome --> id mapping returns savedBiomeId for both the
            // original and custom biome
            BiomeBase existingBiome = BiomeBase.getBiome(savedBiomeId);

            /*, existingBiomeName*/
            if (existingBiome == null) {
                // Original biome not yet registered. This is because it's a
                // custom biome that is loaded after this virtual biome, so it
                // will soon be registered
                BiomeBase.REGISTRY_ID.a(savedBiomeId, biomeKey, customBiome);
            } else {
                MinecraftKey existingBiomeKey = BiomeBase.REGISTRY_ID.b(existingBiome);
                BiomeBase.REGISTRY_ID.a(savedBiomeId, biomeKey, customBiome);
                BiomeBase.REGISTRY_ID.a(savedBiomeId, existingBiomeKey, existingBiome);

                // String existingBiomeName = existingBiome.getClass().getSimpleName();
                // if(existingBiome instanceof CustomBiome) {
                //     existingBiomeName = String.valueOf(((CustomBiome) existingBiome).generationId);
                // }
            }
        } else {
            // Normal insertion
            BiomeBase.REGISTRY_ID.a(savedBiomeId, biomeKey, customBiome);

        }
        AWG.log(LogMarker.DEBUG, ",{},{},{}", biomeConfig.getName(), savedBiomeId, biomeIds.getGenerationId());

        // Add biome to Bukkit enum if it's not there yet
        try {
            Biome.valueOf(biomeNameWithoutSpaces.toUpperCase());
        } catch (IllegalArgumentException e) {
            EnumHelper.addEnum(Biome.class, biomeNameWithoutSpaces.toUpperCase(), new Class[0], new Object[0]);
        }

        // Sanity check: check if biome was actually registered
        int registeredSavedId = WorldHelper.getSavedId(customBiome);
        if (registeredSavedId != savedBiomeId) {
            throw new AssertionError("Biome " + biomeConfig.getName() + " is not properly registered: got id " + registeredSavedId + ", should be " + savedBiomeId);
        }

        checkRegistry();

        return customBiome;
    }

    /**
     * Check if biome ID registry is well filled.
     */
    private static void checkRegistry() {
        for (int i = 168; i >= 0; --i) {
            BiomeBase biome = getBiome(i);
            if (biome instanceof AWGBiomeBase && ((AWGBiomeBase) biome).generationId != i) {
                throw new AssertionError("Biome ID #" + i + " returns custom biome #" +
                        ((AWGBiomeBase) biome).generationId + " instead of its own.");
            }
        }
    }

    // Adds the mobs to the internal list.
    protected void addMobs(List<BiomeMeta> internalList, List<WeightedMobSpawnGroup> configList) {
        internalList.clear();
        internalList.addAll(MobSpawnGroupHelper.toMinecraftlist(configList));
    }

    private static class BiomeBase_a extends BiomeBase.a {

        public BiomeBase_a(String name, BiomeConfig biomeConfig) {
            super(name);

            // Minecraft doesn't like temperatures between 0.1 and 0.2, so avoid
            // them: round them to either 0.1 or 0.2
            float adjustedTemperature = biomeConfig.biomeTemperature;
            if (adjustedTemperature >= 0.1 && adjustedTemperature <= 0.2) {
                if (adjustedTemperature >= 1.5)
                    adjustedTemperature = 0.2f;
                else
                    adjustedTemperature = 0.1f;
            }

            c(biomeConfig.biomeHeight);
            d(biomeConfig.biomeVolatility);
            a(adjustedTemperature);
            b(biomeConfig.biomeWetness);
            if (biomeConfig.biomeWetness <= 0.0001) {
                a(); // disableRain()
            }
            if (biomeConfig.biomeTemperature <= WorldStandardValues.SNOW_AND_ICE_MAX_TEMP) {
                b(); // enableSnowfall()
            }
        }
    }
}
