package Login;
import Administrator.AdministratorController;
import Zaposleni.ZaposleniController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();// login model je objekat preko kog se poziva metoda LoginModel()
    @FXML
    private Label statusbaze;
    @FXML
    private TextField ime;
    @FXML
    private PasswordField lozinka;
    @FXML
    private ComboBox<option> izbor;
    @FXML
    private Button logindugme;
    @FXML
    private Label isLoginstatus;

    @Override
    public void initialize( URL url, ResourceBundle rb){
    if(this.loginModel.isDatabaseConnected()) {//ako je isDatabaseConnected true
        this.statusbaze.setText("Povezano na bazu");//postavlja se test u labelu status baze
    }
        else{
        this.statusbaze.setText("Nije povezano na bazu");
        }
        this.izbor.setItems(FXCollections.observableArrayList(option.values()));//popunjavanje comboboxa
    }

    @FXML
    public void login(ActionEvent event){
        try{
            if(this.loginModel.isLogin(this.ime.getText(), this.lozinka.getText(), ((option)this.izbor.getValue()).toString().toLowerCase()))//ako je vrednost isLogin true, ovo prolazi
            {
                Stage stage = (Stage) this.logindugme.getScene().getWindow();//Stage je javAFX container u kom se otvara scene preko logindugme i dobija vrednost property prozora
                stage.close();// zatvori container stage

                switch (((option) this.izbor.getValue()).toString()) {
                    case "Administrator":
                        administratorLogin();
                        break;
                    case "Zaposleni":
                        zaposleniLogin();
                        break;
                }
            }
            else{
                this.isLoginstatus.setText("Pogrešan unos");
            }
            }
        catch(Exception localEx){
        }
    }

    public void zaposleniLogin() {
        try {
            Stage userstage = new Stage();// userstage novi Stage
            FXMLLoader loader = new FXMLLoader();// loaduje hijerarhiju objekata iz FXML-a za novi objekat klase FXMLLoader
            Pane root = (Pane) loader.load(getClass().getResource("/Zaposleni/Zaposleni.fxml").openStream());//Pane je bazna klasa za layout pane. loader loaduje fajl preko getClass i getResource. OpenStream otvara konekciju ka upisanom URL
            ZaposleniController zaposleniController = (ZaposleniController) loader.getController();//kreira se novi controller kojem se prosleđuje loader sa controllerom vezanim za root objekat
            Scene scene = new Scene(root); // nova scena scene kojoj se prosleđuje root panel
            userstage.setScene(scene);// postavljanje scene za userstage
            userstage.setTitle("Zaposleni Dashboard");
            userstage.setResizable(false);//da ne može stage da se proširi
            userstage.show();// da se prikaže stage
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void administratorLogin(){
    try {
        Stage adminStage = new Stage();
        FXMLLoader administratorLoader = new FXMLLoader();
        Pane administratorroot = (Pane)administratorLoader.load(getClass().getResource("/Administrator/Admin.fxml").openStream());
        AdministratorController administratorController = (AdministratorController)administratorLoader.getController();
        Scene adminscene = new Scene(administratorroot);
        adminStage.setScene(adminscene);
        adminStage.setTitle(("Administrator Dashboard"));
        adminStage.setResizable(false);
        adminStage.show();
    }

    catch (IOException e){
        e.printStackTrace();
    }
    }

}

