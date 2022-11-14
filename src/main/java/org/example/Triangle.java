package org.example;

public class Triangle {

    public final Vec3d[] p;

    public Triangle(Vec3d a, Vec3d b, Vec3d c) {
        this.p = new Vec3d[]{
                a, b, c
        };
    }
}
