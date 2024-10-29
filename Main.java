interface IBeverage {
    double getCost();
    String getDescription();
}

class Coffee implements IBeverage {

    @Override
    public double getCost() {
        return 50.0;
    }

    @Override
    public String getDescription() {
        return "Coffee";
    }
}

class Cacao implements IBeverage {

    @Override
    public double getCost() {
        return 60;
    }

    @Override
    public String getDescription() {
        return "Cacao";
    }
}

abstract class BeverageDecorator implements IBeverage {
    protected IBeverage beverage;

    public BeverageDecorator(IBeverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double getCost() {
        return beverage.getCost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }
}

class MilkDecorator extends BeverageDecorator {

    public MilkDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 10.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
}

class SugarDecorator extends BeverageDecorator {

    public SugarDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 5.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }
}

class ChocolateDecorator extends BeverageDecorator {

    public ChocolateDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double getCost() {
        return super.getCost() + 15.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Chocolate";
    }
}
class VanileDecorator extends BeverageDecorator{
    public VanileDecorator(IBeverage beverage){
        super(beverage);
    }
    @Override
    public double getCost(){
        return super.getCost() + 20;
    }
    @Override
    public String getDescription(){
        return super.getDescription() + ", Vanile";
    }
}

public class Main {
    public static void main(String[] args) {
        IBeverage beverage = new Coffee();
        System.out.println(beverage.getDescription() + " : " + beverage.getCost());

        beverage = new MilkDecorator(beverage);
        System.out.println(beverage.getDescription() + " : " + beverage.getCost());

        beverage = new SugarDecorator(beverage);
        System.out.println(beverage.getDescription() + " : " + beverage.getCost());

        beverage = new ChocolateDecorator(beverage);
        System.out.println(beverage.getDescription() + " : " + beverage.getCost());

        beverage = new VanileDecorator(beverage);
        System.out.println(beverage.getDescription() + " : " + beverage.getCost());

        System.out.println("\n");

        IBeverage Cacao = new Cacao();
        System.out.println(Cacao.getDescription() + " : " + Cacao.getCost());

        Cacao = new ChocolateDecorator(Cacao);
        System.out.println(Cacao.getDescription() + " : " + Cacao.getCost());

        Cacao = new VanileDecorator(Cacao);
        System.out.println(Cacao.getDescription() + " : " + Cacao.getCost());

        Cacao = new MilkDecorator(Cacao);
        System.out.println(Cacao.getDescription() + " : " + Cacao.getCost());
    }
}
