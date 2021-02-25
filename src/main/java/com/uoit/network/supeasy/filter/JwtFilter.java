package com.uoit.network.supeasy.filter;

import com.uoit.network.supeasy.model.JwtUser;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.security.auth.login.LoginException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.uoit.network.supeasy.util.JwtHelper;

public class JwtFilter extends GenericFilterBean {
    @Autowired
    private JwtUser jwtUser;
    @SneakyThrows
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {

            if (authHeader == null || !authHeader.startsWith("bearer;")) {
                throw new LoginException("login error");
            }
            final String token = authHeader.substring(7);

            try {

                final Claims claims = JwtHelper.parseJWT(token,"MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");
                if(claims == null){
                    throw new LoginException("login error");
                }
                request.setAttribute("claims", claims);
            } catch (final Exception e) {
                throw new LoginException("login error");
            }

            chain.doFilter(req, res);
        }
    }

}
