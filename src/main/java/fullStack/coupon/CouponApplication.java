package fullStack.coupon;

import fullStack.coupon.service.AdminService;
import fullStack.coupon.service.CompanyService;
import fullStack.coupon.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponApplication.class, args);
		
//		ConfigurableApplicationContext ctx = SpringApplication.run(CouponApplication.class, args);
//		AdminService adminFacade = ctx.getBean(AdminService.class);
//		CompanyService companyFacade = ctx.getBean(CompanyService.class);
//		CustomerService customerFacade = ctx.getBean(CustomerService.class);

	}
//Admin //Admin //Admin //Admin //Admin //Admin //Admin //Admin //Admin //Admin //Admin //Admin //Admin //Admin

		//ADD COMPANY
//		adminFacade.addCompany(new Company("Granit LTD", "granit@com", "1234"));
//		adminFacade.addCompany(new Company("Marble LTD", "marble@com", "1235"));
//		adminFacade.addCompany(new Company("Cesar LTD", "stone@com", "1237"));

		//UPDATE COMPANY
//		Company company = null;
//		try {
//			company = adminFacade.getOneCompany(1);
//			company.setName("Marble & Granit");
//			adminFacade.updateCompany(company);
//			System.out.println(company);
//		} catch (CompanyException e) {
//			e.printStackTrace();
//		}

		//GET ALL COMPANIES
//		System.out.println(adminFacade.getAllCompanies());

		//DELETE COMPANY
//		try {

//			Company company = adminFacade.getOneCompany(3);
//			adminFacade.deleteCompany(company.getId());
//			System.out.println(adminFacade.getAllCompanies());
//		} catch (CompanyException e) {
//			System.out.println(e.getMessage());;
//		}

		//ADD CUSTOMER
//		adminFacade.addCustomer(new Customer("Mihael", "Sin", "mihael@email", "1234"));
//		adminFacade.addCustomer(new Customer("Yael", "Ayal", "yael@email", "1235"));
//		adminFacade.addCustomer(new Customer("Moral", "Lamor", "moral@email", "1236"));
//		adminFacade.addCustomer(new Customer("Uri", "Yura", "uri@email", "1237"));

		//UPDATE CUSTOMER
//		try {
//			Customer customer = adminFacade.getOneCustomer(3);
//			customer.setEmail("stam@email");
//			adminFacade.updateCustomer(customer);
//			System.out.println(customer);
//		} catch (CustomerException e) {
//			e.printStackTrace();
//		}

		//GET ALL CUSTOMERS
//		System.out.println(adminFacade.getAllCustomers());

		//DELETE CUSTOMER
//		try {
//			Customer customer = adminFacade.getOneCustomer(4);
//			adminFacade.deleteCustomer(customer.getId());
//			System.out.println(adminFacade.getAllCustomers());
//		} catch (CustomerException e) {
//			e.printStackTrace();
//		}

		//COMPANY //COMPANY //COMPANY //COMPANY //COMPANY //COMPANY //COMPANY //COMPANY //COMPANY //COMPANY //COMPANY //COMPANY
		//ADD COUPON
//		try {
//			companyFacade.addCoupon(companyFacade.createCoupon(7));
//		} catch (CompanyException e) {
//			System.out.println(e.getMessage());
//		}


		//DELETE COUPON
//		try {
//			companyFacade.deleteCoupon(3);
//		} catch (CouponException e) {
//			System.out.println(e.getMessage());
//		}


//		try {
//			System.out.println(adminFacade.getOneCompany(10));
//		} catch (CompanyException e) {
//			System.out.println(e.getMessage());
//		}

		//UPDATE COMPANY
		////////////////////////////////////////////////////////////////////////doesn't always work////////////?????????????
//		try {
//			companyFacade.updateCoupon(new Coupon(7,
//					adminFacade.getOneCompany(4),
//					Category.values()[4],
//					Generator.getRandomTitle(),
//					Generator.getRandomDescription(),
//					Generator.convertStartDateToSqlDate(2022,6, 22),
//					Generator.convertStartDateToSqlDate(2022,6, 30),
//					10,
//					Generator.getRandomDouble(100,900),
//					"Image"));
//		} catch (CouponException | CompanyException e) {
//			System.out.println(e.getMessage());
//		}

		//GET COUPONS BY COMPANY ID
//		try {
//			companyFacade.getCompanyCoupons(adminFacade.getOneCompany(7));
//			System.out.println(companyFacade.getCompanyCoupons(adminFacade.getOneCompany(7)));
//		} catch (CompanyException e) {
//			System.out.println(e.getMessage());
//		}

		//GET COUPONS BY CATEGORY ID
//		try {
//			System.out.println(companyFacade.getCompanyCouponsByCategory(7,Category.FOOD));
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		//GET COUPONS BY maxPrice

//		try {
//			System.out.println(companyFacade.getCompanyCouponsByMaxPrice(2,400));
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		//Company Details
//		try {
//			System.out.println(companyFacade.getCompanyDetails(1));
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		//CUSTOMER //CUSTOMER //CUSTOMER //CUSTOMER //CUSTOMER //CUSTOMER //CUSTOMER //CUSTOMER //CUSTOMER //CUSTOMER

		//Purchase Coupon
//		try {
//			customerFacade.purchaseCoupon(1,5 );
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}


		//getOneCustomer
//		try {
//			System.out.println(customerFacade.getOneCustomer(1));
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		//getOneCoupon
//		try {
//			System.out.println(customerFacade.getOneCoupon(6));
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		//LOGIN
//		System.out.println( "Yor id = " + customerFacade.login("yael@email","1235"));
//		System.out.println("Yor id = " + customerFacade.login("mihael@email","1234"));


		//List<Coupon> getCustomerCoupon

//		try {
//			System.out.println( "Yor id = " + customerFacade.login("yael@email","1235"));
////		System.out.println("Yor id = " + customerFacade.login("mihael@email","1234"));
//			System.out.println(customerFacade.getCustomerCoupon());
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}


		//List<Coupon> getCustomerCouponByCategory
//		try {
//			System.out.println(customerFacade.getCustomerCoupon(1, 1));
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		//get coupon by customer
//		System.out.println( "Yor id = " + customerFacade.login("yael@email","1235"));
//		System.out.println("Yor id = " + customerFacade.login("mihael@email","1234"));
//		try {
//			System.out.println(customerFacade.getCustomerCoupon(700));
//		} catch (NotFoundException e) {
//			System.out.println(e.getMessage());
//		}


//		System.out.println("Yor id = " + customerFacade.login("mihael@email","1234"));
//		try {
//			System.out.println(customerFacade.getCustomerDetails());
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//			customerFacade.getCustomerDetails();
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//

//		try {
//			companyFacade.deleteCoupon(4);
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		}
//	}


}

