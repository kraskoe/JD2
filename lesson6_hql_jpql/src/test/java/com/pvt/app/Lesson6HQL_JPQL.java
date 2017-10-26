package com.pvt.app;

import com.pvt.app.entity.Author;
import com.pvt.app.entity.Book;
import com.pvt.app.entity.User;
import com.pvt.app.entity.UserWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson6HQL_JPQL extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson6HQL_JPQL.class.getName());

    @Before
    public void initTest() {
        em.getTransaction().begin();
        User user1 = new User(null, "Vasya", "Vasev", 30, 1.8);
        User user2 = new User(null, "Ilya", "Solovyev", 20, 1.6);
        User user3 = new User(null, "Vasya", "Pupkin", 43, 1.9);
        User user4 = new User(null, "Sergey", "Shmatko", 32, 1.6);
        User user5 = new User(null, "Artem", "Kirzachev", 27, 1.7);
        User user6 = new User(null, "Nikolay", "Popov", 15, 1.8);
        User user7 = new User(null, "Sasha", "Popov", 24, 1.7);
        User user8 = new User(null, "Sergey", "Salin", 36, 1.8);
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
        em.persist(user4);
        em.persist(user5);
        em.persist(user6);
        em.persist(user7);
        em.persist(user8);

        Author author1 = new Author("George Martin");
        Author author2 = new Author("Herbert Wells");
        Author author3 = new Author("Nikolay Zlotnikov");
        Book book1 = new Book(null, "The song of ice and fire", author1);
        Book book2 = new Book(null, "Time machine", author2);
        Book book3 = new Book(null, "Vechniy", author3);
        Book book4 = new Book(null, "Shpagi nad zvezdami", author3);
        Book book5 = new Book(null, "Poslednyaya bitva", author3);
        Book book6 = new Book(null, "Moro's island", author2);
        Book book7 = new Book(null, "Invisible man", author2);
        author1.getBooks().add(book1);
        author2.getBooks().add(book2);
        author2.getBooks().add(book6);
        author2.getBooks().add(book7);
        author3.getBooks().add(book3);
        author3.getBooks().add(book4);
        author3.getBooks().add(book5);
        em.persist(author1);
        em.persist(author2);
        em.persist(author3);
        em.getTransaction().commit();
    }

    @Test
    public void hqlSelect() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("from User");
        // timeout - в milliseconds
        query.setTimeout(1000)
                // включить в кеш запросов
                .setCacheable(true)
                // добавлять в кэш, но не считывать из него
                .setCacheMode(CacheMode.REFRESH)
                .setHibernateFlushMode(FlushMode.COMMIT)
                // сущности и коллекции помечаюся как только для чтения
                .setReadOnly(true);
        query.list().forEach(System.out::println);
    }

    @Test
    public void hqlSelectById(){
        javax.persistence.Query query = em.createQuery("from User u where u.id = 1L");
        logger.error(query.getSingleResult());
    }

    @Test
    public void jpqlSelect() {
        javax.persistence.Query query = em.createQuery("from User");
        query.getResultList().forEach(System.out::println);
    }

    @Test
    public void jpqlSelectById() {
        javax.persistence.Query query = em.createQuery("from User u where u.id = 1L");
        logger.error(query.getSingleResult());
    }

    @Test
    public void orderByTest() {
        javax.persistence.Query query = em.createQuery("from User order by age desc");
        query.getResultList().forEach(System.out::println);
    }

    @Test
    public void parameterTest() {
        String name = "Vasya";
        javax.persistence.Query query = em.createQuery("from User u where u.name= :name");
        query.setParameter("name", name)
                .getResultList().forEach(System.out::println);
    }

    @Test
    public void parameterOrderTest() {
        javax.persistence.Query query = em.createQuery("from User u where u.name=? and u.age > :age");
        query.setParameter(0, "Vasya")
                .setParameter("age", 30)
                .getResultList().forEach(System.out::println);
    }

    @Test
    public void parameterListTest() {
        javax.persistence.Query query = em.createQuery("from User u where u.id in(:ids)");
        query.setParameter("ids", Stream.of(1L, 4L).collect(Collectors.toList()))
                .getResultList().forEach(System.out::println);
    }

    @Test
    public void groupByTest() {
        javax.persistence.Query query = em.createQuery("select count(u.name), u.name from User u group by u.name");
        query.getResultList().forEach(userInUsers -> {
            Object[] user = (Object[]) userInUsers;
            System.out.println("Имя: " + user[1] + " количество:" + user[0]);
        });
    }

    @Test
    public void groupByWithoutLambdaTest() {
        javax.persistence.Query query = em.createQuery("select count(u.name), u.name from User u group by u.name order by u.name desc");
        List<Object[]> list = query.getResultList();
        for (Object[] res : list) {
            System.out.println("Имя: " + res[1] + " количество:" + res[0]);
        }
    }

    @Test
    public void countTest() {
        javax.persistence.Query query = em.createQuery("select count(u.name) from User u");
        System.out.println(query.getSingleResult());
    }

    @Test
    public void countDistinctTest() {
        javax.persistence.Query query = em.createQuery("select count(distinct u.name) from User u");
        System.out.println(query.getSingleResult());
    }

    @Test
    public void leftJoinTest() {
        List<String> authors = em.createQuery(
                "select distinct a.name " +
                        "from Author a " +
                        "left join a.books b " +
                        "where b.title in ('Vechniy', 'Poslednyaya bitva')", String.class)
                .getResultList();
        authors.forEach(System.out::println);
    }

    @Test
    public void innerJoinTest() {
        List<String> authors = em.createQuery(
                "select distinct a.name " +
                        "from Author a " +
                        "inner join a.books b on b.title = 'Vechniy'")
                .getResultList();
        authors.forEach(System.out::println);
    }

    @Test
    public void aggregateTest(){
        javax.persistence.Query query = em.createQuery("select avg(u.age), min(u.age), max(u.age), sum(u.age) from User u");
        List<Object[]> list = query.getResultList();
        for (Object[] res : list) {
            System.out.println("Средний возраст: " + res[0] + ", минимальный возраст:" + res[1]
                    + ", максимальный возраст:" + res[2] + ", сумма возрастов:" + res[3]);
        }

    }

    @Test
    public void insertTest() {
        em.getTransaction().begin();
        Session session = em.unwrap(Session.class);
        Query query = session.createNativeQuery("insert into LESSON6_USER(id, name, surname, age, height) " +
                "VALUES (?, ?, ?, ?, ?)");
        System.out.println("Number of updated entries: " +
                query
                        .setParameter(1, 9L)
                        .setParameter(2, "Insert")
                        .setParameter(3, "Insertov")
                        .setParameter(4, 100)
                        .setParameter(5, 5L)
                        .executeUpdate());
        em.getTransaction().commit();
    }

//    @Test
//    public void insertTest() {
//        em.getTransaction().begin();
//        Session session = em.unwrap(Session.class);
//        Query query = session.createNativeQuery("insert into User(null, u.name, u.surname, u.age, u.height) " +
//                "select u.name, u.surname, u.age, u.height from User u where u.id = :id");
//        System.out.println("Number of updated entries: " +
//                query.setParameter("id", 1L)
//                        .executeUpdate());
//        em.getTransaction().commit();
//    }
//
    @Test
    public void updateTest() {
        em.getTransaction().begin();
        javax.persistence.Query query = em.createQuery("update User u set u.surname=:newSurname where u.surname=:oldSurname");
        System.out.println("Number of updated entries: " +
                query.setParameter("newSurname", "PumpkinMan")
                        .setParameter("oldSurname", "Pupkin")
                        .executeUpdate());
        em.getTransaction().commit();
}

    @Test
    public void deleteTest() {
        User user = new User(null, "Naf", "Naf", 100, 99d);
        em.getTransaction().begin();
        em.persist(user);
        javax.persistence.Query query = em.createQuery("delete from User u where u.id=:id");
        System.out.println("Number of deleted entries: " +
                query.setParameter("id", user.getId())
                        .executeUpdate());
        em.getTransaction().commit();
    }

//    @Test
//    public void wrapperTest(){
//        Session session = em.unwrap(Session.class);
//        session.createSQLQuery("select u.id as id, u.name as firstName, u.surname as surname from User u where u.name = :name")
//                .addScalar("id", StandardBasicTypes.LONG )
//                .addScalar("firstName", StandardBasicTypes.STRING )
//                .addScalar("surname", StandardBasicTypes.STRING )
//                .setParameter("name", "Artem")
//                .setResultTransformer(Transformers.aliasToBean(UserWrapper.class))
//                .list();
//    }

    @Test
    public void paginationTest(){
        int page = 2;
        int maxResults = 3;
        em.getTransaction().begin();
        javax.persistence.Query query = em.createQuery("from User");
        query.setFirstResult((page-1)*maxResults)
                .setMaxResults(maxResults);
        query.getResultList().forEach(System.out::println);

        em.getTransaction().commit();
    }

    @Test
    public void autoPaginationTest(){
        int maxResults = 2;
        int page;
        int max;
        int size;
        em.getTransaction().begin();
        javax.persistence.Query query = em.createQuery("from User");
        if ((size = query.getResultList().size()) % maxResults == 0){
            max = size / maxResults;
        } else max = size / maxResults + 1;
        for (int i = 1; i<= max; i++){
            page = i;
            query.setFirstResult((page-1)*maxResults)
                    .setMaxResults(maxResults);
            query.getResultList().forEach(System.out::println);
            System.out.println("Page " + page);
        }
        em.getTransaction().commit();
    }
}
