package object;

import ray.Ray;
import vectorlib.Normal3;
import vectorlib.Point3;
import color.Color;

public class Plane extends Geometry {

	public Point3 a;
	public Normal3 n;

	public Plane(Color color, Point3 a, Normal3 n) {
		super(color);
		this.a = a;
		this.n = n;
	}

	@Override
	public Hit hit(Ray r) {
		double t = a.sub(r.o).dot(n) / r.d.dot(n);
		return new Hit(t, r, this);
	}

}