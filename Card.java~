import java.util.ArrayList;

// This is the superclass Card. Card objects represent UNO cards. It has two subclasses: RegularCard and PlusFourCard

public class Card
{
  private String displayValue; //this is a String with the card's information formatted correctly
  int value; //this contains whatever number the card is or -1 for PlusFourCards
  private String color; //this contains whatever color the card is, represented by the #, @, !, and * symbols

  /* The method's constructor.
  @param int valueInput this is the int that the parameter value is set to
  @param String colorInput this is the String that the parameter color is set to
  */
  public Card(int valueInput, String colorInput)
  {
    value = valueInput;
    color = colorInput;
  }

  /* This method returns the card's displayValue
  @return String displayValue
  */

  public String getDisplayValue()
  {
    return displayValue;
  }

  /* Sets the card's displayValue.
  @param String input- what the displayValue is set to
  */
  public void setDisplayValue(String input)
  {
    displayValue = input;
  }

  /* Returns the card's value
  @return int - the card's value
  */
  public int getValue()
  {
    return value;
  }

  /* Returns the card's color.
  @return String- the card's color
  */

  public String getColor()
  {
    return color;
  }

  /* A method that returns an ArrayList<String> of the symbols that represent the different options for cards' colors.
  @return ArrayList<String> - the list of the symbols that represent the different options for cards' colors.
  */
  public ArrayList<String> listOfColorOptions()
  {
    ArrayList<String> toReturn = new ArrayList<String>();
    toReturn.add("@");
    toReturn.add("#");
    toReturn.add("!");
    toReturn.add("*");
    return toReturn;
  }
}
