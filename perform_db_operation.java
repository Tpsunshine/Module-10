
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Perform_db_operation {

    private static SessionFactory factory;


    //Obtains the session factory.
    public static void getSessionFactory()
    {

        try{
            Configuration conf = new Configuration().configure();
            StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
            factory= conf.buildSessionFactory(builder.build());

        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static void main(String[] args) {

        try
        {

            getSessionFactory();
            Perform_db_operation client_1 = new Perform_db_operation();

            /* Add few employee records in database */
            client_1.InsertRecordInDatabase("Suresh", 99);
            client_1.InsertRecordInDatabase("Ravi", 100);
            client_1.InsertRecordInDatabase("Nidu", 28);


            /* List down all the employees */
            System.out.println("Listing employees..");
            client_1.DisplayRecords();


            /* Update employee's records */
            System.out.println("UPdated the record..");
            client_1.UpdateRecord(1, 100);
            client_1.DisplayRecords();


            /* Delete an employee from the database */
            System.out.println("Deleted the 2nd Record...");
            client_1.DeleteRecord();

            /* List down new list of the employees */
            System.out.println("Listing all the employees...");
            client_1.DisplayRecords();
        }
        catch (HibernateException e)
        {
            System.out.println("Exception is : " + e);
        }
    }

    /* Method to CREATE an employee in the database */
    public void InsertRecordInDatabase(String name, int marks) throws HibernateException
    {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();;

        student3 e1 = new student3(name,marks);
        session.save(e1);
        tx.commit();

        session.close();

    }

    /* Method to  READ all the employees */
    public void DisplayRecords( ) throws HibernateException{
        Session session = factory.openSession();
        List empLst = session.createQuery("FROM student3").list();
        for (Iterator iterator =
             empLst.iterator(); iterator.hasNext();){
            student3 emp = (student3) iterator.next();
            System.out.print("id: " + emp.id);
            System.out.print("  Name: " + emp.name);
            System.out.println(" Marks: " + emp.marks);

        }
        session.close();
    }



    /* Method to UPDATE salary for an employee */
    public void UpdateRecord(Integer StudentId, int marks1 ) throws HibernateException
    {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();;

        student3 st1 =
                (student3)session.get(student3.class, StudentId);
        st1.marks = marks1;
        session.update(st1);
        tx.commit();
        session.close();
    }


    /* Method to DELETE an employee from the records */
    public void DeleteRecord() throws HibernateException
    {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from student3 where marks < 35";
        Query query = session.createQuery(hql);
        query.executeUpdate();

        tx.commit();
        session.close();

    }
}