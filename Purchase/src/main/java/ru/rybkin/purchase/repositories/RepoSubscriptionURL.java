package ru.rybkin.purchase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rybkin.purchase.entities.SubscriptionURL;

public interface RepoSubscriptionURL extends JpaRepository<SubscriptionURL, Integer> {
}
