package com.example.wweb;

import com.example.wweb.managers.AreaCheckerBean;
import com.example.wweb.managers.ErrorControllerBean;
import com.example.wweb.managers.ValidationCheckerBean;
import com.example.wweb.models.Point;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Named("formBean")
@SessionScoped
public class FormBean implements Serializable {

    public static Logger logger = Logger.getLogger(FormBean.class.getName());

    private final ValidationCheckerBean validationCheckerBean;
    private final ErrorControllerBean errorControllerBean;
    private final AreaCheckerBean areaCheckerBean;
    private float x = 0; // Изменено на float
    private float y = 0; // Изменено на float
    private int r = 0;
    private boolean result = false;
    private float scriptTime; // Время скрипта
    private String currentTime; // Текущее время
    private List<Point> tableData = new ArrayList<>();

    @Inject
    public FormBean(@Named("validationBean") ValidationCheckerBean validationCheckerBean, @Named("errorControllerBean") ErrorControllerBean errorControllerBean, @Named("areaCheckerBean") AreaCheckerBean areaCheckerBean) {
        this.validationCheckerBean = validationCheckerBean;
        this.errorControllerBean = errorControllerBean;
        this.areaCheckerBean = areaCheckerBean;
    }

    public FormBean(ValidationCheckerBean validationCheckerBean, ErrorControllerBean errorControllerBean, AreaCheckerBean areaCheckerBean, float x, float y, int r) {
        this.validationCheckerBean = validationCheckerBean;
        this.errorControllerBean = errorControllerBean;
        this.areaCheckerBean = areaCheckerBean;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    // Геттеры и сеттеры для x, y, r, scriptTime и currentTime
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

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public float getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(float scriptTime) {
        this.scriptTime = scriptTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public List<Point> getTableData() {
        return tableData;
    }

    public void processSubmit() {

        logger.info("button CHECK was clicked\n CURRENT VALUES:" + x + " " + y + " " + r);

        long executionStart = System.nanoTime();

        if (!validationCheckerBean.checkR(r)) {
            errorControllerBean.send400Error("ВЫБЕРИТЕ РАДИУС пж)");
            return;
        }
        else if (!validationCheckerBean.checkIntervals(x, y, r)) {
            errorControllerBean.send400Error("НЕЕЕЕТ сюда НЕЛЬЗЯ тапать");
            return;
        } else {
            setResult(areaCheckerBean.checkIfInArea(x, y, r));
            this.scriptTime = Float.parseFloat(new DecimalFormat("#0.00").format((System.nanoTime() - executionStart) * 0.000001).replaceAll(",", "."));
            LocalDateTime ldt = LocalDateTime.now();
            this.currentTime = ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            tableData.add(0, new Point(x, y, r, result, scriptTime, currentTime));
        }
    }


    public void addFromJS(){
//        final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        params.values().forEach(System.out::println);

//        try {
//            float x = Float.parseFloat(params.get("x"));
//            float y = Float.parseFloat(params.get("y"));
//            float r = Float.parseFloat(params.get("r"));
//
//            final Point attemptBean = new Point(x, y, r);
//            attemptBean.setStartTime(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
//            attemptBean.setHit(Validator.checkArea(x, y, r));
//            attemptBean.setScriptTime((long) ((System.nanoTime() - timer) * 0.01));
//
//            this.addPoint(attemptBean);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getCause());
//            System.out.println(e.getMessage());
//            System.out.println(e.getLocalizedMessage());
//        }
    }





}
