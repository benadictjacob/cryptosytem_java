package fingerprints;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This program demonstrates how to draw XY line chart with XYDataset
 * using JFreechart library.
 * @author www.codejava.net
 *
 */
public class XYLineChartExample extends JFrame {

	public XYLineChartExample() throws SQLException, ClassNotFoundException {
		super("FAR Graph");
		
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel() throws SQLException, ClassNotFoundException {
        
		String chartTitle = "Performance Graph";
		String xAxisLabel = "no of fingers";
		String yAxisLabel = "far";
		
		XYDataset dataset = createDataset();
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
				xAxisLabel, yAxisLabel, dataset,PlotOrientation.VERTICAL,true,true,true);
		
//		boolean showLegend = false;
//		boolean createURL = false;
//		boolean createTooltip = false;
//		
//		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
//				xAxisLabel, yAxisLabel, dataset, 
//				PlotOrientation.HORIZONTAL, showLegend, createTooltip, createURL);
		
		customizeChart(chart);
		
		// saves the chart as an image files
		File imageFile = new File("XYLineChart.png");
		int width = 640;
		int height = 480;
		
		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		return new ChartPanel(chart);
	}

	private XYDataset createDataset() throws SQLException, ClassNotFoundException {
                database d= new database();
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("FAR");
//		XYSeries series2 = new XYSeries("KNN");
//		XYSeries series3 = new XYSeries("Object 3");
		ResultSet rs= d.GetAnalysis(login1.id);
                while(rs.next()){
                
		series1.add(rs.getInt("no.finger"), rs.getDouble("far"));
		series1.add(rs.getInt("no.finger"), rs.getDouble("far"));
		series1.add(rs.getInt("no.finger"), rs.getDouble("far"));
		series1.add(rs.getInt("no.finger"), rs.getDouble("far"));
		series1.add(rs.getInt("no.finger"), rs.getDouble("far"));
                }
//                ResultSet rs1= d.graphKnn();
//                while(rs1.next()){
//		series2.add(rs1.getInt("num"), rs1.getDouble("accuracy"));
//		series2.add(rs1.getInt("num"), rs1.getDouble("accuracy"));
//		series2.add(rs1.getInt("num"), rs1.getDouble("accuracy"));
//		series2.add(rs1.getInt("num"), rs1.getDouble("accuracy"));
//		series2.add(rs1.getInt("num"), rs1.getDouble("accuracy"));
//                }
//		series3.add(1.2, 4.0);
//		series3.add(2.5, 4.4);
//		series3.add(3.8, 4.2);
//		series3.add(4.3, 3.8);
//		series3.add(4.5, 4.0);
		
		dataset.addSeries(series1);
//		dataset.addSeries(series2);
//		dataset.addSeries(series3);
		
		return dataset;
	}
	
	private void customizeChart(JFreeChart chart) {
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
//		renderer.setSeriesPaint(2, Color.YELLOW);

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
//		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		
		// sets paint color for plot outlines
		plot.setOutlinePaint(Color.BLUE);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		
		// sets renderer for lines
		plot.setRenderer(renderer);
		
		// sets plot background
		plot.setBackgroundPaint(Color.DARK_GRAY);
		
		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			}
		});
	}
}