package dev.luhegi.miningvibes.handlers;

import dev.luhegi.miningvibes.IShape;
import dev.luhegi.miningvibes.IVibe;
import dev.luhegi.miningvibes.MiningVibes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.DrawSelectionEvent.HighlightBlock;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber
@OnlyIn(Dist.CLIENT)
public class HighlightBlockHandler {

  private static final Logger LOGGER = LogManager.getLogger();

  @SubscribeEvent
  public static void onHighlightBlock(HighlightBlock event) {
    ClientLevel world = Minecraft.getInstance().level;
    if (world.getBlockState(event.getTarget().getBlockPos()).is(Blocks.ORES_IN_GROUND_STONE)) {
      MiningVibes.CURRENT_SHAPE.getValidBlocks(MiningVibes.CURRENT_VIBE, world, event.getTarget().getBlockPos()).forEach(HighlightBlockHandler::drawAround);
    }
  }

  private static void drawAround(BlockPos pos) {
    Minecraft.getInstance().level.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0, 0);
  }
}
