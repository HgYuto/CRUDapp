<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/syain.css" rel="stylesheet" />
	<title>社員マスター管理(更新)</title>

</head>
<body>
<script type="text/javascript">
function backList(){
	var form = document.getElementById("form");
	var ele= document.createElement("input");
	ele.setAttribute("type","hidden");
	ele.setAttribute("name","action");
	ele.setAttribute("value","list");
	return form.appendChild(ele);
};
</script>
 <form id="form"  name="form" action="/CRUDapp/SyainController" method="POST" >
 <div id="f">
<h1>社員マスター管理画面(更新)</h1>
<table>
 <tr>
    <th>社員コード</th>
    <td>
      <input type="text" value="${syain.syainCode}" name="syain_code" id="gray" size="15" readonly/>
    </td>
  </tr>
  <tr>
    <th>役職</th>
    <td>
      <input type="text" value="${syain.position}" name="position" size="9" <c:out value="${syain.position}" /> />
    </td>
  </tr>
  <tr>
    <th>社員名</th>
    <td>
     <input type="text" value="${syain.syainName}" name="syain_name" size="20" <c:out value="${syain.syainName}" /> />
    </td>
  </tr>
  <tr>
    <th>メールアドレス</th>
    <td>
      <input type="text" value="${syain.mailAddress}" name="mail_address" size="40" <c:out value="${syain.mailAddress}" /> />
    </td>
  </tr>
  <tr>
    <th>電話番号</th>
    <td>
      <input type="text" value="${syain.tel}" name="tel" size="20" <c:out value="${syain.tel}" /> />
    </td>
  </tr>
</table>
</div>
<div id="b">
<table>
<tr>
 <td>
  <input type="submit" id="update" name="update" value="更新" onclick="updateCheck()">
 </td>
 <td>
  <input type="submit" id="list" name="list" value="戻る" onclick="backList()">
 </td>
</tr>
</table>
</div>
 <div id="b">
<table>
<tr>
 <td><span id="err">${result}</span></td>
</tr>
</table>
 </div>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/updateCheck_syain.js"></script>
 </form>
</body>
</html>