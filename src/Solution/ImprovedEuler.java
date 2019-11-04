package Solution;

import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class ImprovedEuler {
    private float lim, step, x0, y0, c;
    private XYChart.Series<Number, Number> seriesE;
    private XYChart.Series<Number, Number> seriesEE;

    ImprovedEuler(float x0, float y0, float step, float lim) {
        this.x0 = x0;
        this.y0 = y0;
        this.lim = lim;
        this.step = step;
        this.c = (float) (1 / (-1 * Math.exp(x0) + y0) + x0);
        seriesE = new XYChart.Series<Number, Number>();
        seriesE.setName("Improved Euler");
        seriesEE = new XYChart.Series<>();
        seriesEE.setName("Improved Euler Error");
    }

    XYChart.Series<Number, Number> getSeries() {
        seriesE.getData().clear();
        seriesE.getData().add(new XYChart.Data<Number, Number>(x0, y0));
        System.out.println(x0 + " " + y0);
        DecimalFormat df = new DecimalFormat("#.###");
        double x = x0;
        double y = y0;
        double k1;
        double k2;
        double y1;

        while (x <= lim) {
            x = Double.parseDouble(df.format(x));
            k1 = (Math.exp(2 * x) + Math.exp(x) + y * y - 2 * y * Math.exp(x));
            k1 = Double.parseDouble(df.format(k1));
            k2 = (Math.exp(2 * (x+step)) + Math.exp((x+step)) + (y + step * k1) * (y + step * k1) - 2 * (y + step * k1) * Math.exp((x+step)));
            k2 = Double.parseDouble(df.format(k2));
            y1 = y + (step / 2) * (k1 + k2);
            y1 = Double.parseDouble(df.format(y1));
            y = y1;

            //y = new BigDecimal((y.doubleValue() + step * (Math.exp(2*x) + Math.exp(x) + y.doubleValue()*y.doubleValue() + 2 * y.doubleValue() * Math.exp(x))), MathContext.DECIMAL64);

            seriesE.getData().add(new XYChart.Data<Number, Number>(x, y));
            System.out.println(k1 + "     " + k2 + "     " + x + "       " + y);
            x += step;
        }

        return seriesE;
    }

    XYChart.Series<Number, Number> getError() {
        seriesEE.getData().clear();
        seriesEE.getData().add(new XYChart.Data<Number, Number>(x0, y0));
        System.out.println(x0 + " " + y0);
        DecimalFormat df = new DecimalFormat("#.###");
        double x = x0;
        double y = y0;
        double k1;
        double k2;
        double y1;

        while (x <= lim) {
            x = Double.parseDouble(df.format(x));
            k1 = (Math.exp(2 * x) + Math.exp(x) + y * y - 2 * y * Math.exp(x));
            k1 = Double.parseDouble(df.format(k1));
            k2 = (Math.exp(2 * (x+step)) + Math.exp((x+step)) + (y + step * k1) * (y + step * k1) - 2 * (y + step * k1) * Math.exp((x+step)));
            k2 = Double.parseDouble(df.format(k2));
            y1 = y + (step / 2) * (k1 + k2);
            y1 = Double.parseDouble(df.format(y1));
            y = y1;

            //y = new BigDecimal((y.doubleValue() + step * (Math.exp(2*x) + Math.exp(x) + y.doubleValue()*y.doubleValue() + 2 * y.doubleValue() * Math.exp(x))), MathContext.DECIMAL64);

            seriesEE.getData().add(new XYChart.Data<Number, Number>(x, calculation(x).subtract(new BigDecimal(y))));
            System.out.println(k1 + "     " + k2 + "     " + x + "       " + y);
            x += step;
        }

        return seriesEE;
    }

    private BigDecimal calculation(double x) {
        BigDecimal b = new BigDecimal((Math.exp(x) + 1 / (c - x)), MathContext.DECIMAL64);
        return b;
    }
}
