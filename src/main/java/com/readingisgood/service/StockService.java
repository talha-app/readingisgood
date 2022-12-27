package com.readingisgood.service;

import com.readingisgood.dto.request.CreateNewBookRequestDTO;
import com.readingisgood.dto.request.UpdateStockRequestDTO;
import com.readingisgood.dto.response.MessageResponse;
import com.readingisgood.entity.Book;
import com.readingisgood.entity.Customer;
import com.readingisgood.entity.OrderLine;
import com.readingisgood.repository.IBookRepository;
import com.readingisgood.repository.IStockRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final IStockRepository stockRepository;
    private final IBookRepository bookRepository;
    private static final Logger logger = LogManager.getLogger(StockService.class);

    public StockService(IStockRepository stockRepository, IBookRepository bookRepository)
    {
        this.stockRepository = stockRepository;
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<?> updateStock(UpdateStockRequestDTO requestDTO)
    {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = customer.getUsername().equals("admin");
        if (!isAdmin)
        {
            logger.error("Error: Only admin can update stock!");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Only admin can update stock!"));
        }
        Optional<Book> bookOptional = bookRepository.findById(requestDTO.getBookId());
        if (!bookOptional.isPresent())
        {
            logger.error("Error: Book is not found!");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Book is not found!"));
        }
        Book book = bookOptional.get();
        int count = requestDTO.getStockCount();

        stockRepository.updateStock(book, count, "admin", LocalDateTime.now());
        logger.info("Stock is updated successfully!");
        return ResponseEntity.ok(new MessageResponse("Stock is updated successfully!"));
    }

    @Transactional
    public void updateStock(List<OrderLine> orderLines) throws Exception
    {
        try
        {
            orderLines.forEach(orderLine ->
            {
                stockRepository.updateStockAfterOrderPlacing(orderLine.getBook(), "MOBILEAPP", LocalDateTime.now());
            });
        } catch (Exception ex)
        {
            throw new Exception("Stock Problem");
        }


    }

    public ResponseEntity<?> createNewBook(CreateNewBookRequestDTO requestDTO)
    {
        Book book = new Book(requestDTO.getTitle(), requestDTO.getAuthor(), requestDTO.getPrice(), LocalDateTime.now());
        bookRepository.save(book);
        logger.info("Book is saved successfully!");
        return ResponseEntity.ok(new MessageResponse("Book is saved successfully!"));
    }
}
