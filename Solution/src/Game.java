import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Map<String, Player> players;
    private Map<String, Match> matches;
    private long hostBalance;

    public Game() {
        this.players = new HashMap<>();
        this.matches = new HashMap<>();
        this.hostBalance = 0;
    }

    public List<Player> getLegitimatePlayers() {
        List<Player> legitimatePlayers = new ArrayList<>();
        for (Player player : players.values()) {
            if (isPlayerLegitimate(player)) {
                legitimatePlayers.add(player);
            }
        }
        return legitimatePlayers;
    }

    public List<Player> getIllegitimatePlayers() {
        List<Player> illegitimatePlayers = new ArrayList<>();
        for (Player player : players.values()) {
            if (player.hasIllegalOperations()) {
                illegitimatePlayers.add(player);
            }
        }
        return illegitimatePlayers;
    }


    private boolean isPlayerLegitimate(Player player) {
        return !player.hasIllegalOperations();
    }

    public void processPlayerData(List<String> playerDataLines) {
        for (String line : playerDataLines) {
            String[] data = line.split(",");
            String playerId = data[0];
            String operation = data[1];

            Player player = players.computeIfAbsent(playerId, Player::new);

            switch (operation) {
                case "DEPOSIT":
                    int amount = Integer.parseInt(data[3]);
                    player.deposit(amount);
                    break;
                case "WITHDRAW":
                    amount = Integer.parseInt(data[3]);
                    player.withdraw(amount);
                    break;
                case "BET":
                    String matchId = data[2];
                    amount = Integer.parseInt(data[3]);
                    String side = data[4];
                    player.placeBet(matchId, amount, side);
                    break;
                default:
                    System.out.println("Unknown operation");
            }
        }
    }

    public void processMatchData(List<String> matchDataLines) {
        for (String line : matchDataLines) {
            String[] data = line.split(",");
            String matchId = data[0];
            double rateA = Double.parseDouble(data[1]);
            double rateB = Double.parseDouble(data[2]);
            String result = data[3];

            Match match = new Match(matchId, rateA, rateB, result);
            matches.put(matchId, match);
        }
    }

    public void calculateResults() {
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            for (Operation operation : player.getOperations()) {
                if (operation.getEvent().equals("BET")) {
                    Match match = matches.get(operation.getMatchId());
                    processBetResult(player, operation, match);
                }
            }
        }
    }

    private void processBetResult(Player player, Operation operation, Match match) {
        String betSide = operation.getBetSide();
        int betAmount = operation.getAmount();
        double rate = betSide.equals("A") ? match.getRateA() : match.getRateB();
        String matchResult = match.getResult();

        if (betSide.equals(matchResult)) {
            int winAmount = (int) (betAmount * rate);
            player.updateBalance(winAmount);
            player.updateWinCount(true);
            updateHostBalance(-winAmount);
        } else if (!"Draw".equals(matchResult)) {
            updateHostBalance(betAmount);
        } else {
            player.updateBalance(betAmount);
        }
    }

    public void updateHostBalance(long amount) {
        this.hostBalance += amount;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public Map<String, Match> getMatches() {
        return matches;
    }

    public void setMatches(Map<String, Match> matches) {
        this.matches = matches;
    }

    public long getHostBalance() {
        return hostBalance;
    }

    public void setHostBalance(long hostBalance) {
        this.hostBalance = hostBalance;
    }

}
