package selfstudy;

public class Study05_CommandLine {
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		if (args.length != 0) {
			for (int i = 0; i < args.length; i++) {
				counter.count(args[i]);
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
	
	public void count(double i) {
		sum += i;
		if (i < min) {
			min = i;
		}
		if (i > max) {
			max = i;
		}
	}
	
	public void count(int i) {
		count((double) i);
	}
	
	public void count(float i) {
		count((float) i);
	}
	
	public void count(String i) {
		try {
			count(Double.parseDouble(i));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void print() {
		System.out.println("SUM = " + sum + "; MIN = " + min + "; MAX = " + max);
	}
}
