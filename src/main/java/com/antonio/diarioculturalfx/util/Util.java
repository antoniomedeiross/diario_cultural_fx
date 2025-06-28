package com.antonio.diarioculturalfx.util;

import com.antonio.diarioculturalfx.DiarioCultural;
import com.antonio.diarioculturalfx.model.Book;
import com.antonio.diarioculturalfx.model.Film;
import com.antonio.diarioculturalfx.model.Season;
import com.antonio.diarioculturalfx.model.Serie;
import com.antonio.diarioculturalfx.repository.MemoryManagement;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Util {

    public static final Pattern pattern = Pattern.compile("^[1-9]\\d{3}$");
    public static final LocalDate now = LocalDate.now();
    static String cor;

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
            botao.setStyle("-fx-background-color: linear-gradient(to left, #1d8147, #2ECC71); -fx-background-radius: 25; -fx-padding: 15 40; -fx-cursor: hand; -fx-font-weight: bold; -fx-text-fill: white;");
            scaleIn.setToX(1.03);
            scaleIn.setToY(1.03);
            scaleIn.play();

        });
        // quando mouse sai do botao retira efeito
        botao.setOnMouseExited(e -> {
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), botao);
            botao.setStyle("-fx-background-color: linear-gradient(to right, #4A90E2, #60b4ec); -fx-background-radius: 25;-fx-padding: 15 40; -fx-cursor: hand; -fx-font-size: 20");
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
            cor = "-fx-background-color: linear-gradient(to bottom right, #4a90e2, #ffffff, #4a90e2);";
        } else if (type == Alert.AlertType.WARNING || type == Alert.AlertType.ERROR) {
            cor = "-fx-background-color: linear-gradient(to bottom right, #fd5656, #ffffff, #fd5656);";

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

    public static void encheMemoria(MemoryManagement memoryManagement) {
        memoryManagement.addBook(new Book("Dom Casmurro", "Romance", 1899, "Machado de Assis", "Editora Globo", "978-85-260-1813-7", true));
        memoryManagement.addBook(new Book("O Hobbit", "Fantasia", 1937, "J.R.R. Tolkien", "HarperCollins", "978-0-261-10221-7", true));
        memoryManagement.addBook(new Book("1984", "Distopia", 1949, "George Orwell", "Companhia das Letras", "978-85-359-0277-2", true));
        memoryManagement.addBook(new Book("A Revolução dos Bichos", "Sátira", 1945, "George Orwell", "Companhia das Letras", "978-85-359-0276-5", true));
        memoryManagement.addBook(new Book("O Pequeno Príncipe", "Fábula", 1943, "Antoine de Saint-Exupéry", "Agir", "978-85-220-0639-7", false));
        memoryManagement.addBook(new Book("Capitães da Areia", "Romance", 1937, "Jorge Amado", "Companhia das Letras", "978-85-359-0846-0", false));
        memoryManagement.addBook(new Book("It: A Coisa", "Terror", 1986, "Stephen King", "Suma", "978-85-5651-001-8", true));
        memoryManagement.addBook(new Book("O Código Da Vinci", "Suspense", 2003, "Dan Brown", "Arqueiro", "978-85-8041-333-0", true));
        memoryManagement.addBook(new Book("A Menina que Roubava Livros", "Drama", 2005, "Markus Zusak", "Intrínseca", "978-85-98078-45-1", false));
        memoryManagement.addBook(new Book("Orgulho e Preconceito", "Romance", 1813, "Jane Austen", "Penguin", "978-85-431-0199-6", true));

        memoryManagement.addFilm(new Film("Um Sonho de Liberdade", "Drama", 1994, 142, "Frank Darabont", "Stephen King", new ArrayList<>(List.of("Tim Robbins", "Morgan Freeman")), "The Shawshank Redemption", new ArrayList<>(List.of("Netflix"))));
        memoryManagement.addFilm(new Film("O Poderoso Chefão", "Crime", 1972, 175, "Francis Ford Coppola", "Mario Puzo", new ArrayList<>(List.of("Marlon Brando", "Al Pacino")), "The Godfather", new ArrayList<>(List.of("Paramount+", "Amazon Prime"))));
        memoryManagement.addFilm(new Film("A Origem", "Ficção Científica", 2010, 148, "Christopher Nolan", "Christopher Nolan", new ArrayList<>(List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt")), "Inception", new ArrayList<>(List.of("HBO Max"))));
        memoryManagement.addFilm(new Film("Pulp Fiction", "Crime", 1994, 154, "Quentin Tarantino", "Quentin Tarantino", new ArrayList<>(List.of("John Travolta", "Uma Thurman")), "Pulp Fiction", new ArrayList<>(List.of("Star+"))));
        memoryManagement.addFilm(new Film("Matrix", "Ação/Ficção", 1999, 136, "Lana Wachowski", "Wachowski Sisters", new ArrayList<>(List.of("Keanu Reeves", "Carrie-Anne Moss")), "The Matrix", new ArrayList<>(List.of("HBO Max"))));
        memoryManagement.addFilm(new Film("O Senhor dos Anéis: A Sociedade do Anel", "Fantasia", 2001, 178, "Peter Jackson", "J.R.R. Tolkien", new ArrayList<>(List.of("Elijah Wood", "Ian McKellen")), "The Fellowship of the Ring", new ArrayList<>(List.of("HBO Max"))));
        memoryManagement.addFilm(new Film("Interestelar", "Ficção Científica", 2014, 169, "Christopher Nolan", "Jonathan Nolan", new ArrayList<>(List.of("Matthew McConaughey", "Anne Hathaway")), "Interstellar", new ArrayList<>(List.of("Paramount+", "HBO Max"))));
        memoryManagement.addFilm(new Film("Gladiador", "Ação/Drama", 2000, 155, "Ridley Scott", "David Franzoni", new ArrayList<>(List.of("Russell Crowe", "Joaquin Phoenix")), "Gladiator", new ArrayList<>(List.of("Star+"))));
        memoryManagement.addFilm(new Film("Clube da Luta", "Drama", 1999, 139, "David Fincher", "Chuck Palahniuk", new ArrayList<>(List.of("Brad Pitt", "Edward Norton")), "Fight Club", new ArrayList<>(List.of("Star+"))));
        memoryManagement.addFilm(new Film("Titanic", "Romance/Drama", 1997, 195, "James Cameron", "James Cameron", new ArrayList<>(List.of("Leonardo DiCaprio", "Kate Winslet")), "Titanic", new ArrayList<>(List.of("Star+"))));

        Serie s1 = new Serie("Breaking Bad", "Crime/Drama", 2008, 2013, new ArrayList<>(List.of("Bryan Cranston", "Aaron Paul")), "Breaking Bad", new ArrayList<>(List.of("Netflix")));
        s1.getSeasons().add(new Season(2008, "Temporada 1", 7));
        s1.getSeasons().add(new Season(2009, "Temporada 2", 13));
        s1.getSeasons().add(new Season(2010, "Temporada 3", 13));
        s1.getSeasons().add(new Season(2011, "Temporada 4", 13));
        s1.getSeasons().add(new Season(2012, "Temporada 5", 16));
        memoryManagement.addSerie(s1);

        Serie s2 = new Serie("Stranger Things", "Ficção Científica", 2016, 2025, new ArrayList<>(List.of("Millie Bobby Brown", "Finn Wolfhard")), "Stranger Things", new ArrayList<>(List.of("Netflix")));
        s2.getSeasons().add(new Season(2016, "Temporada 1", 8));
        s2.getSeasons().add(new Season(2017, "Temporada 2", 9));
        s2.getSeasons().add(new Season(2019, "Temporada 3", 8));
        s2.getSeasons().add(new Season(2022, "Temporada 4", 9));
        memoryManagement.addSerie(s2);

        Serie s3 = new Serie("Game of Thrones", "Fantasia", 2011, 2019, new ArrayList<>(List.of("Emilia Clarke", "Kit Harington")), "Game of Thrones", new ArrayList<>(List.of("HBO Max")));
        s3.getSeasons().add(new Season(2011, "Temporada 1", 10));
        s3.getSeasons().add(new Season(2012, "Temporada 2", 10));
        s3.getSeasons().add(new Season(2013, "Temporada 3", 10));
        s3.getSeasons().add(new Season(2014, "Temporada 4", 10));
        s3.getSeasons().add(new Season(2015, "Temporada 5", 10));
        s3.getSeasons().add(new Season(2016, "Temporada 6", 10));
        s3.getSeasons().add(new Season(2017, "Temporada 7", 7));
        s3.getSeasons().add(new Season(2019, "Temporada 8", 6));
        memoryManagement.addSerie(s3);

        Serie s4 = new Serie("The Office", "Comédia", 2005, 2013, new ArrayList<>(List.of("Steve Carell", "John Krasinski")), "The Office (US)", new ArrayList<>(List.of("Amazon Prime")));
        s4.getSeasons().add(new Season(2005, "Temporada 1", 6));
        s4.getSeasons().add(new Season(2006, "Temporada 2", 22));
        s4.getSeasons().add(new Season(2007, "Temporada 3", 23));
        s4.getSeasons().add(new Season(2008, "Temporada 4", 19));
        memoryManagement.addSerie(s4);

        Serie s5 = new Serie("Dark", "Mistério/Ficção", 2017, 2020, new ArrayList<>(List.of("Louis Hofmann", "Lisa Vicari")), "Dark", new ArrayList<>(List.of("Netflix")));
        s5.getSeasons().add(new Season(2017, "Temporada 1", 10));
        s5.getSeasons().add(new Season(2019, "Temporada 2", 8));
        s5.getSeasons().add(new Season(2020, "Temporada 3", 8));
        memoryManagement.addSerie(s5);

        Serie s6 = new Serie("Sherlock", "Mistério/Drama", 2010, 2017, new ArrayList<>(List.of("Benedict Cumberbatch", "Martin Freeman")), "Sherlock", new ArrayList<>(List.of("Prime Video")));
        s6.getSeasons().add(new Season(2010, "Temporada 1", 3));
        s6.getSeasons().add(new Season(2012, "Temporada 2", 3));
        s6.getSeasons().add(new Season(2014, "Temporada 3", 3));
        s6.getSeasons().add(new Season(2017, "Temporada 4", 3));
        memoryManagement.addSerie(s6);

        Serie s7 = new Serie("Friends", "Comédia", 1994, 2004, new ArrayList<>(List.of("Jennifer Aniston", "Matthew Perry")), "Friends", new ArrayList<>(List.of("HBO Max")));
        s7.getSeasons().add(new Season(1994, "Temporada 1", 24));
        s7.getSeasons().add(new Season(1995, "Temporada 2", 24));
        s7.getSeasons().add(new Season(1996, "Temporada 3", 25));
        memoryManagement.addSerie(s7);

        Serie s8 = new Serie("The Mandalorian", "Aventura/Ficção", 2019, 2024, new ArrayList<>(List.of("Pedro Pascal")), "The Mandalorian", new ArrayList<>(List.of("Disney+")));
        s8.getSeasons().add(new Season(2019, "Temporada 1", 8));
        s8.getSeasons().add(new Season(2020, "Temporada 2", 8));
        s8.getSeasons().add(new Season(2023, "Temporada 3", 8));
        memoryManagement.addSerie(s8);

        Serie s9 = new Serie("Better Call Saul", "Crime/Drama", 2015, 2022, new ArrayList<>(List.of("Bob Odenkirk", "Rhea Seehorn")), "Better Call Saul", new ArrayList<>(List.of("Netflix")));
        s9.getSeasons().add(new Season(2015, "Temporada 1", 10));
        s9.getSeasons().add(new Season(2016, "Temporada 2", 10));
        s9.getSeasons().add(new Season(2017, "Temporada 3", 10));
        memoryManagement.addSerie(s9);

        Serie s10 = new Serie("Chernobyl", "Drama/Histórico", 2019, 2019, new ArrayList<>(List.of("Jared Harris", "Stellan Skarsgård")), "Chernobyl", new ArrayList<>(List.of("HBO Max")));
        s10.getSeasons().add(new Season(2019, "Temporada única", 5));
        memoryManagement.addSerie(s10);

    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
