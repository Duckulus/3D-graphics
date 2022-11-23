package org.example;

import processing.core.PApplet;
import processing.core.PConstants;

import java.awt.*;
import java.util.List;

public class Processing extends PApplet {

    private List<Triangle> meshCube;
    private Mat4x4 projMat;
    float theta;

    @Override
    public void settings() {
        size(1000, 1000, PConstants.JAVA2D);
    }

    @Override
    public void setup() {
        Vec3d a = new Vec3d(0, 0, 0);
        Vec3d b = new Vec3d(0, 1, 0);
        Vec3d c = new Vec3d(1, 1, 0);
        Vec3d d = new Vec3d(1, 0, 0);
        Vec3d e = new Vec3d(0, 0, 1);
        Vec3d f = new Vec3d(0, 1, 1);
        Vec3d g = new Vec3d(1, 1, 1);
        Vec3d h = new Vec3d(1, 0, 1);

        meshCube = List.of(
                //SOUTH
                new Triangle(a, b, c),
                new Triangle(a, c, d),
                //NORTH
                new Triangle(h, g, e),
                new Triangle(h, f, e),
                //EAST
                new Triangle(d, c, g),
                new Triangle(d, g, h),
                //WEST
                new Triangle(e, f, b),
                new Triangle(e, b, a),
                //TOP
                new Triangle(b, f, g),
                new Triangle(b, g, c),
                //BOTTOM
                new Triangle(h, e, a),
                new Triangle(h, a, d)
        );

        float fNear = 0.1f;
        float fFar = 1000.0f;
        float fFov = 90.0f;
        float fAspectRatio = (float) height / width;
        float fFovRad = 1.0f / ((float) Math.tan(fFov * 0.5f / 180.0f * (float) Math.PI));

        projMat = new Mat4x4();
        projMat.m[0][0] = fAspectRatio * fFovRad;
        projMat.m[1][1] = fFovRad;
        projMat.m[2][2] = fFar / (fFar - fNear);
        projMat.m[3][2] = (-fFar * fNear) / (fFar - fNear);
        projMat.m[2][3] = 1.0f;
        projMat.m[3][3] = 0.0f;

    }

    @Override
    public void draw() {
        theta += 0.02;
        background(Color.BLACK.getRGB());

        float xRotationDiff = 0.5f;

        for (Triangle tri : meshCube) {
            stroke(Color.WHITE.getRGB());
            drawTriangle(
                    tri
                            .rotateZ(theta)
                            .rotateX(theta*xRotationDiff)
                            .translateZ(3f)
                            .project(projMat)
                            .center(width, height)
            );
        }
    }

    private void drawTriangle(Triangle tri) {
        line(tri.p[0].x, tri.p[0].y, tri.p[1].x, tri.p[1].y);
        line(tri.p[1].x, tri.p[1].y, tri.p[2].x, tri.p[2].y);
        line(tri.p[2].x, tri.p[2].y, tri.p[0].x, tri.p[0].y);
    }

}
