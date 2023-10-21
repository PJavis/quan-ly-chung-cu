module quan.ly.chung.cu {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;
    opens org.example.EntityAll to org.hibernate.orm.core;
    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.ConTroller;
    opens org.example.ConTroller to javafx.fxml;

}