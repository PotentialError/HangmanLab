package michaelrodyushkin.honorsmobileapps.hangmanlab;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameResultFragment extends Fragment {
    private static final String STATUS_KEY = "status_key";
    private static final String WORD_KEY = "word_key";
    TextView textView;

    public GameResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_game_result, container, false);
        int status = getArguments().getInt(STATUS_KEY);
        textView = rootView.findViewById(R.id.gameStatus);
        if(status == 1) {
            textView.setText("You won");
            textView.setBackgroundColor(Color.parseColor("#90EE90"));
        }
        else if(status == 0) {
            String text = "You lose\nWord was: " + getArguments().getString(WORD_KEY);
            textView.setText(text);
            textView.setBackgroundColor(Color.parseColor("#ff0000"));
        }
        // Inflate the layout for this fragment
        return rootView;
    }
    public static GameResultFragment newInstance(int status, String word) {
        Bundle bundle = new Bundle();
        GameResultFragment gameResultFragment = new GameResultFragment();
        bundle.putInt(STATUS_KEY, status);
        bundle.putString(WORD_KEY, word);
        gameResultFragment.setArguments(bundle);
        return gameResultFragment;
    }
}
