import java.util.HashMap;
import java.util.Map;

public class Cruzamento {
    private String nome;
    private Map<String, Sensor> sensores;
    private Map<String, Semaforo> semaforos;
    private boolean falhaTecnicaAtiva;
    private String faseAtual;

    private static final int TEMPO_MIN_VERDE = 20;
    private static final int TEMPO_MAX_VERDE = 120;
    private static final int TEMPO_AMARELO = 5;
    private static final int TEMPO_VERMELHO = 5;

    public Cruzamento(String nome) {
        this.nome = nome;
        this.sensores = new HashMap<>();
        this.semaforos = new HashMap<>();
        this.falhaTecnicaAtiva = false;
        this.faseAtual = "NS";

        sensores.put("Norte", new Sensor("Norte"));
        sensores.put("Sul", new Sensor("Sul"));
        sensores.put("Leste", new Sensor("Leste"));
        sensores.put("Oeste", new Sensor("Oeste"));

        semaforos.put("Norte", new Semaforo());
        semaforos.put("Sul", new Semaforo());
        semaforos.put("Leste", new Semaforo());
        semaforos.put("Oeste", new Semaforo());

        configurarFase("NS");
    }

    public Map<String, Sensor> getSensores() {
        return sensores;
    }

    public Map<String, Semaforo> getSemaforos() {
        return semaforos;
    }

    public boolean isFalhaTecnicaAtiva() {
        return falhaTecnicaAtiva;
    }

    public void setFalhaTecnicaAtiva(boolean falhaAtiva) {
        this.falhaTecnicaAtiva = falhaAtiva;
        if (falhaAtiva) {
            for (Semaforo s : semaforos.values()) {
                s.setEstado(EstadoSemaforo.AMARELO_PISCANTE, -1);
            }
        } else {
            configurarFase(faseAtual);
        }
    }

    private void configurarFase(String fase) {
        this.faseAtual = fase;
        int tempoVerde;

        if (fase.equals("NS")) {
            int fluxoNS = sensores.get("Norte").getFluxoVeiculos() + sensores.get("Sul").getFluxoVeiculos();
            tempoVerde = calcularTempoVerde(fluxoNS);

            semaforos.get("Norte").setEstado(EstadoSemaforo.VERDE, tempoVerde);
            semaforos.get("Sul").setEstado(EstadoSemaforo.VERDE, tempoVerde);

            semaforos.get("Leste").setEstado(EstadoSemaforo.VERMELHO, tempoVerde + TEMPO_AMARELO + TEMPO_VERMELHO);
            semaforos.get("Oeste").setEstado(EstadoSemaforo.VERMELHO, tempoVerde + TEMPO_AMARELO + TEMPO_VERMELHO);
        } else {
            int fluxoLO = sensores.get("Leste").getFluxoVeiculos() + sensores.get("Oeste").getFluxoVeiculos();
            tempoVerde = calcularTempoVerde(fluxoLO);

            semaforos.get("Leste").setEstado(EstadoSemaforo.VERDE, tempoVerde);
            semaforos.get("Oeste").setEstado(EstadoSemaforo.VERDE, tempoVerde);

            semaforos.get("Norte").setEstado(EstadoSemaforo.VERMELHO, tempoVerde + TEMPO_AMARELO + TEMPO_VERMELHO);
            semaforos.get("Sul").setEstado(EstadoSemaforo.VERMELHO, tempoVerde + TEMPO_AMARELO + TEMPO_VERMELHO);
        }
    }

    private int calcularTempoVerde(int fluxo) {
        int tempo = fluxo * 3;
        if (tempo < TEMPO_MIN_VERDE) tempo = TEMPO_MIN_VERDE;
        if (tempo > TEMPO_MAX_VERDE) tempo = TEMPO_MAX_VERDE;
        return tempo;
    }

    public void simularSegundo() {
        if (falhaTecnicaAtiva) return;

        for (Semaforo s : semaforos.values()) s.tick();

        if (faseAtual.equals("NS")) {
            Semaforo semN = semaforos.get("Norte");
            if (semN.getEstado() == EstadoSemaforo.VERDE && semN.getTempoRestante() <= 0) {
                semaforos.get("Norte").setEstado(EstadoSemaforo.AMARELO, TEMPO_AMARELO);
                semaforos.get("Sul").setEstado(EstadoSemaforo.AMARELO, TEMPO_AMARELO);
            } else if (semN.getEstado() == EstadoSemaforo.AMARELO && semN.getTempoRestante() <= 0) {
                semaforos.get("Norte").setEstado(EstadoSemaforo.VERMELHO, TEMPO_VERMELHO);
                semaforos.get("Sul").setEstado(EstadoSemaforo.VERMELHO, TEMPO_VERMELHO);
                configurarFase("LO");
            }
        } else {
            Semaforo semL = semaforos.get("Leste");
            if (semL.getEstado() == EstadoSemaforo.VERDE && semL.getTempoRestante() <= 0) {
                semaforos.get("Leste").setEstado(EstadoSemaforo.AMARELO, TEMPO_AMARELO);
                semaforos.get("Oeste").setEstado(EstadoSemaforo.AMARELO, TEMPO_AMARELO);
            } else if (semL.getEstado() == EstadoSemaforo.AMARELO && semL.getTempoRestante() <= 0) {
                semaforos.get("Leste").setEstado(EstadoSemaforo.VERMELHO, TEMPO_VERMELHO);
                semaforos.get("Oeste").setEstado(EstadoSemaforo.VERMELHO, TEMPO_VERMELHO);
                configurarFase("NS");
            }
        }

        tratarPedestres();
    }

    public void acionarBotaoPedestre(String direcao) {
        Sensor sensor = sensores.get(direcao);
        if (sensor != null) {
            sensor.setBotaoPedestre(true);
            System.out.println("🚶 Botão de pedestre pressionado na via " + direcao + ". Pedido de travessia registrado.");
        }
    }

    private void tratarPedestres() {
        for (Sensor sensor : sensores.values()) {
            if (sensor.isBotaoPedestre()) {
                Semaforo sem = semaforos.get(sensor.getDirecao());

                if (sem.getEstado() == EstadoSemaforo.VERDE && sem.getTempoRestante() > 5) {
                    continue; // aguarda o fim do verde
                }

                sem.setEstado(EstadoSemaforo.VERMELHO, 10);
                String oposto = direcaoOposta(sensor.getDirecao());
                if (oposto != null) {
                    semaforos.get(oposto).setEstado(EstadoSemaforo.VERMELHO, 10);
                }

                sensor.setBotaoPedestre(false);
                System.out.println("🚦 Travessia autorizada na via " + sensor.getDirecao() + " (10 segundos de sinal vermelho para veículos).");
            }
        }
    }

    private String direcaoOposta(String direcao) {
        return switch (direcao) {
            case "Norte" -> "Sul";
            case "Sul" -> "Norte";
            case "Leste" -> "Oeste";
            case "Oeste" -> "Leste";
            default -> null;
        };
    }

    public String relatorioStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------------------------------------------\n");
        sb.append("Cruzamento: ").append(nome).append("\n\n");

        sb.append("Sensores e Fluxo de Veículos:\n");
        for (Sensor s : sensores.values()) {
            sb.append("- ").append(s.getDirecao()).append(": ").append(s.getFluxoVeiculos());
            if (s.hasAnormalidade()) {
                sb.append(" (Anormalidade: ").append(s.getTipoAnormalidade().name()).append(")");
            }
            if (s.hasDefeitoTecnico()) {
                sb.append(" (Defeito: ").append(s.getDescricaoDefeito()).append(")");
            }
            sb.append("\n");
        }

        sb.append("\nSemáforos:\n");
        for (Map.Entry<String, Semaforo> entry : semaforos.entrySet()) {
            String dir = entry.getKey();
            Semaforo sem = entry.getValue();
            sb.append("- ").append(dir).append(": ").append(sem.getEstado().name())
              .append(" (").append(sem.getModoOperacao()).append(") - Tempo restante: ")
              .append(sem.getTempoRestante()).append("s - Próxima cor: ").append(sem.proximaCor())
              .append("\n");
        }

        sb.append("\nAlertas e Ações do Sistema:\n");
        boolean alerta = false;
        boolean defeito = false;

        for (Sensor s : sensores.values()) {
            if (s.hasAnormalidade()) {
                alerta = true;
                sb.append("* Anormalidade detectada na via ").append(s.getDirecao())
                  .append(": ").append(s.getTipoAnormalidade().name()).append("\n");

                switch (s.getTipoAnormalidade()) {
                    case EMERGENCIA:
                        sb.append("  -> Emergência detectada! Sinal na via ").append(s.getDirecao())
                          .append(" está verde para liberar passagem prioritária.\n");
                        break;
                    case OBSTACULO:
                        sb.append("  -> Obstáculo detectado. Sistema ajusta fluxo para garantir segurança.\n");
                        break;
                    default:
                        sb.append("  -> Anormalidade detectada, ação padrão aplicada.\n");
                        break;
                }
            }
            if (s.hasDefeitoTecnico()) {
                defeito = true;
                sb.append("* Defeito técnico na via ").append(s.getDirecao())
                  .append(": ").append(s.getDescricaoDefeito()).append("\n");
            }
        }

        if (!alerta) {
            sb.append("Nenhuma anormalidade detectada.\n");
        }
        if (!defeito) {
            sb.append("Nenhum defeito técnico detectado.\n");
        }

        sb.append("\nStatus do sistema: ");
        sb.append(falhaTecnicaAtiva ? "Falha técnica ativa - Semáforos em amarelo piscante" : "Operando normalmente");
        sb.append("\n------------------------------------------------------------\n");

        return sb.toString();
    }
}

