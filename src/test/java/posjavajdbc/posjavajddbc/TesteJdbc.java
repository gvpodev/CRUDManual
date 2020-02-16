package posjavajdbc.posjavajddbc;

import java.util.List;

import org.junit.Test;

import DAO.UserBancoDAO;
import Model.BeanUserFone;
import Model.Telefone;
import Model.Userbancojava;

public class TesteJdbc {

	@Test
	public void Testando() {
		UserBancoDAO userBancoDAO = new UserBancoDAO();
		Userbancojava userbancojava = new Userbancojava();

		userbancojava.setNome("Deco");
		userbancojava.setEmail("deco@20.com");

		userBancoDAO.salvar(userbancojava);
	}

	@Test
	public void initListar() {
		UserBancoDAO dao = new UserBancoDAO();
		try {
			List<Userbancojava> list = dao.listar();

			for (Userbancojava userbancojava : list) {
				System.out.println(userbancojava);
				System.out.println(
						"-------------------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initBuscar() {
		UserBancoDAO dao = new UserBancoDAO();

		Userbancojava userbancojava;
		try {
			userbancojava = dao.buscarId(4L);
			System.out.println(userbancojava);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {
		try {
			UserBancoDAO dao = new UserBancoDAO();
			Userbancojava objetoBanco = dao.buscarId(4L);
			objetoBanco.setNome("Nome mudado com o método atualizado.");

			dao.atualizar(objetoBanco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initDeletar() {
		try {
			UserBancoDAO dao = new UserBancoDAO();
			dao.deletar(1L);
			dao.deletar(2L);
			dao.deletar(4L);
			dao.deletar(6L);
			dao.deletar(7L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("(21)912345678");
		telefone.setTipo("casa");
		telefone.setUsuario(3L);
		
		UserBancoDAO dao = new UserBancoDAO();
		dao.salvarTelefone(telefone);
	}
	
	@Test
	public void testeCarregaFoneUser() {
		UserBancoDAO dao = new UserBancoDAO();
		
		List<BeanUserFone> beanUserFones = dao.listaUserFone(2L);
		
		for(BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("-----------------------------------------------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserBancoDAO dao = new UserBancoDAO();
		dao.deleteFonesPorUser(3L);
	}

}
