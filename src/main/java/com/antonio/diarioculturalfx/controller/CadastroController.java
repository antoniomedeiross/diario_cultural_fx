package com.antonio.diarioculturalfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;

import static com.antonio.diarioculturalfx.DiarioCultural.*;
import static com.antonio.diarioculturalfx.util.Util.*;

public class CadastroController implements Initializable {
    private static String tipoCadastro;

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
        // add efeitos nos botões
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
        String validar = "";

        if (validarCampos()) {
            // prosseguir com o envio
            System.out.println("Chamando o cadastro...");
            System.out.println(tipoCadastro);
            ArrayList<String> elencoArraylist = new ArrayList<>(elencoList);
            ArrayList<String> ondeAssistirArrayList = new ArrayList<>(ondeAssistirList);
            // cadastro das mídias

            validar = switch (tipoCadastro) {
                // livro
                case "Livro" -> memoryManagementController.registerMedia(
                        nomeField.getText(), generoField.getText(), publicacaoField.getValue().getYear(),
                        autorField.getText(), editoraField.getText(), isbnField.getText(),
                        possuiField.isSelected()
                );
                // filme
                case "Filme" -> memoryManagementController.registerMedia(nomeField.getText(), generoField.getText(),
                        publicacaoField.getValue().getYear(), Integer.parseInt(duracaoField.getText()),
                        diretorField.getText(), escritorField.getText(), elencoArraylist,
                        tituloOriginalField.getText(), ondeAssistirArrayList
                );

                // serie
                case "Série" -> memoryManagementController.registerMedia(nomeField.getText(), generoField.getText(),
                        publicacaoField.getValue().getYear(), anoEncerramentoField.getValue().getYear(), elencoArraylist,
                        tituloOriginalField.getText(), ondeAssistirArrayList
                );
                default -> validar;
            };

            // cadastro sucesso
            if(validar.equals("Registrado com SUCESSO!") || validar.equals("Registrada com SUCESSO!")){
                showAlert("Cadastro feito com Sucesso",  tipoCadastro + " " + validar, Alert.AlertType.CONFIRMATION);
                memoryManagementController.salvarArquivos();
                limparCampos();
            }
            // erro no cadastro
            else {
                showAlert("Erro na Validação", validar, Alert.AlertType.ERROR);
            }
        }
    }


    /**
     * Verifica as informaçoes presentes nos campos
     * @return true para campos validos, false para inválidos
     */
    private boolean validarCampos() {

        // valida de acordo a midia
        switch(tipoCadastro){

            case "Livro":
                if (nomeField.getText().trim().isEmpty() || generoField.getText().trim().isEmpty() || publicacaoField.getValue() == null || autorField.getText().trim().isEmpty()
                        || autorField.getText().trim().isEmpty() || editoraField.getText().trim().isEmpty() || isbnField.getText().trim().isEmpty()) {
                    showAlert("Erro na Validação", "CAMPOS VAZIOS", Alert.AlertType.ERROR);
                    return false;
                }
                break;

            case "Filme":
                if (nomeField.getText().trim().isEmpty() || generoField.getText().trim().isEmpty() || publicacaoField.getValue() == null || diretorField.getText().trim().isEmpty()
                        || tituloOriginalField.getText().trim().isEmpty() || duracaoField.getText().trim().isEmpty() || escritorField.getText().trim().isEmpty() ||
                        ondeAssistirList.isEmpty() || elencoList.isEmpty()) {
                    showAlert("Erro na Validação", "CAMPOS VAZIOS", Alert.AlertType.ERROR);
                    return false;
                } else if(!isNumeric(duracaoField.getText())){ // valida a duracao
                    showAlert("Erro na Validação", "CAMPO DURAÇÃO INVÁLIDO", Alert.AlertType.ERROR);
                    return false;
                }
                break;

            case "Série":
                if (nomeField.getText().trim().isEmpty() || generoField.getText().trim().isEmpty() || publicacaoField.getValue() == null ||
                        ondeAssistirList.isEmpty() || elencoList.isEmpty() || anoEncerramentoField.getValue() == null ||
                        tituloOriginalField.getText().trim().isEmpty()) {
                    showAlert("Erro na Validação", "CAMPOS VAZIOS", Alert.AlertType.ERROR);
                    return false;
                }
                break;
        }
        return true;
    }


    // Muda a cena do cadastro
    public void mudarSceneCadastro(ActionEvent actionEvent) {
        Button botao = (Button) actionEvent.getSource();
        String destino = botao.getText();
        tipoCadastro = destino;

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
    private TextField diretorField;
    @FXML
    private TextField duracaoField;
    @FXML
    private TextField tituloOriginalField;
    @FXML
    private TextField escritorField;
    @FXML
    private TextField elencoField;
    @FXML
    private TextField ondeAssistirField;
    @FXML
    private Button addElenco;
    @FXML
    private Button addOndeAssistir;
    @FXML
    private DatePicker anoEncerramentoField;

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

    // limpa os campos
    void limparCampos(){

        switch (tipoCadastro){
            case "Livro":
                nomeField.clear();
                generoField.clear();
                publicacaoField.setValue(null);
                autorField.clear();
                editoraField.clear();
                possuiField.setSelected(false);
                isbnField.clear();
                break;

            case "Filme":
                nomeField.clear();
                generoField.clear();
                publicacaoField.setValue(null);
                diretorField.clear();
                duracaoField.clear();
                tituloOriginalField.clear();
                escritorField.clear();
                elencoField.clear();
                elencoList.clear();
                ondeAssistirField.clear();
                ondeAssistirList.clear();
                break;

            case "Série":
                nomeField.clear();
                generoField.clear();
                publicacaoField.setValue(null);
                tituloOriginalField.clear();
                elencoField.clear();
                elencoList.clear();
                ondeAssistirField.clear();
                anoEncerramentoField.setValue(null);
                ondeAssistirList.clear();
                break;
        }
    }

}
