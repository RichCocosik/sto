package com.project.sto.domain.workService;

public class CarWashService extends ServiceWork{

    public CarWashService(Integer id, String nameService, Double costService) {
        super(id, nameService, costService);
    }

    public CarWashService(String nameService, Double costService) {
        super(nameService, costService);
    }

    public CarWashService() {
    }

    @Override
    public String toString() {
        return "CarWashService{" +
                "id=" + getId() +
                ", nameService='" + getNameService() + '\'' +
                ", costService=" + getCostService() +
                '}';
    }
}
