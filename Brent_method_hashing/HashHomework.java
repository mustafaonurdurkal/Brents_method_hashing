import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HashHomework {

	public static void main(String[] args) {
		BrentHashMap<Integer, String, Integer> bhm = new BrentHashMap<>(5000);

		try {
		File file = new File("C:/Users/MT-PC/Desktop/story.txt");
		FileReader fileReader = new FileReader(file);
		String line;

		BufferedReader br = new BufferedReader(fileReader);
		while ((line = br.readLine()) != null) {
			String[] words = line.split(" ");
			for (int i = 0; i < words.length; i++) {
				String kelime = words[i].toLowerCase();
				if (!kelime.equals("")) {
					int haslanmis = asciPurpose(kelime);
					bhm.put(haslanmis, kelime);
				}
			}
		}
		bhm.sinep();
		String input;
		System.out.println("Search:");
		Scanner scan = new Scanner(System.in);
		input=scan.nextLine();
		input = input.toLowerCase();
		int inputHash=0;
		for(int i = 0;i<input.length();i++)
		{
			inputHash=inputHash + (input.charAt(i) * 93);
			
		}
		
		bhm.search(inputHash,input);
		
		br.close();
		}catch(IOException e) {
			System.out.println("RED ALERT SYSTEM DOWN");
		}
	}

	
	public static int asciPurpose(String a) {
		int optimusPrime =93;
		int hash =0 ;
		for (int i = 0; i < a.length(); i++) {
			hash += a.charAt(i) * optimusPrime;
		}
		return hash;
	}

}