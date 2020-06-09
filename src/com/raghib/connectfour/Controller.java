package com.raghib.connectfour;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
im
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static final int ROWS=6;
    public static final int COLUMNS=7;
    public static final int CIRCLE_DIAMETER=80;
    public static final String discColor1="#24303E";
    public static final String discColor2="#4CAA88";

    public static String PLAYER_ONE="Player one";
    public static String PLAYER_TWO="Player two";

    private  boolean isPlayerOneTurn=true;

    @FXML
    public GridPane rootGridPane;
  @FXML
   public Pane insertedDiscsPane;
  private Disc[][] insertedDiscArray= new Disc[ROWS][COLUMNS];
  public void createPlayground()
  {
      Shape rectangleWithHoles=createGameStructuralGrid();
      rootGridPane.add(rectangleWithHoles,0,1);
      List<Rectangle> rectangleList=createClickAbleColumn();
      for(Rectangle rectangle:rectangleList) {
          rootGridPane.add(rectangle, 0, 1);
      }


  }

  private Shape createGameStructuralGrid()
  {
      Shape rectangleWithHoles=new Rectangle((COLUMNS+1)*CIRCLE_DIAMETER,(ROWS+1)*CIRCLE_DIAMETER);

      rectangleWithHoles.prefWidth(insertedDiscsPane.getWidth());

      for(int row=0;row<ROWS;row++)
          for(int col=0;col<COLUMNS;col++) {
              Circle circle = new Circle();
              circle.setRadius(CIRCLE_DIAMETER / 2);
              circle.setCenterX(CIRCLE_DIAMETER / 2);
              circle.setCenterY(CIRCLE_DIAMETER / 2);
              circle.setTranslateX((CIRCLE_DIAMETER+5)*col+CIRCLE_DIAMETER/4);
              circle.setTranslateY((CIRCLE_DIAMETER+5)*row+CIRCLE_DIAMETER/4);;
              rectangleWithHoles = Shape.subtract(rectangleWithHoles, circle);

          }
      rectangleWithHoles.setFill(Color.WHITE);
      return rectangleWithHoles;
  }
  private List<Rectangle> createClickAbleColumn()
  {
      List<Rectangle> rectangleList=new ArrayList<>();
      for(int i=0;i<COLUMNS;i++) {
          Rectangle rectangle = new Rectangle(CIRCLE_DIAMETER, (ROWS + 1) * CIRCLE_DIAMETER);
          rectangle.setTranslateX(CIRCLE_DIAMETER / 4+(CIRCLE_DIAMETER+5)*i);
          rectangle.setFill(Color.TRANSPARENT);
          rectangle.setOnMouseEntered(event -> rectangle.setFill(Color.valueOf("#eeeeee26")));
          rectangle.setOnMouseExited(event -> rectangle.setFill(Color.TRANSPARENT));

          final int column=i;
          rectangle.setOnMouseClicked(event -> {
                insertDisc(new Disc(isPlayerOneTurn),column);
          });
          rectangleList.add(rectangle);
      }
      return rectangleList;

  }

  private void insertDisc(Disc disc,int column)
  {
      insertedDiscArray[0][column]=disc;
      insertedDiscsPane.getChildren().add(disc);
      disc.setTranslateX(column*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);
      TranslateTransition translateTransition=new TranslateTransition(Duration.seconds(0.5),disc);
      translateTransition.setToY(5*(CIRCLE_DIAMETER+5)+CIRCLE_DIAMETER/4);
      translateTransition.play();
  }

  private  static class Disc extends  Circle{

      private  boolean isPlayerOneTurn;
      public Disc(boolean isPlayerOneTurn)
      {
          this.isPlayerOneTurn=isPlayerOneTurn;
          setFill(isPlayerOneTurn?Color.valueOf(discColor1):Color.valueOf(discColor2));
          setRadius(CIRCLE_DIAMETER/2);
          setCenterX(CIRCLE_DIAMETER/2);
          setCenterY(CIRCLE_DIAMETER/2);

      }


  }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
