package Ex_7_Lista_exercicios_cxpretaPCEAVL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockBrokerTest {
    Portfolio portfolio;
    Stock stock;
    Stock liveStock;
    MarketWatcher marketWatcher;
    StockBroker stockBroker;

    @BeforeEach
    void setUp() {
        portfolio = mock(Portfolio.class);
        stock = mock(Stock.class);
        marketWatcher = mock(MarketWatcher.class);
        liveStock = mock(Stock.class);

        when(stock.getSymbol()).thenReturn("AAPL");
        when(liveStock.getSymbol()).thenReturn("AAPL");

        when(marketWatcher.getQuote("AAPL")).thenReturn(liveStock);

        stockBroker = new StockBroker(marketWatcher);
    }


    @Test
    void venderAcaoMaiorQue10Porcento() {

        //percentGain valendo 0.1001, é maior que 10%, então deve vender

        when(portfolio.getAvgPrice(stock)).thenReturn(new BigDecimal("100.0"));
        when(liveStock.getPrice()).thenReturn(new BigDecimal("110.01"));

        stockBroker.perform(portfolio, stock);

        verify(portfolio, times(1)).sell(stock);
    }

    @Test
    void comprarAcaoMenorQue10Porcento() {

        //percentGain valendo 0.0999, é menor que 10%, então deve comprar

        when(portfolio.getAvgPrice(stock)).thenReturn(new BigDecimal("100.0"));
        when(liveStock.getPrice()).thenReturn(new BigDecimal("109.99"));

        stockBroker.perform(portfolio, stock);

        verify(portfolio, times(1)).buy(stock);
    }

    @Test
    void acaoEm10Porcento() {

        //percentGain valendo exatamente 0.1, ou seja 10%, então não vende e nem compra

        when(portfolio.getAvgPrice(stock)).thenReturn(new BigDecimal("100.0"));
        when(liveStock.getPrice()).thenReturn(new BigDecimal("110.00"));

        stockBroker.perform(portfolio, stock);

        verify(portfolio, times(0)).buy(stock);
        verify(portfolio, times(0)).sell(stock);
    }

    @Test
    void divisaoPorZero() {

        //AvgPrice valendo 0, então quando há o cálculo para saber o valor de percentGain, ocorre uma divisão por 0 e ocorre um erro

        when(portfolio.getAvgPrice(stock)).thenReturn(new BigDecimal("0.0"));
        when(liveStock.getPrice()).thenReturn(new BigDecimal("100.00"));

        stockBroker.perform(portfolio, stock);

        verify(portfolio, times(0)).buy(stock);
        verify(portfolio, times(0)).sell(stock);
    }
}