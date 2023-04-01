package main;

import dao.*;
import pojo.*;

import java.util.Scanner;

class PasswordSizeException extends RuntimeException
{
	public PasswordSizeException()
	{
		super("password size should be less than 8.");
	}
}
/*class OutOfStockException extends RuntimeException
{
	public OutOfStockException() 
	{
		super("Thanks for your order! Unfortunately, the following items from your order are out of stock");
	}
}*/
public class Test 
{
	public static void main(String[] args) 
	{
		String fullname,email,password;
		long mobileNo;
		boolean flag;
		AdminData ad;
		UserImp ui = new UserImp();
		MedicineImp mi = new MedicineImp();
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		int choice,ch,option=0;
		do
		{
			System.out.println("1.User       2.Admin      3.Exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				do
				{
					System.out.println("-----------------------Welcome to 24*7 Medical store--------------------------");
					System.out.println("1.Login       2.Registration       3.Main menu");
					System.out.println("Enter your choice: ");
					ch = sc.nextInt();
					switch(ch)
					{
					case 1:
						System.out.println("Enter Email Address: ");
						email = sc1.nextLine();
						while(email.trim().length()==0 || email.trim().isEmpty())
						{
							System.out.println("Email cannot be empty..");
							System.out.println("Enter Email Address: ");
							email = sc1.nextLine();
						}
						System.out.println("Enter Password: ");
						password = sc1.nextLine();
						while(password.trim().length()==0 || password.trim().isEmpty())
						{
							System.out.println("Password cannot be empty..");
							System.out.println("Enter Password: ");
							password = sc1.nextLine();
						}
						while(password.length()>8)
						{
							try
							{
								throw new PasswordSizeException();
							}
							catch(PasswordSizeException e)
							{
								e.printStackTrace();
							}
							System.out.println("Create Password: ");
							password = sc1.nextLine();
						}
						ui.login(email,password);
						break;
					case 2:
						System.out.println("Enter Full Name: ");
						fullname = sc1.nextLine();
						while(fullname.trim().length()==0 || fullname.trim().isEmpty())
						{
							System.out.println("Name cannot be empty..");
							System.out.println("Enter Full Name: ");
							fullname = sc1.nextLine();
						}
						System.out.println("Enter Mobile Number: ");
						mobileNo = sc.nextLong();
						System.out.println("Enter Email Address: ");
						email = sc1.nextLine();
						while(email.trim().length()==0 || email.trim().isEmpty())
						{
							System.out.println("Email cannot be empty..");
							System.out.println("Enter Email Address: ");
							email = sc1.nextLine();
						}
						System.out.println("Create Password: ");
						password = sc1.nextLine();
						while(password.trim().length()==0 || password.trim().isEmpty())
						{
							System.out.println("Password cannot be empty..");
							System.out.println("Create Password: ");
							password = sc1.nextLine();
						}
						while(password.length()>8)
						{
							try
							{
								throw new PasswordSizeException();
							}
							catch(PasswordSizeException e)
							{
								e.printStackTrace();
							}
							System.out.println("Create Password: ");
							password = sc1.nextLine();
						}
						UserData ud = new UserData(fullname, mobileNo, email, password);
						flag = ui.registration(ud);
						if(flag)
						{
							System.out.println("Registration is successfull..");
						}
						else
						{
							System.out.println("Please try again...\nRegistration is not successfull.");
						}
						break;
					case 3:
						break;
					default:
						try
						{
							throw new IllegalArgumentException("Unexpected value: " + ch);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}while(ch!=3);
				break;
			case 2:
				do
				{
					System.out.println("1.Admin Registration       2.Admin Login        3.Main menu");
					System.out.println("Enter your choice: ");
					option = sc.nextInt();
					switch(option)
					{
					case 1:
						System.out.println("Enter Username: ");
						String username = sc1.nextLine();
						while(username.trim().length()==0 || username.trim().isEmpty())
						{
							System.out.println("Username cannot be empty..");
							System.out.println("Enter Username: ");
							username = sc1.nextLine();
						}
						System.out.println("Enter Mobile Number: ");
						mobileNo = sc.nextLong();
						System.out.println("Create Password: ");
						password = sc1.nextLine();
						while(password.trim().length()==0 || password.trim().isEmpty())
						{
							System.out.println("Password cannot be empty..");
							System.out.println("Create Password: ");
							password = sc1.nextLine();
						}
						while(password.length()>8)
						{
							try
							{
								throw new PasswordSizeException();
							}
							catch(PasswordSizeException e)
							{
								e.printStackTrace();
							}
							System.out.println("Create Password: ");
							password = sc1.nextLine();
						}
						ad = new AdminData(username, mobileNo, password);
						flag = mi.registration(ad);
						if(flag)
						{
							System.out.println("Admin registration is successfull..");
						}
						else
						{
							System.out.println("Something went wrong. Please try again.");
						}
						break;
					case 2:
						System.out.println("Enter Username: ");
						username = sc1.nextLine();
						while(username.trim().length()==0 || username.trim().isEmpty())
						{
							System.out.println("Username cannot be empty..");
							System.out.println("Enter Username: ");
							username = sc1.nextLine();
						}
						System.out.println("Enter Password: ");
						password = sc1.nextLine();
						while(password.trim().length()==0 || password.trim().isEmpty())
						{
							System.out.println("Password cannot be empty..");
							System.out.println("Enter Password: ");
							password = sc1.nextLine();
						}
						while(password.length()>8)
						{
							try
							{
								throw new PasswordSizeException();
							}
							catch(PasswordSizeException e)
							{
								e.printStackTrace();
							}
							System.out.println("Create Password: ");
							password = sc1.nextLine();
						}
						mi.login(username,password);
						break;
					case 3:
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
					
				}while(option!=3);
				break;
			case 3:
				System.out.println("Thank you for visting. Have a nice day..");
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
		}while(choice!=3);
	}
}

