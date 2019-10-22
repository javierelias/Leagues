package league.controllers.services;

/**
 * @author javier.elias
 *
 */
public interface LeagueService {

	void importCompetition(String competitionCode);

	boolean competitionExists(String competitionCode);

	boolean competitionImported(String competitionCode);

	Long countTotalPlayers(String competitionCode);
}
