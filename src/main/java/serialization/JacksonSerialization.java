package serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JacksonSerialization {


    private static final Logger logger = LoggerFactory.getLogger(JacksonSerialization.class);

    public static void serializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Set mapper to pretty-print
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        //Create objects to serialize
        ModelObjectsCreator objectsCreator = new ModelObjectsCreator();
        List<Employee> employee = objectsCreator.getEmployees();

        //Serialize to file and string
        mapper.writeValue(new File("result." + fileSuffix), employee);
        String jsonString = mapper.writeValueAsString(employee);

        logger.info("Printing serialized original object " + fileSuffix);
        logger.info(jsonString);

        //Deserialize from file
        List<Employee> deserializedEmployee = mapper.readValue(
                new File("result." + fileSuffix), new TypeReference<List<Employee>>() { } );

        //Give a rise
        deserializedEmployee.get(0).setSalary(deserializedEmployee.get(0).getSalary() * 2);

        //Serialize back
        mapper.writeValue(new File("result-modified." + fileSuffix), deserializedEmployee);
        String modifiedJsonString = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing serialized modified object " + fileSuffix);
        logger.info(modifiedJsonString);
    }

    public static void deserializeDemo(ObjectMapper mapper, String fileSuffix) throws IOException {
        //Deserialized employee object from employees.* file in resources
        InputStream employeeIs = JacksonSerialization.class.getClassLoader().
                getResourceAsStream("employee." + fileSuffix);

        //Read value - set class type of serialization
        Employee deserializedEmployee = mapper.readValue(employeeIs, Employee.class);

        //Give eployee big salary
        deserializedEmployee.setSalary(100000);

        String modifiedSerialzied = mapper.writeValueAsString(deserializedEmployee);
        logger.info("Printing modified re-serialized employee to" + fileSuffix);

        logger.info(modifiedSerialzied);
    }

    public static void main(String[] args) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JodaModule());
        serializeDemo(jsonMapper, "json");
        deserializeDemo(jsonMapper, "json");
//        ObjectMapper xmlMapper = new XmlMapper();
//        serializeDemo(xmlMapper, "xml");
//        deserializeDemo(xmlMapper, "xml");

    }
}


