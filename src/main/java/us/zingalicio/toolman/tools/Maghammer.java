package us.zingalicio.toolman.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import us.zingalicio.cordstone.util.MessageUtil;
import us.zingalicio.cordstone.util.SoundUtil;
import us.zingalicio.toolman.AbstractTool;
import us.zingalicio.toolman.Toolman;
import us.zingalicio.toolman.util.BlockGetter;

public class Maghammer extends AbstractTool
{
  Toolman plugin;
  SoundUtil soundUtil;

  public Maghammer(Toolman plugin)
  {
    this.plugin = plugin;
    this.toolName = "Maghammer";
  }

  @SuppressWarnings("deprecation")
public void onRangedUse(Player player, ItemStack item, Action action)
  {
    Block targetBlock = player.getTargetBlock(null, this.plugin.getToolManager().getRange());

    BlockGetter blockGetter = new BlockGetter();
    BlockFace blockFace = blockGetter.getBlockFace(targetBlock, player);

    Material mat = targetBlock.getType();

    if ((player.isSneaking()) && (blockFace != null))
    {
      if (mat != Material.AIR)
      {
        if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
        {
          BlockFace oppositeFace = blockFace.getOppositeFace();
          Block newBlock = targetBlock.getRelative(oppositeFace);
          moveBlock(Boolean.valueOf(false), Boolean.valueOf(true), item, player, newBlock, targetBlock);
          return;
        }

        Block newBlock = targetBlock.getRelative(blockFace);
        moveBlock(Boolean.valueOf(false), Boolean.valueOf(true), item, player, newBlock, targetBlock);
        return;
      }
      else
      {
      MessageUtil.sendMessage(plugin, player, "Block out of range.");
      return;
      }
    }
  }

  public void onCloseUse(Block clickedBlock, BlockFace blockFace, Player player, ItemStack item, Action action)
  {
    Block newBlock = clickedBlock.getRelative(blockFace);
    Block newBlockO = clickedBlock.getRelative(blockFace.getOppositeFace());

    Material mat = clickedBlock.getType();

    if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
    {
      if ((newBlockO != null) && (mat != Material.AIR))
      {
        if (player.isSneaking())
        {
          moveBlock(Boolean.valueOf(true), Boolean.valueOf(false), item, player, newBlockO, clickedBlock);
          return;
        }

        moveBlock(Boolean.valueOf(false), Boolean.valueOf(true), item, player, newBlockO, clickedBlock);
      }

    }
    else if ((newBlock != null) && (mat != Material.AIR))
    {
      if (player.isSneaking())
      {
        moveBlock(true, false, item, player, newBlock, clickedBlock);
        return;
      }

      moveBlock(false, true, item, player, newBlock, clickedBlock);
      return;
    }
  }

  @SuppressWarnings("deprecation")
private void moveBlock(Boolean overwrite, Boolean physics, ItemStack item, Player player, Block destination, Block clicked)
  {
    int id = clicked.getTypeId();
    byte data = clicked.getData();
    BlockState state = clicked.getState();
    if (destination.getType() == Material.AIR)
    {
      breakBlock(physics, clicked, player);

      destination.setTypeId(id, physics.booleanValue());
      destination.setData(data, physics.booleanValue());

      BlockPlaceEvent placeEvent = new BlockPlaceEvent(destination, state, clicked, item, player, true);
      Bukkit.getPluginManager().callEvent(placeEvent);
      return;
    }
    if (overwrite.booleanValue())
    {
      breakBlock(physics, clicked, player);

      BlockBreakEvent breakEvent2 = new BlockBreakEvent(destination, player);
      Bukkit.getPluginManager().callEvent(breakEvent2);

      destination.getWorld().playSound(destination.getLocation(), SoundUtil.getSound(destination.getType()), 1.0F, 1.0F);

      destination.setTypeId(id, physics.booleanValue());
      destination.setData(data, physics.booleanValue());

      BlockPlaceEvent placeEvent = new BlockPlaceEvent(destination, state, clicked, item, player, true);
      Bukkit.getPluginManager().callEvent(placeEvent);
      return;
    }
    else
    {
      player.sendMessage(ChatColor.GOLD + "[Toolman] Crouch to overwrite blocks.");
    }
  }
}