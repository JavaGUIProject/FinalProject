import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Point3D;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ShapeSaver {
	public static void main(String[] args) {
		ShapeSaver saver = new ShapeSaver();
		Box b = new Box(0, 0, 0);
		b.getTransforms().add(new Translate(1, 1, 1));
		Rotate r = new Rotate();
		r.setAxis(new Point3D(5, 5, 5));
		b.getTransforms().add(r);
		saver.saveBox(b);
		saver.saveBox(b);
		saver.saveAll("C:\\Users\\Shant\\Desktop\\proba.txt");
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
		 		Rotate r = (Rotate)boxes.get(i).getTransforms().get(1);
		 		text.append("Box");
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
		 		text.append(System.getProperty("line.separator"));
			}
		 	for(int i = 0; i < spheres.size(); i++){
		 		Translate t = (Translate)spheres.get(i).getTransforms().get(0);
		 		Rotate r = (Rotate)spheres.get(i).getTransforms().get(1);
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
		 		text.append(System.getProperty("line.separator"));
		 	}
		 	
		 	for(int i = 0; i < cylinders.size(); i++){
		 		Translate t = (Translate)cylinders.get(i).getTransforms().get(0);
		 		Rotate r = (Rotate)cylinders.get(i).getTransforms().get(1);
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
		 		text.append(System.getProperty("line.separator"));
		 	}
		 	bw.write(text.toString());
	 		text.setLength(0);
		 	bw.close();
		 	return true;
		}catch(IOException e){
			return false;
		}
	}	
	
	public boolean loadAll(String path){
		FileReader reader;
		try {
			reader = new FileReader(path);
			BufferedReader br = new BufferedReader(reader);
			String text;
			while((text =br.readLine()) != null){
				String[] texts = text.split(",");
				if(texts[0].equals("Box")){
					Box box = new Box();
					Translate t = new Translate();
					Rotate r = new Rotate();
					box.setHeight(Double.parseDouble(texts[1]));
					box.setWidth(Double.parseDouble(texts[2]));
					box.setDepth(Double.parseDouble(texts[3]));
					box.setScaleX(Double.parseDouble(texts[4]));
					box.setScaleY(Double.parseDouble(texts[5]));
					box.setScaleZ(Double.parseDouble(texts[6]));
					t.setX(Double.parseDouble(texts[7]));
					t.setY(Double.parseDouble(texts[8]));
					t.setZ(Double.parseDouble(texts[9]));
					r.setAxis(new Point3D(Double.parseDouble(texts[10]), Double.parseDouble(texts[11]), Double.parseDouble(texts[12])));
					box.getTransforms().add(t);
					box.getTransforms().add(r);
					
				}
				else if(texts[0].equals("Sphere")){
					Sphere sphere = new Sphere();
					Translate t = new Translate();
					Rotate r = new Rotate();
					sphere.setRadius(Double.parseDouble(texts[1]));
					sphere.setScaleX(Double.parseDouble(texts[2]));
					sphere.setScaleY(Double.parseDouble(texts[3]));
					sphere.setScaleZ(Double.parseDouble(texts[4]));
					t.setX(Double.parseDouble(texts[5]));
					t.setY(Double.parseDouble(texts[6]));
					t.setZ(Double.parseDouble(texts[7]));
					r.setAxis(new Point3D(Double.parseDouble(texts[8]), Double.parseDouble(texts[9]), Double.parseDouble(texts[10])));
					sphere.getTransforms().add(t);
					sphere.getTransforms().add(r);
				}
				else if(texts[0].equals("Cylinder")){
					Cylinder cylinder = new Cylinder();
					Translate t = new Translate();
					Rotate r = new Rotate();
					cylinder.setRadius(Double.parseDouble(texts[1]));
					cylinder.setHeight(Double.parseDouble(texts[2]));
					cylinder.setScaleX(Double.parseDouble(texts[3]));
					cylinder.setScaleY(Double.parseDouble(texts[4]));
					cylinder.setScaleZ(Double.parseDouble(texts[5]));
					t.setX(Double.parseDouble(texts[6]));
					t.setY(Double.parseDouble(texts[7]));
					t.setZ(Double.parseDouble(texts[8]));
					r.setAxis(new Point3D(Double.parseDouble(texts[9]), Double.parseDouble(texts[10]), Double.parseDouble(texts[11])));
					cylinder.getTransforms().add(t);
					cylinder.getTransforms().add(r);
				}
				else{
					return false;
				}
			}
			br.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e){
			return false;
		} catch (NumberFormatException e){
			return false;
		} catch (Exception e){
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
