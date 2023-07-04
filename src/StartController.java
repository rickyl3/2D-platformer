import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartController implements Initializable{

    // @FXML
    // private ChoiceBox<String> selectScene;

    // private String[] scenes = {"main", "play scene"};

    private Stage stage;
    protected Parent selectStageRoot;
    protected Scene selectStageScene;
    protected ArrayList<String> skins;
    private int loaded = 0;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Rectangle background;
    @FXML
    private ImageView gif;
    @FXML
    private ImageView playButton;
    @FXML
    private ImageView skinsImage;
    private int skinsImageFrame;
    @FXML
    private ImageView exitButton;
    @FXML
    private Button skinLeft;
    @FXML
    private Button skinRight;
    protected int selectedSkin = 0;
    protected ArrayList<String> availableSkins = new ArrayList<String>(Arrays.asList("Player", "Rabbit", "Grub", "AmongUs"));
    protected Timeline playerShowcase;
    protected KeyFrame keyframe;

    private Image player;
    private Image playerRotated;
    private PixelReader playerReader;
    private PixelReader playerReaderRotated;
    private WritableImage playerStill0;
    private WritableImage playerStill1;
    private WritableImage playerStill2;
    private WritableImage playerStill3;
    private WritableImage playerStill4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            selectStageRoot = FXMLLoader.load(getClass().getResource("SelectStage.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        gif.setImage(new Image("assets/Backgrounds/startBG.gif"));
        playButton.setImage(new Image("assets/StartScreen/play.PNG"));
        skinsImage.setImage(new Image("assets/StartScreen/Skins.png"));
        exitButton.setImage(new Image("assets/StartScreen/Exit.png"));
        // selectStageRoot.translateYProperty().set(selectStageScene.getHeight());

        player = new Image("assets/Player/" + availableSkins.get(selectedSkin) + ".png");
        playerRotated = new Image("assets/Player/" + availableSkins.get(selectedSkin) + "Rotated" + ".png");
        playerReader = player.getPixelReader();
        playerReaderRotated = playerRotated.getPixelReader();
        playerStill0 = flip(new WritableImage(playerReaderRotated, 4033, 258, 61, 130));
        playerStill1 = flip(new WritableImage(playerReaderRotated, 2265, 597, 61, 130));
        playerStill2 = flip(new WritableImage(playerReaderRotated, 2330, 695, 61, 130));
        playerStill3 = new WritableImage(playerReader, 3214, 2395, 61, 130);
        playerStill4 = new WritableImage(playerReader, 3149, 2395, 61, 130);

        playerShowcase = new Timeline();
        keyframe = new KeyFrame(Duration.millis(100), e -> {
            if (skinsImageFrame == 0) {
                skinsImage.setImage(playerStill0);
                skinsImageFrame++;
            } else if (skinsImageFrame == 1) {
                skinsImage.setImage(playerStill1);
                skinsImageFrame++;
            } else if (skinsImageFrame == 2) {
                skinsImage.setImage(playerStill2);
                skinsImageFrame++;
            } else if (skinsImageFrame == 3) {
                skinsImage.setImage(playerStill3);
                skinsImageFrame++;
            } else {
                skinsImage.setImage(playerStill4);
                skinsImageFrame = 0;
            }
        });
        playerShowcase.getKeyFrames().add(keyframe);
        playerShowcase.setCycleCount(Animation.INDEFINITE);
        playerShowcase.play();

        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorPane.setCursor(Cursor.HAND);
                Node node = (Node) event.getSource();
                stage = (Stage) node.getScene().getWindow();
                playButton.setImage(new Image("assets/StartScreen/playHover.png"));
            }
        });
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorPane.setCursor(Cursor.DEFAULT);
                playButton.setImage(new Image("assets/StartScreen/play.PNG"));
            }
        });
        playButton.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fadeOut();
            }
        });
        

        skinLeft.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
                if (selectedSkin > 0) {
                    selectedSkin--;
                    player = new Image("assets/Player/" + availableSkins.get(selectedSkin) + ".png");
                    playerRotated = new Image("assets/Player/" + availableSkins.get(selectedSkin) + "Rotated" + ".png");
                    playerReader = player.getPixelReader();
                    playerReaderRotated = playerRotated.getPixelReader();
                    playerStill0 = flip(new WritableImage(playerReaderRotated, 4033, 258, 61, 130));
                    playerStill1 = flip(new WritableImage(playerReaderRotated, 2265, 597, 61, 130));
                    playerStill2 = flip(new WritableImage(playerReaderRotated, 2330, 695, 61, 130));
                    playerStill3 = new WritableImage(playerReader, 3214, 2395, 61, 130);
                    playerStill4 = new WritableImage(playerReader, 3149, 2395, 61, 130);
                } 
           } 
        });
        skinRight.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (selectedSkin < availableSkins.size() - 1) {
                    selectedSkin++;
                    player = new Image("assets/Player/" + availableSkins.get(selectedSkin) + ".png");
                    playerRotated = new Image("assets/Player/" + availableSkins.get(selectedSkin) + "Rotated" + ".png");
                    playerReader = player.getPixelReader();
                    playerReaderRotated = playerRotated.getPixelReader();
                    playerStill0 = flip(new WritableImage(playerReaderRotated, 4033, 258, 61, 130));
                    playerStill1 = flip(new WritableImage(playerReaderRotated, 2265, 597, 61, 130));
                    playerStill2 = flip(new WritableImage(playerReaderRotated, 2330, 695, 61, 130));
                    playerStill3 = new WritableImage(playerReader, 3214, 2395, 61, 130);
                    playerStill4 = new WritableImage(playerReader, 3149, 2395, 61, 130);
                }
            }
        });
        // skinsButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
        //     @Override
        //     public void handle(MouseEvent event) {
        //         anchorPane.setCursor(Cursor.HAND);
        //         Node node = (Node) event.getSource();
        //         stage = (Stage) node.getScene().getWindow();
        //         skinsButton.setImage(new Image("assets/StartScreen/skinsHover.png"));
        //     }
        // });
        // skinsButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
        //     @Override
        //     public void handle(MouseEvent event) {
        //         anchorPane.setCursor(Cursor.DEFAULT);
        //         skinsButton.setImage(new Image("assets/StartScreen/Skins.PNG"));
        //     }
        // });
        // skinsButton.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
        //     @Override
        //     public void handle(MouseEvent event) {

        //     }
        // });

        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorPane.setCursor(Cursor.HAND);
                Node node = (Node) event.getSource();
                stage = (Stage) node.getScene().getWindow();
                exitButton.setImage(new Image("assets/StartScreen/exitHover.png"));
            }
        });
        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                anchorPane.setCursor(Cursor.DEFAULT);
                exitButton.setImage(new Image("assets/StartScreen/Exit.png"));
            }
        });
        exitButton.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public WritableImage flip(WritableImage img) {
        BufferedImage imgHolder = SwingFXUtils.fromFXImage(img, null);
        BufferedImage resultHolder = SwingFXUtils.fromFXImage(img, null);
        for (int x = imgHolder.getWidth()-1; x > 0; x--) {
            for (int y = 0; y < imgHolder.getHeight(); y++) {
                resultHolder.setRGB(imgHolder.getWidth()-x, y, imgHolder.getRGB(x, y));
            }
        }
        WritableImage result = SwingFXUtils.toFXImage(resultHolder, null);
        return result;
    }
    private void fadeOut() {
        FadeTransition transition = new FadeTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(anchorPane);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.setOnFinished((ActionEvent event) -> 
        {
            saveSelectedSkin();
            stage = (Stage) skinLeft.getScene().getWindow();
            selectStageScene = new Scene(selectStageRoot);
            selectStageRoot.requestFocus();
            stage.setScene(selectStageScene);
        });
        transition.play();
    }
    public Parent getRoot() {
        return anchorPane.getParent();
    }
    public void saveSelectedSkin() {
        File file = new File("saver.txt");
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.write(String.valueOf(selectedSkin));
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
