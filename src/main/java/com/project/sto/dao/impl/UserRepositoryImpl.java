package com.project.sto.dao.impl;

import com.project.sto.dao.ConnectDao;
import com.project.sto.dao.repository.UserRepository;
import com.project.sto.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.project.sto.dao.HelperDao.populateUser;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getAll() {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from user;";
        List<User> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                result.add(populateUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User getOneById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from user where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return populateUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateById(Integer id, User user) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "update user set fio = ?, phone_number = ?, counter = ?, purchases = ? where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, user.getFio());
            preparedStatement.setInt(2, user.getPhoneNumber());
            preparedStatement.setInt(3, user.getCounter());
            preparedStatement.setDouble(4, user.getPurchases());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "delete from user where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertOne(User entity) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "insert into user (fio, phone_number, counter, purchases) values (?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, entity.getFio());
            preparedStatement.setInt(2, entity.getPhoneNumber());
            preparedStatement.setInt(3, entity.getCounter());
            preparedStatement.setDouble(4, entity.getPurchases());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getOneByPhoneNumber(Integer phoneNumber) {
        Connection connection = new ConnectDao().getConnection();
        String sqlCommand = "select * from user where phone_number = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return populateUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
