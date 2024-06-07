package com.example.product_service_12052024.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class HomePage {


   @RequestMapping()
   public void homepage (HttpServletResponse httpServletResponse) throws IOException {
	  httpServletResponse.sendRedirect ("https://akshaykumarvk.me");
   }
}
