package com.example.wweb.models;

public class Point {
    private float x;
    private float y;
    private int r;
    private boolean result;
    private float scriptTime;
    private String currentTime;



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public float getScriptTime() {
        return scriptTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public boolean getResult() {
        return result;
    }
    public void setX(float x) {
        this.x = (float) (Math.round(x * 1000.0) / 1000.0);
    }

    public void setY(float y) {
        this.y = (float) (Math.round(y * 1000.0) / 1000.0);
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setScriptTime(float scriptTime) {
        this.scriptTime = scriptTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}