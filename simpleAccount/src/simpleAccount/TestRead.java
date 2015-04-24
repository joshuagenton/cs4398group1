package simpleAccount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TestRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String myFilePath = "testfileHW4.txt";
        myFilePath = args[0];
        

        // read and parse the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(myFilePath)));
            String line, name, id;
            double amount;
            
            FileOutputStream fileoutput = new FileOutputStream("output.txt");
            PrintWriter pw = new PrintWriter(fileoutput);
            pw.printf("    name          | id     |   amount %n");
            pw.printf("--------------------------------------%n");
            //pw.printf("%6d\t%30s%d\t%d%n");
            //pw.printf("%6d\t%30s%d\t%d%n");
            
            // read through the first two lines to get to the data
            line = br.readLine();
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.contains("|")) {
                    // do line by line parsing here
                    line = line.trim();
                    // split the line
                    String[] parts = line.split("[|]");
                    // parse out the name and email
                    name = parts[0].trim();
                    id = parts[1].trim();
                    amount = Double.parseDouble(parts[2].trim().substring(1));
                    // all done now, let's print the name and email
                    System.out.println(name + " " + id + " " + amount);
                    pw.printf(" %-16s | %-6s | $%-11s%n", name, id, String.format("%.2f", amount));
                }
            }
            br.close();
            pw.close();
        } catch (Exception e) {
            System.out.println("Unable to read the file " + myFilePath);
        }
	}

}
