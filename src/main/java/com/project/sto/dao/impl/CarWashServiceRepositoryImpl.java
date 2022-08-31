package com.project.sto.dao.impl;

import com.project.sto.dao.ConnectDao;
import com.project.sto.dao.repository.CarWashServiceRepository;
import com.project.sto.domain.workService.CarWashService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.project.sto.dao.HelperDao.*;

public class CarWashServiceRepositoryImpl implements CarWashServiceRepository {
    @Override
    public List<CarWashService> getAll() {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from car_wash_service;";
        List<CarWashService> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result.add(populateCarWashService(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public CarWashService getOneById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from car_wash_service where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return populateCarWashService(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateById(Integer id, CarWashService service) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "update car_wash_service set name_service = ?, cost_service = ? where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, service.getNameService());
            preparedStatement.setDouble(2, service.getCostService());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "delete from car_wash_service where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOne(CarWashService entity) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "insert into car_wash_service (name_service, cost_service) values (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, entity.getNameService());
            preparedStatement.setDouble(2, entity.getCostService());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
