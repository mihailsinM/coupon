package fullStack.coupon.controller;

import fullStack.coupon.model.Coupon;
import fullStack.coupon.repository.CouponRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CouponController {
    @Autowired
    private CouponRepository couponRepository;

    @GetMapping("/coupons")
    List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
