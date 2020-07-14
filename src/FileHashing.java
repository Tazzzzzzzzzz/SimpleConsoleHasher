import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.security.MessageDigest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHashing {

	public static void main(String[] args) throws Exception {

		Scanner menu = new Scanner(System.in); //Input Scan
		while (true) {
			System.out.println("________________________________");
			System.out.println("File Hasher");
			System.out.println("________________________________");
			System.out.println("1. MD5 Hashing Text");
			System.out.println("2. SHA - 1 Hashing Text");
			System.out.println("3. SHA - 256 Hashing Text");
			System.out.println("4. File Hashing");
			System.out.println("5. View all Hashes");
			System.out.println("6. Exit");
			System.out.println("________________________________");
			System.out.println("Enter Your option? ");
			if (menu.hasNextInt()) {
				int number = menu.nextInt();

				if (number == 1) { // if statement jumps to function depending on number input
					MD5HashFunction();
				} else if (number == 2) {
					SHA1HashFunction();
				} else if (number == 3) {
					SHA256HashFunction();
				} else if (number == 4) {
					FileHashings();
				}else if (number == 5)
				{
					displayfile();
					
				} else if (number == 6) {
					System.out.println("***Program End***");
					System.exit(0); // exits program

				}

			} else {
				System.out.println("Please input a valid number"); //validation of invalid input
			}

		}

	}

	private static void displayfile() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hashes.txt"));
		String line;
		while((line = in.readLine()) != null)
		{
		    System.out.println(line);
		}
		in.close();
		
		
		
	}

	public static void MD5HashFunction() throws Exception {
		System.out.println("Enter Your String you want to hash? "); 

		Scanner input = new Scanner(System.in);
		String inputstring = input.next();
		System.out.println("You have Entered: " + inputstring);

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(inputstring.getBytes());

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(byteData[i] & 0xff);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		System.out.println("The MD5 Hash: " + hexString.toString());

		File md5hash = new File("hashes.txt");
		PrintWriter writer = new PrintWriter(new FileWriter(md5hash, true));

		for (int i = 0; i < 1; i++) {
			if (readFile("hashes.txt").indexOf(hexString.toString()) == -1) {
				writer.println(hexString.toString());
				System.out.println("Hash saved");
			} else {
				System.out.println("Hash already there!");
			}
		}

		writer.close();

	}

	public static void SHA256HashFunction() throws Exception {
		System.out.println("Enter Your String you want to hash? ");
		Scanner input = new Scanner(System.in);
		String inputstring = input.nextLine();

		System.out.println("You have Entered: " + inputstring);

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(inputstring.getBytes());

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(byteData[i] & 0xff);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		System.out.println("The SHA - 256 Hash: " + hexString.toString());
		File sha256 = new File("hashes.txt");
		PrintWriter writer = new PrintWriter(new FileWriter(sha256, true));

		for (int i = 0; i < 1; i++) {
			if (readFile("hashes.txt").indexOf(hexString.toString()) == -1) {
				writer.println(hexString.toString());
				System.out.println("Hash saved");
			} else {
				System.out.println("Hash already there!");
			}
		}
		writer.close();
	}

	public static void SHA1HashFunction() throws Exception {
		System.out.println("Enter Your String you want to hash? ");
		Scanner input = new Scanner(System.in);
		String inputstring = input.nextLine();

		System.out.println("You have Entered: " + inputstring);

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(inputstring.getBytes());

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(byteData[i] & 0xff);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		System.out.println("The SHA - 1 Hash: " + hexString.toString());

		File sha1 = new File("hashes.txt");
		PrintWriter writer = new PrintWriter(new FileWriter(sha1, true));

		for (int i = 0; i < 1; i++) {
			if (readFile("hashes.txt").indexOf(hexString.toString()) == -1) {
				writer.println(hexString.toString());
				System.out.println("Hash saved");
			} else {
				System.out.println("Hash already there!");
			}
		}
		writer.close();
	}

	public static void FileHashings() throws Exception {
		String hash = null;
		System.out.println("1.MD5 File Hash");
		System.out.println("2.SHA-1 File Hash");
		System.out.println("3.SHA-256 File Hash");
		System.out.println("How do you want to hash your file?:  ");

		Scanner input2 = new Scanner(System.in);
		int number = input2.nextInt();

		if (number == 1)
			hash = ("MD5");
		else if (number == 2)
			hash = ("SHA-1");
		else if (number == 3)
			hash = ("SHA-256");
		else {
			System.out.println("Invalid Input");
			return;
		}
		String filepath = null;
		MessageDigest md = MessageDigest.getInstance(hash);
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("Choose the file you wish to hash. ");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			filepath = fc.getSelectedFile().getAbsolutePath();
		}
	
		

		FileInputStream fis = new FileInputStream(filepath);
		byte[] dataBytes = new byte[1024];
		int nread = 0;
		while ((nread = fis.read(dataBytes)) != -1) {
			// md is the MessageDigest instance
			md.update(dataBytes, 0, nread);
		}
		;

		byte byteData[] = md.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(byteData[i] & 0xff);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		System.out.println("The File Hash is: " + hexString.toString());
		File fileh = new File("hashes.txt");
		PrintWriter writer = new PrintWriter(new FileWriter(fileh, true));

		for (int i = 0; i < 1; i++) {
			if (readFile("hashes.txt").indexOf(hexString.toString()) == -1) {
				writer.println(hexString.toString());
				System.out.println("Hash saved");
			} else {
				System.out.println("Hash already there!");
			}
		}
		writer.close();
	}

	private static String readFile(String pathname) throws Exception {

		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}

}