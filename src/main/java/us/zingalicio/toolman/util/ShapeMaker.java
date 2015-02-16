package us.zingalicio.toolman.util;

import java.util.ArrayList;

import org.spongepowered.api.block.BlockLoc;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.extent.Extent;

import com.flowpowered.math.vector.Vector3d;

public class ShapeMaker
{
  public ArrayList<BlockLoc> getCube(BlockLoc block, int radius)
  {
    ArrayList<BlockLoc> blocksInCube = new ArrayList<BlockLoc>();

    double centreX = block.getX();
    double centreY = block.getY();
    double centreZ = block.getZ();

    Extent extent = block.getExtent();

    for (double iX = -radius; iX < 1.0D; iX += 1.0D)
    {
      for (double iY = -radius; iY < 1.0D; iY += 1.0D)
      {
        for (double iZ = -radius; iZ < 1.0D; iZ += 1.0D)
        {
          Location blockLoc0 = new Location(extent, new Vector3d(centreX - iX, centreY - iY, centreZ - iZ));
          Location blockLoc1 = new Location(extent, new Vector3d(centreX + iX, centreY - iY, centreZ - iZ));
          Location blockLoc2 = new Location(extent, new Vector3d(centreX - iX, centreY + iY, centreZ - iZ));
          Location blockLoc3 = new Location(extent, new Vector3d(centreX + iX, centreY + iY, centreZ - iZ));
          Location blockLoc4 = new Location(extent, new Vector3d(centreX - iX, centreY - iY, centreZ + iZ));
          Location blockLoc5 = new Location(extent, new Vector3d(centreX + iX, centreY - iY, centreZ + iZ));
          Location blockLoc6 = new Location(extent, new Vector3d(centreX - iX, centreY + iY, centreZ + iZ));
          Location blockLoc7 = new Location(extent, new Vector3d(centreX + iX, centreY + iY, centreZ + iZ));

          blocksInCube.add(blockLoc0.getBlock());
          blocksInCube.add(blockLoc1.getBlock());
          blocksInCube.add(blockLoc2.getBlock());
          blocksInCube.add(blockLoc3.getBlock());
          blocksInCube.add(blockLoc4.getBlock());
          blocksInCube.add(blockLoc5.getBlock());
          blocksInCube.add(blockLoc6.getBlock());
          blocksInCube.add(blockLoc7.getBlock());
        }
      }
    }

    return blocksInCube;
  }
  
  public ArrayList<BlockLoc> getSphere(BlockLoc block, int radius)
  {
    ArrayList<BlockLoc> blocksInSphere = new ArrayList<BlockLoc>();

    double centreX = block.getX();
    double centreY = block.getY();
    double centreZ = block.getZ();

    Extent extent = block.getExtent();

    double adjRadius = radius + 0.5D;

    for (double iX = -radius; iX < 1.0D; iX += 1.0D)
    {
      for (double iY = -radius; iY < 1.0D; iY += 1.0D)
      {
        for (double iZ = -radius; iZ < 1.0D; iZ += 1.0D)
        {
          if (Math.pow(iX, 2.0D) + Math.pow(iY, 2.0D) + Math.pow(iZ, 2.0D) <= Math.pow(adjRadius, 2.0D))
          {
              Location blockLoc0 = new Location(extent, new Vector3d(centreX - iX, centreY - iY, centreZ - iZ));
              Location blockLoc1 = new Location(extent, new Vector3d(centreX + iX, centreY - iY, centreZ - iZ));
              Location blockLoc2 = new Location(extent, new Vector3d(centreX - iX, centreY + iY, centreZ - iZ));
              Location blockLoc3 = new Location(extent, new Vector3d(centreX + iX, centreY + iY, centreZ - iZ));
              Location blockLoc4 = new Location(extent, new Vector3d(centreX - iX, centreY - iY, centreZ + iZ));
              Location blockLoc5 = new Location(extent, new Vector3d(centreX + iX, centreY - iY, centreZ + iZ));
              Location blockLoc6 = new Location(extent, new Vector3d(centreX - iX, centreY + iY, centreZ + iZ));
              Location blockLoc7 = new Location(extent, new Vector3d(centreX + iX, centreY + iY, centreZ + iZ));

            blocksInSphere.add(blockLoc0.getBlock());
            blocksInSphere.add(blockLoc1.getBlock());
            blocksInSphere.add(blockLoc2.getBlock());
            blocksInSphere.add(blockLoc3.getBlock());
            blocksInSphere.add(blockLoc4.getBlock());
            blocksInSphere.add(blockLoc5.getBlock());
            blocksInSphere.add(blockLoc6.getBlock());
            blocksInSphere.add(blockLoc7.getBlock());
          }
        }
      }
    }
    return blocksInSphere;
  }
  
  public ArrayList<BlockLoc> getSquare(BlockLoc block, int radius)
  {
    ArrayList<BlockLoc> blocksInSquare = new ArrayList<BlockLoc>();

    double centreX = block.getX();
    double centreY = block.getY();
    double centreZ = block.getZ();

    Extent extent = block.getExtent();

    for (double iX = -radius; iX < 1.0D; iX += 1.0D)
    {
      for (double iZ = -radius; iZ < 1.0D; iZ += 1.0D)
      {
        Location blockLoc0 = new Location(extent, new Vector3d(centreX - iX, centreY, centreZ - iZ));
        Location blockLoc1 = new Location(extent, new Vector3d(centreX + iX, centreY, centreZ - iZ));
        Location blockLoc2 = new Location(extent, new Vector3d(centreX - iX, centreY, centreZ + iZ));
        Location blockLoc3 = new Location(extent, new Vector3d(centreX + iX, centreY, centreZ + iZ));

        blocksInSquare.add(blockLoc0.getBlock());
        blocksInSquare.add(blockLoc1.getBlock());
        blocksInSquare.add(blockLoc2.getBlock());
        blocksInSquare.add(blockLoc3.getBlock());
      }
    }
    return blocksInSquare;
  }
}