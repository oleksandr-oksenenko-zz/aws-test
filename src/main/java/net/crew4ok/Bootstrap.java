package net.crew4ok;

import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class Bootstrap implements SparkApplication {

    public static void main(String[] args) {
        initRoutes();
    }

    @Override
    public void init() {
        initRoutes();
    }

    private static void initRoutes() {
        get("/", (req, res) -> "Hello, world!");
    }
}
