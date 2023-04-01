package pojo;

public class MedicineData 
{
	int mid,stock;
	String mname,company,type,units,expDate;
	double price;
	public MedicineData(int mid, String mname, String type,String company, String units, int stock, double price,String expDate) 
	{
		this.mid = mid;
		this.mname = mname;
		this.type = type;
		this.company = company;
		this.units = units;
		this.stock = stock;
		this.price = price;
		this.expDate = expDate;
	}
	
	public MedicineData(String mname, String type, String company, String units, int stock, double price,String expDate) 
	{
		this.stock = stock;
		this.mname = mname;
		this.type = type;
		this.company = company;
		this.units = units;
		this.price = price;
		this.expDate = expDate;
	}

	public String getUnits() 
	{
		return units;
	}

	public void setUnits(String units) 
	{
		this.units = units;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public int getMid() 
	{
		return mid;
	}
	public void setMid(int mid) 
	{
		this.mid = mid;
	}
	public String getMname()
	{
		return mname;
	}
	public void setMname(String mname) 
	{
		this.mname = mname;
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String company) 
	{
		this.company = company;
	}
	public int getStock()
	{
		return stock;
	}
	public void setStock(int stock) 
	{
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	@Override
	public String toString() {
		return "MedicineData [mid=" + mid + ", stock=" + stock + ", mname=" + mname + ", company=" + company + ", type="
				+ type + ", units=" + units + ", expDate=" + expDate + ", price=" + price + "]";
	}
}

