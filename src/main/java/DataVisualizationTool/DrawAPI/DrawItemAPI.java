package DataVisualizationTool.DrawAPI;

import DataVisualizationTool.Accesories.Item;
import javax.swing.*;

/**
 * Created by Jeremy on 4/11/17.
 */
public abstract class DrawItemAPI implements DrawAPI {

    // Layer for the drawing drew by the api
    private JPanel layer;

    // The plot that the api will draw
    private Item item;

    // The layer that the layer of this item attaches to
    private JFrame container;

    /**
     * Constructor.
     *
     * @param item;
     * @param layer;
     */
    public DrawItemAPI (Item item, JPanel layer, JFrame container) {
        this. item = item;
        this. layer = layer;
        this.container = container;
    }

    /**
     * Draw layer.
     */
    private void drawLayer() {
        layer = new JPanel();
        container.add(layer);
    }

    /**
     * Plot data.
     */
    private void drawItem() {}

    /**
     * Plot every details of data.
     */
    public void draw() {
        drawLayer();
        drawItem();
    }
}
