package se325.example07.parolee.services;

import org.apache.commons.lang3.SerializationUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Produces(SerializationMessageBodyReaderAndWriter.APPLICATION_JAVA_SERIALIZED_OBJECT) //give it a produces annotation to let jaxrs know that we can use this class to serialise java object into serialised java object
@Consumes(SerializationMessageBodyReaderAndWriter.APPLICATION_JAVA_SERIALIZED_OBJECT) //consumes lets us know that htis class can deserialise
public class SerializationMessageBodyReaderAndWriter implements
        MessageBodyReader<Serializable>, MessageBodyWriter<Serializable> {

    public static final String APPLICATION_JAVA_SERIALIZED_OBJECT = "application/java-serialization";//we want to support java serialised classes as a data interchange format
    public static final MediaType APPLICATION_JAVA_SERIALIZED_OBJECT_TYPE = MediaType
            .valueOf(APPLICATION_JAVA_SERIALIZED_OBJECT);

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
                              Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(APPLICATION_JAVA_SERIALIZED_OBJECT_TYPE)
                && Serializable.class.isAssignableFrom(type);
    }

    @Override
    public Serializable readFrom(Class<Serializable> type, Type genericType,
                                 Annotation[] annotations, MediaType mediaType,
                                 MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
        return (Serializable) SerializationUtils.deserialize(entityStream);
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(APPLICATION_JAVA_SERIALIZED_OBJECT_TYPE)
                && Serializable.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Serializable o, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Serializable o, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) {
        SerializationUtils.serialize(o, entityStream);
    }
}
