package com.gpnu.server.jwt;

import com.gpnu.entity.system.PrivilegeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeCheck {
    PrivilegeType privilegeType();
}
