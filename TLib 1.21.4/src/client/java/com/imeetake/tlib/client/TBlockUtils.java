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
 * Utility class for working with blocks and block-based logic on the client side.
 */
public class TBlockUtils {

    /**
     * Returns true if the block is solid: not air, opaque, and without fluid.
     */
    public static boolean isSolid(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isOpaque() && !state.isAir() && state.getFluidState().isEmpty();
    }

    /**
     * Returns true if the block is either air or contains a fluid (e.g. water or lava).
     */
    public static boolean isAirOrFluid(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        FluidState fs = state.getFluidState();
        return state.isAir() || !fs.isEmpty();
    }

    /**
     * Returns true if the block emits any light.
     */
    public static boolean isLightSource(World world, BlockPos pos) {
        return world.getBlockState(pos).getLuminance() > 0;
    }

    /**
     * Finds the nearest block matching the predicate in the specified radius cube.
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
     * Returns true if the block can be replaced (e.g. air, tall grass, flowers).
     */
    public static boolean isReplaceable(World world, BlockPos pos) {
        return world.getBlockState(pos).isReplaceable();
    }

    /**
     * Returns true if the block contains any fluid (e.g. water, lava).
     */
    public static boolean isLiquid(World world, BlockPos pos) {
        return !world.getBlockState(pos).getFluidState().isEmpty();
    }

    /**
     * Returns true if the block is walkable: solid, non-replaceable, and not fire.
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
     * Returns the position directly above the given one.
     */
    public static BlockPos getBlockAbove(BlockPos pos) {
        return pos.up();
    }

    /**
     * Returns the position directly below the given one.
     */
    public static BlockPos getBlockBelow(BlockPos pos) {
        return pos.down();
    }

    /**
     * Traces vertically from the starting block in a direction until a block matches the predicate.
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
     * Returns a list of all blocks around the center position within the given radius that match the predicate.
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
     * Returns the surface height (top block) at the given x/z coordinates.
     */
    public static int getSurfaceHeight(World world, int x, int z) {
        return world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);
    }
}
