package me.knut_.application.utils

import redis.clients.jedis.JedisPool

object RedisDatabase {

    private var jedis = JedisPool("172.17.0.2", 6379)
    fun init() {
        // Hier können Sie eine Initialisierung durchführen, z. B. die Verbindung prüfen
        // und gegebenenfalls eine Ausnahme werfen
        jedis.ping()
    }


    fun setValue(key: String, value: String) {
        jedis.sadd(key, value);
    }

    fun getValue(key: String): String? {
        return jedis.get(key)
    }

    // Weitere Datenbank-Operationen können hier hinzugefügt werden

}
