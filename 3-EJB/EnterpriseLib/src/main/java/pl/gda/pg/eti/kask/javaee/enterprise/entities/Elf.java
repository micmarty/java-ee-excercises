package pl.gda.pg.eti.kask.javaee.enterprise.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@ToString(of = "imie")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "elfs")
@NamedQueries({
        @NamedQuery(name = Elf.FIND_ALL, query = "SELECT b FROM Elf b"),
        @NamedQuery(name = Elf.dodajStrzal, query = "update Elf set liczbaStrzal = liczbaStrzal + :dodatkowe_strzaly ")

})
public class Elf  implements Serializable {

    public static final String FIND_ALL = "Elf.findAll";
    public static final String dodajStrzal = "Elf.dodajStrzal";


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String imie;

    @Column
    private Integer liczbaStrzal;

    @Column
    @Enumerated(EnumType.STRING)
    private RodzajeLuku rodzajeLuku;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="LAS_ID")
    private Las las;

}

