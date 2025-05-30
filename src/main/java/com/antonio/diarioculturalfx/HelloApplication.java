package com.antonio.diarioculturalfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/antonio/diarioculturalfx/view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        String cssPath = "/com/antonio/diarioculturalfx/styles/global.css";
        URL cssUrl = HelloApplication.class.getResource(cssPath);
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
            System.out.println("CSS carregado com sucesso: " + cssUrl.toExternalForm());
        } else {
            System.err.println("ERRO: Não foi possível encontrar o arquivo CSS: " + cssPath);
            System.err.println("Verifique se o arquivo está em: src/main/resources" + cssPath);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}