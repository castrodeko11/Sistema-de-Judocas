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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProfessorDaoTest {

	private static DAO<Professor> professorDao;
	private static Entidade entidade;
	private static Endereco endereco;
	private static Filiado f1;
	private static Filiado filiadoProf;
	private static Professor professor;

	@BeforeClass
	public static void setUp() {
		DatabaseManager.setEnviroment(DatabaseManager.TEST);
		
		filiadoProf = new Filiado();
		filiadoProf.setNome("Professor");
		filiadoProf.setCpf("036.464.453-27");
		filiadoProf.setDataNascimento(new Date());
		filiadoProf.setDataCadastro(new Date());
		filiadoProf.setId(3332L);
		filiadoProf.setEndereco(endereco);
		
		professor = new Professor();
		professor.setFiliado(filiadoProf);
		

		professorDao = new DAOImpl<Professor>(Professor.class);
	}


	public static void clearDatabase() {
		List<Professor> all = professorDao.list();
		for (Professor each : all) {
			professorDao.delete(each);
		}
		assertEquals(0, professorDao.list().size());
	}

	@Test
	public void testAdicionarProfessor() {
		int qtd = professorDao.list().size();

		professorDao.save(professor);
		assertEquals(qtd + 1, professorDao.list().size());
	}

	@Test
	public void testSearchProfessor() throws Exception {

		professorDao.save(professor);
		List<Professor> result = professorDao.search(professor);
		assertEquals("Professor", result.get(0).getFiliado().getNome());

		clearDatabase();
		assertEquals(0, professorDao.search(professor).size());
	}

	@AfterClass
	public static void closeDatabase() {
		clearDatabase();
		DatabaseManager.close();
	}
}
