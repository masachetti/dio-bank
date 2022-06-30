package bank.model.account;

import bank.model.client.Client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHECKING")
public class CheckingAccount extends Account{
    public CheckingAccount() {
    }

    public CheckingAccount(Integer agency, Client client) {
        super(agency, client);
    }

    public String toString(){
        return "Checking Account{" + super.getDescriptionString() + '}';
    }
}
