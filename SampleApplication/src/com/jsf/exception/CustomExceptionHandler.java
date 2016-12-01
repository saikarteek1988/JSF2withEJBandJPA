/**
 * 
 */
package com.jsf.exception;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.event.SystemEvent;

/**
 * @author NAPC0383
 *
 */
public class CustomExceptionHandler extends ExceptionHandler {

	private static final Logger log = Logger.getLogger(CustomExceptionHandler.class.getCanonicalName());
    private ExceptionHandler wrapped;
 
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    @Override
    public void handle() throws FacesException {
 
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context =
                    (ExceptionQueuedEventContext) event.getSource();
 
            // get the exception from context
            Throwable t = context.getException();
 
            final FacesContext fc = FacesContext.getCurrentInstance();
            final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
            final NavigationHandler nav = fc.getApplication().getNavigationHandler();
 
            //here you do what ever you want with exception
            try {
 
                //log error ?
                log.log(Level.SEVERE, "Critical Exception!", t);
 
                //redirect error page
                requestMap.put("exceptionMessage", t.getMessage());
                nav.handleNavigation(fc, null, "/error");
                fc.renderResponse();
 
                // remove the comment below if you want to report the error in a jsf error message
                //JsfUtil.addErrorMessage(t.getMessage());
 
            } finally {
                //remove it from queue
                i.remove();
            }
        }
        //parent hanle
        getWrapped().handle();
    }

	@Override
	public ExceptionQueuedEvent getHandledExceptionQueuedEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ExceptionQueuedEvent> getHandledExceptionQueuedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Throwable getRootCause(Throwable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ExceptionQueuedEvent> getUnhandledExceptionQueuedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isListenerForSource(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processEvent(SystemEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub
		
	}

}
