package hibernate;

import hibernate.entities.sda.Book;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.management.Query;
import java.sql.ResultSet;
import java.util.List;

public class BookOperations {


    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            //Dodaj nowe ksiazke

            Book testBook = new Book();
            testBook.setAuthor("Test Author");
            testBook.setPrice(100.0);
            testBook.setTitle("Test Title ");
            testBook.setQty(1);
            testBook.setId(1000);
            session.save(testBook);
            session.getTransaction().commit();
            session.close();

            session = sessionFactory.openSession();

            session.beginTransaction();
            Book newBook = session.get(Book.class, 1000);
            System.out.println("Newly created book:"+newBook);
            session.getTransaction().commit();
            session.close();

            session = sessionFactory.openSession();

            session.beginTransaction();
            Book updatedBook = session.get(Book.class, 1000);
            updatedBook.setAuthor("Updated Author");
            updatedBook.setPrice(50.0);
            updatedBook.setTitle("Updated Title ");
            updatedBook.setQty(55);
            System.out.println("Updated  book:"+updatedBook);
            session.getTransaction().commit();
            session.close();







           // session = sessionFactory.openSession();
           // session.beginTransaction();
           // Book deletedBook = session.get(Book.class, 1000);
           // System.out.println("Deleted  book:"+deletedBook);
           // session.delete(deletedBook);
           // session.getTransaction().commit();
           // session.close();
        }

       catch (Exception e) {
           System.out.println(e);
       }

    }
}
