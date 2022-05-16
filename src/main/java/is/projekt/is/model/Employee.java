package is.projekt.is.model;

import javax.persistence.*;

@Entity
@Table(name = "zaposlenik")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zaposlenik_id_gen")
    @SequenceGenerator(name = "zaposlenik_id_gen", sequenceName = "zaposlenik_id_seq", allocationSize = 1)
    private Long id;

    @Column(name="ime")
    private String firstName;

    @Column(name="prezime")
    private String lastName;

    @Column(name="oib")
    private String oib;

    @Column(name="email")
    private String email;

    @Column(name="adresa")
    private String address;

    @Column(name="mobitel")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sef_id", referencedColumnName = "id")
    private Employee boss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="posao_id", referencedColumnName = "id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="odjel_id", referencedColumnName = "id")
    private Branch branch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
