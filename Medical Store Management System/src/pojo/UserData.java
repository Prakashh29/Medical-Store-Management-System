package pojo;


public class UserData 
{
	int id;
	String fullName;
	long mobileNo;
	String email;
	String password;
	
	public UserData(int id, String fullName, long mobileNo, String email, String password) 
	{
		this.id = id;
		this.fullName = fullName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
	}

	public UserData(String fullName, long mobileNo, String email, String password) {
		super();
		this.fullName = fullName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFullName() 
	{
		return fullName;
	}

	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}

	public long getMobileNo() 
	{
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) 
	{
		this.mobileNo = mobileNo;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
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
		return "UserData [id=" + id + ", fullName=" + fullName + ", mobileNo=" + mobileNo + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
	
	
}


