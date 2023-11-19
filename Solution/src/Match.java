public class Match {
    private String id;
    private double rateA;
    private double rateB;
    private String result;

    public Match(String id, double rateA, double rateB, String result) {
        this.id = id;
        this.rateA = rateA;
        this.rateB = rateB;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRateA() {
        return rateA;
    }

    public void setRateA(double rateA) {
        this.rateA = rateA;
    }

    public double getRateB() {
        return rateB;
    }

    public void setRateB(double rateB) {
        this.rateB = rateB;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
