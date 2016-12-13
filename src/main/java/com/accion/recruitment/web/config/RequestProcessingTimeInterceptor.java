package com.accion.recruitment.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Manas
 *
 */
public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LogManager.getLogger(RequestProcessingTimeInterceptor.class);

	private String[] excludeUrls;

	public void setExcludeUrls(final String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		String requestUrl = request.getRequestURL().toString();
		if (!exclude(requestUrl)) {
			long startTime = (Long) request.getAttribute("startTime");
			logger.info("Request URL::" + requestUrl + ":: Time Taken=" + (System.currentTimeMillis() - startTime)
					+ " Ms.");
		}
	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		String requestUrl = request.getRequestURL().toString();
		if (exclude(requestUrl)) {
			return true;
		}
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	private boolean exclude(final String url) {
		for (String exclude : excludeUrls) {
			if (StringUtils.contains(url, exclude)) {
				return true;
			}
		}
		return false;
	}

}
