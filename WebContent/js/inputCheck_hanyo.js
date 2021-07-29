function inputCheck() {
	eMassage = "";

	eMassage = mustCheck("汎用コード",form.hanyo_code).concat(
				lengthCheck("汎用コード",form.hanyo_code,3),
				surroCheck("汎用コード",form.hanyo_code),
				vanWordCheck("汎用コード",form.hanyo_code),
				isAlpNumHan("汎用コード",form.hanyo_code),
				mustCheck("値コード",form.value_code),
				lengthCheck("値コード",form.value_code,10),
				surroCheck("値コード",form.value_code),
				vanWordCheck("値コード",form.value_code),
				isAlpNumHan("値コード",form.value_code),
				isPoNum("値コード",form.value_code),
				lengthCheck("値",form.value_name,100),
				surroCheck("値",form.value_name),
				vanWordCheck("値",form.value_name));

	eMassage = eMassage.replace(/( |　|undefined)+/g, "");
	if(eMassage == ""){
		inputInsert();
	}
	else{
		window.alert(eMassage);
	}
};

function inputInsert(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","insert");
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