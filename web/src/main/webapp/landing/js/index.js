$(document).ready(function() {
    var SCRIPTS = function() {
        this.viewportWidth = function() {
            var e = window,
                a = 'inner';
            if (!('innerWidth' in window)) {
                a = 'client';
                e = document.documentElement || document.body;
            }
            return {
                width: e[a + 'Width'],
                height: e[a + 'Height']
            };
        };
        this.slickCarousel = function() {
            $('.companies-slides').slick({
                dots: false,
                speed: 300,
                slidesToShow: 4,
                slidesToScroll: 1,
                autoplay: true,
                infinite: true,
                autoplaySpeed: 2000,
                prevArrow: $('.slider-wrapper .trigger-left '),
                nextArrow: $('.slider-wrapper .trigger-right'),
                responsive: [{
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 1,
                        infinite: true,
                        dots: false
                    }
                }, {
                    breakpoint: 768,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 2
                    }
                }, {
                    breakpoint: 500,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1
                    }
                }]
            });
            $('.comments-slides ').slick({
                dots: false,
                speed: 1000,
                slidesToShow: 1,
                slidesToScroll: 1,
                autoplay: true,
                infinite: true,
                autoplaySpeed: 2000,
                prevArrow: $('.comments-slider-wrapper .trigger-left '),
                nextArrow: $('.comments-slider-wrapper .trigger-right'),
            });
        };
        this.magnificPopupInit = function() {
            $('.popup-gallery').magnificPopup({
                delegate: 'a',
                type: 'image',
                tLoading: 'Loading image #%curr%...',
                mainClass: 'mfp-img-mobile',
                gallery: {
                    enabled: true,
                    navigateByImgClick: true,
                    preload: [0, 1]
                },
                image: {
                }
            });
        };
        this.googleMap = function() {
            function initialize() {
                var position = {
                    lat: 50.447490,
                    lng: 30.465798
                };
                var mapCanvas = document.getElementById('map');
                var mapOptions = {
                    center: new google.maps.LatLng(position.lat + 0.0008, position.lng),
                    zoom: 15,
                    disableDefaultUI: true,
                    zoomControl: true,
                    zoomControlOptions: {
                        position: google.maps.ControlPosition.LEFT_BOTTOM
                    },
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    scrollwheel: false,
                    navigationControl: false,
                    mapTypeControl: false,
                    scaleControl: false,
                    styles: [{
                        "featureType": "all",
                        "elementType": "labels.text.fill",
                        "stylers": [{
                            "saturation": 36
                        }, {
                            "color": "#000000"
                        }, {
                            "lightness": 40
                        }]
                    }, {
                        "featureType": "all",
                        "elementType": "labels.text.stroke",
                        "stylers": [{
                            "visibility": "on"
                        }, {
                            "color": "#000000"
                        }, {
                            "lightness": 16
                        }]
                    }, {
                        "featureType": "all",
                        "elementType": "labels.icon",
                        "stylers": [{
                            "visibility": "off"
                        }]
                    }, {
                        "featureType": "administrative",
                        "elementType": "geometry.fill",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 20
                        }]
                    }, {
                        "featureType": "administrative",
                        "elementType": "geometry.stroke",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 17
                        }, {
                            "weight": 1.2
                        }]
                    }, {
                        "featureType": "landscape",
                        "elementType": "geometry",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 20
                        }]
                    }, {
                        "featureType": "poi",
                        "elementType": "geometry",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 21
                        }]
                    }, {
                        "featureType": "road.highway",
                        "elementType": "geometry.fill",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 17
                        }]
                    }, {
                        "featureType": "road.highway",
                        "elementType": "geometry.stroke",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 29
                        }, {
                            "weight": 0.2
                        }]
                    }, {
                        "featureType": "road.arterial",
                        "elementType": "geometry",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 18
                        }]
                    }, {
                        "featureType": "road.local",
                        "elementType": "geometry",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 16
                        }]
                    }, {
                        "featureType": "transit",
                        "elementType": "geometry",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 19
                        }]
                    }, {
                        "featureType": "water",
                        "elementType": "geometry",
                        "stylers": [{
                            "color": "#000000"
                        }, {
                            "lightness": 17
                        }]
                    }]
                };
                var map = new google.maps.Map(mapCanvas, mapOptions)
                var marker = new google.maps.Marker({
                    position: position,
                    map: map,
                    title: 'Company-name'
                });
                var url = window.location.hostname;
                marker.setIcon('./landing/img/google-map-marker.png');
            }
            initialize();
        };
        this.stickyMenu = function() {
            var menu = $('.menu');
            var menuOverflowTop = $('.menu').offset().top;
            $(window).on('scroll', function() {
                if ($(window).scrollTop() >= menuOverflowTop) {
                    if (!menu.hasClass('fixed')) {
                        menu.toggleClass('fixed');
                    }
                } else {
                    if (menu.hasClass('fixed')) {
                        menu.toggleClass('fixed');
                    }
                }
            });
        };
        this.setAnchors = function() {
            $('a[href^="#"]:not(".open-popup-link"), a[href^="."]:not(".open-popup-link")').click(function() {
                $('.menu li.active').removeClass('active');
                $(this).closest('li').addClass('active');
                var scroll_el = $(this).attr('href');
                if ($(scroll_el).length !== 0) {
                    $('html, body').animate({
                        scrollTop: $(scroll_el).offset().top - 20
                    }, 500);
                }
                return false;
            });
        };
        this.parallax = function() {
            var parallaxBlock = $('.parallax-js'),
                parallaxItems = parallaxBlock.find('.parallax-item').toArray(),
                parallaxItemsLength = parallaxItems.length,
                windowWidth = $(window).width(),
                windowHeight = $(window).height(),
                scrollSpeed = 30;
            var tempScrollTop = 0;
            var currentScrollTop = 0;
            var tempScrollTopTime = 0;
            var currentScrollTopTime = 0;
            var way = "default";
            $(window).on('scroll', function(event) {
                currentScrollTop = $(window).scrollTop();
                currentScrollTopTime = event.timeStamp;
                if (tempScrollTop < currentScrollTop) {
                    way = "down";
                } else if (tempScrollTop > currentScrollTop) {
                    way = "up";
                }
                tempScrollTop = currentScrollTop;
                tempScrollTopTime = currentScrollTopTime;
                if ($('.parallax-js:in-viewport').length > 0) {
                    var elem = $('.parallax-js:in-viewport');
                    (function() {
                        if (way == "up") {
                            for (var i = 0; i < parallaxItemsLength; i++) {
                                var elementCustomSpeed = $(parallaxItems[i]).attr('speed') || 0;
                                var temp = $(parallaxItems[i]).position().top;
                                $(parallaxItems[i]).css({
                                    top: temp - scrollSpeed / 2 * elementCustomSpeed
                                })
                            }
                        } else {
                            for (var i = 0; i < parallaxItemsLength; i++) {
                                var elementCustomSpeed = $(parallaxItems[i]).attr('speed') || 0;
                                var temp = $(parallaxItems[i]).position().top;
                                $(parallaxItems[i]).css({
                                    top: temp + scrollSpeed / 2 * elementCustomSpeed
                                })
                            }
                        }
                    })();
                }
            })
        };
        this.upArrow = function() {
            var arrow = $('.up-arrow'),
                pageCenter = Math.round($(document).height() / 2);
            arrow.mouseover(function() {
                $(this).attr('src', '/landing/img/up-arrow-hover.png');
            });
            arrow.mouseout(function() {
                $(this).attr('src', '/landing/img/up-arrow.png');
            });
            $(window).on('scroll', function() {
                if ($(window).scrollTop() >= pageCenter) {
                    if (!arrow.hasClass('visible')) {
                        arrow.toggleClass('visible');
                    }
                } else {
                    if (arrow.hasClass('visible')) {
                        arrow.toggleClass('visible');
                    }
                }
            });
        };
        this.popups = function() {
            $('.open-popup-link').magnificPopup({
                type: 'inline',
                midClick: true,
                callbacks: {
                    beforeOpen: function() {}
                }
            });
        };
        this.selectBox = function() {
            $('select').select2();
        };
        this.validation = function() {
            $.validator.addClassRules("name", {
                required: true
            });
            $.validator.addClassRules("email", {
                required: true
            });
            $.validator.addClassRules("phone", {
                required: true
            });
            $("form").each(function() {
                $(this).validate({
                    errorPlacement: function(error, element) {}
                });
            });
        };
        this.formSubmission = function() {
            $("form").each(function() {
                $(this).find('.submitForm').on('click', function() {
                    var targetForm = $(this).closest('form');
                    if (targetForm.valid()) {
                        var data = targetForm.serialize();
                        $.ajax({
                            type: "POST",
                            url: "contact.php",
                            data: data
                        }).done(function() {
                            var magnificPopup = $.magnificPopup.instance;
                            magnificPopup.close();
                            setTimeout(function() {
                                $("#callThanksPopup").trigger('click');
                            }, 1000);
                            targetForm[0].reset();
                        });
                    }
                });
            });
        };
        this.responsiveMenu = function() {
            $('.show-adaptive-menu').on('click', function() {
                $('.menu').toggleClass('visible');
                $(this).find('.hamburger-icon').toggleClass('transform-to-close');
            });
            $('.menu a').on('click', function() {
                if ($('.menu').hasClass('visible')) {
                    $('.menu').removeClass('visible');
                    $('.hamburger-icon').removeClass('transform-to-close');
                }
            });
        };
        this.onScrollMenuItems = function() {
            $(document).on("scroll", onScroll);

            function onScroll(event) {
                var scrollPos = $(document).scrollTop();
                $('.menu li.anchor').each(function() {
                    var currLink = $(this).find('a');
                    var refElement = $(currLink.attr("href"));
                    if (refElement.position().top - 40 <= scrollPos && refElement.position().top + refElement.height() > scrollPos) {
                        $('.menu li').removeClass("active");
                        currLink.closest('li').addClass("active");
                    } else {
                        currLink.closest('li').removeClass("active");
                    }
                });
            }
        };
        this.animations = function() {
            if (this.viewportWidth().width >= 992) {
                $('.header .title').addClass("hideElement");
                $('.header h1').addClass("hideElement");
                $('.discount .table .cell').addClass('hideElement');
                setTimeout(function() {
                    $('.header .title:in-viewport').addClass('showElement animated fadeInDown');
                    setTimeout(function() {
                        $('.header h1:in-viewport').addClass('showElement animated fadeInDown');
                    }, 500);
                }, 1000);
                $(window).on('scroll', function() {
                    $('.section-title').addClass("hideElement");
                    $('.section-title:in-viewport').addClass("showElement animated fadeInDown");
                    var offset = 2000;
                    $('.discount .table .cell').each(function() {
                        var temp = $(this);
                        setTimeout(function() {
                            temp.viewportChecker({
                                classToAdd: 'showElement animated pulse',
                                offset: 100
                            });
                        }, offset);
                        offset += 200;
                    });
                    $('.schedule .equipment .cell:first-child').addClass("hideElement");
                    $('.schedule .equipment .cell:first-child:in-viewport').addClass("showElement animated fadeInDown");
                    $('.certificate .overlay').addClass("hideElement");
                    $('.certificate .overlay:in-viewport').addClass("showElement animated lightSpeedIn");
                    $('.process .overlay').addClass("hideElement");
                    $('.process .overlay:in-viewport').addClass("showElement animated slideInLeft");
                    $('.comments .footer img').addClass("hideElement");
                    $('.comments .footer img:in-viewport').addClass("showElement animated slideInLeft");
                    $('.support .overlay').addClass("hideElement");
                    $('.support .overlay:in-viewport').addClass("showElement animated zoomIn");
                });
            }
        };
        this.init = function() {
            this.slickCarousel();
            this.magnificPopupInit();
            this.googleMap();
            this.stickyMenu();
            this.setAnchors();
            this.parallax();
            this.upArrow();
            this.popups();
            this.selectBox();
            this.validation();
            this.formSubmission();
            this.responsiveMenu();
            this.onScrollMenuItems();
            this.animations();
        }
    };
    var scripts = new SCRIPTS();
    scripts.init();
    if (scripts.viewportWidth().width < 768) {
        $('.header video').remove();
    }
});