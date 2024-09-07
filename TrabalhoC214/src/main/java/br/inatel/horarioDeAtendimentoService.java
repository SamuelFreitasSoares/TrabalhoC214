package br.inatel;

import java.util.List;

public interface horarioDeAtendimentoService {
    public List<horarioDeAtendimento> findAll();
    public String busca(int id);
    public void save(horarioDeAtendimento horarioDeAtendimento);
    public void delete(horarioDeAtendimento horarioDeAtendimento);
}