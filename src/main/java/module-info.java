module com.example.chainmeatproducerandexporter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chainmeatproducerandexporter to javafx.fxml;
    exports com.example.chainmeatproducerandexporter;
}