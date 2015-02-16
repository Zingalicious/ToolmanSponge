package us.zingalicio.toolman;

import org.spongepowered.api.block.BlockLoc;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.entity.EntityInteractionType;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.event.block.BlockBreakEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.extent.Extent;

import us.zingalicio.cordstone.util.SoundUtil;

public abstract class AbstractTool
  implements Tool
{
  public String toolName = "Default Tool";

  public String getName()
  {
    return this.toolName;
  }

  public void onRangedUse(Player player, ItemStack item, EntityInteractionType action)
  {
    player.sendMessage("Ranged " + MetadataUtil.getDisplayName(item.toContainer()));
  }

  public void onCloseUse(BlockLoc targetBlock, Direction direction, Player player, ItemStack item, EntityInteractionType action)
  {
    player.sendMessage("Close " + MetadataUtil.getDisplayName(item.toContainer()));
  }

  @SuppressWarnings("deprecation")
public void updateBlockChange(BlockLoc changedBlock)
  {
    int horizon = BlockUpdateUtil.getViewDistance();
    int horSqr = (int)Math.pow(horizon, 2.0D);
    Extent extent = changedBlock.getExtent();
    for (Player player : Toolman.getGame().getServer().get().getOnlinePlayers())
    {
      if ((extent.equals(player.getWorld())) && 
        (changedBlock.getLocation().getPosition().distanceSquared(player.getLocation().getPosition()) < horSqr))
      {
        player.sendBlockChange(changedBlock.getLocation(), changedBlock.getType(), changedBlock.getData());
      }
    }
  }

  @SuppressWarnings("deprecation")
  public void breakBlock(Boolean physics, BlockLoc block, Player player)
  {
    BlockBreakEvent breakEvent = new BlockBreakEvent(block, player);
    Bukkit.getPluginManager().callEvent(breakEvent);
    block.getWorld().playSound(block.getLocation(), SoundUtil.getSound(block.getType()), 1.0F, 1.0F);
    block.setTypeId(0, physics.booleanValue());
    if (!physics.booleanValue())
    {
      updateBlockChange(block);
    }
  }

  @SuppressWarnings("deprecation")
  public void changeBlock(Boolean physics, BlockLoc block, BlockType type, byte data, Player player, ItemStack item)
  {
    BlockBreakEvent breakEvent = new BlockBreakEvent(block, player);
    Bukkit.getPluginManager().callEvent(breakEvent);
    block.getWorld().playSound(block.getLocation(), SoundUtil.getSound(block.getType()), 0.2F, 1.0F);

    block.setTypeId(mat.getId(), physics.booleanValue());
    block.setData(data, physics.booleanValue());

    block.getWorld().playSound(block.getLocation(), SoundUtil.getSound(block.getType()), 1.0F, 1.0F);

    BlockPlaceEvent placeEvent = new BlockPlaceEvent(block, block.getState(), block, item, player, true);
    Bukkit.getPluginManager().callEvent(placeEvent);

    if (!physics.booleanValue())
    {
      updateBlockChange(block);
    }
  }

  @SuppressWarnings("deprecation")
  public void placeBlock(Boolean physics, Block block, Material mat, byte data, Player player, ItemStack item)
  {
    block.setTypeId(mat.getId(), physics.booleanValue());
    block.setData(data, physics.booleanValue());

    block.getWorld().playSound(block.getLocation(), SoundUtil.getSound(block.getType()), 1.0F, 1.0F);

    BlockPlaceEvent placeEvent = new BlockPlaceEvent(block, block.getState(), block, item, player, true);
    Bukkit.getPluginManager().callEvent(placeEvent);

    if (!physics.booleanValue())
    {
      updateBlockChange(block);
    }
  }
}