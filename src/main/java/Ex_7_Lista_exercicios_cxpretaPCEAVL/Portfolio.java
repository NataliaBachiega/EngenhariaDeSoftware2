package Ex_7_Lista_exercicios_cxpretaPCEAVL;

import java.math.BigDecimal;

public interface Portfolio {
    public BigDecimal getAvgPrice(Stock stock);

    public void buy(Stock stock);

    public void sell(Stock stock);
}
