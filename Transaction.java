import java.util.Date;

public class Transaction
{
   
   //The amount of the transaction
   private double amount;
   
   //The time and date the transaction happened
   private Date timestamp;
   
   //A memo for the transaction
   private String memo;
   
   //The account in which the transaction was performed
   private Account inAccount;
   
   /**
     Create a new Transaction
     amount    = the amount transacted
     inAccount = the account the transaction belongs to
   */
   
   public Transaction(double amount, Account inAccount)
   {
      this.amount = amount;
      this.inAccount = inAccount;
      this.timestamp = new Date();
      this.memo = "";
   }
   
   /**
      Create a new Transaction
      amount     = the amount transacted
      memo       = the memo for the transaction
      inAccount  = the account the transaction belongs to
   */
   
   public Transaction(double amount, String memo, Account inAccount)
   {
      //call the two-arg constructor first
      this(amount, inAccount);
      
      //set the memo
      this.memo = memo;
   }
   
   //Get the amount of the transaction
   public double getAmount()
   {
      return this.amount;
   }
   
   //Get a string summarizing the transaction
   public String getSummaryLine()
   {
      if (this.amount >= 0)
      {
         return String.format("%s : $%.02f : %s", this.timestamp.toString(),
                              this.amount, this.memo);
      }
      else
      {
         return String.format("%s : $(%.02f) : %s", this.timestamp.toString(),
                              -this.amount, this.memo);
      }
   }
}