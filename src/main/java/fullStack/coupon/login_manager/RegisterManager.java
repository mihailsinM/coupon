package fullStack.coupon.login_manager;

import fullStack.coupon.service.AdminService;
import fullStack.coupon.service.ClientService;
import fullStack.coupon.service.CompanyService;
import fullStack.coupon.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
@Service
public class RegisterManager {
    @Autowired
    private ApplicationContext applicationContext;

    public RegisterManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

    }
    public ClientService login(String email, String password, ClientType clientType) throws LoginException {

        switch(clientType){
            case CUSTOMER:
                CustomerService customerService = applicationContext.getBean(CustomerService.class);
                if(customerService.login(email, password) <= 0){
                    throw new LoginException();}
                return customerService;

            case COMPANY:
                CompanyService companyService = applicationContext.getBean(CompanyService.class);
                if (companyService.login(email, password) <= 0)
                    throw new LoginException();
                return companyService;
        }
        return null;
    }
}