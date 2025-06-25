package com.antonio.diarioculturalfx.controller;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import static com.antonio.diarioculturalfx.HelloApplication.memoryManagement;
import static com.antonio.diarioculturalfx.HelloApplication.trocarScene;
import static com.antonio.diarioculturalfx.controller.HelloController.showAlert;
import static com.antonio.diarioculturalfx.util.Util.addImgOnButton;
import static com.antonio.diarioculturalfx.util.Util.setupHoverEffect;

public class MenuPrincipalController implements Initializable {
    MemoryManagementController memoryManagementController = new MemoryManagementController(memoryManagement);


    @FXML
    protected void onCadastroButtonClick() throws IOException { trocarScene("Cadastro"); }
    @FXML
    protected void onAvaliarButtonClick() throws IOException { trocarScene("Avaliar"); }
    @FXML
    protected void onListarButtonClick() throws IOException { trocarScene("Listar"); }
    @FXML
    protected void onBuscarButtonClick() throws IOException { trocarScene("Buscar"); }


    @FXML
    private VBox div_titulo;
    @FXML
    private Button bt_livro;
    @FXML
    private Button bt_filme;
    @FXML
    private Button bt_serie;
    @FXML
    private Button voltarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // icone voltar
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png", voltarButton);

        // Carrega efeitos dos botões
        if(div_titulo!=null) {
            for (Node node : div_titulo.getChildren()) {
                if (node instanceof Button button) {
                    setupHoverEffect(button);
                }
            }
        }
        if(bt_livro!=null && bt_filme!=null && bt_serie!=null) {
            setupHoverEffect(bt_livro);
            setupHoverEffect(bt_filme);
            setupHoverEffect(bt_serie);
        }
    }

    // Volta a tela anterior
    @FXML
    private void handleVoltarCadastro(){
        trocarScene("Cadastro");
    }
    // Voltar menu
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }


    // Sai da aplicação
    @FXML
    protected void onSairButtonClick() throws IOException {
        trocarScene("Sair");
    }

    // Tela cadastro
    @FXML
    private TextField nomeField;
    @FXML
    private TextField generoField;
    @FXML
    private DatePicker publicacaoField;
    @FXML
    private TextField autorField;
    @FXML
    private TextField editoraField;
    @FXML
    private CheckBox possuiField;
    @FXML
    private TextField isbnField;


    @FXML
    protected void onEnviarClick() {
        if (validarCampos()) {
            // prosseguir com o envio
            System.out.println("Chamando o cadastro...");
            System.out.println(memoryManagementController.registerMedia(nomeField.getText(), generoField.getText(), publicacaoField.getValue().getYear(), autorField.getText(),editoraField.getText(),
                    isbnField.getText(), possuiField.isSelected()));
        }
    }


    /**
     * Verifica as informaçoes presentes nos campos
     * @return true para campos validos, false para inválidos
     */
    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty() || generoField.getText().trim().isEmpty() || publicacaoField.getValue() == null || autorField.getText().trim().isEmpty()
                || autorField.getText().trim().isEmpty() || editoraField.getText().trim().isEmpty() || isbnField.getText().trim().isEmpty()) {
            showAlert("Erro na validação", "CAMPOS INVÁLIDOS OU VAZIOS", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }


    // Muda a cena do cadastro
    public void mudarSceneCadastro(ActionEvent actionEvent) {
        Button botao = (Button) actionEvent.getSource();
        String destino = botao.getText();

        switch (destino) {
            case "Livro":
                trocarScene("Cadastro-livro");
                break;
            case "Filme":
                trocarScene("Cadastro-filme");
                break;
            case "Série":
                trocarScene("Cadastro-serie");
                break;
            default:
                break;
        };
    }


    // Adiciona efeitos nos botoes


    // Cadastro Filmes e Séries

    @FXML
    private TextField elencoField;
    @FXML
    private TextField ondeAssistirField;
    @FXML
    private Button addElenco;
    @FXML
    private Button addOndeAssistir;

    private final ObservableList<String> elencoList = FXCollections.observableArrayList();
    private final ObservableList<String> ondeAssistirList = FXCollections.observableArrayList();


    @FXML
    private void adicionarNome(ActionEvent event) {
        if(event.getSource()==addElenco) {
            String atores = elencoField.getText().trim();
            if (!atores.isEmpty()) {
                elencoList.add(atores);
                elencoField.clear();
            }
        System.out.println("Adicionando novo nome");
        } else if (event.getSource()==addOndeAssistir) {
            String ondeAssistir = ondeAssistirField.getText().trim();
            if(!ondeAssistir.isEmpty()) {
                ondeAssistirList.add(ondeAssistir);
                ondeAssistirField.clear();
            }
            System.out.println("Adicionando novo nome");

        }
    }

    @FXML
    private void verListaElenco() {
        listar(elencoList);
    }
    @FXML
    private void verListaOndeAssistir() {
        listar(ondeAssistirList);
    }
    private void listar(ObservableList<String> lista) {
        if (lista.isEmpty()) {
            showAlert("Lista vazia", "Nenhum nome foi adicionado ainda.", Alert.AlertType.INFORMATION);
            return;
        }

        StringBuilder conteudo = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            conteudo.append(i + 1).append(". ").append(lista.get(i)).append("\n");
        }

        showAlert("Lista", conteudo.toString(), Alert.AlertType.INFORMATION);
    }


}
