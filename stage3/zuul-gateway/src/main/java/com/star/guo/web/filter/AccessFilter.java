package com.star.guo.web.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 访问拦截器
 * 
 * @author Star.Guo
 *
 */
@Component
public class AccessFilter extends ZuulFilter {
	private static final Logger log = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	// 是否要过滤，false不过滤，true则需要过滤
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	// 具体实施过滤逻辑的地方
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		HttpServletResponse response = requestContext.getResponse();
		// 是否登录
		boolean loginSuccess = false;
		// 是否权限认证成功
		boolean authOk = false;
		String loginId = request.getParameter("loginId");
		String authId = request.getParameter("authId");
		// 假设传入的参数loginId不为空则代表登录成功,authId不为空则代表该访问有权限
		loginSuccess = StringUtils.isNotEmpty(loginId);
		authOk = StringUtils.isNotEmpty(authId);
		authOk = StringUtils.isNotEmpty(authId);
		if (loginSuccess && authOk) {
			log.debug("验证成功");
			requestContext.setSendZuulResponse(true);
			return null;
		}
		requestContext.setSendZuulResponse(false);
		// 不成功则根据请求类型进行处理
		if (isAjaxRequest(request)) {
			try {
				if (!loginSuccess) {
					log.debug("未登录,非ajax请求,返回401");
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED REQUEST");
				} else {
					log.debug("鉴权失败,非ajax请求,返回403");
					response.sendError(403, "UNAUTHORIZED REQUEST");

				}
			} catch (IOException e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				log.error("run", e);
			}
		} else {
			try {
				if (!loginSuccess) {
					log.debug("未登录,非ajax请求,调转到指定页面");
					turnPage(response, "https://www.baidu.com");

				} else {
					log.debug("鉴权失败,非ajax请求,返回403");
					response.sendError(403, "UNAUTHORIZED REQUEST");
				}
			} catch (IOException e) {
				log.error("redirect fail", e);
			}
		}

		return null;
	}

	@Override
	// 过滤器类型
	public String filterType() {
		// pre：路由之前 routing：路由之时 post： 路由之后 error：发送错误调用
		return FilterConstants.PRE_TYPE;
	}

	@Override
	// 过滤的顺序
	public int filterOrder() {
		return 2;
	}

	/**
	 * 判断是否是Ajax请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		return (request.getHeader("accept") != null && request.getHeader("accept").contains("application/json"))
				|| (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
	}

	/**
	 * 跳转页面
	 * 
	 * @param response
	 * @param url      需要跳转页面的全路径
	 */
	private void turnPage(HttpServletResponse response, String url) {
		try {
			response.setContentType("text/html; charset=utf-8");
			java.io.PrintWriter out = response.getWriter();
			out.println("<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> ");
			out.println("<script>");
			out.println("window.open ('" + url + "','_top')");
			out.println("</script>");
			out.println("</html>");
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("responseHTML", e);
		}
	}
}
