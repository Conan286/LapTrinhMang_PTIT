/*
[Mã câu hỏi (qCode): IvYR6qZi].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
Giao diện từ xa:
public interface CharacterService extends Remote {
public String requestCharacter(String studentCode, String qCode) throws RemoteException;
public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
}
Trong đó:
• Interface CharacterService được viết trong package RMI.
• Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi đầu vào".
b. Thực hiện thao tác mã hóa URL (URL Encoding) cho chuỗi đầu vào nhận được từ server. Mã hóa URL chuyển đổi các ký tự đặc biệt thành định dạng URL an toàn bằng cách thay thế các ký tự đó bằng ký hiệu phần trăm (%) và mã ASCII của chúng.
Ví dụ: Chuỗi ban đầu "Hello World!" -> Chuỗi mã hóa URL là: "Hello%20World%21"
c. Triệu gọi phương thức submitCharacter để gửi chuỗi đã được mã hóa trở lại server.
d. Kết thúc chương trình client. 
*/
package RMIClient;
import RMI.CharacterService;
import java.util.*;
import java.math.*;
import java.rmi.*;
import java.io.*;
import java.lang.*;
import java.net.*;
import java.rmi.registry.*;
/**
 *
 * @author Legion
 */
public class RMI_Character {
    public static void main(String args[]) throws RemoteException, NotBoundException, UnsupportedEncodingException {
       Registry rg = LocateRegistry.getRegistry("203.162.10.109",1099);
       CharacterService cs = (CharacterService) rg.lookup("RMICharacterService");
       String input  = cs.requestCharacter("B21DCCN441", "IvYR6qZi");
       input = URLEncoder.encode(input,"UTF-8");
       cs.submitCharacter("B21DCCN441", "IvYR6qZi", input);
    }
}
