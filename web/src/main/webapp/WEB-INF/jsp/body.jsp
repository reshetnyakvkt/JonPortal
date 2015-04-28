<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<div id="2" class="logo"></div>--%>

<div class="back">
    <div id="battlestar" class="logo"></div>
</div>

<script>
    $(document).ready(function() {

        <%-- управление анимацией меню --%>
        <%--'это не ошибка. Просто смешение jQuery и JSTL синтаксиса--%>
        $('#<c:out value="${item}"/>').addClass("active").css('position','relative').css('top',-100).animate({top: 0},300);

        $('#header').siblings('div').css('opacity',0).animate({opacity:1},600);
    });

    window.onload = function () {
        function wait(millis) {
            window.setTimeout(run, millis)
        }

        function run() {
            var battlestar = document.getElementById("battlestar");
            battlestar.style.display = 'block';
            move(-800, 0, 0.2, 0.1, battlestar);
        }

        function move(left, top, leftInc, topInc, logo) {
            var iniLeft = left;
            var iniTop = top;
            var iniTopInc = topInc;
            var iniLeftInc = leftInc;

            function frame() {
                left += leftInc;
                top = top + topInc;
                logo.style.left = Math.round(left) + 'px';
                logo.style.top = Math.round(top) + 'px';

                if (left > 2000) {
                    left = iniLeft;
                    top = iniTop;
                    leftInc = iniLeftInc;
                    topInc = iniTopInc;
                    clearInterval(timer);
                }
            }

            var timer = setInterval(frame, 20);
        }

        wait(Math.random() * 500000 + 20000);
   }
</script>
