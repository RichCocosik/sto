package com.project.sto.dao.impl;

import com.project.sto.dao.ConnectDao;
import com.project.sto.dao.repository.RepairServiceRepository;
import com.project.sto.domain.workService.RepairService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.project.sto.dao.HelperDao.populateRepairService;

public class RepairServiceRepositoryImpl implements RepairServiceRepository {
    @Override
    public List<RepairService> getAll() {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from repair_service;";
        List<RepairService> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result.add(populateRepairService(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public RepairService getOneById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from repair_service where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return populateRepairService(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateById(Integer id, RepairService service) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "update repair_service set name_service = ?, cost_service = ? where id = ?;";
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
        String sqlCommand = "delete from repair_service where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOne(RepairService entity) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "insert into repair_service (name_service, cost_service) values (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, entity.getNameService());
            preparedStatement.setDouble(2, entity.getCostService());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
