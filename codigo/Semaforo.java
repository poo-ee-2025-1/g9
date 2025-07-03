
public class Semaforo {
    private EstadoSemaforo estado;
    private int tempoRestante;
    private String modoOperacao;

    public Semaforo() {
        this.estado = EstadoSemaforo.VERMELHO;
        this.tempoRestante = 0;
        this.modoOperacao = "Automático";
    }

    public EstadoSemaforo getEstado() {
        return estado;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public String getModoOperacao() {
        return modoOperacao;
    }

    public void setEstado(EstadoSemaforo novoEstado, int tempo) {
        this.estado = novoEstado;
        this.tempoRestante = tempo;
        this.modoOperacao = "Automático";
    }

    public void setEstadoManual(EstadoSemaforo novoEstado, int tempo) {
        this.estado = novoEstado;
        this.tempoRestante = tempo;
        this.modoOperacao = "Manual";
    }

    public void tick() {
        if (tempoRestante > 0) {
            tempoRestante--;
        }
    }

    public String proximaCor() {
        switch (estado) {
            case VERDE: return "Amarelo";
            case AMARELO: return "Vermelho";
            case VERMELHO: return "Verde";
            case AMARELO_PISCANTE: return "Amarelo piscante";
            default: return "Desconhecido";
        }
    }
}
