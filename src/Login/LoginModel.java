package Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;
public class LoginModel {
    Connection connection;

    public LoginModel(){
        try{
            this.connection = dbConnection.getConnection(); //poziv funkcija getConnecti0n za objekat connection
        }
        catch
            (SQLException ex){
            ex.printStackTrace();
        }
        if(this.connection == null){
            System.exit(1);
        }
    }
    public boolean isDatabaseConnected(){//boolean za db Connection
        return this.connection != null;
    }

    public boolean isLogin(String ime, String lozinka, String opt) throws Exception{//metoda sa parametrima
        PreparedStatement pr = null;// prekompajliran statement
        ResultSet rs = null;//resultset predstavlja tabelu podataka baze dobijenu querijem
        String sql = "SELECT * from administrator where ime = ? and lozinka = ? and role = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, ime);
            pr.setString(2, lozinka);
            pr.setString(3, opt);
            rs = pr.executeQuery();

            boolean boll1;
            if (rs.next()) {//next pomera kursor jedan red napred sa trenutne pozicije. Pri prvom pozivu, postavlja se na prvi red.  kada je next false, onda je pozicija kursora iza poslednje reda tabele
                return true;
            }
            return false;
        }
            catch (SQLException ex ) {
                return false;
            }
            finally{ // mora da se zatvori konekcija
                pr.close();
                rs.close();
            }

    }
}
