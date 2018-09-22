package br.diabetes.alarmes.remedio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlarmeRemedioRepository extends JpaRepository<AlarmeRemedio, AlarmeRemedioId> {
	@Query(nativeQuery = true, countQuery = "select count(id) from alarme", value = "select * from alarme where id_usuario = :usuarioId")
	AlarmeRemedio findAll(@Param("usuarioId") String usuarioId);

	@Query(nativeQuery = true, countQuery = "select count(id) from alarme", value = "select * from alarme where id_usuario = :usuarioId and id = :id")
	AlarmeRemedio findById(@Param("id") String id, @Param("usuarioId") String usuarioId);

}
