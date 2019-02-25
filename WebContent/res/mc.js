function validate() {
	var ok = true;
	var principal = document.getElementById("principal").value;
	var interest = document.getElementById("interest").value;
	var period = document.getElementById("period").value;
	
	if (isNaN(principal) || principal <= 0) {
		document.getElementById("principalErr").style.display = "block"
		ok = false;
	} else {
		document.getElementById("principalErr").style.display = "none"
	}
	if (isNaN(interest) || interest <= 0 || interest >= 100) {
		document.getElementById("interestErr").style.display = "block"
		ok = false;
	} else {
		document.getElementById("interestErr").style.display = "none"
	}
	if (isNaN(period) || period <= 0) {
		document.getElementById("periodErr").style.display = "block"
		ok = false;
	} else {
		document.getElementById("periodErr").style.display = "none"
	}
	
	if (document.getElementById("studentNum") != null) {
		var studentNum = document.getElementById("studentNum").value;
		if (isNaN(studentNum) || studentNum <= 0 || studentNum > 999999999) {
			document.getElementById("studentErr").style.display = "block"
			ok = false;
		} else {
			document.getElementById("studentErr").style.display = "none"
		}
	}

	return ok;
}

function doSimpleAjax(address) {
	var request = new XMLHttpRequest();
	var data = "";
	data += "principal=" + document.getElementById("principal").value + "&";
	data += "interest=" + document.getElementById("interest").value + "&";
	data += "period=" + document.getElementById("period").value;
	var checked = document.getElementById("grace").checked;
	if (checked == true) {
		data += "&grace=true";
	}
	data += "&ajaxCalc=true";
	request.open("GET", (address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send();
}

function handler(request) {
	if (request.readyState == 4 && request.status == 200) {
		var target = document.getElementById("ajaxTarget")
		target.innerHTML = request.responseText;
	}
}