package bookstore.controllers;

import bookstore.models.entities.Book;
import bookstore.models.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    BookRepository books;

    /**
     * полный список - GET
     */
    @GetMapping("/books")
    public List<Book> index() {
        return books.findAll();
    }

    /**
     * возвращаем одну книгу - GET
     * @param id
     */
    @GetMapping("/books/{id}")
    public Book get(@PathVariable long id) {
        Optional<Book> result = books.findById(id);

        return result.orElse(null);
    }

    /**
     * создание новой записи - POST
     */
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return books.save(book);
    }

    /**
     * сохранение записи - PUT
     * @param id
     */
    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book save(@PathVariable long id, @RequestBody Book newBook) {
        return books.findById(id)
            .map(book -> {
                book.setTitle(newBook.getTitle());
                book.setAuthor(newBook.getTitle());
                book.setPrice(newBook.getPrice());
                book.setGenres(newBook.getGenres());
                return books.save(book);
            })
            .orElse(null);
    }

    /**
     * удаление книги - DELETE
     * @param id
     */
    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        books.deleteById(id);
    }

}
