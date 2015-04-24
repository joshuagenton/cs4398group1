package simpleAccount.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.SortedMap;

import simpleAccount.model.AccountModel;
import simpleAccount.view.AccountView;
import simpleAccount.view.ErrorView;
import simpleAccount.view.JFrameView;
import simpleAccount.view.TransferView;

public class AccountController extends AbstractController {
	private String datafile;
	
	public AccountController(){
		setModel(new AccountModel());
		//  View is instantiated after the data file is successfully read in
		//((JFrameView)getView()).setVisible(true);
	}
	
	public void operation(String option, String id){
		System.out.println("Operation: " + option);
		if(option.equals("Edit in USD")){
			TransferView transferView = new TransferView((AccountModel)getModel(), this);
			transferView.setValues(id, "USD");
			((AccountModel)getModel()).refresh();
		}else if(option.equals("Edit in Euros")){
			TransferView transferView = new TransferView((AccountModel)getModel(), this);
			transferView.setValues(id, "Euros");
			((AccountModel)getModel()).refresh();
		}else if(option.equals("Edit in Yuan")){
			TransferView transferView = new TransferView((AccountModel)getModel(), this);
			transferView.setValues(id, "Yuan");
			((AccountModel)getModel()).refresh();
		}else if(option.equals("Save") || option.equals("Exit")){
			WriteFile();
		}else {
			((AccountModel)getModel()).store(Integer.parseInt(option));
		}
	}
	
	public void operationTransfer(String id, String currency, Double amount){
		Double transAmount = amount;
		if (currency.equals("Euros"))
			transAmount = amount / 0.92;
		else if (currency.equals("Yuan"))
			transAmount = amount / 6.23;
		try {
			((AccountModel)getModel()).deposit(id, currency, transAmount);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			ErrorView errorView = new ErrorView((AccountModel)getModel(), this);
			errorView.setError(ex.getMessage());
		}
	}
	
	public Boolean ReadFile (String file) {
		this.datafile = file;
		int lineCount = 0;
        String line, name, id;
        double amount;
        HashMap<String, Object> account;
        
        // read and parse the file
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(datafile)));
            
            // read through the first two lines to clear out unneeded data
            line = br.readLine();
            line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                if (line.contains("|")) {
                    // do line by line parsing here
                    line = line.trim();
                    // split the line
                    String[] parts = line.split("[|]");
                    // parse out the name and id
                    name = parts[0].trim();
                    id = parts[1].trim();
                    try {
                        amount = Double.parseDouble(parts[2].trim().substring(1));
                    } catch (NumberFormatException e) {
                    	System.out.println("Data error in file " + datafile + " at line " + (lineCount + 3) + " " + id + " " + name);
                    	return false;
                    }
                    // all done now, let's print the name and id
                    //System.out.println(name + " " + id + " " + amount);
                    account = new HashMap<String, Object>();
                    account.put("id", id);
                    account.put("name", name);
                    account.put("amount", amount);
                    ((AccountModel)getModel()).AddAccount(account);
                    lineCount++;
                }
            }
            //((AccountModel)getModel()).PrintAccounts();
            
            br.close();
            
        } catch (Exception e) {
            System.out.println("Unable to read the file " + datafile);
            //System.out.println(e);
            return false;
        }
        
        if (lineCount == 0) {
        	System.out.println("No data found in file " + datafile);
        	return false;
        }

		setView(new AccountView((AccountModel)getModel(), this));
        ((AccountModel)getModel()).store(1);
        return true;
	}
	
	public Boolean WriteFile () {
		SortedMap<Integer, HashMap> accounts = ((AccountModel)getModel()).getAccounts();
        // write the file
        try {
            String name = "", id = "";
            double amount = 0;
            
            FileOutputStream fileoutput = new FileOutputStream(datafile);
            PrintWriter pw = new PrintWriter(fileoutput);
            pw.printf("    name          | id     |   amount %n");
            pw.printf("--------------------------------------%n");
            
            for (HashMap<String, Object> account : accounts.values()) {
                pw.printf(" %-16s | %-6s | $%-11s%n", account.get("name"), account.get("id"), String.format("%.2f", account.get("amount")));
            }
            pw.close();
            
        } catch (Exception e) {
            System.out.println("Unable to write the file " + datafile);
        }
        return true;
	}

}