package cn.s0228.jdbc.dao;

import cn.s0228.bean.Ticket;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TicketDao {
    String sql;
    int rowsAffected;
    QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());

    public TicketDao(QueryRunner runner) {
        this.runner = runner;
    }

    public void insertDao(String name, String time, String startStation, String endStation, int id,String buyName) throws SQLException {
        sql = "INSERT INTO ticket (name,time,startStation,endStation,id,buyName) values (?,?,?,?,?,?)";
        rowsAffected = runner.update(sql, name, time, startStation, endStation,id,buyName);
    }

    public void deleteDao(String name) throws SQLException {
        sql = "DELETE FROM ticket WHERE name = ?";
        rowsAffected = runner.update(sql,name);

    }


    public List<Ticket> queryDao() {
        sql = "SELECT * FROM ticket";
        List<Ticket> tickets = null;
        try {
            tickets = (List) runner.query(sql, new BeanListHandler(Ticket.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Ticket ticket : tickets) {
            System.out.println(ticket.getStartStation());
        }
        return tickets;

    }
}
