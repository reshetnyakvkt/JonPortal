<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 04.09.14
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes.jsp" %>
</head>
<body>

<div id="container" class="container-fluid">
    <%@include file="../header.jsp" %>


    <div class="row">

        <!-- tabs -->
        <div class="panel panel-default">
            <div class="panel-body">
                <%@include file="menu.jsp" %>
                <div class="col-md-8">
                    <div class="row">

                        <div class="span8">
                            <h2>Элементы айтишного сленга</h2>
                            <hr/>
                            <div class="row">
                                <div class="span8">
                                    <blockquote class="pull-right">
                                        <p>Начиная занятия с новой группой, некоторое время</p>

                                        <p>Я стараюсь говорить на человеческом языке</p>

                                        <p>Но через некоторое время начинаю говорить</p>

                                        <p>На другом языке, близкому к человеческому</p>
                                        <small>Александр Рощупкин</small>
                                    </blockquote>
                                </div>
                            </div>


                            <h4>Апнуться, Апдейтится</h4>

                            <p>Выполнить команду git update или svn update. Эта команда для системы контроля версий,
                                синхронизирующая код в хранилище с локальной рабочей копией</p>

                            <h4>Асматик</h4>

                            <p>Программист, пишущий на языке ассемблера</p>

                            <h4>Баг</h4>

                            <p>Ошибка в программе</p>

                            <h4>Барсик, Васик</h4>

                            <p>Язык программирования Бэйсик</p>

                            <h4>Бойлерплейт-код</h4>

                            <p>Код, не описывающий непосредственно логику работы программы, но который необходимо
                                писать</p>

                            <h4>Битый</h4>

                            <p>Поломанный, испорченный</p>

                            <h4>Бсод — от англ. [[en:BSoD]]</h4>

                            <p>Сообщение OS Microsoft Windows о серьёзной ошибке, требующей перезагрузки системы</p>

                            <h4>Быдлокодер</h4>

                            <p>Человек, который не умеет программировать. Не хочет или не понимает зачем нужно знать
                                алгоритмы, использовать шаблоны проектирования, поддерживать стиль кодирования и т.п.
                                Чаще всего быдлокодер использует готовые решения и компоненты, не заботится о повторном
                                использовании кода</p>

                            <h4>Бэкапить</h4>

                            <p>Создавать резервные(страховочные) копии</p>

                            <h4>Вылетать</h4>

                            <p>Внезапное завершение программы</p>

                            <h4>Веб</h4>

                            <p>Интернет, всемирная паутина</p>

                            <h4>Варик</h4>

                            <p>Компьютерная игра Warcraft</p>

                            <h4>Велосипед (от "изобретать велосипед")</h4>

                            <p>Приложение, для которого существует множество аналогов</p>

                            <h4>Вешаться</h4>

                            <p>То же, что и виснуть</p>

                            <h4>Винда, Маздай</h4>

                            <p>Операционная система Windows (устаревающая)</p>

                            <h4>Виндузятник</h4>

                            <p>Пользователь операционной системы Windows</p>

                            <h4>Видюха</h4>

                            <p>Видеокарта</p>

                            <h4>Геттер, Сеттер</h4>

                            <p>Методы доступа к полям класса (чтение, запись)</p>

                            <h4>Гектар</h4>

                            <p>Гигабайт</p>

                            <h4>Глюк</h4>

                            <p>Ошибка программы</p>

                            <h4>Глючить</h4>

                            <p>Работать со сбоями</p>

                            <h4>Гнусный</h4>

                            <p>Написанный под эгидой GNU</p>

                            <h4>Грохнуть</h4>

                            <p>Стереть, уничтожить, испортить</p>

                            <h4>Гуглить</h4>

                            <p>Искать в Интернете (как правило, при помощи Google)</p>

                            <h4>Гуй от англ. GUI</h4>

                            <p>Графический интерфейс пользователя</p>

                            <h4>Дебаг</h4>

                            <p>От англ. debug. Процесс обнаружения и исправления ошибок программы.
                                На дебаг уходит значительная часть времени разработки программы.</p>

                            <h4>Дебажить (англ. debug)</h4>

                            <p>Искать ошибки в программе, отлаживать программу</p>

                            <h4>Девелопер (англ. developer)</h4>

                            <p>Разработчик. Часто на русский не переводятся и приставки, например "синиор девелопер"
                                (старший разработчик)</p>

                            <h4>Дока</h4>

                            <p>Сопроводительная документация</p>

                            <h4>Дрова (англ. driver)</h4>

                            <p>Драйверы</p>

                            <h4>DDD (Drink Driven Development)</h4>

                            <p>Разработка осуществляемая в особом состоянии</p>

                            <h4>Дыра</h4>

                            <p>Способ, не предусмотренный разработчиками ПО, и позволяющий получить к чему-либо
                                несанкционированный доступ</p>

                            <h4>Естественный отбор</h4>

                            <p>Замена чужого кода своим</p>

                            <h4>Железо</h4>

                            <p>1) компьютер и аксессуары к нему; 2) любая техника</p>

                            <h4>Жужжать</h4>

                            <p>Устанавливать связь при помощи модема</p>

                            <h4>Залить</h4>

                            <p>Закачать или отправить файл на удаленный компьютер</p>

                            <h4>Зазипованный</h4>

                            <p>Архив формата zip</p>

                            <h4>Заимпортировать</h4>

                            <p>Подключить классы с помощью инструкции import</p>

                            <h4>Индусский код</h4>

                            <p>Запутанный, нелогичный, абсурдный код</p>

                            <h4>Капча</h4>

                            <p>Система распознавания человека путём показа букв или цифр, которые нужно ввести для
                                подтверждения</p>

                            <h4>Кастовать</h4>

                            <p>Термин программирования, сокращённый. От англ. type cast — приведение типа.</p>

                            <h4>Камень</h4>

                            <p>Центральный процессор</p>

                            <h4>Кеды</h4>

                            <p>Cвободная среда рабочего стола KDE</p>

                            <h4>Клава</h4>

                            <p>Клавиатура</p>

                            <h4>Класстрофобия</h4>

                            <p>Отказ от объектно-ориентированного подхода при очевидной необходимости и возможности его
                                использования</p>

                            <h4>Код</h4>

                            <p>Исходный код программы</p>

                            <h4>Комьюнити — англ. community</h4>

                            <p>Сообщество, группа пользователей одного и того же сетевого ресурса; разработчиков и др.
                                одного программного продукта</p>

                            <h4>Копи-паст — от англ. Copy-Paste</h4>

                            <p>Копировать и вставить</p>

                            <h4>Корень</h4>

                            <p>Первая директория в дереве</p>

                            <h4>Компилить</h4>

                            <p>От компилировать, процесс преобразования текста программы написанного на языке высокого
                                уровня в машинный язык</p>

                            <h4>Курить</h4>

                            <p>Изучать что-либо</p>

                            <h4>Костыль</h4>

                            <p>Уловка, обходной способ сделать что-либо в компьютерах и программировании. Как правило не
                                понятный сходу и трудоёмкий. Но часто являющийся единственным способом решить проблему /
                                использовать технологию.</p>

                            <h4>Комбинация из трёх пальцев</h4>

                            <p>
                                <ctrl>+
                                    <alt>+
                                        <delete>
                            </p>

                            <h4>Кривой</h4>

                            <p>Кривая программа – плохо структурированная и/или с большим количеством багов. Работающая,
                                но в некоторых случаях вызывающая проблемы</p>

                            <h4>Китайский код</h4>

                            <p>Код, написанный методом копирования-вставки; решение задачи в лоб, без поиска подходящих
                                алгоритмов, без оптимизации</p>

                            <h4>Ламер</h4>

                            <p>Полный профан во всем что связанно с компьютерами, считающий себя гением компьютеров</p>

                            <h4>Лагать</h4>

                            <p>Глагольная форма слова Лаг, означает тормозить, медленно работать</p>

                            <h4>Левый</h4>

                            <p>Странный</p>

                            <h4>Лечить</h4>

                            <p>Исправлять ошибки, часто самыми простыми способами, по проинципу латания дыр</p>

                            <h4>Лежать</h4>

                            <p>Пребывать в неработоспособном состоянии</p>

                            <h4>Либа</h4>

                            <p>Программная библиотека. (калька с англ. lib, library)</p>

                            <h4>Линух</h4>

                            <p>Операционная система семейства Linux</p>

                            <h4>Линк</h4>

                            <p>Гипертекстовая ссылка URL</p>

                            <h4>Линуксоид</h4>

                            <p>Человек который пользуется Linux</p>

                            <h4>Логиниться</h4>

                            <p>Входить в систему под определённым именем пользователя</p>

                            <h4>Лыжа</h4>

                            <p>Компания LG</p>

                            <h4>Мазила</h4>

                            <p>Браузер Mozilla</p>

                            <h4>Машина</h4>

                            <p>Электронно-вычислительная машина (ЭВМ)</p>

                            <h4>Мануал</h4>

                            <p>Руководство пользователя</p>

                            <h4>Мускул</h4>

                            <p>СУБД MySQL</p>

                            <h4>Мыло</h4>

                            <p>Электронная почта</p>

                            <h4>НаСИльник</h4>

                            <p>Программист на языке Си</p>

                            <h4>Оперативка</h4>

                            <p>Оперативная память, ОЗУ</p>

                            <h4>Операционка</h4>

                            <p>Операционная система</p>

                            <h4>Очень ориентированное программирование (Over Oriented Programming)</h4>

                            <p>Использование пяти уровней классов, когда можно обойтись одним</p>

                            <h4>Отец</h4>

                            <p>Игрок в компьютерные игры, достигший совершенства в своем деле</p>

                            <h4>Плюсы</h4>

                            <p>Язык программирования C++</p>

                            <h4>Пень</h4>

                            <p>Центральный процессор марки Pentium компании Intel</p>

                            <h4>Подмышка</h4>

                            <p>Коврик для манипулятора типа «Мышь»</p>

                            <h4>Пинговать</h4>

                            <p>Использовать программу «ping».</p>

                            <h4>Повиснуть</h4>

                            <p>Зависнуть, перестать работать (применительно к софту и компьютерам)</p>

                            <h4>Пилить</h4>

                            <p>Реализовывать функциональность программаного продукта</p>

                            <h4>Планка</h4>

                            <p>Модуль оперативной памяти</p>

                            <h4>Пофиксить — (от английского fix)</h4>

                            <p>Исправить</p>

                            <h4>Прикрутить</h4>

                            <p>Добавить, обеспечить совместную работу</p>

                            <h4>Ребутать от англ. reboot</h4>

                            <p>Перегружать</p>

                            <h4>Релиз, от англ. RELEASE</h4>

                            <p>Окончательная версия программы</p>

                            <h4>Родить</h4>

                            <p>Выдать результат и вообще сделать что-нибудь осмысленное после долгой имитации глубоких
                                раздумий</p>

                            <h4>Ругаться</h4>

                            <p>Выдавать сообщение об ошибке</p>

                            <h4>Сантехника</h4>

                            <p>Продукты фирмы Sun</p>

                            <h4>Сдохнуть</h4>

                            <p>Прекратить работать</p>

                            <h4>Семёрка</h4>

                            <p>Операционная система Windows 7</p>

                            <h4>Сетка, локалка</h4>

                            <p>Локальная компьютерная сеть</p>

                            <h4>Сервак</h4>

                            <p>Сервер</p>

                            <h4>Слететь</h4>

                            <p>Выйти из строя, отказ в работоспособности</p>

                            <h4>Слить</h4>

                            <p>Скачать файл из Сети на свой компьютер</p>

                            <h4>Сносить</h4>

                            <p>Деинсталлировать (удалять) программное обеспечение</p>

                            <h4>Сорцы</h4>

                            <p>От англ. source – исходные коды программы</p>

                            <h4>Спагетти-код</h4>

                            <p>Код с запутанной последовательностью исполнения</p>

                            <h4>Ставить</h4>

                            <p>Устанавливать новый программный продукт в ОС</p>

                            <h4>Таск</h4>

                            <p>(Калька с английского Task) Задача, которая ставится перед разработчиком.</p>

                            <h4>Тулза</h4>

                            <p>(Калька с английского Tools) Инструментальное, вспомогательное приложение</p>

                            <h4>Убить</h4>

                            <p>Удалить, остановить</p>

                            <h4>Уши</h4>

                            <p>Наушники</p>

                            <h4>ФАК — русское произношение слова (англ. [[FAQ]] — Frequently Asked Questions)</h4>

                            <p>Ответы на часто задаваемые вопросы</p>

                            <h4>Файло</h4>

                            <p>(пренебрежительно, иронично) совокупность файлов; файлы</p>

                            <h4>Формошлёпство</h4>

                            <p>Разработка интерфейсов программ в визуальных средах проектирования</p>

                            <h4>Tачка</h4>

                            <p>Компьютер</p>

                            <h4>Топтать клаву</h4>

                            <p>Работать на клавиатуре</p>

                            <h4>Тормозить</h4>

                            <p>Работать очень медленно</p>

                            <a href="#FDD"></a><h4>Fear Driven Development</h4>

                            <p>Разработка, управляемая страхом — когда руководитель только подливает масла в огонь и
                                оказывает излишнее давление на разработчиков</p>

                            <h4>Фича</h4>

                            <p>Дополнительная возможность/функциональность программы или девайса</p>

                            <h4>Шара</h4>

                            <p>Папка обмена документами в локальной сети</p>

                            <h4>Юзверь (он же юзер, от англ. user)</h4>

                            <p>Пользователь компьютера</p>

                            <h4>Юзать</h4>

                            <p>Пользоваться чем-либо</p>

                            <h4>Хабр</h4>

                            <p>Крупнейшее русское it-сообщество «хабрахабр» (habrahabr.ru)</p>

                            <h4>Хак</h4>

                            <p>модификация кода программы, позволяющая уменьшить размер программы, расширить
                                функциональность или ускорить её работу, наконец просто красивое программистское
                                решение</p>

                            <h4>Хип</h4>

                            <p>Структура данных (англ. heap – куча, также «пирамида»), двоичное дерево с выполнением
                                правила кучи (каждая вершина больше (меньше) либо равна своему предку)</p>

                            <h4>Hope Driven Development</h4>

                            <p>Разработка, управляемая надеждой — разработка в длительном неспланированном цикле с
                                надеждой, что все заработает в релизе</p>

                            <h4>Холивар — (от англ. holy war)</h4>

                            <p>Cвященные войны, словесные баталии по поводу какой из обсуждаемых объектов лучше</p>

                            <h4>Хомяк</h4>

                            <p>Домашняя (англ. home) папка пользователя</p>

                            <h4>Чайник</h4>

                            <p>Малоопытный пользователь, человек, который не умеет целесообразно пользоваться
                                персональным компьютером в нужном для него объёме</p>

                            <h4>Читер (англ. cheat — жульничество, англ. cheater — жулик)</h4>

                            <p>Человек который старается обмануть</p>

                            <h4>Шаманство, Магия</h4>

                            <p>Действия специалиста (программиста, системного администратора) непонятные окружающим</p>

                            <h4>Хоткей</h4>

                            <p>Горячая клавиша, клавиша быстрого доступа</p>

                            <h4>Яблочник</h4>

                            <p>Любитель Apple и всего, что с ней связано</p>
                        </div>
                    </div>

                    <br/>

                </div>

            </div>
            <!-- tabs -->
        </div>

    </div>

    <footer id="footer"></footer>
</div>

</body>
</html>

