/**
 * 
 */
package league.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import league.model.dto.CompetitionDTO;
import league.model.dto.CompetitionResultDTO;
import league.model.dto.TeamDTO;

/**
 * @author javier.elias
 *
 */
@Component("dataClient")
public class FootballDataClient {

	private static String COMPETITION_URL = "/competitions";
	private static String TEAMS_URL = "/teams";

	@Autowired
	private MicroserviceClient footballDataClient;

	public CompetitionDTO findCompetition(String competitionCode) {

		CompetitionDTO data = footballDataClient.doGet(COMPETITION_URL + "/" + competitionCode, CompetitionDTO.class)
				.getBody();

		return data;
	}

	public CompetitionResultDTO findCompetitionTeams(String competitionCode) {

		CompetitionResultDTO teams = footballDataClient
				.doGet(COMPETITION_URL + "/" + competitionCode + "/teams", CompetitionResultDTO.class).getBody();

		return teams;
	}

	public TeamDTO findTeamPlayers(String teamId) {
		return footballDataClient.doGet(TEAMS_URL + "/" + teamId, TeamDTO.class).getBody();
	}
}
