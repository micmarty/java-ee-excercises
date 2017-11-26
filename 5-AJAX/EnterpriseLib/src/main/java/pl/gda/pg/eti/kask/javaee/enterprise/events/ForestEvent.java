package pl.gda.pg.eti.kask.javaee.enterprise.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Book;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.Forest;


@AllArgsConstructor(staticName = "of")
public class ForestEvent {
    @Getter
    @Setter
    Forest forest;
}
