package fullStack.coupon.service;

import fullStack.coupon.exeption.EmailExistsException;
import fullStack.coupon.exeption.LoginNotFoundException;
import fullStack.coupon.exeption.NotFoundException;
import fullStack.coupon.model.Company;
import fullStack.coupon.model.Coupon;
import fullStack.coupon.model.Credentials;
import fullStack.coupon.model.Customer;
import fullStack.coupon.repository.CouponRepository;
import fullStack.coupon.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends ClientService{

    private int id;

    private CustomerRepository customerRepository;
    private CouponRepository couponRepository;

    public CustomerService(CustomerRepository customerRepository, CouponRepository couponRepository) {
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomerLogin(Credentials cred) throws LoginNotFoundException {
        return customerRepository.findByEmailAndPassword(cred.getEmail(),
                cred.getPassword()).orElseThrow(()-> new LoginNotFoundException());
    }

    public Customer addCustomer(Customer customer) throws EmailExistsException {
        if(!customerRepository.existsByEmail(customer.getEmail()))
            return customerRepository.save(customer);
        throw new EmailExistsException();
    }

    public boolean loginBool(String email, String password) {
        if(customerRepository.existsByEmail(email)){
            return true;
        }
        return false;
    }

    public int login(String email, String password){
        int isExist = 0;
        try {
            isExist = customerRepository.findCustomerByEmailPassword(email, password);
            id = isExist;
        }catch (Exception e) {
            System.out.println("Customer not exist by email or password ...");
        }
        return isExist;
    }

    public void purchaseCoupon(int customerId, int couponId) throws NotFoundException {
        Customer customer = getOneCustomer(customerId);
        customer.addCoupon(getOneCoupon(couponId));
        updateCustomer(customer);
    }

    public Customer getOneCustomer(int id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not exist by id..."));
    }

    public Coupon getOneCoupon(int id) throws NotFoundException {
        return couponRepository.findById(id).orElseThrow(() -> new NotFoundException("Coupon not exist by id..."));
    }

    public void updateCustomer(Customer customer) throws NotFoundException {
        if (customerRepository.existsById(customer.getId()))
            customerRepository.save(customer);
        else
            throw new NotFoundException("Customer not exist by id...");
    }

    public List<Coupon> getCustomerCoupon() throws NotFoundException {
        return getOneCustomer(id).getCoupons();
    }

    public List<Coupon> getCustomerCoupon( int categoryId,int customerId) throws NotFoundException {
        if (couponRepository.findCouponsByCustomerCategory(categoryId, customerId).size() != 0)
            return couponRepository.findCouponsByCustomerCategory(categoryId, customerId);
        throw new NotFoundException("Customer has no such coupon...");
    }

    public List<Coupon> getCustomerCoupon(double maxPrice) throws NotFoundException {
        if(couponRepository.findCouponsByCustomer(id,maxPrice).size() == 0)
            throw new NotFoundException("Something did not fit no such id or such price");
        return couponRepository.findCouponsByCustomer(id,maxPrice);
    }

    public  Customer getCustomerDetails() throws NotFoundException {
        return  customerRepository.findById(id).orElseThrow(()-> new NotFoundException("Customer not exist by id... "));
    }

}
