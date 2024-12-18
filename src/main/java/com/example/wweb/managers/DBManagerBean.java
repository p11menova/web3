package com.example.wweb.managers;

import com.example.wweb.FormBean;
import com.example.wweb.models.Point;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("dbManagerBean")
@SessionScoped
public class DBManagerBean implements Serializable {
    public static List<Point> points = new ArrayList<>();
    public static Logger logger = Logger.getLogger(DBManagerBean.class.getName());


    public void addPoint(Point point){
        logger.info("adding Point to DB");
        points.add(0, point);
    }

    public List<Point> getPoints() {
        return points;
    }
}
