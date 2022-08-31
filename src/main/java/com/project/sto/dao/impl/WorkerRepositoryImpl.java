package com.project.sto.dao.impl;

import com.project.sto.dao.ConnectDao;
import com.project.sto.dao.repository.WorkerRepository;
import com.project.sto.domain.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.project.sto.dao.HelperDao.populateWorker;

public class WorkerRepositoryImpl implements WorkerRepository {
    @Override
    public List<Worker> getAll() {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from worker;";
        List<Worker> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result.add(populateWorker(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Worker getOneById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from worker where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return populateWorker(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateById(Integer id, Worker user) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "update worker set fio = ?, phone_number = ?, password = ?, admin = ? where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, user.getFio());
            preparedStatement.setInt(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, user.getAdmin());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "delete from worker where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOne(Worker entity) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "insert into worker (fio, phone_number, password, admin) values (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, entity.getFio());
            preparedStatement.setInt(2, entity.getPhoneNumber());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setBoolean(4, entity.getAdmin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getWorkersCount() {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "SELECT count(*) FROM worker;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return Integer.parseInt(resultSet.getString("count(*)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
