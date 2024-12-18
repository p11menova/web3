package com.example.wweb.service;

import java.io.Serializable;


public class AreaChecker implements Serializable {

    public static boolean checkIfInArea(float x, float y, int r) {
        return check1(x, y, r) || check3(x, y, r) || check4(x, y, r);
    }

    static boolean check1(float x, float y, int r) {
        return x >= 0 && y >= 0 && y < -x + r;
    }

    static boolean check3(float x, float y, int r) {
        return x <= 0 && y <= 0 && (x * x + y * y <= (float) (r * r)/4);
    }

    static boolean check4(float x, float y, int r) {
        return x >= 0 && y <= 0 && x <= (float) r / 2 && y >= -r;
    }


}
