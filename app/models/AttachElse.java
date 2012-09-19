package models;

import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附其他
 */
@Entity
@Table(name = "ZLQLDJ_FJ")
public class AttachElse extends Model {
    /*标号*/
    @Id
    @Column(name = "BH")
    public String id;

    /*名称*/
    @Column(name = "MC")
    public String name;
}
