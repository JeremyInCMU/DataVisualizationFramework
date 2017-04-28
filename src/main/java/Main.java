import DataReader.DataReader;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeToolFactory;
import DataVisualizationTool.DrawAPI.DrawDataAPIFactory;
import DataVisualizationTool.Plot.PlotFactory;
import Framework.GUI.DataVisualizationGUI;
import Framework.core.DataVisualizationBox;

import javax.swing.*;
import java.util.List;

/**
 * Created by Jeremy on 4/22/17.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndStartFramework);
    }

    private static void createAndStartFramework() {
        DataVisualizationBox box = new DataVisualizationBox("Data Visualization");
        DataVisualizationGUI gui = new DataVisualizationGUI(box);
        box.setFrameworkListener(gui);
        DataReader reader = new DataReader();
        box.setDataReader(reader);
        PlotFactory plotFactory = PlotFactory.getInstance();
        box.setPlotFactory(plotFactory);
        DrawDataAPIFactory drawAPIFactory = DrawDataAPIFactory.getInstance();
        box.setDrawDataAPIFactory(drawAPIFactory);
        DataRearrangeToolFactory dataRearrangeToolFactory = DataRearrangeToolFactory.getInstance();
        box.setDataRearrangeTool(dataRearrangeToolFactory);

    }
}
