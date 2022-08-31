package com.project.sto.dao;

import com.project.sto.domain.Worker;
import com.project.sto.domain.workService.CarWashService;
import com.project.sto.domain.User;
import com.project.sto.domain.WorkDay;
import com.project.sto.domain.workService.RepairService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperDao {

    public static CarWashService populateCarWashService(ResultSet resultSet) throws SQLException {
        CarWashService service = new CarWashService();
        service.setId(resultSet.getInt("id"));
        service.setNameService(resultSet.getString("name_service"));
        service.setCostService(resultSet.getDouble("cost_service"));
        return service;
    }

    public static User populateUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFio(resultSet.getString("fio"));
        user.setPhoneNumber(resultSet.getInt("phone_number"));
        user.setCounter(resultSet.getInt("counter"));
        user.setPurchases(resultSet.getDouble("purchases"));
        return user;
    }

    public static WorkDay populateWorkDay(ResultSet resultSet) throws SQLException {
        WorkDay workDay = new WorkDay();
        workDay.setId(resultSet.getInt("id"));
        workDay.setName(resultSet.getString("name"));
        workDay.setUserId(resultSet.getInt("user_id"));
        workDay.setServiceId(resultSet.getInt("service_id"));
        workDay.setDate(resultSet.getDate("date").toLocalDate());
        workDay.setPurchase(resultSet.getDouble("purchase"));
        return workDay;
    }
    public static RepairService populateRepairService(ResultSet resultSet) throws SQLException {
        RepairService service = new RepairService();
        service.setId(resultSet.getInt("id"));
        service.setNameService(resultSet.getString("name_service"));
        service.setCostService(resultSet.getDouble("cost_service"));
        return service;
    }

    public static Worker populateWorker(ResultSet resultSet) throws SQLException {
        Worker user = new Worker();
        user.setId(resultSet.getInt("id"));
        user.setFio(resultSet.getString("fio"));
        user.setPhoneNumber(resultSet.getInt("phone_number"));
        user.setPassword(resultSet.getString("password"));
        user.setAdmin(resultSet.getBoolean("admin"));
        return user;
    }
}
