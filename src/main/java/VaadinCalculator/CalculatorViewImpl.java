package VaadinCalculator;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

import java.util.ArrayList;
import java.util.List;

//the implemented Button.ClickListener is not the one of CalculatorView it's only to implement the buttonClick method
//the view is an observable and the presenter an observer
class CalculatorViewImpl extends CustomComponent
        implements CalculatorView, Button.ClickListener {
    private Label display = new Label("0.0");

    public CalculatorViewImpl() {
        GridLayout layout  = new GridLayout(4, 5);

        // Create a result label that spans over all
        // the 4 columns in the first row
        layout.addComponent(display, 0, 0, 3, 0);

        // The operations for the calculator in the order
        // they appear on the screen (left to right, top
        // to bottom)
        String[] operations = new String[] {
                "7", "8", "9", "/", "4", "5", "6",
                "*", "1", "2", "3", "-", "0", "=", "C", "+" };

        // Add buttons and have them send click events
        // to this class
        for (String caption: operations)
            layout.addComponent(new Button(caption, this));

        setCompositionRoot(layout);
    }

    public void setDisplay(double value) {
        display.setValue(Double.toString(value));
    }

    /* Only the presenter registers one listener... */
    // here is the list of observers for this view
    List<CalculatorViewListener> listeners =
            new ArrayList<CalculatorViewListener>();

    //the observers will be added to this list by the constructor of the presenter. see presenter
    public void addListener(CalculatorViewListener listener) {
        listeners.add(listener);
    }

    /** Relay button clicks to the presenter with an
     *  implementation-independent event */
    //override of the Button.ClickListener interface.
    //for each object the buttonclick method of the interface CalculatorViewListener is called
    @Override
    public void buttonClick(Button.ClickEvent event) {
        for (CalculatorViewListener listener: listeners)
            listener.buttonClick(event.getButton()
                    .getCaption().charAt(0));
    }
}
