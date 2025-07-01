package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.model.Media;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.*;
import static com.antonio.diarioculturalfx.util.Util.setupHoverEffect;

/**
 * Controlador do menu principal
 */
public class MenuPrincipalController implements  Initializable {

    @FXML
    private VBox div_titulo;

    /**
     * Método de inicialização
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Carrega efeitos dos botões
        if (div_titulo != null) {
            for (Node node : div_titulo.getChildren()) {
                if (node instanceof Button button) {
                    setupHoverEffect(button);
                }
            }
        }

        // inicialização do arquivo
        File aqv = memoryManagement.buscaOuCriarArquivo();
        Map<String, List<Media>> response = memoryManagementController.loadDataBase(aqv);
        if (response != null) {
            memoryManagement.populaOsArrays(response);
        }
    }

    /**
     * Leva para a tela principal de Cadastro
     */
    @FXML
    protected void onCadastroButtonClick() { trocarScene("Cadastro"); }

    /**
     * Leva para a tela principal de Avaliação
     */
    @FXML
    protected void onAvaliarButtonClick() { trocarScene("Avaliar"); }

    /**
     * Leva para a tela principal de Listagem
     */
    @FXML
    protected void onListarButtonClick() { trocarScene("Listar-tipo"); }

    /**
     * Leva para a tela principal de Busca
     */
    @FXML
    protected void onBuscarButtonClick() { trocarScene("Buscar"); }

    /**
     * Sai da aplicação
     */
    @FXML
    protected void onSairButtonClick() {
        trocarScene("Sair");
    }


}
