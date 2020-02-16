package DAO;

import java.beans.Beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.BeanUserFone;
import Model.Telefone;
import Model.Userbancojava;
import posjavajdbc.posjavajddbc.App;

public class UserBancoDAO {
	private Connection connection;

	public UserBancoDAO() {
		connection = App.getConnection();
	}

	public void salvar(Userbancojava userbancojava) {
		try {
			String sql = "insert into userbancojava (nome, email) values(?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userbancojava.getNome());
			insert.setString(2, userbancojava.getEmail());
			insert.execute();
			connection.commit(); // Salva no banco
		} catch (Exception e) {
			try {
				connection.rollback(); // Revert a operacao no banco
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Userbancojava> listar() throws Exception {
		List<Userbancojava> list = new ArrayList<Userbancojava>();

		String sql = "select * from userbancojava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Userbancojava userbancojava = new Userbancojava();
			userbancojava.setId(resultado.getLong("id"));
			userbancojava.setNome(resultado.getString("nome"));
			userbancojava.setEmail(resultado.getString("email"));

			list.add(userbancojava);
		}

		return list;
	}

	public Userbancojava buscarId(Long id) throws Exception {
		Userbancojava retorno = new Userbancojava();

		String sql = "select * from userbancojava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}

		return retorno;
	}

	public List<BeanUserFone> listaUserFone(Long idUser) {
		List<BeanUserFone> beanUserfone = new ArrayList<BeanUserFone>();
		String sql = " select * from telefoneuser as fone ";
		sql += " inner join userbancojava as userb ";
		sql += " on fone.usuariopessoa = userb.id ";
		sql += " where userb.id = " + idUser;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				beanUserfone.add(userFone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return beanUserfone;
	}

	public void atualizar(Userbancojava userbancojava) {
		try {
			String sql = "update userbancojava set nome = ? where id = " + userbancojava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userbancojava.getNome());

			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {
		try {
			String sql = "delete from userbancojava where id = " + id;

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void deleteFonesPorUser(Long idUser) {
		try {
			String sqlFone = " delete from telefoneuser where usuariopessoa = " + idUser;
			String sqlUser = " delete from userbancojava where id = " + idUser;

			PreparedStatement statement = connection.prepareStatement(sqlFone);
			statement.executeUpdate();
			connection.commit();

			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
