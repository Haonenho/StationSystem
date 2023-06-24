package cn.s0228.servlet.manager;

import cn.s0228.bean.Station;
import cn.s0228.jdbc.dao.StationDao;
import cn.s0228.jdbc.utils.C3p0Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "JOS", urlPatterns = "/S-JOS")
public class StationJSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//                设置请求编码,相应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

//        创建QueryRunner对象
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        StationDao stationDao = new StationDao(runner);
        List<Station> stations = stationDao.queryDao();
//        创建JSON对象
        JSONArray jsonArray =JSONArray.fromObject(stations);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonArray);
        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
