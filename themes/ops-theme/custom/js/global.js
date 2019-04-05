function isTabletPortraitOrSmalller(){
    if(document.body.clientWidth < thConfig.tabletPortraitBreakpoint){
        return true;
    }
    return false;
}