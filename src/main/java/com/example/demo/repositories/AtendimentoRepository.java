
package com.example.demo.repositories;

import com.example.demo.models.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findAllByOrderByDataAtendimentoAsc();

}
