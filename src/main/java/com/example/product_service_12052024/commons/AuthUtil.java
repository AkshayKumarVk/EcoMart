package com.example.product_service_12052024.commons;

import com.example.product_service_12052024.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthUtil {
   private final RestTemplate restTemplate;

   public AuthUtil (RestTemplate restTemplate) {
	  this.restTemplate = restTemplate;
   }


   public UserDto validateToken (String tokenValue) {
//	   Need to call user service validate token method.
//	   For than we need rest template to communicate.

	  return restTemplate.getForObject ("http://localhost:8181/users/validateToken/" + tokenValue,
			  UserDto.class);
   }
}
