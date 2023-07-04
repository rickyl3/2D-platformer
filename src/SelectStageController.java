import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SelectStageController extends StartController implements Initializable{
    protected Stage stage;
    protected Scene scene;
    protected Node node;

    protected Parent firstStageRoot;
    protected Scene firstStageScene;
    private Timeline firstStageHover;
    private int firstStageFrame = 2;

    private Parent startScreenRoot;
    private Scene startScreenScene;
    
    protected boolean stagePressed;

    @FXML
    protected AnchorPane anchorPaneSelect;
    @FXML
    private ImageView firstStage;
    @FXML
    private ImageView secondStage;
    @FXML
    private ImageView gif;
    @FXML
    private ImageView backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (readString("saver.txt").equals("false")) {
            System.out.println("yo");
        }
        backButton.setImage(new Image("assets/SelectStage/Back.png"));
        gif.setImage(new Image("assets/Backgrounds/startBG.gif"));
        secondStage.setImage(new Image("assets/Backgrounds/starfall-gif-46.gif"));
        firstStageHover = new Timeline(new KeyFrame(Duration.millis(175), e -> {
            if (firstStageFrame == 1) {
                firstStage.setImage(new Image("assets/SelectStage/firstStageHover1.png"));
                firstStageFrame++;
            } else if (firstStageFrame == 2) {
                firstStage.setImage(new Image("assets/SelectStage/firstStageHover2.png"));
                firstStageFrame++;
            } else if (firstStageFrame == 3) {
                firstStage.setImage(new Image("assets/SelectStage/firstStageHover3.png"));
                firstStageFrame++;
                
            } else {
                firstStage.setImage(new Image("assets/SelectStage/firstStageHover4.png"));
                firstStageFrame = 1;
            }
        }));
        firstStageHover.setCycleCount(Animation.INDEFINITE);
        firstStage.setImage(new Image("assets/SelectStage/firstStageIdle.png"));
        try {
            firstStageRoot = FXMLLoader.load(getClass().getResource("FirstStage.fxml"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        firstStage.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                firstStage.setImage(new Image("assets/SelectStage/firstStageHover1.png"));
                anchorPaneSelect.setCursor(Cursor.HAND);
                firstStageHover.play();
            }
        });
        firstStage.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorPaneSelect.setCursor(Cursor.DEFAULT);
                firstStageHover.stop();
                firstStageFrame = 1;
                firstStage.setImage(new Image("assets/SelectStage/firstStageIdle.png"));
            }
        });
        firstStage.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // firstStageScene = new Scene(firstStageRoot);
                // firstStageRoot.requestFocus();
                // node = (Node) event.getSource();
                // stage = (Stage) node.getScene().getWindow();
                // scene = firstStage.getScene();
                // stage.setScene(firstStageScene);
                fadeOutFirstStage();
            }
        });
        secondStage.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorPaneSelect.setCursor(Cursor.HAND);
            }
        });
        secondStage.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorPaneSelect.setCursor(Cursor.DEFAULT);
            }
        });
        backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                backButton.setImage(new Image("assets/SelectStage/BackHover.png"));
                anchorPaneSelect.setCursor(Cursor.HAND);
            }
        });
        backButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                backButton.setImage(new Image("assets/SelectStage/Back.png"));
                anchorPaneSelect.setCursor(Cursor.DEFAULT);
            }
        });
        backButton.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                fadeOut();
            }
        });
    }
    private void fadeOut() {
        try {
            startScreenRoot = FXMLLoader.load(getClass().getResource("Start.fxml"));
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        FadeTransition transition = new FadeTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(anchorPaneSelect);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.setOnFinished((ActionEvent event) -> 
        {
            
            stage = (Stage) firstStage.getScene().getWindow();
            startScreenScene = new Scene(startScreenRoot);
            startScreenRoot.requestFocus();
            stage.setScene(startScreenScene);
        });
        transition.play();
    }
    private void fadeOutFirstStage() {
        FadeTransition transition = new FadeTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(anchorPaneSelect);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.setOnFinished((ActionEvent event) -> 
        {
            stage = (Stage) firstStage.getScene().getWindow();
            firstStageScene = new Scene(firstStageRoot);
            firstStageRoot.requestFocus();
            stage.setScene(firstStageScene);
        });
        transition.play();
    }
    public AnchorPane getAnchorPane() {
        return anchorPaneSelect;
    }
    public static String readString(String file) {
        String finished = "";
        try {
            Scanner s = new Scanner(new File(file));
            while (s.hasNextLine()) {
                finished += s.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        return finished;
    }
    public boolean stagePressedMethod() {
        return stagePressed;
    }
}
