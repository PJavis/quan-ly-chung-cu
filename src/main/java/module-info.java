module quan.ly.chung.cu {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;

    requires javafx.base;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.jfoenix;
    exports org.example;
    exports org.example.ConTroller;
    exports org.example.Model.EntityAll;
    opens org.example.Model.EntityAll to org.hibernate.orm.core, javafx.base;
    opens org.example to javafx.fxml;
    opens org.example.ConTroller to javafx.fxml;
}