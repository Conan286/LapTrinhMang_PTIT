/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package TCP;

/**
 *
 * @author Legion
 */
import java.util.*;
import java.io.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;

public class TCP_ObjectStream2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        Socket socket = new Socket("203.162.10.109",2209);
        ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
        String code = "B21DCCN002;YBuPjIB7";
        oo.writeObject(code);
        Laptop lt = (Laptop) oi.readObject();
        System.out.println(lt.name);
        System.out.println(lt.quantity);
        lt.update();
        System.out.println(lt.name);
        System.out.println(lt.quantity);
        oo.writeObject(lt);
        oi.close();
        oo.close();
    }
}
