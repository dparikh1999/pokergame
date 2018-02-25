/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */
import java.util.ArrayList;
public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count; 
   
   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }
   
   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {
      
	  for(int i=0 ; i<hand.length; i++){ 
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }

	  
   }
   
   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
   public void removeCard(Card c) {

	for(int i=0 ; i<hand.length; i++){ 
		if (hand[i].equals(c)){
			hand[i] = null;
			count = count-1;
		}
	}

   }
   
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
		if (position < 0 || position >= hand.length)
			throw new IllegalArgumentException("Position does not exist in hand: " + position);
		hand[position] = null;
		count = count - 1;
	}	

   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }
   
   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            
			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   public void printHand(){
	   
	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }
   /******************************** Implement your methods here ****************************************/
 // MILESTONE 1
   /**returns how many pairs are in the hand. 4 of the same count as 2 pairs
  * 3 of the same count as one pair **/
   public int numPairs() {
	   sortByValue();
	   int pairs=0;
	   for (int i=0; i<hand.length-1; i++) {
		   if (hand[i].getValue() == hand[i+1].getValue()){
			   pairs++;
			   i++;
		   }
	   }
	   return pairs;
   }
   public boolean hasTriplet() {
	   sortByValue();
	   boolean isTriplet = false;
	   for (int i=0; i<3;i++) {
		   if (hand[i].getValue()==hand[i+1].getValue() 
			   && hand[i+1].getValue()==hand[i+2].getValue()) {
			   isTriplet=true;
		   }
	   }
	   return isTriplet;
   }
   public boolean hasFlush() {
	   sortBySuit();
	   for (int i=0; i<hand.length-1; i++) {
		   if (hand[i].getSuit()!=hand[i+1].getSuit()) {
			   return false;
		   }
	   }
	   return true;
   }
   public boolean hasStraight() { //take into account ace can be 1 or 14
	   sortByValue();
	   if (hand[0].getValue()==1 && hand[4].getValue()==13) {
		   for (int i=1; i<hand.length-1; i++) {
			   if (hand[i].getValue()+1 != hand[i+1].getValue()) {
				   return false;
			   }
		   }
		   return true;
	   }else {
		   for (int i=0; i<hand.length-1; i++) {
			   if (hand[i].getValue()+1 != hand[i+1].getValue()) {
				   return false;
			   }
		   }
		   return true;
	   }
   }
   public boolean hasFourOfAKind() {
	   sortByValue();
	   boolean fOAK = false;
	   for (int i=0; i<1;i++) {
		   if (hand[i].getValue()==hand[i+1].getValue() 
			   && hand[i+1].getValue()==hand[i+2].getValue()
			   && hand[i].getValue()==hand[i+3].getValue()) {
			   fOAK=true;
		   }
	   }
	   return fOAK;
   }
   public boolean hasFullHouse() {
	   sortByValue();
	   if (hasFourOfAKind()==true) {
		   return false;
	   }else if (hasTriplet()==true && numPairs()==2) {
		   //need to account for the fact that triplet counts as a pair
		   //1 pair counts for double, 1 pair accounts for triplet
		   return true;
	   }else {
		   return false;
	   }
   }
   public Card highestValue() {
	   sortByValue();
	   Card largest = hand[4];
	   if (hand[0].getValue()==1) {
		   largest = hand[0];
	   }
	   return largest;
   }
  
   public Card highestDuplicate() {
	   sortByValue();
	   if (numPairs()==0) {
		   return null;
	   }else if (hand[0].getValue()==1 && hand[1].getValue()==1){
		   return hand[0];
	   }else if (numPairs()==1) {
		   for (int i=0; i<hand.length-1; i++) {
			   if (hand[i].getValue()==hand[i+1].getValue()) {
				   return hand[i];
			   }
		   }
	   }else if (numPairs()==2) {
		   for (int i=4; i>=1; i--) {
			   if (hand[i].getValue()==hand[i-1].getValue()) {
				   return hand[i];
			   }
		   }
	   }
	   return null;
   }
   public Card secondHighestPair() {
	   sortByValue();
	   if (highestDuplicate().getValue() == 1) {
		   return hand[3];
	   }else {
		   return hand[1];
	   }
	   
   }
   public Card highestNotDupof2P() {
	   sortByValue();
	   for (int i=0; i<hand.length; i+=2) {
		   if (hand[i].getValue() != highestDuplicate().getValue() 
				   && hand[i].getValue() != secondHighestPair().getValue()) {
			   return hand[i];
		   }
	   }
	   return null;
   }
   public Card highestNotDupof1P() {
	   sortByValue();
	   for (int i=4; i>=0; i--) {
		   if (hand[i].getValue() ==1 && hand[i].getValue()!=highestDuplicate().getValue()) {
			   return hand[i];
		   }
	   }
	   for (int i=4; i>=0; i--) {
		   if (hand[i].getValue() != highestDuplicate().getValue()) {
			   return hand[i];
		   }
	   }
	   return null;
   }
   public Card secondHNDo1P() { //finds the second highest non duplicate of a one pair hand
	   sortByValue();
	   for (int i=4; i>=0; i--) {
		   if (hand[i].getValue() != highestDuplicate().getValue() 
				   && hand[i].getValue() != highestNotDupof1P().getValue()) {
			   return hand[i];
		   }
	   }
	   return null;
   }
   public Card thirdHNDo1P() { //finds third highest non dup of a one pair hand
	   sortByValue();
	   for (int i=4; i>=0; i--) {
		   if (hand[i].getValue() != highestDuplicate().getValue() 
				   && hand[i].getValue() != highestNotDupof1P().getValue()
				   && hand[i].getValue() != secondHNDo1P().getValue()) {
			   return hand[i];
		   }
	   }
	   return null;
   }
   
   public Card triplet() {
	   sortByValue();
	   if (numPairs()==0) {
		   return null;
	   }else if (hand[0].getValue()==1 && hand[1].getValue()==1
			   && hand[2].getValue()==1){
		   return hand[0];
	   }else if (numPairs()==1) {
		   for (int i=0; i<hand.length-2; i++) {
			   if (hand[i].getValue()==hand[i+1].getValue() 
					   && hand[i].getValue()==hand[i+2].getValue()) {
				   return hand[i];
			   }
		   }
	   }else if (numPairs()==2) {
		   for (int i=4; i>=2; i--) {
			   if (hand[i].getValue()==hand[i-1].getValue()
					   && hand[i].getValue()==hand[i-2].getValue()) {
				   return hand[i];
			   }
		   }
	   }
	   return null;
   }
   
   public Card secondHighest() {
	   sortByValue();
	   if (hand[0].getValue()==1) {
		   return hand[4];
	   }else {
		  return hand[3]; 
	   }
   }
   public Card thirdHighest() {
	   sortByValue();
	   if (hand[0].getValue()==1) {
		   return hand[3];
	   }else {
		  return hand[2]; 
	   }
   }
   public Card fourthHighest() {
	   sortByValue();
	   if (hand[0].getValue()==1) {
		   return hand[2];
	   }else {
		  return hand[1]; 
	   }
   }
   public Card fifthHighest() {
	   sortByValue();
	   if (hand[0].getValue()==1) {
		   return hand[1];
	   }else {
		  return hand[0]; 
	   }
   }
   public int rank() {
	   sortByValue();
	   int rank = 0;
	   //checks hand of parameter hand
	   //straight flushes and royal flushes
	   if (hasStraight()==true && hasFlush()==true) {
		   if ((getCard(4)).getValue() == 13) {
			   rank = 10;
		   }else {
			   rank = 9;
		   }
		   
		//four of a kind
	   }else if (hasFourOfAKind()==true) {
		   rank = 8;
		   
		//full house
	   }else if (hasFullHouse()==true) {
		   rank = 7;
		   
		//flush
	   }else if (hasFlush()==true) {
		   rank = 6;
		   
		//straight
	   }else if (hasStraight()==true) {
		   rank = 5;
		   
		//triplet
	   }else if (hasTriplet()==true && hasFullHouse()==false) {
		   rank = 4;
		   
		//two pair
	   }else if (numPairs()==2 && hasFourOfAKind()==false && hasFullHouse()==false) {
		  rank = 3; 
		  
		//one pair
	   }else if (numPairs()==1 && hasTriplet()==false) {
		   rank = 2;
		   
		//high card
	   }else {
		   rank = 1;
	   }
	   return rank;
   }
   
  /**START OF COMPARISONS!!!!!!!!!!!!!!!!!!!!!!!**/
   
   public int compareTo(Hand h) {
	   this.sortByValue();
	   h.sortByValue();
	   int hRank = h.rank();
	   int inRank = this.rank();
	
	   //compare ranks
	   if (hRank>inRank) {
		   return -1; 		//if instance loses return -1
	   }if (hRank<inRank) {
		   return 1;			//if instance wins return 1
	   }
	   
	   //if ranks are same
	   if (hRank==inRank) {
		   
		   /**royal flush**/
		   if (hRank == 10 && inRank == 10) {
			   return 0;
		   }
		   
		   /**straight flush, but not royal**/
		   if (hRank == 9 && inRank == 9) {
			   //aces are 1 in straights so don't have to take into account
			   if (h.getCard(4).getValue() > this.getCard(4).getValue()) {
				   return -1;
			   }else if (h.getCard(4).getValue() < this.getCard(4).getValue()) {
				   return 1;
			   }else if (h.getCard(4).getValue() == this.getCard(4).getValue()
					   && h.getCard(0).getValue() == this.getCard(0).getValue()) {
				   //if first and last cards are the same then the straight is equal
				   return 0;
			   }
				   
		   }
		   
		   /**four of a kind**/
		   if (hRank == 8 && inRank == 8) {
			   //hand with all 4 aces automatically wins
			   if (h.highestDuplicate().getValue()==1) {
				   return -1;
			   }else if (this.highestDuplicate().getValue()==1) {
				   return 1;
			   }else if (h.highestDuplicate().getValue() > this.highestDuplicate().getValue()) {
				   return -1;
			   }else if (h.highestDuplicate().getValue() < this.highestDuplicate().getValue()) {
				   return 1;
			   }
		   }
		   
		   /**full house**/
		   if (hRank == 7 && inRank == 7) {
			   //aces
			   //no aces
			   if (h.triplet().getValue() > this.triplet().getValue()) {
				   return -1;
			   }else if (h.triplet().getValue() < this.triplet().getValue()) {
				   return 1;
			   }
			   
		   }
		   
		   /**flush**/ 
		   if (hRank == 6 && inRank == 6) { //basically the same as high card
			   //aces
			   if ((h.highestValue()).getValue() == 1 && (this.highestValue()).getValue() != 1) {
				   return -1;
			   }else if ((h.highestValue()).getValue() != 1 && (this.highestValue()).getValue() == 1) {
				   return 1;
			   }else if ((h.highestValue()).getValue() == 1 && (this.highestValue()).getValue() == 1) {
				   if ((h.secondHighest()).getValue() > this.secondHighest().getValue()) {
					   return -1;
				   }else if ((h.secondHighest()).getValue() < this.secondHighest().getValue()) {
					   return 1;
				   }else if ((h.secondHighest()).getValue() == this.secondHighest().getValue()) {
					   if ((h.thirdHighest()).getValue() > this.thirdHighest().getValue()) {
						   return -1;
					   }else if ((h.thirdHighest()).getValue() < this.thirdHighest().getValue()) {
						   return 1;
					   }else if ((h.thirdHighest()).getValue() == this.thirdHighest().getValue()) {
						   if ((h.fourthHighest()).getValue() > this.fourthHighest().getValue()) {
							   return -1;
						   }else if ((h.fourthHighest()).getValue() < this.fourthHighest().getValue()) {
							   return 1;
						   }else if ((h.fourthHighest()).getValue() == this.fourthHighest().getValue()) {
							   if ((h.fifthHighest()).getValue() > this.fifthHighest().getValue()) {
								   return -1;
							   }else if ((h.fifthHighest()).getValue() < this.fifthHighest().getValue()) {
								   return 1;
							   }else if ((h.fifthHighest()).getValue() == this.fifthHighest().getValue()) {
								   return 0;
							   }
						   }
					   }
				   }
			   }
			   //no aces
			   if ((h.highestValue()).getValue() > (this.highestValue()).getValue()) {
				   return -1;
			   }else if ((h.highestValue()).getValue() < (this.highestValue()).getValue()) {
				   return 1;
			   }else if ((h.highestValue()).getValue() == (this.highestValue()).getValue()) {
				   if ((h.secondHighest()).getValue() > this.secondHighest().getValue()) {
					   return -1;
				   }else if ((h.secondHighest()).getValue() < this.secondHighest().getValue()) {
					   return 1;
				   }else if ((h.secondHighest()).getValue() == this.secondHighest().getValue()) {
					   if ((h.thirdHighest()).getValue() > this.thirdHighest().getValue()) {
						   return -1;
					   }else if ((h.thirdHighest()).getValue() < this.thirdHighest().getValue()) {
						   return 1;
					   }else if ((h.thirdHighest()).getValue() == this.thirdHighest().getValue()) {
						   if ((h.fourthHighest()).getValue() > this.fourthHighest().getValue()) {
							   return -1;
						   }else if ((h.fourthHighest()).getValue() < this.fourthHighest().getValue()) {
							   return 1;
						   }else if ((h.fourthHighest()).getValue() == this.fourthHighest().getValue()) {
							   if ((h.fifthHighest()).getValue() > this.fifthHighest().getValue()) {
								   return -1;
							   }else if ((h.fifthHighest()).getValue() < this.fifthHighest().getValue()) {
								   return 1;
							   }else if ((h.fifthHighest()).getValue() == this.fifthHighest().getValue()) {
								   return 0;
							   }
						   }
					   }
				   }
			   }
		   }
		   
		   /**straight only**/
		   if (hRank == 5 && inRank == 5) {
			   //aces are 1 in straights so don't have to take into account
			   if (h.getCard(4).getValue() > this.getCard(4).getValue()) {
				   return -1;
			   }else if (h.getCard(4).getValue() < this.getCard(4).getValue()) {
				   return 1;
			   }else if (h.getCard(4).getValue() == this.getCard(4).getValue()
					   && h.getCard(0).getValue() == this.getCard(0).getValue()) {
				   //if first and last cards are the same then the straight is equal
				   return 0;
			   }
				   
		   }
		   
		   /**triplet**/
		   if (hRank == 4 && inRank == 4) {
			   //aces
			   if (h.triplet().getValue() == 1) {
				   return -1;
			   }else if (this.triplet().getValue() == 1) {
				   return 1;
			   }
			   //no aces
			   if (h.triplet().getValue() > this.triplet().getValue()) {
				   return -1;
			   }else if (h.triplet().getValue() < this.triplet().getValue()) {
				   return 1;
			   }
		   }
		   /**two pair**/
		   if (hRank == 3 && inRank == 3) {
			   //aces
			   if (h.highestDuplicate().getValue()==1 && this.highestDuplicate().getValue()!=1) {
					  return -1;
				  }else if (h.highestDuplicate().getValue()!=1 && this.highestDuplicate().getValue()==1) {
					  return 1;
				  }else if (h.highestDuplicate().getValue()==1 && this.highestDuplicate().getValue()==1) {
					  if (h.secondHighestPair().getValue() > this.secondHighestPair().getValue()) {
						  return -1;
					  }else if (h.secondHighestPair().getValue() < this.secondHighestPair().getValue()) {
						  return 1;
					  }else if (h.secondHighestPair().getValue() == this.secondHighestPair().getValue()) {
						  if (h.highestNotDupof2P().getValue() > this.highestNotDupof2P().getValue()) {
							  return -1;
						  }else if (h.highestNotDupof2P().getValue() < this.highestNotDupof2P().getValue()) {
							  return 1;
						  }else if (h.highestNotDupof2P().getValue() == this.highestNotDupof2P().getValue()) {
							  return 0;
						  }
					  }
				  }
			   
			   //aces only possible in the non dup card
			  if (h.highestDuplicate().getValue() > this.highestDuplicate().getValue()) {
				  return -1;
			  }else if (h.highestDuplicate().getValue() < this.highestDuplicate().getValue()) {
				  return 1;
			  }else if (h.highestDuplicate().getValue() == this.highestDuplicate().getValue()) {
				  if (h.secondHighestPair().getValue() > this.secondHighestPair().getValue()) {
					  return -1;
				  }else if (h.secondHighestPair().getValue() < this.secondHighestPair().getValue()) {
					  return 1;
				  }else if (h.secondHighestPair().getValue() == this.secondHighestPair().getValue()) {
					  if (h.highestNotDupof2P().getValue() ==1 && this.highestNotDupof2P().getValue()!=1) {
						  return -1;
					  }else if (h.highestNotDupof2P().getValue()!=1 && this.highestNotDupof2P().getValue()==1) {
						  return 1;
					  }else if (h.highestNotDupof2P().getValue()==1 && this.highestNotDupof2P().getValue()==1) {
						  return 0;
					  } 
					  if (h.highestNotDupof2P().getValue() > this.highestNotDupof2P().getValue()) {
						  return -1;
					  }else if (h.highestNotDupof2P().getValue() < this.highestNotDupof2P().getValue()) {
						  return 1;
					  }else if (h.highestNotDupof2P().getValue() == this.highestNotDupof2P().getValue()) {
						  return 0;
					  }
				  }
			  }
		   }
		   
		   /**one pair**/
		   if (hRank == 2 && inRank ==2) {
			   //if aces
			   if ((h.highestValue()).getValue() == 1 && (this.highestValue()).getValue() != 1) {
				   return -1;
			   }else if ((h.highestValue()).getValue() != 1 && (this.highestValue()).getValue() == 1) {
				   return 1;
			   }else if ((h.highestValue()).getValue() == 1 && (this.highestValue()).getValue() == 1) {
				   if (h.highestNotDupof1P().getValue() > this.highestNotDupof1P().getValue()) {
					   return -1;
				   }else if (h.highestNotDupof1P().getValue() < this.highestNotDupof1P().getValue()) {
					   return 1;
				   }else if (h.highestNotDupof1P().getValue() == this.highestNotDupof1P().getValue()) {
					   if (h.secondHNDo1P().getValue() > this.secondHNDo1P().getValue()) {
						   return -1;
					   }else if (h.secondHNDo1P().getValue() < this.secondHNDo1P().getValue()) {
						   return 1;
					   }else if (h.secondHNDo1P().getValue() == this.secondHNDo1P().getValue()) {
						   if (h.thirdHNDo1P().getValue() > this.thirdHNDo1P().getValue()) {
							   return -1;
						   }else if (h.thirdHNDo1P().getValue() < this.thirdHNDo1P().getValue()) {
							   return 1;
						   }else if (h.thirdHNDo1P().getValue() == this.thirdHNDo1P().getValue()) {
							   return 0;
						   }
					   }
				   }
			   }
			   
			   //if not aces
			   if (h.highestDuplicate().getValue() > this.highestDuplicate().getValue()) {
				   return -1;
			   }else if (h.highestDuplicate().getValue() < this.highestDuplicate().getValue()) {
				   return 1;
			   }if (h.highestDuplicate().getValue() == this.highestDuplicate().getValue()) {
				   if (h.highestNotDupof1P().getValue() > this.highestNotDupof1P().getValue()) {
					   return -1;
				   }else if (h.highestNotDupof1P().getValue() < this.highestNotDupof1P().getValue()) {
					   return 1;
				   }else if (h.highestNotDupof1P().getValue() == this.highestNotDupof1P().getValue()) {
					   if (h.secondHNDo1P().getValue() > this.secondHNDo1P().getValue()) {
						   return -1;
					   }else if (h.secondHNDo1P().getValue() < this.secondHNDo1P().getValue()) {
						   return 1;
					   }else if (h.secondHNDo1P().getValue() == this.secondHNDo1P().getValue()) {
						   if (h.thirdHNDo1P().getValue() > this.thirdHNDo1P().getValue()) {
							   return -1;
						   }else if (h.thirdHNDo1P().getValue() < this.thirdHNDo1P().getValue()) {
							   return 1;
						   }else if (h.thirdHNDo1P().getValue() == this.thirdHNDo1P().getValue()) {
							   return 0;
						   }
					   }
			   }
		   }
		   }
		   
		   /**high card**/
		   if (hRank == 1 && inRank == 1) {
			   //if aces
			   if ((h.highestValue()).getValue() == 1 && (this.highestValue()).getValue() != 1) {
				   return -1;
			   }else if ((h.highestValue()).getValue() != 1 && (this.highestValue()).getValue() == 1) {
				   return 1;
			   }else if ((h.highestValue()).getValue() == 1 && (this.highestValue()).getValue() == 1) {
				   if ((h.secondHighest()).getValue() > this.secondHighest().getValue()) {
					   return -1;
				   }else if ((h.secondHighest()).getValue() < this.secondHighest().getValue()) {
					   return 1;
				   }else if ((h.secondHighest()).getValue() == this.secondHighest().getValue()) {
					   if ((h.thirdHighest()).getValue() > this.thirdHighest().getValue()) {
						   return -1;
					   }else if ((h.thirdHighest()).getValue() < this.thirdHighest().getValue()) {
						   return 1;
					   }else if ((h.thirdHighest()).getValue() == this.thirdHighest().getValue()) {
						   if ((h.fourthHighest()).getValue() > this.fourthHighest().getValue()) {
							   return -1;
						   }else if ((h.fourthHighest()).getValue() < this.fourthHighest().getValue()) {
							   return 1;
						   }else if ((h.fourthHighest()).getValue() == this.fourthHighest().getValue()) {
							   if ((h.fifthHighest()).getValue() > this.fifthHighest().getValue()) {
								   return -1;
							   }else if ((h.fifthHighest()).getValue() < this.fifthHighest().getValue()) {
								   return 1;
							   }else if ((h.fifthHighest()).getValue() == this.fifthHighest().getValue()) {
								   return 0;
							   }
						   }
					   }
				   }
			   }
				   
			   //no aces
			   if ((h.highestValue()).getValue() > (this.highestValue()).getValue()) {
				   return -1;
			   }else if ((h.highestValue()).getValue() < (this.highestValue()).getValue()) {
				   return 1;
			   }else if ((h.highestValue()).getValue() == (this.highestValue()).getValue()) {
				   if ((h.secondHighest()).getValue() > this.secondHighest().getValue()) {
					   return -1;
				   }else if ((h.secondHighest()).getValue() < this.secondHighest().getValue()) {
					   return 1;
				   }else if ((h.secondHighest()).getValue() == this.secondHighest().getValue()) {
					   if ((h.thirdHighest()).getValue() > this.thirdHighest().getValue()) {
						   return -1;
					   }else if ((h.thirdHighest()).getValue() < this.thirdHighest().getValue()) {
						   return 1;
					   }else if ((h.thirdHighest()).getValue() == this.thirdHighest().getValue()) {
						   if ((h.fourthHighest()).getValue() > this.fourthHighest().getValue()) {
							   return -1;
						   }else if ((h.fourthHighest()).getValue() < this.fourthHighest().getValue()) {
							   return 1;
						   }else if ((h.fourthHighest()).getValue() == this.fourthHighest().getValue()) {
							   if ((h.fifthHighest()).getValue() > this.fifthHighest().getValue()) {
								   return -1;
							   }else if ((h.fifthHighest()).getValue() < this.fifthHighest().getValue()) {
								   return 1;
							   }else if ((h.fifthHighest()).getValue() == this.fifthHighest().getValue()) {
								   return 0;
							   }
						   }
					   }
				   }
			   }
		   }
		   
	}
	   return 0;
}
}
	   

