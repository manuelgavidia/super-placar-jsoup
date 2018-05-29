package br.com.thiengo.superplacar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import br.com.thiengo.superplacar.domain.Goal;


public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {
    private final Context context;
    private final int idLayout;
    private final List<Goal> goals;

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvTime;
        final TextView tvNome;

        ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvNome = itemView.findViewById(R.id.tv_nome);
        }

        private void setData( Goal goal){
            tvTime.setText( goal.getTeam() );
            tvNome.setText( goal.getName() );
        }
    }

    GoalsAdapter(Context context, int idLayout){
        this.context = context;
        this.idLayout = idLayout;
        this.goals = new ArrayList<>();
    }

    @NonNull
    @Override
    public GoalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from( context )
                .inflate( idLayout, parent, false );

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData( goals.get( position ) );
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public void setGoasl( List<Goal> goals){
        this.goals.clear();
        this.goals.addAll(goals);
        notifyDataSetChanged();
    }
}
