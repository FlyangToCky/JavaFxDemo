package address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * JAXB�ṩ������Ҫ����������(marshal)Java����XML��������������(unmarshal)XML��Java����
 *
 * JAXBҪ��ʹ��@XmlRootElementע����Ϊ�����ࡣ
 * personData��ObservableList�࣬
 * ���ǲ��ܰ��κ�ע�ͷŵ�ObservableList�ϡ�
 * ��ˣ�������Ҫ��������һ���࣬��ֻ���ڱ���Person�б���
 * ���ڴ洢��XML�ļ���
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