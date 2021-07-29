function updateCheck() {

	eMassage = "";

	eMassage = lengthCheck("取引先名",form.cust_name,30).concat(
				surroCheck("取引先名",form.cust_name),
				vanWordCheck("取引先名",form.cust_name),
				lengthCheck("URL",form.url,120),
				surroCheck("URL",form.url),
				lengthCheck("支払サイト",form.payment_site,2),
				surroCheck("支払サイト",form.payment_site),
				vanWordCheck("支払サイト",form.payment_site),
				isNumHan("支払サイト",form.payment_site));

	eMassage = eMassage.replace(/( |　|undefined)+/g, "");
	if(eMassage == ""){
		inputUpdate();
	}
	else{
		window.alert(eMassage);
	}

};

function inputUpdate(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","update");
	form.appendChild(ele);
	document.form.submit();
};

function backList(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","list");
	form.appendChild(ele);
	document.form.submit();
};
