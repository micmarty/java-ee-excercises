package pl.gda.pg.eti.kask.javaee.enterprise;

import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.CryptUtils;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.*;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.User.Roles;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.util.Arrays.asList;
import static pl.gda.pg.eti.kask.javaee.enterprise.entities.Cover.HARD;
import static pl.gda.pg.eti.kask.javaee.enterprise.entities.Cover.SOFT;

@Singleton
@Startup
@Log
public class InitialFixture {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {
        Long authorsCount = em.createNamedQuery(Author.Queries.COUNT_ALL, Long.class).getSingleResult();

        if (authorsCount == 0) {
            try {
                List<User> users = asList(
                        new User("admin", CryptUtils.sha256("admin"), asList(Roles.ADMIN, Roles.USER)),
                        new User("user1", CryptUtils.sha256("p@ss1"), asList(Roles.USER)),
                        new User("user2", CryptUtils.sha256("p@ss2"), asList(Roles.USER))
                );

                users.forEach(user -> em.persist(user));
                em.flush();

                List<Author> authors = asList(
                        new Author("Orson Scott", "Card"),
                        new Author("Aaron", "Johnston"),
                        new Author("Maciej", "Guzek"),
                        new Author("Maja", "Kossakowska"),
                        new Author("Neil", "Gaiman")
                );

                authors.forEach(author -> em.persist(author));
                em.flush();


                Elf e1 = new Elf("Misiek", null, BowType.JESIONOWY, users.get(0));
                Elf e2 = new Elf("Aee", null, BowType.JESIONOWY, users.get(0));
                Elf e3 = new Elf("Bee", null, BowType.DREWNIANY, users.get(1));
                Elf e4 = new Elf("Dee", null, BowType.KARBONOWY, users.get(2));
                Elf e5 = new Elf("Cee", null, BowType.DREWNIANY, users.get(1));
                Elf e6 = new Elf("Fee", null, BowType.KARBONOWY, users.get(2));

                Forest f1 = new Forest(10, asList(e1,e2), users.get(0));
                Forest f2 = new Forest(20, asList(e3,e4), users.get(1));
                Forest f3 = new Forest(30, asList(e5,e6), users.get(2));
                List<Forest> forests = asList(f1,f2,f3);
                forests.forEach(forest -> em.persist(forest));
                em.flush();

                e1.setHomeForest(f1);
                e2.setHomeForest(f1);
                e3.setHomeForest(f2);
                e4.setHomeForest(f2);
                e5.setHomeForest(f3);
                e6.setHomeForest(f3);

                List<Elf> elves = asList(e1,e2,e3,e4,e5,e6);
                forests.forEach(forest -> em.persist(forest));
                em.flush();


                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                List<Book> books = asList(
                        new Book("Gra Endera", SOFT, format.parse("2011-02-15"), asList(authors.get(0)), users.get(1)),
                        new Book("Trzeci Świat", SOFT, format.parse("2009-10-01"), asList(authors.get(2)), users.get(1)),
                        new Book("W Przededniu", SOFT, format.parse("2013-03-19"), asList(authors.get(0), authors.get(1)), users.get(1)),
                        new Book("Ruda Sfora", SOFT, format.parse("2011-01-14"), asList(authors.get(3)), users.get(2)),
                        new Comics("Sandman - Pora Mgieł", HARD, format.parse("2010-05-17 00:00:00"), 4, asList(authors.get(4)), users.get(2))
                );

                books.forEach(book -> em.persist(book));

            } catch (ParseException e) {
                log.severe("Initial fixture failed");
            }
        }
    }

}
