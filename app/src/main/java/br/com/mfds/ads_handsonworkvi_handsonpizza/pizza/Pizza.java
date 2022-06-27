package br.com.mfds.ads_handsonworkvi_handsonpizza.pizza;

public class Pizza {

    private int id;
    private String nome;
    private String ingredientes;
    private String tempo_preparo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTempo_preparo() {
        return tempo_preparo;
    }

    public void setTempo_preparo(String tempo_preparo) {
        this.tempo_preparo = tempo_preparo;
    }
}
