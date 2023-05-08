package com.gpnu.server.jwt;

import com.gpnu.core.exception.ErrorCodes;
import com.gpnu.core.exception.GPNUException;
import com.gpnu.entity.system.SystemPrivilege;
import com.gpnu.entity.system.User;
import com.gpnu.server.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    SystemService systemService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object object) throws Exception {
        //从http header中获取token
        String token = httpServletRequest.getHeader("token");

        //判断是否loginrequired注解

        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        User user = null;
        if (method.isAnnotationPresent(LoginRequired.class)) {
            //解析token 验证jwt
            LoginRequired annotation = method.getAnnotation(LoginRequired.class);
            if (annotation.required()) {

                if (token == null) {
                    throw new GPNUException("login first", ErrorCodes.SYSTEM_EXCEPTION);
                }

                String userId = JwtManager.parseJwt(token).get("id").toString();

                user = systemService.findUserById(Long.parseLong(userId));
                if (user == null) {
                    throw new GPNUException("user not exists", ErrorCodes.ERROR_USER_NOT_EXISTS);
                }

                Boolean verify = JwtManager.isVerify(token, user.getPwd());
                if (!verify) {
                    throw new GPNUException("password error", ErrorCodes.ERROR_PASSWORD);
                }

                ContextUtil.setCurrentUser(user);

                //校验权限

                if (method.isAnnotationPresent(PrivilegeCheck.class)) {
                    PrivilegeCheck privilegeCheck = method.getAnnotation(PrivilegeCheck.class);
                    SystemPrivilege systemPrivilege = systemService.findSystemPrivilege(user.getTeam(), privilegeCheck.privilegeType());
                    if (systemPrivilege == null) {
                        throw new GPNUException("permission denied", ErrorCodes.ERROR_PERMISSION);
                    }
                }

                return true;
            }
        }
        return true;

    }

}
