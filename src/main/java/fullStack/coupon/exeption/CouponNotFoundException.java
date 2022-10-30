package fullStack.coupon.exeption;

public class CouponNotFoundException extends RuntimeException{
    public CouponNotFoundException(int id) {
        super("Could not found the coupon with id: " + id);
    }
}
