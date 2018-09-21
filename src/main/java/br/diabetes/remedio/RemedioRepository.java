package br.diabetes.remedio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RemedioRepository extends JpaRepository<Remedio, RemedioId> {
	@Query(nativeQuery = true, countQuery = "select count(id) from remedio", value = "select * from remedio where id_usuario = :usuarioId")
	Remedio findAll(@Param("usuarioId") String usuarioId);

	@Query(nativeQuery = true, countQuery = "select count(id) from remedio", value = "select * from remedio where id_usuario = :usuarioId and id = :id")
	Remedio findById(@Param("id") String id, @Param("usuarioId") String usuarioId);

}
