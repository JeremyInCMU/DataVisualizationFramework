package Framework.GUI;

import DataReader.ExternalAPI.ExternalAPI;
import Framework.core.DataVisualizationBox;
import Listener.FrameworkChangeListener;
import Plugin.DataVisualizationPlugin;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import Data.Data;
/**
 * The framework gui implementation. This class is responsible for displaying
 * the framework GUI to the screen, and for forwarding events to
 * {@link Framework.core.DataVisualizationBox} when GUI related events are detected.
 * Created by Jeremy on 4/17/17.
 */
public class DataVisualizationGUI implements FrameworkChangeListener {

    // Default JFrame title.
    private static final String DEFAULT_TITLE = "Data Visualization Box";

    // Menu titles.
    private static final String MENU_TITLE = "File";
    private static final String MENU_EXIT = "Exit";
    private static final String MENU_NEW_BOX = "New Box...";
    private static final String MENU_ADD_API = "Add API...";
    private static final String MENU_REMOVE_API = "Remove API...";

    // Panel border titles
    private static final String DATA_READER_BORDER_TITLE = "Import Data";
    private static final String VISUAL_BORDER_TITLE = "Visualization";

    // Dialog titles and messages.
    private static final String ADD_API_TITLE = "Add New API";
    private static final String ADD_API_MSG = "Enter API name:";
    private static final String REMOVE_API_TITLE = "Remove API";
    private static final String REMOVE_API_MSG = "Select a api to remove.";
    private static final String ERROR_ADD_API_TITLE = "Error!";
    private static final String ERROR_ADD_API_MSG = "Cannot add api with box in progress.";
    private static final String ERROR_REMOVE_API_TITLE = "Error!";
    private static final String ERROR_REMOVE_API_MSG = "Cannot remove api with box in progress.";
    private static final String ERROR_NO_API_TITLE = "Error!";
    private static final String ERROR_NO_API_MSG = "No api are added to the box!";

    // Parameter setting labels.
    private static final String API_TITLE = "External API:";
    private static final String DATA_SOURCE_TITLE = "Data Source:";
    private static final String TYPE_TITLE = "Data Source Type:";
    private static final String PCIK_DATA_SET_LABEL = "Pick A Data Set:";
    private static final String SET_VISUALIZATION_PARAMETER_TITLE = "Set Visualization Parameters";
    private static final String PICK_ATTRIBUTE = "Select Attribute";
    private static final String RESULT_TITLE = "Result";

    // Plot button names.
    private static final String BAR_CHART_TITLE = "Bar Chart";
    private static final String PIE_CHART_TITLE = "Pie Chart";
    private static final String XY_PLOT = "XY Plot";
    private static final String HISTOGRAM_TITLE = "Histogram";

    // Visualization button name.
    private static final String VISUALIZATION_BUTTON_NAME = "Visualize";

    // Color for border.
    private final static Color color = new Color(100, 149, 237);

    // Types of data source.
    private final String[] DATA_SOURCE_TYPES = {"Local File", "Web Page", "External API", "Sensor"};

    // Current data label.
    private static final String DEFAULT_CUR_DATA_LABEL = "Current Data Set is: null";

    // Default footer text.
    private static final String DEFAULT_FOOTER_TEXT = "Please select a new plot.";


    // The parent JFrame window.
    private final JFrame frame;

    // Outer panel contains the Data/footer labels and inner panel.
    private final JPanel outerPanel;

    // Inner panel contains user interface to input parameters for data reader.
    private final JPanel readerInnerPanel;

    // Inner panel contains user interface to input parameters for data visualization.
    private final JPanel visualInnerPanel;

    // The label displaying current data at the top of the frame.
    private JLabel currentDataLabel;

    // The label displayed at the bottom of the frame.
    private final JLabel footerLabel;

    // Menu-related stuff.
    private final JMenuItem newBoxMenu;
    private final JMenuItem addAPIMenuItem;
    private final JMenuItem removeAPIMenuItem;

    // Inner panel components (Parameters for reader and visualization)
    private DefaultComboBoxModel<String> apiModels;
    private JComboBox<String> apiOptions;
    private JComboBox<String> typeOptions;
    private TextField dataSourceField;
    private DefaultComboBoxModel<String> dataSetsModels;
    private JComboBox<String> dataSetsOptions;

    // Box this GUI attached to.
    private DataVisualizationBox box;
    private DataVisualizationPlugin currentPlugin;

    public DataVisualizationGUI(DataVisualizationBox box) {
        this.box = box;

        // Create parent frame.
        frame = new JFrame(DEFAULT_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 500));

        // Create outer panel.
        outerPanel = new JPanel(new BorderLayout());

        currentDataLabel = new JLabel(DEFAULT_CUR_DATA_LABEL);
        currentDataLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        outerPanel.add(currentDataLabel, BorderLayout.NORTH);

        footerLabel = new JLabel(DEFAULT_FOOTER_TEXT);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        outerPanel.add(footerLabel, BorderLayout.SOUTH);


        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
        outerPanel.add(mainPanel);

        // Set main panel.
        GridLayout panelLayout = new GridLayout(0,1 );
        mainPanel.setLayout(panelLayout);

        readerInnerPanel = new JPanel();
        LineBorder readerPanelLineBorder = new LineBorder(color, 2);
        TitledBorder readerPanelBorder = new TitledBorder(readerPanelLineBorder, DATA_READER_BORDER_TITLE);
        readerPanelBorder.setTitleColor(color);
        readerInnerPanel.setBorder(readerPanelBorder);
        mainPanel.add(readerInnerPanel, BorderLayout.CENTER);

        visualInnerPanel = new JPanel();
        LineBorder visualPanelLineBorder = new LineBorder(color, 2);
        TitledBorder visualPanelBorder = new TitledBorder(visualPanelLineBorder, VISUAL_BORDER_TITLE);
        visualPanelBorder.setTitleColor(color);
        visualInnerPanel.setBorder(visualPanelBorder);
        mainPanel.add(visualInnerPanel, BorderLayout.CENTER);

        frame.add(outerPanel);

        // Set-up the menu bar.
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(MENU_TITLE);
        fileMenu.setMnemonic(KeyEvent.VK_F);

        // Add an add api menu
        addAPIMenuItem = new JMenuItem(MENU_ADD_API);
        addAPIMenuItem.setMnemonic(KeyEvent.VK_N);
        addAPIMenuItem.addActionListener(e -> {

            String name =  (String) JOptionPane.showInputDialog(frame, ADD_API_MSG,
                            ADD_API_TITLE, JOptionPane.PLAIN_MESSAGE, null, null, "");

            // Instantiate the external api and add to the api container in box.
            if (name != null) {
                try {
                    ExternalAPI api = (ExternalAPI) Class.forName(name).newInstance();
                    String[] elements = name.split("\\.");
                    apiModels.addElement(elements[elements.length - 1]);
                    box.addAPI(elements[elements.length - 1], api);
                } catch (ClassNotFoundException e1) {
                    System.out.println(e1.getMessage());
                } catch (InstantiationException e2) {
                    System.out.println(e2.getMessage());
                } catch (IllegalAccessException e3) {
                    System.out.println(e3.getMessage());
                }
            }
        });
        fileMenu.add(addAPIMenuItem);

        //Remove an api menu
        removeAPIMenuItem = new JMenuItem(MENU_REMOVE_API);
        removeAPIMenuItem.setMnemonic(KeyEvent.VK_R);
        removeAPIMenuItem.addActionListener(e -> {
            String name = (String) JOptionPane.showInputDialog(frame, REMOVE_API_MSG,
                           REMOVE_API_TITLE, JOptionPane.PLAIN_MESSAGE, null, null, "");

            if (name != null) {
                box.removeAPI(name);
                apiModels.removeElement(name);
            }
        });
        fileMenu.add(removeAPIMenuItem);

        // Add a new box menu.
        newBoxMenu = new JMenuItem(MENU_NEW_BOX);
        newBoxMenu.setMnemonic(KeyEvent.VK_N);

        fileMenu.add(newBoxMenu);
        // Add a separator between new box and exit
        fileMenu.addSeparator();

        JMenuItem exitMenueItem = new JMenuItem(MENU_EXIT);
        exitMenueItem.setMnemonic(KeyEvent.VK_X);
        exitMenueItem.addActionListener(Event -> System.exit(0));
        fileMenu.add(exitMenueItem);

        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);

        //Set up inner panels.
        setUpDataReaderInnerPanel(readerInnerPanel);
        setUpDataVisualInnerPanel(visualInnerPanel);

    }

    // Helper methods.

    /**
     * Set components in inner panel for data reader.
     * @param innerPanel
     */
    private void setUpDataReaderInnerPanel(JPanel innerPanel) {

        // Set lay out
        GridLayout layout = new GridLayout(2, 2);
        innerPanel.setLayout(layout);

        // Label for api option
        JPanel apiPanel = new JPanel();
        JLabel apiLabel = new JLabel(API_TITLE);
        // Combox for api option.
        Vector<String> apiItems = new Vector<>();
        apiItems.addElement("None");
        box.addAPI("None", null);
        apiModels = new DefaultComboBoxModel<>(apiItems);
        apiOptions = new JComboBox<>(apiModels);
        // Add components to panel.
        apiPanel.add(apiLabel);
        apiPanel.add(apiOptions);
        innerPanel.add(apiPanel);

        // Label for data source type
        JPanel typePanel = new JPanel();
        JLabel typeLabel = new JLabel(TYPE_TITLE);
        // Combox for data source type option.
        String[] types = DATA_SOURCE_TYPES;
        typeOptions = new JComboBox<>(types);
        // Add components to panel.
        typePanel.add(typeLabel);
        typePanel.add(typeOptions);
        innerPanel.add(typePanel);

        // Label for data source
        JPanel dataSourcePanel = new JPanel();
        JLabel dataSourceLabel = new JLabel(DATA_SOURCE_TITLE);
        // Text field for data source
        dataSourceField = new TextField(20);
        // Add components to panel.
        dataSourcePanel.add(dataSourceLabel);
        dataSourcePanel.add(dataSourceField);
        innerPanel.add(dataSourcePanel);

        // Button to import data from data source.
        JPanel buttonPanel = new JPanel();
        JButton importBtn = new JButton(DATA_READER_BORDER_TITLE);
        importBtn.addActionListener(e -> {

            readyToImportData();
            currentDataLabel.setText(box.getCurrentData().getName());
            dataSetsModels.addElement(box.getCurrentData().getName());
        });
        buttonPanel.add(importBtn);
        innerPanel.add(buttonPanel);
    }

    /**
     * Notify the box that ready to import data.
     */
    @Override
    public void readyToImportData() {
        String apiName = apiOptions.getSelectedItem().toString();
        ExternalAPI api = box.getAPI(apiName);
        String dataSource = dataSourceField.getText();
        String type = typeOptions.getSelectedItem().toString();
        box.readData(api, type, dataSource);
    }

    @Override
    public void currentDataSetChange(String dataSetName) {
        box.setCurrentData(dataSetName);
    }

    /**
     * Set components in inner panel for data visualization.
     * @param innerPanel
     */
    private void setUpDataVisualInnerPanel(JPanel innerPanel) {
        // Set layout.
        GridLayout layout = new GridLayout(0,1);
        innerPanel.setLayout(layout);

        // Pick data set
        JPanel pickDataSetPanel = new JPanel();
        innerPanel.add(pickDataSetPanel);

        // Set label
        JLabel pickDataSetLabel = new JLabel(PCIK_DATA_SET_LABEL);
        // Combox for pick data set
        Vector<String> dataSets = new Vector<>();
        dataSets.addElement("None");
        dataSetsModels = new DefaultComboBoxModel<>(dataSets);
        dataSetsOptions = new JComboBox<>(dataSetsModels);
        dataSetsOptions.addActionListener(e -> {
            currentDataSetChange((String) dataSetsOptions.getSelectedItem());
        });
        // Add components to inner panel.
        pickDataSetPanel.add(pickDataSetLabel);
        pickDataSetPanel.add(dataSetsOptions);


        // Visualize button group
        JPanel visualizeBtnPanel = new JPanel();
        innerPanel.add(visualizeBtnPanel);

        // Histogram Button
        JPanel histogramPanel = new JPanel();
        JButton histogramBtn = new JButton(HISTOGRAM_TITLE);
        histogramBtn.addActionListener(e -> {
            Thread t = new Thread(new HistogramInterface());
            t.start();
        });
        histogramPanel.add(histogramBtn);
        visualizeBtnPanel.add(histogramPanel);


        // Bar Chart Button
        JPanel barChartPanel = new JPanel();
        JButton barChartBtn = new JButton(BAR_CHART_TITLE);
        barChartBtn.addActionListener(e -> {
            Thread t = new Thread(new BarChartInterface());
            t.start();
        });
        barChartPanel.add(barChartBtn);
        visualizeBtnPanel.add(barChartPanel);

        // Pie Chart Button
        JPanel pieChartPanel = new JPanel();
        JButton pieChartBtn = new JButton(PIE_CHART_TITLE);
        pieChartBtn.addActionListener(e -> {
            Thread t = new Thread(new PieChartInterface());
            t.start();
        });
        pieChartPanel.add(pieChartBtn);
        visualizeBtnPanel.add(pieChartPanel);

        // X Y plot Button
        JPanel xyPlotPanel = new JPanel();
        JButton xyPlotBtn = new JButton(XY_PLOT);
        xyPlotBtn.addActionListener(e -> {
            Thread t= new Thread(new XYPlotInterface());
            t.start();
        });
        xyPlotPanel.add(xyPlotBtn);
        visualizeBtnPanel.add(xyPlotPanel);
    }


    /**
     * Histogram interface implementation.
     * @author Jeremy
     */
    private class HistogramInterface implements Runnable {

        @Override
        public void run() {
            JFrame histogramFrame = new JFrame(HISTOGRAM_TITLE);

            JPanel mainPanel = new JPanel();
            histogramFrame.add(mainPanel);

            //Set parameter option panel.
            JPanel parameterPanel = new JPanel();
            LineBorder parameterPanelLineBorder = new LineBorder(color, 2);
            TitledBorder parameterPanelBorder = new TitledBorder(parameterPanelLineBorder, SET_VISUALIZATION_PARAMETER_TITLE);
            parameterPanelBorder.setTitleColor(color);
            parameterPanel.setBorder(parameterPanelBorder);

            // Set combox for attributes.
            // Set label of it.
            JLabel setParameter = new JLabel(PICK_ATTRIBUTE);
            // Combox for pick data set
            Vector<String> attributeSet = new Vector<>();
            attributeSet.addElement("None");
            List<String> attributes = box.getDataByName(dataSetsOptions.getSelectedItem().toString()).getAttributes();
            for (String attribute: attributes) {
                attributeSet.addElement(attribute);
            }
            DefaultComboBoxModel attributeSetModels = new DefaultComboBoxModel<>(attributeSet);
            JComboBox attributeOptions = new JComboBox<>(attributeSetModels);

            // Set visualization button.
            JButton visualizeBtn = new JButton(VISUALIZATION_BUTTON_NAME);
            visualizeBtn.addActionListener(e -> {
                PerformHistogramVisualization perform = new PerformHistogramVisualization(attributeSetModels);
                Thread thread = new Thread(perform);
                thread.start();
                histogramFrame.dispose();
            });

            // Add components to inner panel.
            parameterPanel.add(setParameter);
            parameterPanel.add(attributeOptions);
            parameterPanel.add(visualizeBtn);
            mainPanel.add(parameterPanel, BorderLayout.CENTER);

            histogramFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            histogramFrame.pack();
            histogramFrame.setResizable(true);
            histogramFrame.setVisible(true);
        }
    }

    /**
     * Bar chart interface.
     * @author Jeremy
     */
    private class BarChartInterface implements Runnable {

        @Override
        public void run() {
            JFrame barChartFrame = new JFrame(BAR_CHART_TITLE);

            JPanel mainPanel = new JPanel();
            barChartFrame.add(mainPanel);

            //Set parameter option panel.
            JPanel parameterPanel = new JPanel();
            LineBorder parameterPanelLineBorder = new LineBorder(color, 2);
            TitledBorder parameterPanelBorder = new TitledBorder(parameterPanelLineBorder, SET_VISUALIZATION_PARAMETER_TITLE);
            parameterPanelBorder.setTitleColor(color);
            parameterPanel.setBorder(parameterPanelBorder);

            // Set combox for attributes.

            // Set label for attributes of x axis.
            JLabel setXLabel = new JLabel(PICK_ATTRIBUTE);
            // Set label for atrributes of y axis.
            JLabel setYLabel = new JLabel(PICK_ATTRIBUTE);
            // Comboxes for pick data set
            Vector<String> attributeSetX = new Vector<>();
            attributeSetX.addElement("None");

            Vector<String> attributeSetY = new Vector<>();
            attributeSetY.addElement("None");

            Data data = box.getDataByName(dataSetsOptions.getSelectedItem().toString());
            List<String> attributes = data.getAttributes();
            for (String attribute: attributes) {
                if(data.getFeatures(attribute).get(0) instanceof String) {
                    attributeSetX.addElement(attribute);
                } else {
                    attributeSetY.addElement(attribute);
                }
            }

            DefaultComboBoxModel attributeSetXModels = new DefaultComboBoxModel<>(attributeSetX);
            JComboBox attributeXOptions = new JComboBox<>(attributeSetXModels);

            DefaultComboBoxModel attributeSetYModels = new DefaultComboBoxModel<>(attributeSetY);
            JComboBox attributeYOptions = new JComboBox<>(attributeSetYModels);

            // Set visualization button.
            JButton visualizeBtn = new JButton(VISUALIZATION_BUTTON_NAME);
            visualizeBtn.addActionListener(e -> {
                PerformBarChartVisualization perform = new PerformBarChartVisualization(attributeSetXModels, attributeSetYModels);
                Thread thread = new Thread(perform);
                thread.start();
                barChartFrame.dispose();
            });

            // Add components to inner panel.
            parameterPanel.add(setXLabel);
            parameterPanel.add(attributeXOptions);
            parameterPanel.add(setYLabel);
            parameterPanel.add(attributeYOptions);
            parameterPanel.add(visualizeBtn);
            mainPanel.add(parameterPanel);

            barChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            barChartFrame.pack();
            barChartFrame.setResizable(true);
            barChartFrame.setVisible(true);
        }
    }

    /**
     * Pie chart inerface.
     * @author Jeremy
     */
    private class PieChartInterface implements Runnable {

        @Override
        public void run() {
            JFrame pieChartFrame = new JFrame(PIE_CHART_TITLE);

            JPanel mainPanel = new JPanel();
            pieChartFrame.add(mainPanel);

            //Set parameter option panel.
            JPanel parameterPanel = new JPanel();
            LineBorder parameterPanelLineBorder = new LineBorder(color, 2);
            TitledBorder parameterPanelBorder = new TitledBorder(parameterPanelLineBorder, SET_VISUALIZATION_PARAMETER_TITLE);
            parameterPanelBorder.setTitleColor(color);
            parameterPanel.setBorder(parameterPanelBorder);

            // Set combox for attributes.

            // Set label for attributes of x axis.
            JLabel setXLabel = new JLabel(PICK_ATTRIBUTE);
            // Set label for atrributes of y axis.
            JLabel setYLabel = new JLabel(PICK_ATTRIBUTE);
            // Comboxes for pick data set
            Vector<String> attributeSetX = new Vector<>();
            attributeSetX.addElement("None");

            Vector<String> attributeSetY = new Vector<>();
            attributeSetY.addElement("None");

            Data data = box.getDataByName(dataSetsOptions.getSelectedItem().toString());
            List<String> attributes = data.getAttributes();
            for (String attribute: attributes) {
                if(data.getFeatures(attribute).get(0) instanceof String) {
                    attributeSetX.addElement(attribute);
                } else {
                    attributeSetY.addElement(attribute);
                }
            }

            DefaultComboBoxModel attributeSetXModels = new DefaultComboBoxModel<>(attributeSetX);
            JComboBox attributeXOptions = new JComboBox<>(attributeSetXModels);

            DefaultComboBoxModel attributeSetYModels = new DefaultComboBoxModel<>(attributeSetY);
            JComboBox attributeYOptions = new JComboBox<>(attributeSetYModels);

            // Set visualization button.
            JButton visualizeBtn = new JButton(VISUALIZATION_BUTTON_NAME);
            visualizeBtn.addActionListener(e -> {
                PerformPieChartVisualization perform = new PerformPieChartVisualization(attributeSetXModels, attributeSetYModels);
                Thread thread = new Thread(perform);
                thread.start();
                pieChartFrame.dispose();
            });

            // Add components to inner panel.
            parameterPanel.add(setXLabel);
            parameterPanel.add(attributeXOptions);
            parameterPanel.add(setYLabel);
            parameterPanel.add(attributeYOptions);
            parameterPanel.add(visualizeBtn);
            mainPanel.add(parameterPanel);

            pieChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pieChartFrame.pack();
            pieChartFrame.setResizable(true);
            pieChartFrame.setVisible(true);
        }
    }


    /**
     * XY Plot interface.
     * @author Jeremy
     */
    private class XYPlotInterface implements Runnable {

        @Override
        public void run() {
            JFrame XYPlotFrame = new JFrame(XY_PLOT);

            JPanel mainPanel = new JPanel();
            XYPlotFrame.add(mainPanel);

            //Set parameter option panel.
            JPanel parameterPanel = new JPanel();
            LineBorder parameterPanelLineBorder = new LineBorder(color, 2);
            TitledBorder parameterPanelBorder = new TitledBorder(parameterPanelLineBorder, SET_VISUALIZATION_PARAMETER_TITLE);
            parameterPanelBorder.setTitleColor(color);
            parameterPanel.setBorder(parameterPanelBorder);

            // Set combox for attributes.

            // Set label for attributes of x axis.
            JLabel setXLabel = new JLabel(PICK_ATTRIBUTE);
            // Set label for atrributes of y axis.
            JLabel setYLabel = new JLabel(PICK_ATTRIBUTE);
            // Comboxes for pick data set
            Vector<String> attributeSetX = new Vector<>();
            attributeSetX.addElement("None");

            Vector<String> attributeSetY = new Vector<>();
            attributeSetY.addElement("None");

            Data data = box.getDataByName(dataSetsOptions.getSelectedItem().toString());
            List<String> attributes = data.getAttributes();
            for (String attribute: attributes) {
                if(!(data.getFeatures(attribute).get(0) instanceof String)) {
                    attributeSetX.addElement(attribute);
                    attributeSetY.addElement(attribute);
                }
            }

            DefaultComboBoxModel attributeSetXModels = new DefaultComboBoxModel<>(attributeSetX);
            JComboBox attributeXOptions = new JComboBox<>(attributeSetXModels);

            DefaultComboBoxModel attributeSetYModels = new DefaultComboBoxModel<>(attributeSetY);
            JComboBox attributeYOptions = new JComboBox<>(attributeSetYModels);

            // Set visualization button.
            JButton visualizeBtn = new JButton(VISUALIZATION_BUTTON_NAME);
            visualizeBtn.addActionListener(e -> {
                PerformXYPlotVisualization perform = new PerformXYPlotVisualization(attributeSetXModels, attributeSetYModels);
                Thread thread = new Thread(perform);
                thread.start();
                XYPlotFrame.dispose();
            });

            // Add components to inner panel.
            parameterPanel.add(setXLabel);
            parameterPanel.add(attributeXOptions);
            parameterPanel.add(setYLabel);
            parameterPanel.add(attributeYOptions);
            parameterPanel.add(visualizeBtn);
            mainPanel.add(parameterPanel);

            XYPlotFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            XYPlotFrame.pack();
            XYPlotFrame.setResizable(true);
            XYPlotFrame.setVisible(true);
        }
    }

    /**
     * Perform Histogrm Visualization.
     * @author Jeremy
     */
    private class PerformHistogramVisualization implements Runnable {

        private DefaultComboBoxModel model;

        /**
         * Constuctor.
         */
        public PerformHistogramVisualization(DefaultComboBoxModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            List<String> attributes = new ArrayList<>();
            attributes.add(model.getSelectedItem().toString());
            box.notifyDataVisualization(attributes, HISTOGRAM_TITLE, dataSetsModels.getSelectedItem().toString());
        }
    }

    /**
     * Perform Bar Chart Visualization.
     * @author Jeremy
     */
    private class PerformBarChartVisualization implements Runnable {

        private DefaultComboBoxModel modelX;
        private DefaultComboBoxModel modelY;

        /**
         * Constuctor.
         */
        public PerformBarChartVisualization(DefaultComboBoxModel modelX, DefaultComboBoxModel modelY) {
            this.modelX = modelX;
            this.modelY = modelY;
        }

        @Override
        public void run() {
            List<String> attributes = new ArrayList<>();
            attributes.add(modelX.getSelectedItem().toString());
            attributes.add(modelY.getSelectedItem().toString());
            box.notifyDataVisualization(attributes, BAR_CHART_TITLE, dataSetsModels.getSelectedItem().toString());
        }
    }

    /**
     * Perform Pie Chart Visualization.
     * @author Jeremy
     */
    private class PerformPieChartVisualization implements Runnable {

        private DefaultComboBoxModel modelX;
        private DefaultComboBoxModel modelY;

        /**
         * Constuctor.
         */
        public PerformPieChartVisualization(DefaultComboBoxModel modelX, DefaultComboBoxModel modelY) {
            this.modelX = modelX;
            this.modelY = modelY;
        }

        @Override
        public void run() {
            List<String> attributes = new ArrayList<>();
            attributes.add(modelX.getSelectedItem().toString());
            attributes.add(modelY.getSelectedItem().toString());
            box.notifyDataVisualization(attributes, PIE_CHART_TITLE, dataSetsModels.getSelectedItem().toString());
        }
    }

    /**
     * Perform Pie Chart Visualization.
     * @author Jeremy
     */
    private class PerformXYPlotVisualization implements Runnable {

        private DefaultComboBoxModel modelX;
        private DefaultComboBoxModel modelY;

        /**
         * Constuctor.
         */
        public PerformXYPlotVisualization(DefaultComboBoxModel modelX, DefaultComboBoxModel modelY) {
            this.modelX = modelX;
            this.modelY = modelY;
        }

        @Override
        public void run() {
            List<String> attributes = new ArrayList<>();
            attributes.add(modelX.getSelectedItem().toString());
            attributes.add(modelY.getSelectedItem().toString());
            box.notifyDataVisualization(attributes, XY_PLOT, dataSetsModels.getSelectedItem().toString());
        }
    }

    /**
     * Show error dialog.
     * @param c : component
     * @param title : title of the error
     * @param msg : error message
     */
    private static void showErrorDialog(Component c, String title, String msg) {
        JOptionPane.showMessageDialog(c, msg, title, JOptionPane.ERROR_MESSAGE);
    }
}
