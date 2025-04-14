package com.jbw.jwtAuthentification.filter;

import com.jbw.jwtAuthentification.configuration.JwtUtils;
import com.jbw.jwtAuthentification.service.CustomDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class FilterJwt extends OncePerRequestFilter {
    private final CustomDetailService customDetailService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorisation");
            String username = null;
            String jwt = null;

            if(authHeader != null && authHeader.startsWith("Bearer")){
                jwt = authHeader.substring(7);
                username = jwtUtils.extractUsername(jwt);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)  // le but est deverier que les user n'est pas encore conenecter
            {

                UserDetails userDetails = customDetailService.loadUserByUsername(username);
                //verifier si le token est valide
                if(jwtUtils.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authentificationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentificationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentificationToken);
                }
            }

            filterChain.doFilter(request, response);
    }
}
