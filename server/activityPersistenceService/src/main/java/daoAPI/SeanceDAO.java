package daoAPI;

import activity.Seance;

import java.util.List;
import java.util.Optional;

public interface SeanceDAO {
    Optional<Seance> get(long id);
    List<Seance> getAll();

    void save(Seance seance);

    void update(Seance seance, Seance newSeance);

    void delete(Seance seance);
}
