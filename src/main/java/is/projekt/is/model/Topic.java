package is.projekt.is.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="kategorija")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kategorija_id_gen")
    @SequenceGenerator(name = "kategorija_id_gen", sequenceName = "kategorija_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="naziv")
    private String name;

    @Column(name="kratica")
    private String shortName;

    @OneToMany(
            mappedBy = "topic",
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Content> contentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }
}
