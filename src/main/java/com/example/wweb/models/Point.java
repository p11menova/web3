package com.example.wweb.models;

public class Point {
    private final float x; // Изменено на float
    private final float y; // Изменено на float
    private final int r;
    private final float scriptTime;
    private final String currentTime;
    private final boolean result;

    public Point(float x, float y, int r, boolean result, float scriptTime, String currentTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.scriptTime = scriptTime;
        this.currentTime = currentTime;
        this.result = result; // Пример вычисления результата
    }

    // Геттеры
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
}