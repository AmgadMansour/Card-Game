package eg.edu.guc.yugioh.board.player;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Deck {
	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private ArrayList<Card> deck = new ArrayList<>();
	private static String monstersPath = "Database-Monsters.csv";
	private static String spellsPath = "Database-Spells.csv";

	public static String getMonstersPath() {
		return monstersPath;
	}

	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}

	public static String getSpellsPath() {
		return spellsPath;
	}

	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}

	public static ArrayList<Card> getSpells() {
		return spells;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public Deck() throws IOException, CloneNotSupportedException,
			MissingFieldException, EmptyFieldException,
			UnknownCardTypeException, UnknownSpellCardException {
		Scanner sc = new Scanner(System.in);
		monsters = new ArrayList<>();
		spells = new ArrayList<>();
		String pathM = monstersPath;
		String pathS = spellsPath;
		for (int i = 0; i <=3; i++) {
			try {

				monsters = loadCardsFromFile(pathM);
				break;
			} catch (FileNotFoundException e) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				}
				System.out.println("Please enter a correct path:");
				System.out.println("The file was not found");

				pathM = sc.nextLine();
			}
			catch ( MissingFieldException e ) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathM = sc.nextLine();
				}
			}
			catch (EmptyFieldException e) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathM = sc.nextLine();
				}
				
			}
			catch(UnknownCardTypeException e) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathM = sc.nextLine();
				}
				
			}
			catch (UnknownSpellCardException e ) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathM = sc.nextLine();
				}
			}
		}
		for (int i = 0; i <= 3; i++) {
			try {

				spells = loadCardsFromFile(pathS);
				break;

			} catch (FileNotFoundException e) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathS = sc.nextLine();
				}
			}
			catch ( MissingFieldException e ) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathS = sc.nextLine();
				}
			}
			catch (EmptyFieldException e) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathS = sc.nextLine();
				}
				
			}
			catch(UnknownCardTypeException e) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathS = sc.nextLine();
				}
				
			}
			catch (UnknownSpellCardException e ) {
				if (i == 3) {
					e.printStackTrace();
					throw e;
				} else {
					System.out.println("The file was not found");
					System.out.println("Please enter a correct path");
					pathS = sc.nextLine();
				}
			}
		}
		buildDeck(monsters, spells);
		shuffleDeck();
	}


	public ArrayList<Card> loadCardsFromFile(String path) throws IOException,
			MissingFieldException, EmptyFieldException,
			UnknownCardTypeException, UnknownSpellCardException {

		String currentLine = "";
		boolean flag = false;

		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		String s;
		int line = 1;
		String[] result;
		ArrayList<Card> output = new ArrayList<>();
		while ((currentLine = br.readLine()) != null) {
			s = currentLine;
			result = s.split(",");
			for(int i=0 ;i< result.length ;i++) {
			if (result[i].equals(null) || result[i].equals(" ")
					)
				throw new EmptyFieldException(path, line,i+1);
		}
			
			if (! (result[0].equals("Spell") || result[0].equals("Monster") )) {
				throw new UnknownCardTypeException(path, line, result[0]);
			}
			
			
			if (result[0].equals("Spell")) {
				if (!(result.length ==3)){
					throw new MissingFieldException(path, line);
		}switch (result[1]) {

		case "Card Destruction":
			output.add(new CardDestruction(result[1], result[2]));
			break;
		case "Change Of Heart":
			output.add(new ChangeOfHeart(result[1], result[2]));
			break;
		case "Dark Hole":
			output.add(new DarkHole(result[1], result[2]));
			break;
		case "Graceful Dice":
			output.add(new GracefulDice(result[1], result[2]));
			break;
		case "Harpie's Feather Duster":
			output.add(new HarpieFeatherDuster(result[1], result[2]));
			break;
		case "Heavy Storm":
			output.add(new HeavyStorm(result[1], result[2]));
			break;
		case "Mage Power":
			output.add(new MagePower(result[1], result[2]));
			break;
		case "Monster Reborn":
			output.add(new MonsterReborn(result[1], result[2]));
			break;
		case "Pot of Greed":
			output.add(new PotOfGreed(result[1], result[2]));
			break;
		case "Raigeki":
			output.add(new Raigeki(result[1], result[2]));
			break;
   default : throw new UnknownSpellCardException(path, line, result[1]) ;
		}
				/*if (result[1].equals("Card Destruction")) {
					output.add(new CardDestruction(result[1], result[2]));
					flag = true;
				}
				if (result[1].equals("Raigeki")) {
					output.add(new Raigeki(result[1], result[2]));
					flag = true;

				}
				if (result[1].equals("Change Of Heart")) {
					output.add(new ChangeOfHeart(result[1], result[2]));
					flag = true ;
				}
				if (result[1].equals("Dark Hole")) {
					output.add(new DarkHole(result[1], result[2]));
					flag = true ;
				}
				if (result[1].equals("Graceful Dice")) {
					output.add(new GracefulDice(result[1], result[2]));
					flag = true ;
				}
				if (result[1].equals("Harpie's Feather Duster")){
					output.add(new HarpieFeatherDuster(result[1], result[2]));
					flag = true ;
				}
				if (result[1].equals("Heavy Storm")) {
					output.add(new HeavyStorm(result[1], result[2]));
					flag = true ;
				}
				if (result[1].equals("Mage Power")) {
					output.add(new MagePower(result[1], result[2]));
					flag = true ;
				}
				if (result[1].equals("Pot of Greed")){
					output.add(new PotOfGreed(result[1], result[2]));
					flag = true ;
				}
				if (result[1].equals("Monster Reborn")){
					output.add(new MonsterReborn(result[1], result[2]));
					flag = true ;
				}
				if(flag==false ){
					throw new UnknownSpellCardException(path, line, result[1]) ;
				}
				*/
		}

			
	
			if (result[0].equals("Monster")) {
				if (! ( result.length==6))
					throw new MissingFieldException(path, line);
				try{
				output.add(new MonsterCard(result[1], result[2], Integer
						.parseInt(result[5]), Integer.parseInt(result[3]),
						Integer.parseInt(result[4])));
				System.out.println(result[1]);
				}catch(NumberFormatException e){
					throw new FileNotFoundException();
				}
			}
			line++;
		}

		return output;
	}

	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells)
			throws CloneNotSupportedException {
		int i = 0;
		while (i < 20) {
			if (i < 15) {

				MonsterCard c = (MonsterCard) monsters.get((new Random())
						.nextInt(monsters.size()));

				deck.add(new MonsterCard(c.getName(), c.getDescription(), c
						.getLevel(), c.getAttackPoints(), c.getDefensePoints()));
				// deck.add(c) ;
			} else {

				SpellCard s = (SpellCard) spells
						.get((new Random()).nextInt(spells.size())).clone();
				// deck.add(new SpellCard(s.getName(), s.getDescription()));
				deck.add(s);
			}
			i++;
		}

	}

	private void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public ArrayList<Card> drawNCards(int n) {
		ArrayList<Card> x = new ArrayList<>();

		int i = 0;
		while (i < n) {
			if (Card.getBoard().getActivePlayer().getField().getDeck()
					.getDeck().size() == 0) {
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
				break;
			}
			x.add(deck.remove(0));
			i++;
		}

		return x;
	}

	public Card drawOneCard() {
		// System.out.println(Card.getBoard().getActivePlayer()+"**");
		if (deck.size() != 0) {
			return deck.remove(0);

		} else
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		return null;

	}

	public static void main(String[] args) throws IOException,
			CloneNotSupportedException, MissingFieldException,
			EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException {
		Deck a = new Deck();

	}
}
