package com.wool.hermes.intercepter;

import com.google.gson.GsonBuilder;
import com.wool.sdk.util.TokenHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Locale;

/**
 * Created by wanglin on 2016/10/14.
 * token name  : X-WOOL-Token
 * token format: v2-{AK}-{ExpireTime}-{Signature}
 */
public class AccessCheckHandler {

    private static final Logger log = LoggerFactory.getLogger(AccessCheckHandler.class);

    public final static String X_WOOL_TOKEN = "X-WOOL-Token";

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private AccessCheckConfig accessCheckConfig;

    @Pointcut("@annotation(com.wool.hermes.intercepter.AccessCheck)")
    private void controllerPointcut() {

    }

    @Around("controllerPointcut()")
    public Object verifyHttpRequestHeader(ProceedingJoinPoint joinPoint) throws Throwable {

        log.debug("==> verify access token begin...");

        // servlet request and response
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        // token
        String token = request.getHeader(X_WOOL_TOKEN);
        log.debug("CheckRequestHeader: token = {}", token);
        if (StringUtils.isEmpty(token)) {
            log.error("ChectRequestHeader: Token is empty");
            writeResponse(response);
            return null;
        }

        // v2-{AK}-{ExpireTime}-{Signature} split
        String[] tokenSplit = token.split("-");
        String ak = tokenSplit[1];
        String sk = accessCheckConfig.getAppSecret(ak);

        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        String requestQueryString = request.getQueryString();

        // controller method
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // construct requestBody
        Object[] args = joinPoint.getArgs();
        Object bodyArgs = null;
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] == null) {
                continue;
            }
            if (parameters[i].isAnnotationPresent(RequestBody.class)) {
                bodyArgs = args[i];
            }
        }
        String requestBody = null;
        if (bodyArgs != null) {
            requestBody = new GsonBuilder().disableHtmlEscaping().create().toJson(bodyArgs);
        }
        log.debug("===>get the equest url:{}; and request method:{}", requestURI, requestMethod);

        //token validate
        TokenHelper tokenHelper = new TokenHelper(ak,sk);
        if (tokenHelper.verifyToken(token, requestURI, requestMethod, requestQueryString, requestBody)) {
            return joinPoint.proceed();
        } else {
            log.error("CheckRequestHead: verifyToken fail");
            writeResponse(response);
            return null;
        }
    }

    private void writeResponse(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,messageSource.getMessage("app.sign.error",null, Locale.SIMPLIFIED_CHINESE));
    }
}
