package fullStack.coupon.service;

import fullStack.coupon.exeption.CompanyNotFoundException;
import fullStack.coupon.exeption.EmailExistsException;
import fullStack.coupon.exeption.NotFoundException;
import fullStack.coupon.exeption.UserNotFoundException;
import fullStack.coupon.model.Company;
import fullStack.coupon.model.Coupon;
import fullStack.coupon.model.Customer;
import fullStack.coupon.repository.CompanyRepository;
import fullStack.coupon.repository.CouponRepository;
import fullStack.coupon.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService extends ClientService{
    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;
    private CouponRepository couponRepository;


    @Override
    public int login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin"))
            return 1;
        return -1;

    }

    public Company addCompany(Company company) throws EmailExistsException {
        if (!companyRepository.existsByEmail(company.getEmail())){
            return companyRepository.save(company);
        }else {
            throw new EmailExistsException();
        }

    }

    public Company updateCompany(Company newCompany) {
        return companyRepository.findById(newCompany.getId())
                .map(company ->{
                    company.setName(newCompany.getName());
                    company.setEmail(newCompany.getEmail());
                    company.setPassword(newCompany.getPassword());
                    return companyRepository.save(company);
                }).orElseThrow(()-> new CompanyNotFoundException(newCompany.getId()));
    }

    public Company getOneCompany(int id) throws CompanyNotFoundException {
        Company company = companyRepository.findById(id).orElseThrow(()-> new CompanyNotFoundException(id));
        company.setCoupons(couponRepository.findCouponsByCompany(company));
        return company;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public boolean deleteCompany(int id) {
        if (!companyRepository.existsById(id)){
            return false;
        }else {
            List<Coupon> coupons = couponRepository.findCouponsByCompanyId(id);
            for (int i = 0; i < coupons.size(); i++) {
                couponRepository.deleteCouponCustomer(coupons.get(i).getId());
            }
            for (int i = 0; i < coupons.size(); i++) {
                couponRepository.deleteCouponByCompanyId(id);
            }
            companyRepository.deleteById(id);
            return true;
        }
    }
    public Customer addCustomer(Customer customer) throws NotFoundException {
        if (!customerRepository.existsByEmail(customer.getEmail()))
            return customerRepository.save(customer);
        else
            throw new NotFoundException("Something went wrong...");

    }
    public Customer updateCustomer(Customer newCustomer) throws NotFoundException {
    return customerRepository.findById(newCustomer.getId())
        .map(user -> {
            user.setFirst_name(newCustomer.getFirst_name());
            user.setLast_name(newCustomer.getLast_name());
            user.setEmail(newCustomer.getEmail());
            user.setPassword(newCustomer.getPassword());
            return customerRepository.save(user);
        }).orElseThrow();
    }


    public Customer getOneCustomer(int id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(()-> new NotFoundException("Customer not exist by id ..."));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public boolean deleteCustomer(int id) {
        if(!customerRepository.existsById(id)){
            return false;
        }else {
            couponRepository.deleteCouponByCustomerId(id);
            customerRepository.deleteById(id);
            return true;
        }
    }
}

