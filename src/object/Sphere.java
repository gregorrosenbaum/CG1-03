package object;

import ray.Ray;
import vectorlib.Normal3;
import vectorlib.Point3;
import vectorlib.Vector3;
import color.Color;

/**
 * Constructs a {@link Geometry} Sphere with a radius, a {@link Point3} and a
 * {@link Color}.
 * 
 * @param color
 *            = Color of the Sphere.
 * @param c
 *            = {@link Point3} of the Sphere.
 * @param r
 *            = Radius of the Sphere.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public class Sphere extends Geometry {

	public Point3 c;
	public double r;

	/**
	 * Constructs a {@link Geometry} Sphere with a radius, a {@link Point3} and
	 * a {@link Color}.
	 * 
	 * @param color
	 *            = Color of the Sphere.
	 * @param c
	 *            = {@link Point3} of the Sphere.
	 * @param r
	 *            = Radius of the Sphere.
	 */
	public Sphere(Color color, Point3 c, double r) {
		super(color);
		this.c = c;
		this.r = r;
	}

	@Override
	public Hit hit(Ray ray) {

		double a = ray.d.dot(ray.d);
		Vector3 test = (ray.o.sub(c));
		double b = ray.d.dot(test.mul(2));
		double cn = (ray.o.sub(c).dot(ray.o.sub(c)) - (this.r * this.r));
		double d = (b * b) - 4.0 * a * cn;
		double t1 = (-b + Math.sqrt(d)) / (2.0 * a);
		double t2 = (-b - Math.sqrt(d)) / (2.0 * a);

		if (d > 0) {
			if (t1 > t2) {
				return new Hit(t2, ray, this);
			} else {
				return new Hit(t1, ray, this);
			}
		}
		return null;
	}
}