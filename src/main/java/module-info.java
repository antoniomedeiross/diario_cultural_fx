module com.antonio.diarioculturalfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.google.gson;
    requires com.google.errorprone.annotations;
    requires java.desktop;

    opens com.antonio.diarioculturalfx to javafx.fxml;
    exports com.antonio.diarioculturalfx;
    exports com.antonio.diarioculturalfx.controller;
    exports com.antonio.diarioculturalfx.repository;
    exports com.antonio.diarioculturalfx.model;
    exports com.antonio.diarioculturalfx.services;
    opens com.antonio.diarioculturalfx.controller to javafx.fxml;
    opens com.antonio.diarioculturalfx.model to com.google.gson;
}