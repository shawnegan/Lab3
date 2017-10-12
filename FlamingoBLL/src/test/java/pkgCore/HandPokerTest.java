package pkgCore;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Test;

import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPokerTest {

	// TODO I don't think we need this anymore. - Hung
//	public HandScorePoker HandHelper(ArrayList<Card> cards) {
//
//		HandScorePoker result = null;
//
//		try {
//			
//			//	c = structure of class 'Hand'
//			Class<?> c = Class.forName("pkgCore.HandPoker");
//
//			//	the arguements for 'AddCard' is a instance of Card
//			Class[] cArgsAddCard = new Class[1];
//			cArgsAddCard[0] = pkgCore.Card.class;
//
//			//	Create an instance of Hand
//			Object inst = c.newInstance();
//
//			//	Find the method 'AddCard' in Hand
//			Method mAddCard = c.getDeclaredMethod("AddCard", cArgsAddCard);
//			
//			//	Make the private method accessible 
//			mAddCard.setAccessible(true);
//			
//			//	Invoke the AddCard method for each card passed into this method
//			for (Card card: cards)
//			{
//				mAddCard.invoke(inst, card);
//			}
//			
//			//	Find the method 'ScoreHand' in 'Hand'
//			Method mScore = c.getDeclaredMethod("ScoreHand", null);
//			
//			//	Invoke 'ScoreHand'.  It returns an array of integers
//			// result = (HandScorePoker) mScore.invoke(inst, null);
//
//
//		} catch (ClassNotFoundException x) {
//			x.printStackTrace();
//		} catch (IllegalAccessException x) {
//			x.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return result;
//	}
	
	@Test
	public void TwoPairTest1() {
		HandPoker hand = new HandPoker();
		
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		
		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.TwoPair);
	}

}
