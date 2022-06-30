//package bank.model.transaction;
//
//import bank.model.account.Account;
//
//import javax.persistence.Column;
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Entity;
//
//@Entity
//@DiscriminatorValue("TRANSFER")
//public class Transfer extends Transaction{
//
//    @Column(name = "receiver_account")
//    private Account receiverAccount;
//
//    public Transfer(Account account, Double value, Account receiverAccount) {
//        super(account, value);
//        this.receiverAccount = receiverAccount;
//    }
//
//    @Override
//    public boolean performTransaction() {
//        return false;
//    }
//
//    public Account getReceiverAccount() {
//        return receiverAccount;
//    }
//
//    public void setReceiverAccount(Account receiverAccount) {
//        this.receiverAccount = receiverAccount;
//    }
//}
