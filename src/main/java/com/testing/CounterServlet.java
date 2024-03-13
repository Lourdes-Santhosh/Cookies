package com.testing;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CounterServlet")
public class CounterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer sessionCount = (Integer) session.getAttribute("sessionCount");
        if (sessionCount == null) {
            sessionCount = 1;
        } else {
            sessionCount++;
        }
        session.setAttribute("sessionCount", sessionCount);

        Cookie[] cookies = request.getCookies();
        Integer cookieCount = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitCount")) {
                    cookieCount = Integer.parseInt(cookie.getValue());
                }
            }
        }
        cookieCount++;
        Cookie cookie = new Cookie("visitCount", String.valueOf(cookieCount));
        response.addCookie(cookie);

        response.getWriter().println("Session Count: " + sessionCount);
        response.getWriter().println("Cookie Count: " + cookieCount);
    }
}
