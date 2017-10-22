var TOKEN_KEY = "ECOMM_access_token";

var saveAccessToken=function(key, token) {
	document.cookie = key + '=' + token;
}

var loadAccessToken = function(key) {
	if (document.cookie) {
		var cookieKey = key + '=';
		var cookieArray = document.cookie.split(';');
		if (cookieArray) {
			for (var i=0,len=cookieArray.length; i<len; i++) {
				var cookieValue = cookieArray[i];
				if (cookieValue) {
					var idx = cookieValue.indexOf(cookieKey);
					if (idx > -1) {
						return cookieValue.substring(idx+cookieKey.length);
					}
				}
			}
		}
	}
	return "";
}

var deleteAccessToken = function(key) {
	var dt = new Date();
	dt.setMonth( dt.getMonth() - 12 ); // set date to 12 months ago
	document.cookie = key + '=; expires=' + dt.toUTCString() + ";" ;
}

var showPopupMessage = function(elemId, msg) {
	$("#"+elemId).text(msg);
	$("#"+elemId).popup("open");
}