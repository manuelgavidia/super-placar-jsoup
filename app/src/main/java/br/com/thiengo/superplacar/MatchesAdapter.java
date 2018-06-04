package br.com.thiengo.superplacar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.thiengo.superplacar.domain.Match;
import br.com.thiengo.superplacar.domain.Team;


public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    final private Context context;
    final private List<Match> mMatches;

    MatchesAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.mMatches = matches;
    }

    @NonNull
    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(context)
                .inflate(R.layout.item_match, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mMatches.get(position));
    }

    @Override
    public int getItemCount() {
        return mMatches.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvStatus;
        final TextView tvScore;
        final TeamView homeView;
        final TeamView awayView;
        ViewHolder(View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvScore = itemView.findViewById(R.id.tv_score);
            homeView = new TeamView((ImageView) itemView.findViewById(R.id.iv_team_home),
                                    (TextView) itemView.findViewById(R.id.tv_name_team_home));
            awayView = new TeamView((ImageView) itemView.findViewById(R.id.iv_team_away),
                                    (TextView) itemView.findViewById(R.id.tv_name_team_away));
        }

        private void setData(Match match) {
            tvStatus.setText(match.getStatus());
            homeView.load(match.getHome());
            awayView.load(match.getAway());
            tvScore.setText(match.getScore());
        }

        class TeamView {
            private final ImageView iv;
            private final TextView tvName;
            TeamView(ImageView iv, TextView tvName) {
                this.iv = iv;
                this.tvName = tvName;
            }

            void load(Team team) {
                try {
                    Picasso.with(context)
                            .load(team.getImageUrl())
                            .into(this.iv);
                } catch (Exception e) {
                    Log.e(this.getClass().getName(), e.toString());
                }

                this.tvName.setText(String.valueOf(team.getName()));
            }
        }
    }
}
