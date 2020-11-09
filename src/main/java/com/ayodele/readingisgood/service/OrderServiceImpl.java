package com.ayodele.readingisgood.service;

import com.ayodele.readingisgood.dto.OrderRequest;
import com.ayodele.readingisgood.dto.OrderResponse;
import com.ayodele.readingisgood.entities.Books;
import com.ayodele.readingisgood.entities.Customer;
import com.ayodele.readingisgood.entities.Orders;
import com.ayodele.readingisgood.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final BooksService booksService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService, BooksService booksService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.booksService = booksService;
    }


    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) throws Exception {
        Optional<Customer> customer = customerService.findById(orderRequest.getCustomerId());
        if (customer.isPresent()) {
            Map<String, Integer> bookIdNumberOfCopiesMap = orderRequest.getBookIdNumberOfCopies();
            Set<String> bookIds = bookIdNumberOfCopiesMap.keySet();
            Iterator<String> bookIdIterator = bookIds.iterator();

            HashSet<OrderResponse.Books> orderedBooks = new HashSet<>();
            OrderResponse.Books bookOrder = new OrderResponse.Books();

            List<Books> books = new ArrayList<>();

            while (bookIdIterator.hasNext()) {
                String currentBookId = bookIdIterator.next().trim();
                Optional<Books> book = booksService.findById(Integer.parseInt(currentBookId));
                if (book.isPresent()) {
                    Books currentBook = book.get();

                    int numberInStock = currentBook.getNumberInStock();
                    int numberOfCopiesToBuy = bookIdNumberOfCopiesMap.get(currentBookId);
                    if (numberOfCopiesToBuy > numberInStock) {
                        numberOfCopiesToBuy = numberInStock;
                    }
                    int copiesLeftInStock = numberInStock - numberOfCopiesToBuy;
                    currentBook.setNumberInStock(copiesLeftInStock);

                    booksService.updateBook(currentBook);
                    bookOrder.bookname = currentBook.getBookName();
                    bookOrder.numberOfCopies = numberOfCopiesToBuy;

                    orderedBooks.add(bookOrder);
                    books.add(currentBook);
                }
            }

            if (orderedBooks.size() == 0) {
                throw new Exception("Order can be made for at least 1 book");
            }

            Orders orders = new Orders();
            orders.setCustomer(customer.get());
            orders.setBooks(books);

            Orders savedOrder = orderRepository.save(orders);

            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(savedOrder.getId());
            orderResponse.setBooks(orderedBooks);
            orderResponse.setCustomerName(customer.get().getFirstName());
            return orderResponse;

        } else {
            throw new Exception("Customer does not exist");
        }
    }

//    @Override
//    public List<OrderResponse> getOrderByCustomer(Integer customerId) throws Exception {
//        Optional<Customer> customer = customerService.findById(customerId);
//        if (customer.isPresent()) {
//
//        }else {
//            throw new Exception("Customer does not exist");
//        }
//    }
}
