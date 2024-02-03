package com.example.app.storage;

import com.example.app.model.WalletOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletOperationRepository extends JpaRepository<WalletOperation, Long> {
}
