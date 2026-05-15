package rikkei_ptit.javawebservicesession14.hackathon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rikkei_ptit.javawebservicesession14.hackathon.exception.ResourceNotFoundException;
import rikkei_ptit.javawebservicesession14.hackathon.model.Book;
import rikkei_ptit.javawebservicesession14.hackathon.model.Category;
import rikkei_ptit.javawebservicesession14.hackathon.repo.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public Book createBook(Book book) {
        // Validate category exists if category is provided
        if (book.getCategory() != null && book.getCategory().getId() != null) {
            categoryService.getCategoryById(book.getCategory().getId());
        } else {
            throw new ResourceNotFoundException("Category is required");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        
        // Partial update: only update fields that are provided (not null)
        if (updatedBook.getTitle() != null) {
            existingBook.setTitle(updatedBook.getTitle());
        }
        if (updatedBook.getAuthor() != null) {
            existingBook.setAuthor(updatedBook.getAuthor());
        }
        if (updatedBook.getPrice() != null) {
            existingBook.setPrice(updatedBook.getPrice());
        }
        if (updatedBook.getStatus() != null) {
            existingBook.setStatus(updatedBook.getStatus());
        }
        
        // Validate and update category if provided
        if (updatedBook.getCategory() != null && updatedBook.getCategory().getId() != null) {
            Category category = categoryService.getCategoryById(updatedBook.getCategory().getId());
            existingBook.setCategory(category);
        }
        
        return bookRepository.save(existingBook);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.softDelete(id);
    }
}
