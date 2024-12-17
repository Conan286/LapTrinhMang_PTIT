/*
 id String, customerCode String, orderDate String, shippingType String, orderCode String.
•	Trường dữ liệu: private static final long serialVersionUID = 20241132L;
•	02 hàm khởi dựng:
    public Order()
    public Order(String id, String customerCode, String orderDate, String shippingType)
*/
package RMI;

import java.io.*;
import java.math.*;
import java.util.*;
import java.lang.*;
public class Order implements Serializable{
    private static final long serialVersionUID = 20241132L;
    String id;
    String customerCode;
    String orderDate;
    String shippingType;
    String orderCode;
    public Order(String id, String customerCode, String orderDate, String shippingType,String OrderCode){
    this.id = id;
    this.customerCode = customerCode;
    this.orderDate = orderDate;
    this.shippingType = shippingType;
    this.orderCode = orderCode;
    }
    public String getId()
    {
    return this.id;
    }
    public String getCustomerCode()
    {
    return this.customerCode;
    }
    public String getDate()
    {
    return this.orderDate;
    }
    public String getType()
    {
    return this.shippingType;
    }
    public String getOrderCode()
    {
    return this.orderCode;
    }
    /*
    Tạo mã orderCode cho đơn hàng dựa trên các quy tắc sau:
•	Bắt đầu bằng hai ký tự đầu của shippingType, viết in hoa.
•	Kế đến là ba ký tự cuối của customerCode.
•	Cuối cùng là ngày và tháng từ orderDate (theo định dạng "ddMM").
    */
    public void setOrderCode()
    {
    this.orderCode = this.shippingType.toUpperCase().substring(0,2)+this.customerCode.substring(this.customerCode.length()-3)+this.orderDate.substring(8)+this.orderDate.substring(5,7);
    }
}
