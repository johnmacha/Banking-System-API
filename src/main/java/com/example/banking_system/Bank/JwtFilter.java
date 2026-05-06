package com.example.banking_system.Bank;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    private final JwtUtil jwtUtil;

public JwtFilter(JwtUtil jwtUtil){
    this.jwtUtil = jwtUtil;
}

@Override
protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
)
throws ServletException, IOException{
    String authHeader = request.getHeader("Authorization");

    if(authHeader != null && authHeader.startsWith("Bearer ")){
        String token = authHeader.substring(7);
    
    try{
      String username = jwtUtil.extractUsername(token); 

      //The key part
      UsernamePasswordAuthenticationToken authentication = 
      new UsernamePasswordAuthenticationToken(
        username,
        null,
        Collections.emptyList()
      );
      authentication.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)

    );
    
    //Attach user to Spring Security context
    SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (Exception e){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid token");
        return;
    }
}
filterChain.doFilter(request, response);
}
}
