package com.nye.myWay.services;

import com.nye.myWay.dto.BookDTO;
import com.nye.myWay.entities.Book;
import com.nye.myWay.exception.BookNotFoundException;
import com.nye.myWay.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        if (!bookRepository.findByIsbn(bookDTO.getIsbn()).isPresent()) {
            Book newBook = modelMapper.map(bookDTO, Book.class);
            bookRepository.save(newBook);
        } else {
            Book storedBook = bookRepository.findByIsbn(bookDTO.getIsbn()).get();
            storedBook.setQuantity(storedBook.getQuantity() + 1);
            bookRepository.save(storedBook);
        }
        return modelMapper.map(bookRepository.findByIsbn(bookDTO.getIsbn()), BookDTO.class);
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (bookDTO.getTitle() != null && !bookDTO.getTitle().isEmpty()) {
                book.setTitle(bookDTO.getTitle());
            }
            if (bookDTO.getAuthor() != null && !bookDTO.getAuthor().isEmpty()) {
                book.setAuthor(bookDTO.getAuthor());
            }
            if (bookDTO.getDescription() != null && !bookDTO.getDescription().isEmpty()) {
                book.setDescription(bookDTO.getDescription());
            }
            if (bookDTO.getIsbn() != null && !bookDTO.getIsbn().isEmpty()) {
                book.setIsbn(bookDTO.getIsbn());
            }
            if (bookDTO.getPrice() != 0.0) {
                book.setPrice(bookDTO.getPrice());
            }
            if (bookDTO.getPublishingDate() != null && !bookDTO.getPublishingDate().isEmpty()) {
                book.setPublishingDate(bookDTO.getPublishingDate());
            }
            if (bookDTO.getPublishingPlace() != null && !bookDTO.getPublishingPlace().isEmpty()) {
                book.setPublishingPlace(bookDTO.getPublishingPlace());
            }
            if (bookDTO.getBookCategory() != null) {
                book.setBookCategory(bookDTO.getBookCategory());
            }
            if (bookDTO.getLanguage() != null && !bookDTO.getLanguage().isEmpty()) {
                book.setLanguage(bookDTO.getLanguage());
            }
            if (bookDTO.getPublisher() != null && !bookDTO.getPublisher().isEmpty()) {
                book.setPublisher(bookDTO.getPublisher());
            }
            if (bookDTO.getQuantity() != 0) {
                book.setQuantity(bookDTO.getQuantity());
            }

            Book updatedBook = bookRepository.save(book);
            return modelMapper.map(updatedBook, BookDTO.class);
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public BookDTO deleteBook(Long bookId) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            Book deletedBook = bookRepository.findById(bookId).get();
            BookDTO deletedBookDTO = modelMapper.map(deletedBook, BookDTO.class);
            bookRepository.delete(deletedBook);
            return deletedBookDTO;
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public Page<BookDTO> getFilteredBook(Integer page, Integer size, String direction, String orderBy) throws BookNotFoundException{
        System.out.println(direction);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        System.out.println("hello");
        Page<Book> foundBooks = bookRepository.findAll(pageRequest);
        if (foundBooks.isEmpty()) {
            throw new BookNotFoundException();
        }
        //Page interface has a -map function!!!
        Page<BookDTO> bookDTOPage = foundBooks.map(book -> modelMapper.map(book, BookDTO.class));
        return bookDTOPage;
    }
}
