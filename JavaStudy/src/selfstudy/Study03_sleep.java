package selfstudy;

public class Study03_sleep {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("I = " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
