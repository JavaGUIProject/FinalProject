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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
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
		SubScene subScene = createSubScene("3D Shapes",
                Color.TRANSPARENT,
                new PerspectiveCamera(), true);

		
		centerBox.getChildren().add(subScene);
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
	                dialogVbox.setAlignment(Pos.CENTER);
	                dialogVbox.setPadding(new Insets(10d,10d,10d,10d));
	                Text title = new Text("Add a new shape");
	                
	                HBox xBox = new HBox();
	                Label xTxt = new Label("Position X: ");
	                TextField xInputBox = new TextField();
	                xInputBox.setText("200");
	                xBox.getChildren().addAll(xTxt,xInputBox);
	                
	                HBox yBox = new HBox();
	                Label yTxt = new Label("Position Y: ");
	                TextField yInputBox = new TextField();
	                yInputBox.setText("100");
	                yBox.getChildren().addAll(yTxt,yInputBox);
	                
	                HBox radiusBox = new HBox();
	                Label radTxt = new Label("Radius: ");
	                TextField radInputBox = new TextField();
	                radInputBox.setText("25");
	                radiusBox.getChildren().addAll(radTxt,radInputBox);
	                
	                HBox widthBox = new HBox();
	                Label widthTxt = new Label("Width: ");
	                TextField widthInputBox = new TextField();
	                widthInputBox.setText("50");
	                widthBox.getChildren().addAll(widthTxt,widthInputBox);
	                
	                HBox heightBox = new HBox();
	                Label heightTxt = new Label("Height");
	                TextField heightInputBox = new TextField();
	                heightInputBox.setText("50");
	                heightBox.getChildren().addAll(heightTxt,heightInputBox);
	                
	                HBox lengthBox = new HBox();
	                Label lengthTxt = new Label("Length: ");
	                TextField lengthInputBox = new TextField();
	                lengthInputBox.setText("50");
	                lengthBox.getChildren().addAll(lengthTxt,lengthInputBox);
	                VBox dimensionBox = new VBox();
	                dimensionBox.getChildren().addAll(widthBox,lengthBox,heightBox);
	                
	                ChoiceBox<String> choices = new ChoiceBox<>();
	                choices.getItems().addAll("Sphere","Cube","Cylinder");
	                
	                Button addShapeBut = new Button("Add Shape");
	                choices.getSelectionModel().selectedItemProperty().addListener((e)->
	                {
	                	if(choices.getSelectionModel().getSelectedItem().equals("Sphere")
	                	|| choices.getSelectionModel().getSelectedItem().equals("Cylinder"))
	                	{
	                		dialogVbox.getChildren().remove(heightBox);
	                		if(!dialogVbox.getChildren().contains(radiusBox))
	                			dialogVbox.getChildren().add(4, radiusBox);
	                		dialogVbox.getChildren().remove(dimensionBox);
	                	}
	                	else if(choices.getSelectionModel().getSelectedItem().equals("Cube"))
	                	{
	                		dialogVbox.getChildren().remove(heightBox);
	                		if(!dimensionBox.getChildren().contains(heightBox))
	                			dimensionBox.getChildren().add(2,heightBox);
	                		dialogVbox.getChildren().remove(radiusBox);
	                		if(!dialogVbox.getChildren().contains(dimensionBox))
	                			dialogVbox.getChildren().add(4,dimensionBox);
	                		
	                		addShapeBut.setOnAction((p)->
	                		{
	                			int posX = Integer.parseInt(xInputBox.getText());
	                			int posY = Integer.parseInt(yInputBox.getText());
		                		int width = Integer.parseInt(heightInputBox.getText());
		                		int height = Integer.parseInt(widthInputBox.getText());
		                		int depth = Integer.parseInt(lengthInputBox.getText());
		                		
	                			Box box = new Box();
	                	        box.setWidth(width);
	                	        box.setHeight(height);
	                	        box.setDepth(depth);
	                	        box.setTranslateX(posX);
	                	        box.setTranslateY(posY);
	                	        
	                	       ((HBox)subScene.getRoot()).getChildren().add(box);
	                	       dialog.hide();
	                		});
	                		
	                	}
	                	if(choices.getSelectionModel().getSelectedItem().equals("Cylinder"))
            			{
	                		if(!dialogVbox.getChildren().contains(heightBox))
	                			dialogVbox.getChildren().add(5,heightBox);
	                		
	                		addShapeBut.setOnAction((p)->
	                		{
	                			int posX = Integer.parseInt(xInputBox.getText());
	                			int posY = Integer.parseInt(yInputBox.getText());
		                		int rad = Integer.parseInt(radInputBox.getText());
		                		int height = Integer.parseInt(widthInputBox.getText());
		                		
	                			Cylinder cyl = new Cylinder();
	                			cyl.setRadius(rad);
	                			cyl.setHeight(height);
	                			cyl.setTranslateX(posX);
	                			cyl.setTranslateY(posY);
	                	        
	                	       ((HBox)subScene.getRoot()).getChildren().add(cyl);
	                	       dialog.hide();
	                		});
            			}
	                	if(choices.getSelectionModel().getSelectedItem().equals("Sphere"))
            			{
	                		addShapeBut.setOnAction((p)->
	                		{
	                			int posX = Integer.parseInt(xInputBox.getText());
	                			int posY = Integer.parseInt(yInputBox.getText());
		                		int rad = Integer.parseInt(radInputBox.getText());
		                		
	                			Sphere sphere = new Sphere();
	                			sphere.setRadius(rad);
	                			sphere.setTranslateX(posX);
	                			sphere.setTranslateY(posY);
	                	        
	                	       ((HBox)subScene.getRoot()).getChildren().add(sphere);
	                	       dialog.hide();
	                		});
            			}
	                });
	               
	                dialogVbox.getChildren().addAll(title,choices, xBox, yBox,addShapeBut);
	                Scene dialogScene = new Scene(dialogVbox, 300, 300);
	                dialog.setScene(dialogScene);
	                dialog.show();
	                choices.getSelectionModel().selectFirst();
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
        HBox root = new HBox();
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);
        PointLight light2 = new PointLight(Color.color(1, 1, 1));
        light2.setTranslateX(400);
        light2.setTranslateY(0);
        light2.setTranslateZ(-400);

        AmbientLight ambientLight = new AmbientLight(Color.color(0, 0, 0));
        root.getChildren().addAll(ambientLight, light, light2);
 
        SubScene subScene = new SubScene(root, 500, 400, true, 
                msaa ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED);
        subScene.setFill(fillPaint);
        subScene.setCamera(camera);
        
        return subScene;
    }

}
