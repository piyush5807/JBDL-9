package com.gfg.majorproject.wallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet,Long> {
    Optional<Wallet> findWalletByUserId(String userId);

    @Transactional
    @Modifying
    @Query("update Wallet w SET w.balance = w.balance + :amount where w.userId =:userId")
    void updateWallet(String userId, Double amount);
}
