package com.example.product_service_12052024.commons;

import com.example.product_service_12052024.dtos.UserDto;
import com.example.product_service_12052024.exception.clientexceptions.UnauthorisedClientErrorException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthUtil {
   private final RestTemplate restTemplate;

   public AuthUtil (RestTemplate restTemplate) {
	  this.restTemplate = restTemplate;
   }


   public UserDto validateToken (String tokenValue)
		   throws UnauthorisedClientErrorException {
//	   Need to call user service validate token method.
//	   For than we need rest template to communicate.
//
//	  return restTemplate.getForObject ("http://localhost:8181/users/validateToken/" + tokenValue,
//			  UserDto.class);
//   }
//

	  UserDto userDto = restTemplate.getForObject ("http://USERSERVICENOV24/users/validateToken/" + tokenValue,
			  UserDto.class);

	  if (userDto == null) {
		 throw new UnauthorisedClientErrorException ("Unauthorised:Invalid Token");
	  }
	  return userDto;


//	  try {
//		 return restTemplate.getForObject ("http://USERSERVICENOV24/users/validateToken/" + tokenValue,
//				 UserDto.class);
//	  } catch (HttpClientErrorException.Unauthorized exception) {
//		 throw new UnauthorisedClientErrorException ("Unauthorised: valid token not found");
//	  } catch (HttpClientErrorException e) {
//		 throw new UnauthorisedClientErrorException ("An error occurred while validating the token " + e.getMessage ());
//	  } catch (Exception e) {
//		 throw new UnauthorisedClientErrorException ("Something bad happened");
//	  }
   }
}
