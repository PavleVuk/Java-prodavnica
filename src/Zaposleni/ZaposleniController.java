package Zaposleni;

import Utils.ArtikliData;
import Login.LoginController;
import Utils.FXMLUtils;
import dbUtil.dbConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ZaposleniController implements Initializable {

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
    private LoginController loginController = new LoginController();
    private FXMLUtils Fx= new FXMLUtils();
    private dbConnection dc;
    private ObservableList<ArtikliData> data;
    private String sql = "SELECT * FROM artikli ";

    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new dbConnection();
    }

    @FXML
    private void adminLogin(ActionEvent event) {
        loginController.administratorLogin();
    }

    @FXML
    private void loadArtikliData() throws SQLException {
        Fx.loadArtikliData(artiklitable,idcolumn, nazivcolumn, katcolumn, cenacolumn, data, sql);
    }

    @FXML
    private void brisiArtikl(ActionEvent event) throws SQLException {
        Fx.brisiArtikl(artiklitable,del);
    }
}


