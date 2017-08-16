package com.artem.zinchenko.controllers;


import com.artem.zinchenko.model.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/send"})
public class ChatController extends HttpServlet {

    private static List<ChatMessage> list = Collections.synchronizedList(new ArrayList<ChatMessage>());

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String timeStamp = req.getParameter("timeStamp");
        String login = (String) req.getSession().getAttribute("login");
        List<ChatMessage> unreadedMassages;
        String collect = "";
        if (!timeStamp.equals("empty")) {
            int indexOf = list.indexOf(new ChatMessage("", "", timeStamp));
            unreadedMassages = list.subList(indexOf + 1, list.size());
            collect = unreadedMassages.stream()
                    .filter(chatMessage -> !chatMessage.getLogin().equals(login))
                    .map(ChatMessage::toString)
                    .collect(Collectors.joining(","));
        } else {
            collect = list.stream().map(ChatMessage::toString).collect(Collectors.joining(","));
        }
        collect = "[" + collect + "]";
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(collect);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String text = req.getParameter("text");
        String login = (String) req.getSession().getAttribute("login");
        ChatMessage chatMessage = new ChatMessage(text, login, sdf.format(timestamp));
        list.add(chatMessage);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(chatMessage.toString());
    }
}
