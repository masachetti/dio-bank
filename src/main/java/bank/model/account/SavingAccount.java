package bank.model.account;

import bank.model.client.Client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SAVING")
public class SavingAccount extends Account{
    public SavingAccount() {
    }

    public SavingAccount(Integer agency, Client client) {
        super(agency, client);
    }

    public String toString(){
        return "Saving Account{" + super.getDescriptionString() + '}';
    }
}
