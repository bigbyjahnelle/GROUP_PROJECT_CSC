package org.example.group_project_csc;

// Adrianna and Jahnelle will work on the front end
// Cobin and Jayden will do the back end

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        // Loginpage swap with FirebaseLoginPage later
        LoginPage loginPage = new LoginPage() {
            @Override
            protected void authenticate(String username, String password, String role) {
                // TODO: replace with real Firebase auth
                onLoginSuccess(username, role);
            }

            @Override
            protected void onLoginSuccess(String username, String role) {
                super.onLoginSuccess(username, role);
                // TODO: navigate to dashboard
                System.out.println("Logged in: " + username + " as " + role);
            }
        };

        Scene scene = new Scene(loginPage.getView(), 320, 300);
        stage.setTitle("Valet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}