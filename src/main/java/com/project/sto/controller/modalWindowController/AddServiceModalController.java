package com.project.sto.controller.modalWindowController;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.sto.controller.ControllerCTO;
import com.project.sto.dao.impl.CarWashServiceRepositoryImpl;
import com.project.sto.domain.workService.CarWashService;
import com.project.sto.domain.workService.ServiceWork;
import com.project.sto.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddServiceModalController extends ControllerCTO<CarWashService> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addValueButtonId;

    @FXML
    private Button backButtonId;

    @FXML
    private TextField costTextFieldId;

    @FXML
    private Label errorLabelId;

    @FXML
    private Label headerLabelId;

    @FXML
    private Label helloMessageLabelID;

    @FXML
    private TextField serviceNameTextFieldId;

    @FXML
    private Label successTextLabelId;

    @FXML
    private Label transactionLabelId;

    private ServiceWork addedService;

    @FXML
    void OnActionAddValueButtonId(ActionEvent event) {
        CarWashServiceRepositoryImpl carWashServiceRepository = new CarWashServiceRepositoryImpl();
        Boolean aBoolean = Service.validateAllTextFields(costTextFieldId, serviceNameTextFieldId);
        if(!aBoolean){
            if(addedService != null){
                try {
                    CarWashService carWashService = new CarWashService(serviceNameTextFieldId.getText(), Double.valueOf(costTextFieldId.getText()));
                    carWashServiceRepository.updateById(addedService.getId(), carWashService);
                    Service.createAlert("Объект обновлён", "Обьект успешно обновлён!", Alert.AlertType.INFORMATION);
                    Service.closeScene(backButtonId);
                } catch (NumberFormatException e){
                    Service.createAlert("Ошибка!", "Проверьте значение поля цена!", Alert.AlertType.ERROR);
                    costTextFieldId.setStyle("-fx-border-color:red");
                }
            }
            else {
                try {
                    CarWashService carWashService = new CarWashService(serviceNameTextFieldId.getText(), Double.valueOf(costTextFieldId.getText()));
                    carWashServiceRepository.insertOne(carWashService);
                    Service.createAlert("Объект добавлен", "Обьект успешно добавлен!", Alert.AlertType.INFORMATION);
                    Service.closeScene(backButtonId);
                } catch (NumberFormatException e){
                    Service.createAlert("Ошибка!", "Проверьте значение поля цена!", Alert.AlertType.ERROR);
                    costTextFieldId.setStyle("-fx-border-color:red");
                }
            }
        }
    }

    @FXML
    void OnActionBack(ActionEvent event) {
    Service.closeScene(backButtonId);
    }

    @FXML
    void initialize() {
    }

    @Override
    public void addData(CarWashService object) {
        addedService = object;
        serviceNameTextFieldId.setText(addedService.getNameService());
        costTextFieldId.setText(String.valueOf(addedService.getCostService()));
    }
}
