/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfarmacy01;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class dashcontrolador implements Initializable {

    @FXML
    private AnchorPane menu_formulario;

    @FXML
    private Label dashboard_total1;

    @FXML
    private Button sair;

    @FXML
    private Label dashboard_clientes1;

    @FXML
    private Label username;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Button btn_venda;

    @FXML
    private Button btn_adicionar;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane dashboard_formulario;

    @FXML
    private AreaChart<?, ?> dashboard_grafico;

    @FXML
    private Label dashboard_disponiveis;

    @FXML
    private Label dashboard_clientes;

    @FXML
    private Label dashboard_venda;

    @FXML
    private AnchorPane add_formulario;

    @FXML
    private TextField add_farmacoID;

    @FXML
    private ComboBox<?> add_classificacao;

    @FXML
    private ComboBox<?> add_status;

    @FXML
    private DatePicker add_validade;

    @FXML
    private ImageView add_imagem;

    @FXML
    private Button btn_imagem;

    @FXML
    private Button btn_adiciona;

    @FXML
    private Button btn_updade;

    @FXML
    private Button btn_limpar;

    @FXML
    private Button btn_apagar;

    @FXML
    private TextField add_preco;

    @FXML
    private TextField add_brand;

    @FXML
    private TextField add_nome;

    @FXML
    private TextField add_pesquisar;

    @FXML
    private TableView<farmacoData> tabelafarmaco;

    @FXML
    private TableColumn<farmacoData, String> tabela_farmacoID;

    @FXML
    private TableColumn<farmacoData, String> tabela_brand;

    @FXML
    private TableColumn<farmacoData, String> tabela_nome;

    @FXML
    private TableColumn<farmacoData, String> tabela_classificacao;

    @FXML
    private TableColumn<farmacoData, String> tabela_preco;

    @FXML
    private TableColumn<farmacoData, String> tabela_status;

    @FXML
    private TableColumn<farmacoData, String> tabela_validade;

    @FXML
    private AnchorPane venda_formulario;

    @FXML
    private ComboBox<?> compra_classificacao;

    @FXML
    private ComboBox<?> compra_farmacoID;

    @FXML
    private ComboBox<?> compra_brand;

    @FXML
    private Spinner<Integer> compra_quantidade;

    @FXML
    private ComboBox<?> compra_nome;

    @FXML
    private Button btn_carrinho;

    @FXML
    private Label compra_total;

    @FXML
    private TextField compra_valorpago;

    @FXML
    private Label compra_balance;

    @FXML
    private Button btn_comprar;

    @FXML
    private TableView<vendasData> tabela_compra;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_farmacoID;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_brand;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_nome;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_classificacao;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_quantidade;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_preco;
    
    
    @FXML
    private TableView<vendasData> tabela_compra1;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_farmacoID1;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_brand1;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_nome1;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_classificacao1;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_quantidade1;

    @FXML
    private TableColumn<vendasData, String> tabelacompra_preco1;
     @FXML
    private TableColumn<vendasData, String> tabelacompra_data1;
    
    
    
    

    //base de dados: 
    private PreparedStatement prepare;
    private Connection conexao;
    private Statement statment;
    private ResultSet resultado;
    private Image imagem;

    //funcoes tela adicionar produtos
    public void addfarmaco() {

        String sql = "INSERT INTO farmacos (farmaco_id, farmaco_nome, farmaco_brand, farmaco_classificacao, farmaco_status, farmaco_preco, farmaco_validade, farmaco_imagem) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        conexao = database.conexaoDB();

        try {

            Alert alerta;

            if (add_farmacoID.getText().isEmpty()
                    || add_brand.getText().isEmpty()
                    || add_nome.getText().isEmpty()
                    || add_classificacao.getSelectionModel().getSelectedItem() == null
                    || add_status.getSelectionModel().getSelectedItem() == null
                    || add_preco.getText().isEmpty()
                    || getData.path == null || getData.path == "") {

                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Mensagem de Erro");
                alerta.setHeaderText(null);
                alerta.setContentText("Complete todos os espacos em branco");
                alerta.showAndWait();
            } else {

                String verFarmaco = "SELECT farmaco_id FROM farmacos WHERE farmaco_id = '" + add_farmacoID.getText() + "'";

                statment = conexao.createStatement();
                resultado = statment.executeQuery(verFarmaco);

                if (resultado.next()) {

                    alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Mensagem de Erro");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Farmaco com ID: " + add_farmacoID.getText() + " Ja existem na tabela! ");
                    alerta.showAndWait();

                } else {
                    prepare = conexao.prepareStatement(sql);
                    prepare.setString(1, add_farmacoID.getText());
                    prepare.setString(2, add_nome.getText());
                    prepare.setString(3, add_brand.getText());
                    prepare.setString(4, (String) add_classificacao.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) add_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, add_preco.getText());

                    Date data = new Date();
                    java.sql.Date sqlData = new java.sql.Date(data.getTime());
                    prepare.setString(7, String.valueOf(sqlData));

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(8, uri);

                    prepare.executeUpdate();

                    alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Notificacao");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Adicionado com Sucesso");
                    alerta.showAndWait();

                    farmacoDataShow();
                    addFarmacoReset();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void procurarFarmaco() {

        FilteredList<farmacoData> filter = new FilteredList<>(addFarmacoList, e -> true);

        add_pesquisar.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicate -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String pesquisa = newValue.toLowerCase();
                if (predicate.getFarmaco_id().toString().contains(pesquisa)) {

                    return true;
                } else if (predicate.getFarmaco_brand().toLowerCase().contains(pesquisa)) {

                    return true;
                } else if (predicate.getFarmaco_nome().toLowerCase().contains(pesquisa)) {

                    return true;
                } else if (predicate.getFarmaco_classificacao().toLowerCase().contains(pesquisa)) {

                    return true;
                } else if (predicate.getFarmaco_status().toLowerCase().contains(pesquisa)) {

                    return true;
                } else if (predicate.getFarmaco_preco().toString().contains(pesquisa)) {

                    return true;
                } else if (predicate.getFarmaco_validade().toString().contains(pesquisa)) {

                    return true;
                } else {
                    return false;
                }
            });

        });

        SortedList<farmacoData> sort = new SortedList<>(filter);

        sort.comparatorProperty().bind(tabelafarmaco.comparatorProperty());
        tabelafarmaco.setItems(sort);

    }

    public void apagarFarmaco() {

        String sql = "DELETE FROM farmacos WHERE farmaco_id ='" + add_farmacoID.getText() + "'";

        conexao = database.conexaoDB();

        try {

            Alert alerta;

            if (add_farmacoID.getText().isEmpty()
                    || add_brand.getText().isEmpty()
                    || add_nome.getText().isEmpty()
                    || add_classificacao.getSelectionModel().getSelectedItem() == null
                    || add_status.getSelectionModel().getSelectedItem() == null
                    || add_preco.getText().isEmpty()
                    || getData.path == null || getData.path == "") {

                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Mensagem de Erro");
                alerta.setHeaderText(null);
                alerta.setContentText("Complete todos os espacos em branco");
                alerta.showAndWait();
            } else {

                alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Notificacao");
                alerta.setHeaderText(null);
                alerta.setContentText("Deseja Deletar o Farmaco com ID: " + add_farmacoID.getText() + " ?");

                Optional<ButtonType> option = alerta.showAndWait();

                if (option.get().equals((ButtonType.OK))) {

                    statment = conexao.createStatement();
                    statment.executeUpdate(sql);

                    alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Notificacao");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Deletado com Sucesso");

                    farmacoDataShow();
                    addFarmacoReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void farmacoUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE farmacos SET farmaco_brand = '"
                + add_brand.getText() + "', farmaco_nome = '"
                + add_nome.getText() + "', farmaco_classificacao = '"
                + add_classificacao.getSelectionModel().getSelectedItem() + "', farmaco_status = '"
                + add_status.getSelectionModel().getSelectedItem() + "', farmaco_preco = '"
                + add_preco.getText() + "', farmaco_imagem = '" + uri + "' WHERE farmaco_id = '"
                + add_farmacoID.getText() + "'";

        conexao = database.conexaoDB();

        try {

            Alert alerta;

            if (add_farmacoID.getText().isEmpty()
                    || add_brand.getText().isEmpty()
                    || add_nome.getText().isEmpty()
                    || add_classificacao.getSelectionModel().getSelectedItem() == null
                    || add_status.getSelectionModel().getSelectedItem() == null
                    || add_preco.getText().isEmpty()
                    || getData.path == null || getData.path == "") {

                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Mensagem de Erro");
                alerta.setHeaderText(null);
                alerta.setContentText("Complete todos os espacos em branco");
                alerta.showAndWait();
            } else {

                alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Notificacao");
                alerta.setHeaderText(null);
                alerta.setContentText("Deseja realmente fazer Update do farmaco: " + add_farmacoID.getText() + " ?");

                Optional<ButtonType> option = alerta.showAndWait();

                if (option.get().equals((ButtonType.OK))) {

                    statment = conexao.createStatement();
                    statment.executeUpdate(sql);

                    // Carregar imagem
                    farmacoData selectedFarmaco = tabelafarmaco.getSelectionModel().getSelectedItem();
                    String imagePath = selectedFarmaco.getFarmaco_imagem();
                    Image img = new Image("file:" + imagePath);
                    add_imagem.setImage(img);

                    farmacoDataShow();
                    addFarmacoReset();

                    alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Notificacao");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Update Feito com Sucesso");

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addFarmacoReset() {

        add_farmacoID.setText("");
        add_brand.setText("");
        add_preco.setText("");
        add_nome.setText("");
        add_classificacao.getSelectionModel().clearSelection();
        add_status.getSelectionModel().clearSelection();
        add_imagem.setImage(null);
        getData.path = "";

    }

    private String[] addFarmactoClassificacao = {
        "Alopatico",
        "Homeopatico",
        "Similar",
        "Generico",
        "Referencia",
        "Manipulado"};

    public void addFarmacoClassificacao() {

        List<String> listT = new ArrayList<>();
        for (String data : addFarmactoClassificacao) {
            listT.add(data);

        }
        ObservableList listData = FXCollections.observableArrayList(listT);
        add_classificacao.setItems(listData);

    }

    private String[] addFarmacoStatus = {
        "Disponivel ",
        "Nao Disponivel"
    };

    public void addFarmacoStatus() {

        List<String> listS = new ArrayList<>();
        for (String data : addFarmacoStatus) {
            listS.add(data);

        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        add_status.setItems(listData);

    }

    public void addimagem() {

        FileChooser open = new FileChooser();
        open.setTitle("Importar imagem");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagem ", "*jpg", "*png"));

        File file = open.showOpenDialog(menu_formulario.getScene().getWindow());

        if (file != null) {

            imagem = new Image(file.toURI().toString(), 117, 156, false, true);
            add_imagem.setImage(imagem);
            getData.path = file.getAbsolutePath();
        }
    }

    public ObservableList<farmacoData> addFarmacosListData() {

        String sql = "SELECT * FROM farmacos";

        ObservableList<farmacoData> listData = FXCollections.observableArrayList();

        conexao = database.conexaoDB();

        try {
            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            farmacoData farmData;

            while (resultado.next()) {

                farmData = new farmacoData(
                        resultado.getInt("farmaco_id"),
                        resultado.getString("farmaco_nome"),
                        resultado.getString("farmaco_brand"),
                        resultado.getString("farmaco_classificacao"),
                        resultado.getString("farmaco_status"),
                        resultado.getDouble("farmaco_preco"),
                        resultado.getDate("farmaco_validade"),
                        resultado.getString("farmaco_imagem")
                );

                listData.add(farmData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;

    }

    private ObservableList<farmacoData> addFarmacoList;

    public void farmacoDataShow() {
        addFarmacoList = addFarmacosListData();

        tabela_farmacoID.setCellValueFactory(new PropertyValueFactory<>("farmaco_id"));
        tabela_brand.setCellValueFactory(new PropertyValueFactory<>("farmaco_brand"));
        tabela_nome.setCellValueFactory(new PropertyValueFactory<>("farmaco_nome"));
        tabela_classificacao.setCellValueFactory(new PropertyValueFactory<>("farmaco_classificacao"));
        tabela_status.setCellValueFactory(new PropertyValueFactory<>("farmaco_status"));
        tabela_preco.setCellValueFactory(new PropertyValueFactory<>("farmaco_preco"));
        tabela_validade.setCellValueFactory(new PropertyValueFactory<>("farmaco_validade"));

        tabelafarmaco.setItems(addFarmacoList);

    }

    public void addtabelaFarmacoSelect() {

        farmacoData farmdata = tabelafarmaco.getSelectionModel().getSelectedItem();

        int num = tabelafarmaco.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        add_farmacoID.setText(String.valueOf(farmdata.getFarmaco_id()));
        add_brand.setText(String.valueOf(farmdata.getFarmaco_brand()));
        add_nome.setText(String.valueOf(farmdata.getFarmaco_nome()));
        add_preco.setText(String.valueOf(farmdata.getFarmaco_preco()));

        String uri = "file: " + farmdata.getFarmaco_imagem();
        imagem = new Image(uri, 171, 156, false, true);
        add_imagem.setImage(imagem);

        getData.path = farmdata.getFarmaco_imagem();

    }

    public void telaP() {

        btn_dashboard.setStyle("-fx-background-color: TRANSPARENT");

    }

    public void alteraTela(ActionEvent event) {

        if (event.getSource() == btn_dashboard) {
            dashboard_formulario.setVisible(true);
            add_formulario.setVisible(false);
            venda_formulario.setVisible(false);

            btn_dashboard.setStyle(" -fx-background-color: #5390D9;");
            btn_venda.setStyle("-fx-background-color: TRANSPARENT");
            btn_adicionar.setStyle("-fx-background-color: TRANSPARENT");
            graficoR();
            totalClientes();
            totalGanhos();
            totalVD();
            contarF();

        } else if (event.getSource() == btn_venda) {

            dashboard_formulario.setVisible(false);
            add_formulario.setVisible(false);
            venda_formulario.setVisible(true);

            btn_venda.setStyle(" -fx-background-color:#5390D9;;");
            btn_dashboard.setStyle("-fx-background-color: TRANSPARENT");
            btn_adicionar.setStyle("-fx-background-color: TRANSPARENT");
            compraClassificacao();
            compraFarmacoID();
            compraBrand();
            compraNome();
            compraLista();
            compraLista2();
            compraShowValue();
            compraTotal();

        } else if (event.getSource() == btn_adicionar) {

            dashboard_formulario.setVisible(false);
            add_formulario.setVisible(true);
            venda_formulario.setVisible(false);

            btn_adicionar.setStyle(" -fx-background-color:#5390D9;");
            btn_dashboard.setStyle("-fx-background-color: TRANSPARENT");
            btn_venda.setStyle("-fx-background-color: TRANSPARENT");

            farmacoDataShow();
            addFarmacoClassificacao();
            addFarmacoStatus();
            procurarFarmaco();
        }

    }

    public void mostraUsuario() {

        String usuario = getData.username;
        username.setText(usuario.substring(0, 1).toUpperCase() + usuario.substring(1));

    }

    private double x = 0;
    private double y = 0;

    @FXML
    public void logout() {

        try {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Mensagem de Confirmacao");
            alerta.setHeaderText(null);
            alerta.setContentText("Deseja trocar de usuario?");
            Optional<ButtonType> option = alerta.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //ocultar antes do login
                logout.getScene().getWindow().hide();

                //link com o formulario do login 
                Parent root = FXMLLoader.load(getClass().getResource("/projectfarmacy01/FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();

                });

                root.setOnMouseDragged((MouseEvent event) -> {

                    stage.setX(event.getSceneX() - x);
                    stage.setY(event.getSceneY() - y);
                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //funcoes tela de venda: 
    public void compraClassificacao() {

        String sql = "SELECT farmaco_classificacao FROM farmacos WHERE farmaco_status = 'Disponivel' ";

        conexao = database.conexaoDB();

        try {

            ObservableList lista = FXCollections.observableArrayList();
            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                lista.add(resultado.getString("farmaco_classificacao"));

            }
            compra_classificacao.setItems(lista);
            compraFarmacoID();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double totalP;

    public void compraADD() {

        compraVendaId();

        String sql = "INSERT INTO vendas (venda_id, classificacao,farmaco_id, brand, nome , quantidade, preco, data) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        conexao = database.conexaoDB();

        try {

            Alert alerta;

            if (compra_classificacao.getSelectionModel().isEmpty()
                    || compra_farmacoID.getSelectionModel().isEmpty()
                    || compra_brand.getSelectionModel().isEmpty()
                    || compra_nome.getSelectionModel().isEmpty()
                    || quantidade == 0) {

                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Mensagem de Erro");
                alerta.setHeaderText(null);
                alerta.setContentText("Preencha todos os campos corretamente");
                alerta.showAndWait();

            } else {

                prepare = conexao.prepareStatement(sql);
                prepare.setString(1, String.valueOf(vendas_id));
                prepare.setString(2, (String) compra_classificacao.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) compra_farmacoID.getSelectionModel().getSelectedItem());
                prepare.setString(4, (String) compra_brand.getSelectionModel().getSelectedItem());
                prepare.setString(5, (String) compra_nome.getSelectionModel().getSelectedItem());
                prepare.setString(6, String.valueOf(quantidade));

                String Check = "SELECT farmaco_preco FROM farmacos WHERE farmaco_id = '" + compra_farmacoID.getSelectionModel().getSelectedItem() + "'";

                statment = conexao.createStatement();
                resultado = statment.executeQuery(Check);

                double precoData = 0;

                if (resultado.next()) {
                    precoData = resultado.getDouble("farmaco_preco");
                }
                totalP = (precoData * quantidade);

                prepare.setString(7, String.valueOf(totalP));

                Date data = new Date();

                java.sql.Date sqlData = new java.sql.Date(data.getTime());

                prepare.setString(8, String.valueOf(sqlData));

                prepare.executeUpdate();
                compraLista();
                compraTotal();
            }

            prepare = conexao.prepareStatement(sql);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void apagarVendas() {

        compraVendaId();
        String sql = "DELETE FROM vendas WHERE venda_id ='" + String.valueOf(vendas_id) + "'";

        conexao = database.conexaoDB();

        try {

            Alert alerta;

            alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Notificacao");
            alerta.setHeaderText(null);
            alerta.setContentText("Deseja Reiniciar a compra ?");

            Optional<ButtonType> option = alerta.showAndWait();

            if (option.get().equals((ButtonType.OK))) {

                statment = conexao.createStatement();
                statment.executeUpdate(sql);

                compraLista();
                addcompraReset();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SpinnerValueFactory<Integer> spinner;

    public void compraShowValue() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        compra_quantidade.setValueFactory(spinner);
    }

    private int quantidade;

    public void compraQuantidade() {

        quantidade = compra_quantidade.getValue();
    }

    public void compraFarmacoID() {

        String sql = "SELECT farmaco_id FROM farmacos WHERE farmaco_classificacao = '" + compra_classificacao.getSelectionModel().getSelectedItem() + "'";

        conexao = database.conexaoDB();

        try {

            ObservableList lista = FXCollections.observableArrayList();
            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                lista.add(resultado.getString("farmaco_id"));

            }
            compra_farmacoID.setItems(lista);
            compraBrand();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void compraBrand() {

        String sql = "SELECT farmaco_brand FROM farmacos WHERE farmaco_classificacao = '" + compra_classificacao.getSelectionModel().getSelectedItem() + "'";

        conexao = database.conexaoDB();

        try {

            ObservableList lista = FXCollections.observableArrayList();
            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                lista.add(resultado.getString("farmaco_brand"));

            }
            compra_brand.setItems(lista);
            compraNome();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void compraNome() {

        String sql = "SELECT farmaco_nome FROM farmacos WHERE farmaco_classificacao = '" + compra_classificacao.getSelectionModel().getSelectedItem() + "'";

        conexao = database.conexaoDB();

        try {

            ObservableList lista = FXCollections.observableArrayList();
            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                lista.add(resultado.getString("farmaco_nome"));

            }
            compra_nome.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double totalPrecodata;

    public void compraTotal() {

        String sql = "SELECT SUM(preco) FROM vendas WHERE venda_id = '" + vendas_id + "'";

        conexao = database.conexaoDB();

        try {

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            if (resultado.next()) {

                totalPrecodata = resultado.getDouble("SUM(preco)");

            }

            compra_total.setText("$" + String.valueOf(totalPrecodata));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double troco;
    private double valorPago;

    public void compraValorPago() {

        if (compra_valorpago.getText().isEmpty() || totalPrecodata == 0) {

            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Mensagem de Erro");
            alerta.setHeaderText(null);
            alerta.setContentText("Compra Invalida");
            alerta.showAndWait();

        } else {

            valorPago = Double.parseDouble(compra_valorpago.getText());
            if (totalPrecodata > valorPago) {
                compra_valorpago.setText("");

                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Mensagem de Erro");
                alerta.setHeaderText(null);
                alerta.setContentText("Compra Invalida");
                alerta.showAndWait();
            } else {
                troco = (valorPago - totalPrecodata);
                compra_balance.setText("$ " + String.valueOf(troco));
            }
        }

    }

    public void compraPagamento() {

        compraVendaId();
        String sql = "INSERT INTO vendas_info (venda_id, total, data) VALUES (?, ?, ?)";

        conexao = database.conexaoDB();

        try {

            Alert alerta;

            if (compra_classificacao.getSelectionModel().isEmpty()
                    || compra_farmacoID.getSelectionModel().isEmpty()
                    || compra_brand.getSelectionModel().isEmpty()
                    || compra_nome.getSelectionModel().isEmpty()
                    || compra_valorpago.getText().isEmpty()
                    || quantidade == 0) {

                alerta = new Alert(AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setContentText("Compra Invalida");
                alerta.showAndWait();

            } else {

                valorPago = Double.parseDouble(compra_valorpago.getText());

                if (valorPago < totalPrecodata) {
                    alerta = new Alert(AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setContentText("Compra Invalida");
                    alerta.showAndWait();

                } else {

                    alerta = new Alert(AlertType.CONFIRMATION);
                    alerta.setHeaderText(null);
                    alerta.setContentText("Deseja Confirmar ?");
                    Optional<ButtonType> option = alerta.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {

                        prepare = conexao.prepareStatement(sql);
                        prepare.setString(1, String.valueOf(vendas_id));

                        prepare.setString(2, String.valueOf(totalPrecodata));

                        Date data = new Date();
                        java.sql.Date sqldata = new java.sql.Date(data.getTime());

                        prepare.setString(3, String.valueOf(sqldata));

                        prepare.executeUpdate();

                        alerta = new Alert(AlertType.INFORMATION);
                        alerta.setHeaderText(null);
                        alerta.setContentText("Compra Realizada!");
                        alerta.showAndWait();
                        addcompraReset();
                        compraLista();
                        compraLista2();
                    }

                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void addcompraReset() {

        compra_total.setText("");
        compra_valorpago.setText("");
        compra_balance.setText("");
        compra_brand.getSelectionModel().clearSelection();
        compra_farmacoID.getSelectionModel().clearSelection();
        compra_nome.getSelectionModel().clearSelection();

        SpinnerValueFactory<Integer> valueFactory = compra_quantidade.getValueFactory();
        if (valueFactory != null) {
            valueFactory.setValue(0);

            getData.path = "";

        }

    }

    public ObservableList<vendasData> vendasListData() {

        compraVendaId();
        String sql = "SELECT * FROM vendas WHERE venda_id = '" + vendas_id + "' ";
        ObservableList<vendasData> lista = FXCollections.observableArrayList();

        conexao = database.conexaoDB();

        try {

            vendasData vendaD;

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                vendaD = new vendasData(resultado.getInt("venda_id"),
                        resultado.getString("classificacao"),
                        resultado.getInt("farmaco_id"),
                        resultado.getString("brand"),
                        resultado.getString("nome"),
                        resultado.getInt("quantidade"),
                        resultado.getDouble("preco"),
                        resultado.getDate("data"));

                lista.add(vendaD);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return lista;

    }

    private ObservableList<vendasData> compralista;
   
    public void compraLista() {

        compralista = vendasListData();

        tabelacompra_farmacoID.setCellValueFactory(new PropertyValueFactory<>("farmaco_id"));
        tabelacompra_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tabelacompra_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelacompra_quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabelacompra_preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelacompra_classificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));

        tabela_compra.setItems(compralista);
    }
    
      public ObservableList<vendasData> vendasListData1() {

       
        String sql = "SELECT * FROM vendas";
        ObservableList<vendasData> lista = FXCollections.observableArrayList();

        conexao = database.conexaoDB();

        try {

            vendasData vendaD;

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                vendaD = new vendasData(resultado.getInt("venda_id"),
                        resultado.getString("classificacao"),
                        resultado.getInt("farmaco_id"),
                        resultado.getString("brand"),
                        resultado.getString("nome"),
                        resultado.getInt("quantidade"),
                        resultado.getDouble("preco"),
                        resultado.getDate("data"));

                lista.add(vendaD);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return lista;

    }

    private ObservableList<vendasData> compralistagem;
   
    public void compraLista2() {

        compralistagem = vendasListData1();

        tabelacompra_farmacoID1.setCellValueFactory(new PropertyValueFactory<>("farmaco_id"));
        tabelacompra_brand1.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tabelacompra_nome1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelacompra_quantidade1.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabelacompra_preco1.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tabelacompra_classificacao1.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        tabelacompra_data1.setCellValueFactory(new PropertyValueFactory<>("data"));

        tabela_compra1.setItems(compralistagem);
    }
    
    
    
    
    

    private int vendas_id;

    public void compraVendaId() {

        String sql = "SELECT venda_id FROM vendas ";

        conexao = database.conexaoDB();

        try {

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                vendas_id = resultado.getInt("venda_id");

            }

            int vID = 0;

            String Check = "SELECT venda_id FROM vendas_info ";
            statment = conexao.createStatement();
            resultado = statment.executeQuery(Check);

            while (resultado.next()) {
                vID = resultado.getInt("venda_id");

            }
            if (vendas_id == 0) {
                vendas_id += 1;

            } else if (vID == vendas_id) {
                vendas_id = vID + 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    
    
  
    
    
    
    
    // tela dashbord:
    public void contarF() {

        String sql = "SELECT COUNT(farmaco_id) FROM farmacos WHERE farmaco_status = 'Disponivel'";

        conexao = database.conexaoDB();
        int totalM = 0;

        try {

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {
                totalM = resultado.getInt("COUNT(farmaco_id)");

            }

            dashboard_disponiveis.setText(String.valueOf(totalM));

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void totalGanhos() {

        String sql = "SELECT SUM(total) FROM vendas_info";

        conexao = database.conexaoDB();
        double totalmoney = 0;

        try {

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {
                totalmoney = resultado.getDouble("SUM(total)");

            }

            dashboard_total1.setText("$ " + String.valueOf(totalmoney));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void graficoR() {

        dashboard_grafico.getData().clear();

        String sql = "SELECT data, SUM(total) FROM vendas_info"
                + " GROUP BY data ORDER BY TIMESTAMP(data) ASC LIMIT 9";

        conexao = database.conexaoDB();

        try {

            XYChart.Series chart = new XYChart.Series();

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {

                chart.getData().add(new XYChart.Data(resultado.getString(1), resultado.getInt(2)));

            }

            dashboard_grafico.getData().add(chart);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void totalClientes() {

        String sql = "SELECT COUNT(venda_id) FROM vendas_info";

        conexao = database.conexaoDB();
        double totalmone = 0;

        try {

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {
                totalmone = resultado.getDouble("COUNT(venda_id)");

            }

            dashboard_clientes.setText(String.valueOf(totalmone));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalVD() {

        String sql = "SELECT COUNT(venda_id) FROM vendas";

        conexao = database.conexaoDB();
        int mone = 0;

        try {

            prepare = conexao.prepareStatement(sql);
            resultado = prepare.executeQuery();

            while (resultado.next()) {
                mone = resultado.getInt("COUNT(venda_id)");

            }

            dashboard_venda.setText(String.valueOf(mone));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void minimizar() {
        Stage stage = (Stage) menu_formulario.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void sair() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostraUsuario();
        telaP();

        totalClientes();
        totalGanhos();
        totalVD();
        contarF();
        graficoR();

        farmacoDataShow();
        addFarmacoClassificacao();
        addFarmacoStatus();

        compraClassificacao();
        compraFarmacoID();
        compraBrand();
        compraNome();
        compraLista();
        compraLista2();
        compraShowValue();
        compraTotal();
    }

}
