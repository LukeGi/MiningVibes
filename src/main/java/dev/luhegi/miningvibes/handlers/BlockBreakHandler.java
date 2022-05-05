package dev.luhegi.miningvibes.handlers;

import dev.luhegi.miningvibes.MiningVibes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class BlockBreakHandler {

  @SubscribeEvent
  public static void onBlockBreak(BreakEvent event) {
    LevelAccessor world = event.getWorld();
    BlockPos origin = event.getPos();
    if (world.getBlockState(origin).is(Blocks.ORES_IN_GROUND_STONE)) {
      MiningVibes.CURRENT_SHAPE.getValidBlocks(MiningVibes.CURRENT_VIBE, world, origin).forEach(p -> world.destroyBlock(p, true));
    }
  }
}
