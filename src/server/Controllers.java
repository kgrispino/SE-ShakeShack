package server;

import javafx.fxml.FXMLLoader;

public class Controllers {

    private static serverController mainController;

    public static serverController getMainController() {
        return mainController;
    }

    public static void setMainController(serverController mainController) {
        Controllers.mainController = mainController;
    }

    public static void setMainControllerLoader(FXMLLoader mainControllerLoader) {
        Controllers.mainController = mainControllerLoader.getController();
    }
}