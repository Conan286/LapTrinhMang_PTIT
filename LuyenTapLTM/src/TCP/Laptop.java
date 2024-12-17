/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
public class Laptop implements Serializable {
    private static final long serialVersionUID = 20150711L; 
     int id, quantity;
     String code , name;
     public Laptop(int id,int quantity,String code, String name)
     {
     this.id = id;
     this.name = name;
     this.code = code;
     this.quantity = quantity;
     }
     public void update()
     {
     String s = this.name;
     char []c = String.valueOf(this.quantity).trim().toCharArray();
     String n = "";
     for(int i = c.length-1;i>=0;i--) n+= c[i];
     this.quantity = Integer.valueOf(n);
     ArrayList<String> as = new ArrayList<>();
     StringTokenizer ss = new StringTokenizer(s);
     while(ss.hasMoreTokens()) as.add(ss.nextToken());
     this.name = "";
     this.name += as.get(as.size()-1)+" ";
     for(int i = 1;i<as.size()-1;i++) this.name += as.get(i)+" ";
     this.name += as.get(0);
     
     }
}
