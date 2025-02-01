package Ex_6_Lista_exerciciosJUnit;

import java.util.ArrayList;

public class Utilitario {
    public ArrayList<Estudante> definirConceito(ArrayList<Estudante> estudantes) {

        if (estudantes == null)
            throw new RuntimeException();

        if (estudantes.size() == 0)
            throw new IllegalArgumentException("lista vazia de estudantes");

        for(var e : estudantes){
            if (e.getEscore() < 0 || e.getEscore() > 100)
                throw new IllegalArgumentException("estudante com escore invalido");

            if (e.getNome().trim().equals(""))
                throw new IllegalArgumentException("estudante com nome invalido");

        }

        estudantes.forEach(e -> {
            if (e.getEscore() >= 90)
                e.setConceito("A");
            else if (e.getEscore() >= 70)
                e.setConceito("B");
            else if (e.getEscore() >= 50)
                e.setConceito("C");
            else
                e.setConceito("F");
        });

        return estudantes;
    }
}
