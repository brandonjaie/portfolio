/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.campkobold;

import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Brandon
 */
@Controller
class ErrorController {
    // #1 - map this end point to /error

    @RequestMapping(value = {"/errors"}, method = RequestMethod.GET)
    // #2 - note the use of the Spring Model object rather than a Map
    public String customError(HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        // #3 - retrieve some useful information from the request
        try {
            Integer statusCode
                    = (Integer) request.getAttribute("javax.servlet.error.status_code");
        } catch (NullPointerException ex) {
            Integer statusCode
                    = (Integer) request.getAttribute("javax.servlet.error.status_code");

            HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
            Throwable throwable
                    = (Throwable) request.getAttribute("javax.servlet.error.exception");
            String exceptionMessage = null;
            exceptionMessage = httpStatus.getReasonPhrase();

            String requestUri
                    = (String) request.getAttribute("javax.servlet.error.request_uri");
            if (requestUri == null) {
                requestUri = "Unknown";
            }
            // #4 - format the message for the view
            String message = MessageFormat.format("{0} {1}",
                    statusCode, exceptionMessage);
            // #5 - put the message in the model object
            model.addAttribute("errorMessage", message);

        }

        return "errors";

    }
}
