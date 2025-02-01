package Ex_6_Lista_exerciciosJUnit;

public class Estudante {

    private String nome;
    private int escore;
    private String conceito;

    public Estudante(String nome, int escore){
        this.nome = nome;
        this.escore = escore;
    }

    public void setConceito(String conceito){
        this.conceito = conceito;
    }

    public String getConceito(){
        return conceito;
    }

    public int getEscore(){
        return escore;
    }

    public String getNome(){
        return nome;
    }
}

