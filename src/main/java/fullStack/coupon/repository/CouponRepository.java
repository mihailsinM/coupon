package fullStack.coupon.repository;

import fullStack.coupon.model.Company;
import fullStack.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findCouponsByCompanyId(int id);
    List<Coupon> findCouponsByCompany(Company company);

    @Transactional
    @Modifying
    void deleteCouponByCompanyId (int id);

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons d where d.coupons_id = :id", nativeQuery = true)
    void deleteCouponCustomer(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons d where d.customer_id = :id", nativeQuery = true)
    void deleteCouponByCustomerId(@Param("id") int id);


    @Query(value = "SELECT * FROM coupons p WHERE p.company_id = :id and p.category = :category", nativeQuery = true)
    List<Coupon> findCouponsByCompanyCategory(@Param("id") int id, @Param("category")int category);

    @Query(value = "SELECT * FROM coupons p WHERE p.company_id = :id and p.price >= :maxPrice", nativeQuery = true)
    List<Coupon> findCouponsByCompanyMaxPrice(@Param("id") int id,@Param("maxPrice") double maxPrice);

    @Query(value = "SELECT * FROM coupons p WHERE p.company_id = :companyId and p.id = :couponId", nativeQuery = true)
    Coupon findByCouponAndCompanyId(@Param("companyId") int companyId, @Param("couponId") int couponId);

    @Query(value = "select * from coupons join customers_coupons on " +
            "coupons.id = customers_coupons.coupons_id where category = :category_id and customer_id = :customer_id", nativeQuery = true)
    List<Coupon> findCouponsByCustomerCategory(@Param("category_id")int categoryId,@Param("customer_id")int customerId);

    @Query(value = "select * from coupons join customers_coupons on " +
            "coupons.id = customers_coupons.coupons_id where price > :maxPrice and customer_id = :customer_id", nativeQuery = true)
    List<Coupon> findCouponsByCustomer(@Param("customer_id") int id,@Param("maxPrice") double maxPrice);


}
