package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.helpers.CuboidIterator;
import ultimat3.endgamemod.helpers.OctahedronIterator;
import ultimat3.endgamemod.helpers.SphereIterator;
import ultimat3.endgamemod.init.ModItems;
import ultimat3.endgamemod.init.ModTileEntities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityForcefieldController extends TileEntityMachine implements
		IInventory {

	private short radius;
	private short shape;

	private static final String TAG_RADIUS = "radius";
	private static final String TAG_SHAPE = "shape";

	public TileEntityForcefieldController() {
		super(new ItemStack[12], "container."
				+ ModTileEntities.FORCEFIELD_CONTROLLER_ID);
		radius = 0;
		shape = 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound mainTag) {
		super.readFromNBT(mainTag);
		// reads other things
		this.radius = mainTag.getShort(TAG_RADIUS);
		this.shape = mainTag.getShort(TAG_SHAPE);
	}

	@Override
	public void writeToNBT(NBTTagCompound mainTag) {
		super.writeToNBT(mainTag);
		// Writes other things
		mainTag.setShort(TAG_RADIUS, radius);
		mainTag.setShort(TAG_SHAPE, shape);
	}

	private SphereIterator getIter() {
		switch (shape) {
		case ModItems.shapeCube:
			return new CuboidIterator(this.xCoord, this.yCoord, this.zCoord,
					radius).setHollow();
		case ModItems.shapeSphere:
			return new SphereIterator(this.xCoord, this.yCoord, this.zCoord,
					radius).setHollow();
		case ModItems.shapeOctahedron:
			return new OctahedronIterator(this.xCoord, this.yCoord,
					this.zCoord, radius).setHollow();
		default:
			return new SphereIterator(0, 0, 0, 0);
		}
	}

	private void eraseField() {
		for (SphereIterator it = getIter(); !it.end(); it.next()) {
			if (this.worldObj.getBlock(it.getX(), it.getY(), it.getZ()) == Blocks.glass)
				this.worldObj.setBlockToAir(it.getX(), it.getY(), it.getZ());
		}
	}

	private void drawField() {

		for (SphereIterator it = getIter(); !it.end(); it.next()) {
			if (this.worldObj.getBlock(it.getX(), it.getY(), it.getZ()) == Blocks.air)
				this.worldObj.setBlock(it.getX(), it.getY(), it.getZ(),
						Blocks.glass);
		}

		
		 /*for (int i = this.xCoord, I = 0; I < 10; I++, i++) { for (int j =
		 this.yCoord, J = 0; J < 10; J++, j++) { for (int k = this.zCoord, K =
		 0; K < 10; K++, k++) { if (this.worldObj.getBlock(i, j, k) ==
		 Blocks.air) this.worldObj.setBlock(i, j, k, Blocks.glass); } } }*/
		 
	}

	public void updateEntity() {
		short oldRadius = radius;
		short oldShape = shape;
		shape = radius = 0;
		// if(!this.worldObj.isRemote) {
		for (int i = 0; i < items.length; i++) {
			if (this.items[i] == null)
				continue;
			if (this.items[i].getItem() == Items.stick)
				radius++;
			else
				shape = (short) this.items[i].getItemDamage();
		}
		// this line shouldn't be here
//		shape = 1;	radius = 10;
		
		if (shape > 0 && radius > 3) {
			if (shape != oldShape || radius != oldRadius)
				eraseField();
			drawField();
		}
	}

}
