function inputCheck() {

	eMassage = "";

	eMassage = mustCheck("社員コード",form.syain_code).concat(
				lengthCheck("社員コード",form.syain_code,5),
				surroCheck("社員コード",form.syain_code),
				vanWordCheck("社員コード",form.syain_code),
				isAlpNumHan("社員コード",form.syain_code),
				mustCheck("ユーザID",form.user_id),
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
		return inputInsert();
	}
	else{
		window.alert(eMassage);
		return backIn();
	}

};

function inputInsert(){
		var form = document.getElementById("form");
		var ele= document.createElement("input");
		ele.setAttribute("type","hidden");
		ele.setAttribute("name","action");
		ele.setAttribute("value","insert");
		return form.appendChild(ele);
};

function backIn(){
			var form = document.getElementById("form");
			var ele= document.createElement("input");
			ele.setAttribute("type","hidden");
			ele.setAttribute("name","action");
			ele.setAttribute("value","backIn");
			return form.appendChild(ele);
};