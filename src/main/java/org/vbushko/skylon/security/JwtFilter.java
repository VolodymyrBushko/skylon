package org.vbushko.skylon.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

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
import static org.vbushko.skylon.security.Config.BEARER;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService detailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) request);
        if (isNotBlank(token) && jwtProvider.validateToken(token)) {
            String login = jwtProvider.getLoginFromToken(token);
            UserDetails customUserDetails = detailsService.loadUserByUsername(login);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        Optional<String> bearerOptional = Optional.ofNullable(request.getHeader(AUTHORIZATION));
        String bearer = bearerOptional.map(String::trim).orElse(EMPTY);
        return bearer.startsWith(BEARER)
                ? bearer.substring(BEARER.length() + 1)
                : EMPTY;
    }
}
