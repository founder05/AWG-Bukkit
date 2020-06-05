package com.marc_val_96.advancedworldgenerator.configuration.standard;

import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig.MineshaftType;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig.RareBuildingType;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig.VillageType;
import com.marc_val_96.advancedworldgenerator.configuration.WeightedMobSpawnGroup;
import com.marc_val_96.advancedworldgenerator.generator.resource.*;
import com.marc_val_96.advancedworldgenerator.generator.resource.IceSpikeGen.SpikeType;
import com.marc_val_96.advancedworldgenerator.generator.terrain.TerrainShapeBase;
import com.marc_val_96.advancedworldgenerator.util.MaterialSet;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultBiome;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.DefaultMaterial;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.MobNames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.marc_val_96.advancedworldgenerator.configuration.standard.BiomeStandardValues.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A biome generator holds all <i>default</i> settings of a biome.
 *
 */
public class StandardBiomeTemplate
{
    protected final int worldHeight;

    public String defaultExtends = "";
    public boolean defaultWaterLakes = true;
    public Object[] defaultTree; // Parameters for tree resource
    public int defaultDandelions = 2;
    public int defaultPoppies = 0;
    public int defaultBlueOrchids = 0;
    public int defaultTallFlowers = 0;
    public int defaultSunflowers = 0;
    public int defaultTulips = 0;
    public int defaultAzureBluets = 0;
    public int defaultOxeyeDaisies = 0;
    public int defaultAlliums = 0;
    public int defaultGrass = 10;
    public boolean defaultGrassIsGrouped = false;
    public int defaultDoubleGrass = 0;
    public boolean defaultDoubleGrassIsGrouped = false;
    public int defaultFerns = 0;
    public int defaultLargeFerns = 0;
    public int defaultDeadBush = 0;
    public int defaultMushroom = 0;
    public int defaultReed = 0;
    public int defaultCactus = 0;
    public int defaultMelons = 0;
    public int defaultWaterSand = 3;
    public int defaultWaterGravel = 1;
    public int defaultSwampPatches = 0;
    public Object[] defaultWell; // Parameters for well resource
    public float defaultBiomeSurface = 0.1F;
    public float defaultBiomeVolatility = 0.3F;
    public DefaultMaterial defaultSurfaceBlock = DefaultMaterial.GRASS_BLOCK;
    public DefaultMaterial defaultGroundBlock = DefaultMaterial.DIRT;
    public float defaultBiomeTemperature = 0.5F;
    public float defaultBiomeWetness = 0.5F;
    public ArrayList<String> defaultIsle = new ArrayList<String>();
    public ArrayList<String> defaultBorder = new ArrayList<String>();
    public ArrayList<String> defaultNotBorderNear = new ArrayList<String>();
    public String defaultRiverBiome = DefaultBiome.RIVER.Name;
    public int defaultSize = 4;
    public int defaultSizeWhenIsle = 6;
    public int defaultSizeWhenBorder = 8;
    public int defaultRarity = 100;
    public int defaultRarityWhenIsle = 97;
    public int defaultColor = 0x000000;
    public int defaultWaterLily = 0;
    public int defaultWaterColorMultiplier = 0xFFFFFF;
    public int defaultGrassColor = 0xFFFFFF;
    public int defaultFoliageColor = 0xFFFFFF;
    public boolean defaultStrongholds = true;
    public boolean defaultOceanMonuments = false;
    public boolean defaultWoodlandMansions = false;
    public boolean defaultNetherFortressEnabled = false;
    public VillageType defaultVillageType = VillageType.disabled;
    public RareBuildingType defaultRareBuildingType = RareBuildingType.disabled;
    public MineshaftType defaultMineshaftType = MineshaftType.normal;
    public int defaultEmeraldOre = 0;
    public boolean defaultHasVines;
    public int defaultBoulder = 0;
    public Object[] defaultSurfaceSurfaceAndGroundControl = new Object[0];
    public boolean defaultIceSpikes;
    public boolean defaultDisableBiomeHeight;
    public double[] defaultCustomHeightControl = new double[TerrainShapeBase.PIECES_PER_CHUNK_Y + 1];;
    public double defaultFossilRarity = 0;
    public String defaultBiomeDictId = "";
    public String defaultInheritMobsBiomeName = "";
    public boolean inheritSaplingResource = false;

    public List<WeightedMobSpawnGroup> defaultCreatures = Arrays.asList(
        new WeightedMobSpawnGroup(MobNames.SHEEP, 12, 4, 4),
        new WeightedMobSpawnGroup(MobNames.PIG, 10, 4, 4),
        new WeightedMobSpawnGroup(MobNames.CHICKEN, 10, 4, 4),
        new WeightedMobSpawnGroup(MobNames.COW, 8, 4, 4));
    public List<WeightedMobSpawnGroup> defaultMonsters = Arrays.asList(
        new WeightedMobSpawnGroup(MobNames.SPIDER, 100, 4, 4),
        new WeightedMobSpawnGroup(MobNames.ZOMBIE, 100, 4, 4),
        new WeightedMobSpawnGroup(MobNames.SKELETON, 100, 4, 4),
        new WeightedMobSpawnGroup(MobNames.CREEPER, 100, 4, 4),
        new WeightedMobSpawnGroup(MobNames.SLIME, 100, 4, 4),
        new WeightedMobSpawnGroup(MobNames.ENDERMAN, 10, 1, 4),
        new WeightedMobSpawnGroup(MobNames.WITCH, 5, 1, 1));
    public List<WeightedMobSpawnGroup> defaultAmbientCreatures = Collections.singletonList(
        new WeightedMobSpawnGroup(MobNames.BAT, 10, 8, 8));
    public List<WeightedMobSpawnGroup> defaultWaterCreatures = Collections.singletonList(
        new WeightedMobSpawnGroup(MobNames.SQUID, 10, 4, 4));

    public StandardBiomeTemplate(int worldHeight)
    {
        this.worldHeight = worldHeight;
    }

    /**
     * Creates the default resources.
     *
     * @param config
     *            The biome config. Custom objects must already be loaded.
     * @return The default resources for this biome.
     */
    public List<Resource> createDefaultResources(BiomeConfig config)
    {
        // TODO: Add new plants etc.

        List<Resource> resources = new ArrayList<Resource>(32);

        // Small water lakes
        if (this.defaultWaterLakes)
        {
            resources.add(Resource.createResource(config, SmallLakeGen.class, DefaultMaterial.WATER, SmallLakeWaterFrequency,
                SmallLakeWaterRarity, SmallLakeMinAltitude, SmallLakeMaxAltitude));
        }

        // Small lava lakes
        resources.add(Resource.createResource(config, SmallLakeGen.class, DefaultMaterial.LAVA, SmallLakeLavaFrequency,
            SmallLakeLavaRarity, SmallLakeMinAltitude, SmallLakeMaxAltitude));

        // Small underground lava lakes
        resources.add(Resource.createResource(config, SmallLakeGen.class, DefaultMaterial.LAVA, SmallLakeLavaFrequency2,
            SmallLakeLavaRarity2, SmallLakeMinAltitude2, SmallLakeMaxAltitude2));

        // Underground lakes
        resources.add(Resource
            .createResource(config, UndergroundLakeGen.class, UndergroundLakeMinSize, UndergroundLakeMaxSize, UndergroundLakeFrequency,
                UndergroundLakeRarity, UndergroundLakeMinAltitude, UndergroundLakeMaxAltitude));

        // Dungeon
        resources.add(Resource.createResource(config, DungeonGen.class, DungeonFrequency, DungeonRarity, DungeonMinAltitude,
            this.worldHeight));

        // Fossil
        if (defaultFossilRarity > 0)
        {
            resources.add(Resource.createResource(config, FossilGen.class, defaultFossilRarity));
        }

        // Dirt
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.DIRT, DirtDepositSize, DirtDepositFrequency,
            DirtDepositRarity, DirtDepositMinAltitude, DirtDepositMaxAltitude, DefaultMaterial.STONE));

        // Gravel
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.GRAVEL, GravelDepositSize, GravelDepositFrequency,
            GravelDepositRarity, GravelDepositMinAltitude, GravelDepositMaxAltitude, DefaultMaterial.STONE));

        // Granite
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.STONE + ":1", GraniteDepositSize,
            GraniteDepositFrequency, GraniteDepositRarity, GraniteDepositMinAltitude,
            GraniteDepositMaxAltitude, DefaultMaterial.STONE));

        // Diorite
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.STONE + ":3", DioriteDepositSize,
            DioriteDepositFrequency, DioriteDepositRarity, DioriteDepositMinAltitude,
            DioriteDepositMaxAltitude, DefaultMaterial.STONE));

        // Andesite
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.STONE + ":5", AndesiteDepositSize,
            AndesiteDepositFrequency, AndesiteDepositRarity, AndesiteDepositMinAltitude,
            AndesiteDepositMaxAltitude, DefaultMaterial.STONE));

        // Coal
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.COAL_ORE, CoalDepositSize, CoalDepositFrequency,
            CoalDepositRarity, CoalDepositMinAltitude, CoalDepositMaxAltitude, DefaultMaterial.STONE));

        // Iron
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.IRON_ORE, IronDepositSize, IronDepositFrequency,
            IronDepositRarity, IronDepositMinAltitude, IronDepositMaxAltitude, DefaultMaterial.STONE));

        // Gold
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.GOLD_ORE, GoldDepositSize, GoldDepositFrequency,
            GoldDepositRarity, GoldDepositMinAltitude, GoldDepositMaxAltitude, DefaultMaterial.STONE));

        // Redstone
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.REDSTONE_ORE, RedstoneDepositSize,
            RedstoneDepositFrequency, RedstoneDepositRarity, RedstoneDepositMinAltitude,
            RedstoneDepositMaxAltitude, DefaultMaterial.STONE));

        // Diamond
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.DIAMOND_ORE, DiamondDepositSize,
            DiamondDepositFrequency, DiamondDepositRarity, DiamondDepositMinAltitude,
            DiamondDepositMaxAltitude, DefaultMaterial.STONE));

        // Lapislazuli
        resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.LAPIS_ORE, LapislazuliDepositSize,
            LapislazuliDepositFrequency, LapislazuliDepositRarity, LapislazuliDepositMinAltitude,
            LapislazuliDepositMaxAltitude, DefaultMaterial.STONE));

        // Emerald ore
        if (defaultEmeraldOre > 0)
        {
            resources.add(Resource.createResource(config, OreGen.class, DefaultMaterial.EMERALD_ORE, EmeraldDepositSize,
                this.defaultEmeraldOre,
                EmeraldDepositRarity, EmeraldDepositMinAltitude, EmeraldDepositMaxAltitude, DefaultMaterial.STONE));
        }

        // Under water sand
        if (defaultWaterSand > 0)
        {
            resources.add(Resource.createResource(config, UnderWaterOreGen.class, DefaultMaterial.SAND, WaterSandDepositSize,
                defaultWaterSand,
                WaterSandDepositRarity, DefaultMaterial.DIRT, DefaultMaterial.GRASS_BLOCK));
        }

        // Under water clay
        resources.add(Resource.createResource(config, UnderWaterOreGen.class, DefaultMaterial.CLAY, WaterClayDepositSize,
            WaterClayDepositFrequency,
            WaterClayDepositRarity, DefaultMaterial.DIRT, DefaultMaterial.CLAY));

        // Under water gravel
        if (defaultWaterGravel > 0)
        {
            resources.add(Resource.createResource(config, UnderWaterOreGen.class, DefaultMaterial.GRAVEL, WaterGravelDepositSize,
                defaultWaterGravel,
                WaterGravelDepositRarity, DefaultMaterial.DIRT, DefaultMaterial.GRASS_BLOCK));
        }

        // Custom objects
        resources.add(Resource.createResource(config, CustomObjectGen.class, "UseWorld"));

        // Boulder
        if (this.defaultBoulder != 0)
        {
            resources.add(Resource.createResource(config, BoulderGen.class, DefaultMaterial.MOSSY_COBBLESTONE, defaultBoulder,
                BoulderDepositRarity,
                BoulderDepositMinAltitude, BoulderDepositMaxAltitude, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT,
                DefaultMaterial.STONE));
        }

        // Ice spikes
        if (this.defaultIceSpikes)
        {
            resources.add(Resource.createResource(config, IceSpikeGen.class, DefaultMaterial.PACKED_ICE, SpikeType.HugeSpike, 3, 1.66,
                IceSpikeDepositMinHeight,
                IceSpikeDepositMaxHeight, DefaultMaterial.ICE, DefaultMaterial.DIRT, DefaultMaterial.SNOW_BLOCK));
            resources.add(Resource.createResource(config, IceSpikeGen.class, DefaultMaterial.PACKED_ICE, SpikeType.SmallSpike, 3, 98.33,
                IceSpikeDepositMinHeight,
                IceSpikeDepositMaxHeight, DefaultMaterial.ICE, DefaultMaterial.DIRT, DefaultMaterial.SNOW_BLOCK));
            resources.add(Resource.createResource(config, IceSpikeGen.class, DefaultMaterial.PACKED_ICE, SpikeType.Basement, 2, 100,
                IceSpikeDepositMinHeight,
                IceSpikeDepositMaxHeight, DefaultMaterial.ICE, DefaultMaterial.DIRT, DefaultMaterial.SNOW_BLOCK));
        }

        // Melons (need to be spawned before trees)
        if (this.defaultMelons > 0)
        {
            resources.add(Resource.createResource(config, PlantGen.class, DefaultMaterial.MELON, this.defaultMelons,
                FlowerDepositRarity, FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        // Melons (need to be spawned before trees)
        if (this.defaultSwampPatches > 0)
        {
            resources.add(Resource.createResource(config, SurfacePatchGen.class, DefaultMaterial.WATER, DefaultMaterial.LILY_PAD,
                62, 62, MaterialSet.SOLID_MATERIALS));
        }

        // Trees
        if (this.defaultTree != null)
        {
            resources.add(Resource.createResource(config, TreeGen.class, this.defaultTree));
        }

        if (this.defaultWaterLily > 0)
        {
            resources.add(Resource.createResource(config, AboveWaterGen.class, DefaultMaterial.LILY_PAD, this.defaultWaterLily, 100));
        }

        if (this.defaultPoppies > 0)
        {
            // Poppy
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.Poppy, this.defaultPoppies, RoseDepositRarity,
                RoseDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultBlueOrchids > 0)
        {
            // Blue orchid
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.BlueOrchid, this.defaultBlueOrchids,
                BlueOrchidDepositRarity, BlueOrchidDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultDandelions > 0)
        {
            // Dandelion
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.Dandelion, this.defaultDandelions, FlowerDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultTallFlowers > 0)
        {
            // Lilac
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.Lilac, this.defaultTallFlowers, FlowerDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));

            // Rose bush
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.RoseBush, this.defaultTallFlowers, FlowerDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));

            // Peony
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.Peony, this.defaultTallFlowers, FlowerDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultSunflowers > 0)
        {
            // Sunflower
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.Sunflower, this.defaultSunflowers, FlowerDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultTulips > 0)
        {
            // Tulip
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.OrangeTulip, this.defaultTulips, TulipDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.RedTulip, this.defaultTulips, TulipDepositRarity,
                FlowerDepositMinAltitude,
                this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.WhiteTulip, this.defaultTulips, TulipDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.PinkTulip, this.defaultTulips, TulipDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultAzureBluets > 0)
        {
            // Azure bluet
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.AzureBluet, this.defaultDandelions,
                FlowerDepositRarity, FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));

        }

        if (this.defaultAlliums > 0)
        {
            // Allium
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.Allium, this.defaultDandelions, FlowerDepositRarity,
                FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));

        }

        if (this.defaultOxeyeDaisies > 0)
        {
            // Oxeye Daisy
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.OxeyeDaisy, this.defaultDandelions,
                FlowerDepositRarity, FlowerDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultMushroom > 0)
        {
            // Red mushroom
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.RedMushroom, this.defaultMushroom,
                RedMushroomDepositRarity, RedMushroomDepositMinAltitude, this.worldHeight, defaultSurfaceBlock, DefaultMaterial.DIRT));

            // Brown mushroom
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.BrownMushroom, this.defaultMushroom,
                BrownMushroomDepositRarity, BrownMushroomDepositMinAltitude,
                this.worldHeight, defaultSurfaceBlock, DefaultMaterial.DIRT));
        }

        if (this.defaultFerns > 0)
        {
            // Ferns
            resources.add(Resource.createResource(config, GrassGen.class, PlantType.Fern, GrassGen.GroupOption.NotGrouped,
                this.defaultFerns, LongGrassDepositRarity, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultDoubleGrass > 0)
        {
            // Double tall grass
            if (this.defaultDoubleGrassIsGrouped)
            {
                resources.add(Resource.createResource(config, GrassGen.class, PlantType.DoubleTallgrass, GrassGen.GroupOption.Grouped, this.defaultDoubleGrass,
                    DoubleGrassGroupedDepositRarity, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
            } else
            {
                resources.add(Resource.createResource(config, GrassGen.class, PlantType.DoubleTallgrass, GrassGen.GroupOption.NotGrouped, this.defaultDoubleGrass,
                    DoubleGrassDepositRarity, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
            }
        }

        if (this.defaultGrass > 0)
        {
            // Tall grass
            if (this.defaultGrassIsGrouped)
            {
                resources.add(Resource.createResource(config, GrassGen.class, PlantType.Tallgrass, GrassGen.GroupOption.Grouped,
                    this.defaultGrass, LongGrassGroupedDepositRarity, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
            } else
            {
                resources.add(Resource.createResource(config, GrassGen.class, PlantType.Tallgrass, GrassGen.GroupOption.NotGrouped,
                    this.defaultGrass, LongGrassDepositRarity, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
            }
        }

        if (this.defaultLargeFerns > 0)
        {
            // Large ferns
            resources.add(Resource.createResource(config, PlantGen.class, PlantType.LargeFern, this.defaultLargeFerns, 90, 30, this.worldHeight, DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT));
        }

        if (this.defaultDeadBush > 0)
        {
            // Dead Bush
            resources.add(Resource.createResource(config, GrassGen.class, PlantType.DeadBush, 0, this.defaultDeadBush,
                DeadBushDepositRarity, DefaultMaterial.SAND, DefaultMaterial.TERRACOTTA,
                DefaultMaterial.WHITE_TERRACOTTA, DefaultMaterial.DIRT));
        }

        // Pumpkin
        resources.add(Resource.createResource(config, PlantGen.class, DefaultMaterial.PUMPKIN, PumpkinDepositFrequency,
            PumpkinDepositRarity, PumpkinDepositMinAltitude, this.worldHeight, DefaultMaterial.GRASS_BLOCK));

        if (this.defaultReed > 0)
        {
            // Reed
            resources.add(Resource.createResource(config, ReedGen.class, DefaultMaterial.SUGAR_CANE, this.defaultReed,
                ReedDepositRarity, ReedDepositMinAltitude, this.worldHeight,
                DefaultMaterial.GRASS_BLOCK, DefaultMaterial.DIRT, DefaultMaterial.SAND));
        }

        if (this.defaultCactus > 0)
        {
            // Cactus
            resources.add(Resource.createResource(config, CactusGen.class, DefaultMaterial.CACTUS, this.defaultCactus, CactusDepositRarity,
                CactusDepositMinAltitude, this.worldHeight, DefaultMaterial.SAND));
        }
        if (this.defaultHasVines)
        {
            resources.add(Resource.createResource(config, VinesGen.class, VinesFrequency, VinesRarity, VinesMinAltitude, this.worldHeight,
                DefaultMaterial.VINE));
        }

        // Water source
        resources.add(Resource.createResource(config, LiquidGen.class, DefaultMaterial.WATER, WaterSourceDepositFrequency,
            WaterSourceDepositRarity, WaterSourceDepositMinAltitude, this.worldHeight, DefaultMaterial.STONE));

        // Lava source
        resources.add(Resource.createResource(config, LiquidGen.class, DefaultMaterial.LAVA, LavaSourceDepositFrequency,
            LavaSourceDepositRarity, LavaSourceDepositMinAltitude, this.worldHeight, DefaultMaterial.STONE));

        // Desert wells
        if (this.defaultWell != null)
        {
            resources.add(Resource.createResource(config, WellGen.class, this.defaultWell));
        }

        // Sort resources according to their natural other
        // (Sorting the resources here is easier and less error prone than
        // keeping the order of this method in sync with the natural resource
        // order)
        Collections.sort(resources);
        return resources;
    }
}
