package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntidadeDaoTest {

	private static DAO<Entidade> entidadeDao;
	private static Aluno aluno;
	private static Entidade entidade;
	private static Endereco endereco;
	private static Filiado f1;
	private static Filiado filiadoProf;
	private static Professor professor;

	@BeforeClass
	public static void setUp() {
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		entidade = new Entidade();
		entidade.setEndereco(endereco);
		entidade.setNome("Entidade");
		entidade.setTelefone1("(086)1234-5432");

		entidadeDao = new DAOImpl<Entidade>(Entidade.class);
	}

	public static void clearDatabase() {
		List<Entidade> all = entidadeDao.list();
		for (Entidade each : all) {
			entidadeDao.delete(each);
		}
		assertEquals(0, entidadeDao.list().size());
	}

	@Test
	public void testAdicionarEntidade() {
		int qtd = entidadeDao.list().size();

		entidadeDao.save(entidade);
		assertEquals(qtd + 1, entidadeDao.list().size());
	}

	@Test
	public void testSearchEntidade() throws Exception {

		entidadeDao.save(entidade);
		List<Entidade> result = entidadeDao.search(entidade);
		assertEquals("Entidade", result.get(0).getNome());

		clearDatabase();
		assertEquals(0, entidadeDao.search(entidade).size());
	}

	@AfterClass
	public static void closeDatabase() {
		clearDatabase();
		DatabaseManager.close();
	}

}
