package br.diabetes.glicose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GlicoseRepository extends JpaRepository<Glicose, GlicoseId> {
	@Query(nativeQuery = true, countQuery = "select count(id) from glicose", value = "select * from glicose where id_usuario = :usuarioId")
	Glicose findAll(@Param("usuarioId") String usuarioId);

	@Query(nativeQuery = true, countQuery = "select count(id) from glicose", value = "select * from glicose where id_usuario = :usuarioId and id = :id")
	Glicose findById(@Param("id") String id, @Param("usuarioId") String usuarioId);
}
