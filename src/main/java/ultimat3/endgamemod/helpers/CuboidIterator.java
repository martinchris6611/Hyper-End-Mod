package ultimat3.endgamemod.helpers;

public class CuboidIterator {
	protected int minX, minY, minZ;
	protected int maxX, maxY, maxZ;
	protected int curX, curY, curZ;
	private boolean hollow;
	public CuboidIterator(int min_x,int min_y,int min_z,int max_x,int max_y,int max_z) {
		minX = Math.min(min_x, max_x);
		minY = Math.min(min_y, max_y);
		minZ = Math.min(min_z, max_z);
		
		maxX = Math.max(min_x, max_x);
		maxY = Math.max(min_y, max_y);
		maxZ = Math.max(min_z, max_z);
		
		curX = minX;
		curY = minY;
		curZ = minZ;
		
		hollow = false;
	}
	
	public void setHollow() {
		hollow = true;
	}
	
	private void nextCheck() {
		
		if(curX == maxX) {
			curX = minX;
			
			if(curY == maxY) {
				curY = minY;
				curZ++;
				
			} else {
				curY++;
			}
			
		} else {
			curX++;
		}
	}
	
	public void next() {
		nextCheck();
		if(hollow) {
			while(curX!=maxX && curY != maxY && curZ != maxZ && curX!=minX && curY != minY && curZ != minZ) {
				nextCheck();
			}
		}
	}
	
	public boolean end() {
		return curZ > maxZ;
	}
	
	public int getX() {
		return curX;
	}
	
	public int getY() {
		return curY;
	}
	
	public int getZ() {
		return curZ;
	}
}
