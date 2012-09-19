package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附正文
 */
@Entity
@Table(name = "ZLQLDJ_ZW")
public class AttachDocument extends Model {

    /*标号*/
    @Id
    @Column(name = "BH")
    public String id;

    /*名称*/
    @Column(name = "ZW_MC")
    public String name;
}
