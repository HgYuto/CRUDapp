function updateCheck() {
	eMassage = "";

	eMassage = lengthCheck("部署名1",form.dept_name1,30).concat(
				surroCheck("部署名1",form.dept_name1),
				vanWordCheck("部署名1",form.dept_name1),
				lengthCheck("部署名2",form.dept_name2,30),
				surroCheck("部署名2",form.dept_name2),
				vanWordCheck("部署名2",form.dept_name2),
				lengthCheck("郵便番号",form.post_code,7),
				surroCheck("郵便番号",form.post_code),
				vanWordCheck("郵便番号",form.post_code),
				isNumHan("郵便番号",form.post_code),
				lengthCheck("住所1",form.address1,100),
				surroCheck("住所1",form.address1),
				vanWordCheck("住所1",form.address1),
				lengthCheck("住所2",form.address2,100),
				surroCheck("住所2",form.address2),
				vanWordCheck("住所2",form.address2),
				lengthCheck("住所3",form.address3,100),
				surroCheck("住所3",form.address3),
				vanWordCheck("住所3",form.address3),
				lengthCheck("電話番号",form.tel,14),
				surroCheck("電話番号",form.tel),
				vanWordCheck("電話番号",form.tel),
				isAlpNumHan("電話番号",form.tel),
				lengthCheck("担当者",form.charge_name,10),
				surroCheck("担当者",form.charge_name),
				vanWordCheck("担当者",form.charge_name),
				lengthCheck("メールアドレス",form.mail,30),
				surroCheck("メールアドレス",form.mail),
				vanWordCheck("メールアドレス",form.mail),
				isMailHan("メールアドレス",form.mail));

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