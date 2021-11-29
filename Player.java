import java.util.ArrayList;

/* This is the superclass Player. It has 2 subclasses: FakePlayer and RealPlayer. Objects of this class represent an UNO player.
*/
public class Player
{
  private String name; //The player's name
  private int numberOfCards; //the number of cards the player has
  private ArrayList<Card> playersHand; //the Cards that a player has in their hand
  private static Game myGame; //the Game that the players are playing

  /* This is a constructor. It sets some instance variables.
  @param String nameInput- this is what you want to set the player's name to
  */
  public Player(String nameInput)
  {
    name = nameInput;
    playersHand = new ArrayList<Card>();
  }

  //Adds a card from the drawing pile to the calling player's hand.
  public void drawCard()
  {
    if (getName().equals("You"))
    {
      System.out.println("You're drawing a card");
    }
    else
    {
      System.out.println(getName() + " is drawing a card. ");
    }
    playersHand.add(myGame.getCardStack().pop());
    numberOfCards++;
  }

  /* This method gives a card to the player.
  @param Card cardToAddToHand- this is the card to add to the player's hand
  */
  public void giveCard(Card cardToAddToHand)
  {
    playersHand.add(cardToAddToHand);
    numberOfCards++;
  }

  /* This method only exists so that I don't get a runtime erorr when I do
  Player x = new RealPlayer()
  And then try to call the playCard method on x.
  @param Card doesNotMatter- this is only so that the parameters of this method and those of the subclass' versions match.
  doesNotMatter is not used within the method.
  @return Card- returns null, as this method should never run
  */
  public Card playCard(Card doesNotMatter)
  {
    System.out.println("This shouldn't run"); //it prints this out so that, if I ever see it, I know something has gone wrong
    return null;
  }

  /* Returns the Player's name
  @return String - returns the Player's name / the instance variable name
  */
  public String getName()
  {
    return name;
  }

  /* Returns the Player's hand
  @return ArrayList<Card> - this is a ArrayList made up of the Cards in a player's hand
  */
  public ArrayList<Card> getPlayersHand()
  {
    return playersHand;
  }

  /* Removes a card from the player's hand.
  @param Card removeCard - this is the card that's removed from the player's hand
  */
  public void removeFromPlayersHand(Card removeCard)
  {
    playersHand.remove(removeCard);
  }

  /* returns the number of cards a player has in their hand
  @param int- the number of cards a player has in their hand
  */
  public int getNumCards()
  {
    return numberOfCards;
  }

  //decreases the number of cards a player has in their hand by 1
  public void decreaseNum()
  {
    numberOfCards--;
  }

  /* Initializes the instance variable myGame
  @param Game input- what the instance variable myGame is set to
  */
  public static void setGame(Game input)
  {
    myGame = input;
  }

  /* returns the instance variable myGame
  @return Game - the instance variable myGame
  */
  public static Game getGame()
  {
    return myGame;
  }
}
