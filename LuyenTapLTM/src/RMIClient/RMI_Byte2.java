/*
[Mã câu hỏi (qCode): XwgdSU5o].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
Giao diện từ xa:
public interface ByteService extends Remote {
public byte[] requestData(String studentCode, String qCode) throws RemoteException;
public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}
Trong đó:
•	Interface ByteService được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân đã mã hóa dưới dạng Base64 nhận được từ RMI Server:
a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server, trong đó mảng này chứa một chuỗi Base64 được mã hóa.
b. Thực hiện giải mã dữ liệu nhận được từ định dạng Base64 về dữ liệu gốc. Sử dụng giải mã Base64 để chuyển mảng byte Base64 thành mảng byte nguyên bản ban đầu.
Ví dụ: Nếu dữ liệu Base64 nhận được là chuỗi mã hóa SGVsbG8gV29ybGQ= (tương ứng với chuỗi "Hello World" trong ASCII), chương trình sẽ thực hiện giải mã để nhận được mảng dữ liệu nhị phân [72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100].
c. Triệu gọi phương thức submitData để gửi mảng dữ liệu gốc đã giải mã trở lại server.
d. Kết thúc chương trình client.
 */
package RMIClient;

import java.io.*;
import java.rmi.*;
import java.math.*;
import java.net.*;
import java.lang.*;
import java.util.*;
import java.rmi.registry.*;
import RMI.ByteService;
import java.util.Base64.Encoder;
public class RMI_Byte2 {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService bs = (ByteService) rg.lookup("RMIByteService");
        byte []bf = bs.requestData("B21DCCN002", "XwgdSU5o");
        bf = Base64.getDecoder().decode(bf);
        bs.submitData("B21DCCN002", "XwgdSU5o", bf);
    }
}
