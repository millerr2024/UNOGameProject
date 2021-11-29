/* This class creates objects that represent normal cards with both a color and a value. This class is also a subclass that extends the parent class Card
*/
public class RegularCard extends Card
{
  /* The class' constructor.
  @param int valueInput - this is what the card's value will be set to
  @param String colorInput - this is what the card's color will be set to
  */
  public RegularCard(int valueInput, String colorInput)
  {
    super(valueInput, colorInput);
    if (getColor().equals("red"))
    {
      setDisplayValue("*" + value + "*");
    }
    else if (getColor().equals("green"))
    {
      setDisplayValue("@" + value + "@");
    }
    else if (getColor().equals("yellow"))
    {
      setDisplayValue("!" + value + "!");
    }
    else
    {
      setDisplayValue("#" + value + "#");
    }
  }
}
