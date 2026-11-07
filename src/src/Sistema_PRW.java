import java.util.HashMap;

public class Sistema_PRW {
    private double precoFixo;
    private HashMap<String, Restaurante> restaurantes;
    private HashMap<String, Cliente> clientes;

    public Sistema_PRW(){
        this.precoFixo = 89.90;
        this.restaurantes = new HashMap<>();
        this.clientes = new HashMap<>();
    }
    //Adiciona um restaurante, caso ele não esteja cadastrado
    public boolean adicionarRestaurante(String nome, String prato) {
        if (restaurantes.containsKey(nome)) {
            return false;
        }
        Restaurante restaurante = new Restaurante(nome, prato);
        restaurantes.put(nome, restaurante);
        return true;
    }
    //Adiciona um cliente, caso seu emial ainda esteja cadastrado
    public boolean adicionarCliente(String nome, String email){
        if (clientes.containsKey(email)){
            return false;
        }
        Cliente cliente = new Cliente(nome, email);
        clientes.put(email, cliente);
        return true;
    }
    //Registra visita sem comentario
    public void visitarRestaurante(String emailCliente, String nomeRestaurante){
        visitarRestaurante(emailCliente, nomeRestaurante, null);
    }
    //Registra visita com comentario
    public void visitarRestaurante(String emailCliente, String nomeRestaurante, String comentario){
        Cliente cliente = clientes.get(emailCliente);
        Restaurante restaurante = restaurantes.get(nomeRestaurante);
        if(cliente == null || restaurante == null){
            return;
        }
        cliente.gastosRestaurantes(precoFixo);
        String data = LocalDate.now().toString();
        restaurante.registraVisita(cliente, data, comentario);
    }
    //Retorna os gastoos de um cliente
    public double obtemGastoCliente(String emailCliente){
        Cliente cliente = clientes.get(emailCliente);
        if(cliente == null){
            return 0.0;
        }
        return cliente.getGastos();
    }
    //Lista os clientes que visitaram determinado restaurante
    public String[] listarClientesRestaurantes(String nomeRestaurante){
        Restaurante restaurante = restaurantes.get(nomeRestaurante);
        if(restaurante == null){
            return new String[0];
        }
        return restaurantes.listarClientes();
    }
    // Registra o voto do cliente no prato do restaurante
    public String votarPratoPreferido(String emailCliente, String nomeRestaurante){
        Cliente cliente = clientes.get(emailCliente);
        Restaurante restaurante = restaurantes.get(nomeRestaurante);

        if (cliente == null || restaurante == null){
            return "Voto não registrado";
        }

        if (!restaurante.contemCliente(emailCliente)){
            return "Voto não registrado";
        }

        restaurante.adicionarVoto();
        cliente.setPratoPreferido(restaurante.getPrato());

        return "Voto registrado com sucesso!";
    }
    // Retorna o prato com mais votos
    public String melhorPrato(){
        Restaurante melhor = null;

        for (Restaurante restaurante : restaurantes.values()){
            if (melhor == null || restaurante.getVotos() > melhor.getVotos()){
                melhor = restaurante;
            }
        }

        if (melhor == null){
            return "Nenhum restaurante cadastrado";
        }

        return melhor.getPrato();
    }
    // Lista todas as visitas de um restaurante
    public String[] listarLivroVisitas(String nomeRestaurante){
        Restaurante restaurante = restaurantes.get(nomeRestaurante);

        if (restaurante == null){
            return new String[0];
        }

        return restaurante.listarLivroVisitas();
    }
    // Lista somente as visitas mais recentes
    public String[] listarLivroVisitas(String nomeRestaurante, int num_recentes){
        Restaurante restaurante = restaurantes.get(nomeRestaurante);

        if (restaurante == null){
            return new String[0];
        }

        return restaurante.listarLivroVisitas(num_recentes);
    }
}