package us.zingalicio.toolman.tools;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import us.zingalicio.cordstone.util.MessageUtil;
import us.zingalicio.cordstone.util.SoundUtil;
import us.zingalicio.toolman.AbstractTool;
import us.zingalicio.toolman.Toolman;
import us.zingalicio.toolman.util.ShapeMaker;
import us.zingalicio.toolman.util.SphereMaker;

public class Chopper extends AbstractTool
{
  Toolman plugin;
  SoundUtil soundUtil;
  private ArrayList<Material> mask = new ArrayList<Material>();

  public Chopper(Toolman plugin)
  {
    this.plugin = plugin;
    this.toolName = "Chopper";
    this.mask.add(Material.LEAVES);
    this.mask.add(Material.LOG);
    this.mask.add(Material.CACTUS);
    this.mask.add(Material.VINE);
  }

  @SuppressWarnings("deprecation")
public void onRangedUse(Player player, ItemStack item, Action action)
  {
    Block targetBlock = player.getTargetBlock(null, this.plugin.getToolManager().getRange());
    if (player.isSneaking())
    {
      if (targetBlock.getType() != Material.AIR)
      {
        SphereMaker sphereMaker = new SphereMaker();

        if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
        {
          ArrayList<Block> blocks = sphereMaker.getSphere(targetBlock, 1);

          for (Block b : blocks)
          {
            if (this.mask.contains(b.getType()))
            {
              breakBlock(Boolean.valueOf(true), b, player);
            }
          }
        }
        else
        {
          ArrayList<Block> blocks = sphereMaker.getSphere(targetBlock, 2);
          for (Block b : blocks)
          {
            if (this.mask.contains(b.getType()))
            {
              breakBlock(Boolean.valueOf(true), b, player);
            }
          }
        }
        return;
      }
      MessageUtil.sendMessage(plugin, player, "Block out of range.");
      return;
    }
  }

  public void onCloseUse(Block block, BlockFace blockFace, Player player, ItemStack item, Action action)
  {
    ShapeMaker shapeMaker = new ShapeMaker();
    if (player.isSneaking())
    {
      if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
      {
        ArrayList<Block> blocks = shapeMaker.getCube(block, 1);

        for (Block b : blocks)
        {
          if (this.mask.contains(b.getType()))
          {
            breakBlock(Boolean.valueOf(true), b, player);
          }
        }
      }
      else
      {
        ArrayList<Block> blocks = shapeMaker.getCube(block, 2);
        for (Block b : blocks)
        {
          if (this.mask.contains(b.getType()))
          {
            breakBlock(Boolean.valueOf(true), b, player);
          }
        }
      }
    }
    else
    {
      SphereMaker sphereMaker = new SphereMaker();

      if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
      {
        ArrayList<Block> blocks = sphereMaker.getSphere(block, 1);

        for (Block b : blocks)
        {
          if (this.mask.contains(b.getType()))
          {
            breakBlock(Boolean.valueOf(true), b, player);
          }
        }
      }
      else
      {
        ArrayList<Block> blocks = sphereMaker.getSphere(block, 2);
        for (Block b : blocks)
        {
          if (this.mask.contains(b.getType()))
          {
            breakBlock(Boolean.valueOf(true), b, player);
          }
        }
      }
    }
  }
	@SuppressWarnings("deprecation")
	@Override
	public void breakBlock(Boolean physics, Block block, Player player)
	{
	  BlockBreakEvent breakEvent = new BlockBreakEvent(block, player);
	  Bukkit.getPluginManager().callEvent(breakEvent);
	  block.getWorld().playSound(block.getLocation(), SoundUtil.getSound(block.getType()), 0.1F, 1.0F);
	  block.setTypeId(0, physics.booleanValue());
	  if (!physics.booleanValue())
	  {
	    updateBlockChange(block);
	  }
	}
}