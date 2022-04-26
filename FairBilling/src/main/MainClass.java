package main;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String path = sc.next();
		BuisnessLogic bs = new BuisnessLogic();
		Map<String, List<Long>> output =  bs.processLogs(path);
		
		for (Map.Entry<String, List<Long>> out : output.entrySet()) {
			List<Long> values = out.getValue();
			System.out.println(out.getKey() + " " + values.get(0) + " " + values.get(1) / 1000);
		}
		sc.close();
	}
	
}
