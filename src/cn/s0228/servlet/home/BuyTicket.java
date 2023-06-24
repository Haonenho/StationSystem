package cn.s0228.servlet.home;

import cn.s0228.jdbc.dao.TicketDao;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BT", value = "/BT")
public class BuyTicket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                设置请求编码,相应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        创建对象
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        TicketDao ticketDao=new TicketDao(runner);
//      获取文本
        String name=request.getParameter("name");
        String startTime=request.getParameter("startTime");
        String startStation=request.getParameter("startStation");
        String endStation=request.getParameter("endStation");
        String id=request.getParameter("id");
        System.out.println(name+id+startStation+endStation+startTime);
        if(id!=null) {
            try {
                ticketDao.insertDao(name, startTime, startStation, endStation, Integer.parseInt(id));
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
