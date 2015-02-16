package us.zingalicio.toolman;

public abstract interface Tool
{
  public abstract String getName();

  public abstract void onRangedUse(Player paramPlayer, ItemStack paramItemStack, Action paramAction);

  public abstract void onCloseUse(Block paramBlock, BlockFace paramBlockFace, Player paramPlayer, ItemStack paramItemStack, Action paramAction);
}