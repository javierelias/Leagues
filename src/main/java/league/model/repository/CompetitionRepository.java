/**
 * 
 */
package league.model.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import league.model.Competition;

/**
 * @author javier.elias
 *
 */
@Repository
@Transactional
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	Competition findByCode(String code);

	@Query("select count(p.id) from Competition c join c.teams t join t.squad p where c.code = :code")
	Long countCompetitionPlayerIdByCode(@Param("code") String code);

}
