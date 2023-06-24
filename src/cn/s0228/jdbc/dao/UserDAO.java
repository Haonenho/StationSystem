package cn.s0228.jdbc.dao;

import cn.s0228.bean.User;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDAO {
    QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
    String sql;
    int rowsAffected;

    public UserDAO(QueryRunner runner) {
        this.runner = runner;
    }

    public List<User> queryDao() {
        sql = "SELECT * FROM user";
        List<User> users = null;

        try {
            users = (List) runner.query(sql, new BeanListHandler(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getPassword());
        }

        return users;
    }

    public boolean insertDao(String name, String password) throws SQLException {
        sql = "INSERT INTO user (name,password) values (?,?)";
        rowsAffected = runner.update(sql, name, password);
        return rowsAffected > 0;
    }

    public boolean checkDao(String name, String password) throws SQLException {
        sql = "SELECT * FROM user WHERE name=? AND password=?";
        List<User> users = queryDao();
        for (User user : users) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                rowsAffected = 1;
            } else {
                rowsAffected = 0;
            }
        }
        return rowsAffected > 0;
    }
}

