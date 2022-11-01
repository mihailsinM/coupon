package fullStack.coupon.filters;

import com.auth0.jwt.JWT;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
public class TokenFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = request.getHeader("authorization").replace("Bearer ", "");
            System.out.println(token);

            String name = JWT.decode(token).getClaim("name").asString();
            String email = JWT.decode(token).getClaim("email").asString();

            System.out.println("filter");

            filterChain.doFilter(request, response);
        }catch (Exception e){
            response.setStatus(401);
            response.getWriter().println("Invalid credentials!");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/auth/login") || path.startsWith("/auth/register");
    }
}
