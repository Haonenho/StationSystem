package cn.s0228.jdbc.dao;

import cn.s0228.bean.Station;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class StationDao {
    String sql;
    int rowsAffected;
    QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());

    public StationDao(QueryRunner runner) {
        this.runner = runner;
    }

    public void insertDao(int id, String startTime, String startStation, String endStation, String capacity, int member) throws SQLException {
        sql = "INSERT INTO station (id,startTime,startStation,endStation,capacity,member) values (?,?,?,?,?,?)";
        rowsAffected = runner.update(sql, id, startTime, startStation, endStation, capacity,member);
    }

    public void deleteDao(String id) throws SQLException {
        sql = "DELETE FROM station WHERE id = ?";
        rowsAffected = runner.update(sql, id);

    }

    public boolean updateDao(int id, int newId, String startTime, String startStation, String endStation, String capacity,String member) throws SQLException {
        sql = "UPDATE station SET id=?,startTime=?,startStation=?,endStation=?,capacity=?,member=? WHERE id=? ";
        rowsAffected = runner.update(sql, newId, startTime, startStation, endStation, capacity,member, id);
        return rowsAffected > 0;
    }

    public List<Station> queryDao() {
        sql = "SELECT * FROM station";
        List<Station> stations = null;
        try {
            stations = (List) runner.query(sql, new BeanListHandler(Station.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Station station : stations) {
            System.out.println(station.getStartStation() + " " + station.getCapacity());
        }
        return stations;

    }
    public Station searchDao(int id){
        sql = "SELECT * FROM station";
        List<Station> stations = null;
        try {
            stations = (List) runner.query(sql, new BeanListHandler(Station.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Station station : stations) {
            if(station.getId()==id){
                return station;
            }
        }
        return null;
    }
}
