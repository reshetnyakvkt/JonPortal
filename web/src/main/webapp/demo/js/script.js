;(function($){
    $.fn.tooltip = function(text, css){

    }
})(jQuery)

;
(function ($) {

    var sleep = 800;

    var selection = "<div class='selection'></div>";
    var savedItem = "<div class='item'><div>X</div></div>";

    var container = 'div#resizable';

    var counter = 0;
    var play = false;
    var isStop = false;
    var pause = false;
    //fignja
    var currentSwap = -1;

    //еще нельзя менять, будет фигня
    var size = 50;
    var itemMargin = 10;
    var topDown = 60;

    $(document).ready(function () {
        prepare();
    });

    var stepFunction = null;

    function getStepSet(type) {
        var path = actions[type](getData());
        var idx = 0;
        return function (cb) {
            if (idx < path.length && !isStop) {
                step(idx, path, cb);
                setStep(idx, path.length);
                idx++;
                return true;
            } else {
                stop();
                stepFunction = null;
                return false;
            }
        };
    }

    function prepare() {
        add(10);

        var resizeFunction = function (width) {
            var innerWidth = $('.inner').width();
            var itemLength = $('div#wrapper div#container div.inner div.item').length;
            var max = $('div#resizable').parent().width();
            //if (width > (size + itemMargin) * 2 + 30 - itemMargin && width <= (size + itemMargin) * 20 + itemMargin * 3) {
            if (width > (size + itemMargin) * 2 + 30 - itemMargin && width <= max) {
                if (innerWidth < itemLength * (size + itemMargin) - itemMargin) {
                    removeLast();
                }
                if (innerWidth >= itemLength * (size + itemMargin) + size) {
                    add(1);
                }
                return true;
            }
            return false;
        };

        $(window).resize(function(event){
            var width = $(container).parent().width();

            if(width < $(container).width()){
                if(resizeFunction(width)){
                    $(container).width(width);
                }
            }
        });

        initResizer(container, resizeFunction);

        $('button[value="random"]').click(function () {
            if (!play && !pause) {
                randomize();
            }
        });

        $('button[value="sort"]').click(function () {
            if (!play) {
                pause = false;
                prepareForPlay();
                start($('select#type option:selected').attr('id'));
            }
        });

        $('button[value="stop"]').click(function () {
            isStop = true;
            if(pause) stop();
            pause = false;
            stepFunction = null;
        });

        $('button[value="pause"]').click(function () {
            pause = true;
            play = false;

        });

        $('input[name="speed"]').change(function () {
            sleep = 1200 - $(this).val();
        });

        $('button[value="ord"]').click(function () {
            if (!play && !pause) {
                randomOrdered(parseInt(($('div#wrapper div#container div.inner').width() + itemMargin + size / 2) / (size + itemMargin)));
            }
        });

        $('button[value="save"]').click(function () {
            if (!play) {
                save();
            }
        });

        $('button[value="step"]').click(function () {
            if (!play) {
                if (stepFunction == null) {
                    stepFunction = stepStart($('select#type option:selected').attr('id'));
                }
                if (!stepFunction()) {
                    stop();
                    stepFunction = null;
                }
            }
        });
    }

    function initMove(obj) {
        var drag = false;
        var x;
        var y;
        var initialIdx;


        $(obj).mousedown(function (e) {
            if (play || pause) return;
            drag = true;
            $(this).addClass('dragged');
            var offset = $(this).offset();
            x = e.pageX - offset.left;
            y = e.pageY - offset.top;
            initialIdx = $(this).index();
            $(this).css({'z-index': 400});
            $('div#resizable div#outer').css({'overflow': 'visible'});
        }).mouseup(function () {
            drag = false;
            $(this).removeClass('dragged');

            //trash
            $('#trash div.trash-box').removeClass('in');

            $(this).animate({'left': $(this).index() * (size + itemMargin) + 'px', 'top': '0px'}, function () {
                $(this).css({'z-index': 100});
                $('div#resizable div#outer').css({'overflow': 'hidden'});
            });
        });
        $('body').mousemove(function (e) {
            if (drag && $(obj).hasClass('dragged')) {
                var parentOffset = $(obj).parent().offset();
                var relX = e.pageX - parentOffset.left;
                var relY = e.pageY - parentOffset.top;
                var left = relX - x;

                $(obj).css({'left': left + 'px', 'top': (relY - y) + 'px'});

                //trash
                var trash = $('#trash').offset();
                if(e.pageX > trash.left && e.pageY > trash.top){
                    $('#trash div.trash-box').addClass('in');

                } else {
                    $('#trash div.trash-box').removeClass('in');
                }

                var length = $('div#resizable div#outer div#wrapper div#container div.item').length;
                if (left > (initialIdx + 1) * (size + itemMargin) - size / 2 && initialIdx < length - 1) {
                    //console.log("right");

                    moveTo(initialIdx + 1, initialIdx);
                    //setSelection('pl', initialIdx + 1, 'green');
                    swapInDOMbyIdx(initialIdx, initialIdx + 1);

                    initialIdx++;
                } else if (left < (initialIdx - 1) * (size + itemMargin) + size / 2 && initialIdx > 0) {
                    moveTo(initialIdx - 1, initialIdx);
                    //setSelection('pl', initialIdx - 1, 'green');
                    swapInDOMbyIdx(initialIdx, initialIdx - 1);
                    initialIdx--;
                }
            }
        });
    }

    function moveTo(idxFrom, idxTo) {
        var obj = $('div#wrapper div#container div.inner .item:nth-child(' + (idxFrom + 1) + ')');
        $(obj).animate({'left': idxTo * (size + itemMargin) + 'px'});
    }

    function prepareForPlay(callback) {
        play = true;
        $('div#saved div.block div.item').addClass('blocked');
        $('div#resizable div.resizer').fadeOut(callback);
    }

    function prepareForStop() {
        $('div#resizable div.resizer').fadeIn();
        $('div#saved div.block div.item').removeClass('blocked');
        play = false;
    }

    function start(type) {
        //var path = actions[type](getData());
        //var idx = 0;
        //$('div.plashka').fadeIn();
        //var cb = function () {
        //    if (idx < path.length && !isStop) {
        //        step(idx, path, cb);
        //        setStep(idx, path.length);
        //        idx++;
        //    } else {
        //        stop();
        //    }
        //};
        //cb();
        $('div.plashka').fadeIn();
        var func = stepFunction == null ? getStepSet(type) : stepFunction;
        if(stepFunction == null){
            stepFunction = getStepSet(type);
            func = stepFunction;
        }
        var cb = function () {
            if(!pause){
                func(cb);
            }
        }
        cb();


    }

    function stepStart(type){
        $('div.plashka').fadeIn();
        return getStepSet(type);
    }

    function setSwap(swap) {
        currentSwap = swap;
        $('div.plashka').html("swap: " + currentSwap);
    }

    function step(idx, path, callback) {
        var data = path[idx];
        var s1 = $.Deferred();
        var s2 = $.Deferred();
        var s3 = $.Deferred();

        setSelection('main', data.globalIdx, 'black', s1.resolve);
        setSelection('second', data.left, 'yellow', s2.resolve);
        setSelection('third', data.right, 'yellow', s3.resolve);

        $.when(s1, s2, s3).done(function () {
                if (data.swap && data.left !== data.right) {
                    //$('div#wrapper div#container div.inner .selection#third').css({'color': 'red','z-index' : 90});
                    //$('div#wrapper div#container div.inner .selection#second').css({'color': 'red','z-index' : 90});
                    //swap(data.left, data.right, callback);
                    $('div#wrapper div#container div.inner .selection#third').css({'color': 'red', 'z-index': 90});
                    $('div#wrapper div#container div.inner .selection#second').css({'color': 'red', 'z-index': 90});

                    swap(data.left, data.right, function () {
                        //setSwap(currentSwap + 1);
                        $('div#wrapper div#container div.inner .selection#third').css({
                            'color': 'yellow',
                            'z-index': 40
                        });
                        $('div#wrapper div#container div.inner .selection#second').css({
                            'color': 'yellow',
                            'z-index': 40
                        });
                        if (callback) callback();
                    });
                } else {
                    //if(path[idx + 1] && path[idx + 1].swap){
                    //    $('div#wrapper div#container div.inner .selection#third').css({'color': 'red','z-index' : 90});
                    //    $('div#wrapper div#container div.inner .selection#second').css({'color': 'red','z-index' : 90});
                    //} else {
                    //    $('div#wrapper div#container div.inner .selection#third').css({'color': 'green','z-index' : 40});
                    //    $('div#wrapper div#container div.inner .selection#second').css({'color': 'green','z-index' : 40});
                    //}
                    $('div#wrapper div#container div.inner .selection#third').css({'color': 'green', 'z-index': 40});
                    $('div#wrapper div#container div.inner .selection#second').css({'color': 'green', 'z-index': 40});

                    if (callback) setTimeout(callback, sleep / 2);
                }
            }
        );


    }

    function stop() {
        $('div#wrapper div#container div.inner div.selection').fadeOut(function () {
            $('div#wrapper div#container div.inner div.selection').remove();
            $('div.plashka').fadeOut();
        });
        prepareForStop();
        isStop = false;
    }

    function swap(idx1, idx2, callback) {

        var left = $('div#wrapper div#container div.inner .item:nth-child(' + (idx1 + 1) + ')');
        var right = $('div#wrapper div#container div.inner .item:nth-child(' + (idx2 + 1) + ')');

        swapInDOM(left, right);

        var upDown = sleep / 3;
        var side = sleep / 2;

        $(left).animate({'top': '-' + topDown + 'px'}, upDown)
            .animate({'left': idx2 * (size + itemMargin) + 'px'}, side)
            .animate({'top': '0'}, upDown);

        $(right).animate({'top': topDown + 'px'}, upDown)
            .animate({'left': idx1 * (size + itemMargin) + 'px'}, side)
            .animate({'top': '0'}, upDown, function () {
                setTimeout(callback, sleep / 4);
            });

        //setTimeout(function () {
        //    $(left).animate({'top': '-' + topDown + 'px'}, upDown)
        //        .animate({'left': idx2 * (size + itemMargin) + 'px'}, side)
        //        .animate({'top': '0'}, upDown);
        //
        //    $(right).animate({'top': topDown + 'px'}, upDown)
        //        .animate({'left': idx1 * (size + itemMargin) + 'px'}, side)
        //        .animate({'top': '0'}, upDown, function () {
        //            setTimeout(callback, sleep / 4);
        //        });
        //}, sleep / 4);
    }

    function swapInDOM(left, right) {
        var stub = "<div id='stub'></div>";

        $(left).after($(stub).addClass('l')).detach();
        $(right).after($(stub).addClass('r')).detach();


        $('div#wrapper div#container div.inner #stub.l').after(right);
        $('div#wrapper div#container div.inner #stub.r').after(left);
        $('div#wrapper div#container div.inner #stub.l, div#wrapper div#container div.inner #stub.r').remove();
    }

    function swapInDOMbyIdx(idx1, idx2) {
        var left = $('div#wrapper div#container div.inner .item:nth-child(' + (idx1 + 1) + ')');
        var right = $('div#wrapper div#container div.inner .item:nth-child(' + (idx2 + 1) + ')');
        swapInDOM(left, right);
    }

    function initResizer(parent, dragFunction) {
        var drag = false;
        var cause;
        $(parent + ' .resizer').mousedown(function (event) {
            drag = true;
            $(this).addClass('act');

            $('body').css({'cursor': 'col-resize'});
            cause = event.target;
        });

        $('body').mouseup(function () {
            drag = false;
            $(parent + ' .resizer').removeClass('act');
            $('body').css({'cursor': 'initial'});
        }).mousemove(function (event) {
            if (drag) {
                var offset =$(parent).parent().offset();
                //var width = $(parent).parent().width() - (event.pageX - offset.left) * 2;

                var x = event.pageX - $(parent).parent().offset().left;
                //console.log(x);
                var width = $(parent).parent().width() - ((x - 19) * 2);
                //console.log(width);
                //if (dragFunction(cause, event, width)) {
                if (dragFunction(width)) {
                    $(cause).parent().width(width);
                }
            }
        });
    }

    function removeLast() {
        var div = $('div#wrapper div#container div.inner div.item:last-child').detach();
        $('div#wrapper div#container div.deleted').append(div);
        var left = $(div).css('left');
        setCounter(counter - 1);
        $(div).animate({'left': (parseInt(left) + 100) + 'px'}, function () {
            $(this).remove();
        });
    }

    function getData() {
        var array = $('div#wrapper div#container div.inner .item');
        var result = [];
        //for(var i = 0; i < array.length; i++){
        //    result[i] = parseInt($(array[i]).html());
        //}
        $(array).each(function (idx, elem) {
            result[idx] = parseInt($(elem).html());
        });
        //console.log(result);
        return result;
    }

    function setSelection(id, idx, color, callback) {
        if (!$('div#wrapper div#container div.inner div').is('.selection#' + id)) {
            $('div#wrapper div#container div.inner').append($(selection).attr({'id': id, idx: idx}).css({
                'color': color,
                'left': (idx * (size + itemMargin)) + 'px'
            }));
            if (callback) setTimeout(callback, sleep / 2);
        } else {
            var div = $('div#wrapper div#container div.inner div.selection#' + id);
            if ($(div).attr('idx') != idx) {
                $(div).attr('idx', idx)
                    .css({'color': color})
                    .animate({'left': idx * (size + itemMargin) + 'px'}, sleep / 2, callback);
            } else if (callback) {

                callback();
            }


        }
    }

    function clear(callback) {
        var items = $('div#wrapper div#container div.inner div.item');
        if (items.length == 0 && callback) callback();
        for (var i = 0; i < items.length; i++) {
            (function (c) {
                $(items[i]).animate({'left': '-' + ((items.length - i) * (size + itemMargin) + 15) + 'px'}, 600, function () {
                    $(this).remove();
                    setCounter(counter - 1);
                    if (c == items.length - 1 && callback) {
                        callback();
                    }
                });
            })(i);
        }
    }

    function save() {
        var data = getData();
        var fullText = data.toString();
        var text = fullText;
        if (fullText.length > 40) {
            text = fullText.substring(0, 40) + "...";
        }
        //console.log(text);
        var b = $(savedItem).prepend(text).attr('title', fullText).click(function () {
            if (!play && !pause) {
                $(b).addClass('pressed');
                $('div#saved div.block div.item').addClass('blocked');
                replace(data, function () {
                    $('div#saved div.block div.item').removeClass('pressed').removeClass('blocked');
                });
            }
        });

        $(b).find('div').click(function (e) {
            e.stopPropagation();
            $(this).parent().slideUp(function () {
                $(this).remove();
            });
        });

        $('div#saved div.block').prepend(b);
        $(b).slideDown();
    }

    function add(count, callback, value) {
        var range = $('input[name="range"]').val();
        var currentOff = $('div#wrapper div#container div.inner div.item').length * (size + itemMargin);

        count = value ? value.length : count;
        for (var i = 0; i < count; i++) {
            var rnd = value ? value[i] : parseInt(Math.random() * range);
            var div = '<div class="item" title="drag-and-drop" style="left:' + (currentOff + i * (size + itemMargin) + (60 * (count))) + 'px">' + rnd + '</div>';

            $('div#wrapper div#container div.inner').append(div);
            initMove($('div#wrapper div#container div.inner div.item:last-child'));
            $('div#wrapper div#container div.inner div.item:last-child').animate({'left': (currentOff + i * (size + itemMargin)) + 'px'}, 100 * count, callback);
            setCounter(counter + 1);
        }
        //initMove($('div#wrapper div#container div.inner div.item'));
    }

    function randomize() {
        prepareForPlay(function () {
            clear(function () {
                setCounter(0);
                var count = parseInt(($('div#wrapper div#container div.inner').width() + itemMargin + size / 2) / (size + itemMargin));
                add(count, function () {
                    prepareForStop();
                });
            });
        });
    }

    function setStep(step, all) {
        $('div.plashka').html(step + "/" + all);
        //$('div.plashka').html("swap : " + step);
    }

    function setCounter(c) {
        counter = c;
        $('.count').html(counter);
    }

    function swapInArray(data, i, j) {
        var tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    //sort

    var defaultCmp = function (a, b) {
        return a > b ? 1 : a == b ? 0 : -1;
    };

    var actions = {
        bubble: function (data, cmp) {
            var array = [];
            var inArray = 0;

            if (!cmp) cmp = defaultCmp;

            var ch = true;
            for (var i = data.length; i > 0 && ch; i--) {
                ch = false;
                for (var j = 0; j < i - 1; j++) {
                    array[inArray++] = new Result(j, j, j + 1, false);
                    if (cmp(data[j], data[j + 1]) > 0) {
                        swapInArray(data, j, j + 1);
                        array[inArray++] = new Result(j, j, j + 1, true);
                        ch = true;
                    }
                }
            }
            return array;
        },

        insert: function (data, cmp) {
            var array = [];
            var inArray = 0;
            if (!cmp) cmp = defaultCmp;
            for (var i = 1; i < data.length; i++) {

                for (var j = i; j > 0; j--) {

                    array[inArray++] = new Result(i, j, j - 1, false);

                    if (cmp(data[j], data[j - 1]) < 0) {
                        array[inArray++] = new Result(i, j, j - 1, true);
                        swapInArray(data, j, j - 1);
                    } else {
                        break;
                    }
                }

            }
            return array;
        },

        shell: function (data, cmp) {
            var array = [];
            var inArray = 0;
            var step = parseInt(data.length / 2);
            if (!cmp) cmp = defaultCmp;
            while (step > 0) {
                for (var i = 0; i < data.length - step; i++) {

                    var j = i;

                    while (j >= 0) {
                        array[inArray++] = new Result(i, j, j + step, false);
                        if (cmp(data[j], data[j + step]) > 0) {
                            //obj.swap = true;
                            array[inArray++] = new Result(i, j, j + step, true);
                            swapInArray(data, j, j + step);
                        } else {
                            break;
                        }
                        j -= step;
                    }

                }
                step = parseInt(step / 2);
            }

            return array;
        },

        select: function (data, cmp) {
            var array = [];
            var inArray = 0;
            if (!cmp) cmp = defaultCmp;
            for (var i = 0; i < data.length; i++) {

                var min = i;

                for (var j = i; j < data.length; j++) {
                    if (cmp(data[j], data[min]) < 0) {
                        min = j;
                    }
                    array[inArray++] = new Result(j, i, min, false);
                }
                swapInArray(data, i, min);
                array[inArray++] = new Result(j - 1, i, min, true);
            }
            return array;
        },

        revers: function (data) {
            var array = [];
            var inArray = 0;

            for (var i = 0; i < data.length / 2; i++) {
                var j = data.length - i - 1;
                array[inArray++] = new Result(i, i, j, false);
                swapInArray(data, i, j);
                array[inArray++] = new Result(i, i, j, true);
            }
            return array;
        },

        qSort: function (data, cmp) {
            var array = [];
            var inArray = 0;
            if (!cmp) cmp = defaultCmp;
            var sort = function (left, right) {
                var l = left;
                var r = right;
                var pivotIdx = parseInt((left + right) / 2);
                var pivot = data[pivotIdx];
                //array[inArray++] = new Result(pivotIdx, l, l, r, false);
                while (l <= r) {
                    //array[inArray++] = new Result(pivotIdx, l, l, r, false);
                    while (cmp(data[l], pivot) < 0) {
                        array[inArray++] = new Result(pivotIdx, l, r, false);
                        l++;
                    }

                    while (cmp(data[r], pivot) > 0 && l <= r) {
                        array[inArray++] = new Result(pivotIdx, l, r, false);
                        r--;
                    }
                    //array[inArray++] = new Result(pivotIdx, l, l, r, data[l] != data[r]);
                    if (l <= r) {

                        if (data[l] != data[r]) {
                            array[inArray++] = new Result(pivotIdx, l, r, false);
                        }
                        if (l == pivotIdx) {
                            //console.log("l");
                            pivotIdx = r;
                        } else if (r == pivotIdx) {
                            //console.log("r");
                            pivotIdx = l;
                        }
                        array[inArray++] = new Result(pivotIdx, l, r, data[l] != data[r]);
                        swapInArray(data, l, r);
                        l++;
                        r--;


                    }
                }
                if (left < r) {
                    sort(left, r);
                }
                if (l < right) {
                    sort(l, right);
                }
            };
            sort(0, data.length - 1);
            return array;
        },

        swapH: function (data) {
            var array = [];
            var inArray = 0;

            for (var i = 0; i < parseInt(data.length / 2); i++) {
                var l = data.length % 2 == 0 ? i : parseInt(data.length / 2);

                var r = i + parseInt((data.length + 1) / 2);
                array[inArray++] = new Result(l, i, r, false);
                swapInArray(data, i, r);
                array[inArray++] = new Result(l, i, r, true);

            }
            console.log(data);
            return array;
        },

        combSort : function (data, cmp){
            var array = [];
            var inArray = 0;
            if (!cmp) cmp = defaultCmp;
            var gap = data.length;
            var swap = true;
            while(swap || gap > 1){
                swap = false;
                if(gap > 1) gap = parseInt(gap / 1.247330950103979);

                for(var i = 0; i < data.length - gap; i++){
                    array[inArray++] = new Result(i, i, i + gap, false);
                    if(cmp(data[i], data[i + gap]) > 0){
                        array[inArray++] = new Result(i, i, i + gap, true);
                        swapInArray(data, i, i + gap);
                        swap = true;
                    }
                }
            }

            return array;


        },

        shaker : function(data, cmp){
            var array = [];
            var inArray = 0;
            if (!cmp) cmp = defaultCmp;
            var l = 0;
            var r = data.length - 1;
            var swap = true;
            while(l < r && swap){
                swap = false;
                for(var i = l; i < r; i++){
                    array[inArray++] = new Result(i, i, i + 1, false);
                    if(cmp(data[i], data[i + 1]) > 0){
                        array[inArray++] = new Result(i, i, i + 1, true);
                        swapInArray(data, i, i + 1);
                        swap = true;
                    }
                }
                r--;
                for(var j = r; j > l; j--){
                    array[inArray++] = new Result(j, j - 1, j, false);
                    if(cmp(data[j], data[j - 1]) < 0){
                        array[inArray++] = new Result(j, j - 1, j, true);
                        swapInArray(data, j, j - 1);
                        swap = true;
                    }
                }
                l++;
            }
            //console.log(data);
            return array;
        },

        chet : function (data, cmp){
            var array = [];
            var inArray = 0;
            if (!cmp) cmp = defaultCmp;

            var swap = true;

            while(swap){
                swap = false;
                for(var i = 0; i < data.length - 1; i += 2){
                    array[inArray++] = new Result(i, i, i + 1, false);
                    if(cmp(data[i], data[i + 1]) > 0){
                        array[inArray++] = new Result(i, i, i + 1, true);
                        swapInArray(data, i, i + 1);
                        swap = true;
                    }
                }
                for(var j = 1; j < data.length - 1; j += 2){
                    array[inArray++] = new Result(j, j, j + 1, false);
                    if(cmp(data[j], data[j + 1]) > 0){
                        array[inArray++] = new Result(j, j, j + 1, true);
                        swapInArray(data, j, j + 1);
                        swap = true;
                    }
                }
            }
            return array;
        },

        stupid : function (data, cmp){
            var array = [];
            var inArray = 0;
            if (!cmp) cmp = defaultCmp;

            var swap = true;
            while(swap){
                swap = false;
                for(var i = 0; i < data.length - 1 && !swap; i++){
                    array[inArray++] = new Result(i, i, i + 1, false);
                    if(cmp(data[i], data[i + 1]) > 0){
                        array[inArray++] = new Result(i, i, i + 1, true);
                        swapInArray(data, i, i + 1);
                        swap = true;
                    }
                }
            }
            return array;

        }

    };

    function Result(globalIdx, left, right, swap) {
        this.globalIdx = globalIdx;
        this.left = left;
        this.right = right;
        this.swap = swap;
    }

    function replace(array, callback) {
        prepareForPlay(function () {
            clear(function () {
                setWidth(array.length, function () {
                    add(null, function () {
                        prepareForStop();
                        if (callback) callback();
                    }, array);
                });
            });
        });
    }

    function setWidth(count, callback) {
        var width = count * (size + itemMargin) - itemMargin + 30;
        $('div#resizable').animate({'width': width}, 500, callback);
    }

    function randomOrdered(count) {
        var range = $('input[name="range"]').val();
        var array = [];
        for (var i = 0; i < count; i++) {
            array[i] = parseInt(Math.random() * range);
        }
        actions.shell(array);
        replace(array);


    }

})(jQuery);