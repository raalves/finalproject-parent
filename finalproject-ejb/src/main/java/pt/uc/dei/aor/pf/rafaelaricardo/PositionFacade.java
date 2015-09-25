package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.PositionEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Location;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.PositionStatus;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.Source;
import pt.uc.dei.aor.pf.rafaelaricardo.enums.TechnicalArea;

public interface PositionFacade {

	public abstract PositionEntity findPositionById(Long id);

	public abstract List<PositionEntity> findPositionByTitle(String title);

	public abstract List<PositionEntity> findPositionByAdminCreator(
			UserEntity adminCreator);

	public abstract List<PositionEntity> findPositionByManager(
			UserEntity manager);

	public abstract List<PositionEntity> findPositionByGuide(GuideEntity guide);

	public abstract List<PositionEntity> findPositionByLocation(
			Location location);

	public abstract List<PositionEntity> findPositionByPositionStatus(
			PositionStatus positionStatus);

	public abstract List<PositionEntity> findPositionByQuantity(int quantity);

	public abstract List<PositionEntity> findPositionByCompany(String company);

	public abstract List<PositionEntity> findPositionByTechnicalArea(
			TechnicalArea technicalArea);

	public abstract List<PositionEntity> findPositionBySource(Source source);

	public abstract List<PositionEntity> findPositionByOpenningDate(
			Date openningDate);

	public abstract List<PositionEntity> findPositionByClosingDate(
			Date closingDate);

	public abstract List<PositionEntity> findPositionBySLA(int sla);

	public abstract List<PositionEntity> findAllByOrder();
}
