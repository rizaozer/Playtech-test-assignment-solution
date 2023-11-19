import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class ResultWriter {
    public static void writeResults(Game game, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writeIllegitimatePlayers(writer, game.getLegitimatePlayers());
            writeIllegitimatePlayers(writer, game.getIllegitimatePlayers());
            writeHostBalance(writer, game.getHostBalance());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeIllegitimatePlayers(FileWriter writer, List<Player> illegitimatePlayers) throws IOException {
        for (Player player : illegitimatePlayers) {
            Operation illegalOperation = player.getFirstIllegalOperation();
            if (illegalOperation != null) {
                String operationType = illegalOperation.getEvent() != null ? illegalOperation.getEvent() : "null";
                String matchId = illegalOperation.getMatchId() != null ? illegalOperation.getMatchId() : "null";
                int amount = illegalOperation.getAmount();
                String side = illegalOperation.getBetSide() != null ? illegalOperation.getBetSide() : "null";

                writer.write(String.format("%s %s %s %d %s%n",
                        player.getId(), operationType, matchId, amount, side));
            } else {
                writer.write(player.getId() + " has an illegitimate operation, but no details are available.\n");
            }
        }
        writer.write("\n");
    }



    private static void writeHostBalance(FileWriter writer, long hostBalance) throws IOException {
        writer.write( hostBalance + "\n");
    }
}
