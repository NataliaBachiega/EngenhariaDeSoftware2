package Ex_2_Lista_exercicios_cxbrancamutacao;

public class Original {
    public int contarA(String word) {
        int contador = 0;

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == 'a' ||
                    word.charAt(i) ==
                            'A')
                contador++;
        }
        return contador;
    }
}

