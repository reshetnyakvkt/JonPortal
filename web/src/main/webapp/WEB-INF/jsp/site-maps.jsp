<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 26.02.16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-UA">
<head>
    <meta charset="UTF-8">
    <title>Карта сайта | Курсы программирования Java.</title>
    <meta name=description itemprop=description content="Карта сайта"/>
    <meta name=keywords itemprop=keywords content="Карта сайта, site-maps, site maps, sitemaps"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/landing/img/icon/favicon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon" href="/landing/img/icon/apple-touch-icon.png" />
    <link rel="apple-touch-icon" sizes="57x57" href="/landing/img/icon/landing/img/icon/apple-touch-icon-57x57.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="/landing/img/icon/apple-touch-icon-72x72.png" />
    <link rel="apple-touch-icon" sizes="76x76" href="/landing/img/icon/apple-touch-icon-76x76.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="/landing/img/icon/apple-touch-icon-114x114.png" />
    <link rel="apple-touch-icon" sizes="120x120" href="/landing/img/icon/apple-touch-icon-120x120.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="/landing/img/icon/apple-touch-icon-144x144.png" />
    <link rel="apple-touch-icon" sizes="152x152" href="/landing/img/icon/apple-touch-icon-152x152.png" />
    <link rel="apple-touch-icon" sizes="180x180" href="/landing/img/icon/apple-touch-icon-180x180.png" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto:100,300,300italic,400&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/landing/css/index.css">
    <script src="/landing/js/jquery-1.11.1.min.js"></script>
    <!--Slick carousel-->
    <link rel="stylesheet" type="text/css" href="/landing/js/slick/slick.css"/>
    <script type="text/javascript" src="/landing/js/slick/slick.min.js"></script>
    <!-- Magnific Popup core CSS file -->
    <link rel="stylesheet" href="/landing/js/magnificpopup/magnific-popup.css">
    <!-- Magnific Popup core JS file -->
    <script src="/landing/js/magnificpopup/jquery.magnific-popup.min.js"></script>
    <!--google map-->
    <script src="https://maps.googleapis.com/maps/api/js"></script>
    <!--google map-->
    <link href="https://file.myfontastic.com/n6vo44Re5QaWo8oCKShBs7/icons.css" rel="stylesheet">
    <script src="/landing/js/isInViewport.min.js"></script>
    <!--selectbox-->
    <script src="/landing/js/select2-4.0.1/dist/js/select2.full.min.js"></script>
    <link rel="stylesheet" href="/landing/js/select2-4.0.1/dist/css/select2.min.css">
    <!--animate-->
    <script src="/landing/js/jquery-viewport.checker.js"></script>
    <script src="/landing/js/animate/animate-css.js"></script>
    <link rel="stylesheet" href="/landing/js/animate/animate.css">
    <!--Validation-->
    <script rel="nofollow" src="https://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
    <script rel="nofollow" src="https://cdn.jsdelivr.net/jquery.validation/1.13.1/additional-methods.min.js"></script>
    <script src="/landing/js/index.js"></script>
</head>
<body>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-74659325-1', 'auto');
    ga('send', 'pageview');
</script>
<a href=".header">
    <img src="/landing/img/up-arrow.png" alt="jon.com.ua" class="up-arrow">
</a>
<div class="header"></div>
<div class="show-adaptive-menu">
    <div class="open-menu-button">
        <div class="hamburger-icon"></div>
    </div>
</div>
<!--Starts menu-->
<div class="menu">
    <div class="container">
        <div class="col-xs-12">
            <ul>
                <li class="active">
                    <a href="http://jon.com.ua/#why-we">преимущества</a>
                </li>
                <li>
                    <a href="http://jon.com.ua/#about-courses">курсы</a>
                </li>
                <li>
                    <a href="http://jon.com.ua/#schedule">расписание</a>
                </li>
                <li>
                    <a href="http://jon.com.ua/#certificate">сертификат</a>
                </li>
                <!--<li>
                    <a href="http://jon.com.ua/#process">школа</a>
                </li>-->
                <li>
                    <a href="http://jon.com.ua/#comments">отзывы</a>
                </li>
                <li>
                    <a href="http://jon.com.ua/#support">поддержка</a>
                </li>
                <li>
                    <a href="http://jon.com.ua/#contacts">контакты</a>
                </li>
                <li>
                    <a rel="nofollow" href="http://jon.com.ua/login.html">Вход</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--Ends menu-->
<!--Starts certificate-->
<div class="certificate section">
    <div class="container">
        <img src="/landing/img/serteficat/bb8.png" alt="bb8 png" class="overlay">
        <div class="section-title"><br />
            Карта сайта
        </div>
        <p style="font-size:1.66em;color:#FFE411;">Страницы</p>
        <div class="table">
            <div class="copyright cell col-xs-12 col-sm-6" style="text-decoration: none;outline: none;">
                <p>
                    <a href="http://jon.com.ua/" title="Курсы программирования Java">Главная</a><br/>
                    <a href="http://jon.com.ua/politika.html" title="Политика конфиденциальности">Политика конфиденциальности</a><br/>
                    <a href="http://jon.com.ua/site-maps.html" title="site maps">Карта сайта</a><br/>
                    <a href="http://jon.com.ua/login.html" title="Портал Java">Вход в портал Java</a><br/>
                    <a href="http://jon.com.ua/trainings/index.html" title="Соглашения о правилах кодирования в Java">Соглашения о правилах кодирования в Java</a><br/>
                    <a href="http://jon.com.ua/lessons.html" title="Полезная информация">Полезная информация</a><br/>
                    <a href="http://jon.com.ua/tasksg.html" title="Выполнить тренировочные задания">Выполнить тренировочные задания</a><br/>
                    <a href="http://jon.com.ua/download.html" title="Материалы для скачивания">Материалы для скачивания</a><br/>
                    <a href="http://jon.com.ua/solutions.html" title="Примеры решение задач">Примеры решение задач</a><br/>
                    <a href="http://jon.com.ua/demo" title="Демонстрационный пример">Демонстрационный пример</a><br/>
                    <a href="http://jon.com.ua/trainings/styleChecker.html" title="Проверка правил кодирования">Проверка правил кодирования</a><br/>
                    <a href="http://jon.com.ua/trainings/style.html" title="Соглашения о правилах кодирования в Java">Соглашения о правилах кодирования в Java</a><br/>
                    <a href="http://jon.com.ua/trainings/folc.html" title="Творчество студентов">Творчество студентов</a><br/>
                    <a href="http://jon.com.ua/trainings/slang.html" title="Элементы айтишного сленга">Элементы айтишного сленга</a><br/>
                    <a href="http://jon.com.ua/trainings/nedocode.html" title="Как писать вредоносный код">Как писать вредоносный код</a><br/>
                    <a href="http://jon.com.ua/trainings/jon.html" title="Проверка заданий">Проверка заданий</a><br/>
                    <a href="http://jon.com.ua/trainings/practicum.html" title="Практикум для желающих">Практикум для желающих</a><br/>
                    <a href="http://jon.com.ua/trainings/registration.html" title="Инструкция по регистрации">Инструкция по регистрации</a><br/>
                    <a href="http://jon.com.ua/trainings/newcomer.html" title="Для новичков">Для новичков</a><br/>
                    <a href="http://jon.com.ua/trainings/assistant.html" title="Инструкция для помощников">Инструкция для помощников</a><br/>
                    <a href="http://jon.com.ua/trainings/reviews.html" title="Отзывы">Отзывы</a><br/>
                    <a href="http://jon.com.ua/#contacts" title="Контакты">Контакты</a><br/>
                </p>
            </div>
        </div>
    </div>
</div>
<!--Ends certificate-->
<!--Starts contacts-->
<div id="contacts"></div>
<div class="contacts section">
    <div class="container">
        <div class="section-title">
            контакты
        </div>
        <p>
            Остались вопросы или уже готов учиться? <br/>
            Свяжись с нами с помощью формы или контактных данных.
        </p>

        <div class="enroll-form col-xs-12 col-sm-6">
            <form name="footer-form" action="" class="form-wrapper">
                <input name="name" class="name" type="text" placeholder="Ваше имя">
                <input name="phone" class="phone" type="text" placeholder="Телефон">
                <input name="" class="email" type="text" placeholder="E-mail">
                <textarea name="message" id="" cols="30" rows="5" placeholder="Сообщение"></textarea>
                <div class="submitForm enroll-button">отправить</div>
            </form>
        </div>
        <div class="info col-xs-12 col-sm-6">
            <img src="/landing/img/circles/circle.png" alt="circle java" class="overlay">
            <ul class="phones">
                <li>
                    <a href="tel:+380681295302">
                        <span class="yellow">068</span> 129 53 02
                    </a>
                </li>
            </ul>
            <a class="email" href="mailto:jonkursyjava@gmail.com">jonkursyjava@gmail.com</a>
            <span class="email" style="padding-top:0">Украина г. Киев 04112<br/>ул. Сикорского 1 </span>
            <ul class="social">
                <li>
                    <a rel="nofollow" href="https://plus.google.com/share?url=http://jon.com.ua" target="_blank"><i class="socicon socicon-googleplus"></i></a>
                </li>
                <li>
                    <a rel="nofollow" href="https://www.facebook.com/sharer.php?u=http://jon.com.ua" target="_blank"><i class="socicon socicon-facebook"></i></a>
                </li>
                <li>
                    <a rel="nofollow" href="http://vk.com/share.php?url=http://jon.com.ua" target="_blank"><i class="socicon socicon-vkontakte"></i></a>
                </li>
                <!--<li>
                    <a rel="nofollow" href="http://digitalsiteagency.com.ua/" target="_blank"><i class="socicon socicon-instagram"></i></a>
                </li>
                <li>
                    <a rel="nofollow" href="http://digitalsiteagency.com.ua/" target="_blank"><i class="socicon socicon-youtube"></i></a>
                </li>-->
            </ul>
        </div>
    </div>
</div>
<!--Ends contacts-->
<!--Starts map-->
<div class="google-map">
    <div id="map"></div>
</div>
<!--Ends map-->
<!--Starts copyright-->
<div class="copyright">
    <div class="container">
        <div class="left col-xs-6 col-sm-3">
            2016 &copy; Copyrights JediOnline
        </div>
        <div class="center col-xs-6 col-sm-6" style="text-align:center;">
            <a href="http://jon.com.ua/site-maps.html" title="site-maps"><i class="fa fa-sitemap"> </i></a> &nbsp <a href="http://jon.com.ua/politika.html" title="Политика конфиденциальности">Политика конфиденциальности</a>
        </div>
        <div class="right col-xs-6 col-sm-3">
            <a target="_blank" href="http://digitalsiteagency.com.ua/" title="Заказать landing page">Заказать landing page</a>
        </div>
    </div>
</div>
<!--Ends copyright-->
<!-- Yandex.Metrika counter -->
<script type="text/javascript">
    (function (d, w, c) {
        (w[c] = w[c] || []).push(function() {
            try {
                w.yaCounter35822985 = new Ya.Metrika({
                    id:35822985,
                    clickmap:true,
                    trackLinks:true,
                    accurateTrackBounce:true,
                    webvisor:true
                });
            } catch(e) { }
        });
        var n = d.getElementsByTagName("script")[0],
                s = d.createElement("script"),
                f = function () { n.parentNode.insertBefore(s, n); };
        s.type = "text/javascript";
        s.async = true;
        s.src = "https://mc.yandex.ru/metrika/watch.js";
        if (w.opera == "[object Opera]") {
            d.addEventListener("DOMContentLoaded", f, false);
        } else { f(); }
    })(document, window, "yandex_metrika_callbacks");
</script>
<noscript><div><img src="https://mc.yandex.ru/watch/35822985" style="position:absolute; left:-9999px;" alt="yandex" /></div></noscript>
<!-- /Yandex.Metrika counter -->
</body>
</html>