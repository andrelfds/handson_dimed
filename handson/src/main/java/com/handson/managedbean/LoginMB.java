/**
 * 
 */
package com.handson.managedbean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import com.handson.controller.ControladorAcesso;
import com.handson.ejb.remote.UserBeanRemote;
import com.handson.entity.User;

/**
 * @author cassio
 * 
 */

@ManagedBean
@SessionScoped
public class LoginMB {

	private static final String	LOGIN_SUCESS		= "sample.xhtml";
	public static final String	LOGIN_FAIL			= "login_falha";
	public static final String	SESSION_NOT_EXIST	= "sessao_invalida";
	private static final String	OUTCOME_LOGOUT		= "logout";
	public static final String	SESSION_USER		= "usuario";

	private User				login;

	@EJB
	private UserBeanRemote		ejb;

	private ControladorAcesso	controladorAcesso;

	public LoginMB() {
	}
	
	@PostConstruct
	public void inicializar() {
		login = new User();
		controladorAcesso = new ControladorAcesso();
		Logger.getLogger(LoginMB.class).log(Level.INFO, ">>>>>>>>>>>>> Inicializando um bean de login.");
	}

	/**
	 * Utilizado para tentativas de login no sistema, confrontando dados
	 * fornecidos * pelo usu�rio com registros de usu�rios cadastrados.
	 * 
	 * @return Outcome associado a fracasso ou sucesso na tentativa de login.
	 * @throws Exception
	 */
	public String doLogin() throws Exception {

		if (!ejb.findUserLogado(login)) {
			User usuarioLogado = ejb.findUserByPassword(login);
			usuarioLogado.setStatus(User.ATIVO);
			HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessao.setAttribute(SESSION_USER, usuarioLogado);
			controladorAcesso.configurarAcesso();
			// new ServicoAtivarUsuario(usuarioLogado).cadastrar();
			Logger.getLogger(LoginMB.class).log(Level.INFO, ">>>>>>>>>>>>> Logado Usuario: " + usuarioLogado.getName());
			return LOGIN_SUCESS;
		}
		return LOGIN_FAIL;
	}

	/**
	 * * Utilizado para finalizar uma sess�o de um usu�rio no sistema. * * @return
	 * Outcome associado a fracasso ou sucesso na tentativa de logout.
	 */
	public String doLogout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		User usuarioSessao = (User) sessao.getAttribute(SESSION_USER);
		if (usuarioSessao != null) {
			usuarioSessao.setStatus(User.INATIVO);
			// new ServicoDesativarUsuario(usuarioSessao).cadastrar();
		}
		context.getExternalContext().invalidateSession();
		
		 FacesMessage msg = new FacesMessage("LOGOUT  " + usuarioSessao.getName());
         FacesContext.getCurrentInstance().addMessage(null, msg);
		return OUTCOME_LOGOUT;
		
	}

	/**
	 * * M�todo utilizado para verificar se um usu�rio tentando logar na
	 * aplica��o * j� n�o possui alguma sess�o aberta em outro navegador ou
	 * outra aba. A * aplica��o est� barrando m�ltiplos acessos simult�neos de
	 * um usu�rio. * * @return <code>true</code> se j� existir uma sess�o ativa
	 * para o usu�rio. * <code>false</code> caso contr�rio.
	 */

	public ControladorAcesso getControladorAcesso() {
		return controladorAcesso;
	}

	public User getLogin() {
		return login;
	}

	public void setLogin(User login) {
		this.login = login;
	}

}
