package ru.rybkin.purchase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybkin.purchase.entities.SubscriptionURL;

import java.util.Optional;

public interface RepoSubscriptionURL extends JpaRepository<SubscriptionURL, Integer> {

    Optional<SubscriptionURL> findByName(String name);
    void deleteByName(String name);
    boolean existsByName(String name);
}
