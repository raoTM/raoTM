package com.web;

import com.alibaba.fastjson.JSON;
import com.pojo.PageBean;
import com.pojo.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    UserService userService = new UserService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.selectAll();
        String toJSONString = JSON.toJSONString(users);
        resp.setContentType("text/json;charset=UTF-8");
        resp.getWriter().write(toJSONString);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        User user = JSON.parseObject(s, User.class);
        UserService.add(user);
        resp.getWriter().write("success");
    }
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        int[] ids = JSON.parseObject(s, int[].class);
        userService.deleteByIds(ids);
        resp.getWriter().write("success");
    }
    public void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _current = req.getParameter("current");
        String _pageSize = req.getParameter("pageSize");
        int current = Integer.parseInt(_current);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<User> pageBean = userService.selectPage(current, pageSize);

        String toJSONString = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=UTF-8");
        resp.getWriter().write(toJSONString);
    }
    public void selectPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _current = req.getParameter("current");
        String _pageSize = req.getParameter("pageSize");
        int current = Integer.parseInt(_current);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        User user = JSON.parseObject(s, User.class);

        PageBean<User> pageBean = userService.selectPageAndCondition(current, pageSize,user);

        String toJSONString = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=UTF-8");
        resp.getWriter().write(toJSONString);
    }
}
