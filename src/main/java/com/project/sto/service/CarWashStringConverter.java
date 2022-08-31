package com.project.sto.service;

import com.project.sto.domain.workService.CarWashService;
import javafx.util.StringConverter;

public class CarWashStringConverter extends StringConverter<CarWashService> {

    @Override
    public String toString(CarWashService object) {
        return object.getNameService();
    }

    @Override
    public CarWashService fromString(String s) {
        return null;
    }
}
