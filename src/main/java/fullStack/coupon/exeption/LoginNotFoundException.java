package fullStack.coupon.exeption;

public class LoginNotFoundException extends Exception{
    public LoginNotFoundException() {
        super("Invalid email or password");
    }
}

