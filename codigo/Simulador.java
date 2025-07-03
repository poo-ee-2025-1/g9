
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Simulador {
    public static void main(String[] args) throws InterruptedException {
        Cruzamento cruz1 = new Cruzamento("Protótipo A");
        Cruzamento cruz2 = new Cruzamento("Protótipo B (Onda Verde)");

        CentralDeGerenciamento central = new CentralDeGerenciamento(Arrays.asList(cruz1, cruz2));

        for (int i = 0; i < 10; i++) {
            central.simularSegundo();
            LocalDateTime now = LocalDateTime.now();
            String horaFormatada = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (Cruzamento c : central.getCruzamentos()) {
                System.out.println("SISTEMA SEMÁFOROS INTELIGENTES - STATUS DO CRUZAMENTO");
                System.out.println("Data/Hora: " + horaFormatada);
                System.out.print(c.relatorioStatus());
            }
            Thread.sleep(1000);
        }

        System.out.println("\nAcionando botão pedestre no Protótipo A, direção Norte...\n");
        central.acionarBotaoPedestre(0, "Norte");

        for (int i = 0; i < 10; i++) {
            central.simularSegundo();
            LocalDateTime now = LocalDateTime.now();
            String horaFormatada = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (Cruzamento c : central.getCruzamentos()) {
                System.out.println("SISTEMA SEMÁFOROS INTELIGENTES - STATUS DO CRUZAMENTO");
                System.out.println("Data/Hora: " + horaFormatada);
                System.out.print(c.relatorioStatus());
            }
            Thread.sleep(1000);
        }
    }
}
