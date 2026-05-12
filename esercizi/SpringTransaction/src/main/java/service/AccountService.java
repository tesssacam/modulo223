package service;

import ch.samt.springtransaction.data.AccountRepository;
import ch.samt.springtransaction.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public void transfer(String account1d, String account2d, int amount) {
        Account account1 = accountRepository.findById(account1d).orElseThrow();
        Account account2 = accountRepository.findById(account2d).orElseThrow();

        //addebita la somma sul primo account
        account1.setBalance(account1.getBalance() - amount);
        accountRepository.save(account1);

        //simula un errore
        if(true){
            throw new RuntimeException("any error");
        }

        //accredita la somma sul secondo account
        account2.setBalance(account2.getBalance() + amount);
        accountRepository.save(account2);




    }
}
