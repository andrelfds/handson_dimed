package com.handson.controller;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.handson.entity.User;
import com.handson.managedbean.LoginMB;

/**
 * Controlador utilizado especificamente para verificação de permissões de 
 * acesso a funcionalidades oferecidas pela aplicação.
 *
 * @author Cassio
 * @since 30/11/2014
 */
public class ControladorAcesso {
  
  private boolean permissaoAdministrador;
  private boolean permissaoFuncionario;
  private boolean permissaoComum;

  public boolean isPermissaoAdministrador() {    
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    User UserSessao = (User) sessao.getAttribute(LoginMB.SESSION_USER);
    
    if (UserSessao != null) {
      permissaoAdministrador  = (UserSessao.getTipo() == User.ADMINISTRADOR);
    } else {
      permissaoAdministrador = false;
    }
    return permissaoAdministrador;
  }

  public boolean isPermissaoFuncionario() {
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    User UserSessao = (User) sessao.getAttribute(LoginMB.SESSION_USER);
    
    if (UserSessao != null) {
      permissaoAdministrador  = (UserSessao.getTipo() == User.ADMINISTRADOR);
      
      if (permissaoAdministrador) {
        permissaoFuncionario = true;
      } else {
        permissaoFuncionario  = (UserSessao.getTipo() == User.FUNCIONARIO);
      }
    } else {
      permissaoFuncionario = false;
    }
    return permissaoFuncionario;
  }

  public boolean isPermissaoComum() {
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    User UserSessao = (User) sessao.getAttribute(LoginMB.SESSION_USER);
    
    if (UserSessao != null) {
      permissaoAdministrador  = (UserSessao.getTipo() == User.ADMINISTRADOR);
      permissaoFuncionario  = (UserSessao.getTipo() == User.FUNCIONARIO);
      
      if (permissaoAdministrador || permissaoFuncionario) {
        permissaoComum = true;
      } else {
        permissaoComum  = (UserSessao.getTipo() == User.CONVIDADO);
      }
    } else {
      permissaoComum = false;
    }
    return permissaoComum;
  }
  
  /**
   * Método utilizado para configurar o perfil de acesso do usuário logado às
   * funcionalidades da aplicação.
   */
  public void configurarAcesso() {
    HttpSession sessao = (HttpSession) 
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    User UserSessao = (User) sessao.getAttribute(LoginMB.SESSION_USER);
    
    if (UserSessao != null) {
      
      Logger.getLogger("ControladorAcesso").log(Level.INFO, 
              ">>>>>>>>>>>>>> Usuario da sessaoo tem tipo {0}", UserSessao.getTipo());
      
      permissaoAdministrador  = (UserSessao.getTipo() == User.ADMINISTRADOR);
      
      if (permissaoAdministrador) {
        permissaoFuncionario = true;
      } else {
        permissaoFuncionario  = (UserSessao.getTipo() == User.FUNCIONARIO);
        permissaoComum  = (UserSessao.getTipo() == User.CONVIDADO);
      }
    }
  }
}
