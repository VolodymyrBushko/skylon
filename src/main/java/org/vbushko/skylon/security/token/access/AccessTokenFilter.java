package org.vbushko.skylon.security.token.access;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.vbushko.skylon.security.userdetails.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AccessTokenFilter extends GenericFilterBean {

    private static final AccessTokenType TOKEN_TYPE = AccessTokenType.BEARER;

    private final AccessTokenService accessTokenService;
    private final CustomUserDetailsService detailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = extractToken((HttpServletRequest) request);
        if (isNotBlank(token) && accessTokenService.validate(token)) {
            String login = accessTokenService.extractLogin(token);
            UserDetails customUserDetails = detailsService.loadUserByUsername(login);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        Optional<String> tokenOptional = Optional.ofNullable(request.getHeader(AUTHORIZATION));
        String token = tokenOptional.map(String::trim).orElse(EMPTY);
        return token.startsWith(TOKEN_TYPE.getType())
                ? token.substring(TOKEN_TYPE.getType().length() + 1)
                : EMPTY;
    }
}
