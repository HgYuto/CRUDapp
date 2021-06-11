function updateCheck() {

	eMassage = "";

	eMassage = mustCheck("役職",form.position).concat(
				lengthCheck("役職",form.position,1),
				surroCheck("役職",form.position),
				vanWordCheck("役職",form.position),
				isNumHan("役職",form.position),
				mustCheck("社員名",form.syain_name),
				lengthCheck("社員名",form.syain_name,30),
				surroCheck("社員名",form.syain_name),
				vanWordCheck("社員名",form.syain_name),
				lengthCheck("メールアドレス",form.mail_address,100),
				surroCheck("メールアドレス",form.mail_address),
				vanWordCheck("メールアドレス",form.mail_address),
				isMailHan("メールアドレス",form.mail_address),
				lengthCheck("電話番号",form.tel,14),
				surroCheck("電話番号",form.tel),
				vanWordCheck("電話番号",form.tel),
				isAlpNumHan("電話番号",form.tel));

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