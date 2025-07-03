
public class Sensor {
    private String direcao;
    private int fluxoVeiculos;
    private boolean botaoPedestre;
    private boolean defeitoTecnico;
    private String descricaoDefeito;
    private TipoAnormalidade anormalidade;

    public Sensor(String direcao) {
        this.direcao = direcao;
        this.fluxoVeiculos = 0;
        this.botaoPedestre = false;
        this.defeitoTecnico = false;
        this.descricaoDefeito = "";
        this.anormalidade = null;
    }

    public String getDirecao() {
        return direcao;
    }

    public int getFluxoVeiculos() {
        return fluxoVeiculos;
    }

    public void setFluxoVeiculos(int fluxoVeiculos) {
        this.fluxoVeiculos = fluxoVeiculos;
    }

    public boolean isBotaoPedestre() {
        return botaoPedestre;
    }

    public void setBotaoPedestre(boolean botaoPedestre) {
        this.botaoPedestre = botaoPedestre;
    }

    public boolean hasDefeitoTecnico() {
        return defeitoTecnico;
    }

    public void setDefeitoTecnico(boolean defeitoTecnico, String descricao) {
        this.defeitoTecnico = defeitoTecnico;
        this.descricaoDefeito = descricao;
    }

    public String getDescricaoDefeito() {
        return descricaoDefeito;
    }

    public boolean hasAnormalidade() {
        return anormalidade != null;
    }

    public TipoAnormalidade getTipoAnormalidade() {
        return anormalidade;
    }

    public void setAnormalidade(TipoAnormalidade anormalidade) {
        this.anormalidade = anormalidade;
    }

    public void resetProblemas() {
        this.defeitoTecnico = false;
        this.descricaoDefeito = "";
        this.anormalidade = null;
    }
}
