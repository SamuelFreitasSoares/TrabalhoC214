import json

class HorarioAtendimentoProfessor:
    def __init__(self, json_data):
        self.dados = self.parse_json(json_data)

    def parse_json(self, json_data):
        try:
            dados = json.loads(json_data)
            campos_obrigatorios = ["nomeDoProfessor", "horarioDeAtendimento", "periodo", "sala"]
            for campo in campos_obrigatorios:
                if campo not in dados:
                    raise ValueError(f"O campo {campo} é obrigatório e está faltando")

            sala = int(dados["sala"])
            predio = self.definir_predio(sala)
            dados["predio"] = predio
            return dados

        except (json.JSONDecodeError, KeyError, ValueError) as e:
            raise ValueError("Dados inválidos") from e

    def definir_predio(self, sala):
        if 1 <= sala <= 5:
            return 1
        elif 6 <= sala <= 10:
            return 2
        else:
            raise ValueError("Sala fora do intervalo permitido")

    def get_dados(self):
        return self.dados
