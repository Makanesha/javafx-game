package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    // Define game choices
    private static final String[] CHOICES = {"Rock", "Paper", "Scissors"};
    private Random random = new Random();

    private Label resultLabel;
    private Label userChoiceLabel;
    private Label computerChoiceLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rock Paper Scissors");

        // Create labels to display user and computer choices, and the result
        userChoiceLabel = new Label("Your choice: ");
        computerChoiceLabel = new Label("Computer's choice: ");
        resultLabel = new Label("Result: ");
        
        // Set font and color for labels
        userChoiceLabel.setFont(Font.font("Arial", 16));
        computerChoiceLabel.setFont(Font.font("Arial", 16));
        resultLabel.setFont(Font.font("Arial", 20));
        resultLabel.setTextFill(Color.BLUE);

        // Create buttons for user choices
        Button rockButton = createButton("Rock");
        Button paperButton = createButton("Paper");
        Button scissorsButton = createButton("Scissors");

        // Button event handlers
        rockButton.setOnAction(e -> playGame("Rock"));
        paperButton.setOnAction(e -> playGame("Paper"));
        scissorsButton.setOnAction(e -> playGame("Scissors"));

        // Arrange buttons in a grid layout
        GridPane buttonLayout = new GridPane();
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setHgap(10);
        buttonLayout.add(rockButton, 0, 0);
        buttonLayout.add(paperButton, 1, 0);
        buttonLayout.add(scissorsButton, 2, 0);

        // Arrange everything in a vertical layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(userChoiceLabel, computerChoiceLabel, resultLabel, buttonLayout);

        // Create and set the scene
        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Create styled buttons
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 18));
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px; -fx-border-radius: 5;"));
        return button;
    }

    // Method to play the game
    private void playGame(String userChoice) {
        // Computer makes a random choice
        String computerChoice = CHOICES[random.nextInt(3)];

        // Display user and computer choices
        userChoiceLabel.setText("Your choice: " + userChoice);
        computerChoiceLabel.setText("Computer's choice: " + computerChoice);

        // Determine and display the result
        String result = determineWinner(userChoice, computerChoice);
        resultLabel.setText("Result: " + result);
    }

    // Method to determine the winner
    private String determineWinner(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        }

        switch (userChoice) {
            case "Rock":
                return (computerChoice.equals("Scissors")) ? "You win!" : "Computer wins!";
            case "Paper":
                return (computerChoice.equals("Rock")) ? "You win!" : "Computer wins!";
            case "Scissors":
                return (computerChoice.equals("Paper")) ? "You win!" : "Computer wins!";
        }

        return "Invalid choice!";
    }
}
