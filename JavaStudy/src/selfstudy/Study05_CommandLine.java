package selfstudy;

public class Study05_CommandLine {
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		if (args.length != 0) {
			for (String arg : args) {
				counter.count(arg);
			}
			counter.print();
		} else {
			System.out.println("No args provided!");
		}
	}
}

class Counter {
	private double sum, min, max;
	
	public Counter() {
		sum = 0.0;
		min = Double.MAX_VALUE;
		max = Double.MIN_VALUE;
	}
	
	public <T extends Number> void count(T i) {
		double num = (Double) i;
		sum += num;
		if (num < min) {
			min = num;
		}
		if (num > max) {
			max = num;
		}
	}

	public <T extends String> void count(T i) {
		double num;
		try {
			num = Double.parseDouble(i);
		} catch (Exception e) {
			num = 0.0;
		}
		count(num);
	}
	
	public void print() {
		System.out.println("SUM = " + sum + "; MIN = " + min + "; MAX = " + max);
	}
}
