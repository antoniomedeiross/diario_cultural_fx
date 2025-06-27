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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.addImgOnButton;
import static com.antonio.diarioculturalfx.util.Util.setupHoverEffect;

/**
 * Controlador de listagem
 */
public class ListController implements Initializable {
    public Button bt_busca;
    public Button voltarButton;
    public Label titulo;

    private ListService listService = new ListService(memoryManagement);

    private List<Book> listaBooks = new ArrayList<>();
    private List<Film> listaFilms = new ArrayList<>();
    private List<Serie> listaSeries = new ArrayList<>();

    public BorderPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaBooks = listService.listBooks();
        listaFilms = listService.listFilms();
        listaSeries = listService.listSeries();

        setPessoas(listaFilms); // tenho que fazer o tratamento para determinar a lista que será jogada como parâmetro

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
    private void listar() {

    }


    @FXML
    private ListView<Media> listViewMidias;
    @FXML
    private TabPane tabPanePrincipal;

    private final ObservableList<Media> mediaObservable = FXCollections.observableArrayList();

    public void setPessoas(List<? extends Media> listaDePessoas) {
        mediaObservable.setAll(listaDePessoas);
        listViewMidias.setItems(mediaObservable);

        listViewMidias.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Media pessoa, boolean empty) {
                super.updateItem(pessoa, empty);

                if (empty || pessoa == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label nomeLabel = new Label(pessoa.getTitle());
                    nomeLabel.setStyle("-fx-text-fill: white;");
                    Button verMaisButton = new Button("Ver mais");
                    verMaisButton.getStyleClass().add("botao-ver-mais");

                    verMaisButton.setOnAction(e -> {
                        if(pessoa instanceof Book book){
                            abrirDetalhes(book);
                        } else if (pessoa instanceof Film film) {
                            abrirDetalhes(film);
                        } else if (pessoa instanceof Serie serie) {
                            abrirDetalhes(serie);
                        } // instanceof para a sobrecarga
                    });

                    HBox hbox = new HBox(10, nomeLabel, verMaisButton);
                    hbox.getStyleClass().add("list-cell-hbox");
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    setGraphic(hbox);
                }
            }
        });
    }

    @FXML
    private VBox detalhesBox;

    private void abrirDetalhes(Book livro) {
        detalhesBox.getChildren().clear(); // limpa conteúdo anterior

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

        String atores;
        String ondeAssistir;

        if (filme.getCast().isEmpty()){
            atores = "Nenhum ator cadastrado";
        } else{
            atores = "\n";
            for(String ator : filme.getCast()){
                atores += "\t" + ator + "\n";
            }
        }

        if (filme.getWhereWatch().isEmpty()){
            ondeAssistir = "Nenhum lugar cadastrado";
        } else{
            ondeAssistir = "\n";
            for(String lugar : filme.getWhereWatch()){
                ondeAssistir += "\t" + lugar + "\n";
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

        String atores;
        String ondeAssistir;

        if (serie.getCast().isEmpty()){
            atores = "Nenhum ator cadastrado";
        } else{
            atores = "\n";
            for(String ator : serie.getCast()){
                atores += "\t" + ator + "\n";
            }
        }

        if (serie.getWhereWatch().isEmpty()){
            ondeAssistir = "Nenhum lugar cadastrado";
        } else{
            ondeAssistir = "\n";
            for(String lugar : serie.getWhereWatch()){
                ondeAssistir += "\t" + lugar + "\n";
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
