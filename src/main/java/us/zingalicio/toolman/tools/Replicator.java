package us.zingalicio.toolman.tools;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import us.zingalicio.toolman.AbstractTool;
import us.zingalicio.toolman.Toolman;
import us.zingalicio.cordstone.util.MessageUtil;
import us.zingalicio.cordstone.util.NameUtil;

public class Replicator extends AbstractTool
{
	Toolman plugin;

	public Replicator(Toolman plugin)
	{
		this.plugin = plugin;
		this.toolName = "Replicator";
	}

	@SuppressWarnings("deprecation")
public void onRangedUse(Player player, ItemStack item, Action action)
	{
		PlayerInventory inventory = player.getInventory();

		if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
		{
			for (int i = 0; i < 9; i++)
			{
				ItemStack invItem = inventory.getItem(i);
				if (invItem != null)
				{
					int maxStackSize = invItem.getMaxStackSize();

					if ((invItem.getAmount() != maxStackSize) && (invItem.getType() != Material.EMERALD) && (invItem.getData().getData() != 1))
					{
						invItem.setAmount(maxStackSize);
					}
				}
			}
			MessageUtil.sendMessage(plugin, player, "Refilled hotbar.");
			player.getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 0.1F, 0.031F);
			player.updateInventory();
			return;
		}

		else if (player.isSneaking())
		{
			Block targetBlock = player.getTargetBlock(null, this.plugin.getToolManager().getRange());

			Material mat = targetBlock.getType();
			byte data = targetBlock.getData();

			int maxStackSize = mat.getMaxStackSize();

			ItemStack newStack = new ItemStack(mat, maxStackSize, data);

			ItemStack extra = inventory.addItem(newStack).get(0);
			int amt;
			if(extra != null)
			{
				amt = maxStackSize - extra.getAmount();
			}
			else
			{
				amt = maxStackSize;
			}
			MessageUtil.sendMessage(plugin, player, "Given " + amt + " " + NameUtil.getItemName(newStack.getType(), newStack.getData()) + ".");
			player.getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 0.1F, 0.031F);
			player.updateInventory();
		}
	}

	@SuppressWarnings("deprecation")
public void onCloseUse(Block block, BlockFace blockFace, Player player, ItemStack item, Action action)
	{
		PlayerInventory inventory = player.getInventory();

		if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
		{
			for (int i = 0; i < 9; i++)
			{
				ItemStack invItem = inventory.getItem(i);
				if (invItem != null)
				{
					int maxStackSize = invItem.getMaxStackSize();

					if ((invItem.getAmount() != maxStackSize) && (invItem.getType() != Material.EMERALD) && (invItem.getData().getData() != 1))
					{
						invItem.setAmount(maxStackSize);
					}
				}
			}
			MessageUtil.sendMessage(plugin, player, "Refilled hotbar.");
			player.getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 0.1F, 0.031F);
			player.updateInventory();
			return;
		}
		else
		{
			Material mat = block.getType();
			byte data = block.getData();
	
			int maxStackSize = mat.getMaxStackSize();
	
			ItemStack newStack = new ItemStack(mat, maxStackSize, data);
			ItemStack extra = inventory.addItem(newStack).get(0);
			int amt;
			if(extra != null)
			{
				amt = maxStackSize - extra.getAmount();
			}
			else
			{
				amt = maxStackSize;
			}
			MessageUtil.sendMessage(plugin, player, "Given " + amt + " " + NameUtil.getItemName(newStack.getType(), newStack.getData()) + ".");
	
			player.getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 0.1F, 0.031F);
			player.updateInventory();
		}
	}
}