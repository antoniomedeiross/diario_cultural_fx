package com.antonio.diarioculturalfx.controller;

import com.antonio.diarioculturalfx.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.antonio.diarioculturalfx.DiarioCultural.trocarScene;
import static com.antonio.diarioculturalfx.util.Util.addImgOnButton;
import static com.antonio.diarioculturalfx.util.Util.deixaHboxtVisivel;

public class EditController implements Initializable {

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
    List<String> ondeAssistir = new ArrayList<>();
    List<String> elenco = new ArrayList<>();

    @FXML
    private Button voltarButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addImgOnButton("/com/antonio/diarioculturalfx/icons/voltar.png" ,voltarButton);
    }

    public void setMediaParaEditar(Media mediaSelecionada) {
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
                ondeAssistir.addAll(filmeSelecionado.getWhereWatch());

                ondeAssistirField.setText("esta no List");
                elencoField.setText("esta no List");
            }
            case Serie serieSelecionada -> {
                deixaHboxtVisivel(true, hboxTituloOriginalField, hboxElencoField, hboxOndeAssistirField);

                tituloOriginalField.setText(serieSelecionada.getOriginalTitle());
                elenco.addAll(serieSelecionada.getCast());
                ondeAssistir.addAll(serieSelecionada.getWhereWatch());

                ondeAssistirField.setText("esta no List");
                elencoField.setText("esta no List");
            }
            default -> {
            }
        }
    }

    public void handleVoltarMenu(ActionEvent actionEvent) {
        trocarScene("Entrar");
    }

    public void adicionarNome(ActionEvent actionEvent) {
    }

    public void verListaElenco(ActionEvent actionEvent) {
    }

    public void verListaOndeAssistir(ActionEvent actionEvent) {
    }

    @FXML
    public void editar(ActionEvent actionEvent) {
        media.setTitle(tituloField.getText());
        media.setGender(generoField.getText());
        media.setYearReleased(Integer.parseInt(anoField.getText()));

//          autorField.getText();
//          editoraField.getText();
//          isbnField.getText();
//          PossuiField.getText();
//          DuracaoField.getText();
//          DiretorField.getText();
//          TituloOriginalField.getText();
//          escritorField.getText();
//          OndeAssistirField.getText();
//          elencoField.getText();
//          encerramentoField.getText();
    }
}
