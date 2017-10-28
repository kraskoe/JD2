package com.pvt.app;

import com.pvt.app.entity.Author;
import com.pvt.app.entity.Book;
import com.pvt.app.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson7Criteria extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson7Criteria.class.getName());

    @SuppressWarnings("Duplicates")
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

        em.getTransaction().commit();
    }

    @Test
    public void selectTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        List<User> users = em.createQuery(criteria).getResultList();
        users.forEach(System.out::println);
    }

    @Test
    public void selectClauseTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).where(cb.equal(userRoot.get("id"), 1L));
        List<User> users = em.createQuery(criteriaQuery).getResultList();
        users.forEach(System.out::println);
    }

    @Test
    public void greaterTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot)
                .where(cb.gt(userRoot.get("age"), 30));
        List<User> users = em.createQuery(criteriaQuery).getResultList();
        users.forEach(System.out::println);
    }

    @Test
    public void likeMultiselectTupleTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Path<Integer> userAge = userRoot.get("age");
        Path<String> userSurname = userRoot.get("surname");
        criteriaQuery.multiselect(userAge,userSurname);
        criteriaQuery.where(cb.like(userRoot.get("name"), "%as%"));
        List<Tuple> usersSummary = em.createQuery(criteriaQuery).getResultList();
        usersSummary.forEach(s-> {
            int age = (int) s.get(0);
            String surname = (String) s.get(1);
            System.out.println("User surname: " + surname + ", age: " + age);
        });
    }

    @Test
    public void likeMultiselectObjectTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = cb.createQuery(Object[].class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Path<Integer> userAge = userRoot.get("age");
        Path<String> userSurname = userRoot.get("surname");
        criteriaQuery.multiselect(userAge,userSurname);
        criteriaQuery.where(cb.like(userRoot.get("name"), "%as%"));
        List<Object[]> usersSummary = em.createQuery(criteriaQuery).getResultList();
        for ( Object[] value : usersSummary ) {
            Integer age = (Integer) value[0];
            String surname = (String) value[1];
            System.out.println("User surname: " + surname + ", age: " + age);
        }
    }

    @Test
    public void betweenTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(cb.between(userRoot.get("age"), 30, 35));
        em.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
    }

    @Test
    public void nullTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(cb.isNotNull(userRoot.get("id")));
        em.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
    }

    @Test
    public void predicateTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = cb.or(
                cb.le(userRoot.get("age"), 20),
                cb.and(
                        cb.ge(userRoot.get("age"), 32),
                        cb.like(userRoot.get("surname"), "S%")
                )
        );
        criteriaQuery.where(predicate);
        em.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
    }

    @Test
    public void aggregateMultiselectTupleTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Expression<Long> namesDistinctCount = cb.countDistinct(userRoot.get("name"));
        Expression<Double> avgAge = cb.avg(userRoot.get("age"));
        Expression<Integer> minAge = cb.min(userRoot.get("age"));
        Expression<Integer> maxAge = cb.max(userRoot.get("age"));
        Expression<Integer> sumAge = cb.sum(userRoot.get("age"));
        criteriaQuery.multiselect(namesDistinctCount,avgAge, minAge, maxAge, sumAge);
        List<Tuple> usersSummary = em.createQuery(criteriaQuery).getResultList();
        usersSummary.forEach(s-> {
            long distCount = (long) s.get(0);
            double avg = (double) s.get(1);
            int min = (int) s.get(2);
            int max = (int) s.get(3);
            long sum = (long) s.get(4);

//            !!!  sum возвращает long?
//            int sum = (int) s.get(4);
            System.out.println("User distinct names count: " + distCount + ", average age: " + avg
                    + ", min age: " + min + ", max age: " + max + ", sum age: " + sum);
        });
    }

    @Test
    public void joinTest(){
        em.getTransaction().begin();
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

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = cb.createQuery(Author.class);
        Root<Author> authorRoot = criteriaQuery.from(Author.class);
        Join<Author, Book> bookJoin = authorRoot.join("books", JoinType.INNER);
        criteriaQuery.where(cb.like(bookJoin.get("title"), "%zvez%"));
        List<Author> authors = em.createQuery(criteriaQuery).getResultList();
        for (Author a:authors) {
            System.out.println(a.getName());
        }
    }

    @Test
    public void orderByTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = cb.createQuery(String.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot.get("name")).orderBy(cb.desc(userRoot.get("name")));
        em.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
    }

    @Test
    public void groupTest(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.groupBy(userRoot.get("name"));
        criteriaQuery.multiselect(userRoot.get("name"), cb.count(userRoot));

        List<Tuple> users = em.createQuery( criteriaQuery ).getResultList();
        users.forEach(u->{
            String name = (String) u.get( 0 );
            long count = (long) u.get( 1 );
            System.out.println("Name:" + name + " count:" + count);
        });

    }

    @Test
    public void autoPaginationTest(){
        int maxResults = 2;
        int page;
        int max;
        int size;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        criteriaQuery.from(User.class);
        TypedQuery<User> typedQuery = em.createQuery(criteriaQuery);

        if ((size = typedQuery.getResultList().size()) % maxResults == 0){
            max = size / maxResults;
        } else max = size / maxResults + 1;
        for (int i = 1; i<= max; i++){
            page = i;
            typedQuery.setFirstResult((page-1)*maxResults)
                    .setMaxResults(maxResults);
            typedQuery.getResultList().forEach(System.out::println);
            System.out.println("Page " + page);
        }
    }

    @Test
    public void fetchTest(){
        em.getTransaction().begin();
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

        Long idParam = 5L;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);

//        Зачем здесь fetch?
        bookRoot.fetch("author");
        ParameterExpression<Long> idParameter = cb.parameter(Long.class);
        criteriaQuery.where(cb.gt(bookRoot.get("id"), idParameter));
        List<Book> books = em.createQuery(criteriaQuery).setParameter(idParameter, idParam).getResultList();
        for (Book b:books) {
            System.out.println(b.getTitle() + " - " + b.getAuthor().getName());
        }
    }

}
