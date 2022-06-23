package flashcards.entities;

import java.util.*;
import java.util.Map.Entry;

public class Deck {
    private final Random random;
    private final List<String> keys;
    private final Map<String, String> cards;
    private final Map<String, String> reversedCards;
    private final Map<String, Integer> cardMistakes;

    private int maxMistakes;
    private List<String> maxMistakesTerms;

    public Deck() {
        random = new Random();

        // for "hardest card" stats
        maxMistakes = 0;
        maxMistakesTerms = new ArrayList<>();

        keys = new ArrayList<>();
        cards = new LinkedHashMap<>();
        reversedCards = new LinkedHashMap<>();
        cardMistakes = new LinkedHashMap<>();
    }

    public List<String> getKeys() {
        return keys;
    }

    public Map<String, String> getCards() {
        return cards;
    }

    public Map<String, String> getReversedCards() {
        return reversedCards;
    }

    public Map<String, Integer> getCardMistakes() {
        return cardMistakes;
    }

    public Map<String, String> getRandomCard() {
        String key = keys.get(random.nextInt(keys.size()));
        return Map.of(key, cards.get(key));
    }

    /**
     * For adding a new flash card (using "term" and "definition")
     */
    public void addCard(String term, String definition, int mistakes) {
        keys.add(term);
        cards.put(term, definition);
        reversedCards.put(definition, term);
        cardMistakes.put(term, mistakes);

        if (mistakes > 0) {
            recalculateMistakeStats(term);
        }
    }

    public void addOrUpdateCard(String term, String definition, int mistakes) {
        if (cards.containsKey(term)) {
            String oldDefinition = cards.get(term);

            // "cards" will be updated with the new value automatically
            // But for "reversedCards" we'll need to do this process manually.
            // So, remove the entry for "oldDefinition" from "reversedCards"
            reversedCards.remove(oldDefinition);
        }
        addCard(term, definition, mistakes);
    }

    /**
     * For removing a card by "term" (i.e. key)
     */
    public void removeCard(String term) {
        cardMistakes.remove(term);
        reversedCards.remove(cards.get(term));
        cards.remove(term);
        keys.remove(term);
    }

    public void updateCardMistakes(String term) {
        cardMistakes.put(term, cardMistakes.getOrDefault(term, 0) + 1);

        recalculateMistakeStats(term);
    }

    private void recalculateMistakeStats(String term) {
        if (cardMistakes.get(term) > maxMistakes) {
            maxMistakes = cardMistakes.get(term);

            maxMistakesTerms.clear();
            for (Entry<String, Integer> entry: cardMistakes.entrySet()) {
                if (entry.getValue() == maxMistakes) {
                    maxMistakesTerms.add(entry.getKey());
                }
            }
        } else if (cardMistakes.get(term) == maxMistakes) {
            maxMistakesTerms.add(term);
        }
    }

    public int getMaxMistakes() {
        return maxMistakes;
    }

    public List<String> getMaxMistakesTerms() {
        return maxMistakesTerms;
    }

    public void resetCardStats() {
        cardMistakes.clear();

        maxMistakes = 0;
        maxMistakesTerms.clear();
    }

    public int size() {
        return cards.size();
    }
}
