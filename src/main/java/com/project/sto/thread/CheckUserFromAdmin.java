package com.project.sto.thread;
import com.project.sto.dao.impl.UserRepositoryImpl;
import com.project.sto.domain.User;

import java.util.Optional;

public class CheckUserFromAdmin extends Thread{

    private Integer phoneNumber;
    private String fio;
    private Double purchase;

    public CheckUserFromAdmin(Integer phoneNumber, String fio, Double purchase) {
        this.phoneNumber = phoneNumber;
        this.fio = fio;
        this.purchase = purchase;
    }

    @Override
    public void run() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User oneByPhoneNumber = userRepository.getOneByPhoneNumber(phoneNumber);
        System.out.println(oneByPhoneNumber);
        if(!Optional.ofNullable(oneByPhoneNumber).isPresent()){
            User user = new User(fio, phoneNumber, 1, purchase);
            userRepository.insertOne(user);
        }
        User oneById = userRepository.getOneById(oneByPhoneNumber.getId());
        if(oneById.getFio() == null || oneById.getFio().equals("")) {
            User user = new User(fio, oneById.getPhoneNumber(), oneById.getCounter() + 1, oneById.getPurchases() + purchase);
            userRepository.updateById(oneById.getId(), user);

        } else {
            User user = new User(oneById.getFio(), oneById.getPhoneNumber(), oneById.getCounter() + 1, oneById.getPurchases() + purchase);
            userRepository.updateById(oneById.getId(), user);
        }
    }
}
