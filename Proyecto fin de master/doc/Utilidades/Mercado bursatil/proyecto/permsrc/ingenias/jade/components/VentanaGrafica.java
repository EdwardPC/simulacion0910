/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.jade.components;

/**
 *
 * @author Victor
 */
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class VentanaGrafica extends JFrame {

    String title;

    public VentanaGrafica(String title,Vector precio,Vector volumen,Vector fecha) {

        super(title);
        this.title = title;
        JFreeChart chart = createChart(title,precio,volumen,fecha);
        ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);

    }

    public void actualizarPanel(Vector precio,Vector volumen,Vector fecha) {

        JFreeChart chart = createChart(title,precio,volumen,fecha);
        ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);

    }

    private static JFreeChart createChart(String empresa,Vector precio,Vector volumen,Vector fecha) {

        XYDataset priceData = createPriceDataset(precio,fecha);
        String title = empresa;
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            title,
            "Fecha",
            "Precio",
            priceData,
            true,
            true,
            false
        );
        XYPlot plot = chart.getXYPlot();
        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setLowerMargin(0.40);  
        DecimalFormat format = new DecimalFormat("00.00");
        rangeAxis1.setNumberFormatOverride(format);

        XYItemRenderer renderer1 = plot.getRenderer();
        renderer1.setToolTipGenerator(
            new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy HH:mm:ss"), new DecimalFormat("0.00")
            )
        );

        NumberAxis rangeAxis2 = new NumberAxis("Volumen");
        rangeAxis2.setUpperMargin(1.00);  
        plot.setRangeAxis(1, rangeAxis2);
        plot.setDataset(1, createVolumeDataset(volumen,fecha));
        plot.setRangeAxis(1, rangeAxis2);
        plot.mapDatasetToRangeAxis(1, 1);
        XYBarRenderer renderer2 = new XYBarRenderer(0.20);
        renderer2.setToolTipGenerator(
            new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-MMM-yyyy HH:mm:ss"), new DecimalFormat("0,000.00")
            )
        );
        plot.setRenderer(1, renderer2);
        return chart;

    }

    private static XYDataset createPriceDataset(Vector precio,Vector date) {

        TimeSeries series1 = new TimeSeries("Precio", Second.class);

        for(int i = 0; i<precio.size();i++){
            series1.add(new Second((Date)date.get(i)), (Double)precio.get(i));
        }

        return new TimeSeriesCollection(series1);

    }

    private static IntervalXYDataset createVolumeDataset(Vector volumen,Vector date) {

        TimeSeries series1 = new TimeSeries("Volumen", Second.class);

        for(int i = 0; i<volumen.size();i++){
            series1.add(new Second((Date)date.get(i)), (Double)volumen.get(i));
        }

        return new TimeSeriesCollection(series1);

    }

    public static JPanel createPanel(String empresa,Vector precio,Vector volumen,Vector fecha) {
        JFreeChart chart = createChart(empresa,precio,volumen,fecha);
        return new ChartPanel(chart);
    }

    public static VentanaGrafica start(String titulo,Vector precio,Vector volumen,Vector fecha) {

        VentanaGrafica ventana = new VentanaGrafica(titulo,precio,volumen,fecha);
        ventana.pack();
        RefineryUtilities.centerFrameOnScreen(ventana);
        return ventana;
       

    }

    void actualizar(Vector precio, Vector volumen, Vector fecha) {
        actualizarPanel(precio,volumen,fecha);
        this.pack();
    }

}
