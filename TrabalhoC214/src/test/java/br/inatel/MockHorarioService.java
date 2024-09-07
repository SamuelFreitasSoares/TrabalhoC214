package br.inatel;

public class MockHorarioService implements horarioDeAtendimentoService{

    @Override
    public String findAll() {
        return busca(1) + "\n" + busca(2) + "\n" + busca(3) + "\n" + busca(4);
    }

    @Override
    public String busca(int id) {
        if (id == 1){
            return HorarioConst.ChrisHorario;
        }else if (id == 2){
            return HorarioConst.RenzoHorario;
        }else if (id == 3){
            return HorarioConst.RenanHorario;
        }else if (id == 4){
            return HorarioConst.MarceloHorario;
        }else {
            return HorarioConst.Inexistente;
        }
    }

    @Override
    public boolean horarioExistente(int id) {
        if (id == 1 || id == 2 || id == 3 || id == 4){
            return true;
        }else {
            return false;
        }
    }


}
