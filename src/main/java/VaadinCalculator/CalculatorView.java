package VaadinCalculator;


//interface for the view implementation
interface CalculatorView {
    public void setDisplay(double value);

    //this nested interface has to be implemented by the presenter
    //with this, the view hasn't to know anything about the presenter
    //it just calls the method buttonclick on the listeners. And the presenter is one of those listeners
    interface CalculatorViewListener {
        void buttonClick(char operation);
    }
    public void addListener(CalculatorViewListener listener);
}
