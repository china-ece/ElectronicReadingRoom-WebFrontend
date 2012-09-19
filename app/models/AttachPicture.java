package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附图
 */
@Entity
@Table(name = "ZLQLDJ_FT")
public class AttachPicture extends Model {
    /*标号*/
    @Id
    @Column(name = "BH")
    public String id;

    /*名称*/
    @Column(name = "TM")
    public String name;
}
