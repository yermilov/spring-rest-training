package org.javaday.training.spring.rest.lab04;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('ROLE_USER')")
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Account> List<S> save(Iterable<S> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Account> S saveAndFlush(S entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteInBatch(Iterable<Account> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAllInBatch();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Account> S save(S entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Long aLong);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Account entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Iterable<? extends Account> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll();
}