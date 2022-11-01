package fullStack.coupon.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import fullStack.coupon.login_manager.LoginManager;
import fullStack.coupon.model.ClientSession;
import fullStack.coupon.model.Company;
import fullStack.coupon.model.Credentials;
import fullStack.coupon.repository.CompanyRepository;
import fullStack.coupon.service.AdminService;
import fullStack.coupon.service.ClientService;
import fullStack.coupon.service.CompanyService;
import fullStack.coupon.service.CustomerService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private CompanyRepository companyRepository;

    protected Map<String, ClientSession> sessions = new HashMap<>();


    @PostMapping(path = "/register")
    public ResponseEntity<Object> registerCompany(@RequestBody Company company) {
        Company newCompany = companyRepository.save(company);
        return ResponseEntity.ok(newCompany);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) {
        try {
            ClientService clientService =
                    loginManager.login(credentials.getEmail(), credentials.getPassword(), credentials.getClientType());
            int id = 0;
            if (clientService instanceof AdminService) {
                id = 0;
            } else if (clientService instanceof CompanyService) {
                id = ((CompanyService) clientService).getId();
            } else if (clientService instanceof CustomerService) {
                id = ((CustomerService) clientService).getId();
            }
            String token = createToken(credentials, id);
            sessions.put(token, new ClientSession(clientService, System.currentTimeMillis()));
            return ResponseEntity.ok(token);
        } catch (LoginException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    private String createToken(Credentials credentials, int id) {
        String token = JWT.create()
                .withIssuer("auth0")//-------------------------------------------????????
                .withIssuedAt(new Date())
                .withClaim("id", id)
                .withClaim("email", credentials.getEmail())
                .withClaim("password", credentials.getPassword())
                .withClaim("clientType", credentials.getClientType().toString())
                .sign(Algorithm.HMAC256("topsecret"));
        return token;
    }

}
