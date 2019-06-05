function myIntervalFunction() {
	var x = document.querySelector("#textarea").value;
	console.log(x);

	if (x != vergelijk) {
		console.log(x);
		vergelijk = x;
	}

}

var interval = setInterval(myIntervalFunction, 5000);

var vergelijk = "";