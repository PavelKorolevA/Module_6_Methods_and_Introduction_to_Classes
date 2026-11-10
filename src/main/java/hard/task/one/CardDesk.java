package hard.task.one;

import java.util.Random;

public class CardDesk {
    private final Card[] cards = new Card[36];
    private int nextCard;

    public CardDesk(Random random) {
        int index = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards[index++] = new Card(suit, rank);
            }
        }

        for (int i = cards.length - 1; i > 0; i--) {
            int other = random.nextInt(i + 1);
            Card temporary = cards[i];
            cards[i] = cards[other];
            cards[other] = temporary;
        }
    }

    public Card draw() {
        if (nextCard == cards.length) {
            throw new IllegalStateException("Карты в колоде закончились");
        }
        return cards[nextCard++];
    }
}
