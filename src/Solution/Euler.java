package Solution;

import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.MathContext;

public class Euler {
    private float lim, step, x0, y0, c;
    private XYChart.Series<Number, Number> seriesE;
    private XYChart.Series<Number, Number> seriesEE;

    Euler(float x0, float y0, float step, float lim) {
        this.x0 = x0;
        this.y0 = y0;
        this.lim = lim;
        this.step = step;
        this.c = (float) (1 / (-1 * Math.exp(x0) + y0) + x0);
        seriesE = new XYChart.Series<>();
        seriesE.setName("Euler");
        seriesEE = new XYChart.Series<>();
        seriesEE.setName("Euler Error");
    }

    XYChart.Series<Number, Number> getSeries() {
        seriesE.getData().clear();
        seriesE.getData().add(new XYChart.Data<Number, Number>(x0, y0));
        double x = x0;
        double y = y0;
        double y1;

        //BigDecimal y = new BigDecimal(y0);


        while (x <= lim) {

            y1 = (y + step * (Math.exp(2 * x) + Math.exp(x) + y * y - 2 * y * Math.exp(x)));
            y = y1;

            //y = new BigDecimal((y.doubleValue() + step * (Math.exp(2*x) + Math.exp(x) + y.doubleValue()*y.doubleValue() + 2 * y.doubleValue() * Math.exp(x))), MathContext.DECIMAL64);
            x += step;
            seriesE.getData().add(new XYChart.Data<Number, Number>(x, y));
            System.out.println(x + " " + y);
        }

        return seriesE;
    }

    XYChart.Series<Number, Number> getError() {

        seriesEE.getData().clear();
        //System.out.println(x0 + " " + calculation(x0).subtract(new BigDecimal(y0)));
        double x = x0;
        double y = y0;
        double y1;

        seriesEE.getData().add(new XYChart.Data<Number, Number>(x, calculation(x).subtract(new BigDecimal(y))));

        while (x <= lim) {

            y1 = (y + step * (Math.exp(2 * x) + Math.exp(x) + y * y - 2 * y * Math.exp(x)));
            y = y1;

            x += step;
            seriesEE.getData().add(new XYChart.Data<Number, Number>(x, calculation(x).subtract(new BigDecimal(y))));
        }

        return seriesEE;
    }

    private BigDecimal calculation(double x) {
        BigDecimal b = new BigDecimal((Math.exp(x) + 1 / (c - x)), MathContext.DECIMAL64);
        return b;
    }


}
