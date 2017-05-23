package cn.kevin.filter;

import cn.kevin.util.Constants;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 对于需要登录的功能，如果没有登录就跳转到登录页面
 * Created by yongkang.zhang on 2017/5/23.
 */
@Slf4j
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute(Constants.LOGIN_USER);
            if (Strings.isNullOrEmpty(name)) {
                throw new RuntimeException();
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (RuntimeException re) {
            log.error("尚未登录的用户");
            servletRequest.setAttribute(Constants.TIP_MESSAGE, "请先登录!");
            response.sendRedirect("/login/index");
        }

    }

    @Override
    public void destroy() {

    }
}
