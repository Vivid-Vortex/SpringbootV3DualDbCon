package com.demo.jpabasics.repository;


import com.demo.jpabasics.non_crud_junit_tested.entity.Product;
import com.demo.jpabasics.non_crud_junit_tested.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void pagination() {
        int pageNo = 0;
        int pageSize = 5;

        //create Pagable objects
        final PageRequest pageRequest = PageRequest.of(pageNo, pageSize);

        //findAll method and pass pagable instance
        final Page<Product> page = productRepository.findAll(pageRequest);

        final List<Product> productList = page.getContent();

        productList.forEach(product -> {
            System.out.println(product);
        });

        //totlapage
        final int totalPages = page.getTotalPages();
        //total elements
        final long totalElements = page.getTotalElements();
        //number of elments
        final int numberOfElements = page.getNumberOfElements();
        //page size
        final int size = page.getSize();
        //last
        final boolean last = page.isLast();
        //first
        final boolean first = page.isFirst();

        System.out.println("total page :" + totalPages);
        System.out.println("Totla elements :" + totalElements);
        System.out.println("Number of elements :" + numberOfElements);
        System.out.println("size :" + size);
        System.out.println("isLast :"+last);
        System.out.println("isFirst :"+first);
    }

}
