package com.xiyou.energy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiyou.energy.pojo.User;
import com.xiyou.energy.service.UserRoleService;
import com.xiyou.energy.service.UserService;
import com.xiyou.energy.util.Result;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	// 用户登录
	@PostMapping("/login")
	public Result login(@RequestBody User user) {
		System.out.println(user.getName());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			//将用户数据token传递到Realm中进行验证
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			user = userService.getByName(user.getName());
			user.setPassword(null);
			user.setSalt(null);
			return Result.success(user);
		} catch (AuthenticationException e) {
			return Result.fail("用户名或密码不正确");
		}

	}

	// 用户注册
	@PostMapping("/addUser")
	public Result add(@RequestBody User user) {
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		// 加密次数
		int times = 2;
		String algorithmName = "md5";
		String encodedPassword = new SimpleHash(algorithmName, user.getPassword(), salt, times).toString();
		user.setSalt(salt);
		user.setPassword(encodedPassword);
		return userService.add(user);
	}
	

}
