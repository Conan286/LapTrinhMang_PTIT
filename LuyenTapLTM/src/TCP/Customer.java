package TCP;
import java.io.*;
import java.util.*;
import java.math.*;
import java.net.*;
public class Customer implements Serializable{
    private static final long serialVersionUID = 20170711L;
    //id int, code String, name String, dayOfBirth String, userName String
    int id;
    String code,name,userName,dayOfBirth;
    public Customer(int id,String code,String name,String userName,String dayOfBirth)
    {
    this.id = id;
    this.code = code;
    this.dayOfBirth = dayOfBirth;
    this.name = name.toLowerCase();
    this.userName = userName;
    }
    public void chuanHoaName(){
    String s = this.name.toLowerCase();
   
    StringTokenizer ss = new StringTokenizer(s);
    ArrayList<String> a = new ArrayList<>();
    ArrayList<String> b = new ArrayList<>();
    while(ss.hasMoreTokens()){
        String kk = ss.nextToken();
        a.add(kk);
        b.add(kk.toUpperCase());
    }
    String ans = "";
     String ans2 = "";
    int n = a.size();
    for(int i = 0;i<n-1;i++)
    {
    String z = a.get(i);
    ans += z.substring(0, 1);
    String y = b.get(i);
    ans2 += y.substring(0,1) + y.substring(1).toLowerCase()+" ";
    }
    ans2 = ans2.substring(0,ans2.length()-1);
    this.name = b.get(n-1)+", "+ans2;
    ans += a.get(n-1);
    this.userName = ans;
    }
    public void chuanHoaNS(){
    String s = this.dayOfBirth;
    // 10-11-2012 
    String ans = s.substring(3,5)+"/"+s.substring(0,2)+"/"+s.substring(6);
    this.dayOfBirth = ans;
    }
//    @Override
//    public String toString()
//    {
//    return "";
//    }
}
