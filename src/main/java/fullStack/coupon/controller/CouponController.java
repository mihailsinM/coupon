package fullStack.coupon.controller;

import fullStack.coupon.exeption.CouponNotFoundException;
import fullStack.coupon.model.Coupon;
import fullStack.coupon.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CouponController {
    @Autowired
    private CouponRepository couponRepository;



    @GetMapping("/coupons")
    List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }





}
