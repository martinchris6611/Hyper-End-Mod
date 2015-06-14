package ultimat3.endgamemod.items;

import ultimat3.endgamemod.EndGame;
import ultimat3.endgamemod.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword {
	
	private final String		name;
	public static final String	WEAPON_LOC	= "";
	
	public ItemModSword(String name, ToolMaterial material) {
		super(material);
		this.name = name;
		this.setUnlocalizedName(Reference.MOD_ID + "_" + name);
		this.setTextureName(Reference.RESOURCE_PREFIX + name);
		this.setCreativeTab(EndGame.creaTab);
	}

	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase entity, EntityLivingBase p_77644_3_) {
		entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, Reference.RESOURCE_PREFIX + "swordHit", 1, 1);
		return super.hitEntity(p_77644_1_, entity, p_77644_3_);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		entityLiving.worldObj.playSoundEffect(entityLiving.posX, entityLiving.posY, entityLiving.posZ, Reference.RESOURCE_PREFIX + "swordSwing", 1, 1);
		return false;
	}
}
