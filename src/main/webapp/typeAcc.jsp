
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>TypeAcc</title>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="cotn_principal">
    <c:forEach items="${sessionScope.Accounts}" var="acc">
        <div class="cont_centrar">
            <div class="cont_login">
                <div class="col_md_sign_up">
                    <div class="cont_ba_opcitiy">
                        <p>Номер карти: ${acc.bankAccount}</p>
                        <p>Баланс: ${acc.balance}</p>
                        <c:choose>
                            <c:when test="${acc.type == 1}">
                                <p>Overdraft: ${acc.overdraftAmount}</p>
                            </c:when>
                            <c:otherwise>
                                <p>Rate: ${acc.interestRate}</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
