/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;
import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;
import java.rmi.registry.*;
import java.rmi.*;
//[Mã câu hỏi (qCode): 9i4T9xwb].  
//Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 
//(hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
//Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng 
//các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau: 
//a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;BAA62945"
//b.	Nhận một chuỗi ngẫu nhiên từ server
//Ví dụ: dgUOo ch2k22ldsOo
//c.	Liệt kê các ký tự (là chữ hoặc số) xuất hiện nhiều hơn một lần trong chuỗi và số lần xuất hiện của chúng và gửi lên server
//Ví dụ: d:2,O:2,o:2,2:3,
//d.	Đóng kết nối và kết thúc chương trình.

public class TCP_CharacterStream2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109",2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String code = "B21DCCN002;9i4T9xwb";
        bw.write(code);
        bw.newLine();
        bw.flush();
        String s = br.readLine();
        System.out.println(s);
        Map<Character,Integer> mp = new HashMap<>();
        Set<Character> se = new HashSet<>();
        mp.clear();
        se.clear();
        char []a = s.toCharArray();
        int n = a.length;
        for(int i = 0 ; i<n;i++)
        {
        if(Character.isDigit(a[i]) || Character.isAlphabetic(a[i]))
        {
        se.add(a[i]);
        if(mp.containsKey(a[i])) mp.put(a[i],mp.get(a[i])+1);
        else mp.put(a[i], 1);
        }
        }
        String ans = "";
        for(char i:a){
        if( (Character.isAlphabetic(i) || Character.isDigit(i)) && mp.get(i)>=2 ){
            System.out.print(i+":"+mp.get(i)+",");
            ans += i+":"+mp.get(i)+",";
            mp.put(i, 1);
        }
        }
        bw.write(ans);
        bw.close();
        br.close();
    }
}
