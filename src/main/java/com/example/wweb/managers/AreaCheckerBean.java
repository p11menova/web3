package com.example.wweb.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
@Named("areaCheckerBean")
@SessionScoped
public class AreaCheckerBean implements Serializable {
    public AreaCheckerBean(){};

    public boolean checkIfInArea(float x, float y, int r) {

        return check1(x, y, r) || check3(x, y, r) || check4(x, y, r);
    }

    private boolean check1(float x, float y, int r) {
        return x >= 0 && y >= 0 && y < -x + r;
    }

    private boolean check3(float x, float y, int r) {
        return x <= 0 && y <= 0 && (x * x + y * y <= (float) (r * r)/4);
    }

    private boolean check4(float x, float y, int r) {
        return x >= 0 && y <= 0 && x <= (float) r / 2 && y >= -r;
    }


}
