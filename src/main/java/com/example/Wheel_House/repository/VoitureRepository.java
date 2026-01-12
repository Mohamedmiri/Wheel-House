package com.example.Wheel_House.repository;

import com.example.Wheel_House.model.Voiture;
import com.example.Wheel_House.outile.TypeFuel;
import com.example.Wheel_House.outile.TypeVoiture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VoitureRepository extends JpaRepository<Voiture,Long> {

    List<Voiture> findByTypeVoiture(TypeVoiture typeVoiture);

    List<Voiture> findByTypeFuel(TypeFuel typeFuel);

    List<Voiture> findByMarque(String marque);

    Voiture findByMatricule(String matricule);


}
