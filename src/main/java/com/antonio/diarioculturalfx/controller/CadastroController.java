package com.antonio.diarioculturalfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;

public class CadastroController implements Initializable {
    MemoryManagementController memoryManagementController = new MemoryManagementController(memoryManagement);

    // Adiciona efeitos nos botoes

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

        if(bt_livro!=null && bt_filme!=null && bt_serie!=null) {
            setupHoverEffect(bt_livro);
            setupHoverEffect(bt_filme);
            setupHoverEffect(bt_serie);
        }
    }

    // Controller elementos FX cadastro
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
            // Fazer integração aqui
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
