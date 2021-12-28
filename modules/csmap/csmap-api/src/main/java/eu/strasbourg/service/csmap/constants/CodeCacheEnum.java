package eu.strasbourg.service.csmap.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CodeCacheEnum {
    AGENDA(1, "Agenda"),
    EVENT(2, "Event"),
    CATEGORIES(3, "Categories"),
    TYPE(4, "Type"),
    THEME(5, "Theme"),
    TERRITOIRE(6, "Territoire");

    private long id;
    private String name;

    CodeCacheEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static CodeCacheEnum get(long id) {
        for (CodeCacheEnum codeCacheEnum : values()) {
            if (codeCacheEnum.getId() == id) {
                return codeCacheEnum;
            }
        }
        return null;
    }

    public static List<CodeCacheEnum> getAll() {
        List<CodeCacheEnum> codeCacheEnum = new ArrayList<CodeCacheEnum>();
        codeCacheEnum.addAll(Arrays.asList(values()));
        return codeCacheEnum;
    }
}
