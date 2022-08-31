module com.project.sto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.project.sto to javafx.fxml;
    opens com.project.sto.dao;
    opens com.project.sto.controller;
    opens com.project.sto.domain.workService;
    opens com.project.sto.domain;
    opens com.project.sto.service;
    opens com.project.sto.service.resourcesPath;

    exports com.project.sto;
    exports com.project.sto.controller;
    exports com.project.sto.dao.repository;
    exports com.project.sto.dao.impl;
    exports com.project.sto.domain;
    exports com.project.sto.service;
    exports com.project.sto.service.resourcesPath;

    exports com.project.sto.domain.workService;
    exports com.project.sto.controller.adminMenuController;
    opens com.project.sto.controller.adminMenuController;
    exports com.project.sto.controller.modalWindowController;
    opens com.project.sto.controller.modalWindowController;

}