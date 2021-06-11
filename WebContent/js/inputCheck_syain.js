function inputCheck() {

	eMassage = "";

	eMassage = mustCheck("社員コード",form.syain_code).concat(
				lengthCheck("社員コード",form.syain_code,5),
				surroCheck("社員コード",form.syain_code),
				vanWordCheck("社員コード",form.syain_code),
				isAlpNumHan("社員コード",form.syain_code),
				mustCheck("役職",form.position),
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