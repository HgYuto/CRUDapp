package com.crud.app.model;

public class Customer {

          private String custCode;
          private String custName;
          private String url;
          private String paymentSite;

          public Customer() {
          }

          public Customer(String custCode, String custName,String url,String paymentSite) {
                 this.custCode = custCode;
                 this.custName = custName;
                 this.url = url;
                 this.paymentSite = paymentSite;
           }

           public String getCustCode() {
                 return this.custCode;
            }

           public void setCustCode(String custCode) {
                 this.custCode = custCode;
            }

             public String getCustName() {
                   return this.custName;
            }

            public void setCustName(String custName) {
                   this.custName = custName;
            }

            public String getUrl() {
                return this.url;
         }

         public void setUrl(String url) {
                this.url = url;
         }

         public String getPaymentSite() {
             return this.paymentSite;
        }

       public void setPaymentSite(String paymentSite) {
             this.paymentSite = paymentSite;
        }

}