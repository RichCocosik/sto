package com.project.sto;

import com.project.sto.service.Service;
import com.project.sto.service.resourcesPath.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.project.sto.service.resourcesPath.Path.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(MAIN_MENU));
        Scene scene = new Scene(fxmlLoader.load(), 430, 340);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            Service.writeFile("jdbc:mysql://localhost/cto", "root", "root");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        launch();
    }
}