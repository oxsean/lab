package cn.lvu.service;

import cn.lvu.model.Region;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-5-16
 */
public interface RegionService {

    Region getRegion(Integer id);

    Region getRegionWithChildren(Integer id);

    Region saveRegion(Region region);

    void removeRegion(Integer id);
}
