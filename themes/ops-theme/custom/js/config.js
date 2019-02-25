var thConfig = {

    tabletPortraitBreakpoint: 980,
    tabletPaysageBreakpoint: 1280,

    debug: true,

    // themeUrl: document.getElementById('mainscript').getAttribute('data-themeurl'),

    map: {

        init: {
            maps_class: '.maps:not(.no-autoload):not(.initialized)',
            tileLayerUrl: 'http://tile.jawg.io/jawg-sunny/{z}/{x}/{y}.png?access-token={accessToken}',
            accessToken: 'qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkki',
            tileLayerOptions: {
                attribution: '',
                maxZoom: 16,
                accessToken: 'qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkki'
            }
        },

        defaultThemes: {
            accessToken: 'qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkkiz',
            style: 'https://tile.jawg.io/jawg-streets/{z}/{x}/{y}.png?access-token=qyaPkIV5ppcShJ126kJHQEByPuhVf4K3CcVVFS9q50kGoOjR0oCIkTuSJhRNdkki',
            attribution: '',
        }

    },

};