/**
 * 
 */
package league.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import league.controllers.services.LeagueService;
import league.model.dto.TotalPlayersResponseDTO;

/**
 * @author javier.elias
 *
 */
@RestController
@RequestMapping(value = "/")
public class LeaguesController {

	@Autowired
	private LeagueService leagueService;

	@RequestMapping(value = "import-league/{CL}")
	public ResponseEntity<String> importLeague(@PathVariable(name = "CL") String leagueCode) {

		try {
			if (!this.leagueService.competitionImported(leagueCode)) {

				if (this.leagueService.competitionExists(leagueCode)) {
					this.leagueService.importCompetition(leagueCode);

					return ResponseEntity.status(HttpStatus.CREATED).body("Successfully imported");
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
				}
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("League already imported");
			}
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Server Error");
		}
	}

	@RequestMapping(value = "total-players/{CL}")
	public ResponseEntity<TotalPlayersResponseDTO> getLeagueTotalPlayers(@PathVariable(name = "CL") String leagueCode) {

		TotalPlayersResponseDTO response = new TotalPlayersResponseDTO();
		if (this.leagueService.competitionImported(leagueCode)) {

			Long total = this.leagueService.countTotalPlayers(leagueCode);
			if (null != total) {
				response.setTotal(total);
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok().body(response);
	}

}
