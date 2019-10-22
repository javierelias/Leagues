/**
 * 
 */
package league.model.dto;

import java.util.List;

/**
 * @author javier.elias
 *
 */
public class CompetitionResultDTO {

	private int count;
	private CompetitionDTO competition;
	private List<TeamDTO> teams;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CompetitionDTO getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionDTO competition) {
		this.competition = competition;
	}

	public List<TeamDTO> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamDTO> teams) {
		this.teams = teams;
	}

}
