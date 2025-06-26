package com.antonio.diarioculturalfx.controller;


import com.antonio.diarioculturalfx.model.Book;
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

    public List<Book> listaBooks = new ArrayList<>();
    public BorderPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaBooks.add(new Book("Dom Casmurro", "drama", 2004, "Machado de Assis", "Editora X", "978...", true));
        for (int i = 1; i <= 100; i++) {
            listaBooks.add(new Book("Livro " + i, "gênero", 2020, "Autor", "Editora", "ISBN", true));
        }

        setPessoas(listaBooks);

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
    private ListView<Book> listViewMidias;
    @FXML
    private TabPane tabPanePrincipal;

    private final ObservableList<Book> livrosObservable = FXCollections.observableArrayList();

    public void setPessoas(List<Book> listaDePessoas) {
        livrosObservable.setAll(listaDePessoas);
        listViewMidias.setItems(livrosObservable);

        listViewMidias.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Book pessoa, boolean empty) {
                super.updateItem(pessoa, empty);

                if (empty || pessoa == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label nomeLabel = new Label(pessoa.getTitle());
                    nomeLabel.setStyle("-fx-text-fill: white;");
                    Button verMaisButton = new Button("Ver mais");
                    verMaisButton.getStyleClass().add("botao-ver-mais");

                    verMaisButton.setOnAction(e -> abrirDetalhes(pessoa));

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
                new Label("Ano: " + livro.getYearReleased()),
                new Label("Autor: " + livro.getAuthor()),
                new Label("Editora: " + livro.getPublisher()),
                new Label("ISBN: " + livro.getIsbn()),
                new Label("Disponível: " + (livro.isHaveBook() ? "Sim" : "Não"))
        );
    }


}
