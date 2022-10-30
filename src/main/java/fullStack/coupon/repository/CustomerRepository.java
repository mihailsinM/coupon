package fullStack.coupon.repository;

import fullStack.coupon.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer, Integer>{
    Optional<Customer> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);

    @Query(value = "SELECT p.id FROM customers p WHERE p.email = :email and p.password = :password", nativeQuery = true)
    int findCustomerByEmailPassword(@Param("email") String email, @Param("password")String password);
}
