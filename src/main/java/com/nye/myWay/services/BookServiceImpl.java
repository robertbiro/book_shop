package com.nye.myWay.services;

import com.nye.myWay.dto.BookResponseAdminDTO;
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
    private CartItemService cartItemService;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BookResponseAdminDTO addBook(BookResponseAdminDTO bookResponseAdminDTO) {
        if (!bookRepository.findByIsbn(bookResponseAdminDTO.getIsbn()).isPresent()) {
            Book newBook = modelMapper.map(bookResponseAdminDTO, Book.class);
            bookRepository.save(newBook);
        } else {
            Book storedBook = bookRepository.findByIsbn(bookResponseAdminDTO.getIsbn()).get();
            storedBook.setQuantity(storedBook.getQuantity() + 1);
            bookRepository.save(storedBook);
        }
        return modelMapper.map(bookRepository.findByIsbn(bookResponseAdminDTO.getIsbn()), BookResponseAdminDTO.class);
    }

    @Override
    public BookResponseAdminDTO updateBook(Long bookId, BookResponseAdminDTO bookResponseAdminDTO) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (bookResponseAdminDTO.getTitle() != null && !bookResponseAdminDTO.getTitle().isEmpty()) {
                book.setTitle(bookResponseAdminDTO.getTitle());
            }
            if (bookResponseAdminDTO.getAuthor() != null && !bookResponseAdminDTO.getAuthor().isEmpty()) {
                book.setAuthor(bookResponseAdminDTO.getAuthor());
            }
            if (bookResponseAdminDTO.getDescription() != null && !bookResponseAdminDTO.getDescription().isEmpty()) {
                book.setDescription(bookResponseAdminDTO.getDescription());
            }
            if (bookResponseAdminDTO.getIsbn() != null && !bookResponseAdminDTO.getIsbn().isEmpty()) {
                book.setIsbn(bookResponseAdminDTO.getIsbn());
            }
            if (bookResponseAdminDTO.getPrice() != 0.0) {
                book.setPrice(bookResponseAdminDTO.getPrice());
            }
            if (bookResponseAdminDTO.getPublishingDate() != null && !bookResponseAdminDTO.getPublishingDate().isEmpty()) {
                book.setPublishingDate(bookResponseAdminDTO.getPublishingDate());
            }
            if (bookResponseAdminDTO.getPublishingPlace() != null && !bookResponseAdminDTO.getPublishingPlace().isEmpty()) {
                book.setPublishingPlace(bookResponseAdminDTO.getPublishingPlace());
            }
            if (bookResponseAdminDTO.getBookCategory() != null) {
                book.setBookCategory(bookResponseAdminDTO.getBookCategory());
            }
            if (bookResponseAdminDTO.getLanguage() != null && !bookResponseAdminDTO.getLanguage().isEmpty()) {
                book.setLanguage(bookResponseAdminDTO.getLanguage());
            }
            if (bookResponseAdminDTO.getPublisher() != null && !bookResponseAdminDTO.getPublisher().isEmpty()) {
                book.setPublisher(bookResponseAdminDTO.getPublisher());
            }
            if (bookResponseAdminDTO.getQuantity() != 0) {
                book.setQuantity(bookResponseAdminDTO.getQuantity());
            }

            Book updatedBook = bookRepository.save(book);
            return modelMapper.map(updatedBook, BookResponseAdminDTO.class);
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public BookResponseAdminDTO deleteBook(Long bookId) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            Book deletedBook = bookRepository.findById(bookId).get();
            BookResponseAdminDTO deletedBookResponseAdminDTO = modelMapper.map(deletedBook, BookResponseAdminDTO.class);
            bookRepository.delete(deletedBook);
            return deletedBookResponseAdminDTO;
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public Page<BookResponseAdminDTO> getFilteredBook(Integer page, Integer size, String direction, String orderBy) throws BookNotFoundException{
        System.out.println(direction);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        System.out.println("hello");
        Page<Book> foundBooks = bookRepository.findAll(pageRequest);
        if (foundBooks.isEmpty()) {
            throw new BookNotFoundException();
        }
        //Page interface has a -map function!!!
        Page<BookResponseAdminDTO> bookDTOPage = foundBooks.map(book -> modelMapper.map(book, BookResponseAdminDTO.class));
        return bookDTOPage;
    }

    //-------------------------------------------------------------------------------------------------
    //get reachable issue: quantity (storage) - books with same bookId in Cart
    @Override
    public int getReachableIssue(Long bookId) throws BookNotFoundException{
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            int reservedBooks = cartItemService.getSameBookQuantityInCartItems(bookId);
            return optionalBook.get().getQuantity() - reservedBooks;
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public boolean isBookAvailable(Long bookId, int quantity) throws BookNotFoundException {
        return getReachableIssue(bookId) >= quantity;
    }

    @Override
    public Book getBook(Long bookId) throws BookNotFoundException {
        if (bookRepository.findById(bookId).isPresent()) {
            return bookRepository.findById(bookId).get();
        } else {
            throw new BookNotFoundException();
        }
    }
}
