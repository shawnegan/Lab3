package pkgCore;

import static org.junit.Assert.*;
import org.junit.Test;

import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPokerTest {
	
	@Test
	public void RoyalFlushTest1() {
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.JACK));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.QUEEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.KING));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.ACE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.RoyalFlush);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers(),null);
	}
	
	@Test
	public void StraightFlushTest1() {
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.FOUR));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.SIX));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.StraightFlush);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.SIX);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers(),null);
	}
	
	@Test
	public void FlushTest1() {
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.EIGHT));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.KING));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.JACK));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.Flush);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.KING);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers(),null);
	}
	
	@Test
	public void StraightTest1() {
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.SIX));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.Straight);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.SIX);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers(),null);
	}
	
	@Test
	public void HighCardTest1() {
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.SPADES,eRank.ACE));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.EIGHT));
		hand.AddCard(new Card(eSuit.DIAMONDS,eRank.NINE));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.HighCard);
	}
	
	////////// Hands below have different combinations
	////////// For example, a pair can be lower than all kickers, lower than 1 kicker, etc.
	
	@Test
	public void FourOfAKindTest1() { //FourOfAKind lower than kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.SPADES,eRank.TEN));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		hand.AddCard(new Card(eSuit.DIAMONDS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.ACE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.FourOfAKind);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.TEN);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.ACE);
	}
	
	@Test
	public void FourOfAKindTest2() { //FourOfAKind higher than kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.SPADES,eRank.TEN));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		hand.AddCard(new Card(eSuit.DIAMONDS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.THREE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.FourOfAKind);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.TEN);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.THREE);
	}
	
	@Test
	public void FullHouseTest1() { //ThreeOfAKind lower than Pair
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.SPADES,eRank.TWO));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.FullHouse);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.TWO);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard().geteRank(),eRank.THREE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers(),null);
	}

	@Test
	public void FullHouseTest2() { //ThreeOfAKind higher than Pair
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.FIVE));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.FullHouse);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.FIVE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard().geteRank(),eRank.THREE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers(),null);
	}
	
	@Test
	public void ThreeOfAKindTest1() { //ThreeOfAKind lower than kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.SPADES,eRank.TEN));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		hand.AddCard(new Card(eSuit.DIAMONDS,eRank.QUEEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.ACE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.ThreeOfAKind);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.TEN);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.ACE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(1).geteRank(),eRank.QUEEN);
	}
	
	@Test
	public void ThreeOfAKindTest2() { //ThreeOfAKind between kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.SPADES,eRank.TEN));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		hand.AddCard(new Card(eSuit.DIAMONDS,eRank.EIGHT));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.ACE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.ThreeOfAKind);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.TEN);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.ACE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(1).geteRank(),eRank.EIGHT);
	}
	
	@Test
	public void ThreeOfAKindTest3() { //ThreeOfAKind higher than kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.SPADES,eRank.TEN));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.TEN));
		hand.AddCard(new Card(eSuit.DIAMONDS,eRank.EIGHT));
		hand.AddCard(new Card(eSuit.HEARTS,eRank.SEVEN));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.ThreeOfAKind);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.TEN);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.EIGHT);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(1).geteRank(),eRank.SEVEN);
	}

	@Test
	public void TwoPairTest1() { // Kicker lower than TwoPair
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FOUR));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.TwoPair);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.FOUR);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard().geteRank(),eRank.THREE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.TWO);
	}
	
	@Test
	public void TwoPairTest2() { // Kicker between TwoPair
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FOUR));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.TwoPair);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.FOUR);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard().geteRank(),eRank.TWO);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.THREE);
	}
	
	@Test
	public void TwoPairTest3() { // Kicker higher TwoPair
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.TwoPair);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.THREE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard().geteRank(),eRank.TWO);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.FOUR);
	}

	@Test
	public void OnePairTest1() { // Pair is higher than no kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hand.AddCard(new Card(eSuit.SPADES,eRank.TWO));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FOUR));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.Pair);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.TWO);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.JACK);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(1).geteRank(),eRank.FOUR);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(2).geteRank(),eRank.THREE);
	}

	@Test
	public void OnePairTest2() { // Pair is higher than 1 kicker
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FOUR));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.Pair);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.THREE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.JACK);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(1).geteRank(),eRank.FOUR);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(2).geteRank(),eRank.TWO);
	}
	
	@Test
	public void OnePairTest3() { // Pair is higher than 2 kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.FOUR));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.Pair);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.FOUR);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.JACK);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(1).geteRank(),eRank.THREE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(2).geteRank(),eRank.TWO);
	}
	
	@Test
	public void OnePairTest4() { // Pair is higher than all kickers
		HandPoker hand = new HandPoker();

		hand.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hand.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hand.AddCard(new Card(eSuit.SPADES,eRank.THREE));
		hand.AddCard(new Card(eSuit.SPADES,eRank.JACK));

		assertEquals(((HandScorePoker) hand.ScoreHand()).geteHandStrength(),eHandStrength.Pair);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getHiCard().geteRank(),eRank.JACK);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getLoCard(),null);
		
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(0).geteRank(),eRank.FOUR);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(1).geteRank(),eRank.THREE);
		assertEquals(((HandScorePoker) hand.ScoreHand()).getKickers().get(2).geteRank(),eRank.TWO);
	}
}