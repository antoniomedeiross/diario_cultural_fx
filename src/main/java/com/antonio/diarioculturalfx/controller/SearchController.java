package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.DiarioCultural;
import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.services.SearchService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.*;

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;

public class SearchController implements Initializable {
    @FXML
    public Button bt_busca;
    @FXML
    public Button voltarButton;
    @FXML
    public ListView<Media> listaResultadoBusca;
    @FXML
    public VBox detalhesBusca;
    public ComboBox<String> selecaoMedia;
    public ComboBox<String> selecaoBusca;

    private final Map<String, List<String>> buscaPorMedia = new HashMap<>();
    private final ObservableList<Media> observableMedia = FXCollections.observableArrayList();
    @FXML
    public TextField chaveBusca;
    @FXML
    public VBox vboxSelecaoBusca;
    public VBox containerDetalhesBusca;
    @FXML
    private VBox vboxResultado;

    SearchService searchService = new SearchService(memoryManagement);

    private Media mediaSelecionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_busca != null ) {
            setupHoverEffect(bt_busca);
        }
        buscaPorMedia.put("LIVROS", List.of("TÍTULO", "GÊNERO", "AUTOR", "ANO", "ISBN"));
        buscaPorMedia.put("FILMES", List.of("TÍTULO", "GÊNERO", "DIRETOR", "ATOR", "ANO"));
        buscaPorMedia.put("SÉRIES", List.of("TÍTULO", "GÊNERO", "ATOR", "ANO"));


        if(selecaoMedia != null ) {
            // Listener para mudar o tipos de busca
            selecaoMedia.setOnAction(e -> {
                String mediasSelecionada = selecaoMedia.getValue();
                selecaoBusca.getItems().clear();
                selecaoBusca.getItems().addAll(buscaPorMedia.get(mediasSelecionada));
                selecaoBusca.setDisable(false); // Habilita se estiver desativado
            });
        }

    }


    
    // Voltar menu
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }
    
    @FXML
    private void buscar() {
        String media = selecaoMedia.getValue();
        String busca = selecaoBusca.getValue();
        String chave = chaveBusca.getText();

        if (media==null || busca==null|| chave==null || media.isEmpty() || busca.isEmpty() || chave.isEmpty()) {
            showAlert("Busca inválida", "Selecione os parâmentros de busca", Alert.AlertType.ERROR);
        } else {
            selecionaTipoBusca(media, busca, chave);

        }
    }

    public void selecionaTipoBusca(String media, String busca, String chave) {
        if ("LIVROS".equals(media)) {
            if ("TÍTULO".equals(busca)) {
                observableMedia.setAll(searchService.searchBookTitle(chave));
            } else if ("GÊNERO".equals(busca)) {
                observableMedia.setAll(searchService.searchBookGender(chave));
            } else if ("AUTOR".equals(busca)) {
                observableMedia.setAll(searchService.searchBookAuthor(chave));
            } else if ("ANO".equals(busca)) {
                observableMedia.setAll(searchService.searchBookYearReleased(Integer.parseInt(chave)));
            } else if ("ISBN".equals(busca)) {
                observableMedia.setAll(searchService.searchBookIsbn(chave));
            }
        } else if ("FILMES".equals(media)) {
            if ("TÍTULO".equals(busca)) {
                observableMedia.setAll(searchService.searchFilmTitle(chave));
            } else if ("GÊNERO".equals(busca)) {
                observableMedia.setAll(searchService.searchFilmGender(chave));
            } else if ("DIRETOR".equals(busca)) {
                observableMedia.setAll(searchService.searchFilmDirector(chave));
            } else if ("ATOR".equals(busca)) {
                observableMedia.setAll(searchService.searchFilmActor(chave));
            } else if ("ANO".equals(busca)) {
                observableMedia.setAll(searchService.searchFilmYearReleased(Integer.parseInt(chave)));
            }
        } else if("SÉRIES".equals(media)) {
            if("TÍTULO".equals(busca)) {
                observableMedia.setAll(searchService.searchSerieTitle(chave));
            } else if("GÊNERO".equals(busca)) {
                observableMedia.setAll(searchService.searchSerieGender(chave));
            } else if ("ATOR".equals(busca)) {
                observableMedia.setAll(searchService.searchSerieActor(chave));
            } else if("ANO".equals(busca)) {
                observableMedia.setAll(searchService.searchSerieYearReleased(Integer.parseInt(chave)));
            }
        }
        System.out.println(observableMedia);

        // chama o metodo que carrega a lista
        setMedia();
    }


    public void setMedia() {
        vboxSelecaoBusca.setVisible(false);
        vboxSelecaoBusca.setManaged(false);
        vboxResultado.setVisible(true);
        vboxResultado.setManaged(true);

        if(listaResultadoBusca != null ) {
            listaResultadoBusca.setItems(observableMedia);
            System.out.println(observableMedia+"setMedia");
            listaResultadoBusca.setCellFactory(_ -> new ListCell<>() {
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

            listaResultadoBusca.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    containerDetalhesBusca.setVisible(true);
                    containerDetalhesBusca.setManaged(true);
                    mediaSelecionada = newVal;
                    switch (newVal) {
                        case Book book -> mostrarDetalhes(book, detalhesBusca);
                        case Film film -> mostrarDetalhes(film, detalhesBusca);
                        case Serie serie -> mostrarDetalhes(serie, detalhesBusca);
                        default -> {}
                    }
                }
            });
        }
    }



    public void resetaBusca() {
        vboxSelecaoBusca.setVisible(true);
        vboxSelecaoBusca.setManaged(true);
        vboxResultado.setVisible(false);
        vboxResultado.setManaged(false);
        mediaSelecionada = null;
        chaveBusca.clear();
        containerDetalhesBusca.setVisible(false);
    }


    public void editaMedia(ActionEvent actionEvent) {
        // deixa editar a média
        if(mediaSelecionada != null ) {
            // Chama uma nova janela para edição
            DiarioCultural.carregarTelaDeEdicao(mediaSelecionada);
        }
    }
}
