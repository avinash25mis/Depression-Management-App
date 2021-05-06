package com.configuration.security;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author avinash.a.mishra
 */
@Component
public class JwtAppFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      final String authHeader= request.getHeader("Authorization");
      if(StringUtils.isNotEmpty(authHeader) && authHeader.startsWith("Bearer")){
          String jwtToken=authHeader.replace("Bearer","");
          String username=null;

           username= jwtService.getUsernameFromToken(jwtToken);
           if(StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication()==null){
              UserDetails userDetails = appUserDetailService.loadUserByUsername(username);
              Boolean validToken = jwtService.validateToken(jwtToken, userDetails);
              if(validToken){
                  UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                  upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                  SecurityContextHolder.getContext().setAuthentication(upat);
              }

          }


      }
        filterChain.doFilter(request,response);
    }
}
