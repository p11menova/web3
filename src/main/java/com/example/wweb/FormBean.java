package com.example.wweb;

import com.example.wweb.managers.RequestHandlerBean;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;

import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Named("formBean")
@ManagedBean
@SessionScoped
public class FormBean implements Serializable {

    public static Logger logger = Logger.getLogger(FormBean.class.getName());
    private RequestHandlerBean requestHandlerBean = new RequestHandlerBean();

    @Inject
    public FormBean(RequestHandlerBean requestHandlerBean) {
        this.requestHandlerBean = requestHandlerBean;
    }

    private float x = 0;
    private float y = 0;
    private int r = 0;
    private float x_from_canvas = -100; // unreachable value

    private float y_from_canvas = -100;

    //boolean result;

    public FormBean() {
    }

    ;

    public FormBean(float x, float y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    // Геттеры и сеттеры для x, y, r
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public float getX_from_canvas() {
        return x_from_canvas;
    }

    public float getY_from_canvas() {
        return y_from_canvas;
    }

    public void setX_from_canvas(float x_from_canvas) {
        this.x_from_canvas = x_from_canvas;
    }

    public void setY_from_canvas(float y_from_canvas) {
        this.y_from_canvas = y_from_canvas;
    }

    public void clearX_from_canvas() {
        this.x_from_canvas = -100;
    }

    public void clearY_from_canvas() {
        this.y_from_canvas = -100;
    }

    public void processSubmit() {
        logger.info("button CHECK was clicked");
        if (x_from_canvas > -100 && y_from_canvas > -100) {
            logger.info("got values from JS x:" + x_from_canvas + " y:" + y_from_canvas);
            float x1 = x_from_canvas;
            float y1 = y_from_canvas;

            clearX_from_canvas();
            clearY_from_canvas();

            requestHandlerBean.handleRequest(x1, y1, getR());

        } else {
            logger.info("got values from input form:" + x + " " + y + " " + r);
            logger.info(x_from_canvas + " " + y_from_canvas);
            requestHandlerBean.handleRequest(x, y, r);
        }

    }

}
