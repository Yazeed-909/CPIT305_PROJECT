/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication3;

import java.util.Date;

/**
 *
 * @author yzeed
 */
public class UserINFO {

    String Email;
    int userid;
    String Password;
    Date DOB;
    String Gender;
    String name;
    double Balance;

    public UserINFO(int userid,String name,String Email, String Password, Date DOB, String Gender,double Balance) {
        this.Email = Email;
        this.Password = Password;
        this.DOB = DOB;
        this.Gender = Gender;
        this.name=name;
        this.userid=userid;
        this.Balance=Balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getGender() {
        return Gender;
    }

    public int getUserid() {
        return userid;
    }

    public double getBalance() {
        return Balance;
    }

    @Override
    public String toString() {
        return "UserINFO{" + "Email=" + Email + ", userid=" + userid + ", Password=" + Password + ", DOB=" + DOB + ", Gender=" + Gender + ", name=" + name + ", Balance=" + Balance + '}';
    }

   
   

   


}