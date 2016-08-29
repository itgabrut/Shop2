package com.ilya.utils;


import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ilya on 29.08.2016.
 */
public class FormEncodingSetterFilter implements Filter {

    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";
    private static final String FILTERABLE_CONTENT_TYPE2="multipart/form-data";

    private static final String ENCODING_DEFAULT = "UTF-8";

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";

    private String encoding;

    public void destroy() {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String contentType = servletRequest.getContentType();
        if (contentType != null && (contentType.startsWith(FILTERABLE_CONTENT_TYPE)||contentType.startsWith(FILTERABLE_CONTENT_TYPE2)))
            servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }
}
