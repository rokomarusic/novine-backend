package is.projekt.is.model;

import javax.persistence.*;

@Entity
@Table(name="odjel")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odjel_id_gen")
    @SequenceGenerator(name = "odjel_id_gen", sequenceName = "odjel_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="naziv")
    private String name;

    @Column(name="kratica")
    private String shortName;

    @Column(name="opis")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
