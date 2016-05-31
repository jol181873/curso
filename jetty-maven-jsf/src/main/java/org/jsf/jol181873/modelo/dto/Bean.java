package org.jsf.jol181873.modelo.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bean<T> implements Serializable {
	private static final Logger log = LogManager.getLogger(Bean.class);
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	public static final String ATRIBUTOS = "atributos";
	public static final String METODOS = "metodos";

	public Set<ConstraintViolation<T>> validar() {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate((T) this);
		logErroresValidacion(constraintViolations, ATRIBUTOS);

		return constraintViolations;
	}

	protected void logErroresValidacion(Set<ConstraintViolation<T>> constraintViolations, String tipoDeCampoValidado) {
		if (!constraintViolations.isEmpty()) {
			for (ConstraintViolation<T> cons : constraintViolations) {
				log.error("Error validando los " + tipoDeCampoValidado + " del bean " + this);
				log.error(cons.getPropertyPath().iterator().next().getName() + ": " + cons.getMessage());
			}
		}
	}
}
