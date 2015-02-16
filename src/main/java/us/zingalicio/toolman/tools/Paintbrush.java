package us.zingalicio.toolman.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.zingalicio.cordstone.util.MessageUtil;
import us.zingalicio.cordstone.util.NameUtil;
import us.zingalicio.cordstone.util.SoundUtil;
import us.zingalicio.toolman.AbstractTool;
import us.zingalicio.toolman.Toolman;

public class Paintbrush extends AbstractTool
{
	Toolman plugin;
	SoundUtil soundUtil;
	YamlConfiguration materials;

	public Paintbrush(Toolman plugin)
	{
		this.plugin = plugin;
		this.toolName = "Paintbrush";
		this.materials = plugin.getMaterials();
	}

	@SuppressWarnings("deprecation")
public void onRangedUse(Player player, ItemStack item, Action action)
	{
		Block targetBlock = player.getTargetBlock(null, this.plugin.getToolManager().getRange());

		if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
		{
			List<String> newLore = new ArrayList<String>();

			String fullName = NameUtil.getFullName(targetBlock.getType(), targetBlock.getState().getData());

			newLore.add(ChatColor.GOLD + "Bona fide!");
			newLore.add(fullName);
			newLore.add(targetBlock.getType().name() + ":" + targetBlock.getData());

			if (item.getItemMeta().getLore().size() == 3)
			{
				List<?> lore = item.getItemMeta().getLore();

				if (lore.equals(newLore))
				{
					MessageUtil.sendMessage(plugin, player, "Paint was already " + ChatColor.WHITE + fullName + ChatColor.YELLOW + ".");
				}
				else
				{
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setLore(newLore);

					player.getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 0.1F, 0.031F);
					item.setItemMeta(itemMeta);
					MessageUtil.sendMessage(plugin, player, "Paint is now " + ChatColor.WHITE + fullName + ChatColor.YELLOW + ".");
				}
			}
			else
			{
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setLore(newLore);

				item.setItemMeta(itemMeta);
				MessageUtil.sendMessage(plugin, player, "Paint is now " + ChatColor.WHITE + fullName + ChatColor.YELLOW + ".");
			}

		}
		else if (item.getItemMeta().getLore().size() == 3)
		{
			if (player.isSneaking())
			{
				List<String> lore = item.getItemMeta().getLore();
				String fullData = lore.get(2);
				String[] splitData = fullData.split(":");

				List<String> rawData = new ArrayList<String>();

				for (String s : splitData)
				{
					rawData.add(s);
				}

				Material mat = Material.valueOf(rawData.get(0));
				byte data = Byte.parseByte(rawData.get(1));

				Material oldMat = targetBlock.getType();
				Byte oldData = Byte.valueOf(targetBlock.getData());

				if ((mat != oldMat) || (data != oldData))
				{
					changeBlock(Boolean.valueOf(false), targetBlock, mat, data, player, item);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
public void onCloseUse(Block block, BlockFace blockFace, Player player, ItemStack item, Action action)
	{
		if ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK))
		{
			List<String> newLore = new ArrayList<String>();

			String fullName = NameUtil.getFullName(block.getType(), block.getState().getData());

			newLore.add(ChatColor.GOLD + "Bona fide!");
			newLore.add(fullName);
			newLore.add(block.getType().name() + ":" + block.getData());

			if (item.getItemMeta().getLore().size() == 3)
			{
				List<String> lore = item.getItemMeta().getLore();

				if (lore.equals(newLore))
				{
					MessageUtil.sendMessage(plugin, player, "Paint was already " + ChatColor.WHITE + fullName + ChatColor.YELLOW + ".");
				}
				else
				{
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setLore(newLore);

					player.getWorld().playSound(player.getLocation(), Sound.LAVA_POP, 0.1F, 0.031F);
					item.setItemMeta(itemMeta);
					MessageUtil.sendMessage(plugin, player, "Paint is now " + ChatColor.WHITE + fullName + ChatColor.YELLOW + ".");
				}
			}
			else
			{
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setLore(newLore);

				item.setItemMeta(itemMeta);
				MessageUtil.sendMessage(plugin, player, "Paint is now " + ChatColor.WHITE + fullName + ChatColor.YELLOW + ".");
			}

		}
		else if (item.getItemMeta().getLore().size() == 3)
		{
			List<String> lore = item.getItemMeta().getLore();
			String fullData = lore.get(2);
			String[] splitData = fullData.split(":");

			List<String> rawData = new ArrayList<String>();

			for (String s : splitData)
			{
				rawData.add(s);
			}

			Material mat = Material.valueOf(rawData.get(0));
			byte data = Byte.parseByte(rawData.get(1));

			Material oldMat = block.getType();
			Byte oldData = Byte.valueOf(block.getData());

			if ((mat != oldMat) || (data != oldData))
			{
				if (player.isSneaking())
				{
					changeBlock(false, block, mat, data, player, item);
				}
				else
				{
					changeBlock(true, block, mat, data, player, item);
				}
			}
		}
	}
}