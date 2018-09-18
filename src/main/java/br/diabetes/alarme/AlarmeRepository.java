package br.diabetes.alarme;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmeRepository extends JpaRepository<Alarme, AlarmeId>{

}