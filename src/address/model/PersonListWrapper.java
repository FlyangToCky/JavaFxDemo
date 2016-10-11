package address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * JAXB提供两个主要特征：编列(marshal)Java对象到XML的能力，反编列(unmarshal)XML到Java对象
 *
 * JAXB要求使用@XmlRootElement注释作为最顶层的类。
 * personData是ObservableList类，
 * 我们不能把任何注释放到ObservableList上。
 * 因此，我们需要创建另外一个类，它只用于保存Person列表，
 * 用于存储成XML文件。
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}