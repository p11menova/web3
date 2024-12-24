package com.example.wweb.models;


import jakarta.persistence.*;

@Table(name = "points")
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private float x;
    @Column
    private float y;
    @Column
    private int r;
    @Column
    private boolean result;
    @Column(name="script_time")
    private float scriptTime;
    @Column(name = "cur_time")
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    @Override
    public String toString() {
        return x + " " + y ;
    }
}