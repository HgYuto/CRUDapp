<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/syain.css" rel="stylesheet" />
	<title>社員マスター管理</title>
</head>
<body>
  <script>
  function jump(link){
	let url = "SyainController";
	let para;
	let para1 = getCode();
	if(link== 1){
		para = "?action=insertFace";
		location.href = url + para;
	}
	if(link== 2){
		para = "?action=updateFace&syainCode=";
		if(getCode()==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1;
		}
	}
	if(link== 3){
		para = "?action=delete&syainCode=";
		if(getCode()==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1;
		}
	}
	if(link== 4){
		para = "?action=face";
		location.href = url + para;
	}
  }
  function getCode(){
  	let syainCodes = document.getElementsByName("code");
  	let len = syainCodes.length;
  	let syainCode = '';
  	for(let i = 0;i < len; i++){
  		if(syainCodes.item(i).checked){
  			syainCode = syainCodes.item(i).value;
  		}
  	}
  	return syainCode;
  }

</script>
 <form action="/CRUDapp/SyainController" method="POST" >
 <div id="f">
 <h1>社員マスター管理画面</h1>
 <table>
  <tr>
   <th>社員コード</th>
   <th>役職</th>
   <th>社員名</th>
   <th>メールアドレス</th>
   <th>電話番号</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="syain_code" size="11" <c:out value="${syain.syainCode}" /> />
    </td>
    <td>
      <input type="text" name="position" size="3" <c:out value="${syain.position}" /> />
    </td>
    <td>
      <input type="text" name="syain_name" size="13" <c:out value="${syain.syainName}" /> />
    </td>
        <td>
      <input type="text" name="mail_address" size="23" <c:out value="${syain.MailAddress}" /> />
    </td>
        <td>
      <input type="text" name="tel" size="11" <c:out value="${syain.tel}" /> />
    </td>
    <td>
      <button type="Submit" name="action" value="search">検索</button>
    </td>
  </tr>
 </table>
 </div>
 </form>
 <div id="sScroll">
 <table id="st">
 <thead>
  <tr>
   <th></th>
   <th id="syain_code">社員コード</th>
   <th id="position">役職</th>
   <th id="syain_name">社員名</th>
   <th id="mailaddress">メールアドレス</th>
   <th id="tel">電話番号</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${syains}" var="syain">
 <tr>
  <td id="radio">
   <input type="radio" name="code" value="${syain.syainCode}" />
  </td>
  <td>
   <c:out value="${syain.syainCode}"/>
  </td>
  <td>
   <c:out value="${syain.position}" />
  </td>
  <td>
   <c:out value="${syain.syainName}" />
  </td>
  <td>
   <c:out value="${syain.mailAddress}" />
  </td>
  <td>
   <c:out value="${syain.tel}" />
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