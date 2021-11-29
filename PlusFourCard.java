/* This class creates objects that represent +4 cards that, when played on someone, cause them to draw 4 cards from the
draw pile. This class is also a subclass that extends the parent class Card
*/
public class PlusFourCard extends Card
{
  /* This is the constructor for the class.
  @param String colorInput - the color of the card
  */
  public PlusFourCard(String colorInput)
  {
    super(-1, colorInput);
    if (getColor().equals("red"))
    {
      setDisplayValue("* + 4 *");
    }
    else if (getColor().equals("green"))
    {
      setDisplayValue("@ + 4 @");
    }
    else if (getColor().equals("yellow"))
    {
      setDisplayValue("! + 4 !");
    }
    else
    {
      setDisplayValue("# + 4 #");
    }
  }
}
