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

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;

/**
 * Controlador de avaliação
 */
public class EvaluationController implements Initializable {

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

    private final ObservableList<Book> livros = FXCollections.observableArrayList();
    private final ObservableList<Film> filmes = FXCollections.observableArrayList();
    private final ObservableList<Serie> series = FXCollections.observableArrayList();
    private final ObservableList<Season> temporadas = FXCollections.observableArrayList();

    private Book livroAtual;
    private Film filmeAtual;
    private Serie serieAtual;
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


        if (memoryManagementAvaliacao.getBooks().isEmpty()) {
            encheMemoria(memoryManagementAvaliacao);
        }
        System.out.println(memoryManagementAvaliacao.getBooks().toString());


        // listagem
        if(listaLivros != null) {
            configuraListView(listaLivros, livros, livroSelecionado->{
                livroAtual = livroSelecionado;
                notaField.setText(String.valueOf(livroSelecionado.getReview().getNote()));
                comentField.setText(livroSelecionado.getReview().getComment());
                quandoLeuField.setText(livroSelecionado.getReview().getWhenReadWatch());
            });
        }else if(listaFilms != null) {
            configuraListView(listaFilms, filmes, filmsSelecionado->{
                filmeAtual = filmsSelecionado;
                notaField.setText(String.valueOf(filmsSelecionado.getReview().getNote()));
                comentField.setText(filmsSelecionado.getReview().getComment());
                quandoLeuField.setText(filmsSelecionado.getReview().getWhenReadWatch());
            });
        } else if(listaSeries != null) {
            configuraListView(listaSeries, series, seriesSelecionado->{
                serieAtual = seriesSelecionado;
                temporadas.setAll(seriesSelecionado.getSeasons());
                listaTemporadas.setItems(temporadas);

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

    @FXML
    public void salvarProduto() {
        if (livroAtual != null) {
            try {
                evaluationService.evaluate(livroAtual, Integer.parseInt(notaField.getText()), comentField.getText(),
                        true, String.valueOf(quandoLeuField.getText()));
                showAlert("Avaliação", "Livro: " + livroAtual.getTitle() + " avaliado com sucesso!", Alert.AlertType.CONFIRMATION);
            }catch (IllegalArgumentException e) {
                showAlert("Avaliação inválida", e.getMessage(), Alert.AlertType.ERROR );
            }
            limparCampos();
            listaLivros.refresh();
        } else if(temporadaAtual != null) {
            try {
                evaluationService.evaluate(temporadaAtual, Integer.parseInt(notaField.getText()),
                        comentField.getText(),true, quandoLeuField.getText(), serieAtual);
                showAlert("Avaliação", "Temporada: " + temporadaAtual.getTitle() +" -- "+ serieAtual.getTitle() + " avaliado com sucesso!", Alert.AlertType.CONFIRMATION);
            }catch (IllegalArgumentException e) {
                showAlert("Avaliação inválida", e.getMessage(), Alert.AlertType.ERROR );
            }
        }
    }

    public void limparCampos() {
        notaField.clear();
        comentField.clear();
        quandoLeuField.clear();
    }

    @FXML
    private void handleVoltarAvaliacao(){
        trocarScene("Avaliar");
    }
    // Voltar menu
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }


    @FXML
    public void mudarSceneAvaliacao(ActionEvent actionEvent) {
        if(actionEvent.getSource() == bt_livro) {
            trocarScene("Avaliar-livro");
        } else if(actionEvent.getSource() == bt_filme) {
            trocarScene("Avaliar-filme");
        } else if(actionEvent.getSource() == bt_serie) {
            trocarScene("Avaliar-serie");
        }
    }
}
