package xyz.luckypeak.playground.javawebappdemo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import xyz.luckypeak.playground.javawebappdemo.model.UserDo;
import xyz.luckypeak.playground.javawebappdemo.service.UserService;

public class UserController extends HttpServlet {

  private UserService userService;

  public UserController() {
    userService = new UserService();
  }

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    if (action == null) action = "list";
    switch (action) {
      case "list":
        listUsers(req, resp);
        break;
      case "edit":
        break;
      case "delete":
        break;
      case "new":
        break;
      default:
        listUsers(req, resp);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    if (action == null) action = "list";

    switch (action) {
      case "insert":
        break;
      case "update":
        break;
      default:
        listUsers(req, resp);
        break;
    }
  }

  protected void listUsers(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<UserDo> userDoList = userService.findAll();
    req.setAttribute("userList", userDoList);
    req.getRequestDispatcher("/views/user/list.jsp").forward(req, resp);
  }
}
