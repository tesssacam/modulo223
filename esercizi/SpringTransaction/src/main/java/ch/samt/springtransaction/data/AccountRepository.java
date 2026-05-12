package ch.samt.springtransaction.data;

import ch.samt.springtransaction.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
