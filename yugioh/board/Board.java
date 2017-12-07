package eg.edu.guc.yugioh.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.Raigeki;

public class Board {
	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
		
	}

	public Board() {
		Card.setBoard(this);
	}

	public void whoStarts(Player p1, Player p2) {
		int i = (new Random()).nextInt(2) + 1;
		if (i == 1) {
			activePlayer = p1;
			opponentPlayer = p2;
		} else {
			activePlayer = p2;
			opponentPlayer = p1;
		}

	}

	public void startGame(Player p1, Player p2) {
		whoStarts(p1, p2);
		
		p1.getField().addNCardsToHand(5);
		p2.getField().addNCardsToHand(5);
		
		activePlayer.getField().addCardToHand();
		activePlayer.getField().setPhase(Phase.MAIN1);


	}

	public void nextPlayer() throws IOException, CloneNotSupportedException {
		
		Player temp = activePlayer;
		activePlayer = opponentPlayer;
		opponentPlayer = temp;
		activePlayer.getField().addCardToHand();
		activePlayer.getField().setPhase(Phase.MAIN1);

	}


}
