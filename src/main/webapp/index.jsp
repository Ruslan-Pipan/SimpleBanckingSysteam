<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Форма авторизации и регистрации</title>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400&amp;subset=cyrillic" rel="stylesheet">
  <link rel='stylesheet' href='https://fonts.googleapis.com/icon?family=Material+Icons'>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

<script>var articleLink = '/scripts/forms/499-veb-forma-avtorizacii-i-registracii-na-sayte.html';</script>


<div class="cotn_principal">
  <div class="cont_centrar">
    <div class="cont_login">
      <div class="cont_info_log_sign_up">
        <div class="col_md_login">
          <div class="cont_ba_opcitiy">
            <h2>Войти</h2>
            <p>У вас уже есть логин?</p>
            <button class="btn_login" onclick="cambiar_login()">Войти</button>
          </div>
        </div>
        <div class="col_md_sign_up">
          <div class="cont_ba_opcitiy">
            <h2>Регистрация</h2>
            <p>Нет логина?</p>
            <button class="btn_sign_up" onclick="cambiar_sign_up()">Зарегистрироваться</button>
          </div>
        </div>
      </div>

      <div class="cont_back_info">
        <div class="cont_img_back_grey">
          <img src="${pageContext.request.contextPath}img/bg.jpg" alt="" />
        </div>
      </div>

      <div class="cont_forms">
        <div class="cont_img_back_">
          <img src="${pageContext.request.contextPath}img/bg.jpg" alt="" />
        </div>

        <div class="cont_form_login">
          <form action="/login" name="sign_in" method="Get">
            <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
            <h2>Войти</h2>
            <input type="text" name="email" placeholder="E-mail" />
            <input type="password" name="password" placeholder="Пароль" />
            <button class="btn_login" onclick="cambiar_login()">Войти</button>
          </form>
        </div>

        <div class="cont_form_sign_up">
          <form action="/authorization" name="sign_up" method="Get">
          <a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
          <h2>Регистрация</h2>
          <input type="email" name="email" placeholder="E-mail" />
          <input type="text" name="phoneNumber" placeholder="Телефон" />
          <input type="text" name="firstName" placeholder="Имя">
          <input type="text" name="secondName" placeholder="Фамилия">
          <input type="password" name="firstPass" placeholder="Пароль" />
          <input type="password" name="secondPass" placeholder="Повторите пароль" />
          <button class="btn_sign_up" onclick="cambiar_sign_up()">Зарегистрироваться</button>
          </form>
        </div>

      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}js/index.js"></script>
</body>
</html>
