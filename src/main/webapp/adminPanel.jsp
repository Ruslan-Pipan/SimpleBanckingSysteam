
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>AdminPanel</title>
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="cotn_principal">
        <div class="cont_centrar">
            <div class="cont_login">
                <div class="cont_info_log_sign_up">
                    <div class="col_md_sign_up">
                        <div class="cont_ba_opcitiy">
                            <p>Транзакції акаунта по номуру</p>
                            <form action="/last_transaction">
                                <input type="number" name="acc">
                                <button class="btn_login">Останні транзакції</button>
                            </form>
                        </div>
                    </div>
                    <div class="col_md_login">
                        <div class="cont_ba_opcitiy">
                            <p>Отримати консумера по email;</p>
                            <form action="/consumerByID">
                                <input type="email" name="email">
                                <button class="btn_login">Consumer</button>
                            </form>
                        </div>
                    </div>

                    <div class="col_md_login">
                        <div class="cont_ba_opcitiy">
                            <p>Отримати всіх користувачів:</p>
                            <form action="/consumers">
                                <button class="btn_login">Consumer List</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="cont_info_log_sign_up">
        <div class="cont_ba_opcitiy">
            <p>${sessionScope.consumerAdmin} </p>
            <c:forEach items="${sessionScope.consumers}" var="con"><p>${con}</p></c:forEach>
            <c:forEach items="${sessionScope.listTransaction}" var="tr"><p>${tr}</p></c:forEach>
        </div>
    </div>
</body>
</html>
