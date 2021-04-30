<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>部署マスター管理</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
  <script>
  function jump(link){
	let url;
	let para;
	let para1;
	let para2;
	if(link == 1){
		url = "DepartmentController";
		para = "?action=insertFace";
		location.href = url + para;
	}
	if(link== 2){
		url = "DepartmentController";
		para1 = "?action=updateFace&custCode=";
		para2 = getCustCode();
		location.href = url + para1 + para2;
		get
	}
	if(link== 3){
		url = "DepartmentController";
		para1 = "?action=delete&custCode=";
		para2 = getCustCode();
		location.href = url + para1 + para2;
	}
  }
  function getCustCode(){
  	let custCodes = document.getElementsByName("code");
  	let len = custCodes.length;
  	let custCode = '';
  	for(let i = 0;i < len; i++){
  		if(custCodes.item(i).checked){
  			custCode = custCodes.item(i).value;
  		}
  	}
  	return custCode;
  }
</script>
 <h1>部署マスター管理画面</h1>
 <div id="dmFace">
 <table>
 <thead>
  <tr>
   <th id="dmCheck"></th>
   <th id="cust_code">取引先コード</th>
   <th id="dept_code">部署コード</th>
   <th id="dept_name">部署名１</th>
   <th id="dept_name">部署名２</th>
   <th id="post_code">郵便番号</th>
   <th id="address">住所１</th>
   <th id="address">住所２</th>
   <th id="address">住所３</th>
   <th id="tel">電話番号</th>
   <th id="charge_name">担当者</th>
   <th id="mail">メールアドレス</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${departments}" var="department">
 <tr>
  <td>
   <input type="radio" name="code" value="${department.custCode}"/>
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
<table id="dmBotan">
  <tr>
   <td><input type="button" value="追加" onclick="jump(1)"></td>
   <td><input type="button" value="更新" onclick="jump(2)"></td>
   <td><input type="button" value="削除" onclick="jump(3)"/></td>
  </tr>
</table>
</body>
</html>