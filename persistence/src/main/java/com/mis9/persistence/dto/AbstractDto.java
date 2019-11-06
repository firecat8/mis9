package com.mis9.persistence.dto;

import com.mis9.domain.Entity;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author gdimitrova
 */
@MappedSuperclass
public abstract class AbstractDto implements Entity, Serializable {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "increment")
    protected int id;

    @Override
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
    
}
