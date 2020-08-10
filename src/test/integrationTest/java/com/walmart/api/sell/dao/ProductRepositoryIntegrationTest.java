package com.walmart.api.sell.dao;

import com.mongodb.*;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class ProductRepositoryIntegrationTest {
    private static final String LOCALHOST = "127.0.0.1";
    private static final String DB_NAME = "products";
    private static final int MONGO_DB_PORT = 27028;
    private static DB db;
    private static MongodExecutable mongodExecutable = null;

    @BeforeAll
    public static void initializeDB() throws IOException {

        MongodStarter starter = MongodStarter.getDefaultInstance();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(LOCALHOST, MONGO_DB_PORT, Network.localhostIsIPv6()))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();

        MongoClient mongo = new MongoClient(LOCALHOST, MONGO_DB_PORT);
        db = mongo.getDB(DB_NAME);
        DBCollection col = db.createCollection("products", new BasicDBObject());
        col.save(new BasicDBObject("id", 181)
                .append("brand", "brand")
                .append("description","description")
                .append("image","image")
                .append("price", 1500));

    }

    @AfterAll
    public static void shutdownDB() throws InterruptedException {
        if (mongodExecutable != null)
            mongodExecutable.stop();
    }

    @Test
    public void shouldReturnJsonWithIdCorrect() {
        DBCollection dBCollection = db.getCollection("products");
        DBCursor cursor = dBCollection.find();
        DBObject object = null;
        while (cursor.hasNext()) {
            object = cursor.next();
        }

        assertEquals(object.get("id"), 181);
    }

    @Test
    public void shouldReturnNullWithIdNoExist() {
        DBCollection dBCollection = db.getCollection("products");

        assertNull(dBCollection.findOne(182));
    }

}
