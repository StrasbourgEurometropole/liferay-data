window['debugCustom'] = false;

/**
 * Shortcut function for console.log/.info/.warn/.error
 * @function cl
 * @param  {string|var} p {param to console[fct]}
 * @param  {string} [fct=log] {console[fct] to call}
 * @return {none}
 */
function cl(p, fct){
    if(p == undefined || !debugCustom){return false;}
    if(fct == undefined){fct = 'log';}
    if(console && (fct && console[fct])){
        console[fct](p);
    }
}