<!-- diaporama photo -->
<link rel="stylesheet" type="text/css" href="/o/strasbourg-theme/css/slick.css">
<link rel="stylesheet" type="text/css" href="/o/strasbourg-theme/css/slick-theme.css">
<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign request = serviceContext.getRequest()/>

<#assign imageUrl = ""/>
<!-- image -->
<#if image.getData()?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + image.getData()?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${title.getData()?html}",
"og:description":'${content.getData()?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

    <div>

        <h1>${title.getData()}</h1> 
        <div class="seu-container">${content.getData()}</div>
        <#if photos.getSiblings()?has_content>
            <section class="slider-for slider">
                <#list photos.getSiblings() as photo> 
                    <div>
                        <div class="image-for" style="background-image:url('${photo.getData()}')"></div>
                        <div class="legend">
                            <div class="paginate"></div> 
                            <div class="label">${photo.getChildren()[0].getData()}</div>
                            <div class="paginate">${photo?counter}/${photos.getSiblings()?size}</div> 
                        </div>
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
    </div>

    <style>
        .seu-container{
            margin-bottom:40px;
        }
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

        .slider-for{
            margin-bottom: 0;
        }

        .image-for{
            min-height: 180px;
            height: calc(80vh - 105px);
            display: flex !important;
            background-repeat: no-repeat;
            background-position: center;
            background-size: contain;
        }

        .legend{
            display: flex;
            min-height: 45px;
            max-height: 80px;
            height: 15vh ;
            width: 100%;
            background-color: rgba(255,255,255,0.6);
            margin-left: 140px;
        }

        .legend .label{  
            display: flex;
            justify-content: center;
            flex-direction: column;
            padding: 0;
            text-align: center;
            line-height: 1.5em;
            font-size: 0.8em;
            font-weight: 400;
            color: #333333;
            border: none;
            width: calc(100% - 195px);
        }

        .legend .label + .paginate{
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            width: 50px;
            font-weight: 700;
            color: #333333;
        }

        .slider-nav .slick-slide {
            margin: 0px 10px;
            cursor: pointer;
        }  

        .image-nav {
            width: 100%;
            min-height: 60px;
            height: 15vh;
            background-size: cover;
            background-position: center;
        }  

        .slider-une-thumbnail__arrow{
            position: absolute;
            top: calc(100% - 70px);
            z-index: 1;
        }

        .slider-une-thumbnail__arrow--next {
            left: 70px;
        }

        .slider-une-thumbnail__arrow .flexbox{
            background-color: #FFFFFF;
            border: solid #31455d;
            transition: .3s;
            height: 60px;
            width: 60px;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .slider-une-thumbnail__arrow:hover .flexbox{
            background-color: #31455d;
            border-color: #FFFFFF;
        }

        .slider-une-thumbnail__arrow-icon{
            width: 27px;
            height: 22px;
            transition: .3s;
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%2331455d' viewBox='0 0 31.49 22.14'%3E%3Cpath d='M21.2.33A1.12,1.12,0,1,0,19.62,1.9l8,8H1.11A1.11,1.11,0,0,0,0,11.06a1.12,1.12,0,0,0,1.11,1.13H27.67l-8,8a1.14,1.14,0,0,0,0,1.59,1.11,1.11,0,0,0,1.59,0l10-10a1.09,1.09,0,0,0,0-1.57Z'/%3E%3C/svg%3E"); 
            background-position: center;
            background-size: contain;
            background-repeat: no-repeat;
        }

        .slider-une-thumbnail__arrow--prev .slider-une-thumbnail__arrow-icon{
            transform: rotate(-180deg);
        }

        .slider-une-thumbnail__arrow:hover .slider-une-thumbnail__arrow-icon{
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23FFFFFF' viewBox='0 0 31.49 22.14'%3E%3Cpath d='M21.2.33A1.12,1.12,0,1,0,19.62,1.9l8,8H1.11A1.11,1.11,0,0,0,0,11.06a1.12,1.12,0,0,0,1.11,1.13H27.67l-8,8a1.14,1.14,0,0,0,0,1.59,1.11,1.11,0,0,0,1.59,0l10-10a1.09,1.09,0,0,0,0-1.57Z'/%3E%3C/svg%3E"); 
        }

        @media only screen and (max-width: 767px){
            .slider {
                margin: 0;
            }

            .image-for{
                height: calc(65vh - 45px);
            }
            

            .slider-nav .slick-slide {
                margin-bottom: 40px;
            } 
            
            .image-nav {
                height: 20vh;
            }

            .legend{
                margin-left: 0;
            }

            .legend .label{  
                width: 100%;
            }

            .slider-une-thumbnail__arrow{
                display: none !important;
            }
        }
    
    </style>
    

<@liferay_util["html-bottom"]>
    <script type="text/javascript" src="/o/strasbourg-theme/js/slick.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
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