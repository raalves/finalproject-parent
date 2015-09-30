package pt.uc.dei.aor.pf.rafaelaricardo;

import java.util.Date;
import java.util.List;

import pt.uc.dei.aor.pf.rafaelaricardo.entities.GuideEntity;
import pt.uc.dei.aor.pf.rafaelaricardo.entities.UserEntity;

public interface GuideFacade {

	public abstract List<GuideEntity> findAllByOrder();

	public abstract GuideEntity findGuideById(Long id);

	public abstract List<GuideEntity> findGuideByAuthor(UserEntity author);

	public abstract List<GuideEntity> findGuideByTitle(String guideTitle);

	public abstract List<GuideEntity> findGuideByDate(Date guideDate);

	public abstract GuideEntity addGuide(UserEntity author, String guideTitle,
			Date guideDate, String filePath);

	public abstract boolean updateFileGuide(GuideEntity guide, String filePath,
			UserEntity author, Date guideDate);
}
