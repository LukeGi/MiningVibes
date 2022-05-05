package dev.luhegi.miningvibes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface IVibe {

  IVibe ALL = (o, t) -> true;
  IVibe IDENTICAL = (o, t) -> o == t;

  boolean matches(BlockState origin, BlockState target);

  default boolean matches(LevelAccessor level, BlockPos origin, BlockPos pos) {
    return matches(level.getBlockState(origin), level.getBlockState(pos));
  }
}
