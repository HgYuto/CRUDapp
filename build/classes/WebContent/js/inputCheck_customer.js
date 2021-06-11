function inputCheck(){
		eMassage = "";

		eMassage = mustCheck("取引先コード",form.cust_code).concat(
					lengthCheck("取引先コード",form.cust_code,3),
					surroCheck("取引先コード",form.cust_code),
					vanWordCheck("取引先コード",form.cust_code),
					isAlpNumHan("取引先コード",form.cust_code),
					lengthCheck("取引先名",form.cust_name,30),
					surroCheck("取引先名",form.cust_name),
					vanWordCheck("取引先名",form.cust_name),
					lengthCheck("URL",form.url,120),
					surroCheck("URL",form.url),
					lengthCheck("支払いサイト",form.payment_site,2),
					surroCheck("支払いサイト",form.payment_site),
					isNumHan("支払いサイト",form.payment_site),
					vanWordCheck("支払いサイト",form.payment_site));

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