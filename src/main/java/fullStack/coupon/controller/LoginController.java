package fullStack.coupon.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import fullStack.coupon.login_manager.LoginManager;
import fullStack.coupon.model.ClientSession;
import fullStack.coupon.model.Credentials;
import fullStack.coupon.service.AdminService;
import fullStack.coupon.service.ClientService;
import fullStack.coupon.service.CompanyService;
import fullStack.coupon.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

        private LoginManager loginManager;
        protected HashMap<String, ClientSession> sessions;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody Credentials creds){
        try {
            ClientService clientService = loginManager.login(creds.getEmail(), creds.getPassword(), creds.getClientType());

            System.out.println("Controller");

            int id = 0;
            if (clientService instanceof AdminService)
                id = 0;
            else if (clientService instanceof CompanyService){
                id = ((CompanyService) clientService).getId();
            }else if (clientService instanceof CustomerService)
                id = ((CustomerService) clientService).getId();
            String token = createToken(creds, id);
            sessions.put(token, new ClientSession(clientService, System.currentTimeMillis()));
            return ResponseEntity.ok(token);
            } catch (LoginException e) {
                return ResponseEntity.status(401).body(e.getMessage());
            }
    }

    private String createToken( Credentials credentials, int id){
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
