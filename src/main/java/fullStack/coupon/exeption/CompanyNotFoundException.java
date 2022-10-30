package fullStack.coupon.exeption;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(int id) {
        super("Could not found the company with id: " + id);
    }
}
