import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.Scanner;

/* This class is what does the majority of the work setting stuff up. It runs the game of UNO, creates the player objects,
creates the cards, shuffles the cards, etc.
*/
public class Game
{
  private ArrayList<Player> players; //this is a list of all the Players
  private ArrayList<Card> deck; //this is a list of all the Cards
  private Stack<Card> discardPile; //this is a Stack of all the discarded Cards
  private Stack<Card> cardStack; //this is a Stack of all the cards that you can draw from

  /* This is the constructor for the class. It sets the instance variables, creates Player objects, creates all the Card objects, shuffles them, etc.
  @param String firstOpponentName - this is what you want one of your opponents to be named
  @param String secondOpponentName - this is what you want one of your opponents to be named
  @param String thirdOpponentName - this is what you want one of your opponents to be named
  */
  public Game(String firstOpponentName, String secondOpponentName, String thirdOpponentName)
  {
    players = new ArrayList<Player>();
    players.add(new RealPlayer("You"));
    players.add(new FakePlayer(firstOpponentName));
    players.add(new FakePlayer(secondOpponentName));
    players.add(new FakePlayer(thirdOpponentName));
    cardStack = new Stack<Card>();
    deck = new ArrayList<Card>();
    discardPile = new Stack<Card>();
    initializeDeck();
    shuffleCards(deck);
    for (Card x : deck)
    {
      cardStack.add(x);
    }
  }

  /* Creates all of the Card objects and adds them to the ArrayList deck. It creates 9 red cards at values 0 - 9, 9 blue cards valued 0 - 9,
  9 green cards valued 0 -9, and 9 yellow cards valued 0 - 9. Then, it also creates one blue + 4, one green + 4,
  one red + 4, and one blue + 4.
  */
  public void initializeDeck()
  {
    for (int i = 0; i < 4; i++)
    {
      for (int x = 0; x < 9; x++)
      {
        String color = "";
        if (i == 0)
        {
          color = "red";
        }
        else if (i == 1)
        {
          color = "blue";
        }
        else if (i == 2)
        {
          color = "green";
        }
        else
        {
          color = "yellow";
        }
        deck.add(new RegularCard(x, color));
      }
    }
    deck.add(new PlusFourCard("red"));
    deck.add(new PlusFourCard("blue"));
    deck.add(new PlusFourCard("yellow"));
    deck.add(new PlusFourCard("green"));
  }

  /* Shuffles the Cards in an ArrayList of Card objects using Collections' shuffle method
  @param ArrayList<Card> toShuffle - this is the ArrayList of Cards to shuffle.
  */
  public void shuffleCards(ArrayList<Card> toShuffle)
  {
    Collections.shuffle(toShuffle);
  }

  /* Returns the player that should go next
  @param Player current - this is the current player
  @return Player - this is the player that goes next
  */
  public Player getNextPlayer(Player current)
  {
    int indexOfCurrent = players.indexOf(current);
    if (indexOfCurrent + 1 == players.size())
    {
      return players.get(0);
    }
    return players.get(indexOfCurrent + 1);
  }

  /* The method that basically runs the UNO game.
  */
  public void playGame()
  {
    dealCards();
    boolean gameOver = false;
    Card current = cardStack.pop();
    discardPile.add(current);
    while (current.getDisplayValue().contains("+ 4"))
    {
      current = cardStack.pop();
      discardPile.add(current);
    }
    System.out.println("The first card is: " + current.getDisplayValue());
    Player winner = null;
    while (!gameOver)
    {
      for (int i = 0; i < 4; i++)
      {
        Card playersCard = players.get(i).playCard(discardPile.peek());
        if (playersCard != null)
        {
          discardPile.add(playersCard);
        }
        if (players.get(i).getNumCards() == 0)
        {
          gameOver = true;
          winner = players.get(i);
          break;
        }
      }
    }
    if (winner.getName().equals("You"))
    {
      System.out.println("You won the game!");
    }
    else
    {
      System.out.println(winner.getName() + " has won the game!");
    }
  }

  /* Returns the class' instance variable cardStack
  @return Stack<Card> - returns the instance varaible cardStack
  */
  public Stack<Card> getCardStack()
  {
    return cardStack;
  }

  //This method deals 7 cards off of the cardStack to each Player
  public void dealCards()
  {
    for (int i = 0; i < 4; i++)
    {
      for (int x = 0; x < 7; x++)
      {
        players.get(i).giveCard(cardStack.pop());
      }
    }
  }

  /* Returns the size of the class' cardStack
  @return int- the size of the class' cardStack
  */
  public int getCardStackSize()
  {
    return cardStack.size();
  }

  /* Returns the size of the class' discardPile
  @return int- the size of the class' discardPile
  */
  public int getDiscardStackSize()
  {
    return discardPile.size();
  }

  /* If the pile to draw from (cardStack) runs out of cards, this method takes everything but the topmost card on the discard
  pile and shuffles it before adding it to the pile to draw from. The method then returns true.
  However, if there are no cards in the discard pile, this method will not shuffle the discard pile and add it to the pile to draw from but instead return false.
  @return boolean - true if the method works to refill the draw pile, false if it doesn't
  */
  public boolean refillCardStack()
  {
    if (discardPile.size() == 1)
    {
      return false;
    }
    Card cardToKeep = discardPile.pop();
    ArrayList<Card> toShuffle = new ArrayList<Card>();
    for (int i = 0; i < discardPile.size(); i++)
    {
      toShuffle.add(discardPile.pop());
    }
    shuffleCards(toShuffle);
    for (int i = 0; i < toShuffle.size(); i++)
    {
      cardStack.add(toShuffle.get(i));
    }
    discardPile.add(cardToKeep);
    return true;
  }

  /* This is the class' main method. It welcomes you to the game, asks for user input for the names of opponents, and then
  calls various methods.
  */
  public static void main (String[] args)
  {
    System.out.println("Welcome to UNO!");
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter the name of an opponent: ");
    String opponentOneName = keyboard.nextLine();
    System.out.println("Enter the name of a second opponent: ");
    String opponentTwoName = keyboard.nextLine();
    System.out.println("Enter the name of a third opponent: ");
    String opponentThreeName = keyboard.nextLine();
    Game myGame = new Game(opponentOneName, opponentTwoName, opponentThreeName);
    Player.setGame(myGame);
    myGame.playGame();
  }
}
