import unittest
from professor import HorarioAtendimentoProfessor

class TestHorarioAtendimentoProfessor(unittest.TestCase):

    def test_sucesso_predio_1_sala_1(self):
        json_data = '{"nomeDoProfessor": "Professor A", "horarioDeAtendimento": "08:00", "periodo": "integral", "sala": "1", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["predio"], 1)

    def test_sucesso_predio_1_sala_5(self):
        json_data = '{"nomeDoProfessor": "Professor B", "horarioDeAtendimento": "09:00", "periodo": "integral", "sala": "5", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["predio"], 1)

    def test_sucesso_predio_2_sala_6(self):
        json_data = '{"nomeDoProfessor": "Professor C", "horarioDeAtendimento": "10:00", "periodo": "integral", "sala": "6", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["predio"], 2)

    def test_sucesso_predio_2_sala_10(self):
        json_data = '{"nomeDoProfessor": "Professor D", "horarioDeAtendimento": "11:00", "periodo": "noturno", "sala": "10", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["predio"], 2)

    def test_sucesso_dados_completos(self):
        json_data = '{"nomeDoProfessor": "Professor E", "horarioDeAtendimento": "12:00", "periodo": "integral", "sala": "3", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados(), {
            "nomeDoProfessor": "Professor E",
            "horarioDeAtendimento": "12:00",
            "periodo": "integral",
            "sala": "3",
            "predio": 1
        })

    def test_sucesso_periodo_integral(self):
        json_data = '{"nomeDoProfessor": "Professor F", "horarioDeAtendimento": "08:00", "periodo": "integral", "sala": "4", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["periodo"], "integral")

    def test_sucesso_periodo_noturno(self):
        json_data = '{"nomeDoProfessor": "Professor G", "horarioDeAtendimento": "18:00", "periodo": "noturno", "sala": "8", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["periodo"], "noturno")

    def test_sucesso_predio_1(self):
        json_data = '{"nomeDoProfessor": "Professor H", "horarioDeAtendimento": "14:00", "periodo": "integral", "sala": "2", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["predio"], 1)

    def test_sucesso_predio_2(self):
        json_data = '{"nomeDoProfessor": "Professor I", "horarioDeAtendimento": "15:00", "periodo": "noturno", "sala": "9", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertEqual(horario.get_dados()["predio"], 2)

    def test_sucesso_json_bem_formatado(self):
        json_data = '{"nomeDoProfessor": "Professor J", "horarioDeAtendimento": "16:00", "periodo": "integral", "sala": "7", "predio": ["1", "2", "3", "4", "6"]}'
        horario = HorarioAtendimentoProfessor(json_data)
        self.assertIsInstance(horario.get_dados(), dict)

    # Cenários de falha

    def test_falha_json_mal_formatado(self):
        json_data = '{"nomeDoProfessor": "Professor X", "horarioDeAtendimento": "10:00", "periodo": "integral", "sala": "10"'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sala_invalida_negativa(self):
        json_data = '{"nomeDoProfessor": "Professor Y", "horarioDeAtendimento": "08:00", "periodo": "integral", "sala": "-1", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sala_invalida_acima_do_limite(self):
        json_data = '{"nomeDoProfessor": "Professor Z", "horarioDeAtendimento": "09:00", "periodo": "integral", "sala": "15", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sem_campo_nomeDoProfessor(self):
        json_data = '{"horarioDeAtendimento": "08:00", "periodo": "integral", "sala": "3", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sem_campo_horarioDeAtendimento(self):
        json_data = '{"nomeDoProfessor": "Professor K", "periodo": "integral", "sala": "5", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sem_campo_periodo(self):
        json_data = '{"nomeDoProfessor": "Professor L", "horarioDeAtendimento": "10:00", "sala": "2", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sem_campo_sala(self):
        json_data = '{"nomeDoProfessor": "Professor M", "horarioDeAtendimento": "12:00", "periodo": "noturno", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sala_com_valor_nao_numerico(self):
        json_data = '{"nomeDoProfessor": "Professor N", "horarioDeAtendimento": "14:00", "periodo": "noturno", "sala": "abc", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sala_fora_do_intervalo_permitido(self):
        json_data = '{"nomeDoProfessor": "Professor O", "horarioDeAtendimento": "13:00", "periodo": "integral", "sala": "20", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)

    def test_falha_sala_zero(self):
        json_data = '{"nomeDoProfessor": "Professor X", "horarioDeAtendimento": "10:00", "periodo": "integral", "sala": "0", "predio": ["1", "2", "3", "4", "6"]}'
        with self.assertRaises(ValueError):
            HorarioAtendimentoProfessor(json_data)


if __name__ == '__main__':
    unittest.main()
