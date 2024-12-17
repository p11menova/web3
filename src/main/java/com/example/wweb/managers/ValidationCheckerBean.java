package com.example.wweb.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("validationBean")
@SessionScoped
public class ValidationCheckerBean implements Serializable {

    private float x;
    private float y;
    private int r;
    public ValidationCheckerBean(){}

    public ValidationCheckerBean(float x, float y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public boolean checkR(int r){
        return !(r==0);
    }

    public boolean checkIntervals(float x, float y, int r){
        return -4 <= x && x <= 4 &&
                -3 <= y && y <= 5 &&
                1 <= r && r <= 5;
    }
}
