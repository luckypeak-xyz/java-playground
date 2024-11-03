<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #dddddd;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .action-buttons {
            display: flex;
            gap: 8px;
        }

        .btn {
            padding: 4px 8px;
            text-decoration: none;
            color: white;
            background-color: #4CAF50;
            border-radius: 4px;
        }

        .btn-delete {
            background-color: #f44336;
        }
    </style>
</head>
<body>

<h2>User List</h2>

<a href="user?action=new" class="btn">Add New User</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>Phone Number</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- 使用 JSTL 循环显示用户列表 -->
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.role}</td>
            <td>
                <div class="action-buttons">
                    <a href="user?action=edit&id=${user.id}" class="btn">Edit</a>
                    <a href="user?action=delete&id=${user.id}" class="btn btn-delete"
                       onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>