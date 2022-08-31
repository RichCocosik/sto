package com.project.sto.domain.workService;

public class RepairService extends ServiceWork{

    public RepairService(Integer id, String nameService, Double costService) {
        super(id, nameService, costService);
    }

    public RepairService(String nameService, Double costService) {
        super(nameService, costService);
    }

    public RepairService() {
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
