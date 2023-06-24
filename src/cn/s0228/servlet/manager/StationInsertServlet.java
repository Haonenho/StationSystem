package cn.s0228.servlet.manager;

import cn.s0228.jdbc.dao.StationDao;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "sis", value = "/sis")
public class StationInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //                设置请求编码,相应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        创建对象
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        StationDao stationDao = new StationDao(runner);
//      获取文本
        String id=request.getParameter("id");
        String startTime=request.getParameter("startTime");
        String startStation=request.getParameter("startStation");
        String endStation=request.getParameter("endStation");
        String capacity=request.getParameter("capacity");
        String member=request.getParameter("member");
        if(id!=null) {
            try {
                stationDao.insertDao(Integer.parseInt(id), startTime, startStation, endStation, capacity, Integer.parseInt(member));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
