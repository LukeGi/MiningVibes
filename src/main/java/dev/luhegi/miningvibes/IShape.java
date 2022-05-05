package dev.luhegi.miningvibes;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IShape {

  Logger LOGGER = LogManager.getLogger();

  IShape FLOOD = (vibe, level, origin) -> {
    if (vibe != IVibe.IDENTICAL) {
      LOGGER.info("Wrong vibe was used, abandon ship!");
    }
    Set<BlockPos> toMine = new HashSet<>();
    Queue<BlockPos> checklist = new PriorityQueue<>(Comparator.comparing(BlockPos::asLong));
    Set<BlockPos> checkedlist = new HashSet<>();

    checklist.add(origin.immutable());
    while (!checklist.isEmpty()) {
      BlockPos q = checklist.poll();
      if (vibe.matches(level, origin, q)) {
        toMine.add(q);
        for (Direction dir : Direction.values()) {
          BlockPos neighbour = q.relative(dir);
          boolean visited = checkedlist.contains(neighbour);
          boolean willVisit = checklist.contains(neighbour);
          if (!visited && !willVisit) {
            checklist.add(neighbour);
          }
        }
      }
      checkedlist.add(q);
    }
    return toMine;
  };

  IShape GENEROUS_FLOOD = (vibe, level, origin) -> {
    if (vibe != IVibe.IDENTICAL) {
      LOGGER.info("Wrong vibe was used, abandon ship!");
    }
    Set<BlockPos> toMine = new HashSet<>();
    Queue<BlockPos> checklist = new PriorityQueue<>(Comparator.comparing(BlockPos::asLong));
    Set<BlockPos> checkedlist = new HashSet<>();

    checklist.add(origin.immutable());
    while (!checklist.isEmpty()) {
      BlockPos q = checklist.poll();
      if (vibe.matches(level, origin, q)) {
        toMine.add(q);
        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            for (int z = -1; z <= 1; z++) {
              BlockPos neighbour = q.offset(i, j, z);
              boolean visited = checkedlist.contains(neighbour);
              boolean willVisit = checklist.contains(neighbour);
              if (!visited && !willVisit) {
                checklist.add(neighbour);
              }
            }
          }

        }
      }
      checkedlist.add(q);
    }
    return toMine;
  };

  /**
   * @param vibe   The vibe of the miner
   * @param level  The level being mined in currently
   * @param origin The position being looked at
   * @return A list of positions of blocks for this shape given the vibe
   */
  Set<BlockPos> getValidBlocks(IVibe vibe, LevelAccessor level, BlockPos origin);
}
