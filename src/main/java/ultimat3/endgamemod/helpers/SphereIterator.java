package ultimat3.endgamemod.helpers;

public class SphereIterator {
	protected double minX, minY, minZ;
	protected double maxX, maxY, maxZ;
	protected int curX, curY, curZ;
	protected double cenX, cenY, cenZ;
	protected double radius;
	protected boolean hollow;

	public SphereIterator(double min_x, double min_y, double min_z,
			double max_x, double max_y, double max_z, double center_x,
			double center_y, double center_z) {
		
		minX = Math.min(min_x, max_x);
		minY = Math.min(min_y, max_y);
		minZ = Math.min(min_z, max_z);

		maxX = Math.max(min_x, max_x);
		maxY = Math.max(min_y, max_y);
		maxZ = Math.max(min_z, max_z);

		curX = (int) minX;
		curY = (int) minY;
		curZ = (int) minZ;

		cenX = center_x;
		cenY = center_y;
		cenZ = center_z;

		radius = Math.min(Math.abs(cenX - minX), Math.abs(cenX - maxX));
		radius = Math.min(radius,
				Math.min(Math.abs(cenY - minY), Math.abs(cenY - maxY)));
		radius = Math.min(radius,
				Math.min(Math.abs(cenZ - minZ), Math.abs(cenZ - maxZ)));

		hollow = false;
	}

	public SphereIterator(double min_x, double min_y, double min_z,
			double max_x, double max_y, double max_z, double center_x,
			double center_y, double center_z, double Radius) {
		
		this(min_x, min_y, min_z, max_x, max_y, max_z, center_x, center_y,
				center_z);
		radius = Radius;
	}

	public SphereIterator(double min_x, double min_y, double min_z,
			double max_x, double max_y, double max_z) {
		
		this(min_x, min_y, min_z, max_x, max_y, max_z, (min_x + max_x) / 2.0,
				(min_y + max_y) / 2.0, (min_z + max_z) / 2.0);
	}

	public SphereIterator(double center_x, double center_y, double center_z,
			double Radius) {
		
		this(center_x - Radius, center_y - Radius, center_z - Radius, center_x
				+ Radius, center_y + Radius, center_z + Radius, center_x,
				center_y, center_z, Radius);
	}

	public SphereIterator setHollow() {
		hollow = true;
		return this;
	}

	protected void nextCheck() {

		if (curX >= maxX) {
			curX = (int) minX;

			if (curY >= maxY) {
				curY = (int) minY;
				curZ++;

			} else {
				curY++;
			}

		} else {
			curX++;
		}
	}

	protected boolean inRadius() {
		double dx = (double) curX - cenX;
		double dy = (double) curY - cenY;
		double dz = (double) curZ - cenZ;
		double dist = dx * dx + dy * dy + dz * dz;
		if (dist <= radius * radius) {
			if (!hollow)
				return true;
			dist += Math.max(Math.max(dx, dy), dz) * 2 + 1;
			return dist > radius * radius;
		}
		return false;
	}

	public void next() {
		nextCheck();
		while (!inRadius() && curZ <= maxZ) {
			nextCheck();
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
