package br.com.thiengo.superplacar.extras;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.superplacar.MainActivity;
import br.com.thiengo.superplacar.domain.Goal;
import br.com.thiengo.superplacar.domain.Match;
import br.com.thiengo.superplacar.domain.Team;


public class SuperPlacarRequest extends AsyncTask<Void, Void, List<Match>> {
    final private WeakReference<MainActivity> activity;

    public SuperPlacarRequest( MainActivity activity ){
        this.activity = new WeakReference<>( activity );
    }

    @Override
    protected List<Match> doInBackground(Void... voids) {

        List<Match> matches = new ArrayList<>();

        try {
            Document html = Jsoup.connect("http://www.superplacar.com.br/").get();
            Elements time = html.select("div.time-status span.time");
            Elements status = html.select("div.time-status span.status");
            Elements team1_html = html.select("div.team1");
            Elements team2_html = html.select("div.team2");

            for( int i = 0; i < time.size(); i++ ){

                Team team1 = getTeam( team1_html.get(i), true );
                Team team2 = getTeam( team2_html.get(i), false );
                Match match = new Match();

                match.setStart( time.get(i).text() );
                match.setStatus( status.get(i).text() );
                match.setTeam1(team1);
                match.setTeam2(team2);
                matches.add( match );
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return matches;
    }

    private Team getTeam(Element timeTag, boolean home ){
        int idx = home ? 1 : 2;
        Team team = new Team();
        team.setName( timeTag.select("span.team"+idx+"-name").text() );
        team.setImageUrl( timeTag.select("img").attr("src") );

        String goalsString = timeTag.select("span.team"+idx+"-score").text();
        int goals = goalsString.isEmpty() ? 0 : Integer.parseInt( goalsString );
        team.setGoals( goals );

        team.getGoalsList().addAll( getGoalsList( timeTag ) );
        return team;
    }

    private List<Goal> getGoalsList(Element timeTag ){
        Elements goalsList = timeTag.select("ul.goal-players li");
        List<Goal> goals = new ArrayList<>();

        for( Element g : goalsList ){
            Goal goal = new Goal();
            goal.setName( g.select(".name").text() );
            goal.setTeam( g.select(".time").text() );
            goals.add(goal);
        }
        return goals;
    }

    @Override
    protected void onPostExecute(List<Match> matches) {
        super.onPostExecute( matches );

        if( activity.get() != null ){
            activity.get().update( matches );
        }
    }
}