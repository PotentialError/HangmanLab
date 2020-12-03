package michaelrodyushkin.honorsmobileapps.hangmanlab;

public class Hangman {
    private String [ ] possibleWords = { "CHICKEN", "BAMBOOZLE", "ZINGER", "TENDERS", "YATZEE", "UMBRELLA", "ZEBRA"};

    // The word the user is guessing
    private String word;
    // Keeps track of what indices have been correctly guessed
    private boolean [ ] indicesGuessed;
    // How many guesses the user has left before they’ve lost
    private int guessesLeft;
    // Total number of guesses at the start of the game
    private final int GUESSES_ALLOWED = 6;

    public Hangman() {
        guessesLeft = GUESSES_ALLOWED;
        word = possibleWords[((int)(Math.random() * possibleWords.length))];
        indicesGuessed = new boolean[word.length()];
    }

    public int getGUESSES_ALLOWED() {
        return GUESSES_ALLOWED;
    }
    public int getGuessesLeft() {
        return guessesLeft;
    }

    public void makeGuess(char c) {
        String string = "" + c;
        string = string.toUpperCase();
        c = string.charAt(0);
        if(word.indexOf(c) != -1) {
            for(int i = 0; i  < word.length(); i++) {
                if(word.charAt(i) == c)
                    indicesGuessed[i] = true;
            }
        }
        else {
            guessesLeft--;
        }
    }

    public String getCurrentIncompleteWord() {
        String currentWord = " ";
        for(int i = 0; i < indicesGuessed.length; i++) {
            if(indicesGuessed[i]) {
                currentWord = currentWord + word.charAt(i) + " ";
            }
            else {
                currentWord = currentWord + "_" + " ";
            }
        }
        return currentWord;
    }
    public String getWord() {
        return word;
    }


    public int checkForGameOver() {
        if(guessesLeft > 0) {
            for(int i = 0; i < indicesGuessed.length; i++) {
                if(!indicesGuessed[i]) {
                    return -1;
                }
            }
            return 1;
        }
        return 0;
    }
}
