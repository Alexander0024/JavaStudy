package selfstudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Study06_IO {
	public static void main(String[] args) {
		Reader reader = new Reader();
		if (args.length == 0) {
			try {
				reader.readInteger();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (args[0].contains("char") || args[0].contains("CHAR")) {
				reader.readChar();
			} else {
				reader.readLine();
			}
		}
	}
}

class Reader {
	private String[] user;
	private double[] numbers;
	private List<String> counts = new ArrayList<>();
	
	public void readChar() {
		char c = 0;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input words, exit with 'q'");
		do {
			try {
				c = (char) bufferedReader.read();
			} catch (IOException e) {
				System.out.println("Input IOException" + e.getMessage());
			}
			System.out.println(c);
		} while (c != 'q');
	}
	
	public void readLine() {
		String str = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input string, exit with 'exit'");
		do {
			try {
				str = bufferedReader.readLine();
			} catch (IOException e) {
				System.out.println("Input IOException" + e.getMessage());
			}
		} while (!str.equals("exit"));
	}
	
	public void readInteger() throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int id;
		double value;
		System.out.println("Input users split with blank space");
		str = bufferedReader.readLine();
		user = str.split(" ");
		if (user.length == 0) {
			return;
		}
		numbers = new double[user.length];
		do {
			System.out.println("======================================");
			System.out.println("Input id and money, exit with 'q'");
			System.out.println("ID\tUser\tMoney");
			for (int i = 0; i < user.length; i++) {
				System.out.println(i + "\t" + user[i] + "\t" + numbers[i]);
			}
			System.out.println("======================================");
			str = bufferedReader.readLine();
			if (str.split(" ").length < 2) {
				System.err.println("Wrong input! IGNORE");
				continue;
			}
			id = Integer.parseInt(str.split(" ")[0]);
			value = Double.parseDouble(str.split(" ")[1]);
			if (id >= user.length) {
				System.err.println("Wrong input! IGNORE");
				continue;
			}
			numbers[id] += value;
			counts.add(str);
		} while (!str.equals('q'));
		System.out.println("======================================");
		System.out.println("ID\tUser\tMoney");
		for (int i = 0; i < user.length; i++) {
			System.out.println(i + "\t" + user[i] + "\t" + numbers[i]);
		}
		System.out.println("======================================");
		for (String item : counts) {
			System.out.println(item);
		}
		System.out.println("======================================");
	}
}