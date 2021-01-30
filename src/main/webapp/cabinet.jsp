<%--
  Created by IntelliJ IDEA.
  User: ruspi
  Date: 29.09.2020
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Cabinet</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400&amp;subset=cyrillic" rel="stylesheet">
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="cotn_principal">
        <h1 style="color: white; padding-top: 20px;">${sessionScope.consumer.firstName} ${sessionScope.consumer.lastName}</h1>
        <c:forEach items="${sessionScope.consumer.accounts}" var="element">
        <div class="cont_centrar">
            <div class="cont_login">
                <div class="cont_info_log_sign_up">
                        <div class="col_md_sign_up">
                            <div class="cont_ba_opcitiy">
                                <p>Номер карти: ${element.bankAccount}</p>
                                <p>Баланс: ${element.balance}</p>
                            </div>
                        </div>
                        <div class="col_md_login">
                            <div class="cont_ba_opcitiy">
                                <form action="/check_date" name="check_date" method="post">
                                    <input type="text" name="to_bank_acc" required>
                                    <input type="number" name="suma" required>
                                    <input type="hidden" name="balance" value="${element.balance}">
                                    <input type="hidden" name="this_bank_acc" value="${element.bankAccount}">
                                    <button class="btn_login">Переслати</button>
                                </form>
                            </div>
                        </div>
                </div>
                    <div class="cont_back_info" style="height: 220px">
                        <div class="cont_img_back_grey">
                            <img src="img/bg.jpg" alt="" />
                        </div>
                    </div>
            </div>
        </div>
        </c:forEach>
    </div>

</body>
</html>
