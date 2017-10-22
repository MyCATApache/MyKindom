package edu.ldcollege.ctrl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ldcollege.service.AuthorizationService;
import edu.ldcollege.service.UserService;
import edu.ldcollege.util.RestResult;
import edu.ldcollege.util.RestStatus;

/**
 * 
 * authorization
 * @author Raindrops on 20171018
 *
 */
@RestController
public class AuthorizationController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	AuthorizationService authorizationService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/auth/login",method = RequestMethod.POST)
	@ResponseBody()
	public RestResult authorization(String username,String password) {
		if (logger.isInfoEnabled()) {
			logger.info("POST /login [username={},password={}]",username,password);
		}
		Map<String, String> token = new HashMap<>();
		String result = authorizationService.authorization(username, password);
		token.put("token", result);
		return RestResult.success( token);
	}
	
	@RequestMapping(value = "/auth/register",method = RequestMethod.POST)
	@ResponseBody
	public RestResult register(String username,String password) {
		if (logger.isInfoEnabled()) {
			logger.info("POST /signup 注册 [username={},password={}]",username,password);
		}
		authorizationService.authorization(username, password);
		return RestResult.success( null);
	}
	
}
