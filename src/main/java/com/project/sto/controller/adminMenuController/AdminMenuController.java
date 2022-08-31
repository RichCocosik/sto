package com.project.sto.controller.adminMenuController;

import com.project.sto.controller.ControllerCTO;
import com.project.sto.domain.Worker;
import com.project.sto.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.project.sto.service.resourcesPath.Path.*;

public class AdminMenuController extends ControllerCTO<Worker> {

    @FXML
    private Button backButtonId;

    @FXML
    private Button carWashServiceEditId;

    @FXML
    private Label helloMessageLabelID;

    @FXML
    private Button repairServiceEditId;

    @FXML
    private Button repairServiceId;

    @FXML
    private Button watchDayId;

    private Worker signWorker;


    @FXML
    void OnActionBack(ActionEvent event) {
        try {
            Service.onTheNextSceneNotModalAndClose(MAIN_MENU, "Main menu", 430, 340, watchDayId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionCarWashServiceEditId(ActionEvent event) {
        try {
            Service.onNextSceneWithObjectTest(ADMIN_CAR_WASH_EDIT,"Car wash service edit",
                    new AdminCarWashServiceEditController(), signWorker, 430, 340, backButtonId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionPayRepairServiceEditId(ActionEvent event) {
        try {
            Service.onNextSceneWithObjectTest(ADMIN_REPAIR_SERVICE_EDIT,"Admin repair service edit",
                    new AdminRepairServiceEditController(), signWorker, 430, 340, backButtonId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnActionWatchDay(ActionEvent event) {
        try {
            Service.onNextSceneWithObjectTest(ADMIN_WORK_DAY,"Work day",
                    new AdminWorkDayController(), signWorker, 430, 340, backButtonId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnRepairServiceId(ActionEvent event) {
        try {
            Service.onNextSceneWithObjectTest(ADMIN_REPAIR,"Repair",
                    new AdminRepairController(), signWorker, 430, 340, backButtonId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addData(Worker object) {
        signWorker = object;
        if(signWorker.getAdmin()){
            repairServiceEditId.setVisible(true);
            carWashServiceEditId.setVisible(true);
        }
    }
}
