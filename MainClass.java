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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Translate;


public class MainClass extends Application{
    Shape3D selectedShape;
	Box box = new Box();
	Sphere sphere = new Sphere();
    Cylinder cyl = new Cylinder();
    Rotate cylinderRotateX = new Rotate(0, Rotate.X_AXIS);
    Rotate cylinderRotateY = new Rotate(0, Rotate.Y_AXIS);
	Rotate sphereRotateX = new Rotate(0, Rotate.X_AXIS);
	Rotate sphereRotateY = new Rotate(0, Rotate.Y_AXIS);
	Rotate boxRotateX = new Rotate(0, Rotate.X_AXIS);
	Rotate boxRotateY = new Rotate(0, Rotate.Y_AXIS);
	int posX;
	int posY;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Group shapesGroup = new Group();
		PerspectiveCamera pCam = new PerspectiveCamera();
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,10,10,10));
		HBox leftBox = new HBox();
		leftBox.getChildren().add(new Label("Welcome"));
		leftBox.setAlignment(Pos.TOP_CENTER);
		HBox rightBox = new HBox();
		rightBox.setAlignment(Pos.TOP_CENTER);
		HBox centerBox = new HBox();
		bp.setCenter(centerBox);
		bp.setLeft(leftBox);
		bp.setRight(rightBox);
		SubScene subScene = createSubScene(shapesGroup,"3D Shapes",
                Color.TRANSPARENT,
                pCam, true);


		centerBox.getChildren().add(subScene);
		centerBox.setAlignment(Pos.CENTER);

		TextField xTranslate = new TextField();
		TextField yTranslate = new TextField();
		Label translateXLabel = new Label("Translate X-Coordinate");
		Button translateXSubmit = new Button("Submit");
		Label translateYLabel = new Label("Translate Y-Coordinate");
		Button translateYSubmit = new Button("Submit");
		Translate translate = new Translate(0,0);


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
	                			posX = Integer.parseInt(xInputBox.getText());
	                			posY = Integer.parseInt(yInputBox.getText());
		                		int width = Integer.parseInt(heightInputBox.getText());
		                		int height = Integer.parseInt(widthInputBox.getText());
		                		int depth = Integer.parseInt(lengthInputBox.getText());


	                	        box.setWidth(width);
	                	        box.setHeight(height);
	                	        box.setDepth(depth);
	                	        box.setTranslateX(posX);
	                	        box.setTranslateY(posY);

								shapesGroup.getChildren().addAll(box);
	                	       dialog.hide();
	                		});

	                	}
	                	if(choices.getSelectionModel().getSelectedItem().equals("Cylinder"))
            			{
	                		if(!dialogVbox.getChildren().contains(heightBox))
	                			dialogVbox.getChildren().add(5,heightBox);

	                		addShapeBut.setOnAction((p)->
	                		{
	                			posX = Integer.parseInt(xInputBox.getText());
	                			posY = Integer.parseInt(yInputBox.getText());
		                		int rad = Integer.parseInt(radInputBox.getText());
		                		int height = Integer.parseInt(widthInputBox.getText());

	                			cyl.setRadius(rad);
	                			cyl.setHeight(height);
	                			cyl.setTranslateX(posX);
	                			cyl.setTranslateY(posY);

								shapesGroup.getChildren().addAll(cyl);
                                dialog.hide();
	                		});

                        }
	                	if(choices.getSelectionModel().getSelectedItem().equals("Sphere"))
            			{
	                		addShapeBut.setOnAction((p)->
	                		{
	                			posX = Integer.parseInt(xInputBox.getText());
	                			posY = Integer.parseInt(yInputBox.getText());
		                		int rad = Integer.parseInt(radInputBox.getText());


	                			sphere.setRadius(rad);
	                			sphere.setTranslateX(posX);
	                			sphere.setTranslateY(posY);

								shapesGroup.getChildren().addAll(sphere);
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

		cyl.getTransforms().addAll(translate,cylinderRotateX, cylinderRotateY);
		box.getTransforms().addAll(translate, boxRotateX, boxRotateY);
		sphere.getTransforms().addAll(translate,sphereRotateX,sphereRotateY);


		Label optionLabel = new Label("Options");
		optionLabel.setStyle( "-fx-font-size: 14pt ; -fx-underline: true");


        Label vLabel = new Label("Rotate Vertically");
		Slider verticalSlider = new Slider();
        verticalSlider.setShowTickMarks(true);
        verticalSlider.setMin(0);
        verticalSlider.setMax(360);


		Label hLabel = new Label("Rotate Horizontally");
		Slider horizontalSlider = new Slider();
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setMin(0);
		horizontalSlider.setMax(360);

		VBox optionsBox = new VBox(10, optionLabel, vLabel, verticalSlider, hLabel, horizontalSlider,translateXLabel,xTranslate,
				translateXSubmit,translateYLabel,yTranslate,translateYSubmit );
		optionsBox.setAlignment(Pos.TOP_CENTER);
		HBox hb1 = new HBox(bp,optionsBox);


        cyl.setOnMouseClicked(event -> {
            selectedShape = cyl;
			translateXSubmit.setOnMouseClicked(e -> {
				if(selectedShape.equals(cyl))
				{
					translate.setX((Double.parseDouble(xTranslate.getText())));
				}
			});
			translateYSubmit.setOnMouseClicked(e -> {
				if(selectedShape.equals(cyl))
				{
					translate.setY((Double.parseDouble(yTranslate.getText())));
				}

			});
        });

		sphere.setOnMouseClicked(event -> {
			selectedShape = sphere;
			translateXSubmit.setOnMouseClicked(e -> {
				if(selectedShape.equals(sphere))
				{
					translate.setX((Double.parseDouble(xTranslate.getText())));
				}
			});
			translateYSubmit.setOnMouseClicked(e -> {
				if(selectedShape.equals(sphere))
				{
					translate.setY((Double.parseDouble(yTranslate.getText())));
				}

			});
		});

		box.setOnMouseClicked(event -> {
			selectedShape = box;
			translateXSubmit.setOnMouseClicked(e -> {
				if(selectedShape.equals(box))
				{
					translate.setX((Double.parseDouble(xTranslate.getText())));
				}
			});
			translateYSubmit.setOnMouseClicked(e -> {
				if(selectedShape.equals(box))
				{
					translate.setY((Double.parseDouble(yTranslate.getText())));
				}

			});
			if (selectedShape == box){
				System.out.println("Works");
			}
		});

		verticalSlider.valueProperty().addListener((o, oldVal, newVal) ->
		{
			if(selectedShape.equals(cyl))
			{
				cylinderRotateY.setAngle((double)newVal);
			}
			else if(selectedShape.equals(sphere)){
				sphereRotateY.setAngle((double) newVal);
			}

			else if(selectedShape.equals(box)){
				boxRotateY.setAngle((double) newVal);
			}
		});

		horizontalSlider.valueProperty().addListener((o, oldVal, newVal) ->
		{
			if(selectedShape.equals(cyl))
			{
				cylinderRotateX.setAngle((double)newVal);
			}
			else if(selectedShape.equals(sphere)){
				sphereRotateX.setAngle((double) newVal);
			}

			else if(selectedShape.equals(box)){
				boxRotateX.setAngle((double) newVal);
			}
		});

		stage.setWidth(800);
		stage.setHeight(600);
		Scene scene = new Scene(hb1);
		stage.setScene(scene);
		stage.setTitle("Shapes App");
		stage.show();
	}

	private static SubScene createSubScene(Group g1, String title,
        Paint fillPaint, PerspectiveCamera camera, boolean msaa) {
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

        SubScene subScene = new SubScene(g1, 500, 400, true,
                msaa ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED);
        subScene.setFill(fillPaint);
        subScene.setCamera(camera);


        return subScene;
    }

}
