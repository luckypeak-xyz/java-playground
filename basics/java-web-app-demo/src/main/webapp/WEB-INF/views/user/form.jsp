<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Form</title>
    <style>
        .form-container {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .btn {
            padding: 8px 12px;
            color: white;
            background-color: #4CAF50;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }

        .btn-cancel {
            background-color: #f44336;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>${user != null ? "Edit User" : "Create New User"}</h2>

    <!-- 表单提交到 UserController 中，根据用户是否存在决定 action 值 -->
    <form action="users" method="post">
        <!-- 根据是否为编辑操作，设置隐藏的 action 和 id -->
        <input type="hidden" name="action" value="${user != null ? 'update' : 'insert'}"/>
        <c:if test="${user != null}">
            <input type="hidden" name="id" value="${user.id}"/>
        </c:if>

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" value="${user != null ? user.username : ''}" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" value="${user != null ? user.password : ''}" required>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" id="email" name="email" value="${user != null ? user.email : ''}" required>
        </div>

        <div class="form-group">
            <label for="phoneNumber">Phone Number</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${user != null ? user.phoneNumber : ''}"
                   required>
        </div>

        <div class="form-group">
            <label for="role">Role</label>
            <select id="role" name="role" required>
                <option value="">Select Role</option>
                <option value="ADMIN" ${user != null && user.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
                <option value="USER" ${user != null && user.role == 'USER' ? 'selected' : ''}>User</option>
                <!-- 可以根据 UserRoleEnum 的角色类型扩展 -->
            </select>
        </div>

        <div class="form-group">
            <button type="submit" class="btn">${user != null ? "Update" : "Create"}</button>
            <a href="users?action=list" class="btn btn-cancel">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>