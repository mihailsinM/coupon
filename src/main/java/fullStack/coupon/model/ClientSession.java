package fullStack.coupon.model;

import fullStack.coupon.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClientSession {
    private ClientService service;
    private long lastActive;
}
