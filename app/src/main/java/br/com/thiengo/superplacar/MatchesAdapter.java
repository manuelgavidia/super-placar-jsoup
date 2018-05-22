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


public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    final private Context context;
    final private List<Match> matches;

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvStatus;
        final ImageView ivTeam1;
        final TextView tvNameTeam1;
        final TextView tvGoalsTeam1;
        final RecyclerView rvTeam1;
        final ImageView ivTeam2;
        final TextView tvGoalsTeam2;
        final TextView tvNameTeam2;
        final RecyclerView rvTeam2;

        ViewHolder(View itemView) {
            super(itemView);

            tvStatus =    itemView.findViewById(R.id.tv_status);
            ivTeam1 =     itemView.findViewById(R.id.iv_team_1);
            tvNameTeam1 = itemView.findViewById(R.id.tv_name_team_1);
            tvGoalsTeam1 = itemView.findViewById(R.id.tv_goals_team_1);
            rvTeam1 = initRecyclerView( R.id.rv_goals_team_1, R.layout.item_goal_left);

            ivTeam2 =       itemView.findViewById(R.id.iv_time_2);
            tvGoalsTeam2 =   itemView.findViewById(R.id.tv_goals_team_2);
            tvNameTeam2 =   itemView.findViewById(R.id.tv_nome_time_2);
            rvTeam2 = initRecyclerView( R.id.rv_goals_team_2, R.layout.item_goal_right);
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

            Picasso.with( context )
                    .load( match.getTeam1().getImageUrl() )
                    .into(ivTeam1);
            tvNameTeam1.setText( String.valueOf( match.getTeam1().getName() ) );
            tvGoalsTeam1.setText( String.valueOf( match.getTeam1().getGoals() ) );
            updateRecyclerView(rvTeam1, match.getTeam1().getGoalsList() );

            Picasso.with( context )
                    .load( match.getTeam2().getImageUrl() )
                    .into(ivTeam2);
            tvNameTeam2.setText( String.valueOf( match.getTeam2().getName() ) );
            tvGoalsTeam2.setText( String.valueOf( match.getTeam2().getGoals() ) );
            updateRecyclerView(rvTeam2, match.getTeam2().getGoalsList() );
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
