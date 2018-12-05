import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainClass extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		HBox hbox = new HBox();
		
		Scene scene = new Scene(hbox);
		SubScene msaa = createSubScene("MSAA = true", hbox,
                Color.TRANSPARENT,
                new PerspectiveCamera(), true);
		
		
		
		
		
		stage.setWidth(800);
		stage.setHeight(600);
		stage.setScene(scene);
		stage.setTitle("Shapes App");
		stage.show();
	}
	
	private static SubScene createSubScene(String title, Node rootNode,
        Paint fillPaint, Camera camera, boolean msaa) {
        Group root = new Group();
 
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(50);
        light.setTranslateY(-300);
        light.setTranslateZ(-400);
        PointLight light2 = new PointLight(Color.color(0.6, 0.3, 0.4));
        light2.setTranslateX(400);
        light2.setTranslateY(0);
        light2.setTranslateZ(-400);
 
        AmbientLight ambientLight = new AmbientLight(Color.color(0.2, 0.2, 0.2));
        root.getChildren().addAll(ambientLight, light, light2);//, node);
 
        SubScene subScene = new SubScene(root, 500, 400, true, 
                msaa ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED);
        subScene.setFill(fillPaint);
        subScene.setCamera(camera);
        ((HBox)rootNode).getChildren().add(subScene);
        
        return subScene;
    }

}
