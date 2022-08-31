package com.project.sto.controller.modalWindowController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class ProgressBarModalController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ProgressBar progressBarId;

    private Task task;

    private void loadBar(){
        //TODO сделать прогресс бар и реализовать потоки
        progressBarId.progressProperty().unbind();
    }



}
