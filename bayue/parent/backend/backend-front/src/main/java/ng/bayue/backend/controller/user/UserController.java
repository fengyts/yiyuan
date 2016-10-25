package ng.bayue.backend.controller.user;

import ng.bayue.backend.ao.user.UserAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/user"})
public class UserController {
	
	@Autowired
	private UserAO userAO;

}
