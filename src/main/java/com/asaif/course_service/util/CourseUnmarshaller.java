package com.asaif.course_service.util;

import generated.Courses;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;

public class CourseUnmarshaller {
    public static Courses unmarshalCourses(String xmlResponse) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Courses.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlResponse);
        return (Courses) unmarshaller.unmarshal(reader);
    }
}