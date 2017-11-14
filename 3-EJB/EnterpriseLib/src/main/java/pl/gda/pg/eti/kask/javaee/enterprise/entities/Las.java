package pl.gda.pg.eti.kask.javaee.enterprise.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import pl.gda.pg.eti.kask.javaee.enterprise.entities.validators.InNumber;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@EqualsAndHashCode(exclude = "elfyLasu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lases")
@NamedQuery(name = Las.FIND_ALL, query = "SELECT a FROM Las a")
public class Las implements Serializable {

    public static final String FIND_ALL = "Las.findAll";


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @InNumber(value="1")
    @Column
    private int liczbaDrzew;

    @OneToMany(mappedBy = "las", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Elf> elfyLasu = new ArrayList<>();

    @Override
    public String toString() {
        return "" + this.id;
    }
}
