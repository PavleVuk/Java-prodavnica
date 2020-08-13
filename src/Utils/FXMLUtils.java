package Utils;

import dbUtil.dbConnection;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FXMLUtils {


    public void loadArtikliData(TableView<ArtikliData> artik, TableColumn<ArtikliData, String> idc, TableColumn<ArtikliData, String> nac,
                                TableColumn<ArtikliData, String> katc, TableColumn<ArtikliData, String> cenc, ObservableList<ArtikliData> data, String sql) throws SQLException {
        try {
            Connection conn = dbConnection.getConnection();// novi objekat klase konekcija za koje se pozivaju metode getConnection()
            data = FXCollections.observableArrayList(); //data je observableArraylist

            ResultSet rs = conn.createStatement().executeQuery(sql);//result set dobija podatke iz conn. za koje je pozvana funkcija createStatement i execute query
            while (rs.next()) {//dok je next() true, tj dok ne pređe kursor sve redove i doobije false kada izađe iz redova
                data.add(new ArtikliData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));//dodaju se parametri u data, artik, idc, nac, katc, cenc
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        idc.setCellValueFactory(new PropertyValueFactory<ArtikliData, String>("ID"));//prikaz podataka u koloni
        nac.setCellValueFactory(new PropertyValueFactory<ArtikliData, String>("naziv"));
        katc.setCellValueFactory(new PropertyValueFactory<ArtikliData, String>("kategorija"));
        cenc.setCellValueFactory(new PropertyValueFactory<ArtikliData, String>("cena"));
        artik.setItems(null);
        artik.setItems(data);
    }


    public void dodajArtikl(TextField naz, TextField kat, TextField cen) throws SQLException {
        String sqlartikl = "INSERT  INTO artikli ( naziv, kategorija, cena) VALUES (?,?,?)";//ignore da ne mogu da se unesu dva ista id-a

        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlartikl);//PreparedStatemen označava objekat koji sadrži prekompajliran sql query. Preparestatement kreira objekat PreparedStatement

            statement.setString(1, naz.getText());//stringovi koji se unose u statement, tj prosleđuju u bazu preko preparestatement
            statement.setString(2, kat.getText());
            statement.setString(3, cen.getText());
            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();//ispis i backtrace do standardnog error streama
        }
    }

    public void brisiArtikl(TableView<ArtikliData> artik, Button del) throws SQLException {
        String sqlartikl = "DELETE FROM artikli WHERE id= ?";
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlartikl);
            del.disableProperty().bind(Bindings.isEmpty(artik.getSelectionModel().getSelectedItems()));//da dugme ne može da se pritisne ako ništa nije pritisnuto
            ArtikliData artikl = artik.getSelectionModel().getSelectedItem();//da se dobije selectovani item za brisaje
            String id = artikl.getID();
            statement.setString(1, id);

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void izmeniArtikl(TableView<ArtikliData> artik,TextField naz, TextField kat, TextField cen ) throws SQLException{
        String sqlartikl = "UPDATE artikli SET  naziv=?, kategorija=?, cena=? WHERE id=? ";
        try{
            Connection conn = dbConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlartikl);
            ArtikliData artikl = artik.getSelectionModel().getSelectedItem();
            String id = artikl.getID();
            statement.setString(1,naz.getText());
            statement.setString(2,kat.getText());
            statement.setString(3,cen.getText());
            statement.setString(4, id);
            statement.execute();
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void obrisiFormu(TextField naz, TextField kat, TextField cen){//brisanje forme
        naz.setText("");
        kat.setText("");
        cen.setText("");
    }
}