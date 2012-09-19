package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 其他资料
 */
@Entity
@Table(name = "ZLQLDJ_QT")
public class OtherData extends Model {
    /*标号*/
    @Id
    @Column(name = "BH")
    public String id;

    /*名称*/
    @Column(name = "QT_MC")
    public String name;
}
