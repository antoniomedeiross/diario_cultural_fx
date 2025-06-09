package com.antonio.diarioculturalfx;

import com.antonio.diarioculturalfx.repository.MemoryManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    public static MemoryManagement memoryManagement = new MemoryManagement("/com/antonio/diarioculturalfx/db/dataBase.json");
    private static Stage stageTroca;
    private static Scene bemVindoScene;
    private static Scene menuPrincipalScene;
    private static Scene cadastroScene;
    private static Scene avaliacaoScene;
    private static Scene buscaScene;

    // método start do javaFx
    @Override
    public void start(Stage stage) throws IOException {
        stageTroca = stage;

        carregarScenes();
        carregaCss();
        stage.setTitle("Hello!");
        stage.setScene(bemVindoScene);
        stage.show();
    }

    // Main
    public static void main(String[] args) {
        launch();
    }

    // Método que carrega as scenes do app
    private void carregarScenes() throws IOException {
        FXMLLoader bemVindoLoader = new FXMLLoader(HelloApplication.class.getResource("/com/antonio/diarioculturalfx/view/hello-view.fxml"));
        bemVindoScene = new Scene(bemVindoLoader.load(), 1280, 720);

        FXMLLoader menuPrincipalLoader = new FXMLLoader(HelloApplication.class.getResource("/com/antonio/diarioculturalfx/view/menu-principal.fxml"));
        menuPrincipalScene = new Scene(menuPrincipalLoader.load(), 1280, 720);

        FXMLLoader cadastroLoader = new FXMLLoader(HelloApplication.class.getResource("/com/antonio/diarioculturalfx/view/cadastro.fxml"));
        cadastroScene = new Scene(cadastroLoader.load(), 1280, 720);
    }
    // Metodo que carrega o Style css nas scenes
    private void carregaCss() throws IOException {
        String cssPath = "/com/antonio/diarioculturalfx/styles/global.css";
        URL cssUrl = HelloApplication.class.getResource(cssPath);
        if (cssUrl != null) {
            bemVindoScene.getStylesheets().add(cssUrl.toExternalForm());
            menuPrincipalScene.getStylesheets().add(cssUrl.toExternalForm());
            cadastroScene.getStylesheets().add(cssUrl.toExternalForm());
            System.out.println("CSS carregado com sucesso: " + cssUrl.toExternalForm());
        } else {
            System.err.println("ERRO: Não foi possível encontrar o arquivo CSS: " + cssPath);
            System.err.println("Verifique se o arquivo está em: src/main/resources" + cssPath);
        }
    }

    // Método que troca as Scenes do app
    public static void trocarScene(String click) {
        deixaTamanhoDaSceneIgual(stageTroca);
        // swich menu principal
        switch (click){
            case "Entrar":
                stageTroca.setTitle("Menu Principal!");
                stageTroca.setScene(menuPrincipalScene);
                break;
            case "Cadastro":
                stageTroca.setTitle("Cadastro de Mídias");
                stageTroca.setScene(cadastroScene);
                break;
            case "Sair":
                stageTroca.close();
        }

    }
    private static void deixaTamanhoDaSceneIgual(Stage stage) {
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


}