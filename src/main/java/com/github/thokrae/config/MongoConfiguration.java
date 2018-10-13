package com.github.thokrae.config;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host + ":" + port, createOptions());
    }

    private MongoClientOptions createOptions() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true)
                .build();

        /*
        CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry());
        EnumCodecProvider enumCodecProvider = new EnumCodecProvider(defaultCodecRegistry);
        */

        CodecRegistry registry = CodecRegistries.fromRegistries(
                createCustomCodecRegistry(),
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider)
        );

        return MongoClientOptions.builder()
                .codecRegistry(registry)
                .build();
    }

    private CodecRegistry createCustomCodecRegistry() {
        return CodecRegistries.fromCodecs(
                new ZonedDateTimeCodec()
        );
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
