package ultimat3.endgamemod.helpers;

public class CuboidIterator extends SphereIterator {
	
	public CuboidIterator(double min_x,double min_y,double min_z,double max_x,double max_y,double max_z) {
		super(min_x, min_y, min_z, max_x, max_y, max_z);
	}
	
	public CuboidIterator(double center_x, double center_y, double center_z, double radius) {
		super(center_x, center_y, center_z, radius);
	}
	
	@Override
	protected boolean inRadius() {
		if(!hollow) return true;
		return curX==maxX || curY == maxY || curZ == maxZ || curX==minX || curY == minY || curZ == minZ;
	}
}
