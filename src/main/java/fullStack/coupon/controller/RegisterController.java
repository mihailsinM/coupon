package fullStack.coupon.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import fullStack.coupon.exeption.EmailExistsException;
import fullStack.coupon.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RegisterController {
    @Autowired
    private CompanyController companyService;
    @Autowired
    private CustomerController customerController;

//    @PostMapping(path = "/auth/register")
//    public ResponseEntity<String> registerCompany(@RequestBody Company company){
//
//     Company newCompany = companyService.addCompany(company);
//     return ResponseEntity.ok(createToken(newCompany));
//   }


    private String createToken(Company company){
        String token = JWT.create()
                .withIssuer("auth0")//-------------------------------------------????????
                .withIssuedAt(new Date())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.MINUTES))
                .withClaim("id", company.getId())
                .withClaim("name", company.getName())
                .withClaim("email", company.getEmail())
                .sign(Algorithm.HMAC256("topsecret"));
        return token;
    }
}
