package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附表
 */
@Entity
@Table(name = "ZLQLDJ_FB")
public class AttachForm extends Model {

    /*标号*/
    @Id
    @Column(name = "BH")
    public String id;

    /*名称*/
    @Column(name = "FB_MC")
    public String name;
}
