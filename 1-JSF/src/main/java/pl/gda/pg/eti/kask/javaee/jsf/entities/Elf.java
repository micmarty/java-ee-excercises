package pl.gda.pg.eti.kask.javaee.jsf.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author psysiu
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Elf implements Serializable {

    private int id;

    private String imie;

    private int liczbaStrzal;

    private enum RodzajLuku {
        DREWNIANY,
        KARBONOWY,
        JESIONOWY,
        LESZCZYNOWY
    };

}
