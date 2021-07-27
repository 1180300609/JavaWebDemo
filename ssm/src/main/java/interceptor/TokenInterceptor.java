package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("******进入拦截器******");

        //放行OPTIONS请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            System.out.println("放行OPTIONS请求");
            return true;
        }

        String token = request.getHeader("token");
        System.out.println("token::"+token);
        boolean b = token != null && JwtUtil.verify(token);
        System.out.println(b);
        return b;
    }
}
