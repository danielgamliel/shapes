import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Shapes extends Application {
    final int SCREENSIZE = 600;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(SCREENSIZE, SCREENSIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Button btn = new Button();
        btn.setText("create shapes");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i <10 ; i++) {
                  int temp = (int) Math.floor(Math.random()*3);
                //draw 10 random shape from 3 options: ellipse, line and Quadrilateral
                  drawShapes(gc, temp);
                }
            }
        });
        root.getChildren().add(canvas);
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, SCREENSIZE, SCREENSIZE, Color.AQUA));
        primaryStage.show();
    }
    private void drawShapes(GraphicsContext gc, int shape) {
        if(shape==0){ //shape 0 is Quadrilateral
            int x1 = randomPlace();
            int x2 = randomPlace();
            int x3 = randomPlace();
            int x4 = randomPlace();
            int y1 = randomPlace();
            int y2 = randomPlace();
            int y3 = randomPlace();
            int y4 = randomPlace(); //validate Quadrilateral bounds
            int[] newArr = getValidQuadrilateral(x1,x2,x3,x4,y1,y2,y3,y4);
            gc.fillPolygon(new double[]{newArr[0],newArr[1],newArr[2],newArr[3]}, new double[]{
                    newArr[4],newArr[5],newArr[6],newArr[7]}, 4);
            // different random color
            gc.setFill(Color.rgb(randomColor(),randomColor(),randomColor()));
        } else if (shape==1) { //shape 0 is ellipse
            gc.setFill(Color.rgb(randomColor(),randomColor(),randomColor()));
            gc.fillOval(randomPlace(),randomPlace(),randomShape(),randomShape());
        }else{ //else the shape is a line
                //entered default invalid size to get into the getValidLine to correct the length
                int[] newArr = getValidLine(1,1,SCREENSIZE,SCREENSIZE);
            gc.strokeLine(newArr[0],newArr[1],newArr[2],newArr[3]);
        }
    }

    // quarter of the size of the screen
    private int randomShape(){return (int) Math.floor(Math.random()*SCREENSIZE/4);}
    // 255 rgb range
    private int randomColor(){return (int) Math.floor(Math.random()*255);}
    // random position on the screen
    private int randomPlace(){return (int) Math.floor(Math.random()*SCREENSIZE);}

    private int[] getValidLine(int x1, int y1, int x2, int y2) {
        int[] arr = new int[]{x1, y1, x2, y2};
        // distance formula =sqrt((x1-x2) + (y1-y2))
        if ((Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))) < SCREENSIZE/4) {
            return arr;
        } else {
            int newX1 = randomPlace();
            int newY1 = randomPlace();
            int newX2 = randomPlace();
            int newY2 = randomPlace();
            return getValidLine(newX1, newY1, newX2, newY2);
        }
    }
        private int[] getValidQuadrilateral(int x1, int x2, int x3, int x4, int y1,int y2,int y3,int y4){
            int[] arr=  new int[]{x1,x2,x3,x4,y1,y2,y3,y4};
            // calculate the distance between every point
            if((Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)))<SCREENSIZE/4
            && (Math.sqrt(Math.pow(x4 - x1, 2) + Math.pow(y4 - y1, 2)))<SCREENSIZE/4
            && (Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2)))<SCREENSIZE/4
            && (Math.sqrt(Math.pow(x4 - x3, 2) + Math.pow(y4 - y3, 2)))<SCREENSIZE/4) {
                return arr;
            }else{
                int newX1 = randomPlace();
                int newX2 = randomPlace();
                int newX3 = randomPlace();
                int newX4 = randomPlace();
                int newY1 = randomPlace();
                int newY2 = randomPlace();
                int newY3 = randomPlace();
                int newY4 = randomPlace();
                return getValidQuadrilateral( newX1,newX2, newX3, newX4, newY1, newY2, newY3, newY4);
            }
    }

}