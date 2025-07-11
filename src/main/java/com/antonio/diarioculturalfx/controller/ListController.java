package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.DiarioCultural;
import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.services.ListService;
import com.antonio.diarioculturalfx.services.SearchService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;
import static com.antonio.diarioculturalfx.DiarioCultural.*;


/**
 * Controlador de listagem
 */
public class ListController implements Initializable {

    public VBox detalhesBoxContainer;
    public Button bt_editar;
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
    private Media mediaSelecionada;

    private final ListService listService = new ListService(memoryManagement);
    private final SearchService searchService = new SearchService(memoryManagement);

    private static String tipoLista;
    private static String tipoFiltro;
    private static String dado;

    private List<Book> listaBooks;
    private List<Film> listaFilms;
    private List<Serie> listaSeries;

    @FXML
    private ListView<Media> listViewMidias ;

    private final ObservableList<Media> mediaObservable = FXCollections.observableArrayList();

    public BorderPane root;

    /**
     * Método de inicialização
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaBooks = listService.listBooks();
        listaFilms = listService.listFilms();
        listaSeries = listService.listSeries();

        if( bt_livro != null && bt_filme != null && bt_serie != null) {
            setupHoverEffect(bt_livro);
            setupHoverEffect(bt_filme);
            setupHoverEffect(bt_serie);
        }
        if(bt_editar!=null) {
            setupHoverEffect(bt_editar);
            setupHoverEffect(filtroButtom);
        }

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

            filtroButtom.setManaged(false);
            filtroButtom.setVisible(false);
        }

        if(listViewMidias != null && tipoLista != null){
            listService.ordenarPorNota(memoryManagement.getBooks());
            listService.ordenarPorNota(memoryManagement.getFilms());
            listService.ordenarPorNota(memoryManagement.getSeries());

            listaFilms = listService.listFilms();
            listaSeries = listService.listSeries();
            listaBooks = listService.listBooks();
            setMedia(determinarLista("Livro"));
        }

        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_busca != null && bt_livro!=null && bt_filme!=null && bt_serie!=null) {
            setupHoverEffect(bt_busca);
            setupHoverEffect(bt_livro);
            setupHoverEffect(bt_filme);
            setupHoverEffect(bt_serie);
        }
    }


    /**
     * Volta para o menu principal
     */
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
        if(detalhesBoxContainer != null) {
            detalhesBoxContainer.setVisible(false);
        }
    }


    /**
     * Configura a lista de mídias que será exibida
     * Configura a listView para seleção
     * @param listaDeMedia, lista de tipos que estendem de Media
     */
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
                mediaSelecionada = newVal;
                detalhesBoxContainer.setVisible(true);
                switch (newVal) {
                    case Book book -> abrirDetalhes(book, detalhesBox, avaliacaoBox, detalhesBoxContainer);
                    case Film film -> abrirDetalhes(film, detalhesBox, avaliacaoBox, detalhesBoxContainer);
                    case Serie serie -> abrirDetalhes(serie, detalhesBox, avaliacaoBox, detalhesBoxContainer);
                    default -> {}
                }
            }
        });

    }

    @FXML
    private VBox detalhesBox;

    @FXML
    private HBox avaliacaoBox;


    /**
     * Retorna a lista do tipo de mídia a ser listado
     * @param tipo, tipo da lista desejada
     * @return Lista de livros para "Livro", Lista de filmes pra "Filme, Lista de séries para "Série"
     */
    private List<? extends Media> determinarLista(String tipo){
        return switch (tipo) {
            case "Livro" -> listService.listBooks();
            case "Filme" -> listService.listFilms();
            case "Série" -> listService.listSeries();
            default -> null;
        };
    }

    /**
     *
     * @param filtro, tipo de filtro a ser aplicado
     * @param dado, dado a ser filtrado, se necessário
     * @param listaMedias, Lista a ser aplicada o filtro
     * @param tipo, tipo da Lista
     * @return Lista filtrada
     */
    private List<? extends Media> filtrarMedia(String filtro, String dado,
                                                    List<? extends Media> listaMedias,
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

    /**
     * Volta para a tela principal de listagem
     */
    @FXML
    private void handleVoltarListar(){
        limparCampos();
        trocarScene("Listar-tipo");
    }

    /**
     * Muda a cena da listagem
     * @param actionEvent
     */
    public void mudarSceneListar(ActionEvent actionEvent){
        Button botao = (Button) actionEvent.getSource();
        tipoLista = botao.getText();
        trocarScene("Listar");
        ListController controller = (ListController) getListaScene().getUserData();
        controller.setMedia(determinarLista(tipoLista));
    }

    /**
     * Limpa os campos de detalhes da mídia
     */
    private void limparCampos(){
        detalhesBox.getChildren().clear();
        avaliacaoBox.getChildren().clear();
        detalhesBoxContainer.setVisible(false);
    }

    @FXML
    private ComboBox<String> filtroCombo;

    /**
     * Determina visibilidade dos elementos relacionados ao dado do filtro
     * @param actionEvent
     */
    public void selecionarFiltro(ActionEvent actionEvent) {
        tipoFiltro = filtroCombo.getValue();

        if(tipoFiltro.equals("Menor --> Maior") || tipoFiltro.equals("Maior --> Menor")){
            setMedia(filtrarMedia(tipoFiltro, dado, determinarLista(tipoLista), tipoLista));

            filtroLabel.setVisible(false);
            filtroField.setVisible(false);
            filtroField.setManaged(false);

            filtroButtom.setManaged(false);
            filtroButtom.setVisible(false);
        } else{
            filtroLabel.setText(tipoFiltro + ": ");
            filtroLabel.setVisible(true);
            filtroField.setVisible(true);
            filtroField.setManaged(true);
            filtroButtom.setManaged(true);
            filtroButtom.setVisible(true);
        }
    }

    /**
     * Aplica filtro a lista
     * @param actionEvent
     */
    public void aplicarFiltro(ActionEvent actionEvent) {
        // tem algo de errado com a ordenação de série

        if(tipoFiltro.equals("Ano")){
            if(!isNumeric(filtroField.getText())){
                showAlert("Erro na Validação", "Ano inválido", Alert.AlertType.ERROR);
            } else if (!validarAno(Integer.parseInt(filtroField.getText()))) {
                showAlert("Erro na Validação", "Ano inválido", Alert.AlertType.ERROR);
            } else{
                setMedia(filtrarMedia(tipoFiltro, filtroField.getText(), determinarLista(tipoLista), tipoLista));
                dado = filtroField.getText();
            }
        }else {
            setMedia(filtrarMedia(tipoFiltro, filtroField.getText(), determinarLista(tipoLista), tipoLista));
            dado = filtroField.getText();
        }
    }


    /**
     * Verifica se mídia foi selecionada. Se sim chama a tela de Edição
     * @param actionEvent
     */
    public void editaMedia(ActionEvent actionEvent) {
        // deixa editar a média
        if(mediaSelecionada != null ) {
            // Chama uma nova janela para edição
            DiarioCultural.carregarTelaDeEdicao(mediaSelecionada);
        } else {
            showAlert("Selecione uma media", "Voce precisa selecionar uma media para editar", Alert.AlertType.ERROR);
        }
    }

    /**
     * Deleta mídia
     */
    public void deletaMedia() {
        if(mediaSelecionada != null) {
            switch (mediaSelecionada) {
                case Book book -> {
                    memoryManagement.deleteMedia(book);
                    setMedia(memoryManagement.getBooks());
                }
                case Film film -> {
                    memoryManagement.deleteMedia(film);
                    setMedia(memoryManagement.getFilms());
                }
                case Serie serie -> {
                    memoryManagement.deleteMedia(serie);
                    setMedia(memoryManagement.getSeries());
                }
                default -> {
                }
            }
            detalhesBoxContainer.setVisible(false);
            showAlert("Media excluida", mediaSelecionada.getTitle() + " excluido com sucesso", Alert.AlertType.CONFIRMATION);
            mediaSelecionada=null;
        } else {
            showAlert("Media inválida", "Selecione uma media primeiro", Alert.AlertType.ERROR);
        }
        memoryManagementController.salvarArquivos();
    }
}
