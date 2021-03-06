package org.javaday.training.spring.rest.lab02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author yaroslav.yermilov
 */
@RestController
class BookmarkController {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/{username}/bookmarks")
    public HttpEntity<Collection<Bookmark>> findAllBookmarks(@PathVariable String username) {
        Collection<Bookmark> bookmarks = bookmarkRepository.findByAccountUsername(username);

        bookmarks.forEach(bookmark -> bookmark.add(ControllerLinkBuilder.linkTo(getClass(), getMethod("findBookmark", String.class, Long.class), username, bookmark.getBookmarkId()).withSelfRel()));

        return new ResponseEntity<>(bookmarks, HttpStatus.OK);
    }

    @RequestMapping("/{username}/bookmarks/{bookmarkId}")
    public HttpEntity<Bookmark> findBookmark(@PathVariable String username, @PathVariable Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findOne(bookmarkId);
        if (bookmark.getAccount().getUsername().equals(username)) {
            bookmark.add(ControllerLinkBuilder.linkTo(getClass(), getMethod("findBookmark", String.class, Long.class), username, bookmarkId).withSelfRel());
            return new ResponseEntity<>(bookmark, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{username}/bookmarks", method = RequestMethod.POST)
    public HttpEntity<?> createBookmark(@PathVariable String username, @RequestBody Bookmark bookmark) {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new AccountNotExistsException(username));

        bookmark.setAccount(account);
        bookmarkRepository.save(bookmark);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ControllerLinkBuilder.linkTo(getClass(), getMethod("findBookmark", String.class, Long.class), username, bookmark.getBookmarkId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    private Method getMethod(String methodName, Class<?>... parameterTypes) {
        try {
            return getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
