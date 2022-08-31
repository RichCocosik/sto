package com.project.sto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.project.sto.dao.impl.WorkerRepositoryImpl;
import com.project.sto.domain.Worker;
import com.project.sto.service.Service;
import com.project.sto.thread.UpdateWorkerFromDataBaseThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.project.sto.service.resourcesPath.Path.*;

public class SignInController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginTextFieldID;

    @FXML
    private PasswordField passwordTextFieldID;

    @FXML
    private Button signInID;

    @FXML
    private Button backButtonId;

    @FXML
    private Label errorPasswordLabelId;

    private List<Worker> allWorkers;

    @FXML
    void ClickOnBackButton(ActionEvent event) {
        try {
            Service.onTheNextSceneNotModalAndClose(MAIN_MENU, "Main menu", 430, 340, signInID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ClickOnSignIn(ActionEvent event) {
        try {
            Optional.ofNullable(allWorkers)
                    .stream()
                    .flatMap(map -> map.stream())
                    .filter(worker1 -> worker1.getPhoneNumber().equals(Integer.parseInt(loginTextFieldID.getText()))
                            && worker1.getPassword().equals(passwordTextFieldID.getText()))
                    .findAny().ifPresentOrElse(present -> {
                        try {
                            Service.onNextSceneWithObject(ADMIN_MENU, "Admin menu", present, 430, 435, signInID);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }, () -> {
                        System.out.println("Пользователь не найден");
                        passwordTextFieldID.setStyle("-fx-border-color:red");
                        loginTextFieldID.setStyle("-fx-border-color:red");
                        errorPasswordLabelId.setVisible(true);
                    });
        } catch (NumberFormatException e) {
            System.out.println("Пользователь не найден");
            passwordTextFieldID.setStyle("-fx-border-color:red");
            loginTextFieldID.setStyle("-fx-border-color:red");
            errorPasswordLabelId.setVisible(true);
        }
    }

    @FXML
    void initialize() {
        WorkerRepositoryImpl workerRepository = new WorkerRepositoryImpl();
        allWorkers = workerRepository.getAll();
        System.out.println(allWorkers);
        UpdateWorkerFromDataBaseThread updateWorkerFromDataBaseThread = new UpdateWorkerFromDataBaseThread(allWorkers, signInID, this);
        updateWorkerFromDataBaseThread.start();
    }

    public void updateWorkersList(List<Worker> newWorkersList){
        allWorkers = newWorkersList;
    }

    public List<Worker> getAllWorkers() {
        return allWorkers;
    }
}
