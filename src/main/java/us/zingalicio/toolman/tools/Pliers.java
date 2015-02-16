package us.zingalicio.toolman.tools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import us.zingalicio.cordstone.util.MessageUtil;
import us.zingalicio.cordstone.util.SoundUtil;
import us.zingalicio.toolman.AbstractTool;
import us.zingalicio.toolman.Toolman;
import us.zingalicio.toolman.util.BlockGetter;

public class Pliers extends AbstractTool
{
  Toolman plugin;
  SoundUtil soundUtil;

  public Pliers(Toolman plugin)
  {
    this.plugin = plugin;
    this.toolName = "Pliers";
  }

  @SuppressWarnings("deprecation")
public void onRangedUse(Player player, ItemStack item, Action action)
  {
    Block targetBlock = player.getTargetBlock(null, this.plugin.getToolManager().getRange());

    BlockGetter blockGetter = new BlockGetter();
    BlockFace blockFace = blockGetter.getBlockFace(targetBlock, player);

    Material mat = targetBlock.getType();
    byte data = targetBlock.getData();

    if ((player.isSneaking()) && (blockFace != null))
    {
      if (mat != Material.AIR)
      {
        if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
        {
          BlockFace oppositeFace = blockFace.getOppositeFace();
          Block newBlock = targetBlock.getRelative(oppositeFace);

          if (newBlock.getType() == Material.AIR)
          {
            placeBlock(Boolean.valueOf(true), newBlock, mat, data, player, item);
          }
          return;
        }

        Block newBlock = targetBlock.getRelative(blockFace);

        if (newBlock.getType() == Material.AIR)
        {
          placeBlock(Boolean.valueOf(true), newBlock, mat, data, player, item);
        }
        return;
      }

      MessageUtil.sendMessage(plugin, player, "Block out of range.");
      return;
    }
  }

  @SuppressWarnings("deprecation")
public void onCloseUse(Block clickedBlock, BlockFace blockFace, Player player, ItemStack item, Action action)
  {
    Block newBlock = clickedBlock.getRelative(blockFace);
    Block newBlockO = clickedBlock.getRelative(blockFace.getOppositeFace());

    Material mat = clickedBlock.getType();
    byte data = clickedBlock.getData();

    Material newMat = newBlock.getType();
    Material newMatO = newBlockO.getType();

    if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
    {
      if ((newBlockO != null) && (mat != Material.AIR))
      {
        if (player.isSneaking())
        {
          if (newMatO != Material.AIR)
          {
            changeBlock(Boolean.valueOf(true), newBlockO, mat, data, player, item);
            return;
          }
          placeBlock(Boolean.valueOf(true), newBlockO, mat, data, player, item);
          return;
        }
        if (newMatO == Material.AIR)
        {
          placeBlock(Boolean.valueOf(true), newBlockO, mat, data, player, item);
          return;
        }

        MessageUtil.sendMessage(plugin, player, "Sneak to overwrite blocks.");
      }

    }
    else if ((newBlock != null) && (mat != Material.AIR))
    {
      if (player.isSneaking())
      {
        if (newMat != Material.AIR)
        {
          changeBlock(Boolean.valueOf(true), newBlock, mat, data, player, item);
          return;
        }
        placeBlock(Boolean.valueOf(true), newBlock, mat, data, player, item);
        return;
      }
      if (newMat == Material.AIR)
      {
        placeBlock(Boolean.valueOf(true), newBlock, mat, data, player, item);
        return;
      }

      MessageUtil.sendMessage(plugin, player, "Sneak to overwrite blocks.");
      return;
    }
  }
}