package cn.s0228.bean;

public class Station {
    //    车次号
    int id;
    //    起始时间
    String startTime;
    //    起始站
    String startStation;
    //    终点站
    String endStation;
    //    额定载客量
    String capacity;
    int member;

    public Station() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }
}
