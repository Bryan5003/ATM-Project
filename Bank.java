import java.util.ArrayList;
import java.util.Random;

public class Bank
{
   private String name;
   
   private ArrayList<User> user;
   
   private ArrayList<Account> accounts;
   
   
  
   //Create a new Bank object with empty lists of users and accounts
      
   public Bank(String name)
   {
      this.name = name;
      this.user = new ArrayList<User>();
      this.accounts = new ArrayList<Account>();
   }
   
   /**
   Generate a new universally unique ID for a user
   the uuid
   */
   
   public String getNewUserUUID()
   {
      //inits
      String uuid;
      Random rng = new Random();
      int leng = 4;
      boolean nonUnique;
      
      //continue looping until we get a unique ID
      do 
      {
         //generate Number
         uuid = "";
         for (int c = 0; c < leng; c++)
         {
            uuid += ((Integer)rng.nextInt(10)).toString();
            
         }
         
         //check to make sure it is unique
         nonUnique = false; 
         for (User u : this.user)
         {
            if(uuid.compareTo(u.getUUID()) == 0)
            {
               nonUnique = true;
               break;
            }
         }
         
      }
      while (nonUnique);
      
      return uuid;
   }
   
   
   //Generate a new universally unique ID for an account
   
   public String getNewAccountUUID()
   {
      //inits
      String uuid;
      Random rng = new Random();
      int leng = 10;
      boolean nonUnique;
      
      //continue looping until we get a unique ID
      do 
      {
         //generate Number
         uuid = "";
         for (int c = 0; c < leng; c++)
         {
            uuid += ((Integer)rng.nextInt(10)).toString();
            
         }
         
         //check to make sure it is unique
         nonUnique = false; 
         for (Account a : this.accounts)
         {
            if(uuid.compareTo(a.getUUID()) == 0)
            {
               nonUnique = true;
               break;
            }
         }
         
      }
      while (nonUnique);
      
      return uuid;
   }
   
   //Add an account
   public void addAccount(Account anAcct)
   {
      this.accounts.add(anAcct);
   }
   
   /**
      Create a new user of the bank
      firstName = the user's first name
      lastName  = the user's last name
      pin       = the user's pin
      return      the User object
    */
    
   public User addUser(String firstName, String lastName, String pin)
   {
      //create a new User object and add it to the list
      User newUser = new User(firstName, lastName, pin, this);
      this.user.add(newUser);
      
      //create a savings account for the user and add to 
      //User and Bank accounts lists
      Account newAccount = new Account("Savings", newUser, this);
      newUser.addAccount(newAccount);
      this.accounts.add(newAccount);
      
      return newUser;
   }
   
   /**
   Get the User object associated with a userID and pin if they are valid
   userID = the UUID of the user to log in
   pin = the pin of the user
   return the User object, if the login is successful, or null if it is not
   */
   
   public User userLogin(String userID, String pin)
   {
      //search through list of users
      for (User u : this.user)
      {
         //check to see if user ID is correct
         if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin))
         {
            return u;
         }
      }
      //if no user is found or if the pin is incorrect
      return null;
   }
   
   //get the name of the bank and return the name
   public String getName()
   {
      return this.name;
   }
   
}