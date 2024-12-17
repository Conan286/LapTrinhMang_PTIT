/*
 [Mã câu hỏi (qCode): uQ4HubPZ].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện 
cho phép triệu gọi từ xa để quản lý thông tin đơn hàng trong hệ thống thương mại điện tử.
Chương trình sẽ ngẫu nhiên tạo ra đối tượng Order với các giá trị ban đầu và cung cấp cho RMI client như sau:
    Giao diện từ xa:
public interface ObjectService extends Remote {
    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
}
Lớp Order gồm các thuộc tính: id String, customerCode String, orderDate String, shippingType String, orderCode String.
•	Trường dữ liệu: private static final long serialVersionUID = 20241132L;
•	02 hàm khởi dựng:
    public Order()
    public Order(String id, String customerCode, String orderDate, String shippingType)
Trong đó:
•	Interface ObjectService và lớp Order được viết trong package RMI.
•	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService.
Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với đối tượng đơn hàng được nhận từ RMI Server:
a. Triệu gọi phương thức requestObject để nhận đối tượng Order ngẫu nhiên từ server.
b. Tạo mã orderCode cho đơn hàng dựa trên các quy tắc sau:
•	Bắt đầu bằng hai ký tự đầu của shippingType, viết in hoa.
•	Kế đến là ba ký tự cuối của customerCode.
•	Cuối cùng là ngày và tháng từ orderDate (theo định dạng "ddMM").
Ví dụ: Nếu đơn hàng có mã khách hàng là "C123456", ngày đặt hàng là "2023-10-05", 
và loại giao hàng là "Express", thì mã orderCode sẽ là "EX4560510".
c. Cập nhật giá trị orderCode trong đối tượng Order.
d. Triệu gọi phương thức submitObject để gửi đối tượng Order đã được xử lý trở lại server.
e. Kết thúc chương trình client.
 */
package RMIClient;
import RMI.Order;
import RMI.ObjectService;
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;
import java.rmi.*;
import java.net.*;
import java.rmi.registry.*;
public class RMI_Object {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService os = (ObjectService) rg.lookup("RMIObjectService");
        Order input = (Order) os.requestObject("B21DCCN441", "uQ4HubPZ");
        input.setOrderCode();
        System.out.println(input.getId());
        System.out.println(input.getDate());
        System.out.println(input.getType());
        System.out.println(input.getCustomerCode());
        System.out.println(input.getOrderCode());
        os.submitObject("B21DCCN441", "uQ4HubPZ", input);
    }
}
