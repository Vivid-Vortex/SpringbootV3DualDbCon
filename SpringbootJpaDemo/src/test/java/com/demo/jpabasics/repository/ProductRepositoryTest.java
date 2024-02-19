package com.demo.jpabasics.repository;

import com.demo.jpabasics.non_crud_junit_tested.entity.Product;
import com.demo.jpabasics.non_crud_junit_tested.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

//TODO Analyse all the hibernate generated methods later on
@SpringBootTest // It will load all the beans from our application at test phase
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder().name("AppleBan")
                    .description("Apple description")
                    .sku("100ABC")
                    .price(new BigDecimal(100))
                    .active(true)
                    .imageUrl("apple.png")
    //                .dateCreated("2015 ") // not needed as using hibernate annotation
    //                .lastUpdated("2015 ") // not needed as using hibernate annotation
                    .build();
    }

    // Save method - EntityManager will use entityManager.persist() to persist,
    // else if row already exists (identified by the primary key) then entityManager.merge()
    // to update that column)
    @Test
    void saveMethod() {
        final Product saved = productRepository.save(product);
        System.out.println(saved);
        System.out.println(saved.getId());
    }

    // Save method - EntityManager will use entityManager.persist() to persist,
    // else if row already exists (identified by the primary key) then entityManager.merge()
    // to update that column)
    @Test
    void updateUsingSaveMethod() {
        // find or retrieve  an entity by id
        Long id = 1L;
        final Optional<Product> productOptional = productRepository.findById(id);
        final Product product = productOptional.orElseThrow();

        // update entity information
        product.setName("updated rproduct 1");
        product.setDescription("updated rproduct 1 description");

        // save updated entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod() {
            Long id = 1L;
            final Product product = productRepository.findById(id).orElseThrow();
            System.out.println(product);

    }

    @Test
    void saveAllMethod() {
        final Product product2 = Product.builder().name("Banana")
                .description("Banana description")
                .sku("100ABCBB")
                .price(new BigDecimal(102))
                .active(true)
                .imageUrl("banana.png")
                .build();

        final Product product3 = Product.builder().name("Ice cream")
                .description("Ice cream description")
                .sku("100ABCIJ")
                .price(new BigDecimal(105))
                .active(true)
                .imageUrl("iceCream.png")
                .build();

        productRepository.saveAll(List.of(product2, product3));
    }

    @Test
    void findAllMethod() {
        final List<Product> productList = productRepository.findAll();
        productList.forEach(product1 -> {
            System.out.println(product1.getName());
        });
    }

    @Test
    void deleteByIdMethod() {
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod() {
        // find an entity by id
        Long id = 2L;
        final Optional<Product> productOptionalById = productRepository.findById(id);
        final Product productById = productOptionalById.orElseThrow();

        // delete(entity)
        productRepository.delete(productById);
    }

    @Test
    void deleteAllMethod() {
        // Deleting all the rows or entries fro the database table
//        productRepository.deleteAll();

        // Deleting the specified entries only and not all as above
        Long id = 8L;
        Long id2 = 9L;

        final Product product1 = productRepository.findById(id).orElseThrow();

        final Product product2 = productRepository.findById(id2).orElseThrow();

        productRepository.deleteAll(List.of(product1, product2));
    }

    @Test
    void coutMethod() {
        final long count = productRepository.count();
        System.out.println(count);

    }

    //To check if any entity exists by the given id or not
    @Test
    void existByIdMethod(){
        Long id = 11L;
        final boolean existsById = productRepository.existsById(id);
        System.out.println(existsById);
    }
}