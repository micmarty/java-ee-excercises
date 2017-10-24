package pl.gda.pg.eti.kask.javaee.jsf.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.gda.pg.eti.kask.javaee.jsf.KatalogService;

import javax.faces.bean.ManagedProperty;

/**
 *
 * @author psysiu
 */
@ToString(of = "id")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Las implements Serializable {

    private int id;

    private int liczbaDrzew;


    // Las nie zawiera elfów
    @ManagedProperty("#{katalogService}")
    private KatalogService katalogService;
    public void setkatalogService(KatalogService katalogService) {
        this.katalogService = katalogService;
    }

    public List<Elf> getElfy(){
        return katalogService.findAllElfy(this.id);
    }

}
