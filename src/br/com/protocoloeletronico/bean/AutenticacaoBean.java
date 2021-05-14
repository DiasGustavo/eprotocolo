package br.com.protocoloeletronico.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.protocoloeletronico.dao.FuncionarioDAO;
import br.com.protocoloeletronico.dao.OrgaoDAO;
import br.com.protocoloeletronico.domain.Funcionario;
import br.com.protocoloeletronico.domain.Orgao;
import br.com.protocoloeletronico.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {

	private Funcionario funcionarioLogado;
	private Orgao orgaoLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		if (orgaoLogado == null) {
			orgaoLogado = new Orgao();
		}
		this.funcionarioLogado = funcionarioLogado;
	}

	public Orgao getOrgaoLogado() {
		return orgaoLogado;
	}

	public void setOrgaoLogado(Orgao orgaoLogado) {
		this.orgaoLogado = orgaoLogado;
	}

	@SuppressWarnings("deprecation")
	public Date getPegaDataAtual() {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);

		return date;
	}

	public String autenticar() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			funcionarioLogado = fdao.autenticar(funcionarioLogado.getLogin(),
					DigestUtils.md5Hex(funcionarioLogado.getSenha()));

			OrgaoDAO odao = new OrgaoDAO();
			
			orgaoLogado = odao
					.buscarPorRegistro(DigestUtils.md5Hex(funcionarioLogado.getOrgao().getCnpj() + funcionarioLogado.getOrgao().getNome()),getPegaDataAtual());
			
			if (orgaoLogado == null) {
				orgaoLogado = new Orgao();				
				orgaoLogado.setStatus("I");
				FacesUtil.addMsgInfo("Sistema apenas para Consulta!");				
			}

			if (funcionarioLogado == null) {
				FacesUtil.addMsgErro("login e/ou senha inválidos!");
				return null;
			} else {
				FacesUtil.addMsgInfo("Funcionário Logado com Sucesso!");
				return "/pages/protocolo/protocoloPesquisa.xhtml?faces-redirect=true";
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao autenticar no sistema: " + ex.getMessage());
			return null;
		}
	}

	public String sair() {
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
}
