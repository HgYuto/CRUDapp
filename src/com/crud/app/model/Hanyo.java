package com.crud.app.model;

public class Hanyo {

          private String hanyoCode;
          private String valueCode;
          private String valueName;

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

}