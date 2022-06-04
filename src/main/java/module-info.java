module com.dl.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dl.calculator to javafx.fxml;
    exports com.dl.calculator;
}