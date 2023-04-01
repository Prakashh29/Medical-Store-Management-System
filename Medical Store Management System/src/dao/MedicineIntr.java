package dao;

import pojo.MedicineData;

public interface MedicineIntr 
{
	public boolean addMedicine(MedicineData md);
	public void showAllMedicines();
	public boolean searchMedicine(String mname);
	public boolean updateMedicine();
	public boolean deleteMedicine(int mid);
	public void orderDetails();
	
}

