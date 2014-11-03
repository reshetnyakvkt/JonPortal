<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="1" class="logo"></div>
<%--<div id="2" class="logo"></div>--%>

<div class="back">
</div>

<script>

    window.onload = function () {
        function move(left, top, leftInc, topInc, logo) {

            var iniLeft = left; // начальное значение
            var iniTop = top; // начальное значение
            var iniTopInc = topInc;
            var iniLeftInc = leftInc;

            function frame() { // функция для отрисовки

                left += leftInc;
                top = top + topInc;
                logo.style.left = Math.round(left) + 'px';
                logo.style.top = Math.round(top) + 'px';

                if (left > 2000) {
                    left = iniLeft;
                    top = iniTop;
                    leftInc = iniLeftInc;
                    topInc = iniTopInc;
//                    clearInterval(timer); // завершить анимацию
                }
            }

            var timer = setInterval(frame, 20) // рисовать каждые 10мс

        }

        var logo1 = document.getElementById("1");
        var logo2 = document.getElementById("2");
        for (var i = 0; i < 10; i++) {
            move(-400, 200, 0.2, 0.1, logo1);
        }
//        move(500, 500, -0.6, -0.6, logo2);
        /*
         tl = new TimelineMax();

         tl.staggerTo("#logo", 1.5, {x:640, repeat:-1, repeatDelay:0.5, force3D:true, ease:SlowMo.ease.config(0.2, 0.5)}, 0.15)
         */

        /*
         document.lform.j_username.focus();
         var logo = document.getElementById("logo");
         var rnd = Math.round(Math.random() * 6);
         //        console.log("rnd = " + rnd);
         if (rnd == 0) {
         TweenLite.to(logo, 2, {left:"90%", ease:Bounce.easeOut});
         } else if (rnd == 1) {
         TweenLite.to(logo,2, {left:"90%", ease:Circ.easeIn});
         } else if (rnd == 2) {
         TweenLite.to(logo,2, {left:"90%", ease:Expo.easeInOut});
         } else if (rnd == 3) {
         TweenLite.to(logo,2, {left:"90%", ease:Power2.easeInOut});
         } else if (rnd == 4) {
         TweenLite.to(logo,2, {left:"90%", ease:Back.easeIn});
         } else if (rnd == 5) {
         TweenLite.to(logo,2, {left:"90%", ease:Bounce.easeOut});
         } else if (rnd == 6) {
         TweenLite.to(logo,2, {left:"90%", ease:Back.easeIn});
         }
         */
//        TweenLite.to(logo, 1, {width:100, ease:Power2.easeOut});

//        TweenLite.to(logo, 2, {height:10, ease:Elastic.easeOut});
//        TweenLite.to(logo, 2, {left:"500px", backgroundColor:"black", borderBottomColor:"#90e500", color:"white"});
    }
</script>
