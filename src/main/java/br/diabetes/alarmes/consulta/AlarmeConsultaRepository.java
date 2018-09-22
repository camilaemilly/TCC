package br.diabetes.alarmes.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlarmeConsultaRepository extends JpaRepository<AlarmeConsulta, AlarmeConsultaId> {
	@Query(nativeQuery = true, countQuery = "select count(id) from alarme", value = "select * from alarme where id_usuario = :usuarioId")
	AlarmeConsulta findAll(@Param("usuarioId") String usuarioId);

	@Query(nativeQuery = true, countQuery = "select count(id) from alarme", value = "select * from alarme where id_usuario = :usuarioId and id = :id")
	AlarmeConsulta findById(@Param("id") String id, @Param("usuarioId") String usuarioId);

}
