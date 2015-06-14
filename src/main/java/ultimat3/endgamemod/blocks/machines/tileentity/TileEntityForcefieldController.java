package ultimat3.endgamemod.blocks.machines.tileentity;

import ultimat3.endgamemod.helpers.CuboidIterator;
import ultimat3.endgamemod.helpers.OctahedronIterator;
import ultimat3.endgamemod.helpers.SphereIterator;
import ultimat3.endgamemod.init.ModBlocks;
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

	public short radius;
	public short shape;

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

	public void eraseField(short oldShape, short oldRadius) {
		short temp_radius = radius;
		short temp_shape = shape;
		radius = oldRadius;
		shape = oldShape;
		for (SphereIterator it = getIter(); !it.end(); it.next()) {
			if (this.worldObj.getBlock(it.getX(), it.getY(), it.getZ()) == ModBlocks.blockForce)
				this.worldObj.setBlockToAir(it.getX(), it.getY(), it.getZ());
		}
		radius = temp_radius;
		shape = temp_shape;
	}
	

	private void drawField() {

		for (SphereIterator it = getIter(); !it.end(); it.next()) {
			if (this.worldObj.getBlock(it.getX(), it.getY(), it.getZ()) == Blocks.air)
				this.worldObj.setBlock(it.getX(), it.getY(), it.getZ(),
						ModBlocks.blockForce);
		}
		 
	}

	public void updateEntity() {
		short oldRadius = radius;
		short oldShape = shape;
		shape = radius = 0;
		for (int i = 0; i < items.length; i++) {
			if (this.items[i] == null)
				continue;
			if(this.items[i].getItem() != ModItems.itemFFModifiers) continue;
			if(this.items[i].getItemDamage() == 0) radius += items[i].stackSize;
			else
				shape = (short) this.items[i].getItemDamage();
		}
		
		if (shape > 0 && radius > 3) {
			if (shape != oldShape || radius != oldRadius)
				eraseField(oldShape, oldRadius);
			drawField();
		} else {
			eraseField(oldShape, oldRadius);
		}
	}

}
