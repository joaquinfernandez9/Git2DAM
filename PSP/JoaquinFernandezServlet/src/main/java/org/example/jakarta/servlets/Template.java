package org.example.jakarta.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.jakarta.Const;
import org.example.jakarta.listeners.ThymeLeafListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "Play", value = {"/play"})
public class Template extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                ThymeLeafListener.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);

        HttpSession session = request.getSession();

        String template;


        Random r = new Random();
        int number = Integer.parseInt(request.getParameter(Const.NUMBER));
        if ((number > 0 && number <= 10) && !request.getParameter(Const.NUMBER).isEmpty()) {

            int randomNumber;
            int counter = Integer.parseInt(getServletContext().getAttribute(Const.COUNTER).toString());

            if (counter <= 3 && counter > 0) {
                counter--;
                getServletContext().setAttribute(Const.COUNTER, counter);
            }


            int numberServlet = Integer.parseInt(getServletContext().getAttribute(Const.NUMBER).toString());
            if (numberServlet == 0) {
                randomNumber = r.nextInt(10);
                getServletContext().setAttribute(Const.NUMBER, randomNumber);
            } else {
                randomNumber = numberServlet;
            }


            String message;

            if (number > randomNumber) {
                message = Const.THE_NUMBER_IS_TOO_BIG;
            } else if (number < randomNumber) {
                message = Const.THE_NUMBER_IS_TOO_SMALL;
            } else {
                message = Const.YOU_GUESSED_IT;

            }

            context.setVariable(Const.RESPONSE, message);
            context.setVariable(Const.ATTEMPTS, Const.YOU_HAVE + counter + Const.ATTEMPTS_LEFT);

            if (counter == 0) {
                getServletContext().setAttribute(Const.NUMBER, 0);
                getServletContext().setAttribute(Const.COUNTER, 3);
                context.setVariable(Const.ANOTHER_NUM, Const.YOU_HAVE_NO_ATTEMPTS_LEFT_OTHER_NUMBER_HAS_BEEN_GENERATED);
            }

            template = Const.PARAMETERS;


        } else {
            context.setVariable(Const.ERROR, Const.YOU_MUST_ENTER_A_NUMBER);
            template = Const.ERROR;
        }

        try {
            templateEngine.process(template, context, response.getWriter());
        } catch (IOException e) {
            log(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

}
