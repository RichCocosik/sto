package com.project.sto.dao.impl;

import com.project.sto.dao.ConnectDao;
import com.project.sto.dao.repository.WorkDayRepository;
import com.project.sto.domain.WorkDay;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.project.sto.dao.HelperDao.populateWorkDay;

//TODO Проверить рабочий день и работу всех методов

public class WorkDayRepositoryImpl implements WorkDayRepository {
    @Override
    public List<WorkDay> getAll() {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from work_day;";
        List<WorkDay> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result.add(populateWorkDay(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public WorkDay getOneById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from work_day where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return populateWorkDay(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateById(Integer id, WorkDay entity) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "update work_day set name = ?, user_id = ?, date = ?, purchase = ?, service_id = ?  where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setDate(3, Date.valueOf(entity.getDate()));
            preparedStatement.setDouble(4, entity.getPurchase());
            preparedStatement.setInt(5, entity.getServiceId());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "delete from work_day where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOne(WorkDay entity) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "insert into work_day (name, user_id, date, purchase, service_id) values (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setDate(3, Date.valueOf(entity.getDate()));
            preparedStatement.setDouble(4, entity.getPurchase());
            preparedStatement.setInt(5, entity.getServiceId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<WorkDay> getDaysByDate(LocalDate date) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from work_day where date = '" + Date.valueOf(date) + "'" + ";";
        List<WorkDay> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result.add(populateWorkDay(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
