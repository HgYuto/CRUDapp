<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/user.css" rel="stylesheet" />
	<title>ユーザマスター管理</title>
</head>
<body>
 <script>
  function jump(link){
	let url = "UserController";
	let para;
	let para2 = getCode(0);
	let para3 = "&userId=";
	let para4 = getCode(1);
	if(link== 1){
		para = "?action=insertFace";
		location.href = url + para;
	}
	if(link== 2){
		para = "?action=updateFace&syainCode=";
		if(getCode(0)==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para2 + para3 + para4;
		}
	}
	if(link== 3){
		para = "?action=delete&syainCode=";
		if(getCode(0)==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para2 + para3 + para4;
		}
	}
	if(link== 4){
		para = "?action=face";
		location.href = url + para;
	}
  }
  function getCode(num){
  	let syainCodes = document.getElementsByName("syainCode");
  	let userIds = document.getElementsByName("userId");
  	let syainCode = '';
  	let userId = '';
  	for(let i = 0;i < syainCodes.length; i++){
  		if(syainCodes.item(i).checked){
  			syainCode = syainCodes.item(i).value;
  			userId = userIds.item(i).value;
  		}
  	}
  	let code=[syainCode,userId];
  	return code[num];
  }
</script>
 <form action="/CRUDapp/UserController" method="POST" >
 <div id="f">
 <h1>ユーザマスター管理画面</h1>
 <table>
  <tr>
   <th>社員コード</th>
   <th>ユーザID</th>
   <th>権限</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="syain_code" size="11" <c:out value="${syain.syainCode}" /> />
    </td>
    <td>
      <input type="text" name="user_id" size="15" <c:out value="${user.userId}" /> />
    </td>
    <td>
      <input type="text" name="authority" size="9" <c:out value="${user.authority}" /> />
    </td>
    <td>
      <button type="Submit" name="action" value="search">検索</button>
    </td>
  </tr>
 </table>
 </div>
 </form>
 <div id="uScroll">
 <table id="ut">
 <thead>
  <tr>
   <th></th>
   <th id="syain_code">社員コード</th>
   <th id="syain_name">社員名</th>
   <th id="user_id">ユーザID</th>
   <th id="password">パスワード</th>
   <th id="authority">権限</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${users}" var="user">
 <tr>
  <td  id="radio">
   <input type="radio" name="syainCode" value="${user.syainCode}" />
   <input type="hidden" name="userId" value="${user.userId}"/>
  </td>
  <td>
   <c:out value="${user.syainCode}"/>
  </td>
  <td>
   <c:out value="${user.syainName}" />
  </td>
  <td>
   <c:out value="${user.userId}" />
  </td>
  <td>
   <c:out value="${user.password}" />
  </td>
  <td>
   <c:out value="${user.authority}" />
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