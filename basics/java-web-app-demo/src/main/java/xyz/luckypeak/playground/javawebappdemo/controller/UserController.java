package xyz.luckypeak.playground.javawebappdemo.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import xyz.luckypeak.playground.javawebappdemo.model.UserDo;
import xyz.luckypeak.playground.javawebappdemo.model.UserRoleEnum;
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
        showEditForm(req, resp);
        break;
      case "delete":
        deleteUser(req, resp);
        break;
      case "new":
        showNewForm(req, resp);
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
        insertUser(req, resp);
        break;
      case "update":
        updateUser(req, resp);
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
    req.getRequestDispatcher("/WEB-INF/views/user/list.jsp").forward(req, resp);
  }

  protected void showNewForm(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/user/form.jsp").forward(req, resp);
  }

  protected void showEditForm(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String idParam = req.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
      throw new IllegalArgumentException("id cannot be null or empty");
    }
    Long id = Long.valueOf(idParam);
    UserDo userDo = userService.findById(id);
    req.setAttribute("user", userDo);
    req.getRequestDispatcher("/WEB-INF/views/user/form.jsp").forward(req, resp);
  }

  protected void insertUser(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String email = req.getParameter("email");
    String phoneNumber = req.getParameter("phoneNumber");
    String roleParam = req.getParameter("role");

    if (username == null
        || username.isEmpty()
        || password == null
        || password.isEmpty()
        || email == null
        || email.isEmpty()
        || phoneNumber == null
        || phoneNumber.isEmpty()
        || roleParam == null
        || roleParam.isEmpty()) {
      throw new IllegalArgumentException("All fields must be filled");
    }

    UserRoleEnum role = UserRoleEnum.valueOf(roleParam);

    UserDo userDo = new UserDo();
    userDo.setUsername(username);
    userDo.setPassword(password);
    userDo.setEmail(email);
    userDo.setPhoneNumber(phoneNumber);
    userDo.setRole(role);

    int rowCount = userService.insert(userDo);
    resp.sendRedirect("/users?action=list");
  }

  protected void updateUser(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String idParam = req.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
      throw new IllegalArgumentException("id cannot be null or empty");
    }
    Long id = Long.valueOf(idParam);
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String email = req.getParameter("email");
    String phoneNumber = req.getParameter("phoneNumber");
    String roleParam = req.getParameter("role");

    UserDo userDo = userService.findById(id);
    userDo.setUsername(username);
    userDo.setPassword(password);
    userDo.setEmail(email);
    userDo.setPhoneNumber(phoneNumber);
    userDo.setRole(UserRoleEnum.valueOf(roleParam));

    int rowCount = userService.update(userDo);
    resp.sendRedirect("/users?action=list");
  }

  protected void deleteUser(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String idParam = req.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
      throw new IllegalArgumentException("id cannot be null or empty");
    }
    Long id = Long.valueOf(idParam);
    int rowCount = userService.deleteById(id);
    resp.sendRedirect("/users?action=list");
  }
}
