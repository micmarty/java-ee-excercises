package pl.gda.pg.eti.kask.javaee.enterprise.entities;

public enum RodzajeLuku {
    FAJNY("FAJNY"), DREWNIANY("DREWNIANY"), METALOWY("METALOWY");

    private String string;

    private RodzajeLuku(String s) {
        this.string = s;
    }

    public boolean equals(RodzajeLuku other){
        return this.string.equals(other.string);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
