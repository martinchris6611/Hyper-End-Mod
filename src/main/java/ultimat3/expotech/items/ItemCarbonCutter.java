package ultimat3.expotech.items;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ultimat3.expotech.ExpoTech;
import ultimat3.expotech.Reference;

public class ItemCarbonCutter extends ItemPickaxe {
	String name = "carbonCutter";
	Random rand = new Random();

	public ItemCarbonCutter() {
		super(ToolMaterial.IRON);
		GameRegistry.registerItem(this, name, Reference.MOD_ID);
		this.setUnlocalizedName(Reference.MOD_ID + "_" + name);
		this.setTextureName(Reference.RESOURCE_PREFIX + name);
		this.setCreativeTab(ExpoTech.creaTab);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack item, World world, Block block, int x, int y, int z,
			EntityLivingBase player) {
		item.damageItem(1, player);
		if (world.isRemote)
			return true;
		if (block == Blocks.coal_ore) {
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.coal)));
			int tmp = rand.nextInt() % 100;
			if (tmp == 0)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.diamond)));
		} else if (block == Blocks.diamond_ore) {
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.diamond)));
		}
		return true;
	}
}
