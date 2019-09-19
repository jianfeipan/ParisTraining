package daoAPI;

import activity.Seance;
import activityApi.ISeance;

import java.util.List;
import java.util.Optional;

public interface ISeanceDAO {
    Optional<ISeance> get(long id);
    List<ISeance> getAll();

    void save(ISeance seance);

    void update(ISeance seance, ISeance newSeance);

    void delete(ISeance seance);
}
