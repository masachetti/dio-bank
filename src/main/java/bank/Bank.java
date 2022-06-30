package bank;

import bank.model.account.Account;
import bank.model.account.CheckingAccount;
import bank.model.account.SavingAccount;
import bank.model.client.Client;
import bank.model.client.Corporation;
import bank.model.client.Individual;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class Bank {
    @PersistenceContext(unitName = "Bank-PU")
    private final EntityManager entityManager;

    public Bank(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void printLine() {
        System.out.println("----------------------------------------------------------------------------------------");
    }

    public void showAllClients() {
        String jpqlQuery = "select c from Client c";
        try {
            List<Client> clientList = entityManager.createQuery(jpqlQuery, Client.class).getResultList();
            printLine();
            System.out.format("%10s %30s %20s %20s\n", "Client Id", "Name", "CPF", "CNPJ");
            printLine();
            clientList.forEach(client -> {
                String cpf = "Null";
                String cnpj = "Null";
                if (client instanceof Corporation) {
                    cnpj = ((Corporation) client).getCnpj();
                }
                else if (client instanceof Individual) {
                    cpf = ((Individual) client).getCpf();
                }
                System.out.format("%10s %30s %20s %20s\n",
                        client.getId(),
                        client.getName(),
                        cpf,
                        cnpj);
            });
            printLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllAccounts() {
        String jpqlQuery = "select a from Account a";
        try {
            List<Account> accountList = entityManager.createQuery(jpqlQuery, Account.class).getResultList();
            printLine();
            System.out.format("%10s %10s %15s %15s %10s %25s\n",
                    "Id", "Agency", "Account Number", "Account Type", "Balance", "Owner");
            printLine();
            accountList.forEach(account -> {
                String accountType = "Null";
                if (account instanceof CheckingAccount) {
                    accountType = "Checking";
                }
                else if (account instanceof SavingAccount) {
                    accountType = "Saving";
                }
                System.out.format("%10s %10s %15s %15s R$%5.2f %25s\n",
                        account.getId(),
                        account.getAgency(),
                        account.getAccountNumber(),
                        accountType,
                        account.getBalance(),
                        account.getClient().getName());
            });
            printLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }
    public void assignAccountToClient(Client client, Account account){
        client.getAccounts().add(account);
        account.setClient(client);
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }
}
