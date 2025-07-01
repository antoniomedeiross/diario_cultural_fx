package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.model.*;
import com.antonio.diarioculturalfx.repository.MemoryManagement;
import com.antonio.diarioculturalfx.services.EvaluationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static com.antonio.diarioculturalfx.DiarioCultural.*;
import static com.antonio.diarioculturalfx.util.Util.*;

/**
 * Controlador de avaliação
 */
public class EvaluationController implements Initializable {
    public Button bt_salvar;

    // Controller dos elementos FX

    @FXML
    private Button bt_livro;
    @FXML
    private Button bt_filme;
    @FXML
    private Button bt_serie;
    @FXML
    private Button voltarButton;
    @FXML
    private ListView<Book> listaLivros;
    @FXML
    private ListView<Film> listaFilms;
    @FXML
    private ListView<Serie> listaSeries;
    @FXML
    private ListView<Season> listaTemporadas;
    @FXML
    private TextField notaField;
    @FXML
    private TextArea comentField;
    @FXML
    private TextField quandoLeuField;

    @FXML
    private Button addTemporadaButton;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField episodiosField;
    @FXML
    private DatePicker anoField;

    private final ObservableList<Book> livros = FXCollections.observableArrayList();
    private final ObservableList<Film> filmes = FXCollections.observableArrayList();
    private final ObservableList<Serie> series = FXCollections.observableArrayList();
    private final ObservableList<Season> temporadas = FXCollections.observableArrayList();

    private Book livroAtual;
    private Film filmeAtual;
    private static Serie serieAtual;
    private Season temporadaAtual;

    private final MemoryManagement memoryManagementAvaliacao = memoryManagement;

    public VBox campoAvaliacao;


    EvaluationService evaluationService = new EvaluationService();



    /**
     * Metodo de inicialização
     * @param location local
     * @param resources resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_filme != null && bt_livro != null && bt_serie != null) {
            setupHoverEffect(bt_livro);
            setupHoverEffect(bt_filme);
            setupHoverEffect(bt_serie);
        }
        if(bt_salvar!=null) {
            setupHoverEffect(bt_salvar);
        }
        if(addTemporadaButton != null){
            setupHoverEffect(addTemporadaButton);
            addTemporadaButton.setVisible(false);
            addTemporadaButton.setManaged(false);
        }

        // listagem
        if(listaLivros != null) {
            configuraListView(listaLivros, livros, livroSelecionado->{
                livroAtual = livroSelecionado;
                notaField.setText(String.valueOf(livroSelecionado.getReview().getNote()));
                comentField.setText(livroSelecionado.getReview().getComment());
                quandoLeuField.setText(livroSelecionado.getReview().getWhenReadWatch());
            });
        }else if(listaFilms != null) {
            configuraListView(listaFilms, filmes, filmeSelecionado->{
                filmeAtual = filmeSelecionado;
                notaField.setText(String.valueOf(filmeSelecionado.getReview().getNote()));
                comentField.setText(filmeSelecionado.getReview().getComment());
                quandoLeuField.setText(filmeSelecionado.getReview().getWhenReadWatch());
            });
        } else if(listaSeries != null) {
            configuraListView(listaSeries, series, seriesSelecionado->{
                serieAtual = seriesSelecionado;
                temporadas.setAll(seriesSelecionado.getSeasons());
                listaTemporadas.setItems(temporadas);
                mostrarTempButton();

                listaTemporadas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                    temporadaAtual = newVal;
                    if (temporadaAtual != null) {
                        notaField.setText(String.valueOf(temporadaAtual.getReview().getNote()));
                        comentField.setText(temporadaAtual.getReview().getComment());
                        quandoLeuField.setText(temporadaAtual.getReview().getWhenReadWatch());
                    }
                });
            });
        }
        atualizarLista();
    }

    /**
     * Configura a ListView para seleção
     * @param listView, listView de subclasses de Media
     * @param listaObserv, lista de mídia que serão exibidas
     * @param onSelect, ação quando umm item é selecionado
     * @param <T>, elemento que estende Media
     */
    private <T extends Media> void configuraListView(ListView<T> listView, ObservableList<T> listaObserv, Consumer<T> onSelect) {
        if(listaObserv != null) {
            listView.setItems(listaObserv);
            listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    onSelect.accept(newVal);
                }
            });
        }
    }


    /**
     * Atualiza a lista de midias
     */
    public void atualizarLista() {
        ArrayList<Book> listaBooks = memoryManagementAvaliacao.getBooks();
        ArrayList<Film> listaFilms = memoryManagementAvaliacao.getFilms();
        ArrayList<Serie> listaSeries = memoryManagementAvaliacao.getSeries();

        livros.setAll(listaBooks); // recarrega os dados da "base"
        filmes.setAll(listaFilms);
        series.setAll(listaSeries);
    }

    /**
     * Avalia a mídia
     */
    @FXML
    public void salvarProduto() {
        if (livroAtual != null) {
            try {
                evaluationService.evaluate(livroAtual, Integer.parseInt(notaField.getText()), comentField.getText(),
                        true, String.valueOf(quandoLeuField.getText()));
                showAlert("Avaliação bem sucedida", "Livro: " + livroAtual.getTitle() + " avaliado com sucesso!", Alert.AlertType.CONFIRMATION);
            }catch (IllegalArgumentException e) {
                showAlert("Avaliação inválida", e.getMessage(), Alert.AlertType.ERROR );
            }
            limparCampos();
            listaLivros.refresh();
        } else if(filmeAtual != null) {
            try {
                evaluationService.evaluate(filmeAtual, Integer.parseInt(notaField.getText()), comentField.getText(),
                        true, String.valueOf(quandoLeuField.getText()));
                showAlert("Avaliação bem sucedida", "Filme: " + filmeAtual.getTitle() + " avaliado com sucesso!", Alert.AlertType.CONFIRMATION);
            }catch (IllegalArgumentException e) {
                showAlert("Avaliação inválida", e.getMessage(), Alert.AlertType.ERROR );
            }
            limparCampos();
            listaFilms.refresh();
        } else if(temporadaAtual != null) {
            try {
                evaluationService.evaluate(temporadaAtual, Integer.parseInt(notaField.getText()),
                        comentField.getText(),true, quandoLeuField.getText(), serieAtual);
                showAlert("Avaliação", temporadaAtual.getTitle() +" -- "+ serieAtual.getTitle() + " avaliado com sucesso!", Alert.AlertType.CONFIRMATION);
            }catch (IllegalArgumentException e) {
                showAlert("Avaliação inválida", e.getMessage(), Alert.AlertType.ERROR );
            }
        }
    }

    /**
     * Limpa os campos de digitação
     */
    public void limparCampos() {

        if(notaField != null || comentField != null || quandoLeuField != null) {
            notaField.clear();
            comentField.clear();
            quandoLeuField.clear();
        }

        if(listaLivros != null) {
            listaLivros.getSelectionModel().clearSelection();
        }else if(listaFilms != null) {
            listaFilms.getSelectionModel().clearSelection();
        } else if(listaSeries != null) {
            listaSeries.getSelectionModel().clearSelection();

            listaTemporadas.getSelectionModel().clearSelection();
            listaTemporadas.getItems().clear();

        }

        if(addTemporadaButton != null){
            addTemporadaButton.setManaged(false);
            addTemporadaButton.setVisible(false);
        }

        if(nomeField != null || anoField != null || episodiosField != null){
            nomeField.clear();
            episodiosField.clear();
            anoField.setValue(null);
        }
    }

    /**
     * Volta para a tela principal de avaliação
     */
    @FXML
    private void handleVoltarAvaliacao(){
        limparCampos();
        trocarScene("Avaliar");
    }

    /**
     * Volta para o menu principal
     */
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }

    /**
     * Volta para a tela de avaliação de série
     */
    @FXML
    private void handleVoltarAvaliacaoSerie(){
        trocarScene("Avaliar-serie");
    }


    /**
     * Muda a cena da avaliação
     * @param actionEvent
     */
    @FXML
    public void mudarSceneAvaliacao(ActionEvent actionEvent) {
        if(actionEvent.getSource() == bt_livro) {
            trocarScene("Avaliar-livro");
        } else if(actionEvent.getSource() == bt_filme) {
            trocarScene("Avaliar-filme");
        } else if(actionEvent.getSource() == bt_serie) {
            trocarScene("Avaliar-serie");
        } else if(actionEvent.getSource() == addTemporadaButton){
            trocarScene("Add-temporada");
            limparCampos();
        }
    }

    /**
     * Torna visível o botão de adicionar temporada
     */
    @FXML
    private void mostrarTempButton(){
        addTemporadaButton.setVisible(true);
        addTemporadaButton.setManaged(true);
    }

    /**
     * Adiciona temporada
     */
    @FXML
    private void addTemporada(){
        String sucesso;

        if(isNumeric(episodiosField.getText())) {
            sucesso = memoryManagementController.registerSeason(serieAtual, nomeField.getText(),
                    anoField.getValue().getYear(), Integer.parseInt(episodiosField.getText()));
        }else {
            sucesso = "Tentativa de registro FALHO\nNúmero de episódios inválido.";
        }

        if(sucesso.equals("Temporada registrada com SUCESSO!")){
            showAlert("Cadastro feito com Sucesso", sucesso, Alert.AlertType.CONFIRMATION);
            trocarScene("Avaliar-serie");
            limparCampos();
            memoryManagementController.salvarArquivos();
        }else {
            showAlert("Erro na Validação", sucesso, Alert.AlertType.ERROR);
        }
    }
}
