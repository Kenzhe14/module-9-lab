interface IPaymentProcessor {
    void processPayment(double amount);
    void refundPayment(double amount);
}

class InternalPaymentProcessor implements IPaymentProcessor {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of " + amount + " via internal system.");
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Refunding payment of " + amount + " via internal system.");
    }
}

class ExternalPaymentSystemA {
    public void makePayment(double amount) {
        System.out.println("Making payment of " + amount + " via External Payment System A.");
    }

    public void makeRefund(double amount) {
        System.out.println("Making refund of " + amount + " via External Payment System A.");
    }
}

class ExternalPaymentSystemB {
    public void sendPayment(double amount) {
        System.out.println("Sending payment of " + amount + " via External Payment System B.");
    }

    public void processRefund(double amount) {
        System.out.println("Processing refund of " + amount + " via External Payment System B.");
    }
}

class PaymentAdapterA implements IPaymentProcessor {
    private ExternalPaymentSystemA externalSystemA;

    public PaymentAdapterA(ExternalPaymentSystemA externalSystemA) {
        this.externalSystemA = externalSystemA;
    }

    @Override
    public void processPayment(double amount) {
        externalSystemA.makePayment(amount);
    }

    @Override
    public void refundPayment(double amount) {
        externalSystemA.makeRefund(amount);
    }
}

class PaymentAdapterB implements IPaymentProcessor {
    private ExternalPaymentSystemB externalSystemB;

    public PaymentAdapterB(ExternalPaymentSystemB externalSystemB) {
        this.externalSystemB = externalSystemB;
    }

    @Override
    public void processPayment(double amount) {
        externalSystemB.sendPayment(amount);
    }

    @Override
    public void refundPayment(double amount) {
        externalSystemB.processRefund(amount);
    }
}

class PaymentAdapter {
    public static IPaymentProcessor getPaymentProcessor(String currency, String region) {
        if ("KZT".equals(currency) && "KZ".equals(region)) {
            return new InternalPaymentProcessor();
        } else if ("USD".equals(currency) && "USA".equals(region)) {
            return new PaymentAdapterA(new ExternalPaymentSystemA());
        } else {
            return new PaymentAdapterB(new ExternalPaymentSystemB());
        }
    }
}

public class Main2 {
    public static void main(String[] args) {
        IPaymentProcessor internalProcessor = new InternalPaymentProcessor();
        internalProcessor.processPayment(100.0);
        internalProcessor.refundPayment(50.0);

        ExternalPaymentSystemA externalSystemA = new ExternalPaymentSystemA();
        IPaymentProcessor adapterA = new PaymentAdapterA(externalSystemA);
        adapterA.processPayment(200.0);
        adapterA.processPayment(200.0);
        adapterA.refundPayment(100.0);

        ExternalPaymentSystemB externalSystemB = new ExternalPaymentSystemB();
        IPaymentProcessor adapterB = new PaymentAdapterB(externalSystemB);
        adapterB.processPayment(300.0);
        adapterB.refundPayment(150.0);
        System.out.println("\n");
        System.out.println("Payment Adapter");
        System.out.println("KZ");
        IPaymentProcessor kzProcess = PaymentAdapter.getPaymentProcessor("KZT", "KZ");
        kzProcess.processPayment(200);
        kzProcess.refundPayment(100);

        System.out.println("USA");
        IPaymentProcessor usaProcess = PaymentAdapter.getPaymentProcessor("USD", "USA");
        usaProcess.processPayment(500);
        usaProcess.refundPayment(200);

        System.out.println("UZB");
        IPaymentProcessor usbProcess = PaymentAdapter.getPaymentProcessor("USB","SOM");
        usbProcess.processPayment(300);
        usbProcess.refundPayment(100);
    }
}
