import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class ShapeSaver {
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
		 		text.append("Box,");
		 		text.append(",");
		 		text.append(boxes.get(i).getHeight());
		 		text.append(",");
		 		text.append(boxes.get(i).getWidth());
		 		text.append(",");
		 		text.append(boxes.get(i).getDepth());
		 		text.append("\n");
		 		bw.write(text.toString());
		 		text.setLength(0);
			}
		 	bw.close();
		 	return true;
		}catch(IOException e){
			return false;
		}
	}
	
	
	
}
