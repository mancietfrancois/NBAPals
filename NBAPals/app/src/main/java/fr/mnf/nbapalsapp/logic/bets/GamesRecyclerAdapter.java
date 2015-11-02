package fr.mnf.nbapalsapp.logic.bets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wnafee.vector.compat.VectorDrawable;

import java.util.List;

import fr.mnf.nbapals.nbamodel.NBAGame;
import fr.mnf.nbapals.nbamodel.NBAGamesDay;
import fr.mnf.nbapals.nbamodel.utils.Teams;
import fr.mnf.nbapalsapp.R;
import fr.mnf.nbapalsapp.logic.utils.NBATeamsVc;

/**
 * Created by echessa on 7/24/15.
 */
public class GamesRecyclerAdapter extends RecyclerView.Adapter<GamesRecyclerAdapter.ViewHolder> {

    private static final String LOG_TAG = "GAMERECYC_ADAPTER";

    private List<NBAGamesDay> mGamesDay;
    private Context mContext;

    GamesRecyclerAdapter(List<NBAGamesDay> gamesDay, Context context) {
        mGamesDay = gamesDay;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_layout3, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int nbGames = -1;
        NBAGame game = null;
        for (NBAGamesDay aGameDay : mGamesDay) {
            if (i < aGameDay.getGames().size()) {
                for (NBAGame aGame : aGameDay.getGames()) {
                    nbGames++;
                    if (i == nbGames) {
                        game = aGame;
                        break;
                    }
                }
            } else {
                nbGames += aGameDay.getGames().size();
            }
        }
        viewHolder.textViewStatus.setText(game.getStatus().getValue());
        Log.d(LOG_TAG, "Inflate Drawable for : " + game.getTeamHome().getShortname() + " and " + game.getTeamAway().getShortname());
        if (game.getTeamAway() == Teams.MEM || game.getTeamHome() == Teams.MEM)  {
            Log.d(LOG_TAG, "Error case : MEM");
        } else if (game.getTeamAway() == Teams.TOR || game.getTeamHome() == Teams.TOR) {
            //error
            Log.d(LOG_TAG, "Error case : TOR?");
        } else if (game.getTeamAway() == Teams.MIL || game.getTeamHome() == Teams.MIL) {
            Log.d(LOG_TAG, "Error case : MIL?");
        } else if (game.getTeamAway() == Teams.CLE || game.getTeamHome() == Teams.CLE) {
            Log.d(LOG_TAG, "Error case : CLE?");
        } else {
            viewHolder.imageViewHome.setImageDrawable(
                    VectorDrawable.getDrawable(mContext, NBATeamsVc.findVCFromTeam(game.getTeamHome())));
            viewHolder.imageViewAway.setImageDrawable(
                    VectorDrawable.getDrawable(mContext, NBATeamsVc.findVCFromTeam(game.getTeamAway())));
        }
    }

    @Override
    public int getItemCount() {
        int nbGames = -1;
        for (NBAGamesDay aGameDay : mGamesDay) {
            for (NBAGame aGame : aGameDay.getGames()) {
                nbGames++;
            }
        }
        return nbGames;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHomeScore;
        public TextView textViewAwayScore;
        public TextView textViewStatus;

        public ImageView imageViewHome;
        public ImageView imageViewAway;
        public ImageView imageViewBet;


        public ViewHolder(View base) {
            super(base);
            textViewAwayScore = (TextView) base.findViewById(R.id.away_score);
            textViewHomeScore = (TextView) base.findViewById(R.id.home_score);
            imageViewHome = (ImageView) base.findViewById(R.id.image_team_home);
            imageViewAway = (ImageView) base.findViewById(R.id.image_team_away);
            textViewStatus = (TextView) base.findViewById(R.id.status);
            imageViewBet = (ImageView) base.findViewById(R.id.bet);
        }
    }


}
