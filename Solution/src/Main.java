import java.util.List;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();

        List<String> playerDataLines = FileProcessor.readPlayerData("player_data.txt");
        game.processPlayerData(playerDataLines);

        List<String> matchDataLines = FileProcessor.readMatchData("match_data.txt");
        game.processMatchData(matchDataLines);

        ResultWriter.writeResults(game, "result.txt");
    }
}