package com.hengsu.uliketu.core.interceptor;

import com.google.common.cache.Cache;
import com.hengsu.uliketu.core.ErrorCode;
import com.hengsu.uliketu.core.annotation.IgnoreAuth;
import com.hengsu.uliketu.core.model.AuthModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by haiquanli on 15/11/20.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, AuthModel> sessionCache;

    private final static String AUTHORIZATION = "Authorization";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //放过只过滤已sure下的请求
//        if(!request.getRequestURI().startsWith("/sure")){
//            return true;
//        }

        boolean isIgnore = checkIgnore(handler);

        if (!isIgnore) {
            //取得auth token
            String authToken = request.getHeader(AUTHORIZATION);
            if (StringUtils.isEmpty(authToken)) {
                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_MUST);
            }

            AuthModel authModel = sessionCache.getIfPresent(authToken);
            if (null == authModel) {
                ErrorCode.throwBusinessException(ErrorCode.AUTH_TOKEN_INVALID);
            }

            //将UserId设置到request里面
            request.setAttribute("userId", authModel.getId());
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean checkIgnore(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IgnoreAuth ignoreAuth = handlerMethod.getMethodAnnotation(IgnoreAuth.class);
        RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);
        if (null != ignoreAuth) {
            return true;
        }

        return false;
    }
}
