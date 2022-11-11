package dev.luhegi.miningvibes.pattern;

import com.mojang.serialization.Codec;
import java.util.Iterator;
import net.minecraftforge.common.util.BlockSnapshot;

public interface Pattern<T extends Pattern> extends Iterator<BlockSnapshot> {
  Codec<T> codec();
}