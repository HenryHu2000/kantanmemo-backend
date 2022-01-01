package org.skygreen.kantanmemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Example JPA entity.
 * <p>
 * To use it, get access to a JPA EntityManager via injection.
 * <p>
 * {@code
 *
 * @Inject EntityManager em;
 * <p>
 * public void doSomething() {
 * MyEntity entity1 = new MyEntity();
 * entity1.setField("field-1");
 * em.persist(entity1);
 * <p>
 * List<MyEntity> entities = em.createQuery("from MyEntity", MyEntity.class).getResultList();
 * }
 * }
 */
@Entity
@CsvRecord(separator = ",", skipFirstLine = true)
public class Word {
    private Long id;
    @DataField(pos=1)
    private String name;
    @DataField(pos=2)
    private String pronunciation;
    @DataField(pos=3)
    private String meaning;

    @Id
    @GeneratedValue
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

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
