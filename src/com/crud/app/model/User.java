package com.crud.app.model;

public class User {

          private String syainCode;
          private String syainName;
          private String userId;
          private String password;
          //authorityの初期値を－1とする。
          private short authority;

          //更新前のデータ
          private String preUserId;

          private String errResult;

          public User() {
          }

          public User(String syainCode, String userId, String password, short authority) {
                 this.syainCode = syainCode;
                 this.userId = userId;
                 this.password = password;
                 this.authority = authority;
           }

           public String getSyainCode() {
                 return this.syainCode;
            }
           public void setSyainCode(String syainCode) {
                 this.syainCode = syainCode;
            }

             public String getSyainName() {
                   return this.syainName;
            }
            public void setSyainName(String syainName) {
                   this.syainName = syainName;
            }
            public String getUserId() {
                return this.userId;
           }
          public void setUserId(String userId) {
                this.userId = userId;
           }

          public String getPassword() {
              return this.password;
          	}
          public void setPassword(String password) {
              this.password = password;
          	}

          public short getAuthority() {
              return this.authority;
          	}
          public void setAuthority(short authority) {
              this.authority = authority;
          	}

          public String getPreUserId() {
              return this.preUserId;
         }
          public void setPreUserId(String preUserId) {
              this.preUserId = preUserId;
         }

          public String getErrResult() {
          	return this.errResult;
          }

          public void setErrResult(String errResult) {
          	this.errResult = errResult;
          }
}