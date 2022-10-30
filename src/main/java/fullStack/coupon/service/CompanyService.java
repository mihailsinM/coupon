package fullStack.coupon.service;

import fullStack.coupon.exeption.*;
import fullStack.coupon.model.Category;
import fullStack.coupon.model.Company;
import fullStack.coupon.model.Coupon;
import fullStack.coupon.model.Credentials;
import fullStack.coupon.repository.CompanyRepository;
import fullStack.coupon.repository.CouponRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Scope("prototype")

public class CompanyService extends ClientService {



    private int id;

    private CouponRepository couponRepository;
    private CompanyRepository companyRepository;

    public CompanyService(CouponRepository couponRepository, CompanyRepository companyRepository) {
        this.couponRepository = couponRepository;
        this.companyRepository = companyRepository;
    }

    public int getId() {
        return id;
    }

    @Override
    public int login(String email, String password){
        int isExist = 0;
        try {
            isExist = companyRepository.findCompanyByEmailPassword(email, password);
            id = isExist;
        }catch (Exception e) {
        }
        return isExist;
    }

    public Company getCompanyLogin(Credentials cred) throws LoginNotFoundException {
        return companyRepository.findByEmailAndPassword(cred.getEmail(),
                cred.getPassword()).orElseThrow(()-> new LoginNotFoundException());
    }

    public Company addCompany(Company company) throws EmailExistsException {
        if(!companyRepository.existsByEmail(company.getEmail()))
            return companyRepository.save(company);
        throw new EmailExistsException();
    }

    public Coupon addCoupon(Coupon coupon) throws AddFailedException {
        Date nowDate = new Date(System.currentTimeMillis());
        if (
//                couponRepository.findCouponsByCompanyId(coupon.getCompany().getId()) == null &&
                !couponRepository.existsById(coupon.getId())
                && coupon.getAmount() > 0
                && coupon.getEndDate().after(nowDate))
            return couponRepository.save(coupon);
        else
            throw new AddFailedException();
    }

    public Optional<Coupon> updateCoupon(Coupon coupon) {
        if (couponRepository.existsById(coupon.getId())) {
            couponRepository.save(coupon);
            return Optional.of(couponRepository.save(coupon));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteCoupon(int id) throws CouponNotFoundException {
        if (!couponRepository.existsById(id)) {
            throw new CouponNotFoundException(id);
        }else {
            couponRepository.deleteCouponCustomer(id);
            couponRepository.deleteById(id);
            return true;
        }
    }

    public List<Coupon> getCompanyCoupons(Company company) {
        return couponRepository.findCouponsByCompanyId(company.getId());
    }

    public List<Coupon> getCompanyCouponsByCategory(int id, Category category) throws NotFoundException {
        if (0 < couponRepository.findCouponsByCompanyCategory(id, category.ordinal()).size()){
            return couponRepository.findCouponsByCompanyCategory(id, category.ordinal());
        }else {
            throw new NotFoundException("Company Coupons not exist by category...");
        }
    }

    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws NotFoundException {
        if (companyRepository.existsById(id)){
            return couponRepository.findCouponsByCompanyMaxPrice(id, maxPrice);
        }else {
            throw new NotFoundException("Company not exist by id...");
        }
    }
    public Company getCompanyDetails(int companyId) throws NotFoundException {
        Company company = companyRepository.findById(companyId).orElseThrow(()-> new NotFoundException("Company not exist by id... "));
        company.setCoupons(couponRepository.findCouponsByCompany(company));
        return company;
    }

    public Coupon getOneCouponByCompanyId(int couponId) throws CouponNotFoundException {
        if (couponRepository.findByCouponAndCompanyId(id, couponId) == null)
            throw new CouponNotFoundException(id);
        else
            return couponRepository.findByCouponAndCompanyId(id, couponId);
    }

}
