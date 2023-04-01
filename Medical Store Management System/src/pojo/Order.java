package pojo;

import java.time.LocalDateTime;

public class Order 
{
	int id;
	String custname;
	String medname;
	String medunit;
	int quantity;
	double total_Bill;
	String dateTime;
	public Order(int id, String custname, String medname,String medunit, int quantity, double total_Bill, String dateTime) 
	{
		super();
		this.id = id;
		this.custname = custname;
		this.medname = medname;
		this.medunit = medunit;
		this.quantity = quantity;
		this.total_Bill = total_Bill;
		this.dateTime = dateTime;
	}
	public Order(String custname, String medname, String medunit, int quantity, double total_Bill, String dateTime) 
	{
		super();
		this.custname = custname;
		this.medname = medname;
		this.medunit = medunit;
		this.quantity = quantity;
		this.total_Bill = total_Bill;
		this.dateTime = dateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getMedname() {
		return medname;
	}
	public void setMedname(String medname) {
		this.medname = medname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal_Bill() {
		return total_Bill;
	}
	public void setTotal_Bill(double total_Bill) {
		this.total_Bill = total_Bill;
	}
	
	public String getMedunit() {
		return medunit;
	}
	public void setMedunit(String medunit) {
		this.medunit = medunit;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", custname=" + custname + ", medname=" + medname + ", medunit=" + medunit
				+ ", quantity=" + quantity + ", total_Bill=" + total_Bill + ", dateTime=" + dateTime + "]";
	}
}

