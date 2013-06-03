package cn.lvu.service.impl;

import cn.lvu.dao.RegionDAO;
import cn.lvu.model.Region;
import cn.lvu.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-6-3
 */
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDAO regionDAO;

    @Override
    public Region getRegion(Integer id) {
        if (id == null) {
            return regionDAO.findOne(new Specification<Region>() {
                @Override
                public Predicate toPredicate(Root<Region> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return root.get("parent").isNull();
                }
            });
        }
        return regionDAO.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Region getRegionWithChildren(Integer id) {
        Region region = getRegion(id);
        if (region != null) {
            initChildren(region);
        }
        return region;
    }

    private void initChildren(Region region) {
        if (region.hasChildren()) {
            for (Region r : region.getChildren()) {
                initChildren(r);
            }
        }
    }

    @Override
    @Transactional
    public Region saveRegion(Region region) {
        return regionDAO.save(region);
    }

    @Override
    @Transactional
    public void removeRegion(Integer id) {
        regionDAO.delete(id);
    }
}
