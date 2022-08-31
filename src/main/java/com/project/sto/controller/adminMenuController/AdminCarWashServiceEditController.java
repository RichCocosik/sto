package com.project.sto.controller.adminMenuController;

import com.project.sto.controller.modalWindowController.AddServiceModalController;
import com.project.sto.controller.ControllerCTO;
import com.project.sto.dao.impl.CarWashServiceRepositoryImpl;
import com.project.sto.domain.Worker;
import com.project.sto.domain.workService.CarWashService;
import com.project.sto.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.project.sto.service.resourcesPath.Path.*;

public class AdminCarWashServiceEditController extends ControllerCTO<Worker> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addValueButtonId;

    @FXML
    private Button backButtonId;

    @FXML
    private Button editValueButtonId;

    @FXML
    private Label errorLabelId;

    @FXML
    private Label helloMessageLabelID;

    @FXML
    private Button deleteValueButtonId1;

    @FXML
    private TableColumn<CarWashService, Double> serviceCost;

    @FXML
    private TableColumn<CarWashService, String> serviceName;

    @FXML
    private Label successTextLabelId;

    @FXML
    private TableView<CarWashService> tableView;

    @FXML
    private Label transactionLabelId;

    @FXML
    private Button updateTableButtonId;

    private Worker signWorker;

    @FXML
    void initialize() {
        CarWashServiceRepositoryImpl carWashServiceRepository = new CarWashServiceRepositoryImpl();
        List<CarWashService> all = carWashServiceRepository.getAll();
        serviceName.setCellValueFactory(new PropertyValueFactory<CarWashService, String>("nameService"));
        serviceCost.setCellValueFactory(new PropertyValueFactory<CarWashService, Double>("costService"));
        tableView.getItems().addAll(all);
    }

    @FXML
    void OnActionAddValueButtonId(ActionEvent event) {
        try {
            Service.OnTheAddServiceModal(ADMIN_ADD_EDIT_MODAL, "Add modal", 350, 310, backButtonId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionBack(ActionEvent event) {
        try {
            Service.onNextSceneWithObjectTest(ADMIN_MENU, "Admin menu",
                    new AdminMenuController(), signWorker, 430, 340, backButtonId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionEditValueButtonId(ActionEvent event) {
        CarWashService selectedItem = tableView.getSelectionModel().getSelectedItem();
        boolean present = Optional.ofNullable(selectedItem).isPresent();
        if(present) {
            System.out.println(present);
            System.out.println(selectedItem);
            try {
                Service.OnTheAddServiceModalEdit(ADMIN_ADD_EDIT_MODAL, "Add modal",
                        new AddServiceModalController(), selectedItem,430, 310, backButtonId);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new Exception("Ошибка");
            } catch (Exception e) {
                Service.createAlert("Объект не выбран", "", Alert.AlertType.INFORMATION);
            }
        }
    }

    @FXML
    void OnActionUpdateTable(ActionEvent event) {
        CarWashServiceRepositoryImpl carWashServiceRepository = new CarWashServiceRepositoryImpl();
        tableView.getItems().clear();
        List<CarWashService> all = carWashServiceRepository.getAll();
        tableView.getItems().addAll(all);
        Service.createAlert("Таблица обновлена!","", Alert.AlertType.INFORMATION);
    }

    @FXML
    void OnActionDeleteValue(ActionEvent event) {
        try {
            CarWashServiceRepositoryImpl carWashServiceRepository = new CarWashServiceRepositoryImpl();
            CarWashService carWashService = tableView.getSelectionModel().getSelectedItem();
            carWashServiceRepository.deleteById(carWashService.getId());
            tableView.getItems().clear();
            List<CarWashService> all = carWashServiceRepository.getAll();
            tableView.getItems().addAll(all);
        } catch (RuntimeException e){
            Service.createAlert("Объект не выбран!","Невозможно удалить пустой объект!", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void addData(Worker object) {
        signWorker = object;
    }


}
