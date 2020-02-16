package hibernate;

import hibernate.entities.sda.Book;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;


public class DemoQueries {

    public static void main(String[] args) {

        Session session = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        session = sessionFactory.openSession();
        session.beginTransaction();


        System.out.println("Native Query results:\n");
        Query nativeQuery = session.createNativeQuery("SELECT * FROM books where qty >11");
        List<Object[]> books = nativeQuery.getResultList();


        for (Object[] object : books) {
            int pk = (Integer) object[0];
            Book book = session.find(Book.class,pk);
            session.detach(book);
            System.out.println(book.getTitle()+" "+book.getAuthor()+" "+book.getId());
       }

        System.out.println("\n\n\nQuery results:\n");
        Query hqlQuery = session.createQuery("FROM Book where qty >11");
        List<Book> booksList = hqlQuery.setMaxResults(5).getResultList();
        for (Book book : booksList) {
            System.out.println(book.getTitle()+" "+book.getAuthor()+" "+book.getId());
        }

        session.getTransaction().commit();
        session.close();
    }
}
