package com.crud.app.model;

public class Department {

          private String custCode;
          private String deptCode;
          private String deptName1;
          private String deptName2;
          private String postCode;
          private String address1;
          private String address2;
          private String address3;
          private String tel;
          private String chargeName;
          private String mail;
          private String errResult;

          public Department() {
          }

          public Department(String custCode,String deptCode,String deptName1,String deptName2,
        		  			String postCode,String address1,String address2,String address3,
        		  			String tel,String chargeName,String mail) {
                 this.custCode = custCode;
                 this.deptCode = deptCode;
                 this.deptName1 = deptName1;
                 this.deptName2 = deptName2;
                 this.postCode = postCode;
                 this.address1 = address1;
                 this.address2 = address2;
                 this.address3 = address3;
                 this.tel = tel;
                 this.chargeName = chargeName;
                 this.mail = mail;
           }

           public String getCustCode() {
                 return this.custCode;
            }
           public void setCustCode(String custCode) {
                 this.custCode = custCode;
            }

           public String getDeptCode() {
               return this.deptCode;
           	}
           public void setDeptCode(String deptCode) {
               this.deptCode = deptCode;
           	}

             public String getDeptName1() {
                   return this.deptName1;
            }
            public void setDeptName1(String deptName1) {
                   this.deptName1 = deptName1;
            }

            public String getDeptName2() {
                return this.deptName2;
            }
            public void setDeptName2(String deptName2) {
                this.deptName2 = deptName2;
         	}

            public String getPostCode() {
                return this.postCode;
            }
            public void setPostCode(String postCode) {
                this.postCode = postCode;
            }

            public String getAddress1() {
                return this.address1;
            }
            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return this.address2;
            }
            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getAddress3() {
                return this.address3;
            }
            public void setAddress3(String address3) {
                this.address3 = address3;
            }

            public String getTel() {
             return this.tel;
            }
            public void setTel(String tel) {
             this.tel = tel;
            }

            public String getChargeName() {
                return this.chargeName;
            }
            public void setChargeName(String chargeName) {
            	this.chargeName = chargeName;
            }
            public String getMail() {
            	return this.mail;
            }
            public void setMail(String mail) {
                this.mail = mail;
            }

            public String getErrResult() {
            	return this.errResult;
            }

            public void setErrResult(String errResult) {
            	this.errResult = errResult;
            }

}