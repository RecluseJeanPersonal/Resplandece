package app_iglesia.repository.taller;

import app_iglesia.entity.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TallerRepository extends JpaRepository<Taller, UUID> {
}
