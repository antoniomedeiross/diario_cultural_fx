package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.DiarioCultural;
import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Media;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.services.SearchService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.*;

import static com.antonio.diarioculturalfx.DiarioCultural.memoryManagement;
import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;

/**
 * Controlador de Busca
 */
public class SearchController implements Initializable {
    @FXML
    public Button bt_busca;
    @FXML
    public Button voltarButton;
    @FXML
    public ListView<Media> listaResultadoBusca;
    public VBox detalhesBox;
    public ComboBox<String> selecaoMedia;
    public ComboBox<String> selecaoBusca;

    private final Map<String, List<String>> buscaPorMedia = new HashMap<>();
    private final ObservableList<Media> observableMedia = FXCollections.observableArrayList();
    @FXML
    public TextField chaveBusca;
    @FXML
    public VBox vboxSelecaoBusca;
    public VBox containerDetalhesBusca;
    public Button bt_novaBusca;
    public Button bt_editarMedia;
    public Button bt_excluirMedia;
    public HBox avaliacaoBox;
    @FXML
    private VBox vboxResultado;

    SearchService searchService = new SearchService(memoryManagement);

    private Media mediaSelecionada;

    String media;
    String busca;
    String chave;

    /**
     * Método de inicialização
     * @param location local
     * @param resources resource
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(bt_busca != null ) {
            setupHoverEffect(bt_busca);
            setupHoverEffect(bt_editarMedia);
            setupHoverEffect(bt_novaBusca);
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


    /**
     * Volta ao menu
     */
    @FXML
    private void handleVoltarMenu() {
        trocarScene("Entrar");
    }

    /**
     * Busca as mídias
     */
    @FXML
    private void buscar() {
        media = selecaoMedia.getValue();
        busca = selecaoBusca.getValue();
        chave = chaveBusca.getText();

        if (media==null || busca==null|| chave==null || media.isEmpty() || busca.isEmpty() || chave.isEmpty()) {
            showAlert("Busca inválida", "Selecione os parâmentros de busca", Alert.AlertType.ERROR);
        } else {
            selecionaTipoBusca(media, busca, chave);

        }
    }

    /**
     * Seleciona os tipos de busca baseado no inpute do user
     * @param media media
     * @param busca tipo de busca
     * @param chave chave da busca
     */
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

    /**
     * Adiciona as mídias na lista
     */
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
                        case Book book -> abrirDetalhes(book, detalhesBox, avaliacaoBox, containerDetalhesBusca);
                        case Film film -> abrirDetalhes(film, detalhesBox, avaliacaoBox, containerDetalhesBusca);
                        case Serie serie -> abrirDetalhes(serie,  detalhesBox, avaliacaoBox, containerDetalhesBusca);
                        default -> {}
                    }
                }
            });
        }
    }


    /**
     * Reseta os parâmetros da busca
     */
    public void resetaBusca() {
        observableMedia.clear();
        vboxSelecaoBusca.setVisible(true);
        vboxSelecaoBusca.setManaged(true);
        vboxResultado.setVisible(false);
        vboxResultado.setManaged(false);
        mediaSelecionada = null;
        chaveBusca.clear();
        containerDetalhesBusca.setVisible(false);
    }

    /**
     * Edita a mídia selecionada
     * @param actionEvent evento
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
     * Apaga a mídia selecionada
     */
    public void deletaMedia() {
        if(mediaSelecionada != null) {
            switch (mediaSelecionada) {
                case Book book -> memoryManagement.deleteMedia(book);
                case Film film -> memoryManagement.deleteMedia(film);
                case Serie serie -> memoryManagement.deleteMedia(serie);
                default -> {
                }
            }
            selecionaTipoBusca(media,busca,chave);
            showAlert("Media excluida", mediaSelecionada.getTitle() + " excluido com sucesso", Alert.AlertType.CONFIRMATION);
            mediaSelecionada=null;
        } else {
            showAlert("Media inválida", "Selecione uma media primeiro", Alert.AlertType.ERROR);
        }
    }
}
