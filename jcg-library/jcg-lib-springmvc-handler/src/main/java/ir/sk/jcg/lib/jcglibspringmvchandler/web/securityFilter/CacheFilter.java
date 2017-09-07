package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Add cache header to all request that start with startWith!
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class CacheFilter implements Filter {

    private long age;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        final String ageStr = filterConfig.getInitParameter("age");
        if (ageStr != null && !ageStr.isEmpty())
            age = Long.parseLong(ageStr);
        else
            age = 365 * 24 * 60 * 60;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            final String url = httpServletRequest.getRequestURI();
            httpServletResponse.addHeader("Cache-Control", String.format("public, must-revalidate, proxy-revalidate, max-age=%d", age));
            httpServletResponse.addDateHeader("Expires", System
                    .currentTimeMillis() + age * 1000);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
