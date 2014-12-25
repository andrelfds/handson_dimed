package com.handson.controller;



import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import com.handson.managedbean.LoginMB;

/**
 * Responsável por manipular requisições de usuário, permitindo acesso ao 
 * conteúdo da aplicação somente no caso do usuário já ter se autenticado.
 *
 * @author pedrobrigatto
 */
public class ListenerAutenticacao implements PhaseListener {

  private static final String PAGINA_LOGIN = "index.xhtml";
  
  public void afterPhase(PhaseEvent event) {
    
    FacesContext contexto = event.getFacesContext();
    String pagina = contexto.getViewRoot().getViewId();
    
    System.out.println(">>>>>>>>>>>>>>>>> ListenerAutenticacao.afterPhase() "
            + "para página de ID " + event.getFacesContext().getViewRoot().getViewId());
    
    if (!(pagina.lastIndexOf(PAGINA_LOGIN) > -1)) {
      HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
      Object usuario = sessao.getAttribute(LoginMB.SESSION_USER);
      
      if (usuario == null) {
        NavigationHandler navHandler = contexto.getApplication().getNavigationHandler();
        navHandler.handleNavigation(contexto, null, LoginMB.SESSION_NOT_EXIST);
      }
    }
  }

  public void beforePhase(PhaseEvent event) {    
    if (event.getFacesContext().getViewRoot() != null) {
      System.out.println(">>>>>>>>>>>>>>>>> ListenerAutenticacao.beforePhase() "
            + "para página de ID " + event.getFacesContext().getViewRoot().getViewId());      
    } else {
      System.out.println(">>>>>>>>>>>>>>>>> ListenerAutenticacao.beforePhase() "
            + "indicando view root ainda nula");
    }
  }

  public PhaseId getPhaseId() {
    return PhaseId.RESTORE_VIEW;
  }
}
