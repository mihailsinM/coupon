package fullStack.coupon.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import fullStack.coupon.exeption.*;
import fullStack.coupon.model.Category;
import fullStack.coupon.model.Company;
import fullStack.coupon.model.Coupon;
import fullStack.coupon.model.Credentials;
import fullStack.coupon.repository.CompanyRepository;
import fullStack.coupon.repository.CouponRepository;
import fullStack.coupon.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyService companyService;

    @PostMapping("/addcoupon")
    Coupon addCoupon(@RequestBody Coupon coupon) throws AddFailedException {
        return companyService.addCoupon(coupon);
    }
    @PutMapping("/coupon/{id}")
    Coupon updateCoupon(@RequestBody Coupon coupon){
        return companyService.updateCoupon(coupon)
                .map(coup -> {
                    coup.setTitle(coupon.getTitle());
                    coup.setDescription(coupon.getDescription());
                    coup.setStartDate(coupon.getStartDate());
                    coup.setEndDate(coupon.getEndDate());
                    coup.setAmount(coupon.getAmount());
                    coup.setPrice(coupon.getPrice());
                    coup.setImage(coupon.getImage());
                    coup.setCategory(coupon.getCategory());
                    coup.setCompany(coupon.getCompany());
                    return couponRepository.save(coup);
                }).orElseThrow(()-> new CouponNotFoundException(coupon.getId()));
    }
    @DeleteMapping("/coupon/{id}")
    String deleteCoupon(@PathVariable int id) {
        if (!couponRepository.existsById(id)) {
            throw new CouponNotFoundException(id);
        }
        companyService.deleteCoupon(id);
        return "Coupon with id " + id + " has been deleted.";
    }

//    @GetMapping("/coupon/{id}")-------------------------------------------??????????????????????????????
//    Coupon getCouponById(@PathVariable int id){
//        return companyService.getOneCouponByCompanyId(id);
//    }

    @GetMapping("/coupon/{id}")
    Coupon getCouponById(@PathVariable int id){
        return couponRepository.findById(id).orElseThrow(()-> new CouponNotFoundException(id));
    }

    @GetMapping(path = "comp/{id}")
    ResponseEntity<?> getCouponsCompany(@RequestBody Company company){
        List<Coupon> coupons = companyService.getCompanyCoupons(company);
        if (coupons.size() != 0)
            return ResponseEntity.ok(coupons.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found....");
    }

    @GetMapping(path = "/category")
    ResponseEntity<?> getCouponsByCategory (@RequestParam Category category, int id){
        try {
            return ResponseEntity.ok(companyService.getCompanyCouponsByCategory(id, category));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping (path = "/maxprice/{p}")
    ResponseEntity<?> getByMaxPrice (@PathVariable double max_price){
        try {
            return ResponseEntity.ok(companyService.getCompanyCouponsByMaxPrice(max_price));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping (path = "/details")
    Company getOneCompany(@PathVariable int id) throws NotFoundException {
    return companyService.getCompanyDetails(id);
}




}
