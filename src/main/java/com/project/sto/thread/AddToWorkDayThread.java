package com.project.sto.thread;

import com.project.sto.dao.impl.WorkDayRepositoryImpl;
import com.project.sto.domain.User;
import com.project.sto.domain.WorkDay;
import com.project.sto.domain.workService.CarWashService;
import com.project.sto.domain.workService.RepairService;
import com.project.sto.domain.workService.ServiceWork;
import javafx.scene.control.TextArea;

import java.time.LocalDate;

public class AddToWorkDayThread extends Thread{

    private String text;
    private Integer user;
    private Double purchase;
    private Integer serviceWork;

    public AddToWorkDayThread(String text, Integer user, Double purchase, Integer serviceWork) {
        this.text = text;
        this.user = user;
        this.purchase = purchase;
        this.serviceWork = serviceWork;
    }

    @Override
    public void run() {
        WorkDay workDay;
        System.out.println(user);
        workDay = new WorkDay(text, user, serviceWork, LocalDate.now(), purchase);
        WorkDayRepositoryImpl workDayRepository = new WorkDayRepositoryImpl();
        workDayRepository.insertOne(workDay);
        System.out.println(workDay);
    }
}
