package com.hrs.hotelbooking.infra.security.filters;


import com.hrs.hotelbooking.application.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {
        try {
            String credential = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = parseCredential(credential);

            UserDetails user = userService.loadUserByUsername(token);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    user.getPassword(),
                    user.getAuthorities()
            );
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            filterChain.doFilter(request, response);
        } catch (Exception error) {
            filterChain.doFilter(request, response);
        }
    }

    private String parseCredential(String credential) {
        try {
            if (credential == null) {
                throw new IllegalArgumentException("Credential Is Missing.");
            }

            String authSchema = credential.split(" ")[0];
            String token = credential.split(" ")[1].strip();

            if (!authSchema.equals("Bearer")) {
                throw new IllegalArgumentException("Invalid Authorization Scheme.");
            }

            return token;
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("Invalid Token");
        }
    }
}
