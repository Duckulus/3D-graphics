package org.example;

public class Triangle {

    public final Vec3d[] p;

    public Triangle(Vec3d a, Vec3d b, Vec3d c) {
        this.p = new Vec3d[]{
                a, b, c
        };
    }

    public Triangle rotateZ(float theta) {
        Mat4x4 rotMatZ = new Mat4x4();
        rotMatZ.m[0][0] = (float) Math.cos(theta);
        rotMatZ.m[0][1] = (float) Math.sin(theta);
        rotMatZ.m[1][0] = (float) -Math.sin(theta);
        rotMatZ.m[1][1] = (float) Math.cos(theta);
        rotMatZ.m[2][2] = 1;
        rotMatZ.m[3][3] = 1;
        return new Triangle(
                this.p[0].multiplyMatrix4(rotMatZ),
                this.p[1].multiplyMatrix4(rotMatZ),
                this.p[2].multiplyMatrix4(rotMatZ)
        );
    }

    public Triangle rotateX(float theta) {
        Mat4x4 rotMatX = new Mat4x4();
        rotMatX.m[0][0] = 1;
        rotMatX.m[1][1] = (float) Math.cos(theta);
        rotMatX.m[1][2] = (float) Math.sin(theta);
        rotMatX.m[2][1] = (float) -Math.sin(theta);
        rotMatX.m[2][2] = (float) Math.cos(theta);
        rotMatX.m[3][3] = 1;
        return new Triangle(
                this.p[0].multiplyMatrix4(rotMatX),
                this.p[1].multiplyMatrix4(rotMatX),
                this.p[2].multiplyMatrix4(rotMatX)
        );
    }

    public Triangle translateZ(float amount) {
        Triangle translated = this.copy();
        translated.p[0].z += amount;
        translated.p[1].z += amount;
        translated.p[2].z += amount;
        return translated;
    }

    public Triangle project(Mat4x4 projMat) {
        return new Triangle(
                this.p[0].multiplyMatrix4(projMat),
                this.p[1].multiplyMatrix4(projMat),
                this.p[2].multiplyMatrix4(projMat)
        );

    }

    public Triangle center(float width, float height) {
        Triangle centered = this.copy();

        centered.p[0].x += 1.0;
        centered.p[0].y += 1.0;
        centered.p[1].x += 1.0;
        centered.p[1].y += 1.0;
        centered.p[2].x += 1.0;
        centered.p[2].y += 1.0;

        centered.p[0].x *= 0.5f * width;
        centered.p[0].y *= 0.5f * height;
        centered.p[1].x *= 0.5f * width;
        centered.p[1].y *= 0.5f * height;
        centered.p[2].x *= 0.5f * width;
        centered.p[2].y *= 0.5f * height;

        return centered;
    }

    public Triangle copy() {
        return new Triangle(
                new Vec3d(this.p[0].x, this.p[0].y, this.p[0].z),
                new Vec3d(this.p[1].x, this.p[1].y, this.p[1].z),
                new Vec3d(this.p[2].x, this.p[2].y, this.p[2].z)
        );
    }

}
