package fr.mnf.nbapalsapp.logic.bets;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.mnf.nbapalsapp.R;

/**
 *
 */
public class GamesFragment extends Fragment {

    private static final String LOG_TAG = "GAMES_FRAG";

    private Months mMonth;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param monthName Parameter 1.
     * @return A new instance of fragment GamesFragment.
     */
    public static GamesFragment newInstance(int monthName) {
        GamesFragment fragment = new GamesFragment();
        return fragment;
    }

    public GamesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_games, container, false);
        GamesBetAdapter adapter = new GamesBetAdapter(getChildFragmentManager());
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }
}
