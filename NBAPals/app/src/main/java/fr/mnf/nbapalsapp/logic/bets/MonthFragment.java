package fr.mnf.nbapalsapp.logic.bets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapalsapp.R;

/**
 * Created by Francois on 31/10/2015.
 */
public class MonthFragment extends Fragment {

    private static final String ARG_MONTH = "ARG_MONTH";
    private static final String LOG_TAG = "MONTH_FRAG";


    private Months mMonth;
    private RecyclerView mRecycList;
    private GamesRecyclerAdapter mAdapter;
    private View mView;

    private List<NBAGamesDay> mGamesDays;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param monthName Parameter 1.
     * @return A new instance of fragment GamesFragment.
     */
    public static MonthFragment newInstance(int monthName) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MONTH, monthName);
        fragment.setArguments(args);
        return fragment;
    }

    public MonthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMonth = Months.findMonthFromTabNumber(getArguments().getInt(ARG_MONTH));
            Log.d(LOG_TAG, "Month : " + mMonth.getMonthName());
            new GamesClientTask(mMonth.getMonthNumber() + "-..", "games/getmonth", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d(LOG_TAG, "Failure! Response:" + responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    //Log.d(LOG_TAG, "Success! Response:" + responseString);
                    mGamesDays = new Gson().fromJson(responseString, new TypeToken<List<NBAGamesDay>>() {}.getType());
                    Log.d(LOG_TAG, "Size : " + mGamesDays.size());
                    mAdapter = new GamesRecyclerAdapter(mGamesDays, getContext());
                    mRecycList.setAdapter(mAdapter);
                }
            }).invokeWS(getContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_game_list, container, false);
        RecyclerView recList = (RecyclerView) v.findViewById(R.id.recyclerviewgames);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        mRecycList = recList;
        mView = v;
        return v;
    }
}
