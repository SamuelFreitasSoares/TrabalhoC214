package br.inatel;

public interface horarioDeAtendimentoService {
    public String findAll();
    public String busca(int id);
    public boolean horarioExistente(int id);
}