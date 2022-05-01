package service;

public class NormalPricing implements Pricing{
    @Override
    public double getPrice( double price ) {
        return price;
    }
}
