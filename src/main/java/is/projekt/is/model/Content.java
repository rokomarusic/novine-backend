package is.projekt.is.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sadrzaj")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sadrzaj_id_gen")
    @SequenceGenerator(name = "sadrzaj_id_gen", sequenceName = "sadrzaj_id_seq", allocationSize = 1, initialValue = 3)
    private Long id;

    @Column(name="naziv")
    private String name;

    @Column(name="tekst")
    private String text;

    @Column(name="slika")
    private String image;

    @Column(name="datum")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "zaposlenik_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="kategorija_id", referencedColumnName = "id")
    private Topic topic;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
