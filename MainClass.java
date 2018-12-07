import java.util.ArrayList;
import java.util.List;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainClass extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,10,10,10));
		HBox leftBox = new HBox();
		leftBox.getChildren().add(new Label("Welcome"));
		leftBox.setAlignment(Pos.TOP_CENTER);
		HBox rightBox = new HBox();
		rightBox.getChildren().add(new Label("Options"));
		rightBox.setAlignment(Pos.TOP_CENTER);
		HBox centerBox = new HBox();
		bp.setCenter(centerBox);
		bp.setLeft(leftBox);
		bp.setRight(rightBox);
		SubScene msaa = createSubScene("3D Shapes",
                Color.TRANSPARENT,
                new PerspectiveCamera(), true);

		
		centerBox.getChildren().add(msaa);
		centerBox.setAlignment(Pos.CENTER);
		
		HBox bottomBox = new HBox();
		Button addButton = new Button("Add Shape");
		addButton.setOnAction(
	        new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                final Stage dialog = new Stage();
	                dialog.initModality(Modality.APPLICATION_MODAL);
	                dialog.initOwner(stage);
	                VBox dialogVbox = new VBox(20);
	                Text title = new Text("Add a new shape");
	                ChoiceBox<String> choices = new ChoiceBox<>();
	                choices.getItems().addAll("Sphere","Cube","Cylinder");
	                dialogVbox.getChildren().addAll(title,choices);
	                Scene dialogScene = new Scene(dialogVbox, 300, 200);
	                dialog.setScene(dialogScene);
	                dialog.show();
	            }
	         });
		   
		bottomBox.getChildren().addAll(addButton);
		bottomBox.setAlignment(Pos.CENTER);
		bp.setBottom(bottomBox);
		
		stage.setWidth(800);
		stage.setHeight(600);
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.setTitle("Shapes App");
		stage.show();
	}
	
	private static SubScene createSubScene(String title,
        Paint fillPaint, Camera camera, boolean msaa) {
        //Group root = new Group();
        HBox root = new HBox();
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);
        PointLight light2 = new PointLight(Color.color(0.6, 0.3, 0.4));
        light2.setTranslateX(400);
        light2.setTranslateY(0);
        light2.setTranslateZ(-400);
        
        Box box = new Box();
        box.setWidth(50);
        box.setHeight(50);
        box.setDepth(50);
        box.setTranslateX(200);
        box.setTranslateY(150);
        //box.setRotationAxis(new Point3D(0,0,1));
        //box.setRotate(20);
       // box.setRotationAxis(new Point3D(0,30,1));
        //box.setRotate(30);
        //Rotate r = new Rotate();
        Rotate rotate = Rotate.rotate(50, 0, 0);
        rotate.setAxis(new Point3D(0,1,0));
        box.getTransforms().add(rotate);
        System.out.println(rotate.getAngle());
        AmbientLight ambientLight = new AmbientLight(Color.color(0.2, 0.2, 0.2));
        root.getChildren().addAll(ambientLight, light, light2, box);//, node);
 
        SubScene subScene = new SubScene(root, 500, 400, true, 
                msaa ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED);
        subScene.setFill(fillPaint);
        subScene.setCamera(camera);
        //subScene.setRoot(root);
        return subScene;
    }

}
