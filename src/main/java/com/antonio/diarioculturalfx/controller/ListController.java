package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.services.ListService;
import com.antonio.diarioculturalfx.services.SearchService;
import eu.hansolo.tilesfx.addons.Switch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;
import static com.antonio.diarioculturalfx.util.Util.mostrarDetalhes;
import static com.antonio.diarioculturalfx.DiarioCultural.*;
import static com.antonio.diarioculturalfx.util.Util.*;

/**
 * Controlador de listagem
 */
public class ListController implements Initializable {

    @FXML
    private Button bt_livro;
    @FXML
    private Button bt_filme;
    @FXML
    private Button bt_serie;
    @FXML
    private Button filtroButtom;
    @FXML
    private Label filtroLabel;
    @FXML
    private TextField filtroField;

    public Button bt_busca;
    public Button voltarButton;
    public Label titulo;

  
    private final ListService listService = new ListService(memoryManagement);


    private SearchService searchService = new SearchService(memoryManagement);

    private static String tipoLista;
    private static String tipoFiltro;
    private static String dado;

    private ArrayList<Book> listaBooks = new ArrayList<>();
    private ArrayList<Film> listaFilms = new ArrayList<>();
    private ArrayList<Serie> listaSeries = new ArrayList<>();

    public BorderPane root;
    public VBox detalhesBoxContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Book> listaBooks = listService.listBooks();
        List<Film> listaFilms = listService.listFilms();
        List<Serie> listaSeries = listService.listSeries();

        setMedia(listaFilms); // tenho que fazer o tratamento para determinar a lista que será jogada como parâmetro
        if(filtroCombo != null){
            filtroCombo.getItems().addAll(
                    "Maior --> Menor",
                    "Menor --> Maior",
                    "Ano",
                    "Gênero"
            );
        }

        if(filtroLabel != null && filtroField != null && filtroButtom != null){
            filtroLabel.setVisible(false);
            filtroField.setVisible(false);
            filtroField.setManaged(false);

            setupHoverEffect(filtroButtom);
            filtroButtom.setManaged(false);
            filtroButtom.setVisible(false);
        }

        if(listViewMidias != null && tipoLista != null){
            setPessoas(determinarLista("Livro"));
        }

        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_busca != null && bt_livro!=null && bt_filme!=null && bt_serie!=null) {
            setupHoverEffect(bt_busca);
            setupHoverEffect(bt_livro);
            setupHoverEffect(bt_filme);
            setupHoverEffect(bt_serie);
        }
    }


    // Voltar menu
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }


    @FXML
    private ListView<Media> listViewMidias ;
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

    @FXML
    private VBox avaliacaoBox;

    private void abrirDetalhes(Book livro) {
        avaliacaoBox.getChildren().clear();
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

        avaliacaoBox.getChildren().addAll(
                new Label("Nota: " + livro.getReview().getNote()),
                new Label("Ano que Leu: " + livro.getReview().getWhenReadWatch()),
                new Label("Comentários: " + "\n\t" + livro.getReview().getComment())
        );
    }

    private void abrirDetalhes(Film filme){
        avaliacaoBox.getChildren().clear();
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

        avaliacaoBox.getChildren().addAll(
                new Label("Nota: " + filme.getReview().getNote()),
                new Label("Ano que Viu: " + filme.getReview().getWhenReadWatch()),
                new Label("Comentários: " + "\n\t" + filme.getReview().getComment())
        );
    }

    private void abrirDetalhes(Serie serie){
        avaliacaoBox.getChildren().clear();
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

        avaliacaoBox.getChildren().addAll(
                new Label("Nota: " + serie.getReview().getNote()),
                new Label("Ano que Viu: " + serie.getReview().getWhenReadWatch()),
                new Label("Comentários: " + "\n\t" + serie.getReview().getComment())
        );
    }

    private ArrayList<? extends Media> determinarLista(String tipo){

        return switch (tipo) {
            case "Livro" -> listaBooks;
            case "Filme" -> listaFilms;
            case "Série" -> listaSeries;
            default -> null;
        };
    }

    private ArrayList<? extends Media> filtrarMedia(String filtro, String dado,
                                                    ArrayList<? extends Media> listaMedias,
                                                    String tipo){
        
        int dadint = 0;
        if(isNumeric(dado)) {
            dadint = Integer.parseInt(dado);
        }
        switch (filtro){
            case "Maior --> Menor":
                return listaMedias;
            case "Menor --> Maior":
                ArrayList<? extends Media> listaAuxiliar = new ArrayList<>(listaMedias);
                Collections.reverse(listaAuxiliar);
                return listaAuxiliar;
            case "Ano":
                switch (tipo){
                    case "Livro":
                        return searchService.searchBookYearReleased(dadint);
                    case "Filme":
                        return searchService.searchFilmYearReleased(dadint);
                    case "Série":
                        return searchService.searchSerieYearReleased(dadint);
                }
                break;
            case "Gênero":
                switch (tipo){
                    case "Livro":
                        return searchService.searchBookGender(dado);
                    case "Filme":
                        return searchService.searchFilmGender(dado);
                    case "Série":
                        return searchService.searchSerieGender(dado);
            }
                break;
        }
        return null;
    }

    @FXML
    private void handleVoltarListar(){
        limparCampos();
        trocarScene("Listar-tipo");
    }

    public void mudarSceneListar(ActionEvent actionEvent){
        Button botao = (Button) actionEvent.getSource();
        tipoLista = botao.getText();
        trocarScene("Listar");
        ListController controller = (ListController) getListaScene().getUserData();
        controller.setPessoas(determinarLista(tipoLista));
    }

    private void limparCampos(){
        detalhesBox.getChildren().clear();
        avaliacaoBox.getChildren().clear();
    }

    @FXML
    private ComboBox filtroCombo;

    public void selecionarFiltro(ActionEvent actionEvent) {
        tipoFiltro = (String) filtroCombo.getValue();

        if(tipoFiltro.equals("Menor --> Maior") || tipoFiltro.equals("Maior --> Menor")){
            setPessoas(filtrarMedia(tipoFiltro, dado, determinarLista(tipoLista), tipoLista));
        } else{
            filtroLabel.setText(tipoFiltro + ": ");
            filtroLabel.setVisible(true);
            filtroField.setVisible(true);
            filtroField.setManaged(true);
            filtroButtom.setManaged(true);
            filtroButtom.setVisible(true);
        }
    }

    public void aplicarFiltro(ActionEvent actionEvent) {

        // fazer validação do ano
        // tem algo de errado com a ordenação de série
        setPessoas(filtrarMedia(tipoFiltro, filtroField.getText(), determinarLista(tipoLista), tipoLista));
    }
}
