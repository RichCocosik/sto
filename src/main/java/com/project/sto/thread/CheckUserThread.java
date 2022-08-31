package com.project.sto.thread;

import com.project.sto.dao.impl.UserRepositoryImpl;
import com.project.sto.domain.User;

import java.util.Optional;

public class CheckUserThread extends Thread{

    private Integer phoneNumber;
    private Double purchase;
    private String text;
    private Integer serviceId;

    public CheckUserThread(Integer phoneNumber, Double purchase, String text, Integer serviceId) {
        this.phoneNumber = phoneNumber;
        this.purchase = purchase;
        this.text = text;
        this.serviceId = serviceId;
    }

    @Override
    public void run() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User oneByPhoneNumber = userRepository.getOneByPhoneNumber(phoneNumber);
        System.out.println(oneByPhoneNumber);
        if(!Optional.ofNullable(oneByPhoneNumber).isPresent()){
            User user = new User("", phoneNumber, 1, purchase);
            userRepository.insertOne(user);
            User addedUser = userRepository.getOneByPhoneNumber(phoneNumber);
            AddToWorkDayThread addToWorkDayThread = new AddToWorkDayThread(text, addedUser.getId(), purchase, serviceId);
            addToWorkDayThread.start();
        }
        else {
            User oneById = userRepository.getOneById(oneByPhoneNumber.getId());
            User user = new User(oneById.getFio(), oneById.getPhoneNumber(), oneById.getCounter() + 1, oneById.getPurchases() + purchase);
            userRepository.updateById(oneById.getId(), user);
            AddToWorkDayThread addToWorkDayThread = new AddToWorkDayThread(text, user.getId(), purchase, serviceId);
            addToWorkDayThread.start();
        }
    }
}
