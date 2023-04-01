package dao;


import pojo.*;
import main.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*class PasswordSizeException extends RuntimeException
{
	public PasswordSizeException()
	{
		super("password size should be less than 8.");
	}
}*/
class OutOfStockException extends RuntimeException
{
	public OutOfStockException() 
	{
		super("Thanks for your order! Unfortunately, the following items from your order are out of stock");
	}
}
/*class InvalidChoiceException extends RuntimeException
{
	InvalidChoiceException()
	{
		super("Please enter valid choice..!!");
	}
}*/
public class UserImp implements UserIntr
{
	int option,stock;
	String mname,sqlQuery,again;
	double total=0,price;
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);
	Order o;
	String custname;
	String medname;
	String medunit;
	String datetime;
	int quantity;
	double total_Bill;
	public boolean registration(UserData ud)
	{
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			String sqlQuery = "insert into user(fullname,mobileNo,email,password) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, ud.getFullName());
			ps.setLong(2, ud.getMobileNo());
			ps.setString(3, ud.getEmail());
			ps.setString(4, ud.getPassword());
			
			ps.executeUpdate();
			con.close();
			flag=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public void login(String email,String password)
	{
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			//Statement st = con.createStatement();
			String sqlQuery = "Select*from user where email=? && password=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			System.out.println("-----------------------------------------------------");
			while(rs.next())
			{
				//System.out.println("+++++++++++++++++++++++++++++++++++++++");
				System.out.println("Hello "+rs.getString(2)+"\nWelcome to 24*7 Medical Store");
				custname=rs.getString(2); //for order table
				flag=true;
				System.out.println("-------------------------------------------------");
			}
			//System.out.println("----------------------------------------------------");
			con.close();
			if(flag)
			{
				do 
				{
					System.out.println("1.Show All Medicines     2.Search Medicine      3.Order Medicine & Generate bill     4.Your Orders      5.Logout ");
				    System.out.println("Enter your choice: ");
				    option = sc.nextInt();
				    switch(option)
				    {
				    case 1:
				    	showAllMedicines();
				    	break;
				    case 2:
				    	System.out.println("Enter Medicine name to serach: ");
				    	mname = sc1.nextLine();
						while(mname.trim().length()==0 || mname.trim().isEmpty())
						{
							System.out.println("Medicine Name cannot be empty..");
							System.out.println("Enter Medicine Name to search: ");
							mname = sc1.nextLine();
						}
				    	searchMedicine(mname);
				    	break;
				    case 3:
						order();
				    	break;
				    case 4:
				    	System.out.println("Enter your name to see previous orders: ");
				        String custname=sc1.nextLine();
				        while(custname.trim().length()==0 || custname.trim().isEmpty())
				        {
				        	System.out.println("Name cannot be empty..");
				        	System.out.println("Enter name to see your orders: ");
					        custname=sc1.nextLine();
				        }
				    	yourOrders(custname);
				    	break;
				    case 5:
				    	break;
				    default:
						try
						{
							throw new IllegalArgumentException("Unexpected value: " + option);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
				    }
				}while(option!=5);
			}
			else
			{
				System.out.println("Login failed..!!");
				System.out.println("Invalid username or password.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void searchMedicine(String mname)
	{
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			//Statement st = con.createStatement();
			String sqlQuery = "Select*from meds where mname=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, mname);
			ResultSet rs = ps.executeQuery();
			System.out.println("-----------------------------------------------------");
			while(rs.next())
			{
				//System.out.println("+++++++++++++++++++++++++++++++++++++++");
				/*System.out.println("Medicine id: "+rs.getInt(1)+
						      " \nMedicine Name: "+rs.getString(2)+
						            " \nCompany: "+rs.getString(3)+
						              " \nStock: "+rs.getInt(4)+
						              " \nPrice: "+rs.getDouble(5));*/
				System.out.println("Medicine id: "+rs.getInt(1)+
					          " \nMedicine Name: "+rs.getString(2)+
					          " \nMedicine Type: "+rs.getString(3)+
					                " \nCompany: "+rs.getString(4)+
					                  " \nUnits: "+rs.getString(5)+
					                  " \nStock: "+rs.getInt(6)+
					                  " \nPrice: "+rs.getDouble(7)+
					            " \nExpiry Date: "+rs.getString(8));
				flag=true;
				System.out.println("-------------------------------------------------");
			}
			//System.out.println("----------------------------------------------------");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(flag==false)
    	{
    		System.out.println("Medicine not found..!!");
    		System.out.println("-----------------------------------------------------");
    	}
	}
	public void showAllMedicines()
	{
		try
		{
			Connection con = DbConnection.connection();
			Statement st = con.createStatement();
			sqlQuery = "Select*from meds";
			ResultSet rs = st.executeQuery(sqlQuery+" ");
			System.out.println("---------------------------------------------------");
			while(rs.next())
			{
				//System.out.println("+++++++++++++++++++++++++++++++++++++++");
				/*System.out.println("Medicine id: "+rs.getInt(1)+
						      " \nMedicine Name: "+rs.getString(2)+
						            " \nCompany: "+rs.getString(3)+
						              " \nStock: "+rs.getInt(4)+
						              " \nPrice: "+rs.getDouble(5));*/
				System.out.println("Medicine id: "+rs.getInt(1)+
					          " \nMedicine Name: "+rs.getString(2)+
					          " \nMedicine Type: "+rs.getString(3)+
					                " \nCompany: "+rs.getString(4)+
					                  " \nUnits: "+rs.getString(5)+
					                  " \nStock: "+rs.getInt(6)+
					                  " \nPrice: "+rs.getDouble(7)+
					            " \nExpiry Date: "+rs.getString(8));
				System.out.println("+++++++++++++++++++++++++++++++++++++++");
			}
			//System.out.println("----------------------------------------------------");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void order()
	{
		boolean flag=false;
		System.out.println("Enter Medicine Name: ");
		mname = sc1.nextLine();
		while(mname.trim().length()==0 || mname.trim().isEmpty())
		{
			System.out.println("Name cannot be empty..");
			System.out.println("Enter Medicine Name: ");
			mname = sc1.nextLine();
		}
		try
		{
			Connection con = DbConnection.connection();
			Statement st = con.createStatement();
			sqlQuery = "select*from meds";
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next())
			{
				if(rs.getString(2).equals(mname))
				{
					System.out.println("Enter Quantity: ");
					stock = sc.nextInt();
					
					quantity = stock; //for order table
					
					if(stock>rs.getInt(6))
					{
						try
						{
							throw new OutOfStockException();
						}
						catch(OutOfStockException e)
						{
							e.printStackTrace();
						}	
						flag=true;
					}
					else
					{
						flag=true;
						price = rs.getDouble(7);
						total = total +(stock*price);
						
						medunit = rs.getString(5); //for order table
						medname = mname; //for order table
						total_Bill = total; //for order table
						
						LocalDateTime DateTime = LocalDateTime.now();
						DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy hh:mm:ss");
					    datetime = DateTime.format(format);
					    //System.out.println(datetimee);
						
						o = new Order(custname, medname, medunit, quantity, total_Bill, datetime);
						
						try
						{
							//con = DbConnection.connection();
							sqlQuery = "insert into orderData(custname,medname,medunit,quantity,total_Bill,dateTime) values(?,?,?,?,?,?)";
							PreparedStatement ps = con.prepareStatement(sqlQuery);
							ps.setString(1, o.getCustname());
							ps.setString(2, o.getMedname());
							ps.setString(3, o.getMedunit());
							ps.setInt(4, o.getQuantity());
							ps.setDouble(5, o.getTotal_Bill());
							ps.setString(6, o.getDateTime());
							int i=ps.executeUpdate();
							/*if(i>0)
							{
						      	System.out.println("order details inserted.. ");
							}
							else
							{
								System.out.println("order details not inserted..");
							}*/
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
						stock = rs.getInt(6) - stock;
						//System.out.println(stock);
						
						sqlQuery = "update meds set stock=? where mname=?";
						PreparedStatement ps = con.prepareStatement(sqlQuery);
						ps.setInt(1, stock);
						ps.setString(2, mname);
						ps.executeUpdate();
						
						
						System.out.print("Do you want to order anything(Y/N): ");
						again = sc.next();
						if(again.equalsIgnoreCase("Y"))
						{
							order();
						}
						else if(again.equalsIgnoreCase("N"))
						{
							generateBill();
							total=0;
							break;		
						}
						else
						{
							System.out.println("Invalid choice..!!");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(flag==false)
		{
			System.out.println("-----------------------------------------------");
			System.out.println("Medicine not found..!!");
			System.out.println("-----------------------------------------------");
			
			System.out.print("Do you want to order anything(Y/N): ");
			again = sc.next();
			if(again.equalsIgnoreCase("Y"))
			{
				order();
			}
			else if(again.equalsIgnoreCase("N"))
			{
				//generateBill();
				total=0;
			}
			else
			{
				System.out.println("Invalid choice..!!");
			}
		}
		
	}
	public void generateBill()
	{
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("********************Thank you for ordering**********************");
		System.out.println("Your total bill is: "+total);
		System.out.println("-----------------------------------------------------------------------");
	}
	public void yourOrders(String custname)
	{
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			//Statement st = con.createStatement();
			String sqlQuery = "Select*from orderData where custname=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, custname);
			ResultSet rs = ps.executeQuery();
			System.out.println("-----------------------------------------------------");
			while(rs.next())
			{
				System.out.println("Order id: "+rs.getInt(1)+
					       //" \nCustomer Name: "+rs.getString(2)+
					       " \nMedicine Name: "+rs.getString(3)+
	                  " \nMedicine Units(mg): "+rs.getString(4)+
	    		                " \nQuantity: "+rs.getInt(5)+
		                      " \nTotal Bill: "+rs.getDouble(6)+
		                   " \nDate and Time: "+rs.getString(7));
				flag=true;
				System.out.println("-------------------------------------------------");
			}
			//System.out.println("----------------------------------------------------");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(flag==false)
    	{
    		System.out.println("Order not found..!! Please check you've enter name is correct or not.");
    		System.out.println("-----------------------------------------------------");
    	}
	}
}

