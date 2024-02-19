package com.demo.jpabasics.repository;

import com.demo.jpabasics.non_crud_junit_tested.entity.Product;
import com.demo.jpabasics.non_crud_junit_tested.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class NamedQueryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void namedJpqlQuery() {
        final Product byPrice = productRepository.findByPrice(new BigDecimal(100));
        System.out.println(byPrice);
    }

    @Test
    void findByPriceUsingNamedParamQuery() {
        final Product byPrice = productRepository.findByPriceUsingNamedParam(new BigDecimal(100));
        System.out.println(byPrice);
    }

    @Test
    void namedNativeSqlQuery() {
        final Product bananaDescription = productRepository.findByDescription("Banana description");
        System.out.println(bananaDescription);
    }

    @Test
    void findByDescriptionUsingNamedParamQuery() {
        final Product bananaDescription = productRepository.findByDescriptionUsingNamedParam("Banana description");
        System.out.println(bananaDescription);
    }
}
