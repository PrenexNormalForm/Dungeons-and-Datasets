/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
/*
Last updated 2019-10-22

This is a custom JFX component which has an arbitrary number of child nodes,
but displays only one at any time. The currently displayed node can be
changed during runtime. Each child node has a {@code String} identifier.

Contributors:
Eva Moniz
 */

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * This is a custom JFX component which has an arbitrary number of child nodes,
 * but displays only one at any time. The currently displayed node can be
 * changed during runtime. Each child node has a {@code String} identifier.
 *
 * @author Eva Moniz
 */
public class SwitchPane extends AnchorPane {

    //the different nodes that this SwitchPane can display
    private final Map<String, Node> views;

    //the name of the current view node
    private String currentNodeName;

    /**
     * Constructs the SwitchPane with no children. Children can be added with
     * the {@code SwitchPane.addView} method.
     */
    public SwitchPane() {
        this.views = new HashMap<>();
        this.currentNodeName = null;
    }

    /**
     * Adds the given view to the collection of views.
     *
     * @param _viewName A unique string identifying the view
     * @param _viewNode The view node
     */
    protected void addView(String _viewName, Node _viewNode) {
        this.views.put(_viewName, _viewNode);
        SwitchPane.anchorFillNode(_viewNode);
    }

    /**
     * Hides the current view and displays the view with the given name.
     *
     * @param _viewName The name of the view to display
     */
    public void switchView(String _viewName) {
        this.validateViewName(_viewName);
        this.getChildren().clear();
        this.getChildren().add(this.getView(_viewName));
        this.currentNodeName = _viewName;
    }

    /**
     * Returns the node which is associated with the given view identifier.
     *
     * @param _viewName The name of the view to retrieve
     * @return The view with the given name
     */
    protected Node getView(String _viewName) {
        this.validateViewName(_viewName);
        return this.views.get(_viewName);
    }

    /**
     * Throws an exception if the given String is not the name of a view in this
     * SwitchPane.
     *
     * @param _viewName The name to validate
     */
    private void validateViewName(String _viewName) {
        if (!this.views.containsKey(_viewName)) {
            throw new IllegalArgumentException(
                    "SwitchPane view "
                    + _viewName
                    + " does not exist!");
        }
    }

    /**
     * Sets the anchors of the {@code _node} to fill an entire
     * {@code AnchorPane}
     *
     * @param _node the node to fill
     */
    private static void anchorFillNode(Node _node) {
        AnchorPane.setBottomAnchor(_node, 0.0);
        AnchorPane.setTopAnchor(_node, 0.0);
        AnchorPane.setLeftAnchor(_node, 0.0);
        AnchorPane.setRightAnchor(_node, 0.0);
    }
}
