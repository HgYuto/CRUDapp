<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/hanyo.css" rel="stylesheet" />
	<title>汎用コードマスター管理</title>
</head>
<body>
  <script>
  function jump(link){
	let url = "HanyoController";
	let para;
	let para1 = getCode(0);
	let para2 = "&valueCode=";
	let para3 = getCode(1);
	if(link== 1){
		para = "?action=insertFace";
		location.href = url + para;
	}
	if(link== 2){
		para = "?action=updateFace&hanyoCode=";
		if(getCode(0)==''&&getCode(1)==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1 + para2 + para3;
		}
	}
	if(link== 3){
		para = "?action=delete&hanyoCode=";
		if(getCode(0)==''&&getCode(1)==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1 + para2 + para3;
		}
	}
	if(link== 4){
		url = "CustomerController";
		para = "?action=face";
		location.href = url + para;
	}
  }
  function getCode(num){
  	let hanyoCodes = document.getElementsByName("hanyoCode");
  	let valueCodes = document.getElementsByName("valueCode");
  	let len = hanyoCodes.length;
  	let hanyoCode = '';
  	let valueCode = '';
  	for(let i = 0;i < hanyoCodes.length; i++){
  		if(hanyoCodes.item(i).checked){
  			hanyoCode = hanyoCodes.item(i).value;
  			valueCode = valueCodes.item(i).value;
  		}
  	}
  	let code=[hanyoCode,valueCode];
  	return code[num];
  }
</script>
 <form action="/CRUDapp/HanyoController" method="POST" >
 <div id="f">
 <h1>汎用コードマスター管理画面</h1>
 <table>
  <tr>
   <th>汎用コード</th>
   <th>値コード</th>
   <th>値</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="hanyo_code" size="18" <c:out value="${hanyo.hanyoCode}" /> />
    </td>
    <td>
      <input type="text" name="value_code" size="18" <c:out value="${hanyo.valueCode}" /> />
    </td>
    <td>
      <input type="text" name="value_name" size="18" <c:out value="${hanyo.valueName}" /> />
    </td>
    <td>
      <button type="Submit" name="action" value="search">検索</button>
    </td>
  </tr>
 </table>
 </div>
 </form>
 <div id="hScroll">
 <table id="hanT">
 <thead>
  <tr>
   <th></th>
   <th id="hanyo_code">汎用コード</th>
   <th id="value_code">値コード</th>
   <th id="value_name">値</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${hanyos}" var="hanyo">
 <tr>
  <td id="radio">
   <input type="radio" name="hanyoCode" value="${hanyo.hanyoCode}" />
   <input type="hidden" name="valueCode" value="${hanyo.valueCode}"/>
  </td>
  <td>
   <c:out value="${hanyo.hanyoCode}"/>
  </td>
  <td>
   <c:out value="${hanyo.valueCode}" />
  </td>
  <td>
   <c:out value="${hanyo.valueName}" />
  </td>
 </tr>
  </c:forEach>
 </tbody>
</table>
</div>
<table id="b">
  <tr>
   <td><input type="button" value="追加" onclick="jump(1)"/></td>
   <td><input type="button" value="更新" onclick="jump(2)"/></td>
   <td><input type="button" value="削除" onclick="jump(3)"/></td>
   <td><input type="button" value="戻る" onclick="jump(4)"/></td>
  </tr>
</table>
 <div id="b">
<table>
<tr>
 <td><span id="err">${result}</span></td>
</tr>
</table>
 </div>
</body>
</html>