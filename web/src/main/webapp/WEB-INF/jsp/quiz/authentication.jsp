<%--
  Created by Reshetnyak Viktor on 05.02.2016
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link href="../../css/style.css" rel="stylesheet" type="text/css">--%>
<div>
    <table align="center" class="userForm">
        <tr>
            <td>Login:</td>
            <td><input ng-model="login"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" ng-model="password"></td>
        </tr>

        <tr>
            <td><button ng-click="checkUser()">Submit</button></td>
            <td><button ng-click="toRegisterPage()">New user</button></td>

        </tr>
        <tr style="color: red" ng-show="errorMsg">
            <td colspan="2">{{errorMsg}} </td>
        </tr>
    </table>
</div>