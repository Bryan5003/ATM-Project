import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User
{
   private String firstName;
   
   private String lastName;
   
   // The ID number of the user
   private String uuid;
   
   // The MD5 hash of the user's pin number
   private byte pinHash[];
   
   private ArrayList<Account> accounts;
   
   /** 
      Create a new User
      firstName = the user's first name
      lastName  = the user's last name
      pin       = the user's account pin number
      theBank   = the Bank object that the user is a customer of
   */
   
   public User(String firstName, String lastName, String pin, Bank theBank)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      
      // store the pin's MD5 hash, instead of the original value for security
      try 
      {
      
      MessageDigest md = MessageDigest.getInstance("MD5");
      this.pinHash = md.digest(pin.getBytes());
      
      } 
      catch (NoSuchAlgorithmException e) 
        {
           System.err.println("error, caught NoSuchAlgorithmException");
           e.printStackTrace();
           System.exit(1);
        }
        
        //get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();
        
        //create empty list of accounts
        this.accounts = new ArrayList<Account>();
        
        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
 
   }
   
   /**
      Add an account for the user
      anAcct   the account to add
   */
   
   public void addAccount(Account anAcct)
   {
      this.accounts.add(anAcct);
   }
   
   //return the user's UUID 
   public String getUUID()
   {
      return this.uuid;
   }
   
      /**
         Check if a given pin matches the User's true pin
         aPin   = the pin to check
         return = if the pin is valid or not
      */
      
      public boolean validatePin(String aPin)
      {
         try
         {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
         } 
         catch (NoSuchAlgorithmException e) 
         {
           System.err.println("error, caught NoSuchAlgorithmException");
           e.printStackTrace();
           System.exit(1);
         }
         
         return false;
      }
      
      //returns the user's first name
      public String getFirstName()
      {
         return this.firstName;
      }
      
      //print summaries for the accounts of this user
      public void printAccountsSummary()
      {
         System.out.printf("\n\n%s's accounts summary\n", this.firstName);
         for (int a = 0; a < this.accounts.size(); a++)
         {
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine()); 
         }
         System.out.println();
      }
      
      //Get the number of accounts of the user
      public int numAccounts()
      {
         return this.accounts.size();
      }
      
      //Print transaction history for a particular account
      public void printAcctTransHistory(int acctIdx)
      {
         this.accounts.get(acctIdx).printTransHistory();
      }
      
      //Get the balance of a particular account
      //acctIdx = the index of the account to use
      public double getAcctBalance(int acctIdx)
      {
         return this.accounts.get(acctIdx).getBalance();
      }
      
      //get the UUID of a particular account
      public String getAcctUUID(int acctIdx)
      {
         return this.accounts.get(acctIdx).getUUID();
      }
      
      //add a transaction to a particular account
      public void addAcctTransaction(int acctIdx, double amount, String memo)
      {
         this.accounts.get(acctIdx).addTransaction(amount, memo);
      }
   
}