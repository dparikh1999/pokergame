public class PlayerAI {
	//declare fields 
	
		private double bet;
		private double bal;
		private Hand h;
		private Card c;
		private Card[] discard;

		//initialize your fields in the constructor
		public PlayerAI(double balance){
			bet = 0;
			bal = balance;
			discard = new Card[5];
			c = new Card();
			h = new Hand();
		}

		public void deal(Card c){
			h.addCard(c);
		}

		public Card[] discard(){
			return discard;
		}
		
		public double wager(double min){
			if (bal < min) {
				bet = -1;
			}else if (h.rank() == 10) {
				bet=bal;
			}else if (h.hasFourOfAKind()==true || (h.hasStraight()==true && h.hasFlush()==true)) {
				bet = bal;
			}else {
				bet=-1;
			}
			return bet;
		}
			
		//Returns this player's hand
		public Hand showHand(){
			return h;
		}

		//Returns this player's current balance
		public double getBalance(){
			return bal;
		}

		//Increase player's balance by the amount specified in the parameter,
		//then reset player's hand in preparation for next round. Amount will
		//be 0 if player has lost hand
		public void winnings(double amount){
			bal += amount;
			h.clear();
		}

	}

