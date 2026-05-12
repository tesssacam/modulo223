package ch.samt.springtransaction;

import ch.samt.springtransaction.data.AccountRepository;
import ch.samt.springtransaction.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import service.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testTransfer() {
        //given: creazione 2 account
        accountRepository.save(new Account("account1", 1000));
        accountRepository.save(new Account("account2", 0));

        //when: invio ordine di trasferimento
        //in questo caso non mi interessa testare il controller ed invoco direttamente il service
        try {
            accountService.transfer("account1", "account2",600);
        }catch (Exception e) {
            System.out.println("si è verificato un errore!");
            //then: in questo caso voglio il roolback del pagamento (i 600 eruo devono tornare su account1)
            assertEquals(1000, accountRepository.findById("account1").orElseThrow().getBalance());
            //continuare da slide numero 9 (ppt Transaction)
        }
    }

}
