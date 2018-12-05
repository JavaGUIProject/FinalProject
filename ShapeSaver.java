import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ShapeSaver {
	public static void main(String[] args) {
		ShapeSaver saver = new ShapeSaver();
		Box b = new Box(0, 0, 0);
		b.setScaleX(100);
		b.setScaleY(100);
		b.getTransforms().add(new Translate(1, 5, 10));
		saver.printTranslate(b);
	}
	//sphere, box, cylinder
	private ArrayList<Box> boxes = new ArrayList<Box>();
	private ArrayList<Sphere> spheres = new ArrayList<Sphere>();
	private ArrayList<Cylinder> cylinders = new ArrayList<Cylinder>();
	
	public boolean saveBox(Box b){
		boxes.add(b);
		return true;
	}
	
	public boolean saveCylinder(Cylinder s){
		cylinders.add(s);
		return true;
	}
	public boolean saveSphere(Sphere s){
		spheres.add(s);
		return true;
	}
	public Box[] getBoxes(){
		Box[] result = new Box[boxes.size()];
		for(int i = 0; i < boxes.size(); i++){
			result[i] = boxes.get(i);
		}
		return result;
	}
	public Sphere[] getSpheres(){
		Sphere[] result = new Sphere[spheres.size()];
		for(int i = 0; i < spheres.size(); i++){
			result[i] = spheres.get(i);
		}
		return result;
	}
	
	public Cylinder[] getCylinders(){
		Cylinder[] result = new Cylinder[cylinders.size()];
		for(int i = 0; i < cylinders.size(); i++){
			result[i] = cylinders.get(i);
		}
		return result;
	}
	
	public boolean saveAll(String path){
		try{
		StringBuilder text = new StringBuilder();
		 File file = new File(path);
		 FileWriter fw = new FileWriter(file);
		 BufferedWriter bw = new BufferedWriter(fw);
		 	for(int i = 0; i < boxes.size(); i++){
		 		Translate t = (Translate)boxes.get(i).getTransforms().get(0);
		 		Rotate r = (Rotate)boxes.get(i).getTransforms().get(0);
		 		text.append("Box,");
		 		text.append(",");
		 		text.append(boxes.get(i).getHeight());
		 		text.append(",");
		 		text.append(boxes.get(i).getWidth());
		 		text.append(",");
		 		text.append(boxes.get(i).getDepth());
		 		text.append(",");
		 		text.append(boxes.get(i).getScaleX());
		 		text.append(",");
		 		text.append(boxes.get(i).getScaleY());
		 		text.append(",");
		 		text.append(boxes.get(i).getScaleZ());
		 		text.append(",");
		 		text.append(t.getX());
		 		text.append(",");
		 		text.append(t.getY());
		 		text.append(",");
		 		text.append(t.getZ());
		 		text.append(",");
		 		text.append(r.getAxis().getX());
		 		text.append(",");
		 		text.append(r.getAxis().getY());
		 		text.append(",");
		 		text.append(r.getAxis().getZ());
		 		text.append("\n");
			}
		 	for(int i = 0; i < spheres.size(); i++){
		 		Translate t = (Translate)spheres.get(i).getTransforms().get(0);
		 		Rotate r = (Rotate)spheres.get(i).getTransforms().get(0);
		 		text.append("Sphere");
		 		text.append(",");
		 		text.append(spheres.get(i).getRadius());
		 		text.append(",");
		 		text.append(spheres.get(i).getScaleX());
		 		text.append(",");
		 		text.append(spheres.get(i).getScaleY());
		 		text.append(",");
		 		text.append(spheres.get(i).getScaleZ());
		 		text.append(",");
		 		text.append(t.getX());
		 		text.append(",");
		 		text.append(t.getY());
		 		text.append(",");
		 		text.append(t.getZ());
		 		text.append(",");
		 		text.append(r.getAxis().getX());
		 		text.append(",");
		 		text.append(r.getAxis().getY());
		 		text.append(",");
		 		text.append(r.getAxis().getZ());
		 		text.append("\n");
		 	}
		 	
		 	for(int i = 0; i < cylinders.size(); i++){
		 		Translate t = (Translate)cylinders.get(i).getTransforms().get(0);
		 		Rotate r = (Rotate)cylinders.get(i).getTransforms().get(0);
		 		text.append("Cylinder");
		 		text.append(",");
		 		text.append(cylinders.get(i).getRadius());
		 		text.append(",");
		 		text.append(cylinders.get(i).getHeight());
		 		text.append(",");
		 		text.append(cylinders.get(i).getScaleX());
		 		text.append(",");
		 		text.append(cylinders.get(i).getScaleY());
		 		text.append(",");
		 		text.append(cylinders.get(i).getScaleZ());
		 		text.append(",");
		 		text.append(t.getX());
		 		text.append(",");
		 		text.append(t.getY());
		 		text.append(",");
		 		text.append(t.getZ());
		 		text.append(",");
		 		text.append(r.getAxis().getX());
		 		text.append(",");
		 		text.append(r.getAxis().getY());
		 		text.append(",");
		 		text.append(r.getAxis().getZ());
		 		text.append("\n");
		 	}
		 	bw.write(text.toString());
	 		text.setLength(0);
		 	bw.close();
		 	return true;
		}catch(IOException e){
			return false;
		}
	}	
	
	public void printTranslate(Box b){
		Translate t = (Translate) b.getTransforms().get(0);
		t.setX(100);
		System.out.println(b.getScaleX());
		System.out.println(b.getScaleY());
		//.out.println(t.getZ());
	}
	
	
}
