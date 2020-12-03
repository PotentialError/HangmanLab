package michaelrodyushkin.honorsmobileapps.hangmanlab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GameStateFragment.OnLetterEnteredListener {
    private Hangman hangman;
    private GameGuessesFragment gameGuessesFragment;
    private GameStateFragment gameStateFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hangman = new Hangman();
        gameGuessesFragment = GameGuessesFragment.newInstance(hangman.getGuessesLeft());
        gameStateFragment = GameStateFragment.newInstance(hangman.getCurrentIncompleteWord());
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.gameStatus, gameGuessesFragment).commit();
        fm.beginTransaction().add(R.id.wordText, gameStateFragment).commit();
    }

    @Override
    public void onLetterEntered(char letter) {
        if(hangman.checkForGameOver() != 1) {
            hangman.makeGuess(letter);
        }
        if(hangman.checkForGameOver() == 0 || hangman.checkForGameOver() == 1) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.gameStatus, GameResultFragment.newInstance(hangman.checkForGameOver(), hangman.getWord())).commit();
        }
        else {
            gameGuessesFragment.updateGuesses(hangman.getGuessesLeft());
        }
        gameStateFragment.updateCurrentIncompleteWord(hangman.getCurrentIncompleteWord());
    }
    @Override
    public void onAttachFragment(Fragment fragment) {
        if(fragment instanceof GameStateFragment) {
            GameStateFragment frag = (GameStateFragment) fragment;
            frag.setOnLetterEnteredListener(this);
        }
    }
    @Override
    public void resetGame() {
        hangman = new Hangman();
        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentById(R.id.gameStatus) instanceof GameResultFragment) {
            FragmentManager fm2 = getSupportFragmentManager();
            fm2.beginTransaction().replace(R.id.gameStatus, gameGuessesFragment).commit();
        }
        gameStateFragment.updateCurrentIncompleteWord(hangman.getCurrentIncompleteWord());
        gameGuessesFragment.updateGuesses(hangman.getGuessesLeft());
    }
}
