public class HandTest {
	public static void main(String[] args) {
		Hand hand = new Hand();	
		
		hand.addCard(new Card(9, Card.CLUBS));
		hand.addCard(new Card(10, Card.HEARTS));
		hand.addCard(new Card(Card.KING, Card.CLUBS));
		hand.addCard(new Card(Card.QUEEN, Card.DIAMONDS));
		hand.addCard(new Card(Card.KING, Card.CLUBS));
		
		Hand hand2 = new Hand();	
		
		hand2.addCard(new Card(Card.KING, Card.SPADES));
		hand2.addCard(new Card(Card.KING, Card.CLUBS));
		hand2.addCard(new Card(Card.QUEEN, Card.HEARTS));
		hand2.addCard(new Card(10, Card.HEARTS));
		hand2.addCard(new Card(2, Card.HEARTS));
		
		PlayerAI player1 = new PlayerAI(100.50);
		player1.deal(new Card(9, Card.CLUBS));
		player1.deal(new Card(10, Card.CLUBS));
		player1.deal(new Card(5, Card.CLUBS));
		player1.deal(new Card(6, Card.CLUBS));
		player1.deal(new Card(2, Card.CLUBS));
		
		System.out.println(player1.wager(0));
		System.out.println(player1.getBalance());
	}
}
