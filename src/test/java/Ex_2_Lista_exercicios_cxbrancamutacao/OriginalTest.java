package Ex_2_Lista_exercicios_cxbrancamutacao;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.*;

class OriginalTest {


    @Test
    void testMutante1() {
        Original original = new Original();
        String palavra = "Abacate";

        assertEquals(3, original.contarA(palavra));

        var mutante1 = new Mutante1();
        assertEquals(3, mutante1.contarA(palavra));


    }

    @Test
    void testMutante2() {
        //Nesse caso os mutantes são equivalentes, pois a expressão contador++ do código Original funciona da exata mesma forma que a expressão contador = contador + 1 do mutante.
    }

    @Test
    void testMutante3() {
        Original original = new Original();
        String palavra = "Amora";

        assertEquals(2, original.contarA(palavra));

        var mutante1 = new Mutante1();
        assertEquals(2, mutante1.contarA(palavra));
    }
}