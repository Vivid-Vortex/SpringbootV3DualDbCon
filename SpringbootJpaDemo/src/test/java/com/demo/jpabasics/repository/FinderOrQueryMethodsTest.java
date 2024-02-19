package com.demo.jpabasics.repository;

import com.demo.jpabasics.non_crud_junit_tested.entity.Product;
import com.demo.jpabasics.non_crud_junit_tested.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//TODO Analyse all the hibernate generated methods later on
@SpringBootTest
public class FinderOrQueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        final Product banana = productRepository.findByName("Banana").orElseThrow();
        System.out.println(banana);

    }

    @Test
    void findByIdMethod() {
        Long id = 1L;
        final Product product = productRepository.findById(id).orElseThrow();
        System.out.println(product);
    }

    @Test
    void findByNameorDescriptionMethod() {
        final List<Product> productList = productRepository.findByNameOrDescription("Banana", "Ice cream description");
        productList.forEach(product ->
                {
                    System.out.println(product);
                });
    }

    @Test
    void findByNameAndDescriptionMethod() {
        final List<Product> productList = productRepository.findByNameAndDescription("Banana", "Banana description");
        productList.forEach(product ->
        {
            System.out.println(product);
        });
    }

    /**
     *
     In SQL, the DISTINCT keyword is used with the SELECT statement to remove duplicate rows from the result set. This means it only returns unique values for the specified columns, eliminating any rows that contain identical data based on those columns.
     */
    @Test
    void findDistinctByNameMethod() {
        final Product iceCream = productRepository.findDistinctByName("Ice cream");
        System.out.println(iceCream);
    }

    @Test
    void findByPriceGreaterThanMethod() {
        final List<Product> productsAsPerPrice = productRepository.findByPriceGreaterThan(new BigDecimal(100));
        productsAsPerPrice.forEach(product -> {
            System.out.println(product);
                }
        );
    }

    @Test
    void findByPriceLessThanMethod() {
        final List<Product> productsByPrice = productRepository.findByPriceLessThan(new BigDecimal(105)); // Since less than, so 105 product won't be included
        productsByPrice.forEach(product -> {
            System.out.println(product);
        });
    }

    @Test
    void findByNameContainingMethod() {
        // Worth noticing generated hibernate query for findByNameContaining. Compare it with query generated by findByNameLike.
        // Have two rows/entities having the keyword containing Ban in their name. Banana & AppleBan
        final List<Product> products = productRepository.findByNameContaining("Ban");
        products.forEach(product -> {
            System.out.println(product);
        });
    }

    @Test
    void findByNameLikeMethod() {
        // Worth noticing generated hibernate query for findByNameLike. Compare it with query generated by findByNameContaining.
        // Have two rows/entities having the keyword containing Ban in their name. Banana & AppleBan
        final List<Product> products = productRepository.findByNameLike("AppleBan");
        products.forEach(product -> {
            System.out.println(product);
        });
    }

    @Test
    void findByPriceBetweenMethod() {
        //Both products with price 100 and 105 are included
        final List<Product> priceBetween = productRepository.findByPriceBetween(new BigDecimal(100), new BigDecimal(105));
        priceBetween.forEach(product -> {
            System.out.println(product);
        });
    }

    @Test
    void findByDateCreatedBetween() {
        //start date 2024-02-15 16:19:22.592446
        LocalDateTime startDate = LocalDateTime.of(2024, 02, 15, 16, 19, 22);

        //end date 2024-02-16 13:21:32.060104 - Here the product with id 3 has end date exactly as 2024-02-16 13:21:32 and we are also taking end date exactly the same. So this would product would be excluded.
        LocalDateTime endDate = LocalDateTime.of(2024, 02, 16, 13, 21, 32);

        final List<Product> byDateCreatedBetween = productRepository.findByDateCreatedBetween(startDate, endDate);
        byDateCreatedBetween.forEach(product -> {
            System.out.println(product);
        });
    }

    @Test
    void findByNameInMethod() {
        final List<Product> byNameIn = productRepository.findByNameIn(List.of("Banana", "AppleBan"));
        byNameIn.forEach(product -> {
            System.out.println( product);
        });
    }

    @Test
    void findFirst2ByOrderByNameAscMethod(){
        final List<Product> first2ByOrderByNameAsc = productRepository.findFirst2ByOrderByNameAsc();
        first2ByOrderByNameAsc.forEach(product -> {
            System.out.println(product);
        });
    }

    @Test
    void findTop3ByOrderByPriceDescMethod() {
        final List<Product> top3ByOrderByPriceDesc = productRepository.findTop3ByOrderByPriceDesc();
        top3ByOrderByPriceDesc.forEach(product -> {
            System.out.println(product);
        });
    }


}
