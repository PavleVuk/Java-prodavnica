package Utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ArtikliData {

    private final StringProperty ID;
    private final StringProperty naziv;
    private final StringProperty kategorija;
    private final StringProperty cena;

    public ArtikliData(String id, String naziv, String kategorija, String cena ){

        this.ID = new SimpleStringProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        this.kategorija= new SimpleStringProperty(kategorija);
        this.cena = new SimpleStringProperty(cena);

    }
    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public StringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public String getCena() {
        return cena.get();
    }

    public StringProperty cenaProperty() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena.set(cena);
    }

    public String getKategorija() {
        return kategorija.get();
    }

    public StringProperty kategorijaProperty() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija.set(kategorija);
    }



}

