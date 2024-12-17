/*
[Mã câu hỏi (qCode): kI0AsqfA].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
Giao diện từ xa:
public interface DataService extends Remote {
public Object requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}
Trong đó:
•	Interface DataService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận hai số nguyên dương N và K từ server, đại diện cho khoảng cần kiểm tra (N <= số < K).
b. Xác định tất cả các số nguyên đối xứng (Palindrome Number) trong khoảng từ N đến K. Kết quả trả về là danh sách các số đối xứng thỏa mãn yêu cầu.
Ví dụ: Với N = 50 và K = 150, kết quả là [55, 66, 77, 88, 99, 101, 111, 121, 131, 141].
c. Triệu gọi phương thức submitData để gửi đối tượng List<Integer> danh sách các số nguyên đối xứng đã tìm được trở lại server.
d. Kết thúc chương trình client.
*/
package RMIClient;
import RMI.DataService;
import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;
import java.net.*;
import java.rmi.registry.*;
import java.rmi.*;
/**
 *
 * @author Legion
 */
public class RMI_Data {
    public static boolean check(int k){
    String s = String.valueOf(k);
    int n = s.length();
    int l = 0, r = n-1;
    char []a = s.toCharArray();
    while(l<=r){
    if(a[l++]!=a[r--]) return false;
    
    }
    return true;
    }
  
    public static void main(String args[]) throws RemoteException, NotBoundException {
       Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
       DataService ds = (DataService) rg.lookup("RMIDataService");
       Object input =  ds.requestData("B21DCCN441", "kI0AsqfA");
       System.out.println(input);
       String []s = String.valueOf(input).trim().split(";");
       int n = parseInt(s[0].trim());
       int k = parseInt(s[1].trim());
       ArrayList<Integer> li = new ArrayList<>();
       for(int i = n;i<=k;i++) if(check(i)) li.add(i);
       ds.submitData("B21DCCN441", "kI0AsqfA", li);
    }
}
