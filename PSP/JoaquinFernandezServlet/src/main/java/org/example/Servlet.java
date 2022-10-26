package org.example;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servicios.Test;

import java.io.IOException;


@WebServlet(name="Servlet", value= "/Servlet")
public class Servlet extends HttpServlet {

    private final Test test;

    @Inject
    public Servlet(Test test) {
        this.test = test;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(test.msg());
    }



}
