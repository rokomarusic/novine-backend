package is.projekt.is.model;

import javax.persistence.*;

@Entity
@Table(name="prodavac")
public class Advertiser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prodavac_id_gen")
    @SequenceGenerator(name = "prodavac_id_gen", sequenceName = "prodavac_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="naziv")
    private String name;

    @Column(name="email")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
