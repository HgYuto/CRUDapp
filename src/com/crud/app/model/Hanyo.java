package com.crud.app.model;

public class Hanyo {

          private String hanyoCode;
          private String valueCode;
          private String valueName;

          private String oldHanyoCode;
          private String oldValueCode;
          private String oldValueName;

          private String errResult;

          public Hanyo() {
          }

          public Hanyo(String hanyoCode,String valueCode,String valueName) {
                 this.hanyoCode = hanyoCode;
                 this.valueCode = valueCode;
                 this.valueName = valueName;
           }

           public String getHanyoCode() {
                 return this.hanyoCode;
            }
           public void setHanyoCode(String hanyoCode) {
                 this.hanyoCode = hanyoCode;
            }

           public String getValueCode() {
               return this.valueCode;
           	}
           public void setValueCode(String valueCode) {
               this.valueCode = valueCode;
           	}

             public String getValueName() {
                   return this.valueName;
            }
            public void setValueName(String valueName) {
                   this.valueName = valueName;
            }
            public String getOldHanyoCode() {
                return this.oldHanyoCode;
           }
          public void setOldHanyoCode(String oldHanyoCode) {
                this.oldHanyoCode = oldHanyoCode;
           }

          public String getOldValueCode() {
              return this.oldValueCode;
          	}
          public void setOldValueCode(String oldValueCode) {
              this.oldValueCode = oldValueCode;
          	}

            public String getOldValueName() {
                  return this.oldValueName;
           }
           public void setOldValueName(String oldValueName) {
                  this.oldValueName = oldValueName;
           }

           public String getErrResult() {
           	return this.errResult;
           }

           public void setErrResult(String errResult) {
           	this.errResult = errResult;
           }

}