package org.example.group_project_csc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Abstract login page for the Valet Parking app.
 * Subclass this and implement authenticate() to add real logic.
 *
 * Usage:
 *   new ValetLoginPage().getView();
 */
public abstract class LoginPage {

    protected TextField usernameField;
    protected PasswordField passwordField;
    protected ComboBox<String> roleBox;
    protected Label statusLabel;
    protected Button loginButton;

    /**
     * Returns the fully built login UI.
     * Plug into any Scene or container.
     */
    public VBox getView() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Valet Parking Login");

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Label roleLabel = new Label("Role:");
        roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Attendant", "Manager", "Admin");
        roleBox.setValue("Attendant");

        statusLabel = new Label("");

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> handleLogin());
        passwordField.setOnAction(e -> handleLogin());

        root.getChildren().addAll(
                title,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                roleLabel, roleBox,
                loginButton,
                statusLabel
        );

        return root;
    }

    /**
     * Called when the user clicks Login.
     * Validates input then delegates to authenticate().
     */
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String role     = roleBox.getValue();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        statusLabel.setText("Authenticating...");
        authenticate(username, password, role);
    }

    /**
     * Abstract — implement with real auth logic (e.g. Firebase).
     * Call onLoginSuccess() or onLoginFailure() when done.
     */
    protected abstract void authenticate(String username, String password, String role);

    /**
     * Call this from authenticate() on success.
     */
    protected void onLoginSuccess(String username, String role) {
        statusLabel.setText("Welcome, " + username + "!");
    }

    /**
     * Call this from authenticate() on failure.
     */
    protected void onLoginFailure(String reason) {
        statusLabel.setText("Login failed: " + reason);
        loginButton.setDisable(false);
    }
}