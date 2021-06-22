package com.crud.app.model;

public class Syain {

          private String syainCode;
          //positionの初期値を－1とする。
          private short position = -1;
          private String syainName;
          private String mailAddress;
          private String tel;

          public Syain() {
          }

          public Syain(String syainCode, short position, String syainName, String mailAddress,String tel) {
                 this.syainCode = syainCode;
                 this.position = position;
                 this.syainName = syainName;
                 this.mailAddress = mailAddress;
                 this.tel = tel;
           }

           public String getSyainCode() {
                 return this.syainCode;
            }
           public void setSyainCode(String syainCode) {
                 this.syainCode = syainCode;
            }

           public short getPosition() {
               return this.position;
           	}
           public void setPosition(short position) {
               this.position = position;
           	}

             public String getSyainName() {
                   return this.syainName;
            }
            public void setSyainName(String syainName) {
                   this.syainName = syainName;
            }
            public String getMailAddress() {
                return this.mailAddress;
           }
          public void setMailAddress(String mailAddress) {
                this.mailAddress = mailAddress;
           }

          public String getTel() {
              return this.tel;
          	}
          public void setTel(String tel) {
              this.tel = tel;
          	}
}