/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.*;
import java.net.*;
import java.rmi.*;

public class Product implements Serializable {
    private static final long serialVersionUID = 20151107L;
    String id,code;
    double importPrice, exportPrice;
     public Product(String id, String code, double importPrice, double exportPrice)
     {
     this.id = id;
     this.code = code;
     this.exportPrice = exportPrice;
     this.importPrice = importPrice;
     }
     public void update()
     {
     this.code = this.code.toUpperCase();
     this.exportPrice = this.importPrice *1.2;
     }
}
