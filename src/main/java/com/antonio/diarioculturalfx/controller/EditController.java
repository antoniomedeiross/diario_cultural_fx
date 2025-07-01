package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.model.*;
import com.antonio.diarioculturalfx.services.EditService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.*;

public class EditController implements Initializable {

    public Button addOndeAssistir;
    public Button addElenco;
    public Button salvar;
    EditService editService = new EditService();

    public TextField tituloField;
    public TextField generoField;
    public TextField anoField;
    public TextField autorField;
    public TextField editoraField;
    public TextField isbnField;
    public CheckBox possuiField;
    public TextField duracaoField;
    public TextField diretorField;
    public TextField tituloOriginalField;
    public TextField escritorField;
    public TextField ondeAssistirField;
    public TextField elencoField;
    public TextField encerramentoField;

    public HBox hboxTituloField;
    public HBox hboxGeneroField;
    public HBox hboxAnoField;
    public HBox hboxAutorField;
    public HBox hboxEditoraField;
    public HBox hboxIsbnField;
    public HBox hboxPossuiField;
    public HBox hboxDuracaoField;
    public HBox hboxDiretorField;
    public HBox hboxTituloOriginalField;
    public HBox hboxEscritorField;
    public HBox hboxOndeAssistirField;
    public HBox hboxElencoField;
    public HBox hboxEncerramentoField;

    Media media;
    List<String> ondeAssistirList = new ArrayList<>();
    List<String> elenco = new ArrayList<>();
    ObservableList<String> elencoObservavel =  FXCollections.observableArrayList();
    ObservableList<String> ondeAssistirObservavel =  FXCollections.observableArrayList();

    @FXML
    private Button voltarButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
        if(salvar != null) {
            setupHoverEffect(salvar);
        }
    }

    public void setMediaParaEditar(Media mediaSelecionada) {
        ondeAssistirObservavel.setAll(ondeAssistirList);

        media = mediaSelecionada;
        tituloField.setText(mediaSelecionada.getTitle());
        generoField.setText(mediaSelecionada.getGender());
        anoField.setText(String.valueOf(mediaSelecionada.getYearReleased()));
        switch (mediaSelecionada) {
            case Book livroSelecionado -> {
                deixaHboxtVisivel(true, hboxAutorField, hboxEditoraField, hboxIsbnField, hboxPossuiField);
                autorField.setText(livroSelecionado.getAuthor());
                editoraField.setText(livroSelecionado.getPublisher());
                isbnField.setText(livroSelecionado.getIsbn());
                possuiField.setSelected(livroSelecionado.isHaveBook());
            }
            case Film filmeSelecionado -> {
                deixaHboxtVisivel(true, hboxDuracaoField, hboxDiretorField, hboxTituloOriginalField,
                        hboxEscritorField, hboxElencoField, hboxOndeAssistirField);

                duracaoField.setText(String.valueOf(filmeSelecionado.getDuration()));
                diretorField.setText(filmeSelecionado.getDirector());
                tituloOriginalField.setText(filmeSelecionado.getOriginalTitle());
                escritorField.setText(filmeSelecionado.getWriter());
                elenco.addAll(filmeSelecionado.getCast());
                ondeAssistirList.addAll(filmeSelecionado.getWhereWatch());

                ondeAssistirField.setPromptText(ondeAssistirList.toString());
                elencoField.setPromptText(elenco.toString());
            }
            case Serie serieSelecionada -> {
                deixaHboxtVisivel(true, hboxTituloOriginalField, hboxElencoField, hboxOndeAssistirField, hboxEncerramentoField);

                tituloOriginalField.setText(serieSelecionada.getOriginalTitle());
                elenco.addAll(serieSelecionada.getCast());
                ondeAssistirList.addAll(serieSelecionada.getWhereWatch());
                encerramentoField.setText(String.valueOf(serieSelecionada.getYearEnding()));
                ondeAssistirField.setPromptText(ondeAssistirList.toString());
                elencoField.setPromptText(elenco.toString());
            }
            default -> {
            }
        }
        elencoObservavel.addAll(elenco);
        ondeAssistirObservavel.addAll(ondeAssistirList);
    }

    public void handleVoltarMenu(ActionEvent actionEvent) {
        trocarScene("Entrar");
    }

    public void adicionarNome(ActionEvent actionEvent) {
        if(actionEvent.getSource()==addElenco) {
            String atores = elencoField.getText().trim();
            if (!atores.isEmpty()) {
                elenco.add(atores);
                elencoField.clear();
            }
            System.out.println("Adicionando novo nome");
            elencoObservavel.addAll(elenco);
        } else if (actionEvent.getSource()==addOndeAssistir) {
            String ondeAssistir = ondeAssistirField.getText().trim();
            if(!ondeAssistir.isEmpty()) {
                ondeAssistirList.add(ondeAssistir);
                ondeAssistirField.clear();
            }
            System.out.println("Adicionando novo nome");
        }
        ondeAssistirObservavel.addAll(ondeAssistirList);
    }

    public void verListaElenco(ActionEvent actionEvent) {
        listar(elencoObservavel);
    }

    public void verListaOndeAssistir(ActionEvent actionEvent) {
        listar(ondeAssistirObservavel);
    }

    @FXML
    public void editar(ActionEvent actionEvent) {
        String  titulo = (tituloField.getText());
        String  genero = (generoField.getText());
        String  ano = (anoField.getText());
        try {
            if (media instanceof Book livro) {
                String autor = (autorField.getText());
                String editora = (editoraField.getText());
                String isbn = (isbnField.getText());
                boolean possui = (possuiField.isSelected());
                editService.editMedia(livro, titulo, genero, Integer.parseInt(ano), autor, editora, isbn, possui);
            } else if (media instanceof Film filme) {
                String duracao = (duracaoField.getText());
                String diretor = (diretorField.getText());
                String tituloOriginal = (tituloOriginalField.getText());
                String escritor = (escritorField.getText());
                editService.editMedia(filme, titulo, genero, Integer.parseInt(ano), Integer.parseInt(duracao), diretor,
                        escritor, (ArrayList<String>) elenco, tituloOriginal, (ArrayList<String>) ondeAssistirList);
            } else if (media instanceof Serie serie) {
                String tituloOriginal = (tituloOriginalField.getText());
                String anoEncerramento = encerramentoField.getText();
                editService.editMedia(serie, titulo, genero, Integer.parseInt(ano), Integer.parseInt(anoEncerramento),
                        (ArrayList<String>) elenco, tituloOriginal, (ArrayList<String>) ondeAssistirList);
            }
            showAlert("Edição concluída", media.getTitle()+" Editada com sucesso", Alert.AlertType.CONFIRMATION);
            media = null;
            trocarScene("Entrar");
        } catch (IllegalArgumentException e) {
            showAlert("Edição inválida", "Campos inválidos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
