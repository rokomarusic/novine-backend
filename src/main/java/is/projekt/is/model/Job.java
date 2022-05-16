package is.projekt.is.model;

import javax.persistence.*;

@Entity
@Table(name="posao")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posao_id_gen")
    @SequenceGenerator(name = "posao_id_gen", sequenceName = "posao_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="naziv")
    private String name;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
