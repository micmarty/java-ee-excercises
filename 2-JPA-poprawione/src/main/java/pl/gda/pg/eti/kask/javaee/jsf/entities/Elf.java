package pl.gda.pg.eti.kask.javaee.jsf.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.gda.pg.eti.kask.javaee.jsf.entities.validators.DobrzeWyposazony;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ELFY")
@NamedQuery(name = "Elf.findAll", query = "SELECT e FROM Elf e")
public class Elf implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String imie;

    @DobrzeWyposazony(MinLiczbaStrzal = "20")
    @Column(name = "liczba_strzal")
    private int liczbaStrzal;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "rodzaj_luku")
    private RodzajLuku rodzajLuku;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="las_id")
    private Las las;

    public enum RodzajLuku {
        DREWNIANY,
        KARBONOWY,
        JESIONOWY,
        LESZCZYNOWY
    }
}
