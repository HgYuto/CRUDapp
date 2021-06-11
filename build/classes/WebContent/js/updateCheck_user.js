function updateCheck() {

	eMassage = "";

	eMassage = mustCheck("ユーザID",form.user_id).concat(
				lengthCheck("ユーザID",form.user_id,10),
				surroCheck("ユーザID",form.user_id),
				vanWordCheck("ユーザID",form.user_id),
				isAlpNumHan("ユーザID",form.user_id),
				mustCheck("パスワード",form.password),
				lengthCheck("パスワード",form.password,16),
				surroCheck("パスワード",form.password),
				vanWordCheck("パスワード",form.password),
				isAlpNumHan("パスワード",form.password),
				mustCheck("権限",form.authority),
				lengthCheck("権限",form.authority,2),
				surroCheck("権限",form.authority),
				vanWordCheck("権限",form.authority),
				isNumHan("権限",form.authority));

	eMassage = eMassage.replace(/( |　|undefined)+/g, "");
	if(eMassage == ""){
		return inputUpdate();
	}
	else{
		window.alert(eMassage);
		return backUp();
	}

};

function inputUpdate(){
		var form = document.getElementById("form");
		var ele= document.createElement("input");
		ele.setAttribute("type","hidden");
		ele.setAttribute("name","action");
		ele.setAttribute("value","update");
		return form.appendChild(ele);
};

function backUp(){
			var form = document.getElementById("form");
			var ele= document.createElement("input");
			ele.setAttribute("type","hidden");
			ele.setAttribute("name","action");
			ele.setAttribute("value","backUp");
			return form.appendChild(ele);
};