package ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter;

import ir.sk.jcg.jcglibcommon.util.RandomUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Check random token be exists in parameters
 * Use <filter:CSRF /> to add hidden input to forms
 * Return status 409 if not match
 * Must add param name be required in every request
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 1/7/2017
 */
public class CSRFFilter implements Filter {

    private String param;
    private String session;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        param = filterConfig.getInitParameter("param");
        if (param==null || param.isEmpty())
            param = "csrf_token";
        FilterConfiguration.i().setParamCSRF(param);
        session = filterConfig.getInitParameter("session");
        if (session==null || session.isEmpty())
            session = "csrf_token";
        FilterConfiguration.i().setSessionCSRF(session);

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            if (!checkCSRF(httpServletRequest)) {
            //    FilterConfiguration.logger.warn("Request with bad csrf code!"); // TODO: 1/7/2017
                httpServletResponse.setStatus(409);
                return;
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public boolean checkCSRF(HttpServletRequest request) {
        Object requestSessionCSRF = request.getSession().getAttribute(session);
        if (requestSessionCSRF == null) {
            request.getSession().setAttribute(session, RandomUtils.generateRandomCode(20));
            return true;
        }
        String sessionToken = (String) requestSessionCSRF;
        String requestToken = request.getParameter(param);
        //TODO: Check CSRF for upload form
        if (request.getContentType() != null
                && request.getContentType().startsWith("multipart/form-data"))
            return true;
        return requestToken == null || sessionToken.equals(requestToken);
    }
}
