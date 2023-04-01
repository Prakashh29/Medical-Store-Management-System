package pojo;


public class AdminData 
{
	int id;
	String username;
	long mobileNo;
	String password;
	public AdminData(int id, String username, long mobileNo, String password)
	{
		super();
		this.id = id;
		this.username = username;
		this.mobileNo = mobileNo;
		this.password = password;
	}
	public AdminData(String username, long mobileNo, String password)
	{
		super();
		this.username = username;
		this.mobileNo = mobileNo;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public long getMobileNo() 
	{
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) 
	{
		this.mobileNo = mobileNo;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	@Override
	public String toString() 
	{
		return "AdminData [id=" + id + ", username=" + username + ", mobileNo=" + mobileNo + ", password=" + password
				+ "]";
	}
}

