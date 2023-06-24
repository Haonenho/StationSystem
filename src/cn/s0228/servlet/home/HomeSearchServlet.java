package cn.s0228.servlet.home;

import cn.s0228.bean.Station;
import cn.s0228.jdbc.dao.StationDao;
import cn.s0228.jdbc.utils.C3p0Utils;
import net.sf.json.JSONArray;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HSS", value = "/HSS")
public class HomeSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                设置请求编码,相应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

//        创建QueryRunner对象
        int id= Integer.parseInt(request.getParameter("id"));
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        StationDao stationDao =new StationDao(runner);
        Station station=new Station();
        station=stationDao.searchDao(id);
//        创建JSON对象
        JSONArray jsonArray =JSONArray.fromObject(station);
        response.setContentType("text/html;charset=UTF-8");
        System.out.println(jsonArray);
        PrintWriter out=response.getWriter();
        out.print(jsonArray);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
