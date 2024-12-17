/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package RMIClient;

import RMI.DataService;
import java.io.*;
import java.util.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.lang.*;
import java.math.*;

public class RMI_Data2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws RemoteException, NotBoundException {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService ds = (DataService) rg.lookup("RMIDataService");
        Object ab = ds.requestData("B21DCCN002", "dxv4dDNr");
        int n = (int)ab;
        System.out.println(n);
        int []a = {1,2,5,10};
        int i = 3;
        int ans = 0;    
        Map<Integer,Integer> mp = new HashMap<>();
        mp.put(2, 0);
        mp.put(1, 0);
        mp.put(5, 0);
        mp.put(10, 0);
        while(i>=0 && n>0)
        {
        while(n<a[i]&&i>=0) i--;
        int k = (int)n/a[i];
        mp.put(a[i], k);
        ans += k;
        n = n - k*a[i];
        i--;
        }
        String s = String.valueOf(ans)+"; ";
        System.out.println(1+" "+mp.get(1));
        System.out.println(2+" "+mp.get(2));
        System.out.println(5+" "+mp.get(5));
        System.out.println(10+" "+mp.get(10));
        for(int j = 0;j<mp.get(10);j++)
        {
        s += "10,";
        }
        for(int j = 0;j<mp.get(5);j++)
        {
        s += "5,";
        }
        for(int j = 0;j<mp.get(2);j++)
        {
        s += "2,";
        }
        for(int j = 0;j<mp.get(1);j++)
        {
        s += "1,";
        }
        s = s.substring(0,s.length()-1);
        System.out.println(s);
        ds.submitData("B21DCCN002", "dxv4dDNr", s);
    }
}
