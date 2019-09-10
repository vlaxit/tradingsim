package setup.tf.app2;

import java.util.List;

public interface GameApi {

	void createPlayer(String playerName);
	
	void startGame(String player1, String player2);
	
	void updatePlayersScore(String player, String oponent, int score);
	
	List<String> getPlayerNames();
	
	int getScoreByPlayer(String playerName);
	
	List<String> getPlayerOponents(String player);
	
	List<Integer> getPlayerScoresByGame(String player);
	
	
	
}
