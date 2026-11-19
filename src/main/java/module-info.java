module es.cesur.dam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires static lombok;

    opens es.cesur.dam to javafx.fxml;
    opens es.cesur.dam.controller to javafx.fxml;
    opens es.cesur.dam.model to javafx.base, org.hibernate.orm.core;

    exports es.cesur.dam;
}