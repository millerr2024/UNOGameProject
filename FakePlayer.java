import java.util.ArrayList;
import java.util.Stack;

/* A subclass called FakePlayer that extends the Player class. Basically this class is an artificial player and the methods that
go along with them playing UNO.
*/

public class FakePlayer extends Player
{

  /* The class' constructor.
  @param String name - this sets Player's name instance variable
  */
  public FakePlayer(String name)
  {
    super(name);
  }

  /* This method overrides the Player class' playCard method. It basically looks at the previous card that was played and
  either looks for a card with a matching color or matching value (same number or also a +4) within the player's hand or draws
  a card from the deck.
  @param Card previous - this is the previous card that was played
  @return Card - returns the Card to be played
  */

  public Card playCard(Card previous)
  {
    Card toPlay = null;
    while (toPlay == null)
    {
      ArrayList<Card> hand = getPlayersHand();
      for (Card x : hand)
      {
        if (x.getValue() == previous.getValue() || x.getColor().equals(previous.getColor()))
        {
          toPlay = x;
        }
      }
      if (toPlay == null)
      {
        if (getGame().getCardStackSize() == 0)
        {
          if (getGame().refillCardStack())
          {
            System.out.println(getName() + " is drawing a card");
            giveCard(getGame().getCardStack().pop());
          }
          else
          {
            System.out.println(getName() + " has no playable cards and the draw deck is empty. Their turn will be skipped. ");
            break;
          }
        }
        else
        {
          System.out.println(getName() + " is drawing a card");
          giveCard(getGame().getCardStack().pop());
        }
      }
    }
    if (toPlay != null)
    {
      decreaseNum();
      System.out.println(getName() + " is playing " + toPlay.getDisplayValue() + ". " + getName() + " has " + getNumCards() + " cards left.");
      removeFromPlayersHand(toPlay);
      if (toPlay.getDisplayValue().contains("+ 4"))
      {
        for (int i = 0; i < 4; i++)
        {
          if (getGame().getCardStackSize() == 0)
          {
            if (getGame().refillCardStack())
            {
              getGame().getNextPlayer(this).drawCard();
            }
            else
            {
              System.out.println("There are no cards in the deck for " + getGame().getNextPlayer(this).getName() + " to draw");
            }
          }
          else
          {
            getGame().getNextPlayer(this).drawCard();
          }
        }
      }
    }
    return toPlay;
  }
}
