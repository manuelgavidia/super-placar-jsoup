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
import br.com.thiengo.superplacar.domain.Match;
import br.com.thiengo.superplacar.domain.Team;


public class FifaRequest extends AsyncTask<Void, Void, List<Match>> {
    final private WeakReference<MainActivity> mActivity;

    public FifaRequest(MainActivity activity) {
        this.mActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Match> doInBackground(Void... voids) {

        List<Match> matches = new ArrayList<>();

        try {
            Document html = Jsoup.connect("https://www.fifa.com/worldcup/matches").get();
            Elements m_fixtures = html.select("div.fi-mu.fixture");
            for (Element fix : m_fixtures) {

                Team home = getTeam(fix.select("div.fi-t.fi-i--4.home"));
                Team away = getTeam(fix.select("div.fi-t.fi-i--4.away"));
                Match match = new Match(home, away);

                match.setStart(fix.select("fi-mu__info__datetime").text());
                matches.add(match);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches;
    }

    private Team getTeam(Elements tag) {
        return new Team(tag.select("div.fi-t__n span.fi-t__nText").text(),
                tag.select("div.fi-t__i  img").attr("src"));
    }

    @Override
    protected void onPostExecute(List<Match> matches) {
        super.onPostExecute(matches);

        if (mActivity.get() != null) {
            mActivity.get().update(matches);
        }
    }
}