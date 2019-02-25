<!-- diaporama photo -->

<link rel="stylesheet" type="text/css" href="/o/strasbourg-theme/css/slick.css">
<link rel="stylesheet" type="text/css" href="/o/strasbourg-theme/css/slick-theme.css">

<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${content.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <meta property="og:image" content="${themeDisplay.getPortalURL()}${image.getData()}" />
</@>
    <div>

        <h1>${title.getData()}</h1> 
        <#if photos.getSiblings()?has_content>
            <section class="slider-for slider">
                <#list photos.getSiblings() as photo> 
                    <div class="image-for" style="background-image:url('${photo.getData()}')">
                        <div class="legend">${photo.getChildren()[0].getData()}</div>
                        <div class="paginate">${photo?counter}/${photos.getSiblings()?size}</div>
                    </div> 
                </#list> 
            </section>
            
            <section class="slider-nav slider">
                <#list photos.getSiblings() as photo>  
                    <div>
                        <div class="image-nav" style="background-image:url('${photo.getData()}')"></div>
                    </div> 
                </#list>
            </section>
        </#if>
        <div class="seu-container">${content.getData()}</div>
    </div>

    <style>
        .slider {
            margin: 0 80px 40px 80px;
        }

        .slick-slide {
            transition: all ease-in-out .3s;
            opacity: .2;
        }

        .slick-active {
            opacity: .5;
        }

        .slick-current {
            opacity: 1;
        }

        .slick-slide > div{
            line-height: 0px;
        }

        .slider-for .slick-slide > div > div{
            height: calc(80vh - 105px);
            display: flex !important;
        }

        .slider-for{
            background-color: #31455d;
            margin-bottom: 0;
        }

        .image-for{
            background-repeat: no-repeat;
            background-position: center;
            background-size: contain;
        }

        .legend{
            position: absolute;
            bottom: -1px;
            max-height: 80px;
            height: 15vh ;
            background-color: rgba(255,255,255,0.6);
            width: 100%;
            padding: 0 70px;
            text-align: center;
            display: flex;
            justify-content: center;
            flex-direction: column;
            line-height: 1.5em;
            font-size: 0.8em;
            color: #333333;
        }

        .paginate{
            position: absolute;
            bottom: -1px;
            max-height: 80px;
            height: 15vh ;
            width: 70px;
            left: calc(100% - 70px);
            font-weight: 700;
            padding-right: 15px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: flex-end;
            color: #333333;
        }

        .slider-nav .slick-slide {
            margin: 0px 10px;
        }  

        .image-nav {
            width: 100%;
            height: 15vh;
            background-size: cover;
            background-position: center;
        }  

        .slider-une-thumbnail__arrow{
            position: absolute;
            top: calc(50% - 30px);
        }

        .slider-une-thumbnail__arrow--prev {
            left: 15px;
        }

        .slider-une-thumbnail__arrow--next {
            right: 15px;
        }

        @media only screen and (max-width: 767px){
            .slider {
                margin: 0;
            }

            .slider-for .slick-slide > div > div{
                height: calc(77vh - 45px);
            }
            

            .slider-nav .slick-slide {
                margin-bottom: 40px;
            } 

            .legend{
                padding: 0 40px 0 30px;
            }

            .paginate{
                width: 40px;
                left: calc(100% - 40px);
            }
            
            .image-nav {
                height: 20vh;
            }
        }
    
    </style>
    

<@liferay_util["html-bottom"]>
    <script type="text/javascript" src="/o/strasbourg-theme/js/slick.js"></script>
    <script type="text/javascript">
        $(document).on('ready', function() {
            $('.slider-for').slick({
                slidesToShow: 1,
                slidesToScroll: 1,
                prevArrow:'<button class="slider-une-thumbnail__arrow slider-une-thumbnail__arrow--prev">' +
                                '<div class="flexbox">' +
                                    '<div class="slider-une-thumbnail__arrow-icon"></div>' +
                                '</div>' +
                            '</button>',
                nextArrow:'<button class="slider-une-thumbnail__arrow slider-une-thumbnail__arrow--next">' +
                                '<div class="flexbox">' +
                                    '<div class="slider-une-thumbnail__arrow-icon"></div>' +
                                '</div>' +
                            '</button>',
                fade: true,
                mobileFirst: true,
                asNavFor: '.slider-nav'
            });
            $('.slider-nav').slick({
                slidesToShow: 5,
                slidesToScroll: 1,
                asNavFor: '.slider-for',
                dots: false,
                arrows: false,
                adaptiveHeight: false,
                focusOnSelect: true,
                swipeToSlide: true,
                draggable: true,
                centerMode: true,
                responsive: [
                    {
                        breakpoint: 1024,
                        settings: {
                            slidesToShow: 3
                        }
                    },
                    {
                        breakpoint: 768,
                        settings: {
                            slidesToShow: 3
                        }
                    },
                    {
                        breakpoint: 480,
                        settings: {
                            slidesToShow: 1
                        }
                    }
                ]
            });
        });

</script>
</@>