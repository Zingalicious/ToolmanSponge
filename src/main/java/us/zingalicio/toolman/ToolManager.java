package us.zingalicio.toolman;

import java.util.ArrayList;

import org.spongepowered.api.block.BlockLoc;
import org.spongepowered.api.entity.EntityInteractionType;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.Direction;

public class ToolManager
{
	Toolman plugin;
	String RANGED = "toolman.ranged.";
	String CLOSE = "toolman.close.";

	ArrayList<Tool> registeredTools = new ArrayList<Tool>();

	public ToolManager(Toolman plugin)
	{
		this.plugin = plugin;
	}

	public void registerTool(Tool t)
	{
		this.registeredTools.add(t);
	}

	public void onRangedUse(Player player, ItemStack item, EntityInteractionType action)
	{
		String displayName = MetadataUtil.getDisplayName(item.toContainer()).get();

		if (!PermissionsUtil.checkPermission(player, RANGED + displayName, false))
		{
			return;
		}
		for (Tool t : this.registeredTools)
		{
			if (!(t.getName().equals(displayName)))
			{
				continue;
			}
			t.onRangedUse(player, item, action);
		}
	}

	public boolean onCloseUse(BlockLoc clickedBlock, Direction direction, Player player, ItemStack item, EntityInteractionType action)
	{
		String displayName = MetadataUtil.getDisplayName(item.toContainer()).get();

		if (!PermissionsUtil.checkPermission(player, RANGED + displayName, false))
		{
			return false;
	    }
		for (Tool t : this.registeredTools)
		{
			if (!(t.getName().equals(displayName)))
			{
				continue;
			}
			t.onCloseUse(clickedBlock, direction, player, item, action);
			return true;
		}
		return false;
	}
}