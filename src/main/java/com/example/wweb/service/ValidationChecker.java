package com.example.wweb.service;

import com.example.wweb.models.ResponseStatus;

import java.io.Serializable;


public class ValidationChecker implements Serializable {

    public static ResponseStatus check(float x, float y, int r){
        if (r <= 0) return ResponseStatus.INVALID_R;

        if (!(-4 <= x && x <= 4 && -3 <= y && y <= 5 && r <= 5)) return ResponseStatus.INVALID_INTERVALS;

        return ResponseStatus.OK;

    }

}
