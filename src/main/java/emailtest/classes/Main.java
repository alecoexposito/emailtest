package emailtest.classes;

public class Main {

	public static void main(String[] args) {
		System.out.println("Sending email...");
		TestEmail testEmail = new TestEmail();
		testEmail.sendEmail("hamlethabana84@gmail.com");
		System.out.println("Email sent");

	}

}
