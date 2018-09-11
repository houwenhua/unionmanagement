package cn.pzhu.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import cn.pzhu.model.SysUser;

/**
 * 过滤器
 * @author 逃离
 *
 */
@ServletComponentScan  
@Component 
@WebFilter(filterName="loginFilter",urlPatterns = "*.html") 
public class LoginFilter implements Filter {

	private static String LOGIN_PAGE = "/login.html";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String currentUrl = req.getServletPath();//获取访问路劲

		HttpSession session = req.getSession();//得到session

		System.out.println("登录过滤器LoginFilter【检查是否登录,如果没登录则重定向到login.html】");

		if (currentUrl.equals("")) {
			currentUrl = currentUrl + "/";
			
		} else if ((currentUrl.startsWith("/")) && (!currentUrl.startsWith(LOGIN_PAGE))) {
			SysUser user = (SysUser) session.getAttribute("user");
			if (user == null) {
				res.sendRedirect(req.getContextPath() + LOGIN_PAGE);//跳转到登录页面

				return;
			}
		}

		chain.doFilter(request, response);//放行

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
