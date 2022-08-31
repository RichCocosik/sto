package com.project.sto.domain.workService;

public abstract class ServiceWork {
    private Integer id;
    private String nameService;
    private Double costService;

    public ServiceWork(Integer id, String nameService, Double costService) {
        this.id = id;
        this.nameService = nameService;
        this.costService = costService;
    }

    public ServiceWork(String nameService, Double costService) {
        this.nameService = nameService;
        this.costService = costService;
    }

    public ServiceWork() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Double getCostService() {
        return costService;
    }

    public void setCostService(Double costService) {
        this.costService = costService;
    }

    @Override
    public String toString() {
        return "ServiceWork{" +
                "id=" + id +
                ", nameService='" + nameService + '\'' +
                ", costService=" + costService +
                '}';
    }
}
