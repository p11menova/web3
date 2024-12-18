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
    //boolean result;

    public FormBean(){};

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
//    public boolean isResult() {
//        return result;
//    }
//
//    public void setResult(boolean result) {
//        logger.info("да я обожаю добавлять результаты в форму:"+result);
//        this.result = result;
//    }

    public void processSubmit() {

        logger.info("button CHECK was clicked\n CURRENT VALUES:" + x + " " + y + " " + r);

        requestHandlerBean.handleRequest(x, y, r);

    }







}
