package fullStack.coupon.controller;

import fullStack.coupon.exeption.EmailExistsException;
import fullStack.coupon.exeption.NotFoundException;
import fullStack.coupon.model.Company;
import fullStack.coupon.model.Customer;
import fullStack.coupon.repository.CompanyRepository;
import fullStack.coupon.service.AdminService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService service;

    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping("/companies")
    List<Company> getAllCompanies(){
        return service.getAllCompanies();
    }


    @GetMapping(path = "/company/{id}")
    Company getOneCompany(@PathVariable int id) {
        return service.getOneCompany(id);
    }

    @PostMapping("/addcompany")
    public Company addCompany(@RequestBody Company company) {
        try {
            return service.addCompany(company);
        } catch (EmailExistsException e) {
            e.getMessage();
        }
        return company;
    }

    @Transactional
    @Modifying
    @DeleteMapping(path = "/company/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        if (service.deleteCompany(id))
            return ResponseEntity.ok("Company with id " + id + " has been deleted");
        return ResponseEntity.badRequest().body("id " + id + " was not found");
    }

    @PutMapping("/company/{id}")
    Company updateCompany(@RequestBody Company newCompany){
        return service.updateCompany(newCompany);
    }


    @GetMapping(path = "/customers")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable int id) throws NotFoundException {
        return service.getOneCustomer(id);
    }
    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestBody Customer customer) throws NotFoundException {
        return service.addCustomer(customer);
    }

    @Transactional
    @Modifying
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        if (service.deleteCustomer(id))
            return ResponseEntity.ok("Customer with id " + id + " has been deleted");
        return ResponseEntity.badRequest().body("id " + id + " was not found");
    }

    @PutMapping("/customer/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer) throws NotFoundException {
        return service.updateCustomer(newCustomer);
    }
}

