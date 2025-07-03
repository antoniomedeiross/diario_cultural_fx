package com.antonio.diarioculturalfx.util;

import com.antonio.diarioculturalfx.DiarioCultural;
import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Season;
import com.antonio.diarioculturalfx.model.Serie;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static final Pattern pattern = Pattern.compile("^[1-9]\\d{3}$");
    public static final LocalDate now = LocalDate.now();
    static String cor;
    public static Image starImage = new Image(Objects.requireNonNull(Util.class
            .getResourceAsStream("/com/antonio/diarioculturalfx/icons/star.png")));
    public static Image starVaziaImage = new Image(Objects.requireNonNull(Util.class
            .getResourceAsStream("/com/antonio/diarioculturalfx/icons/starvazia.png")));

    public static void addImgOnButton(String caminho, Button button) {
        InputStream imgStream = Util.class.getResourceAsStream(caminho);
        if (imgStream != null && button != null) {
            ImageView imgView = new ImageView(new Image(imgStream));
            imgView.setFitWidth(40);
            imgView.setFitHeight(40);
            button.setGraphic(imgView);
        } else {
            System.out.println("Imagem não encontrada!");
        }
    }

    public static void deixaTamanhoDaSceneIgual(Stage stage) {
        double largura = stage.getWidth();
        double altura = stage.getHeight();
        boolean isMaximized = stage.isMaximized();
        boolean isFullScreen = stage.isFullScreen();

        // Restabelece exatamente o estado anterior:
        stage.setWidth(largura);
        stage.setHeight(altura);
        stage.setMaximized(isMaximized);
        stage.setFullScreen(isFullScreen);
    }

    public static void setupHoverEffect(Button botao) { // Copiar para outros controllers
        // efeito quando o mouse entra
        botao.setOnMouseEntered(e -> {
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(150), botao);
            botao.setStyle("-fx-background-color: linear-gradient(to left, #1d8147, #2ECC71); -fx-background-radius: 15; -fx-padding: 15 40; -fx-cursor: hand; -fx-font-weight: bold; -fx-text-fill: white;");
            scaleIn.setToX(1.03);
            scaleIn.setToY(1.03);
            scaleIn.play();

        });
        // quando mouse sai do botao retira efeito
        botao.setOnMouseExited(e -> {
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), botao);
            botao.setStyle("-fx-background-color: linear-gradient(to right, #4A90E2, #60b4ec); -fx-background-radius: 15;-fx-padding: 15 40; -fx-cursor: hand");
            scaleOut.setToX(1.0);
            scaleOut.setToY(1.0);
            scaleOut.play();
        });
    }

    /**
     * Avalia se os campos sao nulos
     * @param fields campos
     */
    public static void validateRequiredFields(String ... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                throw new IllegalArgumentException("Campos obrigatórios não podem ser nulos ou vazios");
            }
        }
    }

    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(DiarioCultural.class.getResourceAsStream("/com/antonio/diarioculturalfx/icons/alerta.png"))));
        // Personalizar o estilo do Alert
        if(type == Alert.AlertType.CONFIRMATION) {
            cor = "-fx-background-color: linear-gradient(to bottom right, #4ae387, #84f573, #4ae387);";
        } else if(type == Alert.AlertType.INFORMATION) {
            cor = "-fx-background-color: linear-gradient(to bottom right, #4a90e2, #67a8f6, #4a90e2);";
        } else if (type == Alert.AlertType.WARNING || type == Alert.AlertType.ERROR) {
            cor = "-fx-background-color: linear-gradient(to bottom right, #fd5656, #ff8989, #fd5656);";

        }
        alert.getDialogPane().setStyle(
                        cor  +
                        "-fx-border-color: #dee2e6; " +
                        "-fx-border-radius: 10; " +
                        "-fx-background-radius: 10;"+
                        "-fx-text-alignment: center;" +
                        "-fx-text-fill: #ffffff;"
        );

        alert.showAndWait();
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void mostrarDetalhes(Book livro, VBox detalhesBox) {
        detalhesBox.getChildren().clear();
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

    public static void mostrarDetalhes(Film filme, VBox detalhesBox) {
        detalhesBox.getChildren().clear();

        StringBuilder atores = new StringBuilder(
                filme.getCast().isEmpty() ? "Nenhum ator cadastrado" : "\n"
        );
        for (String ator : filme.getCast()) {
            atores.append("\t").append(ator).append("\n");
        }

        StringBuilder ondeAssistir = new StringBuilder(
                filme.getWhereWatch().isEmpty() ? "Nenhum lugar cadastrado" : "\n"
        );
        for (String lugar : filme.getWhereWatch()) {
            ondeAssistir.append("\t").append(lugar).append("\n");
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
                new Label("Onde assistir: " + ondeAssistir)
        );
    }

    public static void mostrarDetalhes(Serie serie, VBox detalhesBox) {
        detalhesBox.getChildren().clear();

        StringBuilder atores = new StringBuilder(
                serie.getCast().isEmpty() ? "Nenhum ator cadastrado" : "\n"
        );
        for (String ator : serie.getCast()) {
            atores.append("\t").append(ator).append("\n");
        }

        StringBuilder ondeAssistir = new StringBuilder(
                serie.getWhereWatch().isEmpty() ? "Nenhum lugar cadastrado" : "\n"
        );
        for (String lugar : serie.getWhereWatch()) {
            ondeAssistir.append("\t").append(lugar).append("\n");
        }

        detalhesBox.getChildren().addAll(
                new Label("Título: " + serie.getTitle()),
                new Label("Título Original: " + serie.getOriginalTitle()),
                new Label("Gênero: " + serie.getGender()),
                new Label("Ano de Lançamento: " + serie.getYearReleased()),
                new Label("Ano de Encerramento: " + serie.getYearEnding()),
                new Label("Elenco: " + atores),
                new Label("Onde assistir: " + ondeAssistir)
        );
    }

    public static boolean validarAno(int ano){
        Matcher matcher = pattern.matcher(String.valueOf(ano));
        return matcher.matches();
    }

    public static void deixaHboxtVisivel(boolean visivel, HBox... hBoxes){
        for(HBox hb : hBoxes){
            hb.setVisible(visivel);
            hb.setManaged(visivel);
        }
    }

    public static void abrirDetalhes(Film filme, VBox detalhesBox,HBox avaliacaoBox, VBox detalhesBoxContainer){
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
                new Label("Onde assitir: " + ondeAssistir),
                new Separator(Orientation.valueOf("HORIZONTAL")),
                new Label("Nota: " + filme.getReview().getNote()),
                new Label("Quando Leu: " + filme.getReview().getWhenReadWatch()),
                new Label("Comentário: " + filme.getReview().getComment())
        );

        estrelas(filme.getReview().getNote(), avaliacaoBox);
    }

    public static void abrirDetalhes(Serie serie, VBox detalhesBox,HBox avaliacaoBox, VBox detalhesBoxContainer){
        avaliacaoBox.getChildren().clear();
        detalhesBox.getChildren().clear(); // limpa conteúdo anterior
        detalhesBoxContainer.setVisible(true);
        StringBuilder atores;
        StringBuilder temporadas;
        StringBuilder ondeAssistir;

        if (serie.getCast().isEmpty()){
            atores = new StringBuilder("Nenhum ator cadastrado");
        } else{
            atores = new StringBuilder("\n");
            for(String ator : serie.getCast()){
                atores.append("\t").append(ator).append("\n");
            }
        }
        if (serie.getSeasons().isEmpty()){
            temporadas = new StringBuilder("Nenhuma temporada cadastrada");
        } else{
            temporadas = new StringBuilder("\n");
            for(Season tp : serie.getSeasons()){
                temporadas.append("\t").append(tp.getTitle()).append("\n");
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
                new Label("Onde assitir: " + ondeAssistir),
                new Separator(Orientation.valueOf("HORIZONTAL")),
                new Label("Nota: " + serie.getNote()),
                new Label("Quando Leu: " + serie.getReview().getWhenReadWatch()),
                new Label("Temporadas: " + temporadas)
        );

        estrelas(serie.getNote(), avaliacaoBox);
    }
    public static void abrirDetalhes(Book livro, VBox detalhesBox,HBox avaliacaoBox, VBox detalhesBoxContainer) {
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
                new Label("Disponível: " + (livro.isHaveBook() ? "Sim" : "Não")),
                new Separator(Orientation.valueOf("HORIZONTAL")),
                new Label("Nota: " + livro.getReview().getNote()),
                new Label("Quando Leu: " + livro.getReview().getWhenReadWatch()),
                new Label("Comentário: " + livro.getReview().getComment())
        );


        estrelas(livro.getReview().getNote(), avaliacaoBox);

    }

    private static void estrelas(int stars, HBox starBox) {
        for (int i = 0; i < stars; i++) {
            ImageView star = new ImageView();
            star.setImage(starImage);
            star.setFitWidth(24);
            star.setFitHeight(24);
            starBox.getChildren().add(star);
        }
        for (int i = 0; i < 5-stars; i++) {
            ImageView star = new ImageView();
            star.setImage(starVaziaImage);
            star.setFitWidth(24);
            star.setFitHeight(24);
            starBox.getChildren().add(star);
        }
    }


    public static void listar(ObservableList<String> lista) {
        if (lista.isEmpty()) {
            showAlert("Lista vazia", "Nenhum nome foi adicionado ainda.", Alert.AlertType.INFORMATION);
            return;
        }

        StringBuilder conteudo = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            conteudo.append(i + 1).append(". ").append(lista.get(i)).append("\n");
        }

        showAlert("Lista", conteudo.toString(), Alert.AlertType.INFORMATION);
    }


}
