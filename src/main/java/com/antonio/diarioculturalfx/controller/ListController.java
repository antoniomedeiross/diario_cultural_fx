package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.services.ListService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;
import static com.antonio.diarioculturalfx.util.Util.mostrarDetalhes;

/**
 * Controlador de listagem
 */
public class ListController implements Initializable {
    public Button bt_busca;
    public Button voltarButton;
    public Label titulo;

    private final ListService listService = new ListService(memoryManagement);


    public BorderPane root;
    public VBox detalhesBoxContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Book> listaBooks = listService.listBooks();
        List<Film> listaFilms = listService.listFilms();
        List<Serie> listaSeries = listService.listSeries();

        setMedia(listaFilms); // tenho que fazer o tratamento para determinar a lista que será jogada como parâmetro

        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_busca != null ) {
            setupHoverEffect(bt_busca);
        }
    }


    // Voltar menu
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }


    @FXML
    private ListView<Media> listViewMidias;
    @FXML
    private TabPane tabPanePrincipal;

    private final ObservableList<Media> mediaObservable = FXCollections.observableArrayList();

    public void setMedia(List<? extends Media> listaDeMedia) {
        mediaObservable.setAll(listaDeMedia);
        listViewMidias.setItems(mediaObservable);

        listViewMidias.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Media media, boolean empty) {
                super.updateItem(media, empty);

                if (empty || media == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label nomeLabel = new Label(media.getTitle());
                    setGraphic(nomeLabel);
                }
            }
        });

        // Listener da lista
        listViewMidias.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                detalhesBoxContainer.setVisible(true);
                switch (newVal) {
                    case Book book -> mostrarDetalhes(book, detalhesBox);
                    case Film film -> mostrarDetalhes(film, detalhesBox);
                    case Serie serie -> mostrarDetalhes(serie, detalhesBox);
                    default -> {}
                }
            }
        });

    }

    @FXML
    private VBox detalhesBox;

    private void abrirDetalhes(Book livro) {
        detalhesBox.getChildren().clear(); // limpa conteúdo anterior
        detalhesBoxContainer.setVisible(true);
        detalhesBox.getChildren().addAll(
                new Label("Título: " + livro.getTitle()),
                new Label("Gênero: " + livro.getGender()),
                new Label("Ano de Lançamento: " + livro.getYearReleased()),
                new Label("Autor: " + livro.getAuthor()),
                new Label("Editora: " + livro.getPublisher()),
                new Label("ISBN: " + livro.getIsbn()),
                new Label("Disponível: " + (livro.isHaveBook() ? "Sim" : "Não"))
        );
    }

    private void abrirDetalhes(Film filme){
        detalhesBox.getChildren().clear(); // limpa conteúdo anterior
        detalhesBoxContainer.setVisible(true);
        StringBuilder atores;
        StringBuilder ondeAssistir;

        if (filme.getCast().isEmpty()){
            atores = new StringBuilder("Nenhum ator cadastrado");
        } else{
            atores = new StringBuilder("\n");
            for(String ator : filme.getCast()){
                atores.append("\t").append(ator).append("\n");
            }
        }

        if (filme.getWhereWatch().isEmpty()){
            ondeAssistir = new StringBuilder("Nenhum lugar cadastrado");
        } else{
            ondeAssistir = new StringBuilder("\n");
            for(String lugar : filme.getWhereWatch()){
                ondeAssistir.append("\t").append(lugar).append("\n");
            }
        }
        detalhesBox.getChildren().addAll(
                new Label("Título: " + filme.getTitle()),
                new Label("Título Original: " + filme.getOriginalTitle()),
                new Label("Gênero: " + filme.getGender()),
                new Label("Ano de Lançamento: " + filme.getYearReleased()),
                new Label("Diretor: " + filme.getDirector()),
                new Label("Roteirista: " + filme.getWriter()),
                new Label("Duração: " + filme.getDuration() + " Minutos"),
                new Label("Elenco: " + atores),
                new Label("Onde assitir: " + ondeAssistir)
        );
    }

    private void abrirDetalhes(Serie serie){
        detalhesBox.getChildren().clear(); // limpa conteúdo anterior
        detalhesBoxContainer.setVisible(true);
        StringBuilder atores;
        StringBuilder ondeAssistir;

        if (serie.getCast().isEmpty()){
            atores = new StringBuilder("Nenhum ator cadastrado");
        } else{
            atores = new StringBuilder("\n");
            for(String ator : serie.getCast()){
                atores.append("\t").append(ator).append("\n");
            }
        }

        if (serie.getWhereWatch().isEmpty()){
            ondeAssistir = new StringBuilder("Nenhum lugar cadastrado");
        } else{
            ondeAssistir = new StringBuilder("\n");
            for(String lugar : serie.getWhereWatch()){
                ondeAssistir.append("\t").append(lugar).append("\n");
            }
        }
        detalhesBox.getChildren().addAll(
                new Label("Título: " + serie.getTitle()),
                new Label("Título Original: " + serie.getOriginalTitle()),
                new Label("Gênero: " + serie.getGender()),
                new Label("Ano de Lançamento: " + serie.getYearReleased()),
                new Label("Ano de Encerramento: " + serie.getYearEnding()),
                new Label("Elenco: " + atores),
                new Label("Onde assitir: " + ondeAssistir)
        );
    }


}
