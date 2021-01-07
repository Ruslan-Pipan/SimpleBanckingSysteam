<%--
  Created by IntelliJ IDEA.
  User: ruspi
  Date: 06.10.2020
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SentMoney</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400&amp;subset=cyrillic" rel="stylesheet">
    <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="cotn_principal">
        <div class="cont_centrar">
            <div class="cont_login">
                <div class="cont_info_log_sign_up">
                    <div class="col_md_sign_up">
                        <div style="padding: 40px 0px;" class="cont_ba_opcitiy">
                            <p style="color: cadetblue; margin: 0;">Данні користувача:</p>
                            <p>${firstName} ${lastName}</p>
                            <p>Номер карти: ${to_bank_acc}</p>
                            <p>Сума переказу: ${suma}</p>
                        </div>
                    </div>
                    <div class="col_md_login">
                        <div style="padding: 40px 0px;" class="cont_ba_opcitiy">
                            <form action="${pageContext.request.contextPath}/controler?action=draw_money" name="lll" method="post">
                                <input type="hidden" name="this_bank_acc" value="${this_bank_acc}">
                                <input type="hidden" name="awt" value="${suma}">
                                <button type="submit" class="btn_login">Надіслати</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="cont_back_info" style="height: 315px">
                    <div class="cont_img_back_grey">
                        <img src="img/bg.jpg" alt="" />
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
