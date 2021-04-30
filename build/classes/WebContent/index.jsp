<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUDapp</title>
</head>
<body>
<script>
  function jump(link){
	let url;
	let para = "?action=list";
	if(link == 1){
		url = "CustomerController";
		location.href = url + para;
	}
	if(link== 2){
		url = "DepartmentController";
		location.href = url + para;
	}

	if(link== 3){
		url = "HanyoController";
		location.href = url + para;
	}
  }
</script>

<h1>メニュー</h1>
<p><button type="button" onclick="jump(1)">取引先マスター管理画面</button></p>
<p><button type="button" onclick="jump(2)">部署マスター管理画面</button></p>
<p><button type="button" onclick="jump(3)">汎用コードマスター管理画面</button></p>
</body>
</html>
