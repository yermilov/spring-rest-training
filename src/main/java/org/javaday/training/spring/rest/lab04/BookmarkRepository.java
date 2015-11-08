package org.javaday.training.spring.rest.lab04;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;
import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@RepositoryRestController
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Collection<Bookmark> findByAccountUsername(String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Bookmark> List<S> save(Iterable<S> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Bookmark> S saveAndFlush(S entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteInBatch(Iterable<Bookmark> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAllInBatch();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Bookmark> S save(S entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Long aLong);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Bookmark entity);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Iterable<? extends Bookmark> entities);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void deleteAll();
}