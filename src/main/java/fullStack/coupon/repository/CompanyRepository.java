package fullStack.coupon.repository;

import fullStack.coupon.model.Company;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    @Query(value = "SELECT p.id FROM companies p WHERE p.email = :email and p.password = :password", nativeQuery = true)
    int findCompanyByEmailPassword(@Param("email") String email, @Param("password") String password);
}
