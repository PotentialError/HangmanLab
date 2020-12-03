package michaelrodyushkin.honorsmobileapps.hangmanlab;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameStateFragment extends Fragment {
    TextView guessedWord;
    EditText editText;
    private static final String WORD_KEY = "word_key";
    private OnLetterEnteredListener listener;
    Handler handler = new Handler();
    public GameStateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_game_state, container, false);
        guessedWord = rootView.findViewById(R.id.wordText);
        editText = rootView.findViewById(R.id.guessArea);
        updateCurrentIncompleteWord(getArguments().getString(WORD_KEY));
        Button button = rootView.findViewById(R.id.reset);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        editText.setText("");
                    }
                }, 1000);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    listener.onLetterEntered(s.charAt(0));
                }
                handler.removeCallbacksAndMessages(null);
            }
        });
        View.OnClickListener listen = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.resetGame();
            }
        };
        button.setOnClickListener(listen);
        // Inflate the layout for this fragment
        return rootView;
    }
    public void updateCurrentIncompleteWord(String word) {
        guessedWord.setText(word);
    }
    public static GameStateFragment newInstance(String word) {
        Bundle bundle = new Bundle();
        bundle.putString(WORD_KEY, word);
        GameStateFragment gameStateFragment = new GameStateFragment();
        gameStateFragment.setArguments(bundle);
        return gameStateFragment;
    }
    public interface OnLetterEnteredListener {
        public void onLetterEntered(char letter);
        public void resetGame();
    }
    public void setOnLetterEnteredListener(OnLetterEnteredListener list) {
        listener = list;
    }
}
