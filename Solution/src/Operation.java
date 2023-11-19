public class Operation {
    private String event;
    private String matchId;
    private int amount;
    private String betSide;

    public Operation(String event, String matchId, int amount, String betSide) {
        this.event = event;
        this.matchId = matchId;
        this.amount = amount;
        this.betSide = betSide;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String type) {
        this.event = type;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBetSide() {
        return betSide;
    }

    public void setBetSide(String betSide) {
        this.betSide = betSide;
    }
}
