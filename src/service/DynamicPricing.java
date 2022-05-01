package service;

public class DynamicPricing implements Pricing{

    @Override
    public double getPrice( double price ) {
        return 1.1 * price;
    }
}
