package cn.s0228.servlet.manager;

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

@WebServlet(name = "ms", value = "/ms")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryRunner runner=new QueryRunner(C3p0Utils.getDataSource());
        StationDao stationDao =new StationDao(runner);
        String n=null;
        List<Station> stations = stationDao.queryDao();
        //        创建JSON对象
        JSONArray jsonArray =JSONArray.fromObject(stations);
        response.setContentType("text/html;charset=UTF-8");
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
