package com.crud.app.model;

public class Employee {

          private long employeeId;
          private String employeeName;

          public Employee() {
          }

          public Employee(long employeeId, String employeeName) {

                 this.employeeId = employeeId;
                 this.employeeName = employeeName;

           }

           public long getEmployeeId() {
                 return employeeId;
            }

           public void setEmployeeId(long employeeId) {
                 this.employeeId = employeeId;
            }

             public String getEmployeeName() {
                   return employeeName;
            }

            public void setEmployeeName(String employeeName) {
                   this.employeeName = employeeName;
            }
}