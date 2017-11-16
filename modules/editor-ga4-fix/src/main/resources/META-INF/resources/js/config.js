;(function() {$
    AUI().applyConfig(
        {
             groups: {
                editorga4fix: {
                    base: MODULE_PATH + "/js/",
                    combine: Liferay.AUI.getCombine(),
                    filter: Liferay.AUI.getFilterConfig(),
                    modules: {
                        'editor-ga4-fix': {
                            path: 'input_localized_fixed.js',
                            condition: {
                                name: 'editor-ga4-fix',
                                trigger: 'liferay-input-localized',
                                when: 'instead'
                            }
                        }
                    },
                    root: MODULE_PATH + "/js/"
                }
            }
        }
    );
})();