package VaadinCalculator;

// the presenter implements the CalculatorViewListener to have the method buttonclick
// with this, the view hasn't to know anything about the view
// the view is an observable and the presenter an observer
class CalculatorPresenter
        implements CalculatorView.CalculatorViewListener {
    CalculatorModel model;
    CalculatorView  view;

    private double current = 0.0;
    private char   lastOperationRequested = 'C';

    //prenster should have a model and a view in the constructor
    public CalculatorPresenter(CalculatorModel model,
                               CalculatorView  view) {
        this.model = model;
        this.view  = view;

        view.setDisplay(current);
        view.addListener(this);
    }

    //this is the override of the nested CalculatorViewListener interface
    //the view calls this method on the listeners(observer)
    @Override
    public void buttonClick(char operation) {
        // Handle digit input
        if ('0' <= operation && operation <= '9') {
            current = current * 10
                    + Double.parseDouble("" + operation);
            view.setDisplay(current);
            return;
        }

        // Execute the previously input operation
        switch (lastOperationRequested) {
            case '+':
                model.add(current);
                break;
            case '-':
                model.add(-current);
                break;
            case '/':
                model.divide(current);
                break;
            case '*':
                model.multiply(current);
                break;
            case 'C':
                model.setValue(current);
                break;
        } // '=' is implicit

        lastOperationRequested = operation;

        current = 0.0;
        if (operation == 'C')
            model.clear();
        view.setDisplay(model.getValue());
    }
}
