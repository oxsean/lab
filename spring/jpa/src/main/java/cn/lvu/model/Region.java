package cn.lvu.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 11-6-1
 */
@Entity
@Table(name = "t_region")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Region implements Serializable {
    private static final long serialVersionUID = 1546227185241428988L;
    @Id
    @Column(length = 32)
    private Integer id;
    @Column(length = 256, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Region parent;
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL})
    @OrderBy("id")
    private List<Region> children;
    @Column(name = "region_level", nullable = false)
    private RegionLevel level;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String geometry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JSONField(serialize = false)
    public Region getParent() {
        return parent;
    }

    public void setParent(Region parent) {
        this.parent = parent;
    }

    public List<Region> getChildren() {
        return children;
    }

    public void setChildren(List<Region> children) {
        this.children = children;
    }

    public RegionLevel getLevel() {
        return level;
    }

    public void setLevel(RegionLevel level) {
        this.level = level;
    }

    @JSONField(serialize = false)
    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public boolean hasChildren() {
        return CollectionUtils.isNotEmpty(children);
    }
}
