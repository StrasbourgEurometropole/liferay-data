$(document).ready(function(){
    var request = new XMLHttpRequest();

    request.open('GET',
                '/api/jsonws/council.deliberation/get-user-front/official-id/22697306'
                , true);

    request.onload = function() {
      if (this.status >= 200 && this.status < 400) {
        // Success!
        var data = JSON.parse(this.response);
      } else {
        // We reached our target server, but it returned an error

      }
    };

    request.onerror = function() {
      // There was a connection error of some sort
    };

    request.send();
});