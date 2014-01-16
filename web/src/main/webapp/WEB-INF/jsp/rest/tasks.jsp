<?xml version="1.0" encoding="UTF-8"?>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 8/8/13
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/xml;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
<tasks>
    <task>
        <user>
            <name>proff7_examples</name>
            <group>
                <name>Proff7</name>
                <group-status>true</group-status>
                <repository-url>https://subversion.assembla.com/svn/proff7</repository-url>
            </group>
        </user>
        <task-template>
            <name>Empty class</name>
            <description>Create empty class</description>
        </task-template>
        <task-status>
            TEST
        </task-status>
    </task>
    <task>
        <user>
            <name>$proff7_examples</name>
            <group>
                <name>Proff7</name>
                <group-status>true</group-status>
                <repository-url>https://subversion.assembla.com/svn/proff7</repository-url>
            </group>
        </user>
        <task-template>
            <name>Simple bean</name>
            <description>Create simple encapsulated bean</description>
        </task-template>
        <task-status>
            NEW
        </task-status>
    </task>
</tasks>--%>

<tasks>
    <c:forEach var="task" items="${group.tasks}">
        <task>
            <user>
                <name>${task.user.name}</name>
                <group>
                    <name>${task.group.name}</name>
                    <group-status>${task.group.status}</group-status>
                    <repository-url>${task.group.repository}</repository-url>
                </group>
            </user>
            <task-template>
                <name>${task.template.name}</name>
                <description>${task.template.desk}</description>
            </task-template>
            <task-status>
                    ${task.status}
            </task-status>
        </task>
    </c:forEach>
</tasks>
