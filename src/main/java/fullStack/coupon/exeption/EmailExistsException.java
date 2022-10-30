package fullStack.coupon.exeption;

public class EmailExistsException extends Exception{
    public EmailExistsException() {
        super("Email already exists!");
    }
}
