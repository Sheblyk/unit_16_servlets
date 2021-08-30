package ua.com.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Statistic extends HttpServlet {

    private Map<String, String> users;

    @Override
    public void init() throws ServletException {
        users = new ConcurrentHashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getRemoteAddr();
        String agent = req.getHeader("User-Agent");
        users.put(ip, agent);

        resp.setContentType("text/html");
        resp.getWriter().write("<ul>");

        for (Map.Entry<String, String> s : users.entrySet()) {
            if (s.getKey().equals(ip)) {
                resp.getWriter().write("<b> <li> " + ip + " :: " + agent + "</b>");
            } else {
                resp.getWriter().write("<li>" + ip + " :: " + agent);
            }
        }

        resp.getWriter().write("<ul>");
    }
}
