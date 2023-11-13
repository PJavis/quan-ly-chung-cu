module quan.ly.chung.cu {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;
    requires org.controlsfx.controls;
    requires javafx.base;
    exports org.example;
    exports org.example.ConTroller;
    opens org.example.EntityAll to org.hibernate.orm.core;
    opens org.example to javafx.fxml;
    opens org.example.ConTroller to javafx.fxml;
}