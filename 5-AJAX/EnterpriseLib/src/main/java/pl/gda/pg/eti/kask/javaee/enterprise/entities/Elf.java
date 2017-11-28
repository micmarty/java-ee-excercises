package pl.gda.pg.eti.kask.javaee.enterprise.entities;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@ToString(of = "name")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = Elf.Queries.FIND_ALL, query = "SELECT a FROM Elf a"),
        @NamedQuery(name = Elf.Queries.COUNT_ALL, query = "SELECT COUNT(a) FROM Elf a")
})
public class Elf implements Serializable {

    public static class Queries {
        public static final String FIND_ALL = "Elf.findAll";
        public static final String COUNT_ALL = "Elf.countAll";
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @Getter
    @Setter
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="FOREST_ID")
    private Forest homeForest;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private BowType bowType = BowType.JESIONOWY;

    @Getter
    @Setter
    @ManyToOne
    private User owner;

    public Elf(String name, Forest homeForest, BowType bowType, User owner) {
        this.name = name;
        this.homeForest = homeForest;
        this.bowType = bowType;
        this.owner = owner;
    }
}
