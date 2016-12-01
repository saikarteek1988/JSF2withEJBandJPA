/**
 * 
 */
package com.jsf.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * @author NAPC0383
 *
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
	   private ExceptionHandlerFactory parent;
	 
	   // this injection handles jsf
	   public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
	    this.parent = parent;
	   }
	 
	    @Override
	    public ExceptionHandler getExceptionHandler() {
	 
	        ExceptionHandler handler = new  CustomExceptionHandler(parent.getExceptionHandler());
	 
	        return handler;
	    }
	 
	}
