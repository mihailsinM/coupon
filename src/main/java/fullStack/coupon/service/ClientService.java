package fullStack.coupon.service;

import fullStack.coupon.repository.CompanyRepository;
import fullStack.coupon.repository.CouponRepository;
import fullStack.coupon.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;
    private CouponRepository couponRepository;


    public abstract int login(String email, String password);
}

