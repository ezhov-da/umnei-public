package ru.ezhov.umnei.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.ezhov.umnei.utils.MdcAssistant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class MdcFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try (@SuppressWarnings("unused") MDC.MDCCloseable mdcCloseable =
                     MDC.putCloseable("FLOW_ID", MdcAssistant
                             .create(UUID.randomUUID().toString())
                             .and(request.getSession().getId())
                             .build())
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
