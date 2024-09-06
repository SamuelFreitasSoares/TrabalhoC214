package br.inatel;

import java.util.List;

public interface horarioDeAtendimentoService {
    public List<horarioDeAtendimento> findAll();
    public horarioDeAtendimento findById(int id);
    public void save(horarioDeAtendimento horarioDeAtendimento);
    public void delete(horarioDeAtendimento horarioDeAtendimento);
}