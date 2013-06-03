package cn.lvu.dao;

import cn.lvu.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-3-18
 */
public interface RegionDAO extends JpaRepository<Region, Integer>, JpaSpecificationExecutor<Region> {
}
