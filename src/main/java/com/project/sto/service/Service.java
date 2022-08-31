package com.project.sto.service;

import com.project.sto.HelloApplication;
import com.project.sto.controller.adminMenuController.AdminMenuController;
import com.project.sto.controller.ControllerCTO;
import com.project.sto.domain.Worker;
import com.project.sto.domain.workService.CarWashService;
import com.project.sto.domain.workService.ServiceWork;
import com.project.sto.service.resourcesPath.Path;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class Service {

    public static void writeFile(String url, String username, String password) throws FileNotFoundException {
        File file = new File(Path.RESOURCE_DIR);
        file.mkdirs();
        try(FileWriter fileWriter = new FileWriter(Path.DATABASE_PROPERTY_PATH)) {
            fileWriter.write("url = " + url + "\n");
            fileWriter.write("username = " + username + "\n");
            fileWriter.write("password = " + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static <T extends Button> void onTheNextSceneNotModalAndClose(String resource, String title, Integer v, Integer v1, T button) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), v, v1);
        Stage stage2 = new Stage();
        Stage currentStage = Service.getCurrentStage(button);
        currentStage.close();
        stage2.setTitle(title);
        stage2.setScene(scene);
        stage2.show();

    }

    public static <T extends Button> void onTheNextSceneModal(String resource, String title, Integer v, Integer v1, T button) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), v, v1);
        Stage stage2 = new Stage();
        stage2.setTitle(title);
        stage2.initOwner(Service.getCurrentStage(button));
        stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.setScene(scene);
        stage2.showAndWait();
    }

    public static <T extends Labeled> Stage getCurrentStage(T field) {
        return (Stage) field.getScene().getWindow();
    }

    public static <T extends TextField> void validatePhoneNumber(T tf, Integer maxLength) {
        if (tf.getText().length() > maxLength) {
            String s = tf.getText().substring(0, maxLength);
            tf.setText(s);
            tf.end();
        }
        if (!tf.getText().matches("\\d*")) {
            tf.setText(tf.getText().replaceAll("[^\\d]", ""));
        }
    }

    public static <T extends Labeled> void onNextSceneWithObject(String resource, String title, Worker worker, Integer v, Integer v1, T field) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(resource)); //создаем загрузчик
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        AdminMenuController adminMenuController = loader.getController(); //получаем контроллер
        adminMenuController.addData(worker); //передаем данные
        stage.show();
        Stage st = (Stage) field.getScene().getWindow();
        st.close();
    }

    public static <T extends Labeled, S extends ControllerCTO> void onNextSceneWithObjectTest(String resource, String title, S control, Worker object, Integer v, Integer v1, T field) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(resource)); //создаем загрузчик
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        control = loader.getController(); //получаем контроллер
        control.addData(object);//передаем данные
        stage.show();
        Stage st = (Stage) field.getScene().getWindow();
        st.close();
    }

    public static <T extends Labeled> void OnTheAddServiceModal(String resource, String title, Integer v, Integer v1, T field) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), v, v1);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initOwner(Service.getCurrentStage(field));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static <T extends Labeled, C extends ControllerCTO, W extends ServiceWork> void OnTheAddServiceModalEdit(String resource, String title, C control, W serviceWork, Integer v, Integer v1, T field) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), v, v1);
        stage.setTitle(title);
        C controller = (C) fxmlLoader.getController();//получаем контроллер
        controller.addData(serviceWork);
        stage.initOwner(Service.getCurrentStage(field));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }



    public static void createAlert(String header, String contentText, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static Boolean validateFields(ComboBox<CarWashService> comboBox, TextField textField, Label label) {
        comboBox.setStyle(null);
        textField.setStyle(null);
        label.setVisible(false);
        boolean present = Optional.ofNullable(comboBox.getValue()).isEmpty();
        if (present) {
            comboBox.setStyle("-fx-border-color:red");
            label.setVisible(true);
        }
        boolean nullTextField = textField.getText().equals("");
        if (nullTextField) {
            textField.setStyle("-fx-border-color:red");
            label.setVisible(true);
        }
        System.out.println("combobox = " + present + " textF " + nullTextField);
        return !present && !nullTextField;
    }

    public static Boolean adminRepairValidateFields(List list, TextField...textFields){
        Boolean aBoolean = Service.validateAllTextFields(textFields);
        System.out.println(list.isEmpty() + " список пустой " + "Незаполненные поля " + aBoolean);
        if(!list.isEmpty() && !aBoolean){
            return true;
        } else {
            return false;
        }
    }

    public static <T extends Control> void setVisibleAllFields(Boolean visible, T... fields) {
        Stream.of(fields).forEach(field -> field.setVisible(visible));
    }

    public static <T extends Labeled> void closeScene(T button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public static<T extends TextField> void clearAllTextFieldStyle(T...fields){
        Stream.of(fields).forEach(field -> field.setStyle(null));
    }

    public static <T extends TextField> Boolean validateAllTextFields(T...fields) {
        clearAllTextFieldStyle(fields);
        Stream.of(fields).filter(filter -> filter.getText().equals("")).forEach(filtered -> filtered.setStyle("-fx-border-color:red"));
        return Stream.of(fields).anyMatch(field -> field.getText().equals(""));
    }
}

