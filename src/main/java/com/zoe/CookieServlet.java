package com.zoe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CookieServlet",urlPatterns = "/cookie.do")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String formatDate = sdf.format(date);

        //1.创建Cookie
        Cookie cookie = new Cookie("LastTime",formatDate);
        /**
         默认情况Cookies是会话级别
         打开浏览器，关闭浏览器为一次会话
         如果不设置持久化时间，cookie会存储在浏览器内存中，浏览器关闭cookie信息销毁
         */
        //设置cookie客户端的存储时间
       // cookie.setMaxAge(60);
        //2.响应给浏览器
        response.addCookie(cookie);
        String LastTime =null;
        Cookie[] cookies =request.getCookies();
        if(cookies!=null){
            for(Cookie c:cookies){
                if(c.getName().equals("LastTime")){
                    LastTime = c.getValue();
                }
            }
        }
        response.setContentType("text/html;charset=utf-8");
        if(LastTime!=null){
            response.getWriter().write("上次的登录时间为："+LastTime);
        }else {
            response.getWriter().write("你是第一次登录");
        }

    }
}
