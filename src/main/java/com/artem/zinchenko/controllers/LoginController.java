package com.artem.zinchenko.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/chat")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String loginFromRequest = req.getParameter("login");
        if (loginFromRequest == null || loginFromRequest.isEmpty()) {
            loginFromRequest = "Anonymous";
        }
        String loginFromContext = (String) session.getServletContext().getAttribute(loginFromRequest);
        if (loginFromContext != null) {
            loginFromRequest = loginFromRequest + "*";
        }

        session.setAttribute("login", loginFromRequest);
        session.getServletContext().setAttribute(loginFromRequest, loginFromRequest);
        req.setAttribute("login", loginFromRequest);
        req.getRequestDispatcher("WEB-INF/pages/chat.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("login") == null) {
            resp.sendRedirect("index.jsp");
        }
    }
}
