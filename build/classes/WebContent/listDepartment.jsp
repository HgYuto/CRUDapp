<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/style.css" rel="stylesheet" />
	<link type="text/css" href="css/department.css" rel="stylesheet" />
	<title>部署マスター管理</title>
</head>
<body>
 <script>
  function jump(link){
	let url = "DepartmentController";
	let para;
	let para1 = getCode(0);
	let para2 = "&deptCode=";
	let para3 = getCode(1);
	if(link == 1){
		para = "?action=insertFace";
		location.href = url + para;
	}
	if(link== 2){
		para = "?action=updateFace&code=";
		if(getCode(0)==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1 + para2 + para3 ;
		}
	}
	if(link== 3){
		para = "?action=delete&code=";
		if(getCode(0)==''){
			window.alert("データを選択してください。");
		}else{
			location.href = url + para + para1 + para2 + para3 ;
		}
	}
	if(link == 4){
		para = "?action=face";
		location.href = url + para;
	}
  }
  function getCode(num){
  	let custCodes = document.getElementsByName("code");
  	let deptCodes = document.getElementsByName("deptCode");
  	let custCode = '';
  	let deptCode = '';
  	for(let i = 0;i < custCodes.length; i++){
  		if(custCodes.item(i).checked){
  			custCode = custCodes.item(i).value;
  			deptCode = deptCodes.item(i).value;
  		}
  	}
  	let code=[custCode,deptCode];
  	return code[num];
  }
</script>
 <form action="/CRUDapp/DepartmentController" method="POST" >
 <div id="f">
 <h1>部署マスター管理画面</h1>
 <table>
  <tr>
   <th>取引先コード</th>
   <th>部署コード</th>
   <th>部署名1</th>
   <th>部署名2</th>
   <th>郵便番号</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="cust_code" size="11" <c:out value="${department.custCode}" /> />
    </td>
    <td>
      <input type="text" name="dept_code" size="11" <c:out value="${department.deptCode}" /> />
    </td>
    <td>
      <input type="text" name="dept_name1" size="11" <c:out value="${department.deptName1}" /> />
    </td>
    <td>
      <input type="text" name="dept_name2" size="11" <c:out value="${department.deptName2}" /> />
    </td>
    <td>
      <input type="text" name="post_code" size="11" <c:out value="${department.postCode}" /> />
    </td>
  </tr>
  <tr>
   <th>住所1</th>
   <th>住所2</th>
   <th>住所3</th>
   <th>電話番号</th>
   <th>担当者</th>
   <th>メールアドレス</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="address1" size="11" <c:out value="${department.address1}" /> />
    </td>
    <td>
      <input type="text" name="address2" size="11" <c:out value="${department.address2}" /> />
    </td>
    <td>
      <input type="text" name="address3" size="11" <c:out value="${department.address3}" /> />
    </td>
    <td>
      <input type="text" name="tel" size="11" <c:out value="${department.tel}" /> />
    </td>
    <td>
      <input type="text" name="charge_name" size="11" <c:out value="${department.chargeName}" /> />
    </td>
    <td>
      <input type="text" name="mail" size="11" <c:out value="${department.mail}" /> />
    </td>
    <td>
      <button type="Submit" name="action" value="search">検索</button>
    </td>
  </tr>
 </table>
 </div>
 </form>

 <div id="dScroll">
 <table id="deptT">
 <thead>
  <tr>
   <th></th>
   <th id="cust_code">取引先コード</th>
   <th id="dept_code">部署コード</th>
   <th id="dept_name1">部署名１</th>
   <th id="dept_name2">部署名２</th>
   <th id="post_code">郵便番号</th>
   <th id="address1">住所１</th>
   <th id="address2">住所２</th>
   <th id="address3">住所３</th>
   <th id="tel">電話番号</th>
   <th id="charge_name">担当者</th>
   <th id="mail">メールアドレス</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${departments}" var="department">
 <tr>
  <td id="radio">
   <input type="radio" name="code" value="${department.custCode}"/>
    <input type="hidden" name="deptCode" value="${department.deptCode}"/>
  </td>
  <td>
   <c:out value="${department.custCode}"/>
  </td>
  <td>
   <c:out value="${department.deptCode}" />
  </td>
  <td>
   <c:out value="${department.deptName1}" />
  </td>
  <td>
   <c:out value="${department.deptName2}" />
  </td>
  <td>
   <c:out value="${department.postCode}" />
  </td>
  <td>
   <c:out value="${department.address1}" />
  </td>
  <td>
   <c:out value="${department.address2}" />
  </td>
  <td>
   <c:out value="${department.address3}" />
  </td>
  <td>
   <c:out value="${department.tel}" />
  </td>
  <td>
   <c:out value="${department.chargeName}" />
  </td>
  <td>
   <c:out value="${department.mail}" />
  </td>
 </tr>
  </c:forEach>
 </tbody>
</table>
</div>
<table id="b">
  <tr>
   <td><input type="button" value="追加" onclick="jump(1)"></td>
   <td><input type="button" value="更新" onclick="jump(2)"></td>
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