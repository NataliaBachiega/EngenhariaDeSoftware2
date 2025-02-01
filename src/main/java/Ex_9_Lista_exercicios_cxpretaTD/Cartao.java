package Ex_9_Lista_exercicios_cxpretaTD;

public class Cartao{
    private ClienteService clienteService;

    public Cartao(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public double calculaDesconto(String cupom) {
        var ehClienteNovo = clienteService.ehClienteNovo();
        var ehClienteOuro = clienteService.ehClienteOuro();
        var possuiCupom = cupom != null;

        double desconto = 0;

        if (ehClienteNovo) {
            // 10% de desconto
            desconto += 0.1;
        }

        // A pessoa pode criar uma conta no banco já com o status
        // de cliente ouro.
        if (ehClienteOuro) {
            desconto += 0.12;
        }

        if (possuiCupom && !ehClienteNovo) {
            desconto += 0.25;
        }

        return desconto;
    }
}
