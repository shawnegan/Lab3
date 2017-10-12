package pkgCore;

import java.util.ArrayList;
import java.util.Collections;

import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPoker extends Hand {
	
	private ArrayList<CardRankCount> CRC = null;
	HandScorePoker HSP = (HandScorePoker)this.getHS();
	
	protected ArrayList<CardRankCount> getCRC() {
		return CRC;
	}
	
	public HandPoker() {
		this.setHS(new HandScorePoker());
	}

	@Override
	public HandScore ScoreHand() {
		// TODO : Implement this method... call each of the 'is' methods (isRoyalFlush,
		// etc) until
		// one of the hands is true, then score the hand

		Collections.sort(super.getCards());
		Frequency();
		
		if (isRoyalFlush()) {
			return HSP;
		} else if (isStraightFlush()) {
			return HSP;
		} else if (isFourOfAKind()) {
			return HSP;
		} else if (isFullHouse()) {
			return HSP;
		} else if(isFlush()) {
			return HSP;
		} else if(isStraight()) {
			return HSP;
		} else if(isThreeOfAKind()) {
			return HSP;
		} else if(isTwoPair()) {
			return HSP;
		} else if(isPair()) {
			return HSP;
		} else {
			return HSP;
		}

	}
	private void Frequency() {

		CRC = new ArrayList<CardRankCount>();

		int iCnt = 0;
		int iPos = 0;

		for (eRank eRank : eRank.values()) {
			iCnt = (CountRank(eRank));
			if (iCnt > 0) {
				iPos = FindCardRank(eRank);
				CRC.add(new CardRankCount(eRank, iCnt, iPos));
			}
		}

		Collections.sort(CRC);

		for (CardRankCount crcount : CRC) {
			System.out.print(crcount.getiCnt());
			System.out.print(" ");
			System.out.print(crcount.geteRank());
			System.out.print(" ");
			System.out.println(crcount.getiCardPosition());
		}

	}

	private int CountRank(eRank eRank) {
		int iCnt = 0;
		for (Card c : super.getCards()) {
			if (c.geteRank() == eRank) {
				iCnt++;
			}
		}
		return iCnt;
	}

	private int FindCardRank(eRank eRank) {
		int iPos = 0;

		for (iPos = 0; iPos < super.getCards().size(); iPos++) {
			if (super.getCards().get(iPos).geteRank() == eRank) {
				break;
			}
		}
		return iPos;
	}
	
	public boolean isRoyalFlush() {
		boolean bIsRoyalFlush = false;

		if (this.isFlush() && 
				super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank().getiRankNbr() == 14
				&& super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr() == 13) {
			bIsRoyalFlush = true;
			
			HSP.seteHandStrength(eHandStrength.RoyalFlush);
			HSP.setHiCard(null);
			HSP.setLoCard(null);
			HSP.setKickers(null);
			
			this.setHS(HSP);
		}
		
		return bIsRoyalFlush;
	}

	public boolean isStraightFlush() {
		boolean bisStraightFlush = false;

		if (this.isFlush() && this.isStraight()) {
			bisStraightFlush = true;
			
			HSP.seteHandStrength(eHandStrength.StraightFlush);
			HSP.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HSP.setLoCard(null);
			HSP.setKickers(null);
			
			this.setHS(HSP);
		}
		
		return bisStraightFlush;
	}

	public boolean isFourOfAKind() {
		boolean bisFourOfAKind = false;
		HandScorePoker HS = (HandScorePoker) super.getHS();

		if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank() == super.getCards()
				.get(eCardNo.FOURTH.getiCardNo()).geteRank()) {

			HS.seteHandStrength(eHandStrength.FourOfAKind);
			HS.setHiCard(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIFTH.getiCardNo()));
			HS.setKickers(kickers);
			bisFourOfAKind = true;

		} else if (super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank() == super.getCards()
				.get(eCardNo.FIFTH.getiCardNo()).geteRank()) {
			HS.seteHandStrength(eHandStrength.FourOfAKind);
			HS.setHiCard(super.getCards().get(eCardNo.SECOND.getiCardNo()));
			HS.setLoCard(null);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(super.getCards().get(eCardNo.FIRST.getiCardNo()));
			HS.setKickers(kickers);
			bisFourOfAKind = true;
		}
		this.setHS(HSP);
		return bisFourOfAKind;
	}

	public boolean isFullHouse() {
		boolean bisFullHouse = false;
		if (this.CRC.size() == 2) {
			if ((CRC.get(0).getiCnt() == 3) && (CRC.get(1).getiCnt() == 2)) {
				bisFullHouse = true;
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.FullHouse);
				HSP.setHiCard(this.getCards().get(CRC.get(0).getiCardPosition()));
				HSP.setLoCard(this.getCards().get(CRC.get(1).getiCardPosition()));
				ArrayList<Card> kickers = new ArrayList<Card>();
				HSP.setKickers(kickers);
				this.setHS(HSP);
			}
		}
		HSP.seteHandStrength(eHandStrength.FullHouse);
		this.setHS(HSP);
		return bisFullHouse;
	}

	public boolean isFlush() {
		boolean bisFlush = false;

		int iCardCnt = super.getCards().size();
		int iSuitCnt = 0;

		for (eSuit eSuit : eSuit.values()) {
			for (Card c : super.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt)
			bisFlush = true;
		else
			bisFlush = false;
		HSP.seteHandStrength(eHandStrength.Flush);
		this.setHS(HSP);
		return bisFlush;
	}

	public boolean isStraight() {
		boolean bisStraight = false;

		if (super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank().getiRankNbr() == 14 && 
				super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr() == 5) {
			int second = super.getCards().get(eCardNo.SECOND.getiCardNo()).geteRank().getiRankNbr();
			ArrayList<Card> h = super.getCards();
			Card three = h.get(eCardNo.THIRD.getiCardNo());
			Card four = h.get(eCardNo.FOURTH.getiCardNo());
			Card five = h.get(eCardNo.FIFTH.getiCardNo());
			int third = three.geteRank().getiRankNbr();
			int fourth = four.geteRank().getiRankNbr();
			int fifth = five.geteRank().getiRankNbr();
			if (third == second-1 && fourth == third-1 && fifth == fourth-1) {
				bisStraight = true;
			}
		} else {
			int count = 0;  // count of number of cards in straight order
			int a = super.getCards().get(eCardNo.FIRST.getiCardNo()).geteRank().getiRankNbr();
			for (Card c : super.getCards()) {
				if (c.geteRank().getiRankNbr() == a-1) {
					count++; //executes when c is one more than previous card
					a--; //storing the "rank" of card c in integer a
				}
			}
			if (count==5) {
				bisStraight = true;
			}
		}
		HSP.seteHandStrength(eHandStrength.Straight);
		this.setHS(HSP);
		return bisStraight;
	}

	private ArrayList<Card> FindTheKickers(ArrayList<CardRankCount> CRC)
	{
		ArrayList<Card> kickers = new ArrayList<Card>();
		
		for (CardRankCount crcCheck: CRC)
		{
			if (crcCheck.getiCnt() == 1)
			{
				kickers.add(this.getCards().get(crcCheck.getiCardPosition()));
			}
		}
		
		return kickers;
	}
	
	public boolean isThreeOfAKind() {
		boolean bisThreeOfAKind = false;
		if (this.getCRC().size() == 3) {
			if (this.getCRC().get(0).getiCnt() == 3) {

				HSP.seteHandStrength(eHandStrength.ThreeOfAKind);
				
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);

				HSP.setKickers(FindTheKickers(this.getCRC()));
				
				this.setHS(HSP);
				
				bisThreeOfAKind = true;
			}
		}
		return bisThreeOfAKind;
	}

	public boolean isTwoPair() {
		boolean bisTwoPair = false;
		if (this.getCRC().size() == 3) {
			if (this.getCRC().get(0).getiCnt() == 2 && this.getCRC().get(1).getiCnt() == 2) {
				
				HSP.seteHandStrength(eHandStrength.TwoPair);
				
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(this.getCards().get(this.getCRC().get(1).getiCardPosition()));

				HSP.setKickers(FindTheKickers(this.getCRC()));
				
				this.setHS(HSP);
				
				bisTwoPair = true;
			}
		}
		return bisTwoPair;
	}

	public boolean isPair() {
		boolean bisPair = false;
		if (this.getCRC().size() == 4) {
			if (this.getCRC().get(0).getiCnt() == 2) {
				
				HSP.seteHandStrength(eHandStrength.Pair);
				
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);

				HSP.setKickers(FindTheKickers(this.getCRC()));
				
				this.setHS(HSP);
				
				bisPair = true;
			}
		}
		return bisPair;
	}

	public boolean isHighCard() {
		boolean bisHighCard = false;
		if (this.getCRC().size() == 5) {
			HSP.seteHandStrength(eHandStrength.HighCard);
			
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);

			HSP.setKickers(FindTheKickers(this.getCRC()));
			
			this.setHS(HSP);
			
			bisHighCard = true;
		}
		return bisHighCard;
	}

}
