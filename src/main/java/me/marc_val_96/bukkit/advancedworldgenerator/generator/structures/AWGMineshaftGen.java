package me.marc_val_96.bukkit.advancedworldgenerator.generator.structures;

import com.marc_val_96.advancedworldgenerator.LocalBiome;
import com.marc_val_96.advancedworldgenerator.LocalWorld;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig;
import com.marc_val_96.advancedworldgenerator.configuration.BiomeConfig.MineshaftType;
import com.marc_val_96.advancedworldgenerator.util.ChunkCoordinate;
import com.marc_val_96.advancedworldgenerator.util.minecraftTypes.StructureNames;
import me.marc_val_96.bukkit.advancedworldgenerator.util.WorldHelper;

import java.util.Random;
import net.minecraft.server.v1_12_R1.StructureGenerator;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.StructureStart;
import net.minecraft.server.v1_12_R1.WorldGenMineshaft;
import net.minecraft.server.v1_12_R1.WorldGenMineshaftStart;

public class AWGMineshaftGen extends StructureGenerator {
    // canSpawnStructureAtCoords
    @Override
    protected boolean a(int chunkX, int chunkZ) {
        Random rand = this.f;
        World worldMC = this.g;
        if (rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ))) {
            LocalWorld world = WorldHelper.toLocalWorld(worldMC);
            LocalBiome biome = world.getBiome(chunkX * 16 + 8, chunkZ * 16 + 8);
            BiomeConfig biomeConfig = biome.getBiomeConfig();
            if (biomeConfig.mineshaftType == MineshaftType.disabled) {
                return false;
            }
            return rand.nextDouble() * 100.0 < biomeConfig.mineshaftsRarity;
        }

        return false;
    }

    @Override
    public BlockPosition getNearestGeneratedFeature(World world, BlockPosition blockPosition, boolean var3) {
        int var5 = blockPosition.getX() >> 4;
        int var6 = blockPosition.getZ() >> 4;

        for (int var7 = 0; var7 <= 1000; ++var7) {
            for (int var8 = -var7; var8 <= var7; ++var8) {
                boolean var9 = var8 == -var7 || var8 == var7;

                for (int var10 = -var7; var10 <= var7; ++var10) {
                    boolean var11 = var10 == -var7 || var10 == var7;
                    if (var9 || var11) {
                        int var12 = var5 + var8;
                        int var13 = var6 + var10;
                        this.f.setSeed(var12 ^ var13 ^ world.getSeed());
                        this.f.nextInt();
                        if (this.a(var12, var13) && (!var3 || !world.b(var12, var13))) {
                            return new BlockPosition((var12 << 4) + 8, 64, (var13 << 4) + 8);
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    protected StructureStart b(int chunkX, int chunkZ) {
        LocalWorld world = WorldHelper.toLocalWorld(this.g);
        LocalBiome biome = world.getBiome(chunkX * ChunkCoordinate.CHUNK_X_SIZE + 8,
                chunkZ * ChunkCoordinate.CHUNK_Z_SIZE + 8);
        BiomeConfig biomeConfig = biome.getBiomeConfig();
        WorldGenMineshaft.Type mineshaftType = WorldGenMineshaft.Type.NORMAL;
        if (biomeConfig.mineshaftType == MineshaftType.mesa) {
            mineshaftType = WorldGenMineshaft.Type.MESA;
        }

        return new WorldGenMineshaftStart(this.g, this.f, chunkX, chunkZ, mineshaftType);
    }

    @Override
    public String a() {
        return StructureNames.MINESHAFT;
    }

}