import java.util.Scanner;
import java.util.Stack;

// This class is responsible for creating the object that represents the user / real player playing UNO
public class RealPlayer extends Player
{
  /* This is the class' constructor
  @param String name - the player's name
  */
  public RealPlayer(String name)
  {
    super(name);
  }

  /* A method that takes in a String and returns true if that String is an int and false if it isn't
  @return boolean - true if it's an int and false if it's not
  */
  public boolean isInteger(String stringToCheck)
  {
    try
    {
      Integer.parseInt(stringToCheck);
    }
    catch (NumberFormatException ex)
    {
      return false;
    }
    return true;
  }

  /* Shows the user the cards in their hand, asks them what card they'd like to play, takes in user input, and then
  checks if that's valid input. If it's not, they will be asked to enter something else.
  @param Card previous - the previous card that was just played
  @return Card - this is the Card that the player is playing
  */
  public Card playCard(Card previous)
  {
    String allCards = "";
    for (int i = 0; i < (getPlayersHand().size() - 1); i++)
    {
      allCards += (getPlayersHand().get(i).getDisplayValue() + ", ");
    }
    allCards += getPlayersHand().get(getPlayersHand().size() - 1).getDisplayValue();
    System.out.println("Here are your cards: " + allCards);
    Card myCard = new Card(0, null);
    boolean cardIsOk = false;
    boolean cardIsPlusFour = false;
    String displayValueOfCardToPlay = "";
    while (!cardIsOk)
    {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Which card would you like to play? If you can't play a card, type \"Draw\" and you will draw a card from the deck. ");
      String answer = keyboard.nextLine();
      if (answer.length() == 3 || answer.length() == 4)
      {
        if (checkIfSameColorAndValidInput(previous, answer))
        {
          cardIsOk = true;
          displayValueOfCardToPlay = answer;
        }
        else if (answer.equals("Draw"))
        {
          if (!draw())
          {
            System.out.println("There is nothing in the draw pile. Your turn will be skipped. ");
            return null;
          }
        }
        else if (checkIfSameNumberAndValidInput(previous, answer))
        {
          cardIsOk = true;
          displayValueOfCardToPlay = answer;
        }
        else
        {
          System.out.println("It seems like you've either incorrectly typed which card you'd like to choose or you're choosing a card that is not acceptable. Please try again. ");
        }
      }
      else if (answer.length() == 7 && answer.substring(0, 1).equals(previous.getDisplayValue().substring(0, 1)) && answer.substring(2, 5).equals("+ 4"))
      {
        cardIsOk = true;
        cardIsPlusFour = true;
        displayValueOfCardToPlay = answer;
      }
      else if (answer.length() == 7 && previous.getValue() == -1 && answer.substring(2, 5).equals("+ 4") && myCard.listOfColorOptions().contains(answer.substring(0, 1)) && myCard.listOfColorOptions().contains(answer.substring(6)) &&  answer.substring(0, 1).equals((answer.substring(6))))
      {
        cardIsOk = true;
        cardIsPlusFour = true;
        displayValueOfCardToPlay = answer;
      }
      else
      {
        System.out.println("It seems like you've either incorrectly typed which card you'd like to choose or you're choosing a card that is not acceptable. Please try again. ");
      }
    }
    Card toReturn = null;
    for (Card x: getPlayersHand())
    {
      if (x.getDisplayValue().equals(displayValueOfCardToPlay))
      {
        toReturn = x;
      }
    }
    decreaseNum();
    removeFromPlayersHand(toReturn);
    if (cardIsPlusFour)
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
    return toReturn;
  }

  /* This method checks if the String answer has the same color as the previous card's color and if the String answer is a valid input.
  This was created to make the playCard method smaller.
  @param Card previous - the card just played by another player
  @param String answer - the String that the user inputted in playCard
  @return boolean- true if answer is the same color as the previous card and a valid input, false if not
  */
  public boolean checkIfSameColorAndValidInput(Card previous, String answer)
  {
    return (answer.substring(0, 1).equals(previous.getDisplayValue().substring(0, 1)) && (answer.substring(2).equals(previous.getDisplayValue().substring(2)) || previous.getDisplayValue().contains("+ 4")) && isInteger(answer.substring(1, 2)) == true && Integer.parseInt(answer.substring(1, 2)) >= 0 && Integer.parseInt(answer.substring(1, 2)) <= 9);
  }

  /* This method checks if the String answer has the same value as the previous card's value and if the String answer is a valid input.
  This was created to make the playCard method smaller.
  @param Card previous - the card just played by another player
  @param String answer - the String that the user inputted in playCard
  @return boolean- true if answer is the same number as the previous card and a valid input, false if not
  */
  public boolean checkIfSameNumberAndValidInput(Card previous, String answer)
  {
    Card myCard = new Card(0, null);
    return (isInteger(answer.substring(1, 2)) == true && Integer.parseInt(answer.substring(1, 2)) >= 0 && Integer.parseInt(answer.substring(1, 2)) <= 9 && Integer.parseInt(answer.substring(1, 2)) == previous.getValue() && myCard.listOfColorOptions().contains(answer.substring(0, 1)) && myCard.listOfColorOptions().contains(answer.substring(2)));
  }

  /* If it's possible to draw a card, this method draws a card from the draw pile and adds it to the player's hand. Then, it returns true. However, if
  the stack to draw from is empty, it will call the refillCardStack method. If that's false (meaning there's nothing in the
  discard pile to shuffle into the draw pile), then it will return false.
  @return boolean - true if you can draw a card, false if you can't
  */
  public boolean draw()
  {
    String allCards = "";
    if (getGame().getCardStackSize() == 0)
    {
      if(getGame().refillCardStack())
      {
        giveCard(getGame().getCardStack().pop());
        for (int i = 0; i < (getPlayersHand().size() - 1); i++)
        {
          allCards += (getPlayersHand().get(i).getDisplayValue() + ", ");
        }
        allCards += getPlayersHand().get(getPlayersHand().size() - 1).getDisplayValue();
        System.out.println("Here are your cards: " + allCards);
        return true;
      }
      else
      {
        return false;
      }
    }
    else
    {
      giveCard(getGame().getCardStack().pop());
      for (int i = 0; i < (getPlayersHand().size() - 1); i++)
      {
        allCards += (getPlayersHand().get(i).getDisplayValue() + ", ");
      }
      allCards += getPlayersHand().get(getPlayersHand().size() - 1).getDisplayValue();
      System.out.println("Here are your cards: " + allCards);
      return true;
    }
  }
}
