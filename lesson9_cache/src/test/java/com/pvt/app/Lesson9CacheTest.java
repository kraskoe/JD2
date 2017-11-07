package com.pvt.app;

import com.pvt.app.entity.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yauheni Krasko on 08.10.2017.
 */
public class Lesson9CacheTest extends PrepareTest {
    private static final Logger logger = LogManager.getLogger(Lesson9CacheTest.class);

    @Before
    public void initTest(){
        em.getTransaction().begin();
        Author author1 = new Author(null, "Lev Tolstoy", "lev_tolstoy", null);
        em.persist(author1);
        Author author2 = new Author(null, "Alexander Dyuma", "al_dyuma", null);
        em.persist(author2);
        Author author3 = new Author(null, "Alexander Zlotnikov", "al_zl", null);
        em.persist(author3);
        Author author4 = new Author(null, "Shura Detochkin", "sh_det", null);
        em.persist(author4);
        Author author5 = new Author(null, "Alexander Zlotnikov", "al_scum", null);
        em.persist(author5);
        em.getTransaction().commit();

    }

//    Parameter timeTiLiveSeconds doesn't work?
    @Test
    public void cacheEntityTest(){
        Session session = em.unwrap(Session.class);
        Author authorFromDB = session.byNaturalId(Author.class).using("code", "lev_tolstoy").load();
        System.out.println(authorFromDB);
        System.out.println(getAuthor(2L));
        em.clear();
        System.out.println(getAuthor(1L));
        System.out.println(getAuthor(2L));
        em.clear();
        System.out.println(session.byNaturalId(Author.class).using("code", "lev_tolstoy").load());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println(session.byNaturalId(Author.class).using("code", "lev_tolstoy").load());

        Statistics statistics = session.getSessionFactory().getStatistics();
        SecondLevelCacheStatistics secondLevelCacheStatistics =
                statistics.getSecondLevelCacheStatistics( "query.cache.person" );
        long hitCount = secondLevelCacheStatistics.getHitCount();
        long missCount = secondLevelCacheStatistics.getMissCount();
        double hitRatio = (double) hitCount / ( hitCount + missCount );
        System.out.println(hitCount + " " + missCount + " " + hitRatio);
    }

//    Query cache doesn't work?
    @Test
    public void cacheQueryTest(){
        Session session = em.unwrap(Session.class);
        List<Author> authors = em.createQuery(
                "select a " +
                        "from Author a " +
                        "where a.name = :name", Author.class)
                .setParameter( "name", "Alexander Zlotnikov")
                .setHint( "org.hibernate.cacheable", "true")
                .setHint( QueryHints.HINT_CACHE_REGION, "query.cache.author" )
                .setHint( "javax.persistence.cache.storeMode", CacheStoreMode.USE )
                .setHint( "javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE )
                .getResultList();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        em.createQuery(
                "select a " +
                        "from Author a " +
                        "where a.name = :name", Author.class)
                .setParameter( "name", "Alexander Zlotnikov")
                .setHint( "org.hibernate.cacheable", "true")
                .setHint( QueryHints.HINT_CACHE_REGION, "query.cache.author" )
                .setHint( "javax.persistence.cache.storeMode", CacheStoreMode.BYPASS )
                .setHint( "javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE )
                .getResultList();

//        session.createQuery(
//                "select a " +
//                        "from Author a " +
//                        "where a.name = :name", Author.class)
//                .setParameter( "name", "Alexander Zlotnikov")
//                .setCacheable(true)
//                .setCacheRegion( "query.cache.author" )
//                .setCacheMode( CacheMode.NORMAL )
//                .list();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ie) {
//            ie.printStackTrace();
//        }
//        session.createQuery(
//                "select a " +
//                        "from Author a " +
//                        "where a.name = :name", Author.class)
//                .setParameter( "name", "Alexander Zlotnikov")
//                .setCacheable(true)
//                .setCacheRegion( "query.cache.author" )
//                .setCacheMode( CacheMode.GET )
//                .list();
    }

    public Author getAuthor(Long id){
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        em.getTransaction().commit();
        return author;
    }
}
