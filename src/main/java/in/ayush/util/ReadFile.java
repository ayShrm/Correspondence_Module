package in.ayush.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFile {

	public static String readMailBody(String fullName, String fileName) {
		String mailBody = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			StringBuffer buffer = new StringBuffer();

			String line = br.readLine();
			while (line != null) {
				buffer.append(line);
				line = br.readLine();
			}
			br.close();
			mailBody = buffer.toString();
			mailBody = mailBody.replace("{FULLNAME}", fullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

}
