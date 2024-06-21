package com.lnt.postanalog.config;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



import com.lnt.postanalog.constants.AuditCodes;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface SecureActionDefinition {

	@Enumerated(EnumType.STRING)
	AuditCodes code();
}