package com.xiyou.energy.filter;

import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiyou.energy.service.PermissionService;
import com.xiyou.energy.util.JsonUtils;
import com.xiyou.energy.util.Result;
import com.xiyou.energy.util.SpringContextUtils;

public class URLPathMatchingFilter extends PathMatchingFilter {
	@Autowired
	PermissionService permissionService;

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		 
		if (null == permissionService)
			permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);

		String requestURI = getPathWithinApplication(request);
//		System.out.println(requestURI);
		
		Subject subject = SecurityUtils.getSubject();
//		System.out.println(subject.isAuthenticated());
//		System.out.println(subject.getSession());
		// 如果没有登录，就跳转到登录页面
		// 判断用户是否登录
		if (!subject.isAuthenticated()) {
			// WebUtils.issueRedirect(request, response, "/login");
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("application/json");
			Result result = Result.fail("用户未登录");
			// 写回给客户端
//			System.out.println("未登录");
			PrintWriter out = httpResponse.getWriter();

			out.write(JsonUtils.objectToJson(result));

			// 刷新和关闭输出流

			out.flush();

			out.close();
			return false;
		}

		// 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
		boolean needInterceptor = permissionService.needInterceptor(requestURI);
		System.out.println(needInterceptor);
		if (!needInterceptor) {
			return true;
		} else {
			boolean hasPermission = false;
			String userName = subject.getPrincipal().toString();
			Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
			for (String url : permissionUrls) {
				// 这就表示当前用户有这个权限
				if (requestURI.contains(url)) {
					hasPermission = true;
					break;
				}
			}

			if (hasPermission)
				return true;
			else {
				UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");

				subject.getSession().setAttribute("ex", ex);

				// WebUtils.issueRedirect(request, response, "/unauthorized");

				httpResponse.setCharacterEncoding("UTF-8");
				httpResponse.setContentType("application/json");
				Result result = Result.fail("用户无权限");
				// 写回给客户端
				PrintWriter out = httpResponse.getWriter();

				out.write(JsonUtils.objectToJson(result));

				// 刷新和关闭输出流

				out.flush();

				out.close();
				return false;
			}

		}
	}

}