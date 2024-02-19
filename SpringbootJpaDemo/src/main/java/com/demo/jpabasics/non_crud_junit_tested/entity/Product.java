package com.demo.jpabasics.non_crud_junit_tested.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity

/** --------@NamedQueries&@NamedQuery || @NamedNativeQueries/@NamedNativeQuery--------
// You can either use the @NamedQueries or @NamedQuery, but not both at the same time on one enitity
//Choose from @NamedQuery from jakarta
@NamedQuery( // Look for method signature inside ProductRepository interface with method signature as "Product findByPrice(BigDecimal price);"
        name = "Product.findByPrice", //Product is the entity name, which is followed by custom choosen method name which is similar to defining method inside JpaRepository. Except for certain advantages, disadvantages and difference.
        query = "SELECT p from Product p where p.price = ?1" // ?1 - This is index type parameter passing to custom Jpql
)
@NamedQuery( // Look for method signature inside ProductRepository interface with method signature as "Product findByPrice(BigDecimal price);"
        name = "Product.findByPriceUsingNamedParam", //Product is the entity name, which is followed by custom choosen method name which is similar to defining method inside JpaRepository. Except for certain advantages, disadvantages and difference.
        query = "SELECT p from Product p where p.price = :price" // :price - This is named type parameter passing to custom Jpql
) */

@NamedQueries(
        {
                @NamedQuery( // Look for method signature inside ProductRepository interface with method signature as "Product findByPrice(BigDecimal price);"
                        name = "Product.findByPrice", //Product is the entity name, which is followed by custom choosen method name which is similar to defining method inside JpaRepository. Except for certain advantages, disadvantages and difference.
                        query = "SELECT p from Product p where p.price = ?1" // ?1 - This is index type parameter passing to custom Jpql
                ),
                @NamedQuery( // Look for method signature inside ProductRepository interface with method signature as "Product findByPrice(BigDecimal price);"
                        name = "Product.findByPriceUsingNamedParam", //Product is the entity name, which is followed by custom choosen method name which is similar to defining method inside JpaRepository. Except for certain advantages, disadvantages and difference.
                        query = "SELECT p from Product p where p.price = :price" // :price - This is named type parameter passing to custom Jpql
                )
        }
)

//@NamedNativeQuery(
//        name = "Product.findByDescription",
//        query = "select * from products p where p.description = ?1",
//        resultClass = Product.class
//)
//
//@NamedNativeQuery(
//        name = "Product.findByDescriptionUsingNamedParam",
//        query = "select * from products p where p.description = :description",
//        resultClass = Product.class
//)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Product.findByDescription",
                    query = "select * from products p where p.description = ?1",
                    resultClass = Product.class
            ),
            @NamedNativeQuery(
                    name = "Product.findByDescriptionUsingNamedParam",
                    query = "select * from products p where p.description = :description",
                    resultClass = Product.class
            )
        }
)
@Table(
        name = "products",
        schema = "ecommerce", //Schema or database is same
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "sku_unique",
                        columnNames = "stock_keeping_unit" // actual column name, and not the field name
                )
//                ,@UniqueConstraint(
//                        name = "name_unique",
//                        columnNames = "name"
//                )
        }
)
public class Product {
    /**@GeneratedValue(strategy = GenerationType.IDENTITY)
     *
     * Note: Turn on hibernate logging. Use DDL type (such as create-drop etc) as ddl-auto.
     * In case of mysql since it doesn't have any sequence maintenance mechanism, so you should be able to see the create table hibernate_sequence sql query in the logs.
     * In case of PostgresSQL and Oracle, they have the sequence maintenance mechanism.
     *
     * GenerationType.AUTO: #Default generation.
     * Persistence provider or database vendor will choose the generation type or strategy.
     * If using hibernate as the persistence provider, then it selects strategy based on the database-specific dialect.
     * For most popular databases (Postgres, Oracle, MySql), it selects GenerationType.SEQUENCE. That means even if we use generation type as auto,
     * then also behind the scene it will use GenerationType.SEQUENCE if using these types of databases.
     *
     * GenerationType.IDENTITY: It auto increments database column for every insert. It's highly efficient because it doesn't require any additional statements.
     * If not using JDBC batch operations, then we can go for it. Has no performance implications.
     *
     * GenerationType.SEQUENCE: Most commonly used generation strategy if having a large application.
     *
     * GenerationType.TABLE: This is rarely used nowadays. It slows down the application, because this strategy creates pessimistic locks and
     * put all the transactions into a sequential order. We should we SEQUENCE only if database supports. Hibernate team also recommends the same.
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )
    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence_name", // new table with hhis name will be created to maintain the sequences
            allocationSize = 1 // default is 50
    )
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false)//nullable=false means cannot be null
    private String sku; //stock keeping unit

    @Column(nullable = false, length=255) //default length value is 255
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageUrl;

    @CreationTimestamp //hibernate will fetch current jvm time and assign it to this field
    private LocalDateTime dateCreated;

    @UpdateTimestamp //In case update. It works as the name suggests
    private LocalDateTime lastUpdated;
}
