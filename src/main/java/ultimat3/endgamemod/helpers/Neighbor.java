package ultimat3.endgamemod.helpers;

public class Neighbor {

	public int x, y, z;
	private int dx, dy, dz;
	int minx, miny, minz;
	int maxx, maxy, maxz;

	public Neighbor(int _x, int _y, int _z) {
		x = _x - 1;
		y = _y - 1;
		z = _z - 1;
		dx = dy = dz = -1;
		minx = miny = minz = -1;
		maxx = maxy = maxz = 1;
	}

	public Neighbor(int _x, int _y, int _z, int mx, int my, int mz, int xx, int xy, int xz) {
		this(_x, _y, _z);
		dx=minx=mx;
		dy=miny=my;
		dz=minz=mz;
		maxx=xx;
		maxy=xy;
		maxz=xz;
	}

	public void next() {
		if (dx != maxx) {
			dx++;
			x++;
		} else {
			x -= dx - minx;
			dx = minx;
			if (dy != maxy) {
				dy++;
				y++;
			} else {
				y -= dy - miny;
				dy = miny;
				dz++;
				z++;
			}
		}
	}

	public boolean end() {
		return dz > maxz;
	}

}
