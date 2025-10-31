package com.imeetake.tlib.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility methods for interacting with blocks and the world environment.
 */
public class TBlockUtils {

    /**
     * Checks if the block at the given position is solid (not air, not fluid, and opaque).
     */
    public static boolean isSolid(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isOpaque() && !state.isAir() && state.getFluidState().isEmpty();
    }

    /**
     * Returns true if the block is air or contains a fluid.
     */
    public static boolean isAirOrFluid(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        FluidState fs = state.getFluidState();
        return state.isAir() || !fs.isEmpty();
    }

    /**
     * Checks if the block emits any light.
     */
    public static boolean isLightSource(World world, BlockPos pos) {
        return world.getBlockState(pos).getLuminance() > 0;
    }

    /**
     * Finds the nearest block matching the given predicate within the specified radius.
     */
    public static BlockPos findNearest(World world, BlockPos center, int radius, Predicate<BlockState> predicate) {
        BlockPos nearest = null;
        double minDist = Double.MAX_VALUE;
        int cx = center.getX(), cy = center.getY(), cz = center.getZ();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int y = cy - radius; y <= cy + radius; y++) {
                for (int z = cz - radius; z <= cz + radius; z++) {
                    BlockPos p = new BlockPos(x, y, z);
                    BlockState st = world.getBlockState(p);
                    if (predicate.test(st)) {
                        double d = center.getSquaredDistance(p);
                        if (d < minDist) {
                            minDist = d;
                            nearest = p;
                        }
                    }
                }
            }
        }
        return nearest;
    }

    /**
     * Checks if the block can be replaced (e.g. grass, air, plants).
     */
    public static boolean isReplaceable(World world, BlockPos pos) {
        return world.getBlockState(pos).isReplaceable();
    }

    /**
     * Returns true if the block contains any type of fluid.
     */
    public static boolean isLiquid(World world, BlockPos pos) {
        return !world.getBlockState(pos).getFluidState().isEmpty();
    }

    /**
     * Determines whether a block is considered walkable (solid, non-fluid, non-replaceable, not fire).
     */
    public static boolean isWalkable(World world, BlockPos pos) {
        BlockState st = world.getBlockState(pos);
        Block b = st.getBlock();
        return st.isOpaque()
                && st.getFluidState().isEmpty()
                && !st.isReplaceable()
                && b != Blocks.FIRE;
    }

    /**
     * Returns the position directly above the given block.
     */
    public static BlockPos getBlockAbove(BlockPos pos) {
        return pos.up();
    }

    /**
     * Returns the position directly below the given block.
     */
    public static BlockPos getBlockBelow(BlockPos pos) {
        return pos.down();
    }

    /**
     * Traces vertically in the given direction until a block matches the given predicate.
     * Returns null if no such block is found.
     */
    public static BlockPos traceVertical(World world, BlockPos start, Direction dir, Predicate<BlockState> stopAt) {
        BlockPos.Mutable m = start.mutableCopy();
        while (world.isInBuildLimit(m)) {
            m.move(dir);
            if (stopAt.test(world.getBlockState(m))) {
                return m.toImmutable();
            }
        }
        return null;
    }

    /**
     * Collects all blocks around the center position that match the given predicate within a cubic radius.
     */
    public static List<BlockPos> getNearbyBlocks(World world, BlockPos center, int radius, Predicate<BlockState> predicate) {
        List<BlockPos> result = new ArrayList<>();
        int cx = center.getX(), cy = center.getY(), cz = center.getZ();
        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int y = cy - radius; y <= cy + radius; y++) {
                for (int z = cz - radius; z <= cz + radius; z++) {
                    BlockPos p = new BlockPos(x, y, z);
                    BlockState st = world.getBlockState(p);
                    if (predicate.test(st)) {
                        result.add(p);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Returns the Y coordinate of the topmost block on the surface at the given X/Z position.
     */
    public static int getSurfaceHeight(World world, int x, int z) {
        return world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);
    }
}
