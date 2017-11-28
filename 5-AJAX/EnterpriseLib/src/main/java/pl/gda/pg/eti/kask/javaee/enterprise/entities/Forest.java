package pl.gda.pg.eti.kask.javaee.enterprise.entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.validators.InPast;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;

@ToString(of = "id")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = Forest.Queries.FIND_ALL, query = "SELECT b FROM Forest b ORDER BY b.id"),
        @NamedQuery(name = Forest.Queries.FIND_MOST_RECENT, query = "SELECT b FROM Forest b ORDER BY b.id DESC"),
})
public class Forest implements Serializable {

    public static class Queries {
        public static final String FIND_ALL = "Forest.findAll";
        public static final String FIND_MOST_RECENT = "Forest.findMostRecent";
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Range(min = 3, max = 200)
    private Integer treesNumber;

    @Getter
    @Setter
    @OneToMany(mappedBy = "homeForest", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Elf> elves = new ArrayList<>();

    @Getter
    @Setter
    @ManyToOne
    private User owner;

    // TODO dodac parametr authors
    public Forest(Integer treesNumber, List<Elf> elves, User owner) {
        this.treesNumber = treesNumber;
        this.elves = elves;
        this.owner = owner;
    }
}
