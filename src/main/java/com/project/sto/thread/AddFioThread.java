package com.project.sto.thread;

import com.project.sto.dao.impl.UserRepositoryImpl;
import com.project.sto.domain.User;
import javafx.scene.control.TextField;

public class AddFioThread extends Thread{

    private Integer phoneNumber;
    private TextField fioField;

    public AddFioThread(Integer phoneNumber, TextField fioField) {
        this.phoneNumber = phoneNumber;
        this.fioField = fioField;
        setDaemon(true);
    }

    @Override
    public void run() {
        System.out.println("Введённый номер телефона: " + phoneNumber);
        if(phoneNumber.toString().length() < 9){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            User oneByPhoneNumber = userRepository.getOneByPhoneNumber(phoneNumber);
            if(oneByPhoneNumber.getFio().equals("")){
                System.out.println("Поле ФИО пустое");
            }
            else {
                fioField.setText(oneByPhoneNumber.getFio());
            }
        }
    }
}
