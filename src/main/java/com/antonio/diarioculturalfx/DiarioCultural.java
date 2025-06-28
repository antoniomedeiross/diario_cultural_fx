package com.antonio.diarioculturalfx;

import com.antonio.diarioculturalfx.repository.MemoryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import static com.antonio.diarioculturalfx.util.Util.deixaTamanhoDaSceneIgual;

public class DiarioCultural extends Application {
    public static MemoryManagement memoryManagement = new MemoryManagement("/com/antonio/diarioculturalfx/db/dataBase.json");
    private static Stage stageTroca;
    private static Scene bemVindoScene;
    private static Scene menuPrincipalScene;
    private static Scene cadastroScene;
    private static Scene cadastroLivroScene;
    private static Scene cadastroFilmeScene;
    private static Scene cadastroSerieScene;
    private static Scene avaliacaoScene;
    private static Scene avaliacaoLivroScene;
    private static Scene avaliacaoFilmeScene;
    private static Scene avaliacaoSerieScene;
    private static Scene buscaScene;
    private static Scene listaScene;


    // metodo start do javaFx
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/antonio/diarioculturalfx/icons/diarioCultural.png")))); // icone do diário
        stageTroca = stage;
        carregarScenes();
        carregaCss();
        stage.setTitle("Bem Vindo!");
        stage.setScene(bemVindoScene);
        stage.show();
    }


    // Main
    public static void main(String[] args) {
        launch();
    }


    /**
     * Metodo que carrega as scenes do app
     */
    private void carregarScenes() throws IOException {
        FXMLLoader bemVindoLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/hello-view.fxml"));
        bemVindoScene = new Scene(bemVindoLoader.load(), 1280, 720);

        FXMLLoader menuPrincipalLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menu-principal.fxml"));
        menuPrincipalScene = new Scene(menuPrincipalLoader.load(), 1280, 720);

        FXMLLoader cadastroLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_cadastro/cadastro.fxml"));
        cadastroScene = new Scene(cadastroLoader.load(), 1280, 720);

        FXMLLoader cadastroLivroLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_cadastro/cadastro-livro.fxml"));
        cadastroLivroScene = new Scene(cadastroLivroLoader.load(), 1280, 720);

        FXMLLoader cadastroFilmeLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_cadastro/cadastro-filme.fxml"));
        cadastroFilmeScene = new Scene(cadastroFilmeLoader.load(), 1280, 720);

        FXMLLoader cadastroSerieLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_cadastro/cadastro-serie.fxml"));
        cadastroSerieScene = new Scene(cadastroSerieLoader.load(), 1280, 720);

        FXMLLoader avaliacaoLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_avaliacao/avaliacao.fxml"));
        avaliacaoScene = new Scene(avaliacaoLoader.load(), 1280, 720);

        FXMLLoader avaliacaoLivroLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_avaliacao/avaliar-livro.fxml"));
        avaliacaoLivroScene = new Scene(avaliacaoLivroLoader.load(), 1280, 720);

        FXMLLoader avaliacaoFilmeLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_avaliacao/avaliar-filme.fxml"));
        avaliacaoFilmeScene = new Scene(avaliacaoFilmeLoader.load(), 1280, 720);

        FXMLLoader avaliacaoSerieLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_avaliacao/avaliar-serie.fxml"));
        avaliacaoSerieScene = new Scene(avaliacaoSerieLoader.load(), 1280, 720);

        FXMLLoader buscaLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_busca/busca.fxml"));
        buscaScene = new Scene(buscaLoader.load(), 1280, 720);

        FXMLLoader listarLoader = new FXMLLoader(DiarioCultural.class.getResource("/com/antonio/diarioculturalfx/view/menus_listagem/listar.fxml"));
        listaScene = new Scene(listarLoader.load(), 1280, 720);


    }


    /**
     *  Metodo que carrega o Style CSS nas scenes
     */
    private void carregaCss() {
        String cssPath = "/com/antonio/diarioculturalfx/styles/global.css";
        URL cssUrl = DiarioCultural.class.getResource(cssPath);
        if (cssUrl != null) {
            addCssNaScene(bemVindoScene, cssUrl);
            addCssNaScene(menuPrincipalScene, cssUrl);
            addCssNaScene(cadastroScene, cssUrl);
            addCssNaScene(cadastroLivroScene, cssUrl);
            addCssNaScene(cadastroFilmeScene, cssUrl);
            addCssNaScene(cadastroSerieScene, cssUrl);
            addCssNaScene(avaliacaoScene, cssUrl);
            addCssNaScene(avaliacaoLivroScene, cssUrl);
            addCssNaScene(avaliacaoFilmeScene, cssUrl);
            addCssNaScene(avaliacaoSerieScene, cssUrl);
            addCssNaScene(buscaScene, cssUrl);
            addCssNaScene(listaScene, cssUrl);
            System.out.println("CSS carregado com sucesso: " + cssUrl.toExternalForm());
        } else {
            System.err.println("ERRO: Não foi possível encontrar o arquivo CSS: " + cssPath);
            System.err.println("Verifique se o arquivo está em: src/main/resources" + cssPath);
        }
    }
    // carregar css
    private void addCssNaScene(Scene cena, URL url) {
        cena.getStylesheets().add(url.toExternalForm());
    }


    /**
     * Metodo que troca as Scenes do app
     * @param click scene destino
     */
    public static void trocarScene(String click) {
        deixaTamanhoDaSceneIgual(stageTroca);
        // swich menu principal
        switch (click){
            case "Entrar":
                stageTroca.setTitle("Menu Principal!");
                stageTroca.getIcons().clear();
                stageTroca.getIcons().add(new Image(Objects.requireNonNull(DiarioCultural.class.getResourceAsStream("/com/antonio/diarioculturalfx/icons/diarioCultural.png"))));
                stageTroca.setScene(menuPrincipalScene);
                break;
            case "Cadastro":
                stageTroca.setTitle("Cadastro de Mídias");
                stageTroca.getIcons().clear();
                stageTroca.getIcons().add(new Image(Objects.requireNonNull(DiarioCultural.class.getResourceAsStream("/com/antonio/diarioculturalfx/icons/cadastro.png"))));
                stageTroca.setScene(cadastroScene);
                break;
            case "Cadastro-livro":
                stageTroca.setScene(cadastroLivroScene);
                break;
            case "Cadastro-filme":
                stageTroca.setScene(cadastroFilmeScene);
                break;
            case "Cadastro-serie":
                stageTroca.setScene(cadastroSerieScene);
                break;
            case "Avaliar":
                stageTroca.getIcons().clear();
                stageTroca.getIcons().add(new Image(Objects.requireNonNull(DiarioCultural.class.getResourceAsStream("/com/antonio/diarioculturalfx/icons/avaliacao.png"))));
                stageTroca.setScene(avaliacaoScene);
                break;
            case "Avaliar-livro":
                stageTroca.setScene(avaliacaoLivroScene);
                break;
            case "Avaliar-filme":
                stageTroca.setScene(avaliacaoFilmeScene);
                break;
            case "Avaliar-serie":
                stageTroca.setScene(avaliacaoSerieScene);
                break;
            case "Buscar":
                stageTroca.getIcons().clear();
                stageTroca.getIcons().add(new Image(Objects.requireNonNull(DiarioCultural.class.getResourceAsStream("/com/antonio/diarioculturalfx/icons/busca.png"))));
                stageTroca.setScene(buscaScene);
                break;
            case "Listar":
                stageTroca.getIcons().clear();
                stageTroca.getIcons().add(new Image(Objects.requireNonNull(DiarioCultural.class.getResourceAsStream("/com/antonio/diarioculturalfx/icons/lista.png"))));
                stageTroca.setScene(listaScene);
                break;
            case "Sair":
                stageTroca.close();
        }
    }
}