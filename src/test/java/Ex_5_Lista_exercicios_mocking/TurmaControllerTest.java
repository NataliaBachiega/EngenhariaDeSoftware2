package Ex_5_Lista_exercicios_mocking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TurmaControllerTest {

    TurmaDAO turmaDAOMock;
    VerificadorDeCodigos verificadorMock;
    TurmaController turmaController;

    @BeforeEach
    void beforeEach() {
        turmaDAOMock = mock(TurmaDAO.class);
        verificadorMock = mock(VerificadorDeCodigos.class);

        turmaController = new TurmaController(turmaDAOMock);
        turmaController.setVerificador(verificadorMock);
    }

    @Test
    void testCodigoDisciplinaInvalido() {

        Turma turma = new Turma();

        turma.codDisciplina = "123456789";

        when(verificadorMock.verificarCodigoDisciplina(turma.codDisciplina)).thenReturn(false);

        var res = turmaController.cadastrarTurma(turma);

        assertEquals("codigo disciplina invalido", res);
    }

    @Test
    void testCodigoTurmaInvalido() {

        Turma turma = new Turma();

        turma.codTurma = "123";
        turma.codDisciplina = "123456789";

        when(verificadorMock.verificarCodigoDisciplina(turma.codDisciplina)).thenReturn(true);
        when(verificadorMock.verificarCodigoTurma(turma.codTurma)).thenReturn(false);

        var res = turmaController.cadastrarTurma(turma);

        assertEquals("codigo turma invalido", res);

    }

    @Test
    void testTurmaExistente(){

        Turma turma = new Turma();
        turma.codTurma = "123";
        turma.codDisciplina = "123456789";

        when(verificadorMock.verificarCodigoDisciplina(turma.codDisciplina)).thenReturn(true);
        when(verificadorMock.verificarCodigoTurma(turma.codTurma)).thenReturn(true);
        when(turmaDAOMock.existe(turma)).thenReturn(true);

        var res = turmaController.cadastrarTurma(turma);

        assertEquals("turma ja existe", res);
    }

    @Test
    void testTurmaSalvaComSucesso(){
        Turma turma = new Turma();
        turma.codTurma = "123";
        turma.codDisciplina = "123456789";

        when(verificadorMock.verificarCodigoDisciplina(turma.codDisciplina)).thenReturn(true);
        when(verificadorMock.verificarCodigoTurma(turma.codTurma)).thenReturn(true);
        when(turmaDAOMock.existe(turma)).thenReturn(false);
        when(turmaDAOMock.salvar(turma)).thenReturn(true);

        var res = turmaController.cadastrarTurma(turma);

        assertEquals("turma salva com sucesso", res);
    }

    @Test
    void testTurmaNaoSalva(){
        Turma turma = new Turma();
        turma.codTurma = "123";
        turma.codDisciplina = "123456789";

        when(verificadorMock.verificarCodigoDisciplina(turma.codDisciplina)).thenReturn(true);
        when(verificadorMock.verificarCodigoTurma(turma.codTurma)).thenReturn(true);
        when(turmaDAOMock.existe(turma)).thenReturn(false);
        when(turmaDAOMock.salvar(turma)).thenReturn(false);

        var res = turmaController.cadastrarTurma(turma);

        assertEquals("turma nao salva. Erro no BD", res);
    }

}