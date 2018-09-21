package br.diabetes.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultaRepository extends JpaRepository<Consulta, ConsultaId> {
	@Query(nativeQuery = true, countQuery = "select count(id) from consulta", value = "select * from consulta where id_usuario = :usuarioId")
	Consulta findAll(@Param("usuarioId") String usuarioId);

	@Query(nativeQuery = true, countQuery = "select count(id) from consulta", value = "select * from consulta where id_usuario = :usuarioId and id = :id")
	Consulta findById(@Param("id") String id, @Param("usuarioId") String usuarioId);
}
