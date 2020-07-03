package net.mytutorial.springmvc.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mytutorial.springmvc.config.AppContext;
import net.mytutorial.springmvc.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //@Autowired
    //private SessionFactory sessionFactory;

    @Override
    public List < Customer > getCustomers() {
    	Query query;
    	Session session = AppContext.getSessionFactory().openSession();
    	Transaction transaction;
    	if ( session != null )
    	{
    		transaction = session.beginTransaction();
    		CriteriaBuilder cb = session.getCriteriaBuilder();
    	    CriteriaQuery < Customer > cq = cb.createQuery(Customer.class);
    	    Root < Customer > root = cq.from(Customer.class);
    	    cq.select(root);
    	    query = session.createQuery(cq);
    	}else
    	{
    		session = AppContext.getSessionFactory().openSession();
    		transaction = session.beginTransaction();
    		CriteriaBuilder cb = session.getCriteriaBuilder();
    	    CriteriaQuery < Customer > cq = cb.createQuery(Customer.class);
    	    Root < Customer > root = cq.from(Customer.class);
    	    cq.select(root);
    	    query = session.createQuery(cq);
    	}
    	
    	List<Customer> customers = query.getResultList();
    	session.close();
        //Session session = sessionFactory.getCurrentSession();
        return customers;
    }

    @Override
    public void deleteCustomer(int id) {
        //Session session = sessionFactory.getCurrentSession();
    	Session session = AppContext.getSessionFactory().getCurrentSession();
    	Transaction transaction;
    	if ( session != null )
    	{    		
    		transaction = session.beginTransaction();
    		Customer book = session.byId(Customer.class).load(id);
            session.delete(book);
            transaction.commit();
            session.close();
    	}else
    	{
    		session = AppContext.getSessionFactory().openSession();
    		transaction = session.beginTransaction();
    		Customer book = session.byId(Customer.class).load(id);
            session.delete(book);
            transaction.commit();
            session.close();
    	}
    	
        
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        //Session currentSession = sessionFactory.getCurrentSession();
    	Session session = AppContext.getSessionFactory().getCurrentSession();
    	Transaction transaction;
    		
    	if ( session != null )
    	{
    		transaction = session.beginTransaction();
    		session.saveOrUpdate(theCustomer);
    		transaction.commit();
    		session.close();
    	}else
    	{
    		session = AppContext.getSessionFactory().openSession();
    		transaction = session.beginTransaction();
        	session.saveOrUpdate(theCustomer);
        	transaction.commit();
        	session.close();
    	}
    	
    }

    @Override
    public Customer getCustomer(int theId) {
        //Session currentSession = sessionFactory.getCurrentSession();
    	Customer theCustomer;
    	Session session = AppContext.getSessionFactory().getCurrentSession();
    	Transaction transaction;
    	if ( session != null )
    	{
    		transaction = session.beginTransaction();
    		theCustomer = session.get(Customer.class, theId);
    		session.close();
    	}else
    	{
    		session = AppContext.getSessionFactory().openSession();
    		transaction = session.beginTransaction();
    		theCustomer = session.get(Customer.class, theId);
    		session.close();
    	}
        return theCustomer;
    }
}
