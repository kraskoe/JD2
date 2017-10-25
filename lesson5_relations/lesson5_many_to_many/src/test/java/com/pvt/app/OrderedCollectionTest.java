package com.pvt.app;

import com.pvt.app.entity.Article;
import com.pvt.app.entity.Author;
import com.pvt.app.entity.Book;
import com.pvt.app.entity.Pamphlet;
import com.pvt.app.util.EMUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class OrderedCollectionTest extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(OrderedCollectionTest.class);
    @Before
    public void initTest() {
        em.getTransaction().begin();
        Author author = new Author("NewAuthor");
        Book alice = new Book(null, "Alice", author);
        author.getBooks().add(alice);
        author.getBooks().add(new Book(null, "War&Piece", author));
        author.getBooks().add(new Book(null, "Philipok", author));
        author.getArticles().add(new Article(null, "Na prospekte tank snes derevo", author));
        author.getArticles().add(new Article(null, "Creation of the universe", author));
        author.getArticles().add(new Article(null, "To beer or not to beer", author));
        author.getArticles().add(new Article(null, "Learning JAVA", author));
        author.getPamphlets().add(new Pamphlet(null, "Bla-bla-bla", author));
        author.getPamphlets().add(new Pamphlet(null, "Shu-shu-shu", author));
        author.getPamphlets().add(new Pamphlet(null, "In-Yan", author));
        author.getPamphlets().add(new Pamphlet(null, "Yan-In", author));
        em.persist(author);
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void saveTest() {
        Author authorFromDB = em.find(Author.class, 1l);
        List<Book> books = authorFromDB.getBooks();
        for (Book b:books) {
            logger.error(b.getTitle());
        }
        List<Article> articles = authorFromDB.getArticles();
        for (Article a:articles) {
            logger.error(a.getSummary());
        }
        List<Pamphlet> pamphlets = authorFromDB.getPamphlets();
        for (Pamphlet p:pamphlets) {
            logger.error(p.getPTitle());
        }
    }

}
