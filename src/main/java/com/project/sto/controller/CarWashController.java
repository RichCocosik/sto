package com.project.sto.controller;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.project.sto.dao.impl.CarWashServiceRepositoryImpl;
import com.project.sto.dao.impl.UserRepositoryImpl;
import com.project.sto.domain.User;
import com.project.sto.domain.workService.CarWashService;
import com.project.sto.service.Service;
import com.project.sto.service.resourcesPath.Path;
import com.project.sto.thread.AddToWorkDayThread;
import com.project.sto.thread.CheckUserThread;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class CarWashController extends ControllerCTO {

    @FXML
    private Button backButtonId;


    @FXML
    private ResourceBundle resources;

    @FXML
    private Label costLabelId;

    @FXML
    private URL location;

    @FXML
    private ComboBox<CarWashService> comboBoxId;

    @FXML
    private Label helloMessageLabelID;

    @FXML
    private Button payButtonID;

    @FXML
    private TextField phoneNumberTextFieldId;

    @FXML
    private Label successTextLabelId;

    @FXML
    private Label transactionLabelId;

    @FXML
    private Label errorLabelId;

    @FXML
    void OnActionBack(ActionEvent event) {
        try {
            Service.onTheNextSceneNotModalAndClose(Path.MAIN_MENU, "Main menu", 430, 340, payButtonID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionPay(ActionEvent event) throws InterruptedException {
        Boolean aBoolean = Service.validateFields(comboBoxId, phoneNumberTextFieldId, errorLabelId);
        System.out.println(aBoolean);
        if(aBoolean){
            Service.setVisibleAllFields(false, phoneNumberTextFieldId, comboBoxId, payButtonID, costLabelId);
            Service.setVisibleAllFields(true, successTextLabelId, transactionLabelId);

            CheckUserThread checkUserThread = new CheckUserThread(Integer.parseInt(phoneNumberTextFieldId.getText()), comboBoxId.getValue().getCostService(), comboBoxId.getValue().getNameService(), comboBoxId.getValue().getId());
//            try {
//                Service.onTheNextSceneModal(Path.PROGRESS, "Progress bar", 240,140, backButtonId);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            checkUserThread.start();

            //TODO Реализовать сокрытие всех кнопок. Показать надпись с успешной оплатой. Перенести данные в базу.
        }
    }

    @FXML
    void OnKeyTyped(KeyEvent event) {
        Service.validatePhoneNumber(phoneNumberTextFieldId, 9);
    }

    @FXML
    void initialize() {
            phoneNumberTextFieldId.setFocusTraversable(false);
            setupComboBox();
    }

    private void setupComboBox(){

        CarWashServiceRepositoryImpl carWashServiceRepository = new CarWashServiceRepositoryImpl();
        List<CarWashService> allService = carWashServiceRepository.getAll();
        System.out.println(allService);

        comboBoxId.setItems(FXCollections.observableArrayList(allService));
        comboBoxId.setCellFactory(param -> new ListCell<CarWashService>() {
            @Override
            protected void updateItem(CarWashService item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getNameService());
                } else {
                    // handle null object
                }
            }
        });
        comboBoxId.valueProperty().addListener((obs, oldVal, newVal) ->{
            comboBoxId.setButtonCell(comboBoxId.getCellFactory().call(null));
            costLabelId.setText("СТОИМОСТЬ: " + newVal.getCostService());
                });

    }


    @Override
    public void addData(Object object) {

    }
}
