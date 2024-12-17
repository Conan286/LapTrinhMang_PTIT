/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package RMIClient;

import RMI.CharacterService;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
public class RMI_Character2 {   
    public static void main(String args[]) throws RemoteException, NotBoundException {
       Registry rg = LocateRegistry.getRegistry("203.162.10.109",1099);
       CharacterService cs = (CharacterService) rg.lookup("RMICharacterService");
       String s = cs.requestCharacter("B21DCCN002", "yBwAovez");
        System.out.println(s);
        int n = s.length()%7;
        char []c = s.toCharArray();
        String ans = "";
        for(char i:c)
        {
        int k = (i>='a'&&i<='z')?(int)(i-'a'):(int)(i-'A');
        if(n<=k) i = (char)((int)i - n);
        else i = (i>='a'&&i<='z')?(char)((int)'z'+1-(n-k)):(char)((int)'Z'+1-(n-k));
        ans += i;
        }
        System.out.println(ans);
        cs.submitCharacter("B21DCCN002", "yBwAovez", ans);
        
    }
}
