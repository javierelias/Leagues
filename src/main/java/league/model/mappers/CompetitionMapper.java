/**
 * 
 */
package league.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import league.model.Competition;
import league.model.Player;
import league.model.Team;
import league.model.dto.CompetitionDTO;
import league.model.dto.PlayerDTO;
import league.model.dto.TeamDTO;

/**
 * @author javier.elias
 *
 */
@Mapper
public interface CompetitionMapper {

	CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

	@Mapping(source = "area.name", target = "areaname")
	Competition competitionDTOToCompetition(CompetitionDTO source);

	@Mapping(source = "shortName", target = "shortname")
	@Mapping(source = "area.name", target = "areaname")
	Team teamDTOToTeam(TeamDTO source);

	@Mapping(source = "dateOfBirth", target = "dateofbirth")
	@Mapping(source = "countryOfBirth", target = "countryofbirth")
	Player playerDTOToPlayer(PlayerDTO source);
}
