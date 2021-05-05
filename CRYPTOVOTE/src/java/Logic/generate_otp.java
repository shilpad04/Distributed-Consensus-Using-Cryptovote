/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Random;

/**
 *
 * @author sumit
 */
public class generate_otp {
    String otp="";
    
    public String get_otp(){
    Random r=new Random();
    otp=r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10)+""+r.nextInt(10);
        System.out.println(otp);
    return otp;
    }
    
}
