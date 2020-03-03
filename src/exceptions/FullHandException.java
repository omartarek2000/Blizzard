package exceptions;

import model.cards.Card;

public class FullHandException extends HearthstoneException {
	Card burned;
	
	public FullHandException(Card b)
	{
		super();
		
	}

	public FullHandException(String s,Card b)
	{
		super(s);
		
	}

}
