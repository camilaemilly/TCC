package br.diabetes.permissao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PermissaoRepository extends JpaRepository<Permissao, PermissaoId> {
	@Query(nativeQuery = true, countQuery = "select count(id) from permissao", value = "select * from permissao where id_usuario = :usuarioId")
	Permissao findAll(@Param("usuarioId") String usuarioId);

	@Query(nativeQuery = true, countQuery = "select count(id) from permissao", value = "select * from permissao where id_usuario = :usuarioId and id = :id")
	Permissao findById(@Param("id") String id, @Param("usuarioId") String usuarioId);
}
