package org.example;

public class Vec3d {

    public float x;
    public float y;
    public float z;

    public Vec3d(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d multiplyMatrix4(Mat4x4 m) {
        float x = this.x * m.m[0][0] + this.y * m.m[1][0] + this.z * m.m[2][0] + m.m[3][0];
        float y = this.x * m.m[0][1] + this.y * m.m[1][1] + this.z * m.m[2][1] + m.m[3][1];
        float z = this.x * m.m[0][2] + this.y * m.m[1][2] + this.z * m.m[2][2] + m.m[3][2];
        float w = this.x * m.m[0][3] + this.y * m.m[1][3] + this.z * m.m[2][3] + m.m[3][3];
        if(w!=0) {
            x/=w;
            y/=w;
            z/=w;
        }
        return new Vec3d(x,y,z);
    }

}
