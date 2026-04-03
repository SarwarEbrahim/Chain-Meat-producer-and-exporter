module com.example.chainmeatproducerandexporter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chainmeatproducerandexporter to javafx.fxml;
    exports com.example.chainmeatproducerandexporter;
    exports com.example.chainmeatproducerandexporter.EbrahimSarwar;
    opens com.example.chainmeatproducerandexporter.EbrahimSarwar to javafx.fxml;

}