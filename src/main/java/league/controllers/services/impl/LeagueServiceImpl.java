/**
 * 
 */
package league.controllers.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;

import league.clients.FootballDataClient;
import league.controllers.services.LeagueService;
import league.model.Competition;
import league.model.dto.CompetitionDTO;
import league.model.dto.CompetitionResultDTO;
import league.model.dto.TeamDTO;
import league.model.mappers.CompetitionMapper;
import league.model.repository.CompetitionRepository;

/**
 * @author javier.elias
 *
 */
@Service("LeagueService")
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private FootballDataClient footballDataClient;

	@Autowired
	private CompetitionRepository competitionRepository;

	@Override
	public void importCompetition(String competitionCode) {

		// Find the competition teams
		CompetitionResultDTO result = this.footballDataClient.findCompetitionTeams(competitionCode);

		if (!CollectionUtils.isEmpty(result.getTeams())) {
			List<Long> teamIds = new ArrayList<>();
			for (TeamDTO teamDTO : result.getTeams()) {
				// Find each team players
				teamIds.add(teamDTO.getId());
			}

			int i = 1;
			List<TeamDTO> teams = new ArrayList<>();
			if (!CollectionUtils.isEmpty(teamIds)) {
				for (Long id : teamIds) {
					TeamDTO team = this.footballDataClient.findTeamPlayers(id.toString());
					teams.add(team);
					i++;
					if (i == 10) {
						i = 0;
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			CompetitionDTO competitionDTO = result.getCompetition();
			competitionDTO.setTeams(teams);

			Competition competition = CompetitionMapper.INSTANCE.competitionDTOToCompetition(competitionDTO);

			this.competitionRepository.save(competition);
		}

	}

	@Override
	public boolean competitionExists(String competitionCode) {

		boolean exists = false;

		try {
			// Find the competition teams
			CompetitionDTO result = this.footballDataClient.findCompetition(competitionCode);
			if (null != result) {
				exists = true;
			}
		} catch (Exception ex) {
			if (((HttpClientErrorException) ex).getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				exists = false;
			}
		}
		return exists;
	}

	@Override
	public boolean competitionImported(String competitionCode) {
		boolean imported = true;
		Competition c = this.competitionRepository.findByCode(competitionCode);
		if (null == c) {
			imported = false;
		}
		return imported;
	}

	@Override
	public Long countTotalPlayers(String competitionCode) {
		return this.competitionRepository.countCompetitionPlayerIdByCode(competitionCode);
	}

}
