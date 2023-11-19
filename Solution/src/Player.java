import java.util.ArrayList;
import java.util.List;

public class Player {
    private String id;
    private long balance;
    private final List<Operation> operations;
    private int winCount;
    private int betCount;
    private boolean hasIllegalOperation;
    private Operation firstIllegalOperation;

    public Player(String id) {
        this.id = id;
        this.balance = 0;
        this.operations = new ArrayList<>();
        this.winCount = 0;
        this.betCount = 0;
        this.hasIllegalOperation = false;
    }

    public void deposit(int amount) {
        this.balance += amount;
        operations.add(new Operation("DEPOSIT", null, amount, null));
    }

    public boolean withdraw(int amount) {
        if (this.balance < amount) {
            hasIllegalOperation = true;
            if (firstIllegalOperation == null) {
                firstIllegalOperation = new Operation("WITHDRAW", null, amount, null);
            }
            return false;
        }
        this.balance -= amount;
        operations.add(new Operation("WITHDRAW", null, amount, null));
        return true;
    }

    public boolean bet(String matchId, int amount, String side) {
        if (this.balance < amount) {
            hasIllegalOperation = true;
            if (firstIllegalOperation == null) {
                firstIllegalOperation = new Operation("BET", matchId, amount, side);
            }
            return false;
        }
        this.balance -= amount;
        operations.add(new Operation("BET", matchId, amount, side));
        betCount++;
        return true;
    }

    public void addWin() {
        winCount++;
    }

    public double calculateWinRate() {
        return betCount > 0 ? (double) winCount / betCount : 0;
    }

    public void placeBet(String matchId, int amount, String side) {
        if (this.balance >= amount) {
            this.balance -= amount;
            operations.add(new Operation("BET", matchId, amount, side));
            betCount++;
        }
    }

    public void updateWinCount(boolean won) {
        if (won) {
            winCount++;
        }
    }

    public void updateBalance(int amount) {
        this.balance += amount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getBetCount() {
        return betCount;
    }

    public void setBetCount(int betCount) {
        this.betCount = betCount;
    }

    public boolean isHasIllegalOperation() {
        return hasIllegalOperation;
    }

    public void setHasIllegalOperation(boolean hasIllegalOperation) {
        this.hasIllegalOperation = hasIllegalOperation;
    }

    public boolean hasIllegalOperations() {
        return isHasIllegalOperation();
    }

    public Operation getFirstIllegalOperation() {
        return firstIllegalOperation;
    }

    public void setFirstIllegalOperation(Operation firstIllegalOperation) {
        this.firstIllegalOperation = firstIllegalOperation;
    }
}
