package cn.s0228.servlet.home;

import cn.s0228.jdbc.dao.StationDao;
import cn.s0228.jdbc.dao.TicketDao;
import cn.s0228.jdbc.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "TDS", value = "/TDS")
public class TicketDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                设置请求编码,相应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        创建对象
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        TicketDao ticketDao = new TicketDao(runner);
//      获取文本
        String name=request.getParameter("value");
        if(name!=null) {
            try {
                ticketDao.deleteDao(name);
                System.out.println("已删除");
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
