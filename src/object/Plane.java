package object;

import ray.Ray;
import vectorlib.Normal3;
import vectorlib.Point3;
import color.Color;

/**
 * Constructs a {@link Geometry} Plane with a {@link Normal3} and a
 * {@link Point3}.
 * 
 * @param color
 *            = Color of the Plane.
 * @param a
 *            = {@link Normal3} to construct the Plane.
 * @param n
 *            = {@link Point3} to construct the Plane.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 * 
 */
public class Plane extends Geometry {

	public Point3 a;
	public Normal3 n;

	/**
	 * 
	 * @param color
	 *            = Color of the Plane.
	 * @param a
	 *            = {@link Normal3} to construct the Plane.
	 * @param n
	 *            = {@link Point3} to construct the Plane.
	 */

	public Plane(Color color, Point3 a, Normal3 n) {
		super(color);
		this.a = a;
		this.n = n;
	}

	@Override
	public Hit hit(Ray r) {
		double t = a.sub(r.o).dot(n) / r.d.dot(n);
		if (t > 0) {
			return new Hit(t, r, this);
		}
		return null;
	}

	@Override
	public String toString() {
		return "Plane [a=" + a + ", n=" + n + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plane other = (Plane) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}

}
