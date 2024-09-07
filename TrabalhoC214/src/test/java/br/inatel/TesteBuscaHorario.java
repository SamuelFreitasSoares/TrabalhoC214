package br.inatel;

import br.inatel.horarioDeAtendimentoService;
import br.inatel.horarioDeAtendimento;
import br.inatel.BuscaHorarioDeAtendimento;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteBuscaHorario {

    horarioDeAtendimentoService horarioService;
    BuscaHorarioDeAtendimento buscaHorarioDeAtendimento;

    @Before
    public void setup() {
        horarioService = new MockHorarioService();
        buscaHorarioDeAtendimento = new BuscaHorarioDeAtendimento(horarioService);
    }

    // Testes de Sucesso (corretos)
    @Test
    public void testaBuscaHorarioChris() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(1);
        assertEquals("Chris", horario.getNomeDoProfessor());
        assertEquals("Segunda-feira das 10h às 12h", horario.getHorarioDeAtendimento());
        assertEquals("2024.2", horario.getPeriodo());
        assertEquals("A-101", horario.getSala());
        assertEquals(1, (int) horario.getPredio().get(0));
    }

    @Test
    public void testaBuscaHorarioRenzo() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(2);
        assertEquals("Renzo", horario.getNomeDoProfessor());
        assertEquals("Terça-feira das 14h às 16h", horario.getHorarioDeAtendimento());
        assertEquals("2023.1", horario.getPeriodo());
        assertEquals("B-201", horario.getSala());
        assertEquals(2, (int) horario.getPredio().get(0));
    }

    @Test
    public void testaBuscaHorarioRenan() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(3);
        assertEquals("Renan", horario.getNomeDoProfessor());
        assertEquals("Quarta-feira das 8h às 10h", horario.getHorarioDeAtendimento());
        assertEquals("2022.2", horario.getPeriodo());
        assertEquals("C-301", horario.getSala());
        assertEquals(3, (int) horario.getPredio().get(0));
    }

    @Test
    public void testaBuscaHorarioMarcelo() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(4);
        assertEquals("Marcelo", horario.getNomeDoProfessor());
        assertEquals("Quinta-feira das 16h às 18h", horario.getHorarioDeAtendimento());
        assertEquals("2021.1", horario.getPeriodo());
        assertEquals("D-401", horario.getSala());
        assertEquals(4, (int) horario.getPredio().get(0));
    }

    @Test
    public void testaBuscaHorarioInexistente() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(999);
        assertEquals("inexistente", horario.getNomeDoProfessor());
        assertEquals("n.a", horario.getHorarioDeAtendimento());
        assertEquals("n.a", horario.getPeriodo());
        assertEquals("n.a", horario.getSala());
        assertEquals(0, (int) horario.getPredio().get(0));
    }

    @Test
    public void testaHorarioExistente() {
        assertTrue(horarioService.horarioExistente(1));
        assertTrue(horarioService.horarioExistente(2));
        assertTrue(horarioService.horarioExistente(3));
        assertTrue(horarioService.horarioExistente(4));
    }

    @Test
    public void testaHorarioInexistente() {
        assertFalse(horarioService.horarioExistente(999));
    }

    @Test
    public void testaBuscaTodosHorarios() {
        String horarios = horarioService.findAll();
        assertTrue(horarios.contains("Chris"));
        assertTrue(horarios.contains("Renzo"));
        assertTrue(horarios.contains("Renan"));
        assertTrue(horarios.contains("Marcelo"));
    }

    @Test
    public void testaBuscaHorarioRenanPeriodo() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(3);
        assertEquals("2022.2", horario.getPeriodo());
    }

    @Test
    public void testaBuscaHorarioMarceloSala() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(4);
        assertEquals("D-401", horario.getSala());
    }

    // Testes que falham (erros propositalmente)
    @Test
    public void testaBuscaHorarioChrisNomeErrado() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(1);
        assertEquals("Christiano", horario.getNomeDoProfessor()); // Nome incorreto
    }

    @Test
    public void testaBuscaHorarioRenzoHorarioErrado() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(2);
        assertEquals("Segunda-feira das 10h às 12h", horario.getHorarioDeAtendimento()); // Horário incorreto
    }

    @Test
    public void testaBuscaHorarioRenanPeriodoErrado() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(3);
        assertEquals("2024.2", horario.getPeriodo()); // Período incorreto
    }

    @Test
    public void testaBuscaHorarioMarceloSalaErrada() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(4);
        assertEquals("A-101", horario.getSala()); // Sala incorreta
    }

    @Test
    public void testaBuscaHorarioInexistenteNomeErrado() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(999);
        assertEquals("Professor", horario.getNomeDoProfessor()); // Nome incorreto
    }

    @Test
    public void testaBuscaHorarioChrisPredioErrado() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(1);
        assertEquals(2, (int) horario.getPredio().get(0)); // Prédio incorreto
    }

    @Test
    public void testaHorarioExistenteInvalido() {
        assertTrue(horarioService.horarioExistente(999)); // Deve falhar, pois o horário não existe
    }

    @Test
    public void testaBuscaTodosHorariosComErro() {
        String horarios = horarioService.findAll();
        assertTrue(horarios.contains("João")); // Nome incorreto
    }

    @Test
    public void testaBuscaHorarioRenzoSalaErrada() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(2);
        assertEquals("A-101", horario.getSala()); // Sala incorreta
    }

    @Test
    public void testaBuscaHorarioRenanPredioErrado() {
        horarioDeAtendimento horario = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(3);
        assertEquals(1, (int) horario.getPredio().get(0)); // Prédio incorreto
    }
}
