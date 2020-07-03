package net.mytutorial.springmvc.dao;

import java.util.List;

import net.mytutorial.springmvc.entity.Customer;

public interface CustomerDAO {

    public List < Customer > getCustomers();

    public void saveCustomer(Customer theCustomer);

    public Customer getCustomer(int theId);

    public void deleteCustomer(int theId);
}