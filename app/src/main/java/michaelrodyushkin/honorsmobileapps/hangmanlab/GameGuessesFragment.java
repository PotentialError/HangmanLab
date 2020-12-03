package michaelrodyushkin.honorsmobileapps.hangmanlab;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameGuessesFragment extends Fragment {
    private static final String KEY_NUM = "key_num";
    TextView mGuessesText;
    public GameGuessesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_game_guesses, container, false);
        mGuessesText = rootView.findViewById(R.id.numOfGuesses);
        updateGuesses(getArguments().getInt(KEY_NUM));
        return rootView;
    }
    public void updateGuesses(int num) {
        mGuessesText.setText("Guesses Left: " + num);
    }
    public static GameGuessesFragment newInstance(int guesses) {
        Bundle bundle = new Bundle();
        GameGuessesFragment guessesFragment = new GameGuessesFragment();
        bundle.putInt(KEY_NUM, guesses);
        guessesFragment.setArguments(bundle);
        return guessesFragment;
    }

}
