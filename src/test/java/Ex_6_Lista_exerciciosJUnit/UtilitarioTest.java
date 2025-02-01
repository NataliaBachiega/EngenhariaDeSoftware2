package Ex_6_Lista_exerciciosJUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UtilitarioTest {
    Utilitario utilitario;

    @BeforeEach
    void setUp() {
        utilitario = new Utilitario();
    }

    @Test
    void listaNula() {

        var e = assertThrows(RuntimeException.class, () -> utilitario.definirConceito(null));

    }


    @Test
    void listaVazia(){


        var estudantes = new ArrayList<Estudante>(); // Lista vazia
        var e = assertThrows(IllegalArgumentException.class, () -> utilitario.definirConceito(estudantes));
        assertEquals("lista vazia de estudantes", e.getMessage());


    }


    @Test
    void escoreInvalido(){

        var estudante = new Estudante("Maria", -1);
        var estudantes = new ArrayList<Estudante>();
        estudantes.add(estudante);

        var e = assertThrows(IllegalArgumentException.class, () -> utilitario.definirConceito(estudantes));
        assertEquals("estudante com escore invalido", e.getMessage());


    }

    @Test
    void nomeInvalido(){
        var estudante = new Estudante("", 100);
        var estudantes = new ArrayList<Estudante>();
        estudantes.add(estudante);

        var e = assertThrows(IllegalArgumentException.class, () -> utilitario.definirConceito(estudantes));
        assertEquals("estudante com nome invalido", e.getMessage());


    }


    @Test
    void conceitoA(){

        var estudante = new Estudante("João", 90);

        var estudantes = new ArrayList<Estudante>();

        estudantes.add(estudante);

        utilitario.definirConceito(estudantes);
        assertEquals("A", estudante.getConceito());

    }

    @Test
    void conceitoB(){

        var estudante = new Estudante("João", 70);

        var estudantes = new ArrayList<Estudante>();

        estudantes.add(estudante);

        utilitario.definirConceito(estudantes);
        assertEquals("B", estudante.getConceito());

    }

    @Test
    void conceitoC(){

        var estudante = new Estudante("João", 50);

        var estudantes = new ArrayList<Estudante>();

        estudantes.add(estudante);

        utilitario.definirConceito(estudantes);
        assertEquals("C", estudante.getConceito());

    }

    @Test
    void conceitoF(){

        var estudante = new Estudante("João", 49);

        var estudantes = new ArrayList<Estudante>();

        estudantes.add(estudante);

        utilitario.definirConceito(estudantes);
        assertEquals("F", estudante.getConceito());

    }

}