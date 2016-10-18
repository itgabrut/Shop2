package com.ilya.securityservice;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilya on 17.10.2016.
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {
//    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|POST|OPTIONS)$");
    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/loggin", null);

    @Override
    public boolean matches(HttpServletRequest request) {
//        if(allowedMethods.matcher(request.getMethod()).matches()){
//            return false;
//        }

        return !unprotectedMatcher.matches(request);
//        return true;
    }
}
