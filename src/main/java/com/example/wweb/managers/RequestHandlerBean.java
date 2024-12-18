package com.example.wweb.managers;

import com.example.wweb.FormBean;
import com.example.wweb.models.Point;
import com.example.wweb.models.ResponseStatus;
import com.example.wweb.service.AreaChecker;
import com.example.wweb.service.ValidationChecker;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class RequestHandlerBean implements Serializable {
    private ErrorControllerBean errorControllerBean;
    private DBManagerBean dbManagerBean;


    public static Logger logger = Logger.getLogger(RequestHandlerBean.class.getName());

    @Inject
    public RequestHandlerBean(ErrorControllerBean errorControllerBean, DBManagerBean dbManagerBean) {
        this.errorControllerBean = errorControllerBean;
        this.dbManagerBean = dbManagerBean;
    }

    public RequestHandlerBean(){};

    public void handleRequest(float x, float y, int r){
        logger.info("я весь такой в request handler");
        if (processValidationCheck(ValidationChecker.check(x,y,r))) {
            logger.info("даа все норм со значениями");
            Point point = initialPoint(x,y,r);
            if ( point == null ) {
                errorControllerBean.send500Error("хз чето не так пошло");
                return;}

            dbManagerBean.addPoint(point);
        }
    }

    public boolean processValidationCheck(ResponseStatus status){
        if (status == ResponseStatus.OK)  return true;
        if (status == ResponseStatus.INVALID_R){
            errorControllerBean.send400Error("ВЫБЕРИТЕ РАДИУС пж)");
            return false;
        }
        errorControllerBean.send400Error("НЕЕЕЕТ сюда НЕЛЬЗЯ тапать");
        return false;
    }

    public Point initialPoint(float x, float y, int r){
        long executionStart = System.nanoTime();

        boolean result = AreaChecker.checkIfInArea(x,y,r);
        logger.info("RESULT for values: " + result);
        LocalDateTime ldt = LocalDateTime.now();
        String currentTime = ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setResult(result);
        point.setCurrentTime(currentTime);
        point.setScriptTime(Float.parseFloat(new DecimalFormat("#0.00").format((System.nanoTime() - executionStart) * 0.000001).replaceAll(",", ".")));

        return point;
    }
}
