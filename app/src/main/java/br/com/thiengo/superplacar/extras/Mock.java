package br.com.thiengo.superplacar.extras;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.superplacar.domain.Goal;
import br.com.thiengo.superplacar.domain.Match;
import br.com.thiengo.superplacar.domain.Team;


public class Mock {

    private static List<Goal> genGoals(int qtd){
        String[] times = {"16'1T", "35'1T", "01'2T", "21'2T"};
        String[] nomes = {
                "Fernando",
                "Michael",
                "Léo Castro",
                "João Paulo"
        };
        List<Goal> goals = new ArrayList<>();

        for( int i = 0; i < qtd; i++ ){
            int randomPos = (int) (Math.random() * 4);
            Goal g = new Goal();

            g.setTeam( times[randomPos] );
            g.setName( nomes[randomPos] );
            goals.add( g );
        }

        return goals;
    }

    private static Team genTeam(int rank){
        String[] nomes = {"Rio Claro", "São Caetano", "S. J. Campos", "Nacional-SP"};
        String[] imagens = {
            "http://www.superplacar.com.br/images/escudos/f1eab3ac03d333dc76278b2f7989bace-68.png",
            "http://www.superplacar.com.br/images/escudos/173fb38f10e9a24e7cc665e513575bf2-68.png",
            "http://www.superplacar.com.br/images/escudos/a4cd88615deb2decbe7515b74849bee9-68.png",
            "http://www.superplacar.com.br/images/escudos/42ecf680e39db12f2ba513263694d1bc-68.PNG"
        };
        int[] goals = {0, 2, 1, 0};

        Team team = new Team(nomes[rank], imagens[rank]);
        team.setGoals( goals[ rank ] );
        team.getGoalsList().addAll( genGoals( goals[ rank ] ) );

        return team;
    }

    private static Match genMatch(int rank){
        String[] status = {"Em andamento", "Em breve", "Encerrado"};
        String[] inicios = {"16:55", "19:00", "20:00"};
        Match jogo = new Match(genTeam( rank ), genTeam(rank + 1));
        jogo.setStatus( status[rank] );
        jogo.setStart( inicios[rank] );

        return jogo;
    }

    public static ArrayList<Match> genMatches(){
        ArrayList<Match> jogos = new ArrayList<>();
        jogos.add( genMatch(0) );
        jogos.add( genMatch(2) );

        return jogos;
    }
}
