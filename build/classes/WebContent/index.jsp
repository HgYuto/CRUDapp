<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/index.css" rel="stylesheet" />
<title>マスタメンテナンス</title>
</head>
<body>
<script>
  function jump(link){
	let url;
	let para = "?action=firstList";
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

	if(link== 4){
		url = "SyainController";
		location.href = url + para;
	}
	if(link== 5){
		url = "UserController";
		location.href = url + para;
	}
  }
</script>
<div>
<h1>マスタメンテナンス画面</h1>
<table>
	<tbody>
	<tr>
		<td><p id="bm"><button type="button" onclick="jump(1)">取引先マスター</button></p></td>
		<td><p id="bm"><button type="button" onclick="jump(2)">部署マスター</button></p></td>
	</tr>
	<tr>
		<td><p id="bm"><button type="button" onclick="jump(3)">汎用コードマスター</button></p></td>
		<td><p id="bm"><button type="button" onclick="jump(4)">社員マスター</button></p></td>
	</tr>
	<tr>
		<td><p id="bm"><button type="button" onclick="jump(5)">ユーザマスター</button></p></td>
	</tr>
	</tbody>
</table>
</div>
</body>
</html>
