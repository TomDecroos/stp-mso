package benchmarks.data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import basic.Point;

public class DataReader {
	public static String BENCHMARKLOCATION = "src/benchmarks/data/";
	public static String BENCHMARKNAME = "estein";
	public Point[][] data;
	public DataReader(int size) {
		try {
			readData(BENCHMARKLOCATION + BENCHMARKNAME + size +".txt");
		} catch (NumberFormatException e) {
			System.err.println("Could not convert to double/int");
		} catch (IOException e) {
			System.err.println("Could not read file");
		}
	}
	
	public DataReader(String file) throws NumberFormatException, IOException {
		readData(file);
	}
	
	
	private void readData(String file) throws NumberFormatException, IOException {
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		int nbOfProblems = Integer.valueOf(br.readLine().trim());
		data = new Point[nbOfProblems][];
		for(int i=0;i<nbOfProblems;i++) {
			int nbOfPoints = Integer.valueOf(br.readLine().trim());
			data[i] = new Point[nbOfPoints];
			for(int j=0;j<nbOfPoints;j++) {
				String[] coordinates=  br.readLine().trim().split(" ");
				//System.out.println(coordinates[0]);
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				data[i][j] = new Point(x,y);
			}
		}
		br.close();
	}
	
	public static void printData(int size) throws NumberFormatException, IOException {
		String file = BENCHMARKLOCATION + BENCHMARKNAME + size +".txt";
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		int nbOfProblems = Integer.valueOf(br.readLine().trim());
		Path dir = Paths.get("instances/stp" + size);
		Files.createDirectory(dir);
		for(int i=0;i<nbOfProblems;i++) {
			PrintWriter writer = new PrintWriter(dir.toString() + "/stp" + size + ((char) (97 + i)), "UTF-8");
			int nbOfPoints = Integer.valueOf(br.readLine().trim());
			writer.println(nbOfPoints);
			for(int j=0;j<nbOfPoints;j++) {
				writer.println(br.readLine());
			}
			writer.close();
		}
		br.close();
	}
	
	public Point[] getPoints(int index) {
		return data[index];
	}

}
