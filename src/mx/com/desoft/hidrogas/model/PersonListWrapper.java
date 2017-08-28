package mx.com.desoft.hidrogas.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 *
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<EmpleadoDTO> persons;

    @XmlElement(name = "person")
    public List<EmpleadoDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<EmpleadoDTO> persons) {
        this.persons = persons;
    }
}