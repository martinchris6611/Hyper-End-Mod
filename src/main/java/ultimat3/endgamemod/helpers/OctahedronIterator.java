package ultimat3.endgamemod.helpers;

public class OctahedronIterator extends SphereIterator {

	private boolean ignoreX;
	private boolean ignoreY;
	private boolean ignoreZ;

	public OctahedronIterator(double mx, double my, double mz, double xx,
			double xy, double xz, double cx, double cy, double cz) {
		
		super(mx, my, mz, xx, xy, xz, cx, cy, cz);
	}

	public OctahedronIterator(double mx, double my, double mz, double xx,
			double xy, double xz, double cx, double cy, double cz, double rad) {
		
		super(mx, my, mz, xx, xy, xz, cx, cy, cz, rad);
	}

	public OctahedronIterator(double mx, double my, double mz, double xx,
			double xy, double xz) {
		super(mx, my, mz, xx, xy, xz);
	}

	public OctahedronIterator(double cx, double cy, double cz, double rad) {
		super(cx, cy, cz, rad);
	}

	public void setIgnoreX() {
		ignoreX = true;
		ignoreY = ignoreZ = false;
	}

	public void setIgnoreY() {
		ignoreY = true;
		ignoreX = ignoreZ = false;
	}

	public void setIgnoreZ() {
		ignoreZ = true;
		ignoreX = ignoreY = false;
	}

	@Override
	protected boolean inRadius() {
		double dx = ((double) curX - cenX) * (ignoreX ? 0 : 1.0);
		double dy = ((double) curY - cenY) * (ignoreY ? 0 : 1.0);
		double dz = ((double) curZ - cenZ) * (ignoreZ ? 0 : 1.0);
		double dist = dx + dy + dz;
		if (dist <= radius) {
			if (!hollow)
				return true;
			dist++;
			return dist < radius;
		}
		return false;
	}
}
