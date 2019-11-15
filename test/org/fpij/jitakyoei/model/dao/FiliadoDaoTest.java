package org.fpij.jitakyoei.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.fpij.jitakyoei.model.beans.Aluno;
import org.fpij.jitakyoei.model.beans.Endereco;
import org.fpij.jitakyoei.model.beans.Entidade;
import org.fpij.jitakyoei.model.beans.Filiado;
import org.fpij.jitakyoei.model.beans.Professor;
import org.fpij.jitakyoei.util.DatabaseManager;
import org.junit.BeforeClass;
import org.junit.Test;

public class FiliadoDaoTest {
	private static DAO<Filiado> filiadoDao;
	private static Aluno aluno;
	private static Entidade entidade;
	private static Endereco endereco;
	private static Filiado f1;
	private static Filiado filiadoProf;
	private static Professor professor;
	
	
	
	@BeforeClass
	public static void setUp() {
		DatabaseManager.setEnviroment(DatabaseManager.TEST);

		f1 = new Filiado();
		f1.setNome("Filiado");
		f1.setCpf("036.464.453-27");
		f1.setDataNascimento(new Date());
		f1.setDataCadastro(new Date());
		f1.setId(1332L);
		
		filiadoDao = new DAOImpl<Filiado>(Filiado.class);
	}
	
	public static void clearDatabase() {
		List<Filiado> all = filiadoDao.list();
		for (Filiado each : all) {
			filiadoDao.delete(each);
		}
		assertEquals(0, filiadoDao.list().size());
	}

	@Test
	public void testAdicionarFiliado() {
		int qtd = filiadoDao.list().size();

		filiadoDao.save(f1);
		assertEquals(qtd + 1, filiadoDao.list().size());
	}
	
	@Test
	public void testSearcFiliado() throws Exception {

		filiadoDao.save(f1);
		List<Filiado> result = filiadoDao.search(f1);
		assertEquals("Filiado", result.get(0).getNome());

		clearDatabase();
		assertEquals(0, filiadoDao.search(f1).size());
	}

}
