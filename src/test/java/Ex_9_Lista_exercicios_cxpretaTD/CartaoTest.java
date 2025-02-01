package Ex_9_Lista_exercicios_cxpretaTD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartaoTest {

    ClienteService clienteService;
    Cartao cartao;

    @BeforeEach
    void setUp() {
        clienteService = mock(ClienteService.class);
        cartao = new Cartao(clienteService);
    }

    @Test
    void clienteNovo() {
        when(clienteService.ehClienteNovo()).thenReturn(true);
        when(clienteService.ehClienteOuro()).thenReturn(false);

        var res = cartao.calculaDesconto(null);
        assertEquals(0.1, res);
    }


    @Test
    void clientOuro() {
        when(clienteService.ehClienteNovo()).thenReturn(false);
        when(clienteService.ehClienteOuro()).thenReturn(true);

        var res = cartao.calculaDesconto(null);
        assertEquals(0.12, res);
    }

    @Test
    void clienteNovoComCupom() {
        when(clienteService.ehClienteNovo()).thenReturn(true);
        when(clienteService.ehClienteOuro()).thenReturn(false);

        var res = cartao.calculaDesconto("Cupom");
        //Nesse caso, mesmo quando o cliente novo tem cupom, assumi que o cupom válido seja somente o de cliente novo
        assertEquals(0.10, res);

    }

    @Test
    void clienteOuroComCupom() {
        when(clienteService.ehClienteNovo()).thenReturn(false);
        when(clienteService.ehClienteOuro()).thenReturn(true);

        var res = cartao.calculaDesconto("Cupom");
        //Somando os descontos de cliente ouro e do cupom
        assertEquals(0.37, res);
    }

    @Test
    void clienteNovoEOuro() {
        when(clienteService.ehClienteNovo()).thenReturn(true);
        when(clienteService.ehClienteOuro()).thenReturn(true);

        var res = cartao.calculaDesconto(null);
        //Somando os descontos de cliente ouro e de cliente novo
        assertEquals(0.22, res);
    }

    @Test
    void clienteNovoEOuroComCupom() {
        when(clienteService.ehClienteNovo()).thenReturn(true);
        when(clienteService.ehClienteOuro()).thenReturn(true);

        var res = cartao.calculaDesconto("Cupom");
        //Somando os descontos de cliente ouro e de cliente novo, porque cliente novo não pode receber o desconto do cupom
        assertEquals(0.22, res);
    }

    @Test
    void clienteNovoEOuroSemCupom() {
        when(clienteService.ehClienteNovo()).thenReturn(true);
        when(clienteService.ehClienteOuro()).thenReturn(true);

        var res = cartao.calculaDesconto(null);
        //Somando os descontos de cliente ouro e de cliente novo
        assertEquals(0.22, res);
    }

    @Test
    void semDesconto() {
        when(clienteService.ehClienteNovo()).thenReturn(false);
        when(clienteService.ehClienteOuro()).thenReturn(false);

        var res = cartao.calculaDesconto(null);
        assertEquals(0.0, res);
    }

}