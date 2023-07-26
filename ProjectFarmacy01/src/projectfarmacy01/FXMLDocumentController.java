/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfarmacy01;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane pagina_login;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    @FXML
    private Button btnlogin;

    //base de dados: 
    private PreparedStatement prepare;
    private Connection conexao;
    private ResultSet resultado;

    @FXML
    private Button btnsair;

    //funcooes: 
    public void btnsair() {
        System.exit(0);
    }

    public void loginAdmin() {

        String sql = "SELECT * FROM adminstrador WHERE username = ? and password = ?";

        conexao = database.conexaoDB();

        try {

            prepare = conexao.prepareStatement(sql);
            prepare.setString(1, usuario.getText());
            prepare.setString(2, senha.getText());
            resultado = prepare.executeQuery();

            Alert alerta;

            if (usuario.getText().isEmpty() || senha.getText().isEmpty()) {
                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro Login");
                alerta.setHeaderText(null);
                alerta.setContentText("Prencha os campos vazios para o login!");
                alerta.showAndWait();
            } else {

                if (resultado.next()) {
                    
                    getData.username = usuario.getText(); 
                    alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Login");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Usuario logado com sucesso!");
                    alerta.showAndWait();

                    //nao mostrar tela principal sem login
                    btnlogin.getScene().getWindow().hide();
                    //transicao para tela tela principal admi              
                    System.out.println("dash tela acess");
                    Parent root = FXMLLoader.load(getClass().getResource("/projectfarmacy01/dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {

                    alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Erro Login");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Usuario/Senha Errada!");
                    alerta.showAndWait();

                }
            }

        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
