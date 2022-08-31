package com.project.sto.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.project.sto.domain.Worker;
import com.project.sto.service.Service;
import com.project.sto.service.resourcesPath.Path;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.project.sto.service.resourcesPath.Path.*;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button repairId;

    @FXML
    private Button washId;

    @FXML
    void OnActionRepairID(ActionEvent event) {
        try {
            Service.onTheNextSceneNotModalAndClose(SIGN_IN, "Login", 430, 340, repairId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionWashID(ActionEvent event) {
        try {
            Service.onTheNextSceneNotModalAndClose(CAR_WASH, "Car wash", 350,380, repairId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
