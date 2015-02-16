package us.zingalicio.toolman.tools;

import us.zingalicio.cordstone.util.MessageUtil;
import us.zingalicio.cordstone.util.SoundUtil;
import us.zingalicio.toolman.AbstractTool;
import us.zingalicio.toolman.Toolman;

public class Breaker extends AbstractTool
{
  Toolman plugin;
  SoundUtil soundUtil;

  public Breaker(Toolman plugin)
  {
    this.plugin = plugin;
    this.toolName = "Breaker";
  }

  @SuppressWarnings("deprecation")
public void onRangedUse(Player player, ItemStack item, Action action)
  {
    Block targetBlock = player.getTargetBlock(null, this.plugin.getToolManager().getRange());

    if (player.isSneaking())
    {
      if (targetBlock.getType() != Material.AIR)
      {
        if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
        {
          breakBlock(Boolean.valueOf(true), targetBlock, player);
          return;
        }

        breakBlock(Boolean.valueOf(false), targetBlock, player);
        return;
      }

      MessageUtil.sendMessage(plugin, player, "Block out of range.");
      return;
    }
  }

  public void onCloseUse(Block clickedBlock, BlockFace blockFace, Player player, ItemStack item, Action action)
  {
    if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
    {
      breakBlock(Boolean.valueOf(true), clickedBlock, player);
      return;
    }

    breakBlock(Boolean.valueOf(false), clickedBlock, player);
  }
}