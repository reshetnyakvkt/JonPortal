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
    <title>Курсы по программированию на Java в Киеве. | Курсы программирования Java.</title>
    <meta name=description content="Cертифицированные курсы программированию на Java в Киеве. Курсы по программированию на Java с трудоустройством. Уроки Java для начинающих (для чайников) и для профессиональных программистов в Киеве. Обучение программированию на джава!" />
    <meta name=keywords content="java Киев, курсы java, курсы программирования Киев, курсы java Киев, java программирование, курсы java, программирование на java, java курсы киев, java курсы, курсы программирования java, курсы джава Киев, курсы программирования java Киев, курсы java Киев с трудоустройством, курсы джава, курсы по java Киев, уроки java, java уроки" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="it-rating" content="it-rat-7022f297b98d49028cb3b25fa02bda7b" />
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
<!--Starts header-->
<div class="header">
    <div class="wr-banner">
        <div class="wr-video main black-filter">
            <div class="wr-content vertical-center table">
                <div class="content cell">
                    <div class="container">
                        <img class="logo" src="/landing/img/logo_javajedi.png" alt="kursy java">
                        <div class="title col-xs-12">
                            Пробуди в себе силу!
                        </div>
                        <div class="col-xs-12">
                            <h1>
                                <span class="regular">Курсы по программированию на Java в Киеве</span> - от новичка до
                                <br/>
                                профессионала под руководством лучших мастеров программирования!
                            </h1>
                        </div>
                        <div class="buttons-wrap col-xs-12">
                            <div class="col-xs-12 col-sm-6">
                                <a href="#orderplace-popup" class="open-popup-link enroll-button">записаться на курсы</a>
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <a href="#ordercall-popup" class="open-popup-link enroll-button">заказать звонок</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <video autoplay loop ng-if="!isWidthSm">
                <source src="/landing/video/8265610.mp4" type="video/mp4"/>
                Your browser does not support the video tag. I suggest you upgrade your browser.
                <source src="/landing/video/8265610.webm" type="video/webm"/>
                Your browser does not support the video tag. I suggest you upgrade your browser.
            </video>
        </div>
    </div>
</div>
<!--Ends header-->
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
                <li class="active anchor">
                    <a  href=".why-we">преимущества</a>
                </li>
                <li class="anchor">
                    <a  href=".about-courses">курсы</a>
                </li>
                <li class="anchor">
                    <a href=".schedule">расписание</a>
                </li>
                <li class="anchor">
                    <a href=".certificate">сертификат</a>
                </li>
                <!--<li>
                    <a href=".process">школа</a>
                </li>-->
                <li class="anchor">
                    <a href=".comments">отзывы</a>
                </li>
                <li class="anchor">
                    <a href=".support">поддержка</a>
                </li>
                <li class="anchor">
                    <a href=".contacts">контакты</a>
                </li>
                <li>
                    <a rel="nofollow" href="http://jon.com.ua/login.html">Вход</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<!--Ends menu-->
<!--Starts why-we-->
<div id="why-we"></div>
<div class="why-we section">
    <div class="container">
        <div class="section-title">
            почему наши курсы лучше
        </div>
        <div class="row">
            <div class="item col-xs-12 col-sm-6 col-md-4">
                <div class="title">
                    Сайт
                </div>
                <img src="/landing/img/circles/1.png" alt="jon.com.ua курсы" class="overlay">
                <div class="description">
                    преподавателями и студентами создан сайт для обучения
                </div>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-4">
                <div class="title">
                    Преподаватель
                </div>
                <img src="/landing/img/circles/2.png" alt="круг java " class="overlay">
                <div class="description">
                    действующий разработчик в крупной IT компании на должности тимлид, авторизированный тренер в
                    тренинг центре компании LUXOFT
                </div>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-4">
                <div class="title">
                    Трудоустроенные студенты
                </div>
                <img src="/landing/img/circles/3.png" alt="курсы java" class="overlay">
                <div class="description">
                    студенты, окончившие курсы, трудоустроены в крупных IT компаниях
                </div>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-4">
                <div class="title">
                    онлайн проверка заданий
                </div>
                <img src="/landing/img/circles/4.png" alt="уроки java" class="overlay">
                <div class="description">
                    все задания проверяются онлайн и все студенты могут следить за состоянием проверки
                    через интернет
                </div>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-4">
                <div class="title">
                    получение сертификата
                </div>
                <img src="/landing/img/circles/5.png" alt="преподаватель java" class="overlay">
                <div class="description">
                    после окончания курсов Вы получаете сертификат, подтверждающий Ваш уровень владения JAVA
                </div>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-4">
                <div class="title">
                    Группы до 10 человек
                </div>
                <img src="/landing/img/circles/6.png" alt="школа java" class="overlay">
                <div class="description">
                    маленькие группы - это больше внимания для каждого стуеднта и, в результате - лучшее усвоение
                    материала
                </div>
            </div>
        </div>
    </div>
</div>
<!--Ends why-we-->
<!--Starts check-knowledge-->
<div class="check-knowledge section">
    <div class="container">
        <img src="/landing/img/proverka-znaniy/star-fighter.png" alt="jon.com.ua" class="overlay fighter1">
        <img src="/landing/img/proverka-znaniy/star-fighter.png" alt="jon.com.ua" class="overlay fighter2">
        <img src="/landing/img/proverka-znaniy/star-fighter.png" alt="jon.com.ua" class="overlay fighter3">
        <div class="section-title col-xs-12">
            проверьте свои знания, чтобы <br/>правильно подобрать курс
        </div>
        <p>
            не уверен в своих силах? Что-то знаешь, но не знаешь как применить? проверь уровень <br/>
            своих знаний и, вместе с нами, реализуй свой потенциал на полную!
        </p>
        <div class="centered">
            <a rel="nofollow" href="http://jon.com.ua/login.html" target="_blank" class="enroll-button">проверить знания</a>
        </div>
    </div>
</div>
<!--Ends check-knowledge-->
<!--Starts about-courses-->
<div id="about-courses"></div>
<div class="about-courses section">
    <div class="container">
        <div class="section-title">курсы</div>
        <div class="row">
            <div class="item col-xs-12 col-sm-4">
                <div class="wrapper">
                    <div class="head">
                        <div class="title pink table">
                            <div class="cell">JAVA падаван</div>
                            <div class="cell"><img src="/landing/img/kursu/padavan-icon.png" alt="java padavan"></div>
                        </div>
                        <div class="description table">
                            <div class="cell col-xs-12 col-sm-6">
                                1 200 грн.
                            </div>
                            <div class="cell col-xs-12 col-sm-6">
                                для тех, кто начинает
                            </div>
                        </div>
                    </div>
                    <div class="centered">
                        <div class="duration">
                            3 недели - 6 занятий
                        </div>
                    </div>
                    <div class="body pink">
                        <ul>
                            <li>
                                Введение в Java, Java SE, EE, Android.
                            </li>
                            <li>
                                Установка JDK. Среда разработки Eclipse/Intellij IDEA.
                            </li>
                            <li>
                                Компиляция и запуск приложений.
                            </li>
                            <li>
                                Введение в синтаксис языка Java.
                            </li>
                            <li>
                                Примитивные и ссылочные типы данных.
                            </li>
                            <li>
                                Работа с переменными и операторами.
                            </li>
                            <li>
                                Приложение "Калькултяор".
                            </li>
                            <li>
                                Массивы. Индексная арифметика.
                            </li>
                            <li>
                                Работа со строками.
                            </li>
                            <li>
                                Методы классов и параметры.
                            </li>
                            <li>
                                Операторы условного перехода и цикли.
                            </li>
                            <li>
                                Работа с датой и временем.
                            </li>
                            <li>
                                Интсрументы для отладки программ.
                            </li>
                        </ul>
                    </div>
                    <div class="button-wrapper centered">
                        <a href="#orderplace-popup" class="open-popup-link enroll-button">записаться</a>
                    </div>
                </div>
            </div>
            <div class="item col-xs-12 col-sm-4">
                <div class="wrapper">
                    <a href="#program-popup" class="open-popup-link">
                        <div class="head">
                            <div class="title blue table">
                                <div class="cell">JAVA джедай</div>
                                <div class="cell"><img src="/landing/img/kursu/jedi-icon.png" alt="java jedi"></div>
                            </div>
                            <div class="description table">
                                <div class="cell col-xs-12 col-sm-6">
                                    3 400 грн.
                                </div>
                                <div class="cell col-xs-12 col-sm-6">
                                    для тех, у кого есть база
                                </div>
                            </div>
                        </div>
                        <div class="centered">
                            <div class="duration">
                                8 недель -  48 занятий
                            </div>
                        </div>
                        <div class="body blue">
                            <ul>
                                <li>
                                    Введение в Java, Java SE, EE, Android.
                                </li>
                                <li>
                                    Установка JDK. Среда разработки Eclipse/Intellij IDEA.
                                </li>
                                <li>
                                    Компиляция и запуск приложений.
                                </li>
                                <li>
                                    Введение в синтаксис языка Java.
                                </li>
                                <li>
                                    Примитивные и ссылочные типы данных.
                                </li>
                                <li>
                                    Работа с переменными и операторами.
                                </li>
                                <li>
                                    Приложение "Калькултяор".
                                </li>
                                <li>
                                    Массивы. Индексная арифметика.
                                </li>
                                <li>
                                    Работа со строками.
                                </li>
                                <li>
                                    Методы классов и параметры.
                                </li>
                                <li>
                                    Операторы условного перехода и цикли.
                                </li>
                                <li>
                                    Работа с датой и временем.
                                </li>
                                <li>
                                    Интсрументы для отладки программ.
                                </li>
                            </ul>
                        </div>
                        <div class="button-wrapper centered">
                            <a href="#orderplace-popup" class="open-popup-link enroll-button">записаться</a>
                        </div>
                    </a>

                </div>
            </div>
            <div class="item col-xs-12 col-sm-4">
                <div class="wrapper">
                    <a href="#program-popup-magistr" class="open-popup-link">
                        <div class="head">
                            <div class="title green table">
                                <div class="cell">JAVA магистр</div>
                                <div class="cell"><img src="/landing/img/kursu/magistr-icon.png" alt="java magistr"></div>
                            </div>
                            <div class="description table">
                                <div class="cell col-xs-12 col-sm-6">
                                    3 800 грн.
                                </div>
                                <div class="cell col-xs-12 col-sm-6">
                                    чтобы стать мастером
                                </div>
                            </div>
                        </div>
                        <div class="centered">
                            <div class="duration">
                                12 недель - 72 занятий
                            </div>
                        </div>
                        <div class="body green">
                            <ul>
                                <li>
                                    ООП
                                </li>
                                <li>
                                    Потоки выполнения
                                </li>
                                <li>
                                    ORM технологии, Hibernate
                                </li>
                                <li>
                                    Фреймворк Spring
                                </li>
                                <li>
                                    Коллекции
                                </li>
                                <li>
                                    Связь БД с приложением
                                </li>
                                <li>
                                    Паттерны в ООП
                                </li>
                                <li>
                                    Разработка проекта в команде
                                </li>
                                <li>
                                    Разработка веб приложений
                                </li>
                                <li>
                                    Подготовка к собеседованию
                                </li>
                                <li>
                                    &nbsp <br />
                                    &nbsp
                                </li>
                                <li>
                                    &nbsp <br />
                                    &nbsp
                                </li>
                                <li>
                                    &nbsp
                                </li>

                            </ul>
                        </div>
                        <div class="button-wrapper centered">
                            <a href="#orderplace-popup" class="open-popup-link enroll-button">записаться</a>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Ends about-courses-->
<!--Starts discount-->
<div class="discount section">
    <div class="container">
        <img src="/landing/img/skidka/death-star.png" alt="java курсы" class="overlay">
        <div class="section-title">
            <div class="message col-xs-12 col-sm-6">
                купите три курса вместе и получите скидку - 20%
            </div>
            <div class="counter col-xs-12 col-sm-6">
                <script src="http://megatimer.ru/s/35a028672771fe905506ab7069236f47.js"></script>
            </div>
        </div>
        <div class="discount-info table">
            <div class="cell">
                <img src="/landing/img/skidka/padavan-icon.png" alt="padavan icon">
            </div>
            <div class="cell plus">
                <img src="/landing/img/skidka/plus.png" alt="plus png">
            </div>
            <div class="cell">
                <img src="/landing/img/skidka/jedi-icon.png" alt="jedi icon">
            </div>
            <div class="cell plus">
                <img src="/landing/img/skidka/plus.png" alt="plus png">
            </div>
            <div class="cell">
                <img src="/landing/img/skidka/master-icon.png" alt="master icon">
            </div>
            <div class="cell">
                <img src="/landing/img/skidka/equals.png" alt="equals png">
            </div>
            <div class="price cell">
                6 500 грн.
            </div>
        </div>
        <div class="button-wrap centered">
            <a href="#orderplace-popup" class="open-popup-link enroll-button">записаться на курсы</a>
        </div>
    </div>
</div>
<!--Ends discount-->
<!--Starts schedule-->
<div id="schedule"></div>
<div class="schedule section">
    <div class="container">
        <div class="section-title">
            расписание
        </div>
        <div class="col-xs-12">
            <div class="wr-table">
                <table>
                    <tbody>
                    <tr>
                        <td>JAVA падаван</td>
                        <td>с 03.09.2016</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;по субботам и воскресеньям 11:00 - 14:00</td>
                    </tr>
                    <tr>
                        <td>JAVA джежай</td>
                        <td>с 05.09.2016</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;понедельник, среда, пятница 19:00 - 22:00</td>
                    </tr>
                    <tr>
                        <td>JAVA магистр</td>
                        <td>с 06.09.2016</td>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;вторник, четверг 19:00 - 22:00</td>
                    </tr>
                    </tbody></table>
            </div>
        </div>
    </div>
    <div class="equipment-wr ">
        <div class="container">
            <div class="equipment table row">
                <div class="cell col-xs-12 col-sm-5">
                    <img src="/landing/img/circles/circle.png" alt="circle png" class="overlay">
                    на занятии вам понадобятся
                </div>
                <div class="cell col-xs-12 col-sm-7">
                    <ul>
                        <li>ноутбук</li>
                        <li>ручка и тетрадка</li>
                        <li>учебный материал (предоставляет преподаватель)</li>
                        <li>внимательность и сообразительность</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Ends schedule-->
<!--Starts graduates-->
<div class="graduates section">
    <div class="container">
        <img src="/landing/img/resultatu-kursov/milenium-icon.png" alt="milenium icon png" class="overlay">
        <div class="col-xs-12">
            <div class="section-title">
                мы выпускаем только <br/> профессионалов своего дела
            </div>
            <p>
                Наши студенты работают в лучших IT компаниях Украины
            </p>
            <div class="slider-wrapper">
                <div class="companies-slides slick-slider">
                    <div>
                        <img src="/landing/img/companies/ciklum.png" alt="ciklum png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/epam.png" alt="epam png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/globallogic.png" alt="globallogic png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/infopulse.png" alt="infopulse png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/intersog.png" alt="intersog png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/luxoft.png" alt="luxoft png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/miratech.png" alt="miratech png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/netcracker.png" alt="netcracker png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/playtech.png" alt="playtech png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/samsung.png" alt="samsung png">
                    </div>
                    <div>
                        <img src="/landing/img/companies/softserve.png" alt="softserve png">
                    </div>
                </div>
                <span class="slider-trigger trigger-left"><i class="fa fa-angle-left"></i></span>
                <span class="slider-trigger trigger-right"><i class="fa fa-angle-right"></i></span>
            </div>
            <div class="centered">
                <a href="#orderplace-popup" class="open-popup-link enroll-button">записаться на курсы</a>
            </div>
        </div>
    </div>
</div>
<!--Ends graduates-->
<!--Starts certificate-->
<div id="certificate"></div>
<div class="certificate section">
    <div class="container">
        <img src="/landing/img/serteficat/bb8.png" alt="bb8 png" class="overlay">
        <div class="section-title">
            сертификат
        </div>
        <div class="table">
            <div class="photo cell col-xs-12 col-sm-6">
                <img src="/landing/img/serteficat/certeficat.jpg" alt="certeficat jpg">
            </div>
            <div class="info cell col-xs-12 col-sm-6">
                <img src="/landing/img/circles/circle.png" alt="circle" class="overlay">
                <p>
                    После прохождения курсов <br/>Вы получаете сертификат <br/>подтверждающий Ваши <br/>новый знания
                </p>
            </div>
        </div>
    </div>
</div>
<!--Ends certificate-->
<!--Starts opensource-->
<div class="opensource section">
    <div class="container">
        <div class="parallax-js">
            <div class="asteroids-set">
                <div class="parallax-item a1" speed="1">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/github.png" alt="github png">
                    </div>
                </div>
                <div class="parallax-item a2" speed="1.2">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/libreoffice.png" alt="libreoffice png">
                    </div>
                </div>
                <div class="parallax-item a3" speed="1.1">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/vagrant.png" alt="vagrant png">
                    </div>
                </div>
                <div class="parallax-item a4" speed="4">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/hadoop.png" alt="hadoop png">
                    </div>
                </div>
                <div class="parallax-item a5" speed="1.4">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/vlc.png" alt="vlc png">
                    </div>
                </div>
                <div class="parallax-item a6" speed="0.8">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/firefox.png" alt="firefox png">
                    </div>
                </div>
                <div class="parallax-item a7" speed="3">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/ubuntu.png" alt="ubuntu png">
                    </div>
                </div>
                <div class="parallax-item a8" speed="2.8">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/gimp.png" alt="gimp png">
                    </div>
                </div>
                <div class="parallax-item a9" speed="0.8">
                    <div class="asteroid">
                        <img src="/landing/img/opensource/parallax/planet.png" alt="planet png">
                    </div>
                </div>
            </div>
        </div>
        <div class="section-title">
            open source проект
        </div>
        <div class="info col-xs-12 col-sm-7">
            <p>
                Лучшие студенты, окончившие магистрский курс, имеют вохможность учавствоватьв реальном
                Open source проекте
            </p>
            <div class="centered">
                <a href="#orderplace-popup" class="open-popup-link enroll-button">записаться на курсы</a>
            </div>
        </div>
        <div class="col-xs-12 col-sm-5"></div>
    </div>
</div>
<!--Ends opensource-->
<!--Starts process-->
<div id="process"></div>
<div class="process section">
    <div class="container">
<!--        <img src="/landing/img/kak-vse-proishodut/empire-robot.png" alt="empire robot" class="overlay">-->
        <div class="section-title">
            как все происходит
        </div>

        <div class="items-wr clearfix">
            <div class="item col-xs-12 col-sm-6 col-md-3">
                <div class="img-wr">
                    <img src="/landing/img/kak-vse-proishodut/1.png" alt="">
                </div>
                <div class="title">начало <br>&nbsp;</div>
                <ul class="list">
                   <li>
                       <div class="table">
                           <div class="cell">
                               <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                           </div>
                           <div class="cell">
                               Учебные материалы
                           </div>
                       </div>
                   </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Описание курса
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Регистрация на форуме
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-3">
                <div class="img-wr">
                    <img src="/landing/img/kak-vse-proishodut/2.png" alt="">
                </div>
                <div class="title">на занятии <br>&nbsp;</div>
                <ul class="list">
                   <li>
                       <div class="table">
                           <div class="cell">
                               <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                           </div>
                           <div class="cell">
                               Разбор домашки
                           </div>
                       </div>
                   </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Объяснение теории
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Практика
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Домашнее задание
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-3">
                <div class="img-wr">
                    <img src="/landing/img/kak-vse-proishodut/3.png" alt="">
                </div>
                <div class="title">между
                    занятиями</div>
                <ul class="list">
                   <li>
                       <div class="table">
                           <div class="cell">
                               <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                           </div>
                           <div class="cell">
                               Дополнительные дни для обучения
                           </div>
                       </div>
                   </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Консультации преподавателя
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Выполнение домашки
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Проверка ДЗ онлайн
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="item col-xs-12 col-sm-6 col-md-3">
                <div class="img-wr">
                    <img src="/landing/img/kak-vse-proishodut/4.png" alt="">
                </div>
                <div class="title">после
                    обучения <br>&nbsp;</div>
                <ul class="list">
                   <li>
                       <div class="table">
                           <div class="cell">
                               <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                           </div>
                           <div class="cell">
                               Дипломный проект

                           </div>
                       </div>
                   </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Сертификат о окончании
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="table">
                            <div class="cell">
                                <img src="/landing/img/kak-vse-proishodut/yes-sign.png" alt="">
                            </div>
                            <div class="cell">
                                Контракт на стажировку
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--Ends process-->
<!--Starts comments-->
<div id="comments"></div>
<div class="comments section">
    <div class="container">
        <div class="section-title">
            Отзывы
        </div>
        <div class="comments-slider-wrapper">
            <span class="slider-trigger trigger-left"><i class="fa fa-angle-left"></i></span>
            <span class="slider-trigger trigger-right"><i class="fa fa-angle-right"></i></span>
            <div class="comments-slides slick-slider">
                <div>
                    <div class="row">
                        <div class="item">
                            <div class="author col-xs-12 col-sm-5">
                                <div class="table">
                                    <div class="cell">
                                        <div class="photo" alt="отзывы java" style="background: url('/landing/img/otzuvu/viktor_reshetnyak.jpg');background-size: cover;background-position: center center;">
                                        </div>
                                    </div>
                                    <div class="cell">
                                <span class="name">
                                    Решетняк Виктор Владимирович
                                </span>
                               <span class="about">
                                    Разработчик
                               </span>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-wrapper col-xs-12 col-sm-7">
                                <div class="comment">
                                    Я начал изучать Java много лет назад. Читал мануалы, статьи с примерами, даже видео программистов о том как нужно "программировать".
                                    Это стало быстро надоедать потому что скучно и не интересно.
                                    С Александром учить Java не просто инетересно, реально практический уровень поднялся настолько, что сразу после курсов на работе сел за проект и написал приложение).
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="row">
                        <div class="item">
                            <div class="author col-xs-12 col-sm-5">
                                <div class="table">
                                    <div class="cell">
                                        <div class="photo" alt="отзывы java" style="background: url('/landing/img/otzuvu/natalia_reshetnyak.jpg');background-size: cover;background-position: center center;">
                                        </div>
                                    </div>
                                    <div class="cell">
                                <span class="name">
                                    Наталья Савенко-Решетняк
                                </span>
                               <span class="about">
                                    
                               </span>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-wrapper col-xs-12 col-sm-7">
                                <div class="comment">
                                    Я училась у нескольких преподавателей, но Александр Ращупкин превзошел все ожидания.
                                    Иногда для полного овладения учебным материалом требуются многие часы упорных занятий.
                                    Но благодаря методике преподавания на этих курсах материал давался легко.
                                    Благодаря вебинарам Александра, можно реально "творить" шедевры, а не быть ремесленником.
                                    Браво, Александр Ращупкин! Думаю продолжить обучение в этой школе!
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="row">
                        <div class="item">
                            <div class="author col-xs-12 col-sm-5">
                                <div class="table">
                                    <div class="cell">
                                        <div class="photo" alt="отзывы java" style="background: url('/landing/img/otzuvu/dmitry_miroshnikov.jpg');background-size: cover;background-position: center center;">
                                        </div>
                                    </div>
                                    <div class="cell">
                                <span class="name">
                                    Дмитрий Мирошников
                                </span>
                               <span class="about">
                                    Разработчик
                               </span>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-wrapper col-xs-12 col-sm-7">
                                <div class="comment">
                                    Записываясь на курс Java, изначально планировал идти именно к Александру. Причина - хорошие отзывы от знакомых, обучавшихся у него ранее. В целом впечатления от преподавателя и наполнения курса самые позитивные.
                                    Обучение проходило на достаточно высоком уровне с использованием всех основных аспектов технологии Java. Теоретический материал в достаточном количестве + практика, практика и еще раз практика. Стоит отметить профессионализм преподавателя: грамотная методика изложения материала; диалог со студентами, способствовавший учебному процессу; коллективная работа над задачами. Практические задания - интересные, сложные, в формате: постановка задачи - решение студентами - проверка, с указанием оптимального решения. Полезен был также детальный разбор каждого домашнего задания. Рекомендую Александра для всех, желающих освоить Java.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="row">
                        <div class="item">
                            <div class="author col-xs-12 col-sm-5">
                                <div class="table">
                                    <div class="cell">
                                        <div class="photo" alt="отзывы java" style="background: url('/landing/img/otzuvu/oksana_gamza.jpg');background-size: cover;background-position: center center;">
                                        </div>
                                    </div>
                                    <div class="cell">
                                <span class="name">
                                    Оксана Гамза
                                </span>
                               <span class="about">
                                    Интерн 
                               </span>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-wrapper col-xs-12 col-sm-7">
                                <div class="comment">
                                    Очень понравился подход в обучении. За каждым теоретическим блоком следовала практика на уроке, что давало возможность применить теорию сразу и  задать вопрос тут же если “застрял”. Также понравилось, что к решению студентов подводили, а не давали готовое.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="container">
            <img src="/landing/img/otzuvu/jedi-starflight.png" alt="jedi starflight png">
        </div>
    </div>
</div>
<!--Ends comments-->
<!--Ends support-->
<div id="support"></div>
<div class="support section">
    <div class="container">
        <img src="/landing/img/podderzhka/chewbacca.png" alt="chewbacca png" class="overlay">
        <div class="section-title">
            удалённая поддержка
        </div>
        <div class="info col-xs-12 col-sm-6">
            <p>
                Ежедневные занятия требуют наставлений старшего. Зная его галактический идентификатор
                <a href="skype:jedionline.com.ua?add"><img src="/landing/img/podderzhka/skype-white.png" alt="skype white png"></a>
                юнный падаван сможет победить препятствия на пути к  познанию Кодекса Java и обрести
                навыки разработки космических программных продуктов
            </p>
            <a href="skype:jedionline.com.ua?add"><div class="skype">
                <img src="/landing/img/podderzhka/skype-yellow.png" alt="skype yellow png">
                jedionline.com.ua</div></a>
        </div>
        <div class="col-xs-12 col-sm-6">
        </div>
    </div>
</div>
<!--Ends support-->
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
<!--Starts popups-->
<a href="#thanks-popup" id="callThanksPopup" class="open-popup-link mfp-hide">Показать сообщение про успешную отправку</a>
<div id="thanks-popup" class="thanks-popup mfp-hide">
    <div class="single-form">
        <div class="col-xs-12 title">
            Спасибо за заявку!
        </div>
    </div>
</div>
<div id="ordercall-popup" class="ordercall-popup mfp-hide">
    <div class="single-form">
        <div class="col-xs-12 title">
            Заполните форму и мы вам перезвоним
        </div>
        <div class="enroll-form col-xs-12">
            <form name="order-call-form" action="" class="form-wrapper">
                <input name="name" class="name" type="text" placeholder="Ваше имя">
                <input name="phone" class="phone" type="text" placeholder="Телефон">
                <div class="submitForm enroll-button">отправить</div>
            </form>
        </div>
    </div>
</div>

<div id="program-popup" class="ordercall-popup mfp-hide">
    <div class="single-form">
        <div class="col-xs-12 title">
            Программа курса Java OOP
        </div>
        <div class="enroll-form col-xs-12">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog1">
                    <div class="prog_title" style="color:yellow;" data-hc-id="a0083061359300e0587744020000">Первый блок (1 нед.)<span class="hcc hc__ht hc_a0083061359300e0587744020000" data-hc-id="a0083061359300e0587744020000" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Повторение</div>
                    <ul class="bl_arr">
                        <li>Переменные и ветвление.</li>
                        <li>Итерация.</li>
                        <li>Массивы.</li>
                        <li>Методы.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Повторение</div>
                    <ul class="bl_arr">
                        <li>Основные принципы ООП.</li>
                        <li>Использование объектов.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog2">
                    <div class="prog_title" style="color:yellow;" data-hc-id="00081063301300f05837443a0020">Второй блок (1 нед.)<span class="hcc hc__ht hc_00081063301300f05837443a0020" data-hc-id="00081063301300f05837443a0020" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Классы</div>
                    <ul class="bl_arr">
                        <li>Структура класса.</li>
                        <li>Структура класса.</li>
                        <li>Связи между класамми.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Объекты</div>
                    <ul class="bl_arr">
                        <li>Ссылочные типы данных.</li>
                        <li>Связь классов и обектов.</li>
                        <li>Инстанциирование объектов.</li>
                        <li>Уничтожение объектов.</li>
                        <li>Взаимодействие между объектами.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Массивы объектов</div>
                    <ul class="bl_arr">
                        <li>Объявление и инициализация массивов объектов.</li>
                        <li>Особенности работы с массивами объектов.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog3">
                    <div class="prog_title" style="color:yellow;" data-hc-id="20083169b99300f45817c18a4008">Третий блок (1 нед.)<span class="hcc hc__ht hc_20083169b99300f45817c18a4008" data-hc-id="20083169b99300f45817c18a4008" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Алгоритмы</div>
                    <ul class="bl_arr">
                        <li>Обзор алгоритмов.</li>
                        <li>Понятие и определение сложности алгоритма.</li>
                        <li>Примеры сложности разных алгоритмов.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Сортировка</div>
                    <ul class="bl_arr">
                        <li>Обзор алгоритмов сортировки.</li>
                        <li>Общие принципы сортировки.</li>
                        <li>Рассмотрение основных алгоритмов сортировки.</li>
                        <li>Применение стандартной сортировки.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Поиск</div>
                    <ul class="bl_arr">
                        <li>Обзор алгоритмов поиска.</li>
                        <li>Двоичный поиск.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog4">
                    <div class="prog_title" style="color:yellow;" data-hc-id="22003063fc130074405740300028">Четвертый блок (1 нед.)<span class="hcc hc__ht hc_22003063fc130074405740300028" data-hc-id="22003063fc130074405740300028" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Инкапсуляция</div>
                    <ul class="bl_arr">
                        <li>Скрытие данных.</li>
                        <li>Модификаторы доступа.</li>
                        <li>Пакеты.</li>
                        <li>Реализация инкапсуляции.</li>
                        <li>UML: диаграмма классов.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Наследование</div>
                    <ul class="bl_arr">
                        <li>Перегрузка и переопределение методов.</li>
                        <li>Статические поля и методы.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Агрегация</div>
                    <ul class="bl_arr">
                        <li>Подвиды агрегации.</li>
                        <li>Делегирование.</li>
                    </ul>
                </div>
                <div class="clearfix"></div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog1 marg-top50">
                    <div class="prog_title" style="color:yellow;" data-hc-id="8048312b95930074404741884828">Пятый блок (1 нед.)<span class="hcc hc__ht hc_8048312b95930074404741884828" data-hc-id="8048312b95930074404741884828" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Рекурсия</div>
                    <ul class="bl_arr">
                        <li>Бесконечная рекурсия.</li>
                        <li>Связь рекурсии и итерации.</li>
                        <li>Виды рекурсивных алгоритмов.</li>
                        <li>Рекурсивный метод.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Перечисления</div>
                    <ul class="bl_arr">
                        <li>Создание перечислений.</li>
                        <li>Основные действия с перечислениями.</li>
                        <li>Применение перечислений.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog2 marg-top50">
                    <div class="prog_title" style="color:yellow;" data-hc-id="0248306b589300e44867c0a24808">Шестой блок (1 нед.)<span class="hcc hc__ht hc_0248306b589300e44867c0a24808" data-hc-id="0248306b589300e44867c0a24808" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Связанные списки</div>
                    <ul class="bl_arr">
                        <li>Структура связанного списка.</li>
                        <li>Заполнение связанного списка.</li>
                        <li>Операции со связанными списками.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Вложенные классы</div>
                    <ul class="bl_arr">
                        <li>Типы вложенных классов.</li>
                        <li>Особенности создания вложенных классов.</li>
                        <li>Преимущества и недостатки вложенных классов.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Потоки выполнения</div>
                    <ul class="bl_arr">
                        <li>Концепция параллельного программирования.</li>
                        <li>Способы создания потоков</li>
                        <li>Запуск потоков</li>
                        <li>Остановка потоков</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog3 marg-top50">
                    <div class="prog_title" style="color:yellow;" data-hc-id="8048102bb09300f0582741280000">Седьмой блок (1 нед.)<span class="hcc hc__ht hc_8048102bb09300f0582741280000" data-hc-id="8048102bb09300f0582741280000" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Полиморфизм</div>
                    <ul class="bl_arr">
                        <li>Преобразование объектных типов.</li>
                        <li>Позднее связывание.</li>
                        <li>Практическое применение полиморфизма.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Абстрактные классы</div>
                    <ul class="bl_arr">
                        <li>Абстрактные методы.</li>
                        <li>Использования абстрактных классов.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Интерфейсы</div>
                    <ul class="bl_arr">
                        <li>Объявление интерфейсов.</li>
                        <li>Использование интерфейсов.</li>
                        <li>Объектно ориентированная архитектура</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog4 marg-top50">
                    <div class="prog_title" style="color:yellow;" data-hc-id="a00831617593006040e740224008">Восьмой блок (1 нед.)<span class="hcc hc__ht hc_a00831617593006040e740224008" data-hc-id="a00831617593006040e740224008" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;" data-hc-id="7df6e46021387b487f8d4c932b50">Исключения и потоки ввода вывода<span class="hcc hc__ht hc_7df6e46021387b487f8d4c932b50" data-hc-id="7df6e46021387b487f8d4c932b50" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <ul class="bl_arr">
                        <li>Виды исключений.</li>
                        <li>Иерархия исключений.</li>
                        <li>Обработка исключений.</li>
                        <li>Иерархия потоков.</li>
                        <li>Основные операции с потоками.</li>
                        <li>Работа с файлами</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Обобщенное программирование</div>
                    <ul class="bl_arr">
                        <li>Применение родовых типов.</li>
                        <li>Параметризация списков.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Оконные приложения</div>
                    <ul class="bl_arr">
                        <li>Структура Swing приложения.</li>
                        <li>Графические компоненты.</li>
                        <li>Компоновка элементов.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Обработка событий</div>
                    <ul class="bl_arr">
                        <li>События в Swing.</li>
                        <li>Обработчики событий.</li>
                        <li>Анонимные классы.</li>
                        <li>Полноценное Java приложение</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="program-popup-magistr" class="ordercall-popup mfp-hide">
    <div class="single-form">
        <div class="col-xs-12 title">
            Программа курса Java Developer
        </div>
        <div class="enroll-form col-xs-12">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog1">
                    <div class="prog_title" style="color:yellow;" data-hc-id="a0083061359300e0587744020000">Первый блок (2нед.)<span class="hcc hc__ht hc_a0083061359300e0587744020000" data-hc-id="a0083061359300e0587744020000" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">ООП</div>
                    <ul class="bl_arr">
                        <li>Инструментарий для разработки в java.</li>
                        <li>Классы.</li>
                        <li>Объекты.</li>
                        <li>Переопределение.</li>
                        <li>Инкапсуляция.</li>
                        <li>Полиморфизм.</li>
                        <li>Повторное использование кода.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Коллекции</div>
                    <ul class="bl_arr">
                        <li>Иерархия коллекций в Java.</li>
                        <li>Списки.</li>
                        <li>Очереди.</li>
                        <li>Множества.</li>
                        <li>Карты.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog2">
                    <div class="prog_title" style="color:yellow;" data-hc-id="00081063301300f05837443a0020">Второй блок (3 нед.)<span class="hcc hc__ht hc_00081063301300f05837443a0020" data-hc-id="00081063301300f05837443a0020" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Потоки выполнения</div>
                    <ul class="bl_arr">
                        <li>Создание потоков.</li>
                        <li>Остановка потоков.</li>
                        <li>Взаимодействие потоков.</li>
                        <li>Управление потоками.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Разработка архитектуры БД</div>
                    <ul class="bl_arr">
                        <li>Реляционные БД.</li>
                        <li>Ключи.</li>
                        <li>Связи.</li>
                        <li>SQL.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Связь БД с приложением</div>
                    <ul class="bl_arr">
                        <li>Понятие JDBC.</li>
                        <li>Основные объекты JDBC.</li>
                        <li>Подключение из программы к БД.</li>
                        <li>Выражения.</li>
                        <li>Транзакции.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog3">
                    <div class="prog_title" style="color:yellow;" data-hc-id="20083169b99300f45817c18a4008">Третий блок (2 нед.)<span class="hcc hc__ht hc_20083169b99300f45817c18a4008" data-hc-id="20083169b99300f45817c18a4008" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">ORM технологии, Hibernate</div>
                    <ul class="bl_arr">
                        <li>Принципы  ORM.</li>
                        <li>Шаблоны для реализации ORM.</li>
                        <li>Конфигурация Hibernate.</li>
                        <li>Работа с сессией.</li>
                        <li>JPA отображение.</li>
                        <li>Отображение ассоциаций.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog4">
                    <div class="prog_title" style="color:yellow;" data-hc-id="22003063fc130074405740300028">Четвертый блок (2 нед.)<span class="hcc hc__ht hc_22003063fc130074405740300028" data-hc-id="22003063fc130074405740300028" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Фреймворк Spring</div>
                   <ul class="bl_arr">
                        <li>Разработка архитектуры ПО, OOD.</li>
                        <li>IoC</li>
                        <li>Виды инжектирвания</li>
                        <li>Способы задания контекста</li>
                        <li>Различные библиотеки Spring</li>
                    </ul>
                </div>
                <div class="clearfix"></div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog1 marg-top50">
                    <div class="prog_title" style="color:yellow;" data-hc-id="8048312b95930074404741884828">Пятый блок (3 нед.)<span class="hcc hc__ht hc_8048312b95930074404741884828" data-hc-id="8048312b95930074404741884828" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Рефакторинг</div>
                    <ul class="bl_arr">
                        <li>Принципы рефакторинга.</li>
                        <li>Виды рефакторинга.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Паттерны в ООП</div>
                    <ul class="bl_arr">
                        <li>Виды шаблонов.</li>
                        <li>Реализация основных шаблонов.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Разработка веб приложений</div>
                    <ul class="bl_arr">
                        <li>Сервлеты.</li>
                        <li>JSP.</li>
                    </ul>
                </div>

                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 opac prog2 marg-top50">
                    <div class="prog_title" style="color:yellow;" data-hc-id="0248306b589300e44867c0a24808">&nbsp<span class="hcc hc__ht hc_0248306b589300e44867c0a24808" data-hc-id="0248306b589300e44867c0a24808" style="display: none;"><svg width="11" height="11" viewBox="0 0 511.626 511.626"><path d="M477.371,127.44c-22.843-28.074-53.871-50.249-93.076-66.523c-39.204-16.272-82.035-24.41-128.478-24.41c-34.643,0-67.762,4.805-99.357,14.417c-31.595,9.611-58.812,22.602-81.653,38.97c-22.845,16.37-41.018,35.832-54.534,58.385C6.757,170.833,0,194.484,0,219.228c0,28.549,8.61,55.3,25.837,80.234c17.227,24.931,40.778,45.871,70.664,62.811c-2.096,7.611-4.57,14.846-7.426,21.693c-2.855,6.852-5.424,12.474-7.708,16.851c-2.286,4.377-5.376,9.233-9.281,14.562c-3.899,5.328-6.849,9.089-8.848,11.275c-1.997,2.19-5.28,5.812-9.851,10.849c-4.565,5.048-7.517,8.329-8.848,9.855c-0.193,0.089-0.953,0.952-2.285,2.567c-1.331,1.615-1.999,2.423-1.999,2.423l-1.713,2.566c-0.953,1.431-1.381,2.334-1.287,2.707c0.096,0.373-0.094,1.331-0.57,2.851c-0.477,1.526-0.428,2.669,0.142,3.433v0.284c0.765,3.429,2.43,6.187,4.998,8.277c2.568,2.092,5.474,2.95,8.708,2.563c12.375-1.522,23.223-3.606,32.548-6.276c49.87-12.758,93.649-35.782,131.334-69.097c14.272,1.522,28.072,2.286,41.396,2.286c46.442,0,89.271-8.138,128.479-24.417c39.208-16.272,70.233-38.448,93.072-66.517c22.843-28.062,34.263-58.663,34.263-91.781C511.626,186.108,500.207,155.509,477.371,127.44z"></path></svg><span class="hcc hc__htcount">+</span></span></div>
                    <div class="prog_pod_title" style="color:#79742c;">Разработка веб приложений</div>
                    <ul class="bl_arr">
                        <li>Сервлеты.</li>
                        <li>JSP.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Разработка проекта в команде</div>
                    <ul class="bl_arr">
                        <li>Передовые методологии командной разработки ПО.</li>
                        <li>Системы контроля версий.</li>
                    </ul>
                    <div class="prog_pod_title" style="color:#79742c;">Подготовка к собеседованию</div>
                    <ul class="bl_arr">
                        <li>Советы по составлению резюме.</li>
                        <li>Часто задаваемые вопросы на собеседовании.</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="orderplace-popup" class="orderplace-popup mfp-hide">
    <div class="single-form">
        <div class="col-xs-12 title">
            Заполните форму и начните учить Java правильно
        </div>
        <div class="enroll-form col-xs-12">
            <form name="order-place-form" action="" class="form-wrapper">
                <div class="course-type table">
                    <div class="cell">
                        Выберите курс
                    </div>
                    <div class="cell">
                        <select name="courseType">
                            <option value="Падаван">Java Падаван</option>
                            <option value="Джедай">Java Джежай</option>
                            <option value="Магистр">Java Магистр</option>
                            <option value="Три курса">Три курса</option>
                        </select>
                    </div>
                </div>
                <input name="name" class="name" type="text" placeholder="Ваше имя">
                <input name="phone" class="phone" type="text" placeholder="Телефон">
                <input name="email" class="" type="text" placeholder="E-mail">
                <textarea name="message" id="" cols="30" rows="4" placeholder="Сообщение"></textarea>
                <div class="submitForm enroll-button">записаться</div>
            </form>
        </div>
    </div>
</div>
<!--Ends popups-->
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