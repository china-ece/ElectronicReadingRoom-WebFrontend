package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 地质资料
 */
@Entity
@Table(name = "AJJML_ZB")
public class GeoData extends Model implements Comparable {

    /*资料编号*/
    @Id
    @Column(name = "pkiib")
    public String id;

    /*资料名称*/
    @Column(name = "pkiia")
    public String name;

    /*资料类别*/
    @Column(name = "zllbmc")
    public String category;

    /*工作程度*/
    @Column(name = "gzcdmc")
    public String jobProgress;

    /*行政区划1*/
    @Column(name = "xzqmc1")
    public String location;

    /*形成单位*/
    @Column(name = "pkiif")
    public String department;

    /*形成时间*/
    @Column(name = "xcsj")
    public Date finishDate;

    @Override
    public int compareTo(Object o) {
        return this.id.equals(((GeoData)o).id)?0:1;
    }

    public void clean() {
        id = id.trim();
        name = name.trim();
        category = category.trim();
        jobProgress = jobProgress.trim();
        location = location.trim();
        department = department.trim();
        if(department.length() == 0)
            department = "未知";
    }
}
