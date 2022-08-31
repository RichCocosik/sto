package com.project.sto.controller.adminMenuController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.project.sto.controller.ControllerCTO;
import com.project.sto.dao.impl.CarWashServiceRepositoryImpl;
import com.project.sto.dao.impl.WorkDayRepositoryImpl;
import com.project.sto.dao.impl.WorkerRepositoryImpl;
import com.project.sto.domain.User;
import com.project.sto.domain.WorkDay;
import com.project.sto.domain.Worker;
import com.project.sto.domain.workService.CarWashService;
import com.project.sto.domain.workService.RepairService;
import com.project.sto.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.project.sto.service.resourcesPath.Path.ADMIN_MENU;

public class AdminWorkDayController extends ControllerCTO<Worker> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButtonId;

    @FXML
    private DatePicker datePickerID;

    @FXML
    private Label errorLabelId;

    @FXML
    private Label helloMessageLabelID;

    @FXML
    private Label successTextLabelId;

    @FXML
    private TableView<WorkDay> tableView;

    @FXML
    private Label transactionLabelId;

    @FXML
    private Button updateTableButtonId;

    @FXML
    private TableColumn<WorkDay, Integer> workDayClient;

    @FXML
    private TableColumn<WorkDay, Double> workDayCost;

    @FXML
    private TableColumn<WorkDay, LocalDate> workDayDate;

    @FXML
    private TableColumn<WorkDay, String> workDayName;

    private WorkDayRepositoryImpl workDayRepository = new WorkDayRepositoryImpl();
    private Worker signWorker;

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
    void OnActionUpdateTable(ActionEvent event) {
        tableView.getItems().clear();
        List<WorkDay> all = workDayRepository.getDaysByDate(datePickerID.getValue());
        tableView.getItems().addAll(all);
        Service.createAlert("Таблица обновлена!","", Alert.AlertType.INFORMATION);
    }

    @FXML
    void initialize() {
        datePickerID.valueProperty().setValue(LocalDate.now());
        List<WorkDay> all = workDayRepository.getDaysByDate(datePickerID.getValue());

        workDayName.setCellValueFactory(new PropertyValueFactory<WorkDay, String>("name"));
        workDayClient.setCellValueFactory(new PropertyValueFactory<WorkDay, Integer>("userId"));
        workDayDate.setCellValueFactory(new PropertyValueFactory<WorkDay, LocalDate>("date"));
        workDayCost.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("purchase"));
        tableView.getItems().addAll(all);
    }

    @Override
    public void addData(Worker object) {
        signWorker = object;
    }
}
