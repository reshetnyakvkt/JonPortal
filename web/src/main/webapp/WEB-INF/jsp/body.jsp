<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<div id="2" class="logo"></div>--%>

<div class="back">
    <div id="1" class="logo"></div>
</div>

<script>

    window.onload = function () {
        function wait(millis) {
            window.setTimeout(run, millis)
        }

        function run() {
            var logo1 = document.getElementById("1");
            logo1.style.display = 'block';
            move(-800, 0, 0.2, 0.1, logo1);
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
                    clearInterval(timer); // завершить анимацию
                }
            }

            var timer = setInterval(frame, 20);

        }

        wait(Math.random() * 500000 + 20000);
   }
</script>
