package pl.gda.pg.eti.kask.javaee.enterprise.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


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
        @NamedQuery(name = Elf.dojebStrzal, query = "update Elf set liczbaStrzal = liczbaStrzal + :a ")

})
public class Elf  implements Serializable {

    public static final String FIND_ALL = "Elf.findAll";
    public static final String dojebStrzal = "Elf.dojebStrzal";


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String imie;

    @Column
    //@InNumber(value = "1")
    private Integer liczbaStrzal;

    @Column
    @Enumerated(EnumType.STRING)
    private RodzajeLuku rodzajeLuku;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="LAS_ID")
    private Las las;


    @Getter
    @Setter
    @ManyToOne
    private User owner;

    public Elf(String imie,Integer liczbaStrzal, RodzajeLuku rodzajLuku,Las las, User owner) {
        this.imie = imie;
        this.liczbaStrzal = liczbaStrzal;
        this.rodzajeLuku = rodzajLuku;
        this.las = las;
        this.owner = owner;
    }

}

