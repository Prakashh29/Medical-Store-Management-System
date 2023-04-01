package dao;

import pojo.*;

public interface UserIntr 
{
	public boolean registration(UserData ud);
	public void login(String email,String password);
	public void showAllMedicines();
	public void order();
	public void generateBill();
	public void yourOrders(String custname);
}

