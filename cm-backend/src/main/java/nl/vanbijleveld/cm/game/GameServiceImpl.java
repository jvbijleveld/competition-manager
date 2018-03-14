package nl.vanbijleveld.cm.game;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import javassist.NotFoundException;
import nl.vanbijleveld.cm.api.GameService;
import nl.vanbijleveld.cm.team.Team;
import nl.vanbijleveld.cm.team.TeamRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Game getGame(long id) throws NotFoundException {
        GameEnt gameEnt = gameRepository.findOneById(id);
        if (gameEnt == null) {
            LOGGER.error("Game with id " + id + " is not found");
            throw new NotFoundException("Game with id " + id + " is not found");
        }
        return GameFactory.build(gameEnt);
    }

    @Override
    public List<Game> getGamesByStatus(GameStatusEnum status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Game createNewGame(List<Team> teamList) {
        Game game = new Game();
        List<GameMembersEnt> contenders = new ArrayList<GameMembersEnt>();

        for (Team team : teamList) {
            GameMembersEnt memberEnt = new GameMembersEnt();
            memberEnt.setGame(GameEntWrapper.wrap(game));
            memberEnt.setTeam(teamRepository.findOne(team.getId()));

            contenders.add(memberEnt);
        }
        game.setContenders(contenders);
        GameEnt gameEnt = gameRepository.save(GameEntWrapper.wrap(game));
        return GameFactory.build(gameEnt);
    }

    @Override
    public Game addTeamsToGame(long id, List<Team> contenders) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return GameFactory.build(gameRepository.findAll());
    }

}
