package com.antonio.diarioculturalfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    private static Stage stageTroca;
    private static Scene bemVindoScene;
    private static Scene menuPrincipalScene;
    private static Scene cadastroScene;
    private static Scene avaliacaoScene;

    @Override
    public void start(Stage stage) throws IOException {
        stageTroca = stage;

        FXMLLoader bemVindoLoader = new FXMLLoader(HelloApplication.class.getResource("/com/antonio/diarioculturalfx/view/hello-view.fxml"));
        bemVindoScene = new Scene(bemVindoLoader.load(), 1280, 720);

        FXMLLoader menuPrincipalLoader = new FXMLLoader(HelloApplication.class.getResource("/com/antonio/diarioculturalfx/view/menu-principal.fxml"));
        menuPrincipalScene = new Scene(menuPrincipalLoader.load(), 1280, 720);

        String cssPath = "/com/antonio/diarioculturalfx/styles/global.css";
        URL cssUrl = HelloApplication.class.getResource(cssPath);
        if (cssUrl != null) {
            bemVindoScene.getStylesheets().add(cssUrl.toExternalForm());
            menuPrincipalScene.getStylesheets().add(cssUrl.toExternalForm());
            System.out.println("CSS carregado com sucesso: " + cssUrl.toExternalForm());
        } else {
            System.err.println("ERRO: Não foi possível encontrar o arquivo CSS: " + cssPath);
            System.err.println("Verifique se o arquivo está em: src/main/resources" + cssPath);
        }
        stage.setTitle("Hello!");
        stage.setScene(bemVindoScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void trocarScene(String click) {
        if (click.equals("Entrar")) {
            stageTroca.setTitle("Menu Principal!");
            stageTroca.setScene(menuPrincipalScene);
            stageTroca.show();
        }
    }
}