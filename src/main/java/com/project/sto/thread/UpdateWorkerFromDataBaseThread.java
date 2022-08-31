package com.project.sto.thread;

import com.project.sto.controller.SignInController;
import com.project.sto.dao.impl.WorkerRepositoryImpl;
import com.project.sto.domain.Worker;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.List;


public class UpdateWorkerFromDataBaseThread extends Thread{

    private List<Worker> listWorkers;
    private Button button;
    private SignInController sign;

    public UpdateWorkerFromDataBaseThread(List<Worker> listWorkers, Button button, SignInController sign) {
        this.listWorkers = listWorkers;
        this.button = button;
        this.sign = sign;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WorkerRepositoryImpl workerRepository = new WorkerRepositoryImpl();
        Stage stage = (Stage) button.getScene().getWindow();
        while (stage.isShowing()){
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Количество загруженное в контроллере:" + listWorkers.size());
            Integer workersCount = workerRepository.getWorkersCount();
            System.out.println("Количество в базе:" + workersCount);
            if(workersCount != listWorkers.size()){
                System.out.println("Необходимо обновить таблицу");
                List<Worker> newWorkerList = workerRepository.getAll();
                sign.updateWorkersList(newWorkerList);
                setListWorkers(sign.getAllWorkers());
                System.out.println("Обновление завершено.");//передаем данные
            }
        }

    }

    public List<Worker> getListWorkers() {
        return listWorkers;
    }

    public void setListWorkers(List<Worker> listWorkers) {
        this.listWorkers = listWorkers;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
