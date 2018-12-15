import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;


public class MainClass extends Application{
    Shape3D selectedShape;
	Box box = new Box();
	Sphere sphere = new Sphere();
    Cylinder cyl = new Cylinder();
    ShapeSaver saver = new ShapeSaver();
    Translate translate;
    Rotate rX;
    Rotate rY;
    Rotate rZ;
//    Rotate cylinderRotateX = new Rotate(0, Rotate.X_AXIS);
//    Rotate cylinderRotateY = new Rotate(0, Rotate.Y_AXIS);
//	Rotate sphereRotateX = new Rotate(0, Rotate.X_AXIS);
//	Rotate sphereRotateY = new Rotate(0, Rotate.Y_AXIS);
//	Rotate boxRotateX = new Rotate(0, Rotate.X_AXIS);
//	Rotate boxRotateY = new Rotate(0, Rotate.Y_AXIS);
	int posX;
	int posY;
	//Scale scale = new Scale(1.0,1.0,1.0);

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		MenuBar bar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem save = new MenuItem("Save");
		MenuItem load = new MenuItem("Load");
		MenuItem exit = new MenuItem("Exit");
		menu.getItems().addAll(save, load, exit);
		bar.getMenus().add(menu);
		Group shapesGroup = new Group();
		PerspectiveCamera pCam = new PerspectiveCamera();
		BorderPane bp = new BorderPane();
		HBox leftBox = new HBox();
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
	                			box = new Box();
	                			box.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
	                			posX = Integer.parseInt(xInputBox.getText());
	                			posY = Integer.parseInt(yInputBox.getText());
		                		int width = Integer.parseInt(heightInputBox.getText());
		                		int height = Integer.parseInt(widthInputBox.getText());
		                		int depth = Integer.parseInt(lengthInputBox.getText());


	                	        box.setWidth(width);
	                	        box.setHeight(height);
	                	        box.setDepth(depth);
	                	        box.getTransforms().add(new Translate(posX, posY, 0));
	                	        box.getTransforms().add(new Rotate(0, Rotate.X_AXIS));
	                	        box.getTransforms().add(new Rotate(0, Rotate.Y_AXIS));
	                	        box.getTransforms().add(new Rotate(0, Rotate.Z_AXIS));
	                	        //box.setTranslateX(posX);
	                	        //box.setTranslateY(posY);
	                	        saver.saveBox(box);
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
		                		cyl = new Cylinder();
		                		cyl.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
	                			cyl.setRadius(rad);
	                			cyl.setHeight(height);
	                			cyl.getTransforms().add(new Translate(posX, posY, 0));
		                	    cyl.getTransforms().add(new Rotate(0, Rotate.X_AXIS));
		                	    cyl.getTransforms().add(new Rotate(0, Rotate.Y_AXIS));
		                	    cyl.getTransforms().add(new Rotate(0, Rotate.Z_AXIS));
		                	    saver.saveCylinder(cyl);
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

		                		sphere = new Sphere();
		                		sphere.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
	                			sphere.setRadius(rad);
	                			sphere.getTransforms().add(new Translate(posX, posY, 0));
	                			sphere.getTransforms().add(new Rotate(0, Rotate.X_AXIS));
		                	    sphere.getTransforms().add(new Rotate(0, Rotate.Y_AXIS));
		                	    sphere.getTransforms().add(new Rotate(0, Rotate.Z_AXIS));
	                			saver.saveSphere(sphere);
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
		bottomBox.setStyle("-fx-border-color: black");
		bottomBox.setPadding(new Insets(5));
		bp.setBottom(bottomBox);

		//cyl.getTransforms().addAll(translate,scale,cylinderRotateX, cylinderRotateY);
		//box.getTransforms().addAll(translate,scale, boxRotateX, boxRotateY);
		//sphere.getTransforms().addAll(translate,scale,sphereRotateX,sphereRotateY);


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
		
		Label sLabel = new Label("Scale");
		Slider scaleSlider = new Slider();
		scaleSlider.setShowTickMarks(true);
		scaleSlider.setShowTickLabels(true);
		scaleSlider.setMin(1);
		scaleSlider.setMax(10);
		
		Label cLabel = new Label("Color");
		ChoiceBox<String> colorChoice = new ChoiceBox<String>(FXCollections.observableArrayList(
                "Default","Red","Orange","Yellow","Green","Blue","Purple"));
                colorChoice.getSelectionModel().select(0);
                colorChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number oldValue, Number newValue) {
                       int selected = newValue.intValue();
                       if(selectedShape != null) {
                       if(selected == 1) {
                        	   selectedShape.setMaterial(new PhongMaterial(Color.RED));
                       }else if(selected == 2) {
                               
                               selectedShape.setMaterial(new PhongMaterial(Color.ORANGE));
                           
                       } else if(selected == 3) {
                               selectedShape.setMaterial(new PhongMaterial(Color.YELLOW));
                       }else if(selected == 4) {
                                selectedShape.setMaterial(new PhongMaterial(Color.GREEN));

                       }else if(selected == 5) {
                                selectedShape.setMaterial(new PhongMaterial(Color.BLUE));
                       }else if(selected == 6) {
                              selectedShape.setMaterial(new PhongMaterial(Color.PURPLE));

                       }else if(selected == 0) {
                              selectedShape.setMaterial(new PhongMaterial(Color.LIGHTGRAY));
                       }      
                       }
                    }
                });
		
		VBox optionsBox = new VBox(10, optionLabel, vLabel, verticalSlider, hLabel, horizontalSlider,sLabel,scaleSlider,translateXLabel,xTranslate,
				translateXSubmit,translateYLabel,yTranslate,translateYSubmit,cLabel,colorChoice);
		optionsBox.setAlignment(Pos.TOP_CENTER);
		HBox hb1 = new HBox(optionsBox);

		
        
		translateXSubmit.setOnAction(event->{
			if(translate != null)
			translate.setX(Integer.parseInt(xTranslate.getText()));
		});
		translateYSubmit.setOnAction(event->{
			if(translate != null)
			translate.setY(Integer.parseInt(yTranslate.getText()));
		});
		verticalSlider.valueProperty().addListener((o, oldVal, newVal) ->
		{
			if(rY != null)
			rY.setAngle((double)newVal);
		});

		horizontalSlider.valueProperty().addListener((o, oldVal, newVal) ->
		{
			if(rX != null)
			rX.setAngle((double)newVal);
			
		});
		
		scaleSlider.valueProperty().addListener((o,oldVal,newVal) -> {
			if(selectedShape != null) {
				selectedShape.setScaleX(newVal.doubleValue());
				selectedShape.setScaleY(newVal.doubleValue());
			}
		});
		save.setOnAction(event-> {
			FileChooser chooser = new FileChooser();
			File f = chooser.showSaveDialog(stage);
			if(f != null) {
				try {
					f.createNewFile();
					saver.saveAll(f.getAbsolutePath());
				} catch (IOException e) {
					
				}
			}
			
			
		});
		load.setOnAction(event -> {
			FileChooser chooser = new FileChooser();
			File f = chooser.showOpenDialog(stage);
			if(f != null) {
				shapesGroup.getChildren().remove(0, shapesGroup.getChildren().size());
				saver.loadAll(f.getAbsolutePath());
				Box[] boxes = saver.getBoxes();
				for(int i = 0; i < boxes.length; i++) {
					boxes[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
					shapesGroup.getChildren().addAll(boxes[i]);
					System.out.println(boxes[i].getTranslateX());
				}
				Cylinder[] cylinders = saver.getCylinders();
				for(int i = 0; i < cylinders.length; i++) {
					cylinders[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
					shapesGroup.getChildren().addAll(cylinders[i]);
				}
				Sphere[] spheres = saver.getSpheres();
				for(int i = 0; i < spheres.length; i++) {
					spheres[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
					shapesGroup.getChildren().addAll(spheres[i]);
				}
			}
		});
		exit.setOnAction(event -> {
			System.exit(1);
		});
		bp.setTop(bar);
		bp.setRight(hb1);
		bp.setPadding(new Insets(0, 10, 10, 10));
		hb1.setStyle("-fx-border-color: black");
		hb1.setPadding(new Insets(10));
		stage.setWidth(800);
		stage.setHeight(700);
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.setTitle("Shapes App");
		stage.show();
	}
	
	public class ClickHandler implements EventHandler<Event>{

		@Override
		public void handle(Event event) {
			System.out.println("WE ARE HERE");
			selectedShape = (Shape3D)event.getSource();
			translate = (Translate)selectedShape.getTransforms().get(0);
			rX = (Rotate)selectedShape.getTransforms().get(1);
			rY = (Rotate)selectedShape.getTransforms().get(2);
			rZ = (Rotate)selectedShape.getTransforms().get(3);
			selectedShape = (Shape3D)event.getSource();
			
		}
		
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

        SubScene subScene = new SubScene(g1, 500, 550, true,
                msaa ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED);
        subScene.setFill(fillPaint);
        subScene.setCamera(camera);


        return subScene;
    }

}
