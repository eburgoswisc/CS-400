import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintPractice {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("output.txt");
		PrintWriter pw = new PrintWriter(f);
		String message = "Hello World";
		String s1 = String.format("output: %10d %7.2f %s", 123, 3.1415927, message);
		String s2 = "1 + 2 = " + (1 + 2) + "\nGood-Bye!";
		pw.print(s1);
		pw.println(s2);
		pw.printf("Le Fin %10.4f\n", Math.PI);
		pw.close();
	}

}
