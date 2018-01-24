package com.example.lc.accounts.currency;

import java.util.ArrayList;

import com.example.lc.accounts.support.ATMConstants;

public class Currency {
	
	public enum Notes { 
	  SINGLE(1), FIVE(5), TEN(10), TWENTY(20), FIFTY(50); 
	  private int value; 
	  private Notes(int value) { 
		  this.value = value; 
	  }
	  
	  @Override 
	  public String toString() {  
		  String returnValue = "";
		  switch (this) { 
		    case SINGLE: returnValue = "(Manx) One Pound Note: " + value; break; 
		    // They still have pound notes in the Isle of Man (true story!)
		    case FIVE: returnValue = "Five Pound Note: " + value; break; 
		  	case TEN: returnValue = "Ten Pound Note: " + value; break; 
		  	case TWENTY: returnValue = "Twenty Pound Note: " + value; break; 
		  	case FIFTY: returnValue = "Fifty Pound Note: " + value; break; 
		  } 
		  return returnValue; 
	  }
	  
	  public int value() {
		  return this.value;
	  }

	}; 
	
	public static ArrayList<Notes> getBalance(int balanceRequested) {
		int remainder = balanceRequested;
		ArrayList<Notes> listOfNotes = new ArrayList<Notes>();
		if(remainder == 0) {
			return listOfNotes;
		}
		// always have at least two five pound notes.
		if(remainder > ATMConstants.NUMBER_FIVES * Notes.FIVE.value()) {
			remainder = remainder - (ATMConstants.NUMBER_FIVES * Notes.FIVE.value());
		}
		
		int numberFifty = remainder / Notes.FIFTY.value();
		listOfNotes = Currency.addNumberNotesWithDenomination(listOfNotes, numberFifty, Notes.FIFTY);
		remainder = remainder - (numberFifty * Notes.FIFTY.value());
		int numberTwenty = remainder / Notes.TWENTY.value();
		listOfNotes = Currency.addNumberNotesWithDenomination(listOfNotes, numberTwenty, Notes.TWENTY);	
		remainder = remainder - (numberTwenty * Notes.TWENTY.value());
		int numberTen = remainder / Notes.TEN.value();
		listOfNotes = Currency.addNumberNotesWithDenomination(listOfNotes, numberTen, Notes.TEN);
		remainder = remainder - (numberTen * Notes.TEN.value());
		
		int numberFivers = remainder / Notes.FIVE.value();
		remainder = remainder - (numberFivers * Notes.FIVE.value());
		// we are always adding the requisite number of fivers later in the process
		// (but we reduce the balance before we do this, since we adjusted it above).
		numberFivers += ATMConstants.NUMBER_FIVES;
		listOfNotes = Currency.addNumberNotesWithDenomination(listOfNotes, numberFivers, Notes.FIVE);
		// Although this isn't supported, it doesn't mean that we wouldn't have these later.
		int remainingManxPoundNotes = remainder / Notes.SINGLE.value();
		listOfNotes = Currency.addNumberNotesWithDenomination(listOfNotes, remainingManxPoundNotes, Notes.SINGLE);
		return listOfNotes;
	}

	private static ArrayList<Notes> addNumberNotesWithDenomination(ArrayList<Notes> theNotes, int numberNotes, Notes poundValue) {
		for(int i = 0; i < numberNotes; i++) {
			theNotes.add(poundValue);
		}
		return theNotes;
	}

	public static boolean isValid(Notes theNote) {
		Boolean valid = false;
		switch (theNote) { 
		    case SINGLE: valid = false; break; 
		  	case TEN: 
		  	case FIVE: 
		  	case TWENTY: 
		  	case FIFTY: valid=true; break; 
	    }
		return valid;
	}

	
}
