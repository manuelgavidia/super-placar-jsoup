package br.com.thiengo.superplacar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import br.com.thiengo.superplacar.domain.Goal;
import br.com.thiengo.superplacar.domain.Match;
import br.com.thiengo.superplacar.domain.Team;


public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    final private Context context;
    final private List<Match> matches;

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvStatus;

        class TeamView {
            TeamView(ImageView iv, TextView tvName, TextView tvGoals, RecyclerView rv) {
                this.iv = iv;
                this.rv = rv;
                this.tvGoals = tvGoals;
                this.tvName = tvName;
            }

            void load(Team team) {
                Picasso.with( context )
                        .load( team.getImageUrl() )
                        .into(this.iv);
                this.tvName.setText( String.valueOf( team.getName() ) );
                this.tvGoals.setText( String.valueOf( team.getGoals() ) );
                updateRecyclerView(this.rv, team.getGoalsList() );
            }
            private final ImageView iv;
            private final TextView tvGoals;
            private final TextView tvName;
            private final RecyclerView rv;
        }

        final TeamView teamView1;
        final TeamView teamView2;

        ViewHolder(View itemView) {
            super(itemView);

            tvStatus =    itemView.findViewById(R.id.tv_status);

            teamView1 = new TeamView(   (ImageView)itemView.findViewById(R.id.iv_team_1),
                                        (TextView)itemView.findViewById(R.id.tv_name_team_1),
                                        (TextView)itemView.findViewById(R.id.tv_goals_team_1),
                                        initRecyclerView(R.id.rv_goals_team_1, R.layout.item_goal_left));

            teamView2 = new TeamView(   (ImageView)itemView.findViewById(R.id.iv_team_2),
                                        (TextView)itemView.findViewById(R.id.tv_name_team_2),
                                        (TextView)itemView.findViewById(R.id.tv_goals_team_2),
                                        initRecyclerView(R.id.rv_goals_team_2, R.layout.item_goal_right));
        }

        private RecyclerView initRecyclerView( int rvId, int idLayout ){
            RecyclerView rv = itemView.findViewById( rvId );
            LinearLayoutManager mLayoutManager = new LinearLayoutManager( context );
            rv.setLayoutManager(mLayoutManager);
            rv.setAdapter( new GoalsAdapter(context, idLayout) );
            return rv;
        }

        private void setData( Match match ){
            tvStatus.setText(
                    Html.fromHtml( "<b>"+match.getStatus()+"</b> ("+match.getStart()+")" ) );

            teamView1.load(match.getTeam1());
            teamView2.load(match.getTeam2());
        }

        private void updateRecyclerView( RecyclerView rv, List<Goal> goals){
            GoalsAdapter adapter = (GoalsAdapter) rv.getAdapter();
            adapter.setGoasl(goals);
        }
    }



    MatchesAdapter(Context context, List<Match> matches){
        this.context = context;
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from( context )
                .inflate(R.layout.item_match, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData( matches.get( position ) );
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
