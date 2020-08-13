package Administrator;
import Utils.ArtikliData;
import Utils.FXMLUtils;
import dbUtil.dbConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable{//implementiranje interfejsa

    @FXML
    private TextField naziv;
    @FXML
    private TextField kat;
    @FXML
    private TextField cena;
    @FXML
    private TableView<ArtikliData> artiklitable;
    @FXML
    private TableColumn<ArtikliData, String> idcolumn;
    @FXML
    private TableColumn<ArtikliData, String> nazivcolumn;
    @FXML
    private TableColumn<ArtikliData, String> katcolumn;
    @FXML
    private TableColumn<ArtikliData, String> cenacolumn;
    @FXML
    private Button del;

    private dbConnection dc; //objekat klase dbConnection
    private ObservableList <ArtikliData> data; //fx lista koja omogućava praćenje promena koje se dese
    private FXMLUtils Fx = new FXMLUtils();//novi objekat klase FXMLutils

    private String sql = "SELECT * FROM artikli ";// sql query

    public void initialize(URL url, ResourceBundle rb){
        this.dc = new dbConnection();
    }//initialize metoda koja kaže da se za dc objekat pozove metoda dbConnection koja se povezuje sa bazom

    @FXML
    private void loadArtikliData() throws SQLException {
        Fx.loadArtikliData(artiklitable,idcolumn, nazivcolumn, katcolumn, cenacolumn, data, sql);
    }
    @FXML
    private void dodajArtikl(ActionEvent event) throws  SQLException{
        Fx.dodajArtikl(naziv, kat, cena);
    }
    @FXML
    private void brisiArtikl(ActionEvent event) throws SQLException {
        Fx.brisiArtikl(artiklitable,del);
    }
    @FXML
    private void izmeniArtikl(ActionEvent event) throws SQLException{
        Fx.izmeniArtikl(artiklitable, naziv, kat, cena);
    }

    @FXML
    private void obrisiFormu(ActionEvent event){//brisanje forme

        Fx.obrisiFormu(naziv, kat, cena);
    }

}
