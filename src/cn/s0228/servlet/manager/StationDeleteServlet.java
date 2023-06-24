package cn.s0228.servlet.manager;

import cn.s0228.jdbc.dao.StationDao;
import cn.s0228.jdbc.utils.C3p0Utils;
import net.sf.json.JSONArray;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet(name = "sds", value = "/sds")
public class StationDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //                设置请求编码,相应方式和编码方式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        创建对象
        QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
        StationDao stationDao = new StationDao(runner);
//      获取文本
        String Sid=request.getParameter("value");
        if(Sid!=null) {
            try {
                stationDao.deleteDao(Sid);
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
