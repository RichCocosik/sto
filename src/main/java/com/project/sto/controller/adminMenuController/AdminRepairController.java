package com.project.sto.controller.adminMenuController;

import com.project.sto.controller.ControllerCTO;
import com.project.sto.dao.impl.RepairServiceRepositoryImpl;
import com.project.sto.dao.impl.UserRepositoryImpl;
import com.project.sto.dao.impl.WorkDayRepositoryImpl;
import com.project.sto.domain.User;
import com.project.sto.domain.WorkDay;
import com.project.sto.domain.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import com.project.sto.domain.workService.RepairService;
import com.project.sto.service.Service;
import com.project.sto.thread.AddFioThread;
import com.project.sto.thread.AddToWorkDayThread;
import com.project.sto.thread.CheckUserFromAdmin;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import static com.project.sto.service.resourcesPath.Path.ADMIN_MENU;

public class AdminRepairController extends ControllerCTO<Worker> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFromComboButtonID;

    @FXML
    private Button backButtonId;

    @FXML
    private ComboBox<RepairService> comboBoxId;

    @FXML
    private Label costLabelId;

    @FXML
    private Label errorLabelId;

    @FXML
    private TextField fioTextFieldId;

    @FXML
    private Label helloMessageLabelID;

    @FXML
    private Button payButtonID;

    @FXML
    private TextField phoneNumberTextFieldId;

    @FXML
    private Button removeFromComboBoxButtonID;

    @FXML
    private Label successTextLabelId;

    @FXML
    private TextArea textField;

    @FXML
    private Label transactionLabelId;

    private Worker signWorker;
    private List<String> nameService = new ArrayList<>();
    private List<String> valueTextField = new ArrayList<>();
    private List<Double> costService = new ArrayList<>();

    @FXML
    void OnActionRemoveFromComboBox(ActionEvent event) {
        try {
            if (costService.size() > 0){
                costService.remove(costService.size() - 1);
            } else {
               costService.remove(0);
            }
            if (nameService.size() > 0) {
                nameService.remove(nameService.size() - 1);
            } else {
                nameService.remove(0);
            }
            if (valueTextField.size() > 0) {
                valueTextField.remove(valueTextField.size() - 1);
            } else {
                valueTextField.remove(0);
            }
            Double result = 0d;
            for(Double value : costService){
                result += value;
            }
            costLabelId.setText("СТОИМОСТЬ: " + result);
            for (String value : valueTextField) {
                for (String test : nameService) {
                    textField.setText(value + test + "\n");
                }
            }
            if (nameService.isEmpty() && valueTextField.isEmpty() && costService.isEmpty()) {
                textField.setText("");
                costLabelId.setText("СТОИМОСТЬ: ");
            }
        }catch (IndexOutOfBoundsException e){
            Service.createAlert("Ошибка!", "Для отмены неоходимо добавить хотя бы один элемент", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void OnActionAddFromComboBox(ActionEvent event) {
        if (textField.getText() != null) {
            valueTextField.add(textField.getText());
        }
        textField.setText("");
        costService.add(comboBoxId.getValue().getCostService());
        nameService.add(comboBoxId.getValue().getNameService());
        for(String value : valueTextField){
            for(String test : nameService){
                textField.setText(value + test + "\n");
            }
        }
        Double result = 0d;
        for(Double value : costService){
            result += value;
        }
        costLabelId.setText("СТОИМОСТЬ: " + result);
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
    void OnActionPay(ActionEvent event) {
        Boolean aBoolean = Service.adminRepairValidateFields(costService, phoneNumberTextFieldId, fioTextFieldId);
        System.out.println(aBoolean);
        if(aBoolean){
            CheckUserFromAdmin checkUserFromAdmin = new CheckUserFromAdmin(Integer.parseInt(phoneNumberTextFieldId.getText()), fioTextFieldId.getText(), calculateCost());
            checkUserFromAdmin.start();
            Service.createAlert("Оплата прошла", "Оплата прошла успешно!", Alert.AlertType.INFORMATION);
            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            User user = userRepository.getOneByPhoneNumber(Integer.parseInt(phoneNumberTextFieldId.getText()));
            System.out.println(user);
            AddToWorkDayThread addToWorkDayThread = new AddToWorkDayThread(textField.getText(), user.getId(), calculateCost(), comboBoxId.getValue().getId());
            addToWorkDayThread.start();
            clearAllFieldsAndCollections();
            System.out.println("Пользователь добавлен");
        }
    }

    @FXML
    void OnKeyTyped(KeyEvent event) {
        Service.validatePhoneNumber(phoneNumberTextFieldId, 9);
        if(phoneNumberTextFieldId.getText().length() == 9){
            AddFioThread addFioThread = new AddFioThread(Integer.parseInt(phoneNumberTextFieldId.getText()), fioTextFieldId);
            addFioThread.start();
        }
    }

    @FXML
    void initialize() {
        phoneNumberTextFieldId.setFocusTraversable(false);
        fioTextFieldId.setFocusTraversable(false);
        setupComboBox();
    }

    private void setupComboBox(){

        RepairServiceRepositoryImpl repairServiceRepository = new RepairServiceRepositoryImpl();
        List<RepairService> allService = repairServiceRepository.getAll();
        System.out.println(allService);

        comboBoxId.setItems(FXCollections.observableArrayList(allService));
        comboBoxId.setCellFactory(param -> new ListCell<RepairService>() {
            @Override
            protected void updateItem(RepairService item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.getNameService() + " ");
                } else {
                    // handle null object
                }
            }
        });
        comboBoxId.valueProperty().addListener((obs, oldVal, newVal) ->{
            comboBoxId.setButtonCell(comboBoxId.getCellFactory().call(null));
        });

    }


    @Override
    public void addData(Worker object) {
        signWorker = object;
    }

    private Double calculateCost(){
        Double result = 0d;
        for(Double value : costService){
            result += value;
        }
        return result;
    }

    private void clearAllFieldsAndCollections(){
        phoneNumberTextFieldId.setText("");
        fioTextFieldId.setText("");
        clearCollection(costService);
        clearCollection(valueTextField);
        clearCollection(nameService);
        textField.setText("");
        costLabelId.setText("СТОИМОСТЬ: ");
    }

    private void clearCollection(List list){
        list.removeAll(list);
        }
    }

