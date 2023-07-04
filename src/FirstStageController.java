import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.ResourceBundle;

import java.awt.image.*;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FirstStageController extends SelectStageController implements Initializable{

    // @FXML
    // private ChoiceBox<String> selectScene;

    // private String[] scenes = {"main", "play scene"};

    private int sceneCounter = 0;

    private Image HudAll;
    private Image HudAllRotated;
    private Image HudOrb;
    private PixelReader HudAllReader;
    private PixelReader HudAllRotatedReader;
    private PixelReader HudOrbReader;
    @FXML
    private ImageView HudMainImage;
    @FXML
    private ImageView HudOrbImage;
    private double orbPercentage = 0.001;
    private int playerHP = 5;
    private WritableImage playerLife;
    private WritableImage loseLife0;
    private WritableImage loseLife1;
    private WritableImage loseLife2;
    private WritableImage loseLife3;
    private WritableImage loseLife4;
    private WritableImage loseLife5;
    private WritableImage emptyLife;
    @FXML
    private ImageView playerLife1;
    @FXML
    private ImageView playerLife2;
    @FXML
    private ImageView playerLife3;
    @FXML
    private ImageView playerLife4;
    @FXML
    private ImageView playerLife5;
    ImageView[] playerLifeArray = {playerLife1, playerLife2, playerLife3, playerLife4, playerLife5};

    double time = 0.0;
    double velocityY = 2;
    double grav = 2;

    private BooleanProperty upPressed = new SimpleBooleanProperty();
    private BooleanProperty leftPressed = new SimpleBooleanProperty();
    private BooleanProperty downPressed = new SimpleBooleanProperty();
    private BooleanProperty rightPressed = new SimpleBooleanProperty();
    private String lastDirection = "right";
    private BooleanBinding keyPressed = upPressed.or(leftPressed).or(downPressed).or(rightPressed);
    private BooleanProperty jumpPressed = new SimpleBooleanProperty();
    private int jumpCount = 0;
    private double timeJump = 0.935;
    private double velocityYJump = 8;
    private BooleanProperty dashPressed = new SimpleBooleanProperty();
    private BooleanProperty slashPressed = new SimpleBooleanProperty();
    private BooleanProperty gainLifePressed = new SimpleBooleanProperty();
    private int gainLifeDuration = 0;
    private BooleanProperty escPressed = new SimpleBooleanProperty();
    private Timeline gravity;
    private WritableImage playerFall0;
    private WritableImage playerFall1;
    private WritableImage playerFall2;
    private WritableImage playerFall3;
    private WritableImage playerFall4;
    private WritableImage playerFall5;
    private Timeline gravityTimeline;
    private int movementVariable = 4;
    double timeLeftRight = 0.0;
    double velocityX = movementVariable / 2;
    double velocityXHolder = movementVariable / 2;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Ellipse player;


    @FXML
    private ImageView playerAnimation;
    private Image playerAllAnimations;
    private Image playerAllAnimationsRotated;
    private PixelReader playerReader;
    private PixelReader playerReaderRotated;
    private WritableImage playerStill0;
    private WritableImage playerStill1;
    private WritableImage playerStill2;
    private WritableImage playerStill3;
    private WritableImage playerStill4;
    private Timeline playerStillTimeline;
    private int frameCounter = 0;
    private int effectsFrameCounter = 0;
    private Timeline jumpTimeline;
    private Timeline dashTimeline;
    private double dashFrameCounter = 0;
    private Timeline dashCooldownTimeline;
    private int dashCooldown = 0;
    private int dashDistanceTravelled = 0;
    private Timeline playerDashTimeline;
    private WritableImage playerDash0;
    private WritableImage playerDash1;
    private WritableImage playerDash2;
    private WritableImage playerDash3;
    private WritableImage playerDash4;
    private WritableImage playerDash5;
    private WritableImage playerDash6;
    private Timeline playerLeftTimeline;
    private WritableImage playerLeft0;
    private WritableImage playerLeft1;
    private WritableImage playerLeft2;
    private WritableImage playerLeft3;
    private WritableImage playerLeft4;
    private WritableImage playerLeft6;
    private WritableImage playerLeft8;
    private WritableImage playerLeft10;
    private WritableImage playerLeft12;
    private Timeline playerRightTimeline;
    private WritableImage playerRight0;
    private WritableImage playerRight1;
    private WritableImage playerRight2;
    private WritableImage playerRight3;
    private WritableImage playerRight4;
    private WritableImage playerRight6;
    private WritableImage playerRight8;
    private WritableImage playerRight10;
    private WritableImage playerRight12;
    @FXML
    private ImageView upSlashImage0;
    @FXML
    private ImageView upSlashImage1;
    private WritableImage upSlash0;
    private WritableImage upSlash1;
    private WritableImage playerUpSlash0;
    private WritableImage playerUpSlash1;
    private WritableImage playerUpSlash2;
    private WritableImage playerUpSlash3;
    private WritableImage playerUpSlash4;
    @FXML
    private ImageView downSlashImage0;
    @FXML
    private ImageView downSlashImage1;
    private WritableImage downSlash0;
    private WritableImage downSlash1;
    private WritableImage playerDownSlash0;
    private WritableImage playerDownSlash1;
    private WritableImage playerDownSlash2;
    private WritableImage playerDownSlash3;
    private WritableImage playerDownSlash4;
    @FXML
    private ImageView slashImage0;
    @FXML
    private ImageView slashImage1;
    private WritableImage slash0;
    private WritableImage slash1;
    private WritableImage playerSlash0;
    private WritableImage playerSlash1;
    private WritableImage playerSlash2;
    private WritableImage playerSlash3;
    private WritableImage playerSlash4;
    private double slashDirectionDegree;
    @FXML
    private ImageView slashEffectImage;
    private WritableImage slashEffect0;
    private WritableImage slashEffect1;
    private WritableImage slashEffect2;
    private WritableImage slashEffect3;
    private WritableImage slashEffect4;
    private WritableImage slashEffect5;
    private WritableImage slashEffect6;
    private WritableImage slashEffect7;
    private WritableImage slashEffect8;
    private WritableImage slashEffect9;
    private Timeline slashEffectTimeline;
    private int slashEffectFrameCounter = 0;
    private double differenceX;
    private double differenceY;
    private Timeline playerSlashTimeline;
    private Timeline playerSlashCooldownTimeline;
    private double playerSlashCooldown;
    private Image playerHitAnimations;
    private PixelReader playerHitReader;
    private Timeline playerHitEffectTimeline;
    @FXML
    private ImageView playerHitImage0;
    @FXML
    private ImageView playerHitImage1;
    @FXML
    private ImageView playerHitImage2;
    private WritableImage playerHit0;
    private WritableImage playerHit1;
    private WritableImage playerHit2;
    private double playerHitFrameCounter = 0;
    private int shakeX = 0;
    private int shakeY = 0;

    @FXML
    private ImageView bossAnimation;
    private Image bossAllBodyAnimations;
    private PixelReader bossBodyReader;
    private int bossHP = 30;
    private int bossFrameCounter = 1;
    private int bossHitFrames = 1;
    private Timeline bossHitTimeline;
    private Timeline bossIdleTimeline;
    private WritableImage bossIdle1;
    private WritableImage bossIdle2;
    private WritableImage bossIdle3;
    private WritableImage bossIdle4;
    private WritableImage bossDead;
    private Timeline bossMoveLeftRightTimeline;
    private double bossUpSpeed = 5;
    private boolean bossRightDirection = true;
    private boolean bossOppositeDirection = false;
    private boolean bossDownward = false;
    private Timeline playerHitTimeline;
    private Timeline healthLose;
    private int healthLoseFrameCounter = 0;
    private int playerHitTime = 0;
    private Timeline randomProjectileTimeline;
    private Timeline randomProjectileFollowTimeline;
    private Image bossAllProjectile;
    private PixelReader bossProjectileReader;
    private WritableImage bossProjectile;
    @FXML
    private ImageView bossProjectileImageView;
    @FXML
    private Line line;
    private Image bossAllGlowingProjectile;
    private PixelReader bossGlowingProjectileReader;
    private WritableImage bossGlowingProjectile;
    @FXML
    private ImageView bossProjectileMech1;
    @FXML
    private ImageView bossProjectileMech2;
    @FXML
    private ImageView bossProjectileMech3;
    @FXML
    private ImageView bossProjectileMech4;
    @FXML
    private ImageView bossProjectileMech5;
    @FXML
    private ImageView bossProjectileMech6;
    @FXML
    private ImageView bossProjectileMech7;
    @FXML
    private ImageView bossProjectileMech8;
    private Timeline randomBossAttacks;
    private Timeline rainProjectileTimeline;
    private int bossMechFrameCounter = 0;
    private Timeline bossGlowingProjectileDown;
    private int bossFallFrameCounter = 0;
    private boolean moved;

    @FXML
    private Rectangle mapMainLeft;
    @FXML
    private ImageView mapMainLeftImage;

    @FXML
    private Rectangle mapMainMiddle;
    @FXML
    private ImageView mapMainMiddleImage;

    @FXML
    private Rectangle mapMainRight;
    @FXML
    private ImageView mapMainRightImage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // backgroundImage.setImage(new Image("assets/Backgrounds/firstStageBackground.png"));
    
        // try {
        //     selectStageRoot = FXMLLoader.load(getClass().getResource("SelectStage.fxml"));
        // } catch (IOException e2) {
        //     e2.printStackTrace();
        // }
        backgroundImage.setImage(new Image("assets/Backgrounds/firstStageBackground.png"));
        HudAll = new Image("assets/HUD/Hud.png");
        HudAllRotated = new Image("assets/HUD/HudRotated.png");
        HudOrb = new Image("assets/HUD/OrbFull.png");
        HudAllReader = HudAll.getPixelReader();
        HudAllRotatedReader = HudAllRotated.getPixelReader();
        HudOrbReader = HudOrb.getPixelReader();
        HudMainImage.setImage(flip(new WritableImage(HudAllRotatedReader, 1396, 564, 230, 141)));
        HudOrbImage.setImage(new WritableImage(HudOrbReader, 0, 0, 129, 125));
        playerLife = new WritableImage(HudAllRotatedReader, 797, 1972, 54, 70);
        playerLife1.setImage(playerLife);
        playerLife2.setImage(playerLife);
        playerLife3.setImage(playerLife);
        playerLife4.setImage(playerLife);
        playerLife5.setImage(playerLife);
        loseLife0 = new WritableImage(HudAllReader, 1854, 1241, 64, 116);
        loseLife1 = new WritableImage(HudAllReader, 1984, 596, 64, 116);
        loseLife2 = new WritableImage(HudAllReader, 389, 1102, 65, 117);
        loseLife3 = new WritableImage(HudAllReader, 400, 1232, 65, 116);
        loseLife4 = new WritableImage(HudAllReader, 1846, 771, 65, 116);
        loseLife5 = new WritableImage(HudAllReader, 1848, 925, 65, 116);
        emptyLife = new WritableImage(HudAllReader, 432, 1709, 65, 116);

        mapMainLeftImage.setImage(new Image("assets/FirstStageBoss/mapMainLeft.png"));
        mapMainMiddleImage.setImage(new Image("assets/FirstStageBoss/mapMainMiddle.png"));
        mapMainRightImage.setImage(new Image("assets/FirstStageBoss/mapMainLeft.png"));

        player.setVisible(false);
        playerAllAnimations = new Image("assets/Player/" + availableSkins.get(0) + ".png");
        playerAllAnimationsRotated = new Image("assets/Player/" + availableSkins.get(0) + "Rotated" + ".png");
        playerReader = playerAllAnimations.getPixelReader();
        playerReaderRotated = playerAllAnimationsRotated.getPixelReader();

        playerStill0 = flip(new WritableImage(playerReaderRotated, 4033, 258, 61, 130));
        playerStill1 = flip(new WritableImage(playerReaderRotated, 2265, 597, 61, 130));
        playerStill2 = flip(new WritableImage(playerReaderRotated, 2330, 695, 61, 130));
        playerStill3 = new WritableImage(playerReader, 3214, 2395, 61, 130);
        playerStill4 = new WritableImage(playerReader, 3149, 2395, 61, 130);
        playerStillTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            if (lastDirection.equals("left")) {
                if (frameCounter == 0) {
                    playerAnimation.setImage(playerStill0);
                    frameCounter++;
                } else if (frameCounter == 1) {
                    playerAnimation.setImage(playerStill1);
                    frameCounter++;
                } else if (frameCounter == 2) {
                    playerAnimation.setImage(playerStill2);
                    frameCounter++;
                } else if (frameCounter == 3) {
                    playerAnimation.setImage(playerStill3);
                    frameCounter++;
                } else {
                    playerAnimation.setImage(playerStill4);
                    frameCounter = 0;
                }
            } else {
                if (frameCounter == 0) {
                    playerAnimation.setImage(flip(playerStill0));
                    frameCounter++;
                } else if (frameCounter == 1) {
                    playerAnimation.setImage(flip(playerStill1));
                    frameCounter++;
                } else if (frameCounter == 2) {
                    playerAnimation.setImage(flip(playerStill2));
                    frameCounter++;
                } else if (frameCounter == 3) {
                    playerAnimation.setImage(flip(playerStill3));
                    frameCounter++;
                } else {
                    playerAnimation.setImage(flip(playerStill4));
                    frameCounter = 0;
                }
            }
        }));
        playerStillTimeline.setCycleCount(Animation.INDEFINITE);

        playerFall0 = new WritableImage(playerReaderRotated, 2121, 1109, 78, 128);
        playerFall1 = new WritableImage(playerReaderRotated, 3900, 1831, 85, 128);
        playerFall2 = new WritableImage(playerReaderRotated, 2958, 1947, 88, 131);
        playerFall3 = new WritableImage(playerReaderRotated, 3700, 1844, 88, 131);
        playerFall4 = new WritableImage(playerReaderRotated, 3697, 1979, 91, 131);
        playerFall5 = new WritableImage(playerReaderRotated, 3701, 1709, 88, 131);
        gravity = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            double bottomX = player.getLayoutX();
            double bottomY = player.getLayoutY() + player.getRadiusY();

            if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                if (velocityY <= 7) {
                    velocityY += grav * 0.5 * Math.pow(time, 2);
                }
                double newY = player.getLayoutY() + velocityY;
                player.setLayoutY(newY);
                time += 0.005;
                if (player.getLayoutY() > 1200) {
                    player.setLayoutY(200);
                    time = 0;
                    velocityY = 2;
                    grav = 2;
                }
            }
        }));
        gravity.setCycleCount(Animation.INDEFINITE);
        gravityTimeline = new Timeline(new KeyFrame(Duration.millis(25), e -> {
            if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                if (lastDirection.equals("left")) {
                    if (frameCounter == 0) {
                        playerAnimation.setImage(flip(playerFall0));
                        frameCounter++;
                    } else if (frameCounter == 1) {
                        playerAnimation.setImage(flip(playerFall1));
                        frameCounter++;
                    } else if (frameCounter == 2) {
                        playerAnimation.setImage(flip(playerFall2));
                        frameCounter++;
                    } else if (frameCounter == 3) {
                        playerAnimation.setImage(flip(playerFall3));
                        frameCounter++;
                    } else if (frameCounter == 4) {
                        playerAnimation.setImage(flip(playerFall4));
                        frameCounter++;
                    } else {
                        playerAnimation.setImage(flip(playerFall5));
                        frameCounter = 0;
                    }
                } else {
                    if (frameCounter == 0) {
                        playerAnimation.setImage(playerFall0);
                        frameCounter++;
                    } else if (frameCounter == 1) {
                        playerAnimation.setImage(playerFall1);
                        frameCounter++;
                    } else if (frameCounter == 2) {
                        playerAnimation.setImage(playerFall2);
                        frameCounter++;
                    } else if (frameCounter == 3) {
                        playerAnimation.setImage(playerFall3);
                        frameCounter++;
                    } else if (frameCounter == 4) {
                        playerAnimation.setImage(playerFall4);
                        frameCounter++;
                    } else {
                        playerAnimation.setImage(playerFall5);
                        frameCounter = 0;
                    }
                }
            }
        }));
        gravityTimeline.setCycleCount(Animation.INDEFINITE);

        playerLeft0 = new WritableImage(playerReaderRotated, 920, 369, 72, 120);
        playerLeft0 = flip(playerLeft0);
        playerLeft1 = new WritableImage(playerReaderRotated, 238, 248, 77, 111);
        playerLeft1 = flip(playerLeft1);
        playerLeft2 = new WritableImage(playerReader, 2974, 1591, 81, 121);
        playerLeft3 = new WritableImage(playerReaderRotated, 1394, 719, 75, 124);
        playerLeft3 = flip(playerLeft3);
        playerLeft4 = new WritableImage(playerReader, 3409, 1707, 64, 128);
        playerLeft6 = new WritableImage(playerReaderRotated, 3673, 2828, 65, 129);
        playerLeft6 = flip(playerLeft6);
        playerLeft8 = new WritableImage(playerReaderRotated, 2955, 2211, 65, 128);
        playerLeft8 = flip(playerLeft8);
        playerLeft10 = new WritableImage(playerReaderRotated, 3673, 2695, 65, 129);
        playerLeft10 = flip(playerLeft10);
        playerLeft12 = new WritableImage(playerReaderRotated, 2249, 2195, 69, 127);
        playerLeft12 = flip(playerLeft12);
        playerLeftTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            if (player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent())) {
                if (rightPressed.get()) {
                    frameCounter = 0;
                    playerRightTimeline.stop();
                } else if (frameCounter == 0) {
                    playerAnimation.setImage(playerLeft0);
                    frameCounter++;
                } else if (frameCounter == 1) {
                    playerAnimation.setImage(playerLeft1);
                    frameCounter++;
                } else if (frameCounter == 2) {
                    playerAnimation.setImage(playerLeft2);
                    frameCounter++;
                } else if (frameCounter == 3) {
                    playerAnimation.setImage(playerLeft3);
                    frameCounter++;
                } else if (frameCounter == 4) {
                    playerAnimation.setImage(playerLeft4);
                    frameCounter += 2;
                } else if (frameCounter == 6) {
                    playerAnimation.setImage(playerLeft6);
                    frameCounter += 2;
                } else if (frameCounter == 8) {
                    playerAnimation.setImage(playerLeft8);
                    frameCounter += 2;
                } else if (frameCounter == 10) {
                    playerAnimation.setImage(playerLeft10);
                    frameCounter += 2;
                } else if (frameCounter == 12) {
                    playerAnimation.setImage(playerLeft12);
                    frameCounter = 4;
                }
            }
        }));
        playerLeftTimeline.setCycleCount(Animation.INDEFINITE);

        playerRight0 = flip(playerLeft0);
        playerRight1 = flip(playerLeft1);
        playerRight2 = flip(playerLeft2);
        playerRight3 = flip(playerLeft3);
        playerRight4 = flip(playerLeft4);
        playerRight6 = flip(playerLeft6);
        playerRight8 = flip(playerLeft8);
        playerRight10 = flip(playerLeft10);
        playerRight12 = flip(playerLeft12);
        playerRightTimeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            if (player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent())) {
                if (leftPressed.get()) {
                    frameCounter = 0;
                    playerLeftTimeline.stop();
                } else if (frameCounter == 0) {
                    playerAnimation.setImage(playerRight0);
                    frameCounter++;
                } else if (frameCounter == 1) {
                    playerAnimation.setImage(playerRight1);
                    frameCounter++;
                } else if (frameCounter == 2) {
                    playerAnimation.setImage(playerRight2);
                    frameCounter++;
                } else if (frameCounter == 3) {
                    playerAnimation.setImage(playerRight3);
                    frameCounter++;
                } else if (frameCounter == 4) {
                    playerAnimation.setImage(playerRight4);
                    frameCounter += 2;
                } else if (frameCounter == 6) {
                    playerAnimation.setImage(playerRight6);
                    frameCounter += 2;
                } else if (frameCounter == 8) {
                    playerAnimation.setImage(playerRight8);
                    frameCounter += 2;
                } else if (frameCounter == 10) {
                    playerAnimation.setImage(playerRight10);
                    frameCounter += 2;
                } else if (frameCounter == 12) {
                    playerAnimation.setImage(playerRight12);
                    frameCounter = 4;
                }
            }
        }));
        playerRightTimeline.setCycleCount(Animation.INDEFINITE);

        playerDash0 = new WritableImage(playerReader, 2433, 3120, 104, 114);
        playerDash1 = new WritableImage(playerReader, 1976, 2618, 158, 111);
        playerDash2 = new WritableImage(playerReader, 1423, 2610, 187, 114);
        playerDash3 = new WritableImage(playerReader, 2160, 2044, 178, 114);
        playerDash4 = new WritableImage(playerReader, 1105, 2945, 192, 114);
        playerDash5 = new WritableImage(playerReader, 1802, 2615, 170, 113);
        playerDash6 = new WritableImage(playerReader, 3317, 1540, 83, 115);
        playerDashTimeline = new Timeline(new KeyFrame(Duration.millis(35), e -> {
            if (dashFrameCounter == 0) {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(64);
                    playerAnimation.setImage(playerDash0);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (64 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(64);
                    playerAnimation.setImage(flip(playerDash0));
                }
                dashFrameCounter++;
            } else if (dashFrameCounter == 1) {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(98);
                    playerAnimation.setImage(playerDash1);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (98 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(98);
                    playerAnimation.setImage(flip(playerDash1));
                }
                dashFrameCounter++;
            } else if (dashFrameCounter == 2) {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(116);
                    playerAnimation.setImage(playerDash2);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (116 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(116);
                    playerAnimation.setImage(flip(playerDash2));
                }
                dashFrameCounter++;
            } else if (dashFrameCounter == 3) {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(110);
                    playerAnimation.setImage(playerDash3);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (110 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(110);
                    playerAnimation.setImage(flip(playerDash3));
                }
                dashFrameCounter++;
            } else if (dashFrameCounter == 4) {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(119);
                    playerAnimation.setImage(playerDash4);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (119 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(119);
                    playerAnimation.setImage(flip(playerDash4));
                }
                dashFrameCounter++;
            } else if (dashFrameCounter == 5) {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(105);
                    playerAnimation.setImage(playerDash5);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (105 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(105);
                    playerAnimation.setImage(flip(playerDash5));
                }
                dashFrameCounter++;
            } else if (dashFrameCounter == 6) {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(51);
                    playerAnimation.setImage(playerDash6);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (51 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(51);
                    playerAnimation.setImage(flip(playerDash6));
                }
                dashFrameCounter++;
            } else {
                if (lastDirection.equals("left")) {
                    playerAnimation.setFitWidth(60);
                } else {
                    playerAnimation.setLayoutX(playerAnimation.getLayoutX() - (60 - playerAnimation.getFitWidth()));
                    playerAnimation.setFitWidth(60);
                }
                playerDashTimeline.stop();
                dashFrameCounter = 0;
                // playerStillTimeline.play();
                // notOnGround.start();
                // gravity.play();
                // gravityTimeline.play();
            }
        }));
        playerDashTimeline.setCycleCount(Animation.INDEFINITE);

        jumpTimeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            double newY = player.getLayoutY() - velocityYJump;
            player.setLayoutY(newY);
            velocityYJump -= 0.1 * Math.pow(timeJump, 2);
            timeJump -= 0.05;
            if (timeJump <= 0.05) {
                timeJump = 0.935;
                velocityYJump = 8;
                time = 0.0;
                velocityY = 2;
                grav = 2;
                gravity.play();
                notOnGround.start();
                frameCounter = 0;
                jumpTimeline.stop();
                collisionTimerTop.start();
                collisionTimerSide.start();
                jumpControls.start();
            }
        }));
        jumpTimeline.setCycleCount(Animation.INDEFINITE);
        dashTimeline = new Timeline(new KeyFrame(Duration.millis(6), e -> {
            notOnGround.stop();
            gravityTimeline.stop();
            double nextXLeft = player.getLayoutX() - 7;
            double nextXRight = player.getLayoutX() + 7;
            if (lastDirection.equals("left")) {
                accelerateLeft.stop();
                player.setLayoutX(nextXLeft);
                dashDistanceTravelled += 7;
            } else {
                accelerateRight.stop();
                player.setLayoutX(nextXRight);
                dashDistanceTravelled += 7;
            }
            if (dashDistanceTravelled >= 350) {
                dashDistanceTravelled = 0;
                dashTimeline.stop();
                notOnGround.start();
                timeJump = 0.05;
                jumpTimeline.play();
                movementControls.start();
                dashCooldownTimeline.play();
                playerStillTimeline.play();
                gravity.play();
                gravityTimeline.play();
            }
        }));
        dashTimeline.setCycleCount(Animation.INDEFINITE);
        dashCooldownTimeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            dashCooldown += 500;
            if (dashCooldown == 1500) {
                dashCooldown = 0;
                dash.start();
                dashCooldownTimeline.stop();
            }
        }));
        dashCooldownTimeline.setCycleCount(Animation.INDEFINITE);
        upSlash0 = new WritableImage(playerReader, 2647, 1098, 169, 178);
        upSlash1 = flip(new WritableImage(playerReaderRotated, 2941, 3455, 64, 150));
        upSlashImage1.setOpacity(0.7);
        playerUpSlash0 = new WritableImage(playerReader, 2912, 2436, 79, 127);
        playerUpSlash1 = flip(new WritableImage(playerReaderRotated, 2411, 1271, 115, 101));
        playerUpSlash2 = new WritableImage(playerReader, 3983, 282, 84, 99);
        playerUpSlash3 = new WritableImage(playerReader, 3998, 386, 77, 98);
        playerUpSlash4 = flip(new WritableImage(playerReaderRotated, 740, 403, 76, 118));
        downSlash0 = new WritableImage(playerReader, 1193, 2248, 182, 209);
        downSlash1 = new WritableImage(playerReader, 637, 3885, 182, 209);
        playerDownSlash0 = flip(new WritableImage(playerReaderRotated, 319, 348, 70, 124));
        playerDownSlash1 = flip(new WritableImage(playerReaderRotated, 3179, 2010, 122, 116));
        playerDownSlash2 = flip(new WritableImage(playerReaderRotated, 3594, 1732, 102, 118));
        playerDownSlash3 = new WritableImage(playerReader, 3075, 3337, 88, 117);
        playerDownSlash4 = flip(new WritableImage(playerReaderRotated, 3612, 1475, 88, 126));
        slash0 = new WritableImage(playerReader, 665, 2569, 214, 110);
        slash1 = new WritableImage(playerReader, 1735, 2732, 164, 93);
        playerSlash0 = flip(new WritableImage(playerReaderRotated, 2922, 618, 64, 123));
        playerSlash1 = new WritableImage(playerReader, 1973, 3050, 109, 125);
        playerSlash2 = flip(new WritableImage(playerReaderRotated, 35, 169, 67, 126));
        playerSlash3 = flip(new WritableImage(playerReaderRotated, 281, 117, 66, 127));
        playerSlash4 = flip(new WritableImage(playerReaderRotated, 2101, 553, 64, 128));
        playerSlashTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            if ((slashDirectionDegree > 45 && slashDirectionDegree <= 90) || (slashDirectionDegree >= -135 && slashDirectionDegree <= -90)) {
                if (differenceY >= 0) {
                    if (lastDirection.equals("left")) {
                        if (effectsFrameCounter == 0) {
                            gravityTimeline.stop();
                            upSlashImage0.setImage(upSlash0);
                            playerAnimation.setImage(playerUpSlash1);
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 1) {
                            gravityTimeline.stop();
                            upSlashImage1.setImage(upSlash1);
                            playerAnimation.setImage(playerUpSlash2);
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (frameCounter == 3) {
                            gravityTimeline.stop();
                            playerAnimation.setImage(playerUpSlash3);
                            frameCounter++;
                        } else if (effectsFrameCounter == 3) {
                            gravityTimeline.stop();
                            upSlashImage0.setImage(null);
                            playerAnimation.setImage(playerUpSlash4);
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 4) {
                            gravityTimeline.stop();
                            upSlashImage1.setImage(null);
                            effectsFrameCounter = 0;
                            frameCounter = 0;
                            slashPressed.set(false);
                            playerSlashCooldownTimeline.play();
                            playerSlashTimeline.stop();
                            if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                                notOnGround.start();
                            } else {
                                playerStillTimeline.play();
                            }
                        } else {
                            frameCounter++;
                            effectsFrameCounter++;
                        }
                    } else {
                        if (effectsFrameCounter == 0) {
                            gravityTimeline.stop();
                            upSlashImage0.setImage(flip(upSlash0));
                            playerAnimation.setImage(flip(playerUpSlash1));
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 1) {
                            gravityTimeline.stop();
                            upSlashImage1.setImage(flip(upSlash1));
                            playerAnimation.setImage(flip(playerUpSlash2));
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (frameCounter == 3) {
                            gravityTimeline.stop();
                            playerAnimation.setImage(flip(playerUpSlash3));
                            frameCounter++;
                        } else if (effectsFrameCounter == 3) {
                            gravityTimeline.stop();
                            upSlashImage0.setImage(null);
                            playerAnimation.setImage(flip(playerUpSlash4));
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 4) {
                            gravityTimeline.stop();
                            upSlashImage1.setImage(null);
                            effectsFrameCounter = 0;
                            frameCounter = 0;
                            slashPressed.set(false);
                            playerSlashCooldownTimeline.play();
                            playerSlashTimeline.stop();
                            if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                                notOnGround.start();
                            } else {
                                playerStillTimeline.play();
                            }
                        } else {
                            frameCounter++;
                            effectsFrameCounter++;
                        }
                    }
                } else {
                    if (lastDirection.equals("left")) {
                        if (effectsFrameCounter == 0) {
                            gravityTimeline.stop();
                            downSlashImage0.setImage(downSlash0);
                            playerAnimation.setImage(playerDownSlash1);
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 1) {
                            gravityTimeline.stop();
                            downSlashImage1.setImage(downSlash1);
                            playerAnimation.setImage(playerDownSlash2);
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (frameCounter == 3) {
                            gravityTimeline.stop();
                            playerAnimation.setImage(playerDownSlash3);
                            frameCounter++;
                        } else if (effectsFrameCounter == 3) {
                            gravityTimeline.stop();
                            downSlashImage0.setImage(null);
                            playerAnimation.setImage(playerDownSlash4);
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 4) {
                            gravityTimeline.stop();
                            downSlashImage1.setImage(null);
                            effectsFrameCounter = 0;
                            frameCounter = 0;
                            slashPressed.set(false);
                            playerSlashCooldownTimeline.play();
                            playerSlashTimeline.stop();
                            if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                                notOnGround.start();
                            } else {
                                playerStillTimeline.play();
                            }
                        } else {
                            frameCounter++;
                            effectsFrameCounter++;
                        }
                    } else {
                        if (effectsFrameCounter == 0) {
                            gravityTimeline.stop();
                            downSlashImage0.setImage(flip(downSlash0));
                            playerAnimation.setImage(flip(playerDownSlash1));
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 1) {
                            gravityTimeline.stop();
                            downSlashImage1.setImage(flip(downSlash1));
                            playerAnimation.setImage(flip(playerDownSlash2));
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (frameCounter == 3) {
                            gravityTimeline.stop();
                            playerAnimation.setImage(flip(playerDownSlash3));
                            frameCounter++;
                        } else if (effectsFrameCounter == 3) {
                            gravityTimeline.stop();
                            downSlashImage0.setImage(null);
                            playerAnimation.setImage(flip(playerDownSlash4));
                            frameCounter++;
                            effectsFrameCounter++;
                        } else if (effectsFrameCounter == 4) {
                            gravityTimeline.stop();
                            downSlashImage1.setImage(null);
                            effectsFrameCounter = 0;
                            frameCounter = 0;
                            slashPressed.set(false);
                            playerSlashCooldownTimeline.play();
                            playerSlashTimeline.stop();
                            if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                                notOnGround.start();
                            } else {
                                playerStillTimeline.play();
                            }
                        } else {
                            frameCounter++;
                            effectsFrameCounter++;
                        }
                    }
                }
            } else if (slashDirectionDegree >= 0 && slashDirectionDegree < 45) {
                if (effectsFrameCounter == 0) {
                    gravityTimeline.stop();
                    slashImage0.setImage(slash0);
                    playerAnimation.setImage(playerSlash1);
                    frameCounter++;
                    effectsFrameCounter++;
                } else if (effectsFrameCounter == 1) {
                    gravityTimeline.stop();
                    slashImage1.setImage(slash1);
                    playerAnimation.setImage(playerSlash2);
                    frameCounter++;
                    effectsFrameCounter++;
                } else if (frameCounter == 3) {
                    gravityTimeline.stop();
                    playerAnimation.setImage(playerSlash3);
                    frameCounter++;
                } else if (effectsFrameCounter == 3) {
                    gravityTimeline.stop();
                    slashImage0.setImage(null);
                    playerAnimation.setImage(playerSlash4);
                    frameCounter++;
                    effectsFrameCounter++;
                } else if (effectsFrameCounter == 4) {
                    gravityTimeline.stop();
                    slashImage1.setImage(null);
                    effectsFrameCounter = 0;
                    frameCounter = 0;
                    slashPressed.set(false);
                    playerSlashCooldownTimeline.play();
                    playerSlashTimeline.stop();
                    if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                        notOnGround.start();
                    } else {
                        playerStillTimeline.play();
                    }
                } else {
                    frameCounter++;
                    effectsFrameCounter++;
                }
            } else if  (slashDirectionDegree < -135 && slashDirectionDegree >= -180) {
                if (effectsFrameCounter == 0) {
                    gravityTimeline.stop();
                    slashImage0.setImage(flip(slash0));
                    playerAnimation.setImage(flip(playerSlash1));
                    frameCounter++;
                    effectsFrameCounter++;
                } else if (effectsFrameCounter == 1) {
                    gravityTimeline.stop();
                    slashImage1.setImage(flip(slash1));
                    playerAnimation.setImage(flip(playerSlash2));
                    frameCounter++;
                    effectsFrameCounter++;
                } else if (frameCounter == 3) {
                    gravityTimeline.stop();
                    playerAnimation.setImage(flip(playerSlash3));
                    frameCounter++;
                } else if (effectsFrameCounter == 3) {
                    gravityTimeline.stop();
                    slashImage0.setImage(null);
                    playerAnimation.setImage(flip(playerSlash4));
                    frameCounter++;
                    effectsFrameCounter++;
                } else if (effectsFrameCounter == 4) {
                    gravityTimeline.stop();
                    slashImage1.setImage(null);
                    effectsFrameCounter = 0;
                    frameCounter = 0;
                    slashPressed.set(false);
                    playerSlashCooldownTimeline.play();
                    playerSlashTimeline.stop();
                    if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                        notOnGround.start();
                    } else {
                        playerStillTimeline.play();
                    }
                } else {
                    frameCounter++;
                    effectsFrameCounter++;
                }
            }
        }));
        playerSlashTimeline.setCycleCount(Animation.INDEFINITE);
        playerSlashCooldownTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            if (playerSlashCooldown <= 200) {
                playerSlashCooldown += 100;
            } else {
                playerSlashCooldownTimeline.stop();
                playerSlashCooldown = 0;
            }
        }));
        playerSlashCooldownTimeline.setCycleCount(Animation.INDEFINITE);
        slashEffect0 = new WritableImage(playerReader, 689, 3280, 182, 195);
        slashEffect1 = new WritableImage(playerReader, 1617, 639, 247, 276);
        slashEffect2 = new WritableImage(playerReader, 663, 1563, 294, 316);
        slashEffect3 = new WritableImage(playerReader, 1868, 645, 241, 270);
        slashEffect4 = new WritableImage(playerReader, 983, 2203, 206, 241);
        slashEffect5 = new WritableImage(playerReader, 1893, 1026, 197, 212);
        slashEffect6 = new WritableImage(playerReader, 2276, 723, 194, 212);
        slashEffect7 = new WritableImage(playerReader, 828, 2726, 188, 207);
        slashEffect8 = new WritableImage(playerReader, 2094, 1026, 197, 212);
        slashEffect9 = new WritableImage(playerReader, 491, 3235, 194, 212);
        slashEffectTimeline = new Timeline(new KeyFrame(Duration.millis(80), e -> {
            if (slashEffectFrameCounter == 0) {
                slashEffectImage.setImage(slashEffect0);
                slashEffectFrameCounter++;
            } else if (slashEffectFrameCounter == 1) {
                slashEffectImage.setImage(slashEffect1);
                slashEffectFrameCounter++;
            } else if (slashEffectFrameCounter == 2) {
                slashEffectImage.setImage(slashEffect2);
                slashEffectFrameCounter++;
            } else if (slashEffectFrameCounter == 3) {
                slashEffectImage.setImage(slashEffect3);
                slashEffectFrameCounter++;
            } else if (slashEffectFrameCounter == 4) {
                slashEffectImage.setImage(slashEffect4);
                slashEffectFrameCounter++;
            } else if (slashEffectFrameCounter == 5) {
                slashEffectImage.setImage(slashEffect5);
                slashEffectFrameCounter++;
            } else {
                slashEffectImage.setImage(null);
                slashEffectFrameCounter = 0;
                slashEffectTimeline.stop();
            }
        }));
        slashEffectTimeline.setCycleCount(Animation.INDEFINITE);
        anchorPane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (playerSlashCooldownTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                    double mouseX = event.getX();
                    double mouseY = event.getY();
                    double playerX = player.getLayoutX();
                    double playerY = player.getLayoutY();
                    differenceX = playerX - mouseX;
                    differenceY = playerY - mouseY;
                    double hypotenuse = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
                    if (differenceX > 0) {
                        slashDirectionDegree = Math.toDegrees(Math.acos(differenceX / hypotenuse));
                    } else {
                        slashDirectionDegree = Math.toDegrees(Math.acos(differenceX / hypotenuse)) * -1;
                    }
                    playerStillTimeline.stop();
                    slashPressed.set(true);
                }
            }
        });
        playerHitAnimations = new Image("assets/Player/playerHit.png");
        playerHitReader = playerHitAnimations.getPixelReader();
        playerHit0 = new WritableImage(playerHitReader, 3, 11237, 1842, 600);
        playerHit1 = new WritableImage(playerHitReader, 3, 11854, 1840, 600);
        playerHit2 = new WritableImage(playerHitReader, 3, 12474, 1840, 606);
        playerHitEffectTimeline = new Timeline(new KeyFrame(Duration.millis(70), e -> {
            if (playerHitFrameCounter == 0) {
                playerHitImage0.setImage(playerHit0);
                playerHitImage1.setImage(playerHit1);
                playerHitImage2.setImage(playerHit2);
                playerHitFrameCounter++;
            } else if (playerHitFrameCounter == 1) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                shakeStage(5, 14);
                playerHitTimeline.play();
                playerHitFrameCounter++;
            } else if (playerHitFrameCounter == 8) {
                playerHitImage0.setImage(null);
                playerHitFrameCounter++;
            } else if (playerHitFrameCounter == 10) {
                playerHitImage1.setImage(null);
                playerHitFrameCounter++;
            } else if (playerHitFrameCounter == 12) {
                playerHitImage2.setImage(null);
                playerHitEffectTimeline.stop();
                playerHitFrameCounter = 0;
            } else {
                playerHitFrameCounter++;
            }
        }));
        playerHitEffectTimeline.setCycleCount(Animation.INDEFINITE);

        bossAllBodyAnimations = new Image("assets/FirstStageBoss/Character_sheet.png");
        bossBodyReader = bossAllBodyAnimations.getPixelReader();
        bossIdle1 = new WritableImage(bossBodyReader, 25, 24, 48, 46);
        bossIdle2 = new WritableImage(bossBodyReader, 125, 23, 48, 46);
        bossIdle3 = new WritableImage(bossBodyReader, 225, 24, 48, 46);
        bossIdle4 = new WritableImage(bossBodyReader, 325, 24, 48, 46);
        bossDead = new WritableImage(bossBodyReader, 325, 857, 47, 36);
        bossIdleTimeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            if (bossFrameCounter == 1) {
                bossAnimation.setImage(bossIdle1);
                bossFrameCounter++;
            } else if (bossFrameCounter == 2) {
                bossAnimation.setLayoutY(bossAnimation.getLayoutY() - 3);
                bossAnimation.setImage(bossIdle2);
                bossFrameCounter++;
            } else if (bossFrameCounter == 3) {
                bossAnimation.setLayoutY(bossAnimation.getLayoutY() + 3);
                bossAnimation.setImage(bossIdle3);
                bossFrameCounter++;
            } else  {
                bossAnimation.setImage(bossIdle4);
                bossFrameCounter = 1;
            }
        }));
        bossIdleTimeline.setCycleCount(Animation.INDEFINITE);
        bossHitTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            if (bossHitFrames <= 5) {
                bossAnimation.setLayoutY(bossAnimation.getLayoutY() - 15);
                bossHitFrames++;
            } else if (bossHitFrames <= 10) {
                bossAnimation.setLayoutY(bossAnimation.getLayoutY() + 15);
                bossHitFrames++;
            } else {
                bossHitFrames = 0;
                bossHitTimeline.stop();
            }
        }));
        bossHitTimeline.setCycleCount(Animation.INDEFINITE);
        bossMoveLeftRightTimeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            double layoutX = bossAnimation.getLayoutX();
            double layoutY = bossAnimation.getLayoutY();
            if (bossRightDirection) {
                if (bossUpSpeed > 1 && !bossOppositeDirection && !bossDownward) {
                    bossUpSpeed /= 1.09;
                    bossAnimation.setLayoutX(layoutX + 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY + bossUpSpeed * -1);
                    if (bossUpSpeed <= 1) {
                        bossOppositeDirection = true;
                    }
                } else if (bossUpSpeed < 5 && bossOppositeDirection && !bossDownward) {
                    bossUpSpeed *= 1.09;
                    bossAnimation.setLayoutX(layoutX + 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY + bossUpSpeed);
                    if (bossUpSpeed >= 5) {
                        bossDownward = true;
                        bossOppositeDirection = false;
                    }
                } else if (bossUpSpeed > 1 && !bossOppositeDirection && bossDownward) {
                    bossUpSpeed /= 1.09;
                    bossAnimation.setLayoutX(layoutX + 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY + bossUpSpeed);
                    if (bossUpSpeed <= 1) {
                        bossOppositeDirection = true;
                    }
                } else if (bossUpSpeed < 5 && bossOppositeDirection && bossDownward) {
                    bossUpSpeed *= 1.09;
                    bossAnimation.setLayoutX(layoutX + 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY + bossUpSpeed * -1);
                    if (bossUpSpeed >= 5) {
                        bossDownward = false;
                        bossOppositeDirection = false;
                    }
                }
            } else {
                if (bossUpSpeed > 1 && !bossOppositeDirection && !bossDownward) {
                    bossUpSpeed /= 1.09;
                    bossAnimation.setLayoutX(layoutX - 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY - bossUpSpeed * -1);
                    if (bossUpSpeed <= 1) {
                        bossOppositeDirection = true;
                    }
                } else if (bossUpSpeed < 5 && bossOppositeDirection && !bossDownward) {
                    bossUpSpeed *= 1.09;
                    bossAnimation.setLayoutX(layoutX - 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY - bossUpSpeed);
                    if (bossUpSpeed >= 5) {
                        bossDownward = true;
                        bossOppositeDirection = false;
                    }
                } else if (bossUpSpeed > 1 && !bossOppositeDirection && bossDownward) {
                    bossUpSpeed /= 1.09;
                    bossAnimation.setLayoutX(layoutX - 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY - bossUpSpeed);
                    if (bossUpSpeed <= 1) {
                        bossOppositeDirection = true;
                    }
                } else if (bossUpSpeed < 5 && bossOppositeDirection && bossDownward) {
                    bossUpSpeed *= 1.09;
                    bossAnimation.setLayoutX(layoutX - 5 - bossUpSpeed);
                    bossAnimation.setLayoutY(layoutY - bossUpSpeed * -1);
                    if (bossUpSpeed >= 5) {
                        bossDownward = false;
                        bossOppositeDirection = false;
                    }
                }
            }
        }));
        bossMoveLeftRightTimeline.setCycleCount(Animation.INDEFINITE);
        bossAnimation.setLayoutX(350);
        bossAnimation.setLayoutY(400);

        bossAllProjectile = new Image("assets/FirstStageBoss/arm_projectile.png");
        bossProjectileReader = bossAllProjectile.getPixelReader();
        bossProjectile = new WritableImage(bossProjectileReader, 60, 30, 35, 14);
        randomProjectileTimeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            randomProjectileFollowTimeline.stop();
            int randomX = (int) (Math.random() * 1401) + 200;
            int randomY = (int) (Math.random() * 351) + 100;
            bossProjectileImageView.setLayoutX(randomX);
            bossProjectileImageView.setLayoutY(randomY);
            bossProjectileImageView.setImage(bossProjectile);
            randomProjectileFollowTimeline.play();
        }));
        randomProjectileFollowTimeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            double projectileX = bossProjectileImageView.getLayoutX() + bossProjectileImageView.getFitWidth() / 2;
            double projectileY = bossProjectileImageView.getLayoutY() + bossProjectileImageView.getFitHeight() / 2;
            double playerX = player.getLayoutX();
            double playerY = player.getLayoutY();
            double differenceX = playerX - projectileX;
            double differenceY = playerY - projectileY;
            double hypotenuse = Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
            double rate = hypotenuse / 4;
            bossProjectileImageView.setLayoutX(bossProjectileImageView.getLayoutX() + (differenceX / rate) * 2);
            bossProjectileImageView.setLayoutY(bossProjectileImageView.getLayoutY() + (differenceY / rate) * 2);
            if (differenceY > 0) {
                bossProjectileImageView.setRotate(Math.toDegrees(Math.acos(differenceX / hypotenuse)));
            } else {
                bossProjectileImageView.setRotate(Math.toDegrees(Math.acos(differenceX / hypotenuse)) * -1);
            }
        }));
        randomProjectileTimeline.setCycleCount(Animation.INDEFINITE);
        randomProjectileFollowTimeline.setCycleCount(Animation.INDEFINITE);
        playerHitTimeline = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            if (playerHitTime <= 1200) {
                if ((playerHitTime / 300) % 2 == 0) {
                    playerAnimation.setVisible(false);
                }
                if ((playerHitTime / 300) % 2 == 1) {
                    playerAnimation.setVisible(true);
                }
                playerHitTime += 300;
            } else {
                playerHitTime = 0;
                playerAnimation.setVisible(true);
                collisionProjectiles.start();
                playerHitTimeline.stop();
            }
        }));
        playerHitTimeline.setCycleCount(Animation.INDEFINITE);
        
        bossAllGlowingProjectile = new Image("assets/FirstStageBoss/arm_projectile_glowing.png");
        bossGlowingProjectileReader = bossAllGlowingProjectile.getPixelReader();
        bossGlowingProjectile = new WritableImage(bossGlowingProjectileReader, 60, 130, 35, 14);
        randomBossAttacks = new Timeline(new KeyFrame(Duration.seconds(7), e -> {
            bossMechFrameCounter = 0;
            projectileRain();
        }));
        randomBossAttacks.setCycleCount(Animation.INDEFINITE);

        // selectScene.getItems().addAll(scenes);
        // selectScene.setOnAction(arg0 -> {
        //     try {
        //         switchScenes(arg0);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // });

        // escMenu.start();

        movementSetup();
        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                movementControls.start();
            }
            else {
                movementControls.stop();
            }
        }));
        // cursorFollow.start();
        playerDead.start();
        setOrbPercentange.start();

        jumpControls.start();
        playerAnimationFollow.start();
        playerStillTimeline.play();
        dash.start();
        playerSlash.start();
        playerSlashSuccessful.start();
        holdRForHP.start();

        bossIdleTimeline.play();
        // bossMoveLeftRightTimeline.play();
        bossOnEdge.start();
        // randomProjectileTimeline.play();
        // lineFollow.start();
        bossDefeated.start();

        collisionTimerSide.start();
        collisionTimerTop.start();
        collisionLeftRightMap.start();
        notOnGround.start();
        collisionProjectiles.start();

        gravity.play();
    }
    
    //HUD
    AnimationTimer setOrbPercentange = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            double heightHolder = 125 * orbPercentage;
            int height = (int)heightHolder;
            int yLocation = 125 - height;
            HudOrbImage.setLayoutY(HudMainImage.getLayoutY() + HudMainImage.getFitHeight() - height * 0.85);
            if (height == 125) {
                height = 124;
                yLocation = 1;
            }
            if (yLocation == 125) {
                height = 1;
                yLocation = 124;
            }
            HudOrbImage.setImage(new WritableImage(HudOrbReader, 0, yLocation, 129, height));
            // HudOrbImage.setImage(new WritableImage(HudOrbReader, 0, Math.abs(yLocation), 129, Math.abs(height)));
        }
    };
    AnimationTimer escMenu = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (escPressed.get()) {
                GaussianBlur blur = new GaussianBlur();
                blur.setRadius(12);
                anchorPane.setEffect(blur);
                escMenu.stop();
            }
        }
    };

    // WASD controls
    public void movementSetup() {
        anchorPane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                upPressed.set(true);
            }
            if (e.getCode() == KeyCode.A) {
                leftPressed.set(true);
            }
            if (e.getCode() == KeyCode.S) {
                downPressed.set(true);
            }
            if (e.getCode() == KeyCode.D) {
                rightPressed.set(true);
                if (!moved) {
                    try {
                        reloadAssets();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    bossMoveLeftRightTimeline.play();
                    randomBossAttacks.play();
                    randomProjectileTimeline.play();
                    moved = true;
                }
            }
            if (e.getCode() == KeyCode.SPACE) {
                jumpPressed.set(true);
            }
            if (e.getCode() == KeyCode.SHIFT) {
                dashPressed.set(true);
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                escPressed.set(true);
            }
            if (e.getCode() == KeyCode.R) {
                gainLifePressed.set(true);
            }
        });
        anchorPane.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W) {
                upPressed.set(false);
            }
            if (e.getCode() == KeyCode.A) {
                leftPressed.set(false);
                frameCounter = 0;
                playerLeftTimeline.stop();
            }
            if (e.getCode() == KeyCode.S) {
                downPressed.set(false);
                // // fastFall.stop();
                // gravity.start();
                // playerLeftTimeline.stop();
            }
            if (e.getCode() == KeyCode.D) {
                rightPressed.set(false);
                frameCounter = 0;
                playerRightTimeline.stop();
            }
            if (e.getCode() == KeyCode.SPACE) {
                jumpPressed.set(false);
            }
            if (e.getCode() == KeyCode.SHIFT) {
                dashPressed.set(false);
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                escPressed.set(false);
            }
            if (e.getCode() == KeyCode.R) {
                gainLifePressed.set(false);
            }
        });
    }
    AnimationTimer movementControls = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (upPressed.get()) {

            }
            if (downPressed.get()) {
                // gravity.stop();
                // jump.stop();
                // fastFall.start();
                // playerAnimationStillLeft.stop();
                // playerAnimationStillRight.stop();
            }
            if (leftPressed.get() && dashTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                lastDirection = "left";
                accelerateLeft.start();
                playerStillTimeline.stop();
                if (playerSlashTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                    playerLeftTimeline.play();
                }

            }
            if (rightPressed.get() && dashTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                lastDirection = "right";
                accelerateRight.start();
                playerStillTimeline.stop();
                if (playerSlashTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                    playerRightTimeline.play();
                }
            }
        }
    };
    AnimationTimer jumpControls = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (jumpPressed.get() && jumpCount < 3) {
                collisionTimerTop.stop();
                collisionTimerSide.stop();
                jumpCount++;
                gravity.stop();
                jumpTimeline.play();
                playerStillTimeline.stop();
                // jumpControls.stop();
            }
        }
    };
    AnimationTimer dash = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (dashPressed.get() && dashCooldownTimeline.getStatus().equals(Animation.Status.STOPPED)) {                
                jumpTimeline.stop();
                jumpControls.stop();
                notOnGround.stop();
                gravityTimeline.stop();
                gravity.stop();
                movementControls.stop();
                dash.stop();
                accelerateLeft.stop();
                accelerateRight.stop();
                playerStillTimeline.stop();
                dashFrameCounter = 0;
                playerDashTimeline.play();
                dashTimeline.play();
            }
        }
    };
    // AnimationTimer slash = new AnimationTimer() {
    //     @Override
    //     public void handle(long timestamp) {
    //         if (slashPressed.get() && playerSlashTimeline.getStatus().equals(Animation.Status.STOPPED)) {

    //         }
    //     }
    // };
    // AnimationTimer jump = new AnimationTimer() {
    //     double timeJump = 0.935;
    //     double velocityYJump = 8;
    //     @Override
    //     public void handle(long timestamp) {
    //         if (jumpCount < 2) {
    //             double newY = player.getLayoutY() - velocityYJump;
    //             player.setLayoutY(newY);
    //             velocityYJump -= 0.1 * Math.pow(timeJump, 2);
    //             timeJump -= 0.05;
    //             try {
    //                 Thread.sleep(10);
    //             } catch (InterruptedException e) {
    //                 // TODO Auto-generated catch block
    //                 e.printStackTrace();
    //             }
    //             if (timeJump <= 0.05) {
    //                 timeJump = 0.935;
    //                 velocityYJump = 8;
    //                 time = 0.0;
    //                 velocityY = 2;
    //                 grav = 2;
    //                 gravity.start();
    //                 jump.stop();
    //                 jumpControls.start();
    //             }
    //             jumpCount++;
    //         }
    //     }
    // };
    // AnimationTimer gravity = new AnimationTimer() {
    //     @Override
    //     public void handle(long arg0) {
    //         double bottomX = player.getLayoutX();
    //         double bottomY = player.getLayoutY() + player.getRadiusY();

    //         if (velocityY <= 7) {
    //             velocityY += grav * 0.5 * Math.pow(time, 2);
    //         }
    //         double newY = player.getLayoutY() + velocityY;
    //         player.setLayoutY(newY);
    //         time += 0.005;
    //         try {
    //             Thread.sleep(10);
    //         } catch (InterruptedException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //         if (player.getLayoutY() > 1200) {
    //             player.setLayoutY(200);
    //             time = 0;
    //             velocityY = 2;
    //             grav = 2;
    //         }
    //     }
    // };
    // AnimationTimer fastFall = new AnimationTimer() {
    //     @Override
    //     public void handle(long timestamp) {
    //         double bottomX = player.getLayoutX();

    //         double newY = player.getLayoutY() + 5.25 * grav;
    //         if (newY < mapMain.getLayoutY() + mapMain.getHeight() && newY > mapMain.getLayoutY() - player.getRadiusY() && bottomX > mapMain.getLayoutX() + movementVariable && bottomX < mapMain.getLayoutX() + mapMain.getWidth() - movementVariable) {
    //             player.setLayoutY(mapMain.getLayoutY() - player.getRadiusY());
    //             time = 0;
    //             velocityY = 2;
    //         }
    //         else {
    //             player.setLayoutY(newY);
    //         }
    //         try {
    //             Thread.sleep(10);
    //         } catch (InterruptedException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //         if (player.getLayoutY() > 1200) {
    //             player.setLayoutY(200);
    //             time = 0;
    //             velocityY = 2;
    //             grav = 2;
    //         }
    //     }
    // };
    AnimationTimer accelerateLeft = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (rightPressed.get()) {
                velocityXHolder = movementVariable / 2;
                velocityX = movementVariable / 2;
                timeLeftRight = 0.0;
                frameCounter = 0;
                accelerateLeft.stop();
            }
            double newX = player.getLayoutX() - velocityX;
            if (leftPressed.get() == false) {
                if (rightPressed.get()) {
                    velocityXHolder = movementVariable / 2;
                    velocityX = movementVariable / 2;
                    timeLeftRight = 0.0;
                }
                else {
                    if (velocityXHolder >= movementVariable / 2 && velocityX > 0.07 * movementVariable) {
                        velocityX -= 0.07 * movementVariable;
                        newX = player.getLayoutX() - velocityX;
                        player.setLayoutX(newX);
                    }
                    else if (velocityXHolder > 1.0 && velocityX > 0.05 * movementVariable) {
                        velocityX -= 0.05 * movementVariable;
                        newX = player.getLayoutX() - velocityX;
                        player.setLayoutX(newX);
                    }
                    else {
                        timeLeftRight = 0.0;
                        velocityX = movementVariable / 2;
                        velocityXHolder = movementVariable / 2;
                        playerStillTimeline.play();
                        accelerateLeft.stop();
                    }
                }
            }
            else {
                if (velocityX <= movementVariable) {
                    velocityX += movementVariable * Math.pow(timeLeftRight, 2);
                    velocityXHolder += movementVariable * Math.pow(timeLeftRight, 2);
                }
                player.setLayoutX(newX);
                timeLeftRight += 0.005;
            }
        }
    };
    AnimationTimer accelerateRight = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (leftPressed.get()) {
                velocityXHolder = movementVariable / 2;
                velocityX = movementVariable / 2;
                timeLeftRight = 0.0;
                frameCounter = 0;
                accelerateRight.stop();
            }
            double newX = player.getLayoutX() + velocityX;
            if (rightPressed.get() == false) {
                if (rightPressed.get()) {
                    velocityXHolder = movementVariable / 2;
                    velocityX = movementVariable / 2;
                    timeLeftRight = 0.0;
                }
                else {
                    if (velocityXHolder >= movementVariable / 2 && velocityX > 0.07 * movementVariable) {
                        velocityX -= 0.07 * movementVariable;
                        newX = player.getLayoutX() + velocityX;
                        player.setLayoutX(newX);
                    }
                    else if (velocityXHolder > 1.0 && velocityX > 0.05 * movementVariable) {
                        velocityX -= 0.05 * movementVariable;
                        newX = player.getLayoutX() + velocityX;
                        player.setLayoutX(newX);
                    }
                    else {
                        timeLeftRight = 0.0;
                        velocityX = movementVariable / 2;
                        velocityXHolder = movementVariable / 2;
                        playerStillTimeline.play();
                        accelerateRight.stop();
                    }
                }
            }
            else {
                if (velocityX <= movementVariable) {
                    velocityX += movementVariable * Math.pow(timeLeftRight, 2);
                    velocityXHolder += movementVariable * Math.pow(timeLeftRight, 2);
                }
                player.setLayoutX(newX);
                timeLeftRight += 0.005;
            }
        }
    };
    AnimationTimer playerSlash = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (slashPressed.get() && playerSlashTimeline.getStatus().equals(Animation.Status.STOPPED)) {
                effectsFrameCounter = 0;
                frameCounter = 0;
                if (slashDirectionDegree < -45 && slashDirectionDegree >= -135) {
                    if (lastDirection.equals("left")) {
                        playerAnimation.setImage(playerUpSlash0);
                    } else {
                        playerAnimation.setImage(flip(playerUpSlash0));
                    }
                }
                if ((slashDirectionDegree > 45 && slashDirectionDegree <= 90) || (slashDirectionDegree >= -135 && slashDirectionDegree <= -90)) {
                    if (differenceY >= 0) {
                        if (lastDirection.equals("left")) {
                            playerAnimation.setImage(playerUpSlash0);
                        } else {
                            playerAnimation.setImage(flip(playerUpSlash0));
                        }
                    } else {
                        if (lastDirection.equals("left")) {
                            playerAnimation.setImage(playerDownSlash0);
                        } else {
                            playerAnimation.setImage(flip(playerDownSlash0));
                        }
                    }
                } else if (slashDirectionDegree >= 0 && slashDirectionDegree < 45) {
                    playerAnimation.setImage(playerSlash0);
                } else if (slashDirectionDegree < -135 && slashDirectionDegree >= -180) {
                    playerAnimation.setImage(playerSlash0);
                }
                playerStillTimeline.stop();
                playerRightTimeline.stop();
                playerLeftTimeline.stop();
                notOnGround.stop();
                gravityTimeline.stop();
                playerSlashTimeline.play();
                playerSlashSuccessful.start();
            }
        }
    };

    //Animation
    
    // AnimationTimer cursorFollow = new AnimationTimer() {
    //     @Override
    //     public void handle(long timestamp) {
    //         System.out.println(ImageCursor.getBestSize(64, 64));
    //     }
    // };
    // AnimationTimer skinChanged = new AnimationTimer() {
    //     @Override
    //     public void handle(long timestamp) {
    //         if (stagePressed) {
    //             try {
    //                 reloadAssets();
    //             } catch (IOException e) {
    //                 System.out.println("f");
    //             }
    //             stagePressed = false;
    //         }
    //     }
    // };
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
    AnimationTimer playerAnimationFollow = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            playerAnimation.setLayoutX(player.getLayoutX() - player.getRadiusX() - 5);
            playerAnimation.setLayoutY(player.getLayoutY() - player.getRadiusY() - 23);
            playerHitImage0.setLayoutX(playerAnimation.getLayoutX() - playerHitImage0.getFitWidth() / 2);
            playerHitImage0.setLayoutY(playerAnimation.getLayoutY() - 50);
            playerHitImage1.setLayoutX(playerAnimation.getLayoutX() - playerHitImage1.getFitWidth() / 2);
            playerHitImage1.setLayoutY(playerAnimation.getLayoutY() - 50);
            playerHitImage2.setLayoutX(playerAnimation.getLayoutX() - playerHitImage2.getFitWidth() / 2);
            playerHitImage2.setLayoutY(playerAnimation.getLayoutY() - 50);
            playerLife1.setLayoutX(HudMainImage.getLayoutX() + HudMainImage.getFitWidth() - 80);
            playerLife1.setLayoutY(HudMainImage.getLayoutY() + 10);
            playerLife2.setLayoutX(HudMainImage.getLayoutX() + HudMainImage.getFitWidth() - 80 + (1*60));
            playerLife2.setLayoutY(HudMainImage.getLayoutY() + 10);
            playerLife3.setLayoutX(HudMainImage.getLayoutX() + HudMainImage.getFitWidth() - 80 + (2*60));
            playerLife3.setLayoutY(HudMainImage.getLayoutY() + 10);
            playerLife4.setLayoutX(HudMainImage.getLayoutX() + HudMainImage.getFitWidth() - 80 + (3*60));
            playerLife4.setLayoutY(HudMainImage.getLayoutY() + 10);
            playerLife5.setLayoutX(HudMainImage.getLayoutX() + HudMainImage.getFitWidth() - 80 + (4*60));
            playerLife5.setLayoutY(HudMainImage.getLayoutY() + 10);
            if (lastDirection.equals("left")) {
                upSlashImage0.setLayoutX(playerAnimation.getLayoutX() - playerAnimation.getFitWidth() * 0.5);
                upSlashImage0.setLayoutY(playerAnimation.getLayoutY() - playerAnimation.getFitHeight());
                upSlashImage1.setLayoutX(playerAnimation.getLayoutX() + playerAnimation.getFitWidth() * 0.7);
                upSlashImage1.setLayoutY(playerAnimation.getLayoutY() - 50);
                downSlashImage0.setLayoutX(playerAnimation.getLayoutX() - playerAnimation.getFitWidth() * 0.5);
                downSlashImage0.setLayoutY(playerAnimation.getLayoutY() + playerAnimation.getFitHeight() * 0.6);
                downSlashImage1.setLayoutX(playerAnimation.getLayoutX() + playerAnimation.getFitWidth() * 0.45);
                downSlashImage1.setLayoutY(playerAnimation.getLayoutY() + 63);
            } else {
                upSlashImage0.setLayoutX(playerAnimation.getLayoutX() - playerAnimation.getFitWidth() * 0.5);
                upSlashImage0.setLayoutY(playerAnimation.getLayoutY() - playerAnimation.getFitHeight());
                upSlashImage1.setLayoutX(playerAnimation.getLayoutX() - playerAnimation.getFitWidth() * 0.5 + 2);
                upSlashImage1.setLayoutY(playerAnimation.getLayoutY() - 50);
                downSlashImage0.setLayoutX(playerAnimation.getLayoutX() - playerAnimation.getFitWidth() * 0.5);
                downSlashImage0.setLayoutY(playerAnimation.getLayoutY() + playerAnimation.getFitHeight() * 0.6);
                downSlashImage1.setLayoutX(playerAnimation.getLayoutX() - playerAnimation.getFitWidth() * 0.3 + 2);
                downSlashImage1.setLayoutY(playerAnimation.getLayoutY() + 63);
            }
            if (slashDirectionDegree >= 0 && slashDirectionDegree < 45) {
                slashImage0.setLayoutX(playerAnimation.getLayoutX() + playerAnimation.getFitWidth() / 2 - slashImage0.getFitWidth());
                slashImage0.setLayoutY(playerAnimation.getLayoutY() + 12);
                slashImage1.setLayoutX(playerAnimation.getLayoutX() - playerAnimation.getFitWidth() * 1.5);
                slashImage1.setLayoutY(playerAnimation.getLayoutY() + 31);
            } else {
                slashImage0.setLayoutX(playerAnimation.getLayoutX() + slashImage0.getFitWidth() / 2 - slashImage0.getFitWidth() * 0.3 + 5);
                slashImage0.setLayoutY(playerAnimation.getLayoutY() + 12);
                slashImage1.setLayoutX(playerAnimation.getLayoutX() + playerAnimation.getFitWidth() * 0.8 + 1);
                slashImage1.setLayoutY(playerAnimation.getLayoutY() + 31);
            }
        }
    };
    AnimationTimer playerSlashSuccessful = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (playerSlashTimeline.getStatus().equals(Animation.Status.RUNNING)) {
                if ((slashDirectionDegree > 45 && slashDirectionDegree <= 90) || (slashDirectionDegree >= -135 && slashDirectionDegree <= -90)) {
                    if (differenceY >= 0) {
                        if (upSlashImage0.getBoundsInParent().intersects(bossProjectileImageView.getBoundsInParent())) {
                            double bossProjectileX = bossProjectileImageView.getLayoutX() + bossProjectileImageView.getFitWidth() / 2;
                            double bossProjectileY = bossProjectileImageView.getLayoutY() + bossProjectileImageView.getFitHeight() / 2;
                            double playerX = player.getLayoutX();
                            double playerY = player.getLayoutY();
                            double differenceX2 = bossProjectileX - playerX;
                            double differenceY2 = bossProjectileY - playerY;
                            double hypotenuse = Math.sqrt(Math.pow(differenceX2, 2) + Math.pow(differenceY2, 2));
                            double rate = hypotenuse / (Math.sqrt(Math.pow(bossProjectileImageView.getFitHeight() / 2, 2) + Math.pow(bossProjectileImageView.getFitWidth() / 2, 2)));
                            if (differenceX2 > 0) {
                                slashEffectImage.setLayoutX(bossProjectileX - differenceX2 / rate - 100);
                                slashEffectImage.setLayoutY(bossProjectileY - (differenceY2 / rate) * -1);
                            } else {
                                slashEffectImage.setLayoutX(bossProjectileX + differenceX2 / rate);
                                slashEffectImage.setLayoutY(bossProjectileY + differenceY2 / rate);
                            }
                            System.out.println(bossProjectileImageView.getRotate());
                            slashEffectFrameCounter = 0;
                            slashEffectTimeline.play();
                            bossProjectileImageView.setLayoutX(-100);
                            bossProjectileImageView.setLayoutY(-100);
                            randomProjectileFollowTimeline.stop();
                            shakeStage(2, 6);
                            if (orbPercentage <= 0.9) {
                                orbPercentage += 0.1;
                            } else {
                                orbPercentage += 1 - orbPercentage;
                            }
                        }
                        if (upSlashImage0.getBoundsInParent().intersects(bossAnimation.getBoundsInParent())) {
                            bossHP--;
                            if (bossRightDirection) {
                                slashEffectImage.setLayoutX(bossAnimation.getLayoutX() + bossAnimation.getFitWidth() / 2);
                            } else {
                                slashEffectImage.setLayoutX(bossAnimation.getLayoutX() + bossAnimation.getFitWidth() / 2 - 200);
                            }
                            slashEffectImage.setLayoutY(bossAnimation.getLayoutY());
                            slashEffectTimeline.play();
                            bossHitTimeline.play();
                            playerSlashSuccessful.stop();
                            if (orbPercentage <= 0.9) {
                                orbPercentage += 0.1;
                            } else {
                                orbPercentage += 1 - orbPercentage;
                            }
                        }
                    } else {
                        if (downSlashImage0.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || downSlashImage0.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || downSlashImage0.getBoundsInParent().intersects(mapMainRight.getBoundsInParent())) {
                            player.setLayoutY(player.getLayoutY() - 7);
                        }
                    }
                } else if (slashDirectionDegree >= 0 && slashDirectionDegree < 45) {
                    if (slashImage0.getBoundsInParent().intersects(bossProjectileImageView.getBoundsInParent())) {
                        double bossProjectileX = bossProjectileImageView.getLayoutX() + bossProjectileImageView.getFitWidth() / 2;
                        double bossProjectileY = bossProjectileImageView.getLayoutY() + bossProjectileImageView.getFitHeight() / 2;
                        double playerX = player.getLayoutX();
                        double playerY = player.getLayoutY();
                        double differenceX2 = bossProjectileX - playerX;
                        double differenceY2 = bossProjectileY - playerY;
                        double hypotenuse = Math.sqrt(Math.pow(differenceX2, 2) + Math.pow(differenceY2, 2));
                        double rate = hypotenuse / (Math.sqrt(Math.pow(bossProjectileImageView.getFitHeight() / 2, 2) + Math.pow(bossProjectileImageView.getFitWidth() / 2, 2)));
                        slashEffectImage.setLayoutX(bossProjectileX + differenceX2 / rate);
                        slashEffectImage.setLayoutY(bossProjectileY - 100);
                        slashEffectTimeline.play();
                        bossProjectileImageView.setLayoutX(-100);
                        bossProjectileImageView.setLayoutY(-100);
                        randomProjectileFollowTimeline.stop();
                    }
                    if (slashImage0.getLayoutX() < 0) {
                        player.setLayoutX(player.getLayoutX() + 3);
                    }
                } else if (slashDirectionDegree < -135 && slashDirectionDegree >= -180) {
                    if (slashImage0.getLayoutX() + slashImage0.getFitWidth() > 1600) {
                        player.setLayoutX(player.getLayoutX() - 3);
                    }
                    if (slashImage0.getBoundsInParent().intersects(bossProjectileImageView.getBoundsInParent())) {
                        double bossProjectileX = bossProjectileImageView.getLayoutX() + bossProjectileImageView.getFitWidth() / 2;
                        double bossProjectileY = bossProjectileImageView.getLayoutY() + bossProjectileImageView.getFitHeight() / 2;
                        double playerX = player.getLayoutX();
                        double playerY = player.getLayoutY();
                        double differenceX2 = bossProjectileX - playerX;
                        double differenceY2 = bossProjectileY - playerY;
                        double hypotenuse = Math.sqrt(Math.pow(differenceX2, 2) + Math.pow(differenceY2, 2));
                        double rate = hypotenuse / (Math.sqrt(Math.pow(bossProjectileImageView.getFitHeight() / 2, 2) + Math.pow(bossProjectileImageView.getFitWidth() / 2, 2)));
                        slashEffectImage.setLayoutX(bossProjectileX - differenceX2 / rate);
                        slashEffectImage.setLayoutY(bossProjectileY - 100);
                        slashEffectTimeline.play();
                        bossProjectileImageView.setLayoutX(-100);
                        bossProjectileImageView.setLayoutY(-100);
                        randomProjectileFollowTimeline.stop();
                    }
                }
            }
        }
    };


    //Collision
    AnimationTimer collisionTimerSide = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            checkCollisionSide(player, mapMainMiddle);
        }
    };
    AnimationTimer collisionTimerTop = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            checkCollisionTop(player, mapMainMiddle);
            checkCollisionTop(player, mapMainRight);
            checkCollisionTop(player, mapMainLeft);
        }
    };
    AnimationTimer collisionLeftRightMap = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            double bottomX = player.getLayoutX();
            if (bottomX <= player.getRadiusX()) {
                //Dash stop
                movementControls.start();
                gravity.play();
                if (dashTimeline.getStatus().equals(Animation.Status.RUNNING)) {
                    dashDistanceTravelled = 0;
                    dashTimeline.stop();
                    dashCooldownTimeline.play();
                }

                //Accelerate stop
                player.setLayoutX(0 + player.getRadiusX());
                velocityXHolder = movementVariable / 2;
                velocityX = movementVariable / 2;
                timeLeftRight = 0.0;
            } else if (bottomX >= 1600 - player.getRadiusX()) {
                //Dash stop
                movementControls.start();
                gravity.play();
                if (dashTimeline.getStatus().equals(Animation.Status.RUNNING)) {
                    dashDistanceTravelled = 0;
                    dashTimeline.stop();
                    dashCooldownTimeline.play();
                }

                //Accelerate stop
                player.setLayoutX(1600 - player.getRadiusX());
                velocityXHolder = movementVariable / 2;
                velocityX = movementVariable / 2;
                timeLeftRight = 0.0;
            }
        }
    };
    AnimationTimer notOnGround = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (!(player.getBoundsInParent().intersects(mapMainLeft.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainMiddle.getBoundsInParent()) || player.getBoundsInParent().intersects(mapMainRight.getBoundsInParent()))) {
                gravityTimeline.play();
            } else {
                gravityTimeline.stop();
            }
        }
    };
    AnimationTimer collisionProjectiles = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (player.getBoundsInParent().intersects(bossProjectileImageView.getBoundsInParent())) {
                loseHP();
                bossProjectileImageView.setLayoutX(-100);
                bossProjectileImageView.setLayoutY(-100);
                bossProjectileImageView.setImage(null);
                collisionProjectiles.stop();
                playerHitFrameCounter = 0;
                playerHitEffectTimeline.play();
            } else if (player.getBoundsInParent().intersects(bossAnimation.getBoundsInParent())) {
                loseHP();
                collisionProjectiles.stop();
                playerHitEffectTimeline.play();
            }
        }
    };
    AnimationTimer playerDead = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (playerHP == 0 && healthLose.getStatus().equals(Animation.Status.STOPPED)) {
                firstStageRoot = null;
                firstStageScene = null;
                fadeOut();
                holdRForHP.stop();
                // scene = new Scene(selectStageRoot);
                // stage = (Stage) playerAnimation.getScene().getWindow();
                // stage.setScene(scene);
                bossProjectileImageView.setLayoutX(-100);
                bossProjectileImageView.setLayoutY(-100);
                collisionProjectiles.stop();
                bossMoveLeftRightTimeline.stop();
                randomBossAttacks.stop();
                randomProjectileTimeline.stop();
                randomProjectileFollowTimeline.stop();
                playerHP = 5;
                playerDead.stop();
            }
        }
    };
    private void fadeOut() {
        FadeTransition transition = new FadeTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(anchorPane);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.setOnFinished((ActionEvent event) -> 
        {
            try {
                selectStageRoot = FXMLLoader.load(getClass().getResource("SelectStage.fxml"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            stage = (Stage) playerAnimation.getScene().getWindow();
            selectStageScene = new Scene(selectStageRoot);
            stage.setScene(selectStageScene);
            selectStageRoot.requestFocus();
        });
        transition.play();
    }
    public void loseHP() {
        if (playerHP == 5) {
            playerLife5.setFitHeight(72);
            healthLose = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (healthLoseFrameCounter == 0) {
                    playerLife5.setImage(loseLife0);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 1) {
                    playerLife5.setImage(loseLife1);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 2) {
                    playerLife5.setImage(loseLife2);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 3) {
                    playerLife5.setImage(loseLife3);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 4) {
                    playerLife5.setImage(loseLife4);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 5) {
                    playerLife5.setImage(loseLife5);
                    healthLoseFrameCounter++;
                } else {
                    playerLife5.setImage(emptyLife);
                    healthLoseFrameCounter = 0;
                    healthLose.stop();
                }
            }));
        } else if (playerHP == 4) {
            playerLife4.setFitHeight(72);
            healthLose = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (healthLoseFrameCounter == 0) {
                    playerLife4.setImage(loseLife0);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 1) {
                    playerLife4.setImage(loseLife1);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 2) {
                    playerLife4.setImage(loseLife2);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 3) {
                    playerLife4.setImage(loseLife3);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 4) {
                    playerLife4.setImage(loseLife4);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 5) {
                    playerLife4.setImage(loseLife5);
                    healthLoseFrameCounter++;
                } else {
                    playerLife4.setImage(emptyLife);
                    healthLoseFrameCounter = 0;
                    healthLose.stop();
                }
            }));
        } else if (playerHP == 3) {
            playerLife3.setFitHeight(72);
            healthLose = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (healthLoseFrameCounter == 0) {
                    playerLife3.setImage(loseLife0);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 1) {
                    playerLife3.setImage(loseLife1);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 2) {
                    playerLife3.setImage(loseLife2);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 3) {
                    playerLife3.setImage(loseLife3);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 4) {
                    playerLife3.setImage(loseLife4);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 5) {
                    playerLife3.setImage(loseLife5);
                    healthLoseFrameCounter++;
                } else {
                    playerLife3.setImage(emptyLife);
                    healthLoseFrameCounter = 0;
                    healthLose.stop();
                }
            }));
        } else if (playerHP == 2) {
            playerLife2.setFitHeight(72);
            healthLose = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (healthLoseFrameCounter == 0) {
                    playerLife2.setImage(loseLife0);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 1) {
                    playerLife2.setImage(loseLife1);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 2) {
                    playerLife2.setImage(loseLife2);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 3) {
                    playerLife2.setImage(loseLife3);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 4) {
                    playerLife2.setImage(loseLife4);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 5) {
                    playerLife2.setImage(loseLife5);
                    healthLoseFrameCounter++;
                } else {
                    playerLife2.setImage(emptyLife);
                    healthLoseFrameCounter = 0;
                    healthLose.stop();
                }
            }));
        } else {
            playerLife1.setFitHeight(72);
            healthLose = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (healthLoseFrameCounter == 0) {
                    playerLife1.setImage(loseLife0);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 1) {
                    playerLife1.setImage(loseLife1);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 2) {
                    playerLife1.setImage(loseLife2);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 3) {
                    playerLife1.setImage(loseLife3);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 4) {
                    playerLife1.setImage(loseLife4);
                    healthLoseFrameCounter++;
                } else if (healthLoseFrameCounter == 5) {
                    playerLife1.setImage(loseLife5);
                    healthLoseFrameCounter++;
                } else {
                    playerLife1.setImage(emptyLife);
                    healthLoseFrameCounter = 0;
                    healthLose.stop();
                }
            }));
        }
        healthLose.setCycleCount(Animation.INDEFINITE);
        healthLose.play();
        playerHP--;
    }
    AnimationTimer holdRForHP = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (gainLifePressed.get() && !(leftPressed.get()) && !(rightPressed.get()) && !(jumpPressed.get()) && !(slashPressed.get())) {
                gainLifeDuration++;
                System.out.println(gainLifeDuration);
            } else {
                gainLifeDuration = 0;
            }
            if (gainLifeDuration == 100 && orbPercentage > 0.33 && playerHP < 5) {
                slashEffectImage.setLayoutX(playerAnimation.getLayoutX() - 50);
                slashEffectImage.setLayoutY(playerAnimation.getLayoutY() - 50);
                slashEffectTimeline.play();
                orbPercentage -= 0.33;
                gainHP();
                gainLifeDuration = 0;
            }
        }
    };
    public void gainHP() {
        if (playerHP < 5) {
            playerHP++;
        }
        if (playerHP == 5) {
            playerLife5.setImage(playerLife);
        } else if (playerHP == 4) {
            playerLife4.setImage(playerLife);
        } else if (playerHP == 3) {
            playerLife3.setImage(playerLife);
        } else if (playerHP == 2) {
            playerLife2.setImage(playerLife);
        } 
    }
    public void checkCollisionSide(Ellipse player, Rectangle map) {
        double middleY = player.getLayoutY();
        double bottomX = player.getLayoutX();
        double bottomY = player.getLayoutY() + player.getRadiusY();

        if (player.getBoundsInParent().intersects(map.getBoundsInParent()) && middleY >= map.getLayoutY() && middleY <= map.getLayoutY() + map.getHeight() && bottomX <= map.getLayoutX()) {

            //Dash stop
            movementControls.start();
            gravity.play();
            if (dashTimeline.getStatus().equals(Animation.Status.RUNNING)) {
                dashDistanceTravelled = 0;
                dashTimeline.stop();
                dashCooldownTimeline.play();
            }

            //Accelerate stop
            player.setLayoutX(player.getLayoutX() - velocityX);
            velocityXHolder = movementVariable / 2;
            velocityX = movementVariable / 2;
            timeLeftRight = 0.0;
        }
        if (player.getBoundsInParent().intersects(map.getBoundsInParent()) && middleY >= map.getLayoutY() && middleY <= map.getLayoutY() + map.getHeight() && bottomX >= map.getLayoutX() + map.getWidth() ) {

            //Dash stop
            dashDistanceTravelled = 0;
            dashTimeline.stop();
            movementControls.start();
            gravity.play();
            if (dashTimeline.getStatus().equals(Animation.Status.RUNNING)) {
                dashCooldownTimeline.play();
            }

            //Accelerate stop
            player.setLayoutX(player.getLayoutX() + velocityX);
            velocityXHolder = movementVariable / 2;
            velocityX = movementVariable / 2;
            timeLeftRight = 0.0;
        }
        if (bottomY > map.getLayoutY() && bottomY < map.getLayoutY() + movementVariable / 2) {
            collisionTimerTop.stop();
        }
        else {
            collisionTimerTop.start();
        }
    }
    public void checkCollisionTop(Ellipse player, Rectangle map) {
        double bottomX = player.getLayoutX();

        if (velocityY <= 7) {
            velocityY += grav * 0.5 * Math.pow(time, 2);
        }
        double newY = player.getLayoutY() + velocityY;
        if (newY < map.getLayoutY() + map.getHeight() && newY > map.getLayoutY() - player.getRadiusY() && bottomX > map.getLayoutX() + movementVariable && bottomX < map.getLayoutX() + map.getWidth() - movementVariable) {
            player.setLayoutY(map.getLayoutY() - player.getRadiusY());
            time = 0;
            velocityY = 2;
            jumpCount = 0;
            gravityTimeline.stop();
        }
    }
    public void shakeStage(int intensity, int time) {
        Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (shakeX== 0) {
                    anchorPane.setLayoutX(anchorPane.getLayoutX() + intensity);
                    HudMainImage.setLayoutX(HudMainImage.getLayoutX() - intensity);
                    HudOrbImage.setLayoutX(HudOrbImage.getLayoutX() - intensity);
                    shakeX= 1;
                } else {
                    anchorPane.setLayoutX(anchorPane.getLayoutX() - intensity);
                    HudMainImage.setLayoutX(HudMainImage.getLayoutX() + intensity);
                    HudOrbImage.setLayoutX(HudOrbImage.getLayoutX() + intensity);
                    shakeX= 0;
                }
            }
        }));

        timelineX.setCycleCount(time);
        timelineX.setAutoReverse(false);
        timelineX.play();


        Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (shakeY == 0) {
                    anchorPane.setLayoutY(anchorPane.getLayoutY() + intensity);
                    HudMainImage.setLayoutY(HudMainImage.getLayoutY() - intensity);
                    HudOrbImage.setLayoutY(HudOrbImage.getLayoutY() - intensity);
                    shakeY = 1;
                } else {
                    anchorPane.setLayoutY(anchorPane.getLayoutY() - intensity);
                    HudMainImage.setLayoutY(HudMainImage.getLayoutY() + intensity);
                    HudOrbImage.setLayoutY(HudOrbImage.getLayoutY() + intensity);
                    shakeY = 0;
                }
            }
        }));

        timelineY.setCycleCount(time);
        timelineY.setAutoReverse(false);
        timelineY.play();
    }

    //Boss
    public void projectileRain() {
        bossGlowingProjectile = new WritableImage(bossGlowingProjectileReader, 60, 130, 35, 14);
        rainProjectileTimeline = new Timeline(new KeyFrame(Duration.millis(550), e -> {
            if (bossMechFrameCounter == 0) {
                System.out.println("yo");
                bossProjectileMech1.setImage(bossGlowingProjectile);
                bossProjectileMech8.setImage(bossGlowingProjectile);
                bossMechFrameCounter++;
            } else if (bossMechFrameCounter == 1) {
                bossProjectileMech2.setImage(bossGlowingProjectile);
                bossProjectileMech7.setImage(bossGlowingProjectile);
                projectileFall(bossProjectileMech1, mapMainLeft);
                projectileFall(bossProjectileMech8, mapMainRight);
                bossMechFrameCounter++;
            } else if (bossMechFrameCounter == 2) {
                bossProjectileMech3.setImage(bossGlowingProjectile);
                bossProjectileMech6.setImage(bossGlowingProjectile);
                projectileFall(bossProjectileMech2, mapMainLeft);
                projectileFall(bossProjectileMech7, mapMainRight);
                bossMechFrameCounter++;
            } else if (bossMechFrameCounter == 3) {
                bossProjectileMech4.setImage(bossGlowingProjectile);
                bossProjectileMech5.setImage(bossGlowingProjectile);
                projectileFall(bossProjectileMech3, mapMainLeft);
                projectileFall(bossProjectileMech6, mapMainMiddle);
                bossMechFrameCounter++;
            } else if (bossMechFrameCounter == 4) {
                projectileFall(bossProjectileMech4, mapMainMiddle);
                projectileFall(bossProjectileMech5, mapMainMiddle);
                bossMechFrameCounter = 0;
                rainProjectileTimeline.stop();
            }
        }));
        rainProjectileTimeline.setCycleCount(Animation.INDEFINITE);
        rainProjectileTimeline.play();
    }
    public void projectileFall(ImageView image, Rectangle stage) {
        AnimationTimer fall = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                image.setLayoutY(image.getLayoutY() + 7);
                if (player.getBoundsInParent().intersects(image.getBoundsInParent())) {
                    loseHP();
                    collisionProjectiles.stop();
                    playerHitEffectTimeline.play();
                    image.setLayoutY(149);
                    image.setImage(null);
                    shakeStage(5, 14);
                    this.stop();
                }
                if (image.getLayoutY() + image.getFitHeight() / 2 > stage.getLayoutY()) {
                    image.setLayoutY(149);
                    image.setImage(null);
                    shakeStage(5, 14);
                    this.stop();
                }
            }
        };
        fall.start();
    }
    AnimationTimer bossOnEdge = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (bossAnimation.getLayoutX() + bossAnimation.getFitWidth() / 2 > 1500) {
                bossRightDirection = false;
                bossOppositeDirection = false;
                bossDownward = false;
                bossAnimation.setLayoutY(400);
                bossUpSpeed = 5;
            } else if (bossAnimation.getLayoutX()  + bossAnimation.getFitWidth() / 2 < 100) {
                bossRightDirection = true;
                bossOppositeDirection = false;
                bossDownward = false;
                bossAnimation.setLayoutY(400);
                bossUpSpeed = 5;
            }
        }
    };
    AnimationTimer bossDefeated = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (bossHP == 0) {
                FadeTransition transition = new FadeTransition();
                transition.setDuration(Duration.millis(1000));
                transition.setNode(anchorPane);
                transition.setFromValue(1);
                transition.setToValue(0);
                transition.setOnFinished((ActionEvent event) -> 
                {
                    bossMoveLeftRightTimeline.stop();
                    randomBossAttacks.stop();
                    randomProjectileTimeline.stop();
                    bossIdleTimeline.stop();
                    bossMoveLeftRightTimeline.stop();
                    collisionProjectiles.stop();
                    bossAnimation.setLayoutX(800);
                    bossAnimation.setLayoutY(675);
                    bossAnimation.setImage(bossDead);
                    player.setLayoutX(118);
                    player.setLayoutY(690);
                    FadeTransition transition2 = new FadeTransition();
                    transition2.setDuration(Duration.millis(1000));
                    transition2.setNode(anchorPane);
                    transition2.setFromValue(0);
                    transition2.setToValue(1);
                    transition2.setOnFinished((ActionEvent event2) -> {
                        bossFinished.start();
                    });
                    transition2.play();
                    bossDefeated.stop();
                });
                transition.play();
            }
        }
    };
    AnimationTimer bossFinished = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if (playerAnimation.getBoundsInParent().intersects(bossAnimation.getBoundsInParent())) {
                firstStageRoot = null;
                firstStageScene = null;
                fadeOut();
                try {
                    selectStageRoot = FXMLLoader.load(getClass().getResource("SelectStage.fxml"));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                holdRForHP.stop();
                // scene = new Scene(selectStageRoot);
                // stage = (Stage) playerAnimation.getScene().getWindow();
                // stage.setScene(scene);
                bossProjectileImageView.setLayoutX(-100);
                bossProjectileImageView.setLayoutY(-100);
                collisionProjectiles.stop();
                bossMoveLeftRightTimeline.stop();
                randomBossAttacks.stop();
                randomProjectileTimeline.stop();
                playerHP = 5;
                playerDead.stop();
                bossFinished.stop();
            }
        }
    };
    // public void bossMoveLeftRight(double xLocation) {
    //     double layoutX = bossAnimation.getLayoutX();
    //     double layoutY = bossAnimation.getLayoutY();
    //     Path path = new Path();
    //     path.getElements().add(new MoveTo(0, 10));
    //     path.getElements().add(new CubicCurveTo(70, -150, 140, 150, 210, 0));

    //     PathTransition transition = new PathTransition();
    //     transition.setDuration(Duration.millis(1500));
    //     transition.setOnFinished(finish -> {
    //         bossAnimation.setLayoutX(layoutX+210);
    //     });
    //     transition.setCycleCount((1300 - (int)xLocation) / 210);
    //     transition.setPath(path);
    //     transition.setNode(bossAnimation);
    //     transition.play();
    // }
    AnimationTimer lineFollow = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            line.setStartX(bossProjectileImageView.getLayoutX() + bossProjectileImageView.getFitWidth() / 2);
            line.setStartY(bossProjectileImageView.getLayoutY() + bossProjectileImageView.getFitHeight() / 2);
            line.setEndX(player.getLayoutX());
            line.setEndY(player.getLayoutY());
        }
    };
    public void checkProjectileContact(ImageView projectile) {

    }
    // AnimationTimer randomProjectileFollow = new AnimationTimer() {
    //     @Override
    //     public void handle(long timestamp) {
            
    //     }
    // };
    public WritableImage rotateImage(Image image, int rotation) {
        ImageView iv = new ImageView(image);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        params.setTransform(new Rotate(rotation, image.getHeight() / 2, image.getWidth() / 2));
        params.setViewport(new Rectangle2D(0, 0, image.getHeight(), image.getWidth()));
        return iv.snapshot(params, null);
    }
    public void setSelectStage(Parent root) {
        this.selectStageRoot = root;
    }
    public void reloadAssets() throws IOException {
        File file = new File("saver.txt");
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        reader = new BufferedReader(new FileReader(file));
        String text = null;
        while ((text = reader.readLine()) != null) {
            contents.append(text);
        }
        reader.close();
        System.out.println(contents.toString());
        playerAllAnimations = new Image("assets/Player/" + availableSkins.get(Integer.parseInt(contents.toString())) + ".png");
        playerAllAnimationsRotated = new Image("assets/Player/" + availableSkins.get(Integer.parseInt(contents.toString())) + "Rotated" + ".png");
        playerReader = playerAllAnimations.getPixelReader();
        playerReaderRotated = playerAllAnimationsRotated.getPixelReader();

        playerStill0 = flip(new WritableImage(playerReaderRotated, 4033, 258, 61, 130));
        playerStill1 = flip(new WritableImage(playerReaderRotated, 2265, 597, 61, 130));
        playerStill2 = flip(new WritableImage(playerReaderRotated, 2330, 695, 61, 130));
        playerStill3 = new WritableImage(playerReader, 3214, 2395, 61, 130);
        playerStill4 = new WritableImage(playerReader, 3149, 2395, 61, 130);

        playerFall0 = new WritableImage(playerReaderRotated, 2121, 1109, 78, 128);
        playerFall1 = new WritableImage(playerReaderRotated, 3900, 1831, 85, 128);
        playerFall2 = new WritableImage(playerReaderRotated, 2958, 1947, 88, 131);
        playerFall3 = new WritableImage(playerReaderRotated, 3700, 1844, 88, 131);
        playerFall4 = new WritableImage(playerReaderRotated, 3697, 1979, 91, 131);
        playerFall5 = new WritableImage(playerReaderRotated, 3701, 1709, 88, 131);

        playerLeft0 = new WritableImage(playerReaderRotated, 920, 369, 72, 120);
        playerLeft0 = flip(playerLeft0);
        playerLeft1 = new WritableImage(playerReaderRotated, 238, 248, 77, 111);
        playerLeft1 = flip(playerLeft1);
        playerLeft2 = new WritableImage(playerReader, 2974, 1591, 81, 121);
        playerLeft3 = new WritableImage(playerReaderRotated, 1394, 719, 75, 124);
        playerLeft3 = flip(playerLeft3);
        playerLeft4 = new WritableImage(playerReader, 3409, 1707, 64, 128);
        playerLeft6 = new WritableImage(playerReaderRotated, 3673, 2828, 65, 129);
        playerLeft6 = flip(playerLeft6);
        playerLeft8 = new WritableImage(playerReaderRotated, 2955, 2211, 65, 128);
        playerLeft8 = flip(playerLeft8);
        playerLeft10 = new WritableImage(playerReaderRotated, 3673, 2695, 65, 129);
        playerLeft10 = flip(playerLeft10);
        playerLeft12 = new WritableImage(playerReaderRotated, 2249, 2195, 69, 127);
        playerLeft12 = flip(playerLeft12);

        playerRight0 = flip(playerLeft0);
        playerRight1 = flip(playerLeft1);
        playerRight2 = flip(playerLeft2);
        playerRight3 = flip(playerLeft3);
        playerRight4 = flip(playerLeft4);
        playerRight6 = flip(playerLeft6);
        playerRight8 = flip(playerLeft8);
        playerRight10 = flip(playerLeft10);
        playerRight12 = flip(playerLeft12);

        playerDash0 = new WritableImage(playerReader, 2433, 3120, 104, 114);
        playerDash1 = new WritableImage(playerReader, 1976, 2618, 158, 111);
        playerDash2 = new WritableImage(playerReader, 1423, 2610, 187, 114);
        playerDash3 = new WritableImage(playerReader, 2160, 2044, 178, 114);
        playerDash4 = new WritableImage(playerReader, 1105, 2945, 192, 114);
        playerDash5 = new WritableImage(playerReader, 1802, 2615, 170, 113);
        playerDash6 = new WritableImage(playerReader, 3317, 1540, 83, 115);

        upSlash0 = new WritableImage(playerReader, 2647, 1098, 169, 178);
        upSlash1 = flip(new WritableImage(playerReaderRotated, 2941, 3455, 64, 150));
        upSlashImage1.setOpacity(0.7);
        playerUpSlash0 = new WritableImage(playerReader, 2912, 2436, 79, 127);
        playerUpSlash1 = flip(new WritableImage(playerReaderRotated, 2411, 1271, 115, 101));
        playerUpSlash2 = new WritableImage(playerReader, 3983, 282, 84, 99);
        playerUpSlash3 = new WritableImage(playerReader, 3998, 386, 77, 98);
        playerUpSlash4 = flip(new WritableImage(playerReaderRotated, 740, 403, 76, 118));
        downSlash0 = new WritableImage(playerReader, 1193, 2248, 182, 209);
        downSlash1 = new WritableImage(playerReader, 637, 3885, 182, 209);
        playerDownSlash0 = flip(new WritableImage(playerReaderRotated, 319, 348, 70, 124));
        playerDownSlash1 = flip(new WritableImage(playerReaderRotated, 3179, 2010, 122, 116));
        playerDownSlash2 = flip(new WritableImage(playerReaderRotated, 3594, 1732, 102, 118));
        playerDownSlash3 = new WritableImage(playerReader, 3075, 3337, 88, 117);
        playerDownSlash4 = flip(new WritableImage(playerReaderRotated, 3612, 1475, 88, 126));
        slash0 = new WritableImage(playerReader, 665, 2569, 214, 110);
        slash1 = new WritableImage(playerReader, 1735, 2732, 164, 93);
        playerSlash0 = flip(new WritableImage(playerReaderRotated, 2922, 618, 64, 123));
        playerSlash1 = new WritableImage(playerReader, 1973, 3050, 109, 125);
        playerSlash2 = flip(new WritableImage(playerReaderRotated, 35, 169, 67, 126));
        playerSlash3 = flip(new WritableImage(playerReaderRotated, 281, 117, 66, 127));
        playerSlash4 = flip(new WritableImage(playerReaderRotated, 2101, 553, 64, 128));

        slashEffect0 = new WritableImage(playerReader, 689, 3280, 182, 195);
        slashEffect1 = new WritableImage(playerReader, 1617, 639, 247, 276);
        slashEffect2 = new WritableImage(playerReader, 663, 1563, 294, 316);
        slashEffect3 = new WritableImage(playerReader, 1868, 645, 241, 270);
        slashEffect4 = new WritableImage(playerReader, 983, 2203, 206, 241);
        slashEffect5 = new WritableImage(playerReader, 1893, 1026, 197, 212);
        slashEffect6 = new WritableImage(playerReader, 2276, 723, 194, 212);
        slashEffect7 = new WritableImage(playerReader, 828, 2726, 188, 207);
        slashEffect8 = new WritableImage(playerReader, 2094, 1026, 197, 212);
        slashEffect9 = new WritableImage(playerReader, 491, 3235, 194, 212);
    }
}
