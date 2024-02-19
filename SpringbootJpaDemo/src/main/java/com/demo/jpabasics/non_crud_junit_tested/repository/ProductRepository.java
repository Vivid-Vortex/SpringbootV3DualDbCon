package com.demo.jpabasics.non_crud_junit_tested.repository;

import com.demo.jpabasics.non_crud_junit_tested.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * "Spring Data Commons" is the parent project of "Spring Data", which encompasses other individual projects such as "Spring Data LDAP", "Spring Data MongoDB", "Spring Data JPA", "Spring Data JDBC" etc.
 * Project Spring Data Commons - PagingAndSortingRepository extends CrudRepository extends Repository and also one more interface named as QueryDsiPredicateExecutor
 * Project Spring Data JPA - JpaRepository extends PagingAndSortingRepository extends CrudRepository extends Repository and one more interface named as JpaSpecificationExecutor
 */

//@Repository // No need to include @Repository as JPARepository is extends all the other interfaces upto Repository interface, which in turn is implemented by SimpleJpaRepository class.
//    And this class has already has @Repository.
public interface ProductRepository extends JpaRepository<Product, Long> {

   /**
    * It totally depends on us what we want to return. We can either return entity type or Optional of entity type.
    * Both valid.
    */
//   Product findByName(String name);
   Optional<Product> findByName(String name);

   // We are overriding crudrepository findById by overriding it's return type. That is also possible.
   Optional<Product> findById(Long id);

   // We can either return list or single entity as per our chioce and understanding. Since here multipele products can be found with same name and description, so using as return type
   List<Product> findByNameOrDescription(String name, String description);
//   Product findByNameOrDescription(String name, String description);

   // We can either return list or single entity as per our chioce and understanding. Since here multipele products can be found with same name or description, so using as return type
   List<Product> findByNameAndDescription(String name, String description);
//   Product findByNameAndDescription(String name, String description);

   Product findDistinctByName(String name);

   List<Product> findByPriceGreaterThan(BigDecimal price);

   List<Product> findByPriceLessThan(BigDecimal price);

   //It will use the Like keyword to filter the matching predicate. For example, ...Where name LIKE '%Ban%' - It will filter any name column containing Ban substring in their main string.
   //findByContaining: Automatically appends "%" wildcards to both ends of the search string, meaning it matches any substring within the searched field.
   //findByContaining: By default, the search is case-insensitive unless explicitly modified using @Query annotation or JpaMetamodel.
   //findByContaining: Generally slower than findByLike because it needs to match against all substrings within the field.
   List<Product> findByNameContaining(String name);

   //Unlike findByContaining, findByLike Requires you to specify the desired wildcard placement manually using "%" symbols. This gives you more control over the matching pattern.
   //findByLike: By default, the search is case-sensitive unless explicitly specified as case-insensitive using @Query annotation or JpaMetamodel.
   //findByLike: Can be faster when using specific wildcard patterns that limit the matching scope.
   List<Product> findByNameLike(String name);

   List<Product>  findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

   List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

   // the sql query will go like this, where name in ('product_1'', 'product_2') etc.
   List<Product> findByNameIn(List<String> names);

   /** Limiting the Query Result using first or top keyword in the method name*/

   // It will fetch all the products ordered by name in ascending order
   List<Product> findFirstByOrderByNameAsc();

   // It will fetch first 2 products ordered by name in ascending order
   List<Product> findFirst2ByOrderByNameAsc();

   List<Product> findTop3ByOrderByPriceDesc();


   /** --------------NamedQueries (@NamedQuery&@NamedQueries || @NamedNativeQuery&@NamedNativeQueries)----------------------------------------------
    * use cases, advantages and disadvantage of namedquery annotation:
    * The @NamedQuery annotation in JPA serves several useful purposes:
    * 1. Centralized Query Definition:
    * Allows you to define queries directly within entity classes or a separate mapping file, keeping them separate from repository or service code. This improves code organization and readability.
    *
    * 2. Validation and Performance:
    * Queries defined with @NamedQuery are validated during application startup, ensuring syntax correctness and preventing runtime errors. They are also pre-parsed and stored, improving query execution performance compared to dynamically constructed queries.
    *
    * 3. Reusability:
    *
    * Named queries can be invoked from any part of your application using the query name, promoting code reuse and consistency. This avoids duplicating similar queries across different parts of your codebase.
    *
    * 4. Parameterization:
    *
    * You can bind parameters to @NamedQuery queries, enhancing flexibility and security. This allows you to dynamically construct queries without embedding values directly, preventing SQL injection vulnerabilities.
    *
    * 5. Testability:
    *
    * Independent of how they are invoked, named queries can be easily tested and isolated from the rest of your application logic. This facilitates debugging and ensures query functionality.
    *
    * Here are some common use cases for @NamedQuery:
    * Defining frequently used complex queries for clarity and usability.
    * Pre-defining queries for performance optimization, especially in larger applications.
    * Implementing queries with dynamic clauses or parameters for flexibility.
    * Facilitating testing of data access logic by isolating named queries.
    *
    * However, there are also some potential drawbacks to consider:
    * Overuse of named queries can lead to less maintainable code if not kept well-organized.
    * Debugging queries might require searching for the definition location if not named meaningfully.
    *
    * @NamedQuery - It is for using Jpql queries over entity
    * @NamedNativeQuery - It is for slql queries over entity
    */

   /** @NamedQuery Defined over entity method signature*/
   Product findByPrice(BigDecimal price);

   Product findByPriceUsingNamedParam(@Param("price") BigDecimal price);

   /** @NamedNativeQuery Defined over entity method signature*/
   @Query(nativeQuery = true)
   Product findByDescription(String description);

   @Query(nativeQuery = true)
   Product findByDescriptionUsingNamedParam(@Param("description") String description);

}
