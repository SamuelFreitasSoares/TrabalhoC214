package br.inatel.mockito;

import br.inatel.BuscaHorarioDeAtendimento;
import br.inatel.horarioDeAtendimentoService;
import br.inatel.MockHorarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TesteBuscaHorario {

    @Mock
    private horarioDeAtendimentoService horarioService;
    private BuscaHorarioDeAtendimento buscaHorarioDeAtendimento;

    @Before
    public void setUp() {
        buscaHorarioDeAtendimento = new BuscaHorarioDeAtendimento(horarioService);
    }

    @Test
    public void testaBuscaHorario() {
        Mockito.when(horarioService.busca(1)).thenReturn("Chris Horario");
        Mockito.when(horarioService.busca(2)).thenReturn("Renzo Horario");
        Mockito.when(horarioService.busca(3)).thenReturn("Renan Horario");
        Mockito.when(horarioService.busca(4)).thenReturn("Marcelo Horario");

        String horarios = buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(1).toString() + "\n" +
                buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(2).toString() + "\n" +
                buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(3).toString() + "\n" +
                buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(4).toString();

        assertTrue(horarios.contains("Chris Horario"));
        assertTrue(horarios.contains("Renzo Horario"));
        assertTrue(horarios.contains("Renan Horario"));
        assertTrue(horarios.contains("Marcelo Horario"));
    }

    @Test
    public void testaBuscaHorarioInexistente() {
        Mockito.when(horarioService.busca(56)).thenReturn("Inexistente");
        String horarios = String.valueOf(buscaHorarioDeAtendimento.buscaHorarioDeAtendimento(56));

        assertTrue(horarios.contains("Inexistente"));
    }

    @Test
    public void testeHorarioValido() {
        Mockito.when(horarioService.horarioExistente(1)).thenReturn(true);
        boolean horario = horarioService.horarioExistente(1);
        assertEquals(true, horario);
    }
}