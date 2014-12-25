package com.handson.services;

import com.handson.entity.User;



/**
 * Serviço para autenticação de usuário.
 * 
 * @author pedrobrigatto
 */
public class ServicoLogin extends ServicoVerificador {
  
  private User user;
  
  public ServicoLogin(User user ) {
    this.user = user;
  }
  
  @Override
  public boolean executar() {
    return true;
    		/*(DaoFactory.getFactory(propriedades.getProperty(TIPO_EIS)).
            getUsuarioDao().consultarUsuario(true, usuario.getUsername(), 
            usuario.getSenha()).size() > 0);*/
  }
}
