import java.util.HashMap;
import java.util.Locale;

public class RelatoriosPRW {

    private HashMap<String, Restaurante> restaurantes;
    private HashMap<String, Cliente> clientes;
    private double precoFixo;

    public RelatoriosPRW(HashMap<String, Restaurante> restaurantes,
                         HashMap<String, Cliente> clientes,
                         double precoFixo) {

        this.restaurantes = restaurantes;
        this.clientes = clientes;
        this.precoFixo = precoFixo;
    }

    // Retorna o total de visitas de todos os restaurantes
    public int totalVisitasEvento(){
        int total = 0;

        for(Restaurante restaurante : restaurantes.values()){
            total += restaurante.getTotalVisitas();
        }
        return total;
    }

    // Relatório Geral
    public String relatorioGeral(){

        StringBuilder relatorio = new StringBuilder();

        int totalVisitas = totalVisitasEvento();
        int totalVisitantes = 0;
        for(Restaurante restaurante : restaurantes.values()){
            totalVisitantes += restaurante.getTotalClientes();
        }

        relatorio.append("Relatório Geral Paraíba Restaurant Week\n\n");

        // (1) Quantidade de restaurantes
        relatorio.append("(1) Restaurantes participantes: ")
                .append(restaurantes.size()).append("\n");

        // (2) Quantidade de clientes cadastrados
        relatorio.append("(2) Clientes participantes: ")
                .append(clientes.size()).append("\n");

        // (3) Média de visitantes
        double mediaVisitantes = 0.0;
        if(!restaurantes.isEmpty()){
            mediaVisitantes = (double) totalVisitantes / restaurantes.size();
        }

        relatorio.append("(3) Número médio de visitantes: ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", mediaVisitantes))
                .append("\n");
        // (4) Média de visitas
        double mediaVisitas = 0.0;
        if(!restaurantes.isEmpty()){
            mediaVisitas = (double) totalVisitas / restaurantes.size();
        }

        relatorio.append("(4) Número médio de visitas: ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", mediaVisitas))
                .append("\n");

        // (5) Restaurante mais visitado
        int maior = -1;
        String maisVisitados = "";
        for(Restaurante restaurante : restaurantes.values()){
            if(restaurante.getTotalVisitas() > maior){
                maior = restaurante.getTotalVisitas();
                maisVisitados = restaurante.getNomeRestaurante();

            } else if(restaurante.getTotalVisitas() == maior){
                maisVisitados += ", " + restaurante.getNomeRestaurante();
            }
        }
        if(restaurantes.isEmpty()){
            maisVisitados = "Nenhum restaurante cadastrado";
        }

        relatorio.append("(5) Restaurante mais visitado: ")
                .append(maisVisitados).append("\n");

        // (6) e (7) Participação de cada restaurante
        relatorio.append("\nParticipação dos restaurantes:\n");

        for(Restaurante restaurante : restaurantes.values()){

            double participacaoVisitas = 0.0;
            double participacaoVisitantes = 0.0;

            if(totalVisitas > 0){
                participacaoVisitas = (double) restaurante.getTotalVisitas()
                        / totalVisitas * 100;
            }

            if(totalVisitantes > 0){
                participacaoVisitantes = (double) restaurante.getTotalClientes()
                        / totalVisitantes * 100;
            }

            relatorio.append("\nRestaurante: ")
                    .append(restaurante.getNomeRestaurante()).append("\n");

            relatorio.append("(6) Participação de visitas: ")
                    .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", participacaoVisitas))
                    .append("%\n");

            relatorio.append("(7) Participação de visitantes: ")
                    .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", participacaoVisitantes))
                    .append("%\n");
        }
        return relatorio.toString();
    }

    // Calcula a receita total do evento
    public double receitaTotal(){
        return totalVisitasEvento() * precoFixo;
    }

    // Calcula a média de receita por restaurante
    public double mediaReceitaRestaurante(){

        if(restaurantes.isEmpty()){
            return 0.0;
        }
        return receitaTotal() / restaurantes.size();
    }

    // Calcula a média de gastos por cliente
    public double mediaGastosCliente(){

        if(clientes.isEmpty()){
            return 0.0;
        }
        double total = 0.0;
        for(Cliente cliente : clientes.values()){
            total += cliente.getGastos();
        }
        return total / clientes.size();
    }

    // Relatório Financeiro
    public String relatorioFinanceiro(){

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório Financeiro Paraíba Restaurant Week\n\n");

        relatorio.append("Receita total do evento: R$ ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", receitaTotal()))
                .append("\n");

        relatorio.append("Média de receita por restaurante: R$ ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", mediaReceitaRestaurante()))
                .append("\n");

        relatorio.append("Média de gastos por cliente: R$ ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", mediaGastosCliente()))
                .append("\n");

        return relatorio.toString();
    }
    // Relatório de Engajamento
    public String relatorioEngajamento(){

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Engajamento Paraíba Restaurant Week\n\n");

        for(Restaurante restaurante : restaurantes.values()){
            HashMap<String, Integer> visitas = restaurante.getVisitasPorCliente();
            for(String email : visitas.keySet()){
                Cliente cliente = clientes.get(email);
                if(cliente != null){

                    relatorio.append("Cliente: ")
                            .append(cliente.getNomeCliente());

                    relatorio.append(" | Restaurante: ")
                            .append(restaurante.getNomeRestaurante());

                    relatorio.append(" | Visitas: ")
                            .append(visitas.get(email)).append("\n");
                }
            }
        }
        return relatorio.toString();
    }
}