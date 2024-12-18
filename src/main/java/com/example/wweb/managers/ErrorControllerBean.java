package com.example.wweb.managers;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;

@Named("errorControllerBean")
@SessionScoped
public class ErrorControllerBean implements Serializable {
    public void send400Error(String message) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setStatus(400);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"%s\"}".formatted(message));

            facesContext.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send405Error(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().setResponseStatus(405);
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Method Not Allowed", message);
        facesContext.addMessage(null, facesMessage);
    }

    public void send500Error(String message) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setStatus(500);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\": \"%s\"}".formatted(message));


            facesContext.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
