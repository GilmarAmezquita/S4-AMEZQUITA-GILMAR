import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	private static double[] array;
	private static ArrayList<Double> finalArray;
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		finalArray = new ArrayList<>();
		String[] arrayPartsStr = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tries = Integer.parseInt(br.readLine());

		double changes = 0;
		for(int i = 0; i<tries; i++) {
			arrayPartsStr = br.readLine().split(" ");
			array = new double[arrayPartsStr.length];
			for(int j = 0; j<arrayPartsStr.length; j++) {
				array[j] = Double.parseDouble(arrayPartsStr[j]);
			}
			reductArray();
			
			changes = bubbleSort();
			changes = formatDouble(changes);
			
			bw.write(changes+"-");
			bw.write(writeArray());
			bw.newLine();
			
			finalArray.clear();
		}
		br.close();
		bw.close();
	}
	
	public static double bubbleSort() {
		int totalChanges = 0;
		int series = 0;
		for(int i = array.length; i>1; i--) {
			for(int j = 0; j<i-1; j++) {
				if(array[j] > array[j+1]) {
					double temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					totalChanges++;
				}
			}
			series++;
		}
		return (double)totalChanges/series;
	}
	
	public static double formatDouble(double changes) {
		double result = changes - (int) changes;
		result *= 100;
		result = (int) result;
		result /= 100;
		result+= (int) changes;
		return result;
	}
	
	public static void reductArray() {
		for(int i = 1; i<array.length; i++) {
			if(array[i] == array[i-1]) {
				array[i-1] = -1;
			}
		}
		for(int i = 0; i<array.length; i++) {
			if(array[i] >= 0) {
				finalArray.add(array[i]);
			}
		}
	}
	
	public static String writeArray() {
		String str = "";
		int quantity = 0;
		while(quantity<finalArray.size()-1) {
			str += finalArray.get(quantity)+" ";
			quantity++;
		}
		str += finalArray.get(quantity);
		return str;
	}
}