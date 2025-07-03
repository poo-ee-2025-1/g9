import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CentralDeGerenciamento {
    private List<Cruzamento> cruzamentos;
    private Random random;

    public CentralDeGerenciamento(List<Cruzamento> cruzamentos) {
        this.cruzamentos = cruzamentos;
        this.random = new Random();
    }

    public void simularSegundo() {
        for (Cruzamento cruz : cruzamentos) {
            for (Sensor sensor : cruz.getSensores().values()) {
                int fluxo = random.nextInt(31);
                sensor.setFluxoVeiculos(fluxo);
                sensor.resetProblemas();
                int chance = random.nextInt(100);
                if (chance < 5) {
                    TipoAnormalidade tipo = TipoAnormalidade.values()[random.nextInt(TipoAnormalidade.values().length)];
                    sensor.setAnormalidade(tipo);
                } else if (chance >= 5 && chance < 8) {
                    sensor.setDefeitoTecnico(true, "Falha no sensor");
                    cruz.setFalhaTecnicaAtiva(true);
                }
            }

            boolean temDefeito = cruz.getSensores().values().stream().anyMatch(Sensor::hasDefeitoTecnico);
            cruz.setFalhaTecnicaAtiva(temDefeito);
            simularBotaoPedestreAleatorio(cruz);
            cruz.simularSegundo();
        }
    }

    private void simularBotaoPedestreAleatorio(Cruzamento cruz) {
        List<String> direcoes = Arrays.asList("Norte", "Sul", "Leste", "Oeste");
        for (String direcao : direcoes) {
            if (random.nextDouble() < 0.05) {
                cruz.acionarBotaoPedestre(direcao);
            }
        }
    }

    public void acionarBotaoPedestre(int indexCruzamento, String direcao) {
        if (indexCruzamento >= 0 && indexCruzamento < cruzamentos.size()) {
            cruzamentos.get(indexCruzamento).acionarBotaoPedestre(direcao);
        }
    }

    public List<Cruzamento> getCruzamentos() {
        return cruzamentos;
    }
}
