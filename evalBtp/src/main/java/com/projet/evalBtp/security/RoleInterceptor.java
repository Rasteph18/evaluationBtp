package com.projet.evalBtp.security;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.projet.evalBtp.models.Utilisateur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoleInterceptor implements HandlerInterceptor{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.getMethod().getAnnotation(NoSession.class) != null) {
                System.out.println("Method No Session");
                return true;
            } 
            Role roleAnnotation = handlerMethod.getMethod().getAnnotation(Role.class);
            if (roleAnnotation != null) {
                if (!isUserLoggedIn(request)) {
                    System.out.println("No user Redirection to Login");
                    response.sendRedirect("/user/page-login-client");
                    return false;
                }
                String[] allowedRoles = roleAnnotation.value();
                String userRole = getUserRole(request);
                System.out.println("User Role: " + userRole);
                if (!userHasRequiredRole(userRole, allowedRoles)) {
                    response.sendRedirect("/user/erreur");
                    return false;
                }
            } else {
                return true;
            }
        }

        return true;
    }

    private boolean userHasRequiredRole(String userRole, String[] allowedRoles)
    {
        for(String role : allowedRoles) {
            if (userRole.equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUserLoggedIn(HttpServletRequest request)
    {
        return request.getSession().getAttribute("user") != null;
    }

    private String getUserRole(HttpServletRequest request)
    {
        Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
        return user.getRoleAuth();
    }
}
