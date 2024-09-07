package br.inatel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Collections;

public class BuscaHorarioDeAtendimento {
    horarioDeAtendimentoService horarioDeAtendimentoService;

    public BuscaHorarioDeAtendimento(horarioDeAtendimentoService horarioDeAtendimentoService) {
        this.horarioDeAtendimentoService = horarioDeAtendimentoService;
    }

    public horarioDeAtendimento buscaHorarioDeAtendimento(int id) {
        String horarioJson = horarioDeAtendimentoService.busca(id);
        JsonObject jsonObject = JsonParser.parseString(horarioJson).getAsJsonObject();
        return new horarioDeAtendimento(
                jsonObject.get("nomeDoProfessor").getAsString(),
                jsonObject.get("horarioDeAtendimento").getAsString(),
                jsonObject.get("periodo").getAsString(),
                jsonObject.get("sala").getAsString(),
                Collections.singletonList(jsonObject.get("predio").getAsInt())
        );
    }
}