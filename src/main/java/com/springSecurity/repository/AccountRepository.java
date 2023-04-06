package com.springSecurity.repository;

import com.springSecurity.model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Accounts, Long> {
    Accounts findByCustomerId(int customerId);
}
