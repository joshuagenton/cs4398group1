package simpleAccount.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.SortedMap;

import javax.swing.SwingUtilities;

import simpleAccount.model.Account;
import simpleAccount.model.AgentModel;
import simpleAccount.view.AccountView;
import simpleAccount.view.AgentView;
import simpleAccount.view.ErrorView;
import simpleAccount.view.JFrameView;
import simpleAccount.view.TransferView;

/**
 * This is the main AccountController for both the accounting and main views.
 * This contains the operations they perform.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public class AccountController extends AbstractController {
	private String datafile;
	
	/**
	 * AccountController Constructor.
	 */
	public AccountController(){
		setModel(new Account());
		//  View is instantiated after the data file is successfully read in
		//((JFrameView)getView()).setVisible(true);
	}
	
	/**
	 * This is the main operation operation() from the main account view.  This is a list
	 * of actions the user can select from.
	 * @param option the user's selection
	 * @param id the user's ID
	 */
	public void operation(String option, String id){
		System.out.println("Operation: " + option);
		if(option.equals("Edit in USD")){
			TransferView transferView = new TransferView((Account)getModel(), this);
			transferView.setValues(id, "USD");
			((Account)getModel()).refresh();
		
		} else if(option.equals("Edit in Euros")){
			TransferView transferView = new TransferView((Account)getModel(), this);
			transferView.setValues(id, "Euros");
			((Account)getModel()).refresh();
		
		} else if(option.equals("Edit in Yuan")){
			TransferView transferView = new TransferView((Account)getModel(), this);
			transferView.setValues(id, "Yuan");
			((Account)getModel()).refresh();
		
		} else if(option.equals("Create deposit agent")){
			AgentView agentView = new AgentView((Account)getModel(), this);
			agentView.setValues(id, "deposit");
			((Account)getModel()).refresh();
		
		} else if(option.equals("Create withdraw agent")){
			AgentView agentView = new AgentView((Account)getModel(), this);
			agentView.setValues(id, "withdraw");
			((Account)getModel()).refresh();
		
		} else if(option.equals("Save") || option.equals("Exit")){
			WriteFile();
		
		} else {
			((Account)getModel()).store(Integer.parseInt(option));
		}
	}
	
	/**
	 * This is the account operation operationTransfer() from the edit currency view. 
	 * This is a list of the actions the user can select from.
	 * @param id the user's ID
	 * @param currency the currency in which the user is editing in
	 * @param amount the amount the user would like to deposit/withdraw
	 */
	public void operationTransfer(String id, String currency, Double amount) {
		Double transAmount = amount;
		if (currency.equals("Euros"))
			transAmount = amount / 0.92;
		else if (currency.equals("Yuan"))
			transAmount = amount / 6.23;
		try {
			((Account)getModel()).deposit(id, currency, transAmount);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			ErrorView errorView = new ErrorView((Account)getModel(), this);
			errorView.setError(ex.getMessage());
		}
	}
	

	
	public synchronized void SetAgent (String accountID, double amount, double ops, String agentID, Boolean agentRunning, String agentStatus) {
		((Account)getModel()).SetAgent(accountID, amount, ops, agentID, agentRunning, agentStatus);
	}
	
	
	
	/**
	 * This is the ReadFile() function that is called to read in the text file
	 * that the system is interacting with.  The file contains an account number,
	 * a user name, and a total.
	 * @param file location of the file
	 * @return boolean if the read was successful
	 */
	public boolean ReadFile (String file) {
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
                    ((Account)getModel()).AddAccount(account);
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

		setView(new AccountView((Account)getModel(), this));
        ((Account)getModel()).store(1);
        return true;
	}
	
	/**
	 * This is the WriteFile() function that writes back out the data the user
	 * has been interacting with.  It contains all changes that were made to the 
	 * account balances.
	 * @return boolean if the write was successful
	 */
	public boolean WriteFile () {
		SortedMap<Integer, HashMap> accounts = ((Account)getModel()).getAccounts();
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
