package fullStack.coupon.controller;

import fullStack.coupon.exeption.NotFoundException;
import fullStack.coupon.model.Coupon;
import fullStack.coupon.model.Customer;
import fullStack.coupon.repository.CustomerRepository;
import fullStack.coupon.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/couponcastomer/{id}")
    ResponseEntity<?> getOneCoupon (@PathVariable int id) {
        try {
            return ResponseEntity.ok(customerService.getOneCoupon(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body("Coupon not found!");

        }
    }

    @PutMapping(path = "/purchase")
    void purchaseCoupon (@RequestBody Coupon coupon, @RequestBody Customer customer) {
        try {
            customerService.purchaseCoupon(coupon.getId(), customer.getId());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path = "/purchased")
    ResponseEntity<?> getCustomerCoupons()  {
        try {
            return ResponseEntity.ok(customerService.getCustomerCoupon());
        } catch ( NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/cast_details")
    ResponseEntity<?> getDetails (){
        try {
            return ResponseEntity.ok(customerService.getCustomerDetails());
        } catch (NotFoundException e){
            return  ResponseEntity.badRequest().body("No details");
        }
    }

}






























//    @PostMapping(path = "/auth/login")
//    public ResponseEntity<String> login(@RequestBody Credentials creds){
//        try {
//            Customer customer = customerService.getCustomerLogin(creds);
//            return ResponseEntity.ok(createToken(customer));
//        } catch (LoginNotFoundException e) {
//            return ResponseEntity.status(401).body(e.getMessage());
//        }
//    }
//
//    private String createToken(Customer customer){
//        String token = JWT.create()
//                .withIssuer("auth0")//-------------------------------------------????????
//                .withIssuedAt(new Date())
//                .withExpiresAt(Instant.now().plus(1, ChronoUnit.MINUTES))
//                .withClaim("id", customer.getId())
//                .withClaim("firstName", customer.getFirst_name())
//                .withClaim("lastName", customer.getLast_name())
//                .withClaim("email", customer.getEmail())
//                .sign(Algorithm.HMAC256("topsecret"));
//        return token;
//    }
