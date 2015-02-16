package us.zingalicio.toolman.util;

import java.util.ArrayList;

import org.spongepowered.api.block.BlockLoc;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.Direction;

public class BlockGetter
{
  public Direction getBlockFaceDirection(BlockLoc block, Player player)
  {
    Direction direction = null;

    ArrayList<BlockLoc> adjacentBlocks = new ArrayList<BlockLoc>();
    adjacentBlocks.add(block.getRelative(Direction.UP));
    adjacentBlocks.add(block.getRelative(Direction.DOWN));
    adjacentBlocks.add(block.getRelative(Direction.NORTH));
    adjacentBlocks.add(block.getRelative(Direction.SOUTH));
    adjacentBlocks.add(block.getRelative(Direction.EAST));
    adjacentBlocks.add(block.getRelative(Direction.WEST));

    for (BlockLoc b : adjacentBlocks)
    {
      if ((b == null) || (!(player.)
      {
        continue;
      }
      direction = block.;
      return blockFace;
    }

    return blockFace;
  }
}