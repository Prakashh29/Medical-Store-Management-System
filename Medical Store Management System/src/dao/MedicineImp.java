package dao;

import pojo.MedicineData;
import pojo.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MedicineImp implements MedicineIntr
{
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);
	//MedicineImp mi = new MedicineImp();
	UserImp ui = new UserImp();
	public boolean registration(AdminData ad)
	{
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			String sqlQuery = "insert into admin(username,mobileNo,password) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, ad.getUsername());
			ps.setLong(2, ad.getMobileNo());
			ps.setString(3, ad.getPassword());
	
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
	public void login(String username,String password)
	{
		MedicineData md;
		int mid;
		String mname;
		int choice=0;
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			//Statement st = con.createStatement();
			String sqlQuery = "Select*from admin where username=? && password=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			System.out.println("-----------------------------------------------------");
			while(rs.next())
			{
				//System.out.println("+++++++++++++++++++++++++++++++++++++++");
				System.out.println("Hello "+rs.getString(2));
				flag=true;
				System.out.println("-------------------------------------------------");
			}
			//System.out.println("----------------------------------------------------");
			con.close();
			if(flag)
			{
				do 
				{
					System.out.println("-----------------------Welcome to 24*7 Medical store--------------------------");
					System.out.println("1.Add Medicine");
					System.out.println("2.Show All Medicines");
					System.out.println("3.Search Medicine");
					System.out.println("4.Update Medicine");
					System.out.println("5.Delete Medicine");
					System.out.println("6.show Order Details");
					System.out.println("7.Logout");
					System.out.println("Enter your choice: ");
					choice = sc.nextInt();
					switch(choice)
					{
					case 1:
						System.out.println("Enter Medicine Name: ");
						mname = sc1.nextLine();
						while(mname.trim().length()==0 || mname.trim().isEmpty())
						{
							System.out.println("Name cannot be empty..");
							System.out.println("Enter Medicine Name: ");
							mname = sc1.nextLine();
						}
						System.out.println("Enter Medicine Type: ");
						String type = sc1.nextLine();
						while(type.trim().length()==0 || type.trim().isEmpty())
						{
							System.out.println("Medicine type cannot be empty..");
							System.out.println("Enter Medicine Type: ");
							type = sc1.nextLine();
						}
						System.out.println("Enter Company name: ");
						String company = sc1.nextLine();
						while(company.trim().length()==0 || company.trim().isEmpty())
						{
							System.out.println("Company name cannot be empty..");
							System.out.println("Enter Company name: ");
							company = sc1.nextLine();
						}
						System.out.println("Enter Medicine Units(mg): ");
						String units = sc1.nextLine();
						while(units.trim().length()==0 || units.trim().isEmpty())
						{
							System.out.println("Medicine units cannot be empty..");
							System.out.println("Enter Medicine Units(mg): ");
							units = sc1.nextLine();
						}
						System.out.println("Enter Medicine Stock: ");
						int stock = sc.nextInt();
						System.out.println("Enter Medicine Price: ");
						double price = sc.nextDouble();
						System.out.println("Enter Medicine Expiry Date(dd-MM-yy): ");
						String expDate = sc1.nextLine();
						while(expDate.trim().length()==0 || expDate.trim().isEmpty())
						{
							System.out.println("Expiry date cannot be empty..");
							System.out.println("Enter Medicine Expiry Date(yyyy-MM-dd): ");
							expDate = sc1.nextLine();
						}
						md = new MedicineData(mname, type, company, units, stock, price, expDate);
						flag = addMedicine(md);
						if(flag)
						{
							System.out.println("Medicine is added successfully..");
						}
						else
						{
							System.out.println("Please try again..\nMedicine not added successfully.");
						}
					    break;
					case 2:
						showAllMedicines();
						break;
					case 3:
						System.out.println("Enter Medicine name to search: ");
						mname = sc1.nextLine();
						while(mname.trim().length()==0 || mname.trim().isEmpty())
						{
							System.out.println("Name cannot be empty..");
							System.out.println("Enter Medicine Name to search: ");
							mname = sc1.nextLine();
						}
						flag = searchMedicine(mname);
						if(flag==false)
						{
							System.out.println("Medicine not found.");
						}
						break;
					case 4:
					    flag = updateMedicine();
						if(flag==false)
						{
							System.out.println("Medicine is not updated.");
						}
					    break;
					case 5:
						System.out.println("Enter Medicine id to delete: ");
						mid = sc.nextInt();
						flag = deleteMedicine(mid);
						if(flag)
						{
							System.out.println("Medicine deleted succesfully..");
						}
						else
						{
							System.out.println("Please try again..\nMedicine is not deleted succesfully.");
						}
					    break;
					case 6:
						orderDetails();
					    break;
					case 7:
						break;
					default:
						try
						{
							throw new IllegalArgumentException("Unexpected value: " + choice);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}while(choice!=7);
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
	public boolean addMedicine(MedicineData md)
	{
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			String sqlQuery = "insert into meds(mname,type,company,units,stock,price,expDate) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, md.getMname());
			ps.setString(2, md.getType());
			ps.setString(3, md.getCompany());
			ps.setString(4, md.getUnits());
			ps.setInt(5, md.getStock());
			ps.setDouble(6, md.getPrice());
			ps.setString(7, md.getExpDate());
			
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
	public boolean deleteMedicine(int mid)
	{
		boolean flag=false;
		try
		{
			Connection con = DbConnection.connection();
			String sqlQuery = "delete from meds where mid=?";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, mid);
			ps.executeUpdate();
			flag=true;
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public void showAllMedicines()
	{
		try
		{
			Connection con = DbConnection.connection();
			Statement st = con.createStatement();
			String sqlQuery = "Select*from meds";
			ResultSet rs = st.executeQuery(sqlQuery+" ");
			System.out.println("---------------------------------------------------");
			while(rs.next())
			{
				//System.out.println("+++++++++++++++++++++++++++++++++++++++");
				System.out.println("Medicine id: "+rs.getInt(1)+
						      " \nMedicine Name: "+rs.getString(2)+
						       "\nMedicine Type: "+rs.getString(3)+
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
	public boolean searchMedicine(String mname)
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
		return flag;
	}
	public boolean updateMedicine()
	{
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		int choice=0;
		double price;
		String mname,company,type,units;
		int stock,mid;
		boolean flag=false;
		try
		{
			
			Connection con = DbConnection.connection();
			String sql;
			do
			{
				System.out.println("1.update Stock");
				System.out.println("2.update Medicine name");
				System.out.println("3.update Medicine type");
				System.out.println("4.update Company name");
				System.out.println("5.update Medicine units");
				System.out.println("6.update Price");
				System.out.println("7.update Expiry Date");
				System.out.println("8.Main menu");
				System.out.println("Enter you choice: ");
				choice = sc.nextInt();
				switch(choice)
				{
				case 1:
					System.out.println("Enter medicine id to update: ");
					mid = sc.nextInt();
					System.out.println("Update Stock: ");
					stock = sc.nextInt();
					sql = "update meds set stock=? where mid=? ";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, stock);
					ps.setInt(2, mid);
					int i=ps.executeUpdate();
					flag=true;
					con.close();
					if(i>0)
					{
						System.out.println("Medicine stock updated successfully..");
					}
					else
					{
						System.out.println("Medicine stock not updated.");
					}
					break;
				case 2:
					System.out.println("Enter medicine id to update: ");
					mid = sc.nextInt();
					System.out.println("Enter new medicine name: ");
					mname = sc1.nextLine();
					while(mname.trim().length()==0 || mname.trim().isEmpty())
					{
						System.out.println("Name cannot be empty..");
						System.out.println("Enter Medicine Name: ");
						mname = sc1.nextLine();
					}
					sql = "update meds set mname=? where mid=? ";
					ps = con.prepareStatement(sql);
					ps.setString(1, mname);
					ps.setInt(2, mid);
					i=ps.executeUpdate();
					flag=true;
					con.close();
					if(i>0)
					{
						System.out.println("Medicine name updated sucessfully..");
					}
					else
					{
						System.out.println("Medicine name not updated.");
					}
					break;
				case 3:
					System.out.println("Enter medicine id to update: ");
					mid = sc.nextInt();
					System.out.println("Enter new medicine type: ");
					type = sc1.nextLine();
					while(type.trim().length()==0 || type.trim().isEmpty())
					{
						System.out.println("Medicine type cannot be empty..");
						System.out.println("Enter Medicine Type: ");
						type = sc1.nextLine();
					}
					sql = "update meds set type=? where mid=? ";
					ps = con.prepareStatement(sql);
					ps.setString(1, type);
					ps.setInt(2, mid);
					i=ps.executeUpdate();
					flag=true;
					con.close();
					if(i>0)
					{
						System.out.println("Medicine type updated sucessfully..");
					}
					else
					{
						System.out.println("Medicine type not updated.");
					}
					break;
				case 4:
					System.out.println("Enter medicine id to update: ");
					mid = sc.nextInt();
					System.out.println("Enter new company name: ");
					company = sc1.nextLine();
					sql = "update meds set company=? where mid=? ";
					ps = con.prepareStatement(sql);
					ps.setString(1, company);
					ps.setInt(2, mid);
					i=ps.executeUpdate();
					flag=true;
					con.close();
					if(i>0)
					{
						System.out.println("Company name updated sucessfully..");
					}
					else
					{
						System.out.println("Company name not updated.");
					}
					break;
				case 5:
					System.out.println("Enter medicine id to update: ");
					mid = sc.nextInt();
					System.out.println("Enter new Medicine units(mg): ");
					units = sc1.nextLine();
					sql = "update meds set units=? where mid=? ";
					ps = con.prepareStatement(sql);
					ps.setString(1, units);
					ps.setInt(2, mid);
					i=ps.executeUpdate();
					flag=true;
					con.close();
					if(i>0)
					{
						System.out.println("Medicine unit updated sucessfully..");
					}
					else
					{
						System.out.println("Medicine unit not updated.");
					}
					break;
				case 6:
					System.out.println("Enter medicine id to update: ");
					mid = sc.nextInt();
					System.out.println("Enter new Medicine price: ");
					price = sc.nextDouble();
					sql = "update meds set price=? where mid=? ";
					ps = con.prepareStatement(sql);
					ps.setDouble(1, price);
					ps.setInt(2, mid);
					i=ps.executeUpdate();
					flag=true;
					con.close();
					if(i>0)
					{
						System.out.println("Medicine price updated sucessfully..");
					}
					else
					{
						System.out.println("Medicine price not updated.");
					}
					break;
				case 7:
					System.out.println("Enter medicine id to update: ");
					mid = sc.nextInt();
					System.out.println("Enter new Exipry Date(yyyy-MM-dd): ");
					String expDate = sc1.nextLine();
					sql = "update meds set expDate=? where mid=? ";
					ps = con.prepareStatement(sql);
					ps.setString(1, expDate);
					ps.setInt(2, mid);
					i=ps.executeUpdate();
					flag=true;
					//con.close();
					if(i>0)
					{
						System.out.println("Medicine Expiry Date updated sucessfully..");
					}
					else
					{
						System.out.println("Medicine Expiry Date not updated.");
					}
					break;
				case 8:
					break;
				default:
					try
					{
						throw new IllegalArgumentException("Unexpected value: " + choice);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}while(choice!=8);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public void orderDetails()
	{
		try
		{
			Connection con = DbConnection.connection();
			Statement st = con.createStatement();
			String sqlQuery = "Select*from orderData";
			ResultSet rs = st.executeQuery(sqlQuery+" ");
			System.out.println("---------------------------------------------------");
			while(rs.next())
			{
				System.out.println("Order id: "+rs.getInt(1)+
					       " \nCustomer Name: "+rs.getString(2)+
					       " \nMedicine Name: "+rs.getString(3)+
					  " \nMedicine Units(mg): "+rs.getString(4)+
				                " \nQuantity: "+rs.getInt(5)+
		                      " \nTotal Bill: "+rs.getDouble(6)+
				           " \nDate and Time: "+rs.getString(7));
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
}


